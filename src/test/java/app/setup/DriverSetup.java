package app.setup;

import utility.App;
import utility.CommonUtils;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class DriverSetup {
    Logger logger = Logger.getLogger(DriverSetup.class.getName());
    public static WebDriver driver;

    public static WebDriver openBrowser() {
        driver = DriverManager.getDriver(App.getProperty("browser.name"));
        driver.get(App.getProperty("app.url"));
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(CommonUtils.IMPLICIT_WAIT, TimeUnit.SECONDS);
        return driver;
    }

    public static void quitBrowser() {
        driver.quit();
    }

}
