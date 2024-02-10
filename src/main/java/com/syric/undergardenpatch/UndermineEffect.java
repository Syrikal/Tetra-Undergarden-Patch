package com.syric.undergardenpatch;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;
import se.mickelus.tetra.effect.ItemEffect;
import se.mickelus.tetra.items.modular.ModularItem;

import java.util.Objects;

public class UndermineEffect {
    public static final ItemEffect undermine = ItemEffect.get("undergardenpatch:undermine");

    /**
     * @param event
     * Event handler which checks if the mainhand item has our item effect
     */
    @SubscribeEvent
    public void mineEvent(PlayerEvent.BreakSpeed event) {
        Player player = event.getEntity();
        ItemStack heldStack = player.getMainHandItem();
        if (heldStack.getItem() instanceof ModularItem item) {
            int level = item.getEffectLevel(heldStack, undermine);

            boolean isUndergarden = Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(event.getState().getBlock())).getNamespace().equals("undergarden");

            if (level > 0 && isUndergarden) {
                event.setNewSpeed(event.getOriginalSpeed() * 1.5F);
//                    UndergardenPatch.LOGGER.info("Set dig speed to: " + event.getOriginalSpeed() * 1.5F);
            }
        }
    }
}