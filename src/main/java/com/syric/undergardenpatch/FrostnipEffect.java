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
public class FrostnipEffect {
    public static final ItemEffect frostnip = ItemEffect.get("undergardenpatch:frostnip");

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
                int level = item.getEffectLevel(heldStack, frostnip);
                int bitelevel = item.getEffectLevel(heldStack, FrostbiteEffect.frostbite);

                if (level > 0 && bitelevel == 0) {
                    event.getEntityLiving().addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 600, 1));
//                    player.sendMessage(new StringTextComponent("Applied Slowness 1"), player.getUUID());
                }
            }
        }
    }
}
