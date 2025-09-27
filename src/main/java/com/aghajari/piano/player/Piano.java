package com.aghajari.piano.player;

import javax.sound.midi.*;

public class Piano {
    private static Piano instance;
    private final Sequencer sequencer;
    private final MidiChannel synthChannel;
    private PianoSequence pianoSequence;
    private OnSequenceUpdateListener onSequenceUpdateListener = null;

    public static Piano getInstance() throws MidiUnavailableException {
        if (instance == null)
            instance = new Piano();

        return instance;
    }

    private Piano() throws MidiUnavailableException {
        sequencer = MidiSystem.getSequencer();
        sequencer.open();
        Synthesizer synth = MidiSystem.getSynthesizer();
        synth.open();

        MidiChannel[] channels = synth.getChannels();
        synthChannel = channels[channels.length - 1];
        synthChannel.programChange(0);
    }

    public void play(PianoSequence sequence) throws MidiUnavailableException, InvalidMidiDataException {
        sequencer.stop();
        sequencer.close();
        sequencer.open();

        pianoSequence = sequence;
        sequencer.setSequence(sequence.sequence);
        sequencer.setLoopCount(0);
        sequencer.start();

        if (onSequenceUpdateListener != null) {
            onSequenceUpdateListener.onNewSequence(sequence);
        }
    }

    public void continueSequence() {
        if (pianoSequence != null) {
            sequencer.start();
        }
    }

    public void stop() {
        if (sequencer == null) return;

        sequencer.stop();
    }

    public void playNote(String noteName) {
        int key = PianoKeys.getKey(noteName);
        synthChannel.noteOn(key, PianoVelocity.FFF.velocity);
    }

    public double getCurrentBeatPosition() {
        if (pianoSequence == null) return 0;
        return sequencer.getMicrosecondPosition() / getMicrosecondsPerQuarterNotePerBeat();
    }

    public long getMicrosecondPosition() {
        return sequencer.getMicrosecondPosition();
    }

    public void setMicrosecondPosition(long position) {
        sequencer.setMicrosecondPosition(position);
    }

    public long getMicrosecondLength() {
        return sequencer.getMicrosecondLength();
    }

    public boolean isRunning() {
        return sequencer.isRunning();
    }

    public float getTempo() {
        return sequencer.getTempoInBPM();
    }

    public void setTempo(float bpm) {
        sequencer.setTempoInBPM(bpm);
    }

    public void stopNote(String noteName) {
        int key = PianoKeys.getKey(noteName);
        synthChannel.noteOff(key, 127);
    }

    public void setOnSequenceUpdateListener(OnSequenceUpdateListener onSequenceUpdateListener) {
        this.onSequenceUpdateListener = onSequenceUpdateListener;
    }

    private double getMicrosecondsPerQuarterNotePerBeat() {
        long microsecondsPerQuarterNote = (long) (60_000_000.0 / sequencer.getTempoInBPM());
        double beatUnitRatio = 4.0 / pianoSequence.denominator;
        return microsecondsPerQuarterNote * beatUnitRatio;
    }

    @FunctionalInterface
    public interface OnSequenceUpdateListener {
        void onNewSequence(PianoSequence pianoSequence);
    }
}