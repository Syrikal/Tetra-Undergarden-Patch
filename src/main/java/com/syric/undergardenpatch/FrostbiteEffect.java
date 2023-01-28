package com.syric.undergardenpatch;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import se.mickelus.tetra.effect.ItemEffect;
import se.mickelus.tetra.items.modular.ModularItem;

/**
 * Implementation of a slowing effect.
 */
public class FrostbiteEffect {
    public static final ItemEffect frostbite = ItemEffect.get("undergardenpatch:frostbite");

    /**
     * Event handler which checks if the mainhand item has our item effect
     * @param event
     */
    @SubscribeEvent
    public void attackEvent(LivingHurtEvent event) {
        Entity source = event.getSource().getEntity();

        if (source instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) source;
            ItemStack heldStack = player.getMainHandItem();

            if (heldStack.getItem() instanceof ModularItem) {
                ModularItem item = (ModularItem) heldStack.getItem();
                int level = item.getEffectLevel(heldStack, frostbite);

                if (level > 0) {
                    event.getEntityLiving().addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 600, 2));
//                    player.sendMessage(new StringTextComponent("Applied Slowness 2"), player.getUUID());
                }
            }
        }
    }

}
