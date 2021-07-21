package stepdefinitions;

import app.setup.DriverSetup;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import pages.CheckoutPage;
import pages.HomePage;
import pages.LoginPage;

import java.util.logging.Logger;

import static expectedResults.ExpectedValues.*;
import static utility.CommonUtils.verifyTextPresentInPage1;
import static app.setup.DriverSetup.quitBrowser;

public class OnlineShoppingStep {
    Logger logger = Logger.getLogger(OnlineShoppingStep.class.getName());
    public WebDriver driver;
    LoginPage loginPage;
    HomePage homePage;
    CheckoutPage checkoutPage;

    @Given("I am on online Shopping app page")
    public void i_am_on_online_shopping_app_page() {
        logger.info("Launching Online Shopping app");
        driver = DriverSetup.openBrowser();
        loginPage=new LoginPage(driver);

        logger.info("Get Page Title ");
        String actualPageTitle=loginPage.getPageTitle();

        logger.info("Verify Page Title ");
        Assert.assertEquals("Assertion Failed: Page Title is not displayed correctly",EXPECTED_PAGE_TITLE,actualPageTitle);

    }

    @When("I login with email {string} and password {string}")
    public void i_login_with_email_and_password(String userName, String password) {
        logger.info("Click on Sign In link and verify Authentication page is displayed");
        loginPage.clickOnSignInLink();
        boolean authentionPageIsDisplayed=loginPage.authenticationPageIsDisplayed();
        Assert.assertTrue("Assertion Failed: Authentication page is not displayed",authentionPageIsDisplayed);

        logger.info("Enter Login Credentials and verify Home page is displayed");
        loginPage.enterUsernameAndPasswordToLogin(userName,password);
        boolean signoutIsDisplayed=loginPage.signoutLinkIsDisplayed();
        Assert.assertTrue("Assertion Failed: Home page is not displayed",signoutIsDisplayed);
    }

    @Then("I add {int} items to the cart")
    public void i_add_items_to_the_cart(Integer productCount) {
        homePage=new HomePage(driver);

        logger.info("Search Dresses category");
        homePage.searchACategory();

        logger.info("Add two items to the cart and verify that Product is successfully added");
        String successMessage=homePage.addTwoItemsToCart();
        Assert.assertEquals("Assertion Failed: Product added successfully message is not displayed",PRODUCT_ADDED_SUCCESSFULLY_MESSAGE,successMessage);

        logger.info("Verify the number of items added in the cart");
        String itemCount=homePage.getnumberOfItemsInTheCart();
        Assert.assertEquals("Assertion Failed: Incorrect number of items in the cart",String.valueOf(productCount),itemCount);
    }

    @Then("place an order")
    public void place_an_order() {
        checkoutPage=new CheckoutPage(driver);
        checkoutPage.checkout();
        Assert.assertTrue("Assertion Failed: Shopping cart summary page is not displayed",verifyTextPresentInPage1(driver,SHOPPING_CART_SUMMARY_PAGE));
        checkoutPage.clickProceedToCheckoutButton();
        Assert.assertTrue("Assertion Failed: Addresses page is not displayed",verifyTextPresentInPage1(driver,ADDRESS_PAGE));
        checkoutPage.clickProceedToCheckoutButtonInAddressPage();
        Assert.assertTrue("Assertion Failed: Shipping page is not displayed",verifyTextPresentInPage1(driver,SHIPPING_PAGE));
        checkoutPage.clickProceedToCheckoutButtonInShippingPage();
        Assert.assertTrue("Assertion Failed: Choose payment method page is not displayed",verifyTextPresentInPage1(driver,CHOOSE_PAYMENT_METHOD_PAGE));
        checkoutPage.clickPayByBankWire();
        Assert.assertTrue("Assertion Failed: Bankwire page is not displayed",verifyTextPresentInPage1(driver,BANKWIRE_PAYMENT_PAGE));
        checkoutPage.clickIConfirmMyOrderButton();
        Assert.assertTrue("Assertion Failed: Order confirmation page is not displayed",verifyTextPresentInPage1(driver,ORDER_CONFIRMATION_PAGE));
        quitBrowser();
    }
}
