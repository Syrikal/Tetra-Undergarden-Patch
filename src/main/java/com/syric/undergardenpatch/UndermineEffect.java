package com.syric.undergardenpatch;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import se.mickelus.tetra.effect.ItemEffect;
import se.mickelus.tetra.items.modular.ModularItem;

import java.util.Objects;

public class UndermineEffect {
    private static final ItemEffect undermine = ItemEffect.get("undergardenpatch:undermine");

    /**
     * Event handler which checks if the mainhand item has our item effect
     * @param event
     */
    @SubscribeEvent
    public void mineEvent(PlayerEvent.BreakSpeed event) {
        PlayerEntity player = event.getPlayer();
        ItemStack heldStack = player.getMainHandItem();
        if (heldStack.getItem() instanceof ModularItem) {
            ModularItem item = (ModularItem) heldStack.getItem();
            int level = item.getEffectLevel(heldStack, undermine);

            if (level > 0) {
                if (Objects.requireNonNull(event.getState().getBlock().getRegistryName()).getNamespace().equals("undergarden")) {
                    event.setNewSpeed(event.getOriginalSpeed() * 1.5F);
                }
            }
        }
    }
}