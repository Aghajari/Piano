package com.aghajari.piano.player;

public class PianoKeys {
    public static final String[] NOTE_NAMES = {
            "A0", "A#0", "B0",
            "C1", "C#1", "D1", "D#1", "E1", "F1", "F#1", "G1", "G#1", "A1", "A#1", "B1",
            "C2", "C#2", "D2", "D#2", "E2", "F2", "F#2", "G2", "G#2", "A2", "A#2", "B2",
            "C3", "C#3", "D3", "D#3", "E3", "F3", "F#3", "G3", "G#3", "A3", "A#3", "B3",
            "C4", "C#4", "D4", "D#4", "E4", "F4", "F#4", "G4", "G#4", "A4", "A#4", "B4",
            "C5", "C#5", "D5", "D#5", "E5", "F5", "F#5", "G5", "G#5", "A5", "A#5", "B5",
            "C6", "C#6", "D6", "D#6", "E6", "F6", "F#6", "G6", "G#6", "A6", "A#6", "B6",
            "C7", "C#7", "D7", "D#7", "E7", "F7", "F#7", "G7", "G#7", "A7", "A#7", "B7",
            "C8"
    };

    // 88-key piano MIDI numbers
    public static final int A0 = 21;
    public static final int A_SHARP0 = 22;
    public static final int B0 = 23;

    public static final int C1 = 24;
    public static final int C_SHARP1 = 25;
    public static final int D1 = 26;
    public static final int D_SHARP1 = 27;
    public static final int E1 = 28;
    public static final int F1 = 29;
    public static final int F_SHARP1 = 30;
    public static final int G1 = 31;
    public static final int G_SHARP1 = 32;
    public static final int A1 = 33;
    public static final int A_SHARP1 = 34;
    public static final int B1 = 35;

    public static final int C2 = 36;
    public static final int C_SHARP2 = 37;
    public static final int D2 = 38;
    public static final int D_SHARP2 = 39;
    public static final int E2 = 40;
    public static final int F2 = 41;
    public static final int F_SHARP2 = 42;
    public static final int G2 = 43;
    public static final int G_SHARP2 = 44;
    public static final int A2 = 45;
    public static final int A_SHARP2 = 46;
    public static final int B2 = 47;

    public static final int C3 = 48;
    public static final int C_SHARP3 = 49;
    public static final int D3 = 50;
    public static final int D_SHARP3 = 51;
    public static final int E3 = 52;
    public static final int F3 = 53;
    public static final int F_SHARP3 = 54;
    public static final int G3 = 55;
    public static final int G_SHARP3 = 56;
    public static final int A3 = 57;
    public static final int A_SHARP3 = 58;
    public static final int B3 = 59;

    public static final int C4 = 60; // Middle C
    public static final int C_SHARP4 = 61;
    public static final int D4 = 62;
    public static final int D_SHARP4 = 63;
    public static final int E4 = 64;
    public static final int F4 = 65;
    public static final int F_SHARP4 = 66;
    public static final int G4 = 67;
    public static final int G_SHARP4 = 68;
    public static final int A4 = 69;
    public static final int A_SHARP4 = 70;
    public static final int B4 = 71;

    public static final int C5 = 72;
    public static final int C_SHARP5 = 73;
    public static final int D5 = 74;
    public static final int D_SHARP5 = 75;
    public static final int E5 = 76;
    public static final int F5 = 77;
    public static final int F_SHARP5 = 78;
    public static final int G5 = 79;
    public static final int G_SHARP5 = 80;
    public static final int A5 = 81;
    public static final int A_SHARP5 = 82;
    public static final int B5 = 83;

    public static final int C6 = 84;
    public static final int C_SHARP6 = 85;
    public static final int D6 = 86;
    public static final int D_SHARP6 = 87;
    public static final int E6 = 88;
    public static final int F6 = 89;
    public static final int F_SHARP6 = 90;
    public static final int G6 = 91;
    public static final int G_SHARP6 = 92;
    public static final int A6 = 93;
    public static final int A_SHARP6 = 94;
    public static final int B6 = 95;

    public static final int C7 = 96;
    public static final int C_SHARP7 = 97;
    public static final int D7 = 98;
    public static final int D_SHARP7 = 99;
    public static final int E7 = 100;
    public static final int F7 = 101;
    public static final int F_SHARP7 = 102;
    public static final int G7 = 103;
    public static final int G_SHARP7 = 104;
    public static final int A7 = 105;
    public static final int A_SHARP7 = 106;
    public static final int B7 = 107;

    public static final int C8 = 108; // Highest C

    public static String getName(int key) {
        return NOTE_NAMES[key - A0];
    }

    public static int getKey(String name) {
        String note = name.substring(0, name.length() - 1);
        int octave = Integer.parseInt(name.substring(name.length() - 1));

        int base = switch (note.toUpperCase()) {
            case "C" -> 0;
            case "C#" -> 1;
            case "D" -> 2;
            case "D#" -> 3;
            case "E" -> 4;
            case "F" -> 5;
            case "F#" -> 6;
            case "G" -> 7;
            case "G#" -> 8;
            case "A" -> 9;
            case "A#" -> 10;
            case "B" -> 11;
            default -> throw new IllegalArgumentException("Invalid note: " + note);
        };
        return 12 * (octave + 1) + base;
    }
}
