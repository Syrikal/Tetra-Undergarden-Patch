package com.syric.undergardenpatch;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.effect.ItemEffect;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.LabelGetterBasic;
import se.mickelus.tetra.gui.stats.getter.StatGetterEffectLevel;
import se.mickelus.tetra.gui.stats.getter.TooltipGetterNone;
import se.mickelus.tetra.items.modular.ModularItem;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

import java.util.Objects;

public class UndermineEffect {
    public static final ItemEffect undermine = ItemEffect.get("undergardenpatch:undermine");

    /**
     * @param event
     * Event handler which checks if the mainhand item has our item effect
     */
    @SubscribeEvent
    public void mineEvent(PlayerEvent.BreakSpeed event) {
        Player player = event.getEntity();
        ItemStack heldStack = player.getMainHandItem();
        if (heldStack.getItem() instanceof ModularItem item) {
            int level = item.getEffectLevel(heldStack, undermine);

            boolean isUndergarden = Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(event.getState().getBlock())).getNamespace().equals("undergarden");

            if (level > 0 && isUndergarden) {
                event.setNewSpeed(event.getOriginalSpeed() * 1.5F);
//                    UndergardenPatch.LOGGER.info("Set dig speed to: " + event.getOriginalSpeed() * 1.5F);
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static void addBars(FMLClientSetupEvent event) {
        var undermineGetter = new StatGetterEffectLevel(undermine, 1.0);
        GuiStatBar undermineBar = new GuiStatBar(0, 0, 59, "tetra.stats.undermine", 0.0, 1.0, false, undermineGetter, LabelGetterBasic.noLabel, new TooltipGetterNone("tetra.stats.undermine.tooltip"));

        WorkbenchStatsGui.addBar(undermineBar);
        HoloStatsGui.addBar(undermineBar);
    }
}