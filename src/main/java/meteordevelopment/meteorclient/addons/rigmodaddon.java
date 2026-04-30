package com.example.mod.modules;

import meteordevelopment.meteorclient.events.packets.PacketEvent;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.meteorclient.utils.network.PacketUtils;
import meteerridevelopment.orbit.EventHandler;
import net.minecraft.network.packet.s2c.play.GameMessageS2C;

public class GamblingDropper extends Module {
    public GamblingDropper() {
        super(com.example.mod.ExampleMod.CATEGORY, \"gambling-dropper\", \"Forces gambling wins via packet interception.\");
    }

    @EventHandler
    private void onReceivePacket(PacketEvent.Receive event) {

        if (event.packet instanceof GamblingResultPacket) {
            GamblingResultPacket packet = (GamblingResultPacket) event.packet;
            

            packet.setResult(1); 
            


        }
    }
}
