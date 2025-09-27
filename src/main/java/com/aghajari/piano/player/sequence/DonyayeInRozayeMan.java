package com.aghajari.piano.player.sequence;

import com.aghajari.piano.player.*;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

public class DonyayeInRozayeMan {
    private static final double TRIPLET = (PianoNotes.QUARTER * 2f - 4 * PianoNotes.THIRTY_SECOND) / 3.0;

    public static PianoSequence create() throws InvalidMidiDataException, MidiUnavailableException {
        PianoSequence p = new PianoSequence();
        Piano.getInstance().setTempo(126f);
        p.setVelocity(PianoVelocity.FFF);
        p.setTimeSignature(4, 4);

        // Measure 1
        p.treble.addRest(PianoNotes.QUARTER);
        p.treble.addNote(PianoKeys.D5, PianoNotes.QUARTER);
        p.treble.addNote(PianoKeys.C5, PianoNotes.QUARTER);
        p.treble.addNote(PianoKeys.B4, PianoNotes.QUARTER);
        p.bass.addRest(PianoNotes.WHOLE);

        // Measure 2
        p.treble.addNote(PianoKeys.E5, PianoNotes.DOTTED_HALF);
        p.treble.addNote(PianoKeys.F_SHARP5, PianoNotes.QUARTER);
        p.bass.addChord(PianoNotes.HALF, PianoKeys.B3, PianoKeys.G3, PianoKeys.E3, PianoKeys.B2);
        p.bass.addNote(PianoKeys.G3, PianoNotes.HALF);

        // Measure 3
        p.treble.addNote(PianoKeys.F_SHARP5, PianoNotes.QUARTER);
        p.treble.addNote(PianoKeys.G5, PianoNotes.DOTTED_QUARTER);
        p.treble.addNote(PianoKeys.G5, PianoNotes.DOTTED_EIGHTH);
        p.treble.addNote(PianoKeys.A5, PianoNotes.DOTTED_EIGHTH);
        p.bass.addChord(PianoNotes.HALF, PianoKeys.B3, PianoKeys.G3, PianoKeys.E3, PianoKeys.B2);
        p.bass.addNote(PianoKeys.G3, PianoNotes.HALF);

        // Measure 4
        p.treble.addNote(PianoKeys.G5, PianoNotes.THIRTY_SECOND);
        p.treble.addNote(PianoKeys.F_SHARP5, PianoNotes.DOTTED_HALF - PianoNotes.THIRTY_SECOND);
        p.treble.addNote(PianoKeys.E5, PianoNotes.QUARTER);
        p.bass.addChord(PianoNotes.HALF, PianoKeys.D4, PianoKeys.A3, PianoKeys.F_SHARP3, PianoKeys.D3);
        p.bass.addNote(PianoKeys.G3, PianoNotes.HALF);

        // Measure 5
        p.treble.addNote(PianoKeys.D5, PianoNotes.DOTTED_QUARTER);
        p.treble.addNote(PianoKeys.C5, PianoNotes.EIGHTH);
        p.treble.addRest(PianoNotes.THIRTY_SECOND);
        p.treble.addNote(PianoKeys.B4, TRIPLET);
        p.treble.addRest(PianoNotes.THIRTY_SECOND);
        p.treble.addNote(PianoKeys.C5, TRIPLET);
        p.treble.addRest(PianoNotes.THIRTY_SECOND);
        p.treble.addNote(PianoKeys.D5, TRIPLET);
        p.treble.addRest(PianoNotes.THIRTY_SECOND);
        p.bass.addChord(PianoNotes.HALF, PianoKeys.D4, PianoKeys.A3, PianoKeys.F_SHARP3, PianoKeys.D3);
        p.bass.addNote(PianoKeys.G3, PianoNotes.HALF);

        // Measure 6
        p.treble.addNote(PianoKeys.C5, PianoNotes.DOTTED_HALF);
        p.treble.addChord(PianoNotes.QUARTER, PianoKeys.B4, PianoKeys.B5);
        p.bass.addChord(PianoNotes.HALF, PianoKeys.G3, PianoKeys.C4, PianoKeys.E4);
        p.bass.addNote(PianoKeys.C4, PianoNotes.HALF);

        // Measure 7
        p.treble.addChord(PianoNotes.QUARTER, PianoKeys.C5, PianoKeys.C6);
        p.treble.addChord(PianoNotes.QUARTER, PianoKeys.D5, PianoKeys.D6);
        p.treble.addChord(PianoNotes.QUARTER, PianoKeys.E5, PianoKeys.E6);
        p.treble.addChord(PianoNotes.QUARTER, PianoKeys.G5, PianoKeys.G6);
        p.bass.addChord(PianoNotes.HALF, PianoKeys.A3, PianoKeys.C4, PianoKeys.E4);
        p.bass.addNote(PianoKeys.C4, PianoNotes.HALF);

        // Measure 8
        p.treble.addChord(PianoNotes.HALF, PianoKeys.F_SHARP5, PianoKeys.F_SHARP6);
        p.treble.addChord(PianoNotes.QUARTER, PianoKeys.D5, PianoKeys.D6);
        p.treble.addChord(PianoNotes.QUARTER, PianoKeys.F_SHARP5, PianoKeys.F_SHARP6);
        p.bass.addChord(PianoNotes.HALF, PianoKeys.A3, PianoKeys.D4, PianoKeys.F_SHARP4);
        p.bass.addNote(PianoKeys.D4, PianoNotes.HALF);

        // Measure 9
        p.treble.addChord(PianoNotes.HALF, PianoKeys.E5, PianoKeys.E6);
        p.treble.addNote(PianoKeys.D5, PianoNotes.QUARTER);
        p.treble.addNote(PianoKeys.D5, PianoNotes.EIGHTH);
        p.treble.addNote(PianoKeys.C5, PianoNotes.EIGHTH);
        p.bass.addChord(PianoNotes.HALF, PianoKeys.G3, PianoKeys.C4, PianoKeys.E4);
        p.bass.addChord(PianoNotes.HALF, PianoKeys.A3, PianoKeys.F_SHARP3, PianoKeys.D3);

        // Measure 10
        p.treble.addNote(PianoKeys.B4, PianoNotes.WHOLE);
        p.bass.addChord(PianoNotes.WHOLE, PianoKeys.F_SHARP3, PianoKeys.D3, PianoKeys.B2);

        return p;
    }
}