package com.syric.undergardenpatch;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import se.mickelus.tetra.effect.ItemEffect;
import se.mickelus.tetra.items.modular.ModularItem;

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
                boolean rotbane = item.getEffectLevel(heldStack, RotbaneEffect.rotbane) > 0;

                if (level > 0 && !forgor && !rotbane && Objects.requireNonNull(event.getEntityLiving().getType().getRegistryName()).getNamespace().equals("undergarden") && event.getEntityLiving().canChangeDimensions()) {
                    event.setAmount(damage * 1.5F);
//                    player.sendMessage(new StringTextComponent("2x Damage to Undergarden Denizens"), player.getUUID());
                }
            }
        }
    }
}
