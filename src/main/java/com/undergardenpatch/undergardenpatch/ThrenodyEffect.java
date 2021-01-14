package com.undergardenpatch.undergardenpatch;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import se.mickelus.tetra.effect.ItemEffect;
import se.mickelus.tetra.items.modular.ModularItem;


/**
 * Implementation of an effect which deals .
 */
public class ThrenodyEffect {
    private static final ItemEffect threnody = ItemEffect.get("undergardenpatch:threnody");

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
                int level = item.getEffectLevel(heldStack, threnody);

                if (level > 0) {
                    if(event.getEntityLiving().getType().getRegistryName().getNamespace().equals("undergarden") && event.getEntityLiving().isNonBoss()) {
                        event.setAmount(damage * 2);
                    }
                    else event.setAmount(damage);
                }
            }
        }
    }
}
