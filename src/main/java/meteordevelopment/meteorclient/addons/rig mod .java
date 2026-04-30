package com.example.addon.modules;

import meteordevelopment.meteorclient.events.packets.PacketEvent;
import meteordevelopment.meteorclient.systems.modules.Module;
import meteordevelopment.meteorclient.settings.*;
import meteordevelopment.orbit.EventHandler;
import com.example.gambling.packets.GamblingResultPacket; 

public class GamblingRig extends Module {
    private final SettingGroup sgGeneral = settings.getDefaultGroup();

    private final Setting<Boolean> enabled = sgGeneral.add(new BoolSetting.Builder()
        .name(\"rig-gambling\")
        .defaultValue(true)
        .build());

    private final Setting<Integer> winAmount = sgGeneral.add(new IntSetting.Builder()
        .name(\"win-amount\")
        .description(\"Amount to force.\")
        .defaultValue(1000)
        .min(1)
        .sliderMax(1000000)
        .build());

    public GamblingRig() {
        super(com.example.addon.Addon.CATEGORY, \"gambling-rig\", \"Forces wins.\");
    }

    @EventHandler
    private void onReceivePacket(PacketEvent.Receive event) {
        if (!enabled.get()) return;

        if (event.packet instanceof GamblingResultPacket) {
            GamblingResultPacket packet = (GamblingResultPacket) event.packet;
            packet.setWin(true);
            packet.setAmount(winAmount.get());
        }
    }
}
