package com.syric.undergardenpatch;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import se.mickelus.tetra.effect.ItemEffect;
import se.mickelus.tetra.items.modular.ModularItem;


/**
 * Implementation of an effect which deals 1.4x damage to Rotspawn.
 */
public class BloodlustEffect {
    private static final ItemEffect bloodlust = ItemEffect.get("undergardenpatch:bloodlust");

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
                int level = item.getEffectLevel(heldStack, bloodlust);

                if (level > 0) {
                    if(event.getEntityLiving().getClassification(false) == MobCategory.CREATURE) {
                        event.setAmount(damage * 1.5F);
                        event.getEntityLiving().setSecondsOnFire(1); //A very visible way to check if the effect is activating properly
                    }
                }
            }
        }
    }
}
