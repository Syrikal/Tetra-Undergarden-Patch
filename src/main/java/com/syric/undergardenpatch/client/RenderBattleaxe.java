package com.syric.undergardenpatch.client;

import com.syric.undergardenpatch.UndergardenPatch;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import se.mickelus.tetra.effect.ItemEffect;
import se.mickelus.tetra.items.modular.impl.ModularDoubleHeadedItem;

@OnlyIn(Dist.CLIENT)
public class RenderBattleaxe {

    public static void renderBattleaxe(RenderPlayerEvent event) {
        if (event.getEntity().getMainHandItem().getItem() instanceof ModularDoubleHeadedItem item) {
            if (item.getEffectLevel(event.getEntity().getMainHandItem(), ItemEffect.get("undergardenpatch:forgottenbattleaxe")) > 0) {
                UndergardenPatch.LOGGER.info("Detected rendering a forgotten battleaxe in main hand in third person");
                //change model of held item?
            }
        }
    }

    public static void renderBattleaxeFirstPerson(RenderHandEvent event) {
//        UndergardenPatch.LOGGER.info("Rendering an arm in first person");
        if (event.getItemStack().getItem() instanceof ModularDoubleHeadedItem item) {
//            UndergardenPatch.LOGGER.info("Rendering a modular double item");
            if (item.getEffectLevel(event.getItemStack(), ItemEffect.get("undergardenpatch:forgottenbattleaxe")) > 0) {
                UndergardenPatch.LOGGER.info("Detected rendering a forgotten battleaxe in main hand in first person");
                //change model of held item?
            }
        }
    }

}
