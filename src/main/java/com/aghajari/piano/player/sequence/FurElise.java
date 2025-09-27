package com.aghajari.piano.player.sequence;

import com.aghajari.piano.player.*;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

public class FurElise {

    public static PianoSequence create() throws InvalidMidiDataException, MidiUnavailableException {
        PianoSequence p = new PianoSequence();
        Piano.getInstance().setTempo(120f);
        p.setVelocity(PianoVelocity.MP);
        p.setTimeSignature(3, 4);
        // Measure 1
        p.treble.addRest(PianoNotes.HALF);
        p.treble.addNote(PianoKeys.E5, PianoNotes.EIGHTH);
        p.treble.addNote(PianoKeys.D_SHARP5, PianoNotes.EIGHTH);
        p.bass.addRest(PianoNotes.WHOLE);

        // Measure 2
        p.treble.addNote(PianoKeys.E5, PianoNotes.EIGHTH);
        p.treble.addNote(PianoKeys.D_SHARP5, PianoNotes.EIGHTH);
        p.treble.addNote(PianoKeys.E5, PianoNotes.EIGHTH);
        p.treble.addNote(PianoKeys.B4, PianoNotes.EIGHTH);
        p.treble.addNote(PianoKeys.D5, PianoNotes.EIGHTH);
        p.treble.addNote(PianoKeys.C5, PianoNotes.EIGHTH);
        p.bass.addRest(PianoNotes.WHOLE);

        // Measure 3
        p.treble.addNote(PianoKeys.A4, PianoNotes.QUARTER);
        p.treble.addRest(PianoNotes.EIGHTH);
        p.treble.addNote(PianoKeys.C4, PianoNotes.EIGHTH);
        p.treble.addNote(PianoKeys.E4, PianoNotes.EIGHTH);
        p.treble.addNote(PianoKeys.A4, PianoNotes.EIGHTH);
        p.bass.addNote(PianoKeys.A3, PianoNotes.HALF);
        p.bass.addRest(PianoNotes.QUARTER);

        // Measure 4
        p.treble.addNote(PianoKeys.E3, PianoNotes.QUARTER);
        p.treble.addRest(PianoNotes.EIGHTH);
        p.treble.addNote(PianoKeys.E4, PianoNotes.EIGHTH);
        p.treble.addNote(PianoKeys.G_SHARP4, PianoNotes.EIGHTH);
        p.treble.addNote(PianoKeys.B4, PianoNotes.EIGHTH);
        p.bass.addNote(PianoKeys.B4, PianoNotes.HALF);
        p.bass.addRest(PianoNotes.QUARTER);

        // Measure 5
        p.treble.addNote(PianoKeys.C5, PianoNotes.QUARTER);
        p.treble.addRest(PianoNotes.EIGHTH);
        p.treble.addNote(PianoKeys.E4, PianoNotes.EIGHTH);
        p.treble.addNote(PianoKeys.E5, PianoNotes.EIGHTH);
        p.treble.addNote(PianoKeys.D_SHARP5, PianoNotes.EIGHTH);
        p.bass.addNote(PianoKeys.A3, PianoNotes.HALF);
        p.bass.addRest(PianoNotes.QUARTER);

        // Measure 6
        p.treble.addNote(PianoKeys.E5, PianoNotes.EIGHTH);
        p.treble.addNote(PianoKeys.D_SHARP5, PianoNotes.EIGHTH);
        p.treble.addNote(PianoKeys.E5, PianoNotes.EIGHTH);
        p.treble.addNote(PianoKeys.B4, PianoNotes.EIGHTH);
        p.treble.addNote(PianoKeys.D5, PianoNotes.EIGHTH);
        p.treble.addNote(PianoKeys.C5, PianoNotes.EIGHTH);
        p.bass.addRest(PianoNotes.WHOLE);

        // Measure 7
        p.treble.addNote(PianoKeys.A4, PianoNotes.QUARTER);
        p.treble.addRest(PianoNotes.EIGHTH);
        p.treble.addNote(PianoKeys.C4, PianoNotes.EIGHTH);
        p.treble.addNote(PianoKeys.E4, PianoNotes.EIGHTH);
        p.treble.addNote(PianoKeys.A4, PianoNotes.EIGHTH);
        p.bass.addNote(PianoKeys.A3, PianoNotes.HALF);
        p.bass.addRest(PianoNotes.QUARTER);

        // Measure 8
        p.treble.addNote(PianoKeys.E3, PianoNotes.QUARTER);
        p.treble.addRest(PianoNotes.EIGHTH);
        p.treble.addNote(PianoKeys.E4, PianoNotes.EIGHTH);
        p.treble.addNote(PianoKeys.C5, PianoNotes.EIGHTH);
        p.treble.addNote(PianoKeys.B4, PianoNotes.EIGHTH);
        p.bass.addNote(PianoKeys.B4, PianoNotes.HALF);
        p.bass.addRest(PianoNotes.QUARTER);

        // Measure 9
        p.treble.addNote(PianoKeys.A4, PianoNotes.QUARTER);
        p.treble.addRest(PianoNotes.EIGHTH);
        p.setCrescendo(PianoVelocity.MF, 4);
        p.treble.addNote(PianoKeys.B4, PianoNotes.EIGHTH);
        p.treble.addNote(PianoKeys.C5, PianoNotes.EIGHTH);
        p.treble.addNote(PianoKeys.D5, PianoNotes.EIGHTH);
        p.bass.addNote(PianoKeys.A3, PianoNotes.HALF);
        p.bass.addRest(PianoNotes.QUARTER);

        // Measure 10
        p.treble.addNote(PianoKeys.E5, PianoNotes.QUARTER);
        p.treble.addRest(PianoNotes.EIGHTH);
        p.treble.addNote(PianoKeys.G4, PianoNotes.EIGHTH);
        p.treble.addNote(PianoKeys.F5, PianoNotes.EIGHTH);
        p.treble.addNote(PianoKeys.E5, PianoNotes.EIGHTH);
        p.bass.addNote(PianoKeys.C4, PianoNotes.HALF);
        p.bass.addRest(PianoNotes.QUARTER);

        // Measure 11
        p.treble.addNote(PianoKeys.D5, PianoNotes.QUARTER);
        p.treble.addRest(PianoNotes.EIGHTH);
        p.treble.addNote(PianoKeys.F4, PianoNotes.EIGHTH);
        p.treble.addNote(PianoKeys.E5, PianoNotes.EIGHTH);
        p.treble.addNote(PianoKeys.D5, PianoNotes.EIGHTH);
        p.bass.addNote(PianoKeys.B3, PianoNotes.HALF);
        p.bass.addRest(PianoNotes.QUARTER);

        // Measure 12
        p.treble.addNote(PianoKeys.C5, PianoNotes.QUARTER);
        p.treble.addRest(PianoNotes.EIGHTH);
        p.setCrescendo(PianoVelocity.P, 4);
        p.treble.addNote(PianoKeys.E4, PianoNotes.EIGHTH);
        p.treble.addNote(PianoKeys.D5, PianoNotes.EIGHTH);
        p.treble.addNote(PianoKeys.C5, PianoNotes.EIGHTH);
        p.bass.addNote(PianoKeys.A3, PianoNotes.HALF);
        p.bass.addRest(PianoNotes.QUARTER);

        // Measure 13
        p.treble.addNote(PianoKeys.B4, PianoNotes.DOTTED_HALF);
        p.bass.addNote(PianoKeys.G_SHARP3, PianoNotes.DOTTED_HALF);

        return p;
    }
}