private final Setting<Boolean> enabled = sgGeneral.add(new BoolSetting.Builder()
    .name("rig-gambling")
    .defaultValue(true)
    .build());

private final Setting<Integer> winAmount = sgGeneral.add(new IntSetting.Builder()
    .name("win-amount")
    .description("Amount to force.")
    .defaultValue(1000)
    .min(1)
    .sliderMax(1000000)
    .build());

public GamblingRig() {
    super(com.example.addon.Addon.CATEGORY, "gambling-rig", "Forces wins.");
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
