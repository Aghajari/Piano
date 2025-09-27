package com.aghajari.piano;

import com.aghajari.piano.player.sequence.DonyayeInRozayeMan;
import com.aghajari.piano.player.sequence.FurElise;
import com.aghajari.piano.ui.PianoPane;
import com.aghajari.piano.ui.PlayerControllerPane;
import com.aghajari.piano.ui.SequencerPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiUnavailableException;

public class PianoApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws MidiUnavailableException, InvalidMidiDataException {
        PianoPane pianoPane = new PianoPane();
        SequencerPane sequencerPane = new SequencerPane(pianoPane);
        PlayerControllerPane playerControllerPane = new PlayerControllerPane(pianoPane);
        VBox root = new VBox();
        root.setStyle("-fx-background-color: #121212;");
        root.getChildren().add(playerControllerPane);
        root.getChildren().add(sequencerPane);
        root.getChildren().add(pianoPane);

        Scene scene = new Scene(root, pianoPane.getMinWidth(), sequencerPane.getPrefHeight() + pianoPane.getMinHeight() + playerControllerPane.getPrefHeight());
        primaryStage.setTitle("My Piano");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();

        DonyayeInRozayeMan.create().play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}