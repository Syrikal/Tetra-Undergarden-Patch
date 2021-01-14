package com.undergardenpatch.undergardenpatch;

import com.google.common.collect.Sets;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import se.mickelus.tetra.items.modular.ModularItem;
import se.mickelus.tetra.effect.ItemEffect;
import quek.undergarden.registry.*;


/**
 * Implementation of an effect which deals 1.4x damage to Rotspawn.
 */
public class RotbaneEffect {
    private static final ItemEffect rotbane = ItemEffect.get("undergardenpatch:rotbane");

    /**
     * Event handler which checks if the mainhand item has our item effect
     * @param event
     */
    @SubscribeEvent
    public void attackEvent(LivingHurtEvent event) {
        Entity source = event.getSource().getTrueSource();
        float damage = event.getAmount();

        if (source instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) source;
            ItemStack heldStack = player.getHeldItemMainhand();

            if (heldStack.getItem() instanceof ModularItem) {
                ModularItem item = (ModularItem) heldStack.getItem();
                int level = item.getEffectLevel(heldStack, rotbane);

                if (level > 0) {
                    if(event.getEntityLiving().getType().isContained(UGTags.Entities.ROTSPAWN)) {
                        event.setAmount(damage * 1.4F);
                        //event.getEntityLiving().setFire(1); //A very visible way to check if the effect is activating properly
                    }
                }
            }
        }
    }
}
