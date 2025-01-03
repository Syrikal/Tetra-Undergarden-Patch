package com.syric.undergardenpatch;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
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


/**
 * Implementation of an effect which deals .
 */
public class ThrenodyLiteEffect {
    public static final ItemEffect threnody_lite = ItemEffect.get("undergardenpatch:threnody_lite");

    /**
     * Event handler which checks if the mainhand item has our item effect
     * @param event
     */
    @SubscribeEvent
    public void attackEvent(LivingHurtEvent event) {
        Entity source = event.getSource().getEntity();
        float damage = event.getAmount();

        if (source instanceof Player player) {
            ItemStack heldStack = player.getMainHandItem();

            if (heldStack.getItem() instanceof ModularItem item) {
                int level = item.getEffectLevel(heldStack, threnody_lite);
                boolean forgor = item.getEffectLevel(heldStack, ThrenodyEffect.threnody) > 0;
                boolean rotbane = item.getEffectLevel(heldStack, RotbaneEffect.rotbane) > 0;

                boolean isUndergarden = Objects.requireNonNull(ForgeRegistries.ENTITY_TYPES.getKey(event.getEntity().getType())).getNamespace().equals("undergarden");

                if (level > 0 && !forgor && !rotbane && isUndergarden && event.getEntity().canChangeDimensions()) {
                    event.setAmount(damage * 1.25F);
//                    player.sendMessage(new StringTextComponent("2x Damage to Undergarden Denizens"), player.getUUID());
                }
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static void addBars(FMLClientSetupEvent event) {
        var threnodyGetter = new StatGetterEffectLevel(threnody_lite, 1.0);
        GuiStatBar threnodyBar = new GuiStatBar(0, 0, 59, "tetra.stats.threnody_lite", 0.0, 1.0, false, threnodyGetter, LabelGetterBasic.noLabel, new TooltipGetterNone("tetra.stats.threnody_lite.tooltip"));

        WorkbenchStatsGui.addBar(threnodyBar);
        HoloStatsGui.addBar(threnodyBar);
        UndergardenPatch.LOGGER.debug("Triggered Threnody adding bars");
    }
}
