package com.undergardenpatch.undergardenpatch;

import com.google.common.collect.Sets;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import se.mickelus.tetra.effect.ItemEffectHandler;
import se.mickelus.tetra.items.modular.ModularItem;
import se.mickelus.tetra.effect.ItemEffect;


/**
 * Implementation of a slowing effect.
 */
public class FrostbiteEffect {
    private static final ItemEffect frostbite = ItemEffect.get("undergardenpatch:frostbite");

    /**
     * Event handler which checks if the mainhand item has our item effect
     * @param event
     */
    @SubscribeEvent
    public void attackEvent(LivingHurtEvent event) {
        Entity source = event.getSource().getTrueSource();

        if (source instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) source;
            ItemStack heldStack = player.getHeldItemMainhand();

            if (heldStack.getItem() instanceof ModularItem) {
                ModularItem item = (ModularItem) heldStack.getItem();
                int level = item.getEffectLevel(heldStack, frostbite);
                double efficiency = item.getEffectEfficiency(heldStack, frostbite);
                
                if (level > 0 && efficiency == 0) {
                    event.getEntityLiving().addPotionEffect(new EffectInstance(Effects.SLOWNESS, 600, level));
                } else if (level > 0 && efficiency > 0) {
                    event.getEntityLiving().addPotionEffect(new EffectInstance(Effects.SLOWNESS, 600, 4));
                    //event.getEntityLiving().setFire(1); //A very visible way to check if the effect is activating properly
                }
            }
        }
    }
}
