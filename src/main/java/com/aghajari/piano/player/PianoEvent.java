package com.aghajari.piano.player;

public sealed class PianoEvent {
    public final double startBeat;

    protected PianoEvent(double startBeat) {
        this.startBeat = startBeat;
    }

    public static final class Measure extends PianoEvent {
        public final int measureNumber;

        Measure(double startBeat, int number) {
            super(startBeat);
            this.measureNumber = number;
        }
    }

    public static final class Note extends PianoEvent {
        public final double durationBeats;
        public final String note;
        public final boolean isBass;
        public final int velocity;

        Note(double startBeat, double durationBeats, int key, boolean isBass, int velocity) {
            super(startBeat);
            this.durationBeats = durationBeats;
            this.note = PianoKeys.getName(key);
            this.isBass = isBass;
            this.velocity = velocity;
        }
    }
}
