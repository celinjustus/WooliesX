package utility;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;

public class CommonUtils {
    public static long IMPLICIT_WAIT=10;
    public static WebDriverWait wait;
    private static int timeout = 100;

    public static void switchWindow(WebDriver driver){
        Set<String> windows = driver.getWindowHandles();
        for (String handle: windows){
            driver.switchTo().window(handle);
        }
    }

    public static Select getSelectOptions(WebElement element) {
        return new Select(element);
    }

    public static void setOption(WebElement element,String value)
    {
        getSelectOptions(element).selectByVisibleText(value);
    }
    public static void waitForElementToBeClickable(WebElement element,WebDriver driver) {
        try {
            wait = new WebDriverWait(driver, timeout);
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            e.printStackTrace();
            //throw new TestException("The following element is not clickable: " + element);
        }
    }
    public static void waitForPageLoaded(WebDriver driver) {
        ExpectedCondition<Boolean> expectation = new
                ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver driver) {
                        return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
                    }
                };
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            wait.until(expectation);
        } catch (Throwable error) {
            error.printStackTrace();
        }
    }

    public static void scrollPageUp(WebDriver driver){
        JavascriptExecutor je = (JavascriptExecutor) driver;
        je.executeScript("window.scrollBy(0,-350)", "");
    }

    public static boolean verifyTextPresentInPage1(WebDriver driver,String text) {
        List<WebElement> elementsInPage= driver.findElements(By.xpath("//*[contains(text(),'"+text+"')]"));

        if (elementsInPage.size() > 0) {
           return true;
        } else {
            return false;
        }
    }
    public static boolean verifyTextPresentInPage(WebDriver driver,String text) {
        return driver.findElement(By.xpath("//*[contains(text(),'"+text+"')]")).isDisplayed();
    }
    public static void scrollIntoViewAndClick(WebDriver driver,WebElement element){

        JavascriptExecutor je = (JavascriptExecutor) driver;
        je.executeScript("window.scrollBy(0,900)", "");
        je.executeScript("arguments[0].click();", element);


    }

}
