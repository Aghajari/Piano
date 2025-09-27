package com.aghajari.piano.player;

import javax.sound.midi.*;
import java.util.ArrayList;
import java.util.List;

public class PianoSequence {

    private static final int PPQ = 96; // Pulses per quarter note
    private static final int DEFAULT_CHANNEL = 0;
    final Sequence sequence;
    private final Track track;

    int numerator = 3;
    int denominator = 4;

    public final Sheet treble = new Sheet(false);
    public final Sheet bass = new Sheet(true);

    private VelocitySetter velocity = new StaticVelocity(PianoVelocity.MF);
    public final List<PianoEvent> events = new ArrayList<>();

    private int currentMeasureBeat = 0;
    private int currentMeasure = 1;
    private double filledBeats = 0f;

    public PianoSequence() throws InvalidMidiDataException {
        sequence = new Sequence(Sequence.PPQ, PPQ);
        track = sequence.createTrack();
        events.add(new PianoEvent.Measure(currentMeasureBeat, currentMeasure));
    }

    /**
     * Set time signature (e.g. 3/4, 4/4, etc.)
     */
    public void setTimeSignature(int numerator, int denominator) {
        if (numerator <= 0 || denominator <= 0) {
            throw new IllegalArgumentException("Invalid time signature values.");
        }
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public void setVelocity(PianoVelocity velocity) {
        this.velocity = new StaticVelocity(velocity);
    }

    public void setCrescendo(PianoVelocity target, int noteCount) {
        this.velocity = new CrescendoVelocity(velocity.getVelocity(true), target.velocity, noteCount);
    }

    public void play() throws MidiUnavailableException, InvalidMidiDataException {
        Piano.getInstance().play(this);
    }

    void fill(double beats) {
        filledBeats += beats;
        if (filledBeats > numerator) {
            nextMeasure();
        }
    }

    private void nextMeasure() {
        filledBeats -= numerator;
        currentMeasure++;
        currentMeasureBeat += numerator;
        events.add(new PianoEvent.Measure(currentMeasureBeat, currentMeasure));
    }

    public final class Sheet {
        private long currentTick = 0;
        private double currentBeat = 0;
        private final boolean isBass;

        private Sheet(boolean isBass) {
            this.isBass = isBass;
        }

        public void addNote(int note, double duration) throws InvalidMidiDataException {
            addChord(duration, note);
        }

        public void addChord(double duration, int... notes) throws InvalidMidiDataException {
            int vel = velocity.getVelocity(isBass);
            long durationTicks = getNoteTicks(duration);
            double beats = durationTicks / (PPQ * 4.0 / denominator);

            for (int note : notes) {
                events.add(new PianoEvent.Note(currentBeat, beats, note, isBass, vel));

                track.add(new MidiEvent(new ShortMessage(ShortMessage.NOTE_ON, DEFAULT_CHANNEL, note, vel), currentTick));
                track.add(new MidiEvent(new ShortMessage(ShortMessage.NOTE_OFF, DEFAULT_CHANNEL, note, 0), currentTick + durationTicks));
            }
            currentBeat += beats;
            currentTick += durationTicks;

            if (!isBass) fill(beats);
        }

        public void addRest(double duration) {
            long durationTicks = getRestTicks(duration);
            double beats = durationTicks / (PPQ * 4.0 / denominator);
            currentBeat += beats;
            currentTick += getRestTicks(duration);

            if (!isBass) fill(beats);
        }

        private long getNoteTicks(double duration) {
            return Math.round(duration * PPQ / PianoNotes.QUARTER);
        }

        private long getRestTicks(double duration) {
            if (duration == PianoNotes.WHOLE) {
                return getNoteTicks((double) numerator / denominator);
            }
            return getNoteTicks(duration);
        }
    }

    private interface VelocitySetter {
        int getVelocity(boolean isBass);
    }

    private record StaticVelocity(PianoVelocity velocity) implements VelocitySetter {
        @Override
        public int getVelocity(boolean isBass) {
            return velocity.velocity;
        }
    }

    private static class CrescendoVelocity implements VelocitySetter {
        private final int from;
        private final int to;
        private final int notesCount;
        private int index = 0;

        private CrescendoVelocity(int from, int to, int notesCount) {
            this.from = from;
            this.to = to;
            this.notesCount = notesCount;
        }

        @Override
        public int getVelocity(boolean isBass) {
            if (notesCount > index) {
                float fraction = (float) index / (float) notesCount;
                if (!isBass) index++;
                return (int) (from + (to - from) * fraction);
            } else {
                return to;
            }
        }
    }
}

