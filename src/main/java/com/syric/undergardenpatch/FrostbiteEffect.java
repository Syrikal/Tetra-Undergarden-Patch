package com.syric.undergardenpatch;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
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
        Entity source = event.getSource().getEntity();
        float damage = event.getAmount();

        if (source instanceof Player player) {
            ItemStack heldStack = player.getMainHandItem();

            if (heldStack.getItem() instanceof ModularItem item) {
                int level = item.getEffectLevel(heldStack, frostbite);
                double efficiency = item.getEffectEfficiency(heldStack, frostbite);

                if (level > 0 && efficiency == 0) {
                    event.getEntityLiving().addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 600, level));
                } else if (level > 0 && efficiency > 0) {
                    event.getEntityLiving().addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 600, 4));
                    //event.getEntityLiving().setFire(1); //A very visible way to check if the effect is activating properly
                }
            }
        }
    }


}
