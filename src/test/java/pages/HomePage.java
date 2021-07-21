package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.logging.Logger;

import static expectedResults.ExpectedValues.SEARCH;
import static utility.CommonUtils.waitForElementToBeClickable;
import static utility.CommonUtils.waitForPageLoaded;

public class HomePage {
    Logger log = Logger.getLogger(HomePage.class.getName());
    public WebDriver driver;

    @FindBy(how = How.ID, using = "search_query_top")
    private WebElement searchTextbox;

    @FindBy(how = How.NAME, using = "submit_search")
    private WebElement searchButton;

    @FindBy(how = How.CSS, using = "span[title='Continue shopping']")
    private WebElement continueShoppingButton;

    @FindBy(how = How.CSS, using = "div#layer_cart h2")
    private WebElement successMessage;

    @FindBy(how = How.CSS, using = "div.shopping_cart span")
    private WebElement numberOfItemsInTheCart;

    @FindBy(how = How.ID, using = "layer_cart_product_price")
    private WebElement productPrice;

    String listOfProduct = "//ul[@class='product_list grid row']//span[contains(text(),'Add to cart')]";

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void searchACategory() {
        waitForPageLoaded(driver);
        searchTextbox.sendKeys(SEARCH);
        searchButton.click();
    }

    public String addTwoItemsToCart() {
        String succesMessage = "";
        int price = 0;
        List<WebElement> products = driver.findElements(By.xpath(listOfProduct));
        for (int product = 0; product < 2; product++) {
            products.get(product).click();
            waitForPageLoaded(driver);
            waitForElementToBeClickable(continueShoppingButton, driver);
            succesMessage = successMessage.getText();
            continueShoppingButton.click();
        }
        return succesMessage;
    }

    public String getnumberOfItemsInTheCart() {
        return numberOfItemsInTheCart.getText();
    }

}
