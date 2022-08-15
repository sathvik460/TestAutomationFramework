package org.selenium.pom.factory.abstractFactory;

import org.selenium.pom.constants.BrowserType;
import org.selenium.pom.factory.ChromeDriverManager;
import org.selenium.pom.factory.DriverManager;
import org.selenium.pom.factory.FirefoxDriverManager;

public class DriverManagerFactoryAbstract {

    public static DriverManagerAbstract getManager(BrowserType driverType){
        switch (driverType){
            case CHROME -> {
                return new ChromeDriverManagerAbstract();
            }
            case FIREFOX -> {
                return new FirefoxDriverManagerAbstract();
            }
            default -> throw new IllegalStateException("Unexpected value: " + driverType);
        }
    }
}
