package com.syric.undergardenpatch;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import se.mickelus.tetra.effect.ItemEffect;
import se.mickelus.tetra.items.modular.ModularItem;

import java.util.Objects;

public class UndermineLiteEffect {
    private static final ItemEffect undermine_lite = ItemEffect.get("undergardenpatch:undermine_lite");

    /**
     * Event handler which checks if the mainhand item has our item effect
     * @param event
     */
    @SubscribeEvent
    public void mineEvent(PlayerEvent.BreakSpeed event) {
        Player player = event.getPlayer();
        ItemStack heldStack = player.getMainHandItem();
        if (heldStack.getItem() instanceof ModularItem item) {
            int level = item.getEffectLevel(heldStack, undermine_lite);
            boolean forgor = item.getEffectLevel(heldStack, UndermineEffect.undermine) > 0;

            if (level > 0 && !forgor) {
                if (Objects.requireNonNull(event.getState().getBlock().getRegistryName()).getNamespace().equals("undergarden")) {
                    event.setNewSpeed(event.getOriginalSpeed() * 1.25F);
                }
            }
        }
    }
}