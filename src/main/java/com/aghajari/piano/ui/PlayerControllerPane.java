package com.aghajari.piano.ui;

import com.aghajari.piano.player.Piano;
import javafx.animation.AnimationTimer;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import javax.sound.midi.MidiUnavailableException;

public class PlayerControllerPane extends Pane {
    private static final double PANE_HEIGHT = 50;
    private static final double BUTTON_WIDTH = 80;
    private static final double TEXT_MARGIN = 10;

    private double currentPlayerPosition = -1;

    public PlayerControllerPane(PianoPane pianoPane) throws MidiUnavailableException {
        Piano piano = Piano.getInstance();
        setPrefHeight(PANE_HEIGHT);
        setPrefWidth(pianoPane.getMinWidth());

        // Play/Pause button
        Button playPauseButton = new Button(piano.isRunning() ? "Pause" : "Play");
        playPauseButton.setLayoutX(10);
        playPauseButton.setLayoutY(10);
        playPauseButton.setPrefWidth(BUTTON_WIDTH);
        playPauseButton.setStyle(
                "-fx-background-color: #42A5F5; " +
                        "-fx-text-fill: #FAFAFA; " +
                        "-fx-font-family: 'Roboto'; " +
                        "-fx-font-size: 12; " +
                        "-fx-background-radius: 4;"
        );
        playPauseButton.setOnMouseEntered(_ -> playPauseButton.setStyle(
                "-fx-background-color: #90CAF9; " +
                        "-fx-text-fill: #FAFAFA; " +
                        "-fx-font-family: 'Roboto'; " +
                        "-fx-font-size: 12; " +
                        "-fx-background-radius: 4;"
        ));
        playPauseButton.setOnMouseExited(_ -> playPauseButton.setStyle(
                "-fx-background-color: #42A5F5; " +
                        "-fx-text-fill: #FAFAFA; " +
                        "-fx-font-family: 'Roboto'; " +
                        "-fx-font-size: 12; " +
                        "-fx-background-radius: 4;"
        ));
        playPauseButton.setOnAction(_ -> {
            if (piano.isRunning()) {
                piano.stop();
                playPauseButton.setText("Play");
            } else {
                piano.continueSequence();
                playPauseButton.setText("Pause");
            }
        });

        // Seekbar
        Slider seekBar = new Slider(0, piano.getMicrosecondLength(), piano.getCurrentBeatPosition());
        seekBar.setLayoutX(100);
        seekBar.setLayoutY(15);
        seekBar.setPrefWidth(getPrefWidth() - 200);
        seekBar.setStyle(
                "-fx-control-inner-background: #424242; " +
                        "-fx-accent: #42A5F5;"
        );
        seekBar.valueProperty().addListener((_, _, newVal) -> {
            double newPosition = newVal.doubleValue();
            if (Math.abs(newPosition - currentPlayerPosition) > 0.0001) {
                piano.setMicrosecondPosition((long) newPosition);
                pianoPane.clearPlayer();
            }
        });

        // Position text
        Text positionText = new Text();
        positionText.setFill(Color.web("#FAFAFA"));
        positionText.setFont(Font.font("Roboto", 12));
        positionText.setWrappingWidth(getPrefWidth() - TEXT_MARGIN);
        positionText.setTextAlignment(TextAlignment.RIGHT);
        positionText.setLayoutY(25);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                long currentBeats = piano.getMicrosecondPosition();
                long totalBeats = piano.getMicrosecondLength();
                if (seekBar.getMax() != totalBeats) {
                    seekBar.setMax(totalBeats);
                }
                currentPlayerPosition = currentBeats;
                seekBar.setValue(currentPlayerPosition);

                int currentSeconds = (int) (currentBeats / 1_000_000);
                int totalSeconds = (int) (totalBeats / 1_000_000);
                String currentTime = String.format("%d:%02d", currentSeconds / 60, currentSeconds % 60);
                String totalTime = String.format("%d:%02d", totalSeconds / 60, totalSeconds % 60);
                positionText.setText(currentTime + "/" + totalTime);

                playPauseButton.setText(piano.isRunning() ? "Pause" : "Play");
            }
        };
        timer.start();

        getChildren().addAll(playPauseButton, seekBar, positionText);

        setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();
            if (keyCode == KeyCode.SPACE) {
                playPauseButton.getOnAction().handle(null);
            }
        });
    }
}