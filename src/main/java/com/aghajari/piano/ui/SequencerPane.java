package com.aghajari.piano.ui;

import com.aghajari.piano.player.Piano;
import com.aghajari.piano.player.PianoEvent;
import com.aghajari.piano.player.PianoVelocity;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import javax.sound.midi.MidiUnavailableException;
import java.util.List;

public class SequencerPane extends Pane {
    private static final double SEQUENCER_HEIGHT = 300;
    private static final double PIXELS_PER_BEAT = 40;
    private static final double TEXT_MARGIN = 10;
    private List<PianoEvent> events = List.of();

    public SequencerPane(PianoPane pianoPane) throws MidiUnavailableException {
        Piano piano = Piano.getInstance();
        setPrefHeight(SEQUENCER_HEIGHT);
        setPrefWidth(pianoPane.getMinWidth());
        setClip(new Rectangle(getPrefWidth(), getPrefHeight()));

        Piano.getInstance().setOnSequenceUpdateListener(seq -> events = seq.events);

        AnimationTimer timer = new AnimationTimer() {
            private double oldTime = -1;
            private int velocity = -1;

            @Override
            public void handle(long now) {
                double elapsedBeats = piano.getCurrentBeatPosition();
                if (Math.abs(elapsedBeats - oldTime) < 0.001f) {
                    return;
                }
                getChildren().clear();
                pianoPane.clearPlayer();
                oldTime = elapsedBeats;
                double latestStartBeat = -1;

                for (PianoEvent event : events) {
                    if (event instanceof PianoEvent.Measure measureEvent) {
                        double startY = SEQUENCER_HEIGHT - (event.startBeat - elapsedBeats) * PIXELS_PER_BEAT;
                        if (startY >= -PIXELS_PER_BEAT && startY <= SEQUENCER_HEIGHT + PIXELS_PER_BEAT) {
                            Line measureLine = new Line(0, startY, getPrefWidth(), startY);
                            measureLine.setStroke(Color.web("#424242"));
                            Text measureNumber = new Text(5, startY - 5, String.valueOf(measureEvent.measureNumber));
                            measureNumber.setFill(Color.web("#E0E0E0"));
                            measureNumber.setFont(Font.font("Roboto", 12));
                            getChildren().addAll(measureLine, measureNumber);
                        }
                    } else if (event instanceof PianoEvent.Note noteEvent) {
                        boolean isBlackNote = noteEvent.note.contains("#");
                        Rectangle pianoKey = pianoPane.keys.get(noteEvent.note);
                        if (pianoKey == null) continue;

                        double startY = SEQUENCER_HEIGHT - (event.startBeat - elapsedBeats) * PIXELS_PER_BEAT;
                        double height = noteEvent.durationBeats * PIXELS_PER_BEAT;
                        if (startY > -height && startY - height < SEQUENCER_HEIGHT) {
                            Rectangle noteRect = new Rectangle();
                            noteRect.setStroke(Color.BLACK);
                            noteRect.setX(pianoKey.getX());
                            noteRect.setY(startY - height);
                            noteRect.setWidth(pianoKey.getWidth());
                            noteRect.setHeight(height);
                            noteRect.setOpacity(0.8);
                            noteRect.setFill(noteEvent.isBass ?
                                    (isBlackNote ? Color.web("#66BB6A") : Color.web("#A5D6A7")) :
                                    (isBlackNote ? Color.web("#42A5F5") : Color.web("#90CAF9")));
                            getChildren().add(noteRect);
                            if (startY >= SEQUENCER_HEIGHT && piano.isRunning()) {
                                pianoPane.updateKey(noteEvent);
                            }
                        }

                        double playTimeBeats = noteEvent.startBeat;
                        double endTimeBeats = noteEvent.startBeat + noteEvent.durationBeats;
                        if (playTimeBeats <= elapsedBeats && elapsedBeats < endTimeBeats && playTimeBeats >= latestStartBeat && !noteEvent.isBass) {
                            velocity = noteEvent.velocity;
                            latestStartBeat = playTimeBeats;
                        }
                    }
                }

                addVelocity(velocity);
            }
        };
        timer.start();
    }

    private void addVelocity(int velocity) {
        String velocityText;
        if (velocity >= 0) {
            PianoVelocity closestVelocity = PianoVelocity.PPP;
            int minDiff = Math.abs(velocity - PianoVelocity.PPP.velocity);
            for (PianoVelocity pv : PianoVelocity.values()) {
                int diff = Math.abs(velocity - pv.velocity);
                if (diff < minDiff) {
                    minDiff = diff;
                    closestVelocity = pv;
                }
            }
            velocityText = String.format("%s (%d)", closestVelocity.name(), velocity);
        } else {
            velocityText = "";
        }
        Text velocityDisplay = new Text(0, TEXT_MARGIN, velocityText);
        velocityDisplay.setWrappingWidth(getPrefWidth() - TEXT_MARGIN);
        velocityDisplay.setFill(Color.web("#FAFAFA"));
        velocityDisplay.setFont(Font.font("Roboto", 12));
        velocityDisplay.setTextAlignment(TextAlignment.RIGHT);
        getChildren().add(velocityDisplay);
    }
}