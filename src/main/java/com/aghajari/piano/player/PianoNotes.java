package com.aghajari.piano.player;

public class PianoNotes {
    public static final double DOUBLE = 2;
    public static final double WHOLE = 1;
    public static final double HALF = 1.0 / 2.0;
    public static final double QUARTER = 1.0 / 4.0;
    public static final double EIGHTH = 1.0 / 8.0;
    public static final double SIXTEENTH = 1.0 / 16.0;
    public static final double THIRTY_SECOND = 1.0 / 32.0;
    public static final double DOTTED_WHOLE = WHOLE + HALF;
    public static final double DOTTED_HALF = HALF + QUARTER;
    public static final double DOTTED_QUARTER = QUARTER + EIGHTH;
    public static final double DOTTED_EIGHTH = EIGHTH + SIXTEENTH;
}
