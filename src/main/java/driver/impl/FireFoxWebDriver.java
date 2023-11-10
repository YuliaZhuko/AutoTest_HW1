package driver.impl;

import exceptions.DriverTypeNotSupported;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;
import java.util.logging.Level;

public class FireFoxWebDriver implements IDriver{
    @Override
    public WebDriver newDriver() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--no-sandbox");
        firefoxOptions.addArguments("--no-first-run");
        firefoxOptions.addArguments("--enable-extensions");
        firefoxOptions.addArguments("--homepage=about:blank");
        firefoxOptions.addArguments("--ignore-certificate-errors");
        firefoxOptions.addArguments("--lang=ru");
        firefoxOptions.addArguments("--start-maximized");

        firefoxOptions.setCapability(CapabilityType.BROWSER_VERSION, System.getProperty("browser.version", "117.0"));
        firefoxOptions.setCapability(CapabilityType.BROWSER_NAME, System.getProperty("browser", "chrome"));

        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.INFO);
        //chromeOptions.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

        if (getRemoteUrl() == null) {
            try {
                downloadLocalWebDriver(DriverManagerType.FIREFOX);
            } catch (DriverTypeNotSupported ex) {
                ex.printStackTrace();
            }

            return new FirefoxDriver(firefoxOptions);
        } else
            return new RemoteWebDriver(getRemoteUrl(), firefoxOptions);
    }
}
