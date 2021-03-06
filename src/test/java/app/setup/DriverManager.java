package app.setup;

import utility.App;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class DriverManager {
    public static WebDriver getDriver(String browser) {
        WebDriver driver;
        if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", App.getProperty("chrome.driver.location"));
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            System.setProperty("webdriver.edge.driver", App.getProperty("edge.driver.location"));
            driver = new EdgeDriver();
        } else {
            System.setProperty("webdriver.ie.driver", App.getProperty("ie.driver.location"));
            driver = new InternetExplorerDriver();
        }
        return driver;
    }
}
