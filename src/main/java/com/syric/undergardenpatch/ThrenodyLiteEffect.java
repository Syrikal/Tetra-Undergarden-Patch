package com.syric.undergardenpatch;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import quek.undergarden.registry.UGTags;
import se.mickelus.tetra.blocks.workbench.gui.WorkbenchStatsGui;
import se.mickelus.tetra.effect.ItemEffect;
import se.mickelus.tetra.gui.stats.bar.GuiStatBar;
import se.mickelus.tetra.gui.stats.getter.IStatGetter;
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

        if (source instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) source;
            ItemStack heldStack = player.getMainHandItem();

            if (heldStack.getItem() instanceof ModularItem) {
                ModularItem item = (ModularItem) heldStack.getItem();
                int level = item.getEffectLevel(heldStack, threnody_lite);
                boolean forgor = item.getEffectLevel(heldStack, ThrenodyEffect.threnody) > 0;
                boolean rotbane = item.getEffectLevel(heldStack, RotbaneEffect.rotbane) > 0 && event.getEntityLiving().getType().is(UGTags.Entities.ROTSPAWN);
                boolean isUndergarden = Objects.requireNonNull(event.getEntityLiving().getType().getRegistryName()).getNamespace().equals("undergarden");

                if (level > 0 && !forgor && !rotbane && isUndergarden && event.getEntityLiving().canChangeDimensions()) {
                    event.setAmount(damage * 1.5F);
                }
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static void addBars(FMLClientSetupEvent event) {
        IStatGetter threnodyGetter = new StatGetterEffectLevel(threnody_lite, 1.0);
        GuiStatBar threnodyBar = new GuiStatBar(0, 0, 59, "tetra.stats.threnody_lite", 0.0, 1.0, false, threnodyGetter, LabelGetterBasic.noLabel, new TooltipGetterNone("tetra.stats.threnody_lite.tooltip"));

        WorkbenchStatsGui.addBar(threnodyBar);
        HoloStatsGui.addBar(threnodyBar);
        UndergardenPatch.LOGGER.debug("Triggered Threnody adding bars");
    }
}
