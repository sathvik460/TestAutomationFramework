package org.selenium.pom.factory;

import org.selenium.pom.constants.BrowserType;

public class DriverManagerFactory {

    public static DriverManager getManager(BrowserType driverType){
        switch (driverType){
            case CHROME -> {
                return new ChromeDriverManager();
            }
            case FIREFOX -> {
                return new FirefoxDriverManager();
            }
            default -> throw new IllegalStateException("Unexpected value: " + driverType);
        }
    }
}
