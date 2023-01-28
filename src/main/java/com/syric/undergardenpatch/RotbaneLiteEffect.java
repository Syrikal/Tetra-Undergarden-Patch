package com.syric.undergardenpatch;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import quek.undergarden.registry.UGTags;
import se.mickelus.tetra.effect.ItemEffect;
import se.mickelus.tetra.items.modular.ModularItem;


/**
 * Implementation of an effect which deals 1.4x damage to Rotspawn.
 */
public class RotbaneLiteEffect {
    private static final ItemEffect rotbane_lite = ItemEffect.get("undergardenpatch:rotbane_lite");

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
                int level = item.getEffectLevel(heldStack, rotbane_lite);
                boolean forgor = item.getEffectLevel(heldStack, ThrenodyEffect.threnody) > 0 || item.getEffectLevel(heldStack, ThrenodyLiteEffect.threnody_lite) > 0;
                boolean rotbane = item.getEffectLevel(heldStack, RotbaneEffect.rotbane) > 0;

                if (level > 0 && !forgor && !rotbane) {
                    if(event.getEntityLiving().getType().is(UGTags.Entities.ROTSPAWN)) {
                        event.setAmount(damage * 1.25F);
//                        player.sendMessage(new StringTextComponent("150% damage to Rotspawn"), player.getUUID());
                        //event.getEntityLiving().setFire(1); //A very visible way to check if the effect is activating properly
                    }
                }
            }
        }
    }
}
