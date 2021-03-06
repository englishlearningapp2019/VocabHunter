/*
 * Open Source Software published under the Apache Licence, Version 2.0.
 */

package io.github.vocabhunter.gui.controller;

import io.github.vocabhunter.gui.common.ControllerAndView;
import io.github.vocabhunter.gui.i18n.I18nManager;
import io.github.vocabhunter.gui.view.ViewFxml;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

@Singleton
public class SessionProvider implements Provider<ControllerAndView<SessionController, Node>> {
    @Inject
    private Provider<FXMLLoader> loaderProvider;

    @Inject
    private I18nManager i18nManager;

    @Override
    public ControllerAndView<SessionController, Node> get() {
        FXMLLoader loader = loaderProvider.get();
        Node root = ViewFxml.SESSION.loadNode(loader, i18nManager);
        SessionController controller = loader.getController();

        return new ControllerAndView<>(controller, root);
    }
}
