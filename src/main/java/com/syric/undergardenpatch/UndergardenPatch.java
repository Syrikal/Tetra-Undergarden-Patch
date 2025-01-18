package com.syric.undergardenpatch;

import com.syric.undergardenpatch.client.RenderBattleaxe;
import net.minecraftforge.client.event.RenderHandEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("undergardenpatch")
public class UndergardenPatch {
    public static final String MODID = "undergardenpatch";
    public static final Logger LOGGER = LogManager.getLogger();

    public UndergardenPatch() {
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new FrostbiteEffect());
        MinecraftForge.EVENT_BUS.register(new FrostnipEffect());
        MinecraftForge.EVENT_BUS.register(new RotbaneEffect());
        MinecraftForge.EVENT_BUS.register(new RotbaneLiteEffect());
        MinecraftForge.EVENT_BUS.register(new ThrenodyEffect());
        MinecraftForge.EVENT_BUS.register(new ThrenodyLiteEffect());
        MinecraftForge.EVENT_BUS.register(new UndermineEffect());
        MinecraftForge.EVENT_BUS.register(new UndermineLiteEffect());

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::addBars);

    }

    private void addBars(final FMLClientSetupEvent event) {
        FrostbiteEffect.addBars(event);
        FrostnipEffect.addBars(event);
        RotbaneEffect.addBars(event);
        RotbaneLiteEffect.addBars(event);
        ThrenodyEffect.addBars(event);
        ThrenodyLiteEffect.addBars(event);
        UndermineEffect.addBars(event);
        UndermineLiteEffect.addBars(event);
    }

//    private void renderBattleaxeSetup(final FMLClientSetupEvent event) {
//        MinecraftForge.EVENT_BUS.addListener(this::renderBattleaxeListener);
//        MinecraftForge.EVENT_BUS.addListener(this::renderBattleaxe1pListener);
//    }

    private void renderBattleaxeListener(RenderPlayerEvent event) { RenderBattleaxe.renderBattleaxe(event); }
    private void renderBattleaxe1pListener(RenderHandEvent event) { RenderBattleaxe.renderBattleaxeFirstPerson(event); }

}
