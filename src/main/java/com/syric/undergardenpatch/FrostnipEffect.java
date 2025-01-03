package com.syric.undergardenpatch;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.effect.ItemEffect;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.IStatGetter;
import se.mickelus.tetra.gui.stats.getter.LabelGetterBasic;
import se.mickelus.tetra.gui.stats.getter.StatGetterEffectLevel;
import se.mickelus.tetra.gui.stats.getter.TooltipGetterNone;
import se.mickelus.tetra.items.modular.ModularItem;
import se.mickelus.tetra.items.modular.impl.holo.gui.craft.HoloStatsGui;

/**
 * Implementation of a slowing effect.
 */
public class FrostnipEffect {
    public static final ItemEffect frostnip = ItemEffect.get("undergardenpatch:frostnip");

    /**
     * Event handler which checks if the mainhand item has our item effect
     * @param event
     */
    @SubscribeEvent
    public void attackEvent(LivingHurtEvent event) {
        Entity source = event.getSource().getEntity();

        if (source instanceof Player player) {
            ItemStack heldStack = player.getMainHandItem();

            if (heldStack.getItem() instanceof ModularItem item) {
                int level = item.getEffectLevel(heldStack, frostnip);
                int bitelevel = item.getEffectLevel(heldStack, FrostbiteEffect.frostbite);

                if (level > 0 && bitelevel == 0) {
                    event.getEntityLiving().addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 600, 1));
//                    player.sendMessage(new StringTextComponent("Applied Slowness 1"), player.getUUID());
                }
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static void addBars(FMLClientSetupEvent event) {
        IStatGetter frostnipGetter = new StatGetterEffectLevel(frostnip, 1.0);
        GuiStatBar frostnipBar = new GuiStatBar(0, 0, 59, "tetra.stats.frostnip", 0.0, 1.0, false, frostnipGetter, LabelGetterBasic.noLabel, new TooltipGetterNone("tetra.stats.frostnip.tooltip"));

        WorkbenchStatsGui.addBar(frostnipBar);
        HoloStatsGui.addBar(frostnipBar);
    }
}
