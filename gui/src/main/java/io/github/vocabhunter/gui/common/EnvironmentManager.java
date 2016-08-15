/*
 * Open Source Software published under the Apache Licence, Version 2.0.
 */

package io.github.vocabhunter.gui.common;

public interface EnvironmentManager {
    Placement getScreenSize();

    boolean isVisible(Placement placement);

    boolean useSystemMenuBar();
}
