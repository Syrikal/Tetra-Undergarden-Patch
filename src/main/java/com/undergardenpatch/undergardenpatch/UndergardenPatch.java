package com.undergardenpatch.undergardenpatch;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;

@Mod("undergardenpatch")
public class UndergardenPatch {
    public UndergardenPatch() {
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new BloodlustEffect());
        MinecraftForge.EVENT_BUS.register(new FrostbiteEffect());
        MinecraftForge.EVENT_BUS.register(new RotbaneEffect());
        MinecraftForge.EVENT_BUS.register(new ThrenodyEffect());
    }
}
