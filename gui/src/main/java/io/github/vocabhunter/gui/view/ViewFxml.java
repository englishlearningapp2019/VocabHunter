/*
 * Open Source Software published under the Apache Licence, Version 2.0.
 */

package io.github.vocabhunter.gui.view;

import io.github.vocabhunter.analysis.core.VocabHunterException;
import io.github.vocabhunter.gui.i18n.I18nManager;
import javafx.fxml.FXMLLoader;

import java.io.IOException;

public enum ViewFxml {
    MAIN("main.fxml"),
    SESSION("session.fxml"),
    PROGRESS("progress.fxml"),
    ABOUT("about.fxml"),
    SETTINGS("settings.fxml"),
    FILTER_SESSION("filter-session.fxml"),
    FILTER_GRID("filter-grid.fxml"),
    WORD_NOTE("word-note.fxml");

    private final String name;

    ViewFxml(final String name) {
        this.name = name;
    }

    public <T> T loadNode(final FXMLLoader loader, final I18nManager i18nManager) {
        try {
            loader.setLocation(getClass().getResource("/" + name));
            loader.setResources(i18nManager.bundle());

            return loader.load();
        } catch (final IOException e) {
            throw new VocabHunterException(String.format("Unable to load FXML '%s'", name), e);
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
