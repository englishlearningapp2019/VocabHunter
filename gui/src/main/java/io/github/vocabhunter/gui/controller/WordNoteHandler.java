/*
 * Open Source Software published under the Apache Licence, Version 2.0.
 */

package io.github.vocabhunter.gui.controller;

import io.github.vocabhunter.gui.i18n.I18nManager;
import io.github.vocabhunter.gui.model.SessionModel;
import io.github.vocabhunter.gui.model.WordModel;
import io.github.vocabhunter.gui.view.ViewFxml;
import io.github.vocabhunter.gui.view.WindowTool;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import javax.inject.Inject;
import javax.inject.Provider;

import static io.github.vocabhunter.gui.i18n.I18nKey.NOTE_WINDOW_TITLE;
import static javafx.beans.binding.Bindings.selectString;

public class WordNoteHandler {
    private final Provider<FXMLLoader> loaderProvider;

    private final I18nManager i18nManager;

    private final WindowTool windowTool;

    private SessionModel sessionModel;

    @Inject
    public WordNoteHandler(final Provider<FXMLLoader> loaderProvider, final I18nManager i18nManager, final WindowTool windowTool) {
        this.loaderProvider = loaderProvider;
        this.i18nManager = i18nManager;
        this.windowTool = windowTool;
    }

    public void initialise(final Button buttonNote, final TextArea textAreaNotePreview, final SessionModel sessionModel) {
        this.sessionModel = sessionModel;

        buttonNote.setOnAction(e -> show());
        bindProperties(textAreaNotePreview, sessionModel);
    }

    private void bindProperties(final TextArea textAreaNotePreview, final SessionModel sessionModel) {
        SimpleObjectProperty<WordModel> currentWordProperty = sessionModel.currentWordProperty();
        StringBinding noteBinding = selectString(currentWordProperty, "note");

        textAreaNotePreview.textProperty().bind(noteBinding);
        textAreaNotePreview.visibleProperty().bind(noteBinding.isNotEmpty());
    }

    private void show() {
        Stage stage = new Stage();
        FXMLLoader loader = loaderProvider.get();
        Parent root = ViewFxml.WORD_NOTE.loadNode(loader, i18nManager);
        WordNoteController controller = loader.getController();

        controller.initialise(stage, sessionModel);
        windowTool.setupModal(stage, root, NOTE_WINDOW_TITLE);
    }

    public void processKeyPress(final KeyEvent event) {
        if (KeyCode.N.equals(event.getCode())) {
            event.consume();
            show();
        }
    }
}
