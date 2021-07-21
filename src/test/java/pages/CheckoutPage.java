package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.logging.Logger;

import static utility.CommonUtils.*;

public class CheckoutPage {
    Logger log = Logger.getLogger(CheckoutPage.class.getName());
    public WebDriver driver;

    @FindBy(how = How.ID, using = "button_order_cart")
    private WebElement checkoutButton;

    @FindBy(how = How.CSS, using = "div.shopping_cart a")
    private WebElement cartMenu;

    @FindBy(how = How.CSS, using = "a[class*='button btn']")
    private WebElement proceedToCheckoutButton;

    @FindBy(how = How.XPATH, using = "//button[@name='processAddress']//span[contains(text(),'Proceed to checkout')]")
    private WebElement proceedToCheckoutButtonInAddressPage;

    @FindBy(how = How.XPATH, using = "//div[@id='uniform-cgv']")
    private WebElement termsOfServicesCheckBox;

    @FindBy(how = How.XPATH, using = "//button[@name='processCarrier']//span[contains(text(),'Proceed to checkout')]")
    private WebElement proceedToCheckoutButtonInShippingPage;

    @FindBy(how = How.CSS, using = "a.bankwire")
    private WebElement payByBankWire;

    @FindBy(how = How.XPATH, using = "//span[contains(text(),'I confirm my order')]")
    private WebElement iConfirmMyOrderButton;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void checkout() {
        scrollPageUp(driver);
        Actions actions = new Actions(driver);
        actions.moveToElement(cartMenu).perform();
        actions.moveToElement(checkoutButton).perform();
        checkoutButton.click();
        waitForPageLoaded(driver);
    }


    public void clickProceedToCheckoutButton() {
        proceedToCheckoutButton.click();
        waitForPageLoaded(driver);
    }

    public void clickProceedToCheckoutButtonInAddressPage() {
        proceedToCheckoutButtonInAddressPage.click();
        waitForPageLoaded(driver);
    }

    public void clickProceedToCheckoutButtonInShippingPage() {
        termsOfServicesCheckBox.click();
        proceedToCheckoutButtonInShippingPage.click();
        waitForPageLoaded(driver);
    }

    public void clickPayByBankWire() {
        payByBankWire.click();
        waitForPageLoaded(driver);
    }

    public void clickIConfirmMyOrderButton() {
        iConfirmMyOrderButton.click();
        waitForPageLoaded(driver);
    }

}
