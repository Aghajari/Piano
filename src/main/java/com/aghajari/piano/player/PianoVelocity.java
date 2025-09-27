package com.aghajari.piano.player;

public enum PianoVelocity {
    PPP(15),
    PP(30),
    P(45),
    MP(60),
    MF(75),
    F(90),
    FF(110),
    FFF(120);

    public final int velocity;

    PianoVelocity(int velocity) {
        this.velocity = velocity;
    }
}