package com.syric.undergardenpatch;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;
import se.mickelus.tetra.effect.ItemEffect;
import se.mickelus.tetra.items.modular.ModularItem;

import java.util.Objects;


/**
 * Implementation of an effect which deals .
 */
public class ThrenodyEffect {
    public static final ItemEffect threnody = ItemEffect.get("undergardenpatch:threnody");

    /**
     * @param event
     * Event handler which checks if the mainhand item has our item effect
     */
    @SubscribeEvent
    public void attackEvent(LivingHurtEvent event) {
        Entity source = event.getSource().getEntity();
        float damage = event.getAmount();

        if (source instanceof Player player) {
            ItemStack heldStack = player.getMainHandItem();

            if (heldStack.getItem() instanceof ModularItem item) {
                int level = item.getEffectLevel(heldStack, threnody);

                boolean isUndergarden = Objects.requireNonNull(ForgeRegistries.ENTITY_TYPES.getKey(event.getEntity().getType())).getNamespace().equals("undergarden");

                if (level > 0 && isUndergarden && event.getEntity().canChangeDimensions()) {
                    event.setAmount(damage * 1.5F);
//                    player.sendMessage(new StringTextComponent("2x Damage to Undergarden Denizens"), player.getUUID());
                }
            }
        }
    }
}
