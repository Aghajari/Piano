package com.aghajari.piano.ui;

import com.aghajari.piano.player.Piano;
import com.aghajari.piano.player.PianoEvent;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import javax.sound.midi.MidiUnavailableException;
import java.util.ArrayList;
import java.util.HashMap;

import static com.aghajari.piano.player.PianoKeys.NOTE_NAMES;

public class PianoPane extends Pane {

    private static final int NUM_KEYS = 88;
    private static final double WHITE_KEY_WIDTH = 20;
    private static final double WHITE_KEY_HEIGHT = 100;
    private static final double BLACK_KEY_WIDTH = WHITE_KEY_WIDTH * 0.6;
    private static final double BLACK_KEY_HEIGHT = WHITE_KEY_HEIGHT * 0.65;

    private Rectangle currentPressedKey = null;
    private final Piano piano;
    private final ArrayList<Rectangle> whiteKeys = new ArrayList<>();
    private final ArrayList<Rectangle> blackKeys = new ArrayList<>();
    public final HashMap<String, Rectangle> keys = new HashMap<>();

    public PianoPane() throws MidiUnavailableException {
        piano = Piano.getInstance();

        int whiteKeyCount = 0;
        for (int i = 0; i < NUM_KEYS; i++) {
            if (!NOTE_NAMES[i].contains("#")) {
                whiteKeyCount++;
            }
        }

        for (int i = 0; i < whiteKeyCount; i++) {
            Rectangle whiteKey = new Rectangle(i * WHITE_KEY_WIDTH, 0, WHITE_KEY_WIDTH, WHITE_KEY_HEIGHT);
            whiteKey.setFill(Color.WHITE);
            whiteKey.setStroke(Color.BLACK);
            whiteKeys.add(whiteKey);
            getChildren().add(whiteKey);
        }

        int whiteKeyIndex = 0;
        for (int i = 0; i < NUM_KEYS; i++) {
            String note = NOTE_NAMES[i];
            Rectangle key;
            boolean isBlackNote = note.contains("#");

            if (isBlackNote) {
                double xPos = (whiteKeyIndex - 1) * WHITE_KEY_WIDTH + WHITE_KEY_WIDTH * 0.7;
                key = new Rectangle(xPos, 0, BLACK_KEY_WIDTH, BLACK_KEY_HEIGHT);
                key.setFill(Color.BLACK);
                blackKeys.add(key);
                getChildren().add(key);
            } else {
                key = whiteKeys.get(whiteKeyIndex);
                whiteKeyIndex++;
            }
            keys.put(note, key);
            key.setUserData(note);
        }

        blackKeys.forEach(Node::toFront);
        setMinWidth(WHITE_KEY_WIDTH * whiteKeyCount);
        setMinHeight(WHITE_KEY_HEIGHT);
        setClickListeners();
    }

    public void updateKey(PianoEvent.Note event) {
        boolean isBlackNote = event.note.contains("#");
        Rectangle key = keys.get(event.note);
        if (key != null) {
            if (isBlackNote) {
                if (event.isBass) {
                    key.setFill(Color.web("#4CAF50"));
                } else {
                    key.setFill(Color.web("#2196F3"));
                }
            } else {
                if (event.isBass) {
                    key.setFill(Color.web("#8BC34A"));
                } else {
                    key.setFill(Color.web("#64B5F6"));
                }
            }
        }
    }

    private void setClickListeners() {
        setOnMousePressed(event -> {
            Rectangle key = findKeyAt(event.getX(), event.getY());
            if (key != null) {
                currentPressedKey = key;
                playKey(currentPressedKey);
            }
        });

        setOnMouseDragged(event -> {
            Rectangle newKey = findKeyAt(event.getX(), event.getY());
            if (newKey != currentPressedKey) {
                resetKey(currentPressedKey);
                currentPressedKey = newKey;
                playKey(currentPressedKey);
            }
        });

        setOnMouseReleased(_ -> {
            resetKey(currentPressedKey);
            currentPressedKey = null;
        });
    }

    private Rectangle findKeyAt(double x, double y) {
        for (Rectangle key : blackKeys) {
            if (key.getBoundsInParent().contains(x, y)) {
                return key;
            }
        }
        for (Rectangle key : whiteKeys) {
            if (key.getBoundsInParent().contains(x, y)) {
                return key;
            }
        }
        return null;
    }

    private void resetKey(Rectangle key) {
        if (key != null) {
            String note = (String) key.getUserData();
            boolean isBlackNote = note.contains("#");
            key.setFill(isBlackNote ? Color.BLACK : Color.WHITE);
            piano.stopNote(note);
        }
    }

    private void playKey(Rectangle key) {
        if (key != null) {
            String note = (String) key.getUserData();
            boolean isBlackNote = note.contains("#");
            key.setFill(isBlackNote ? Color.DARKGRAY : Color.LIGHTGRAY);
            piano.playNote(note);
        }
    }

    public void clearPlayer() {
        keys.values().forEach(key -> {
            String note = (String) key.getUserData();
            boolean isBlackNote = note.contains("#");
            if (key == currentPressedKey) {
                key.setFill(isBlackNote ? Color.DARKGRAY : Color.LIGHTGRAY);
            } else {
                key.setFill(isBlackNote ? Color.BLACK : Color.WHITE);
            }
        });
    }
}
