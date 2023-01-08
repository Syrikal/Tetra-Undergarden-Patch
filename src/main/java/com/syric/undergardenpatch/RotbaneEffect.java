package com.syric.undergardenpatch;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
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
        Entity source = event.getSource().getEntity();
        float damage = event.getAmount();

        if (source instanceof Player player) {
            ItemStack heldStack = player.getMainHandItem();

            if (heldStack.getItem() instanceof ModularItem item) {
                int level = item.getEffectLevel(heldStack, rotbane);

                if (level > 0 && event.getEntityLiving().getType().is(UGTags.Entities.ROTSPAWN)) {
                    event.setAmount(damage * 1.4F);
                }
            }
        }
    }
}
