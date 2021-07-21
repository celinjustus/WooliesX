package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.logging.Logger;

import static utility.CommonUtils.waitForElementToBeClickable;
import static utility.CommonUtils.waitForPageLoaded;

public class LoginPage {
    Logger log = Logger.getLogger(LoginPage.class.getName());
    public WebDriver driver;

    @FindBy(how = How.CSS, using = ".login")
    private WebElement signInLink;

    @FindBy(how = How.TAG_NAME, using = "h1")
    private WebElement authenticationPage;

    @FindBy(how = How.ID, using = "email")
    private WebElement usernameTextbox;

    @FindBy(how = How.ID, using = "passwd")
    private WebElement passwordTextbox;

    @FindBy(how = How.ID, using = "SubmitLogin")
    private WebElement submitLogin;

    @FindBy(how = How.CSS, using = "a.logout")
    private WebElement signoutLink;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public void clickOnSignInLink() {
        waitForElementToBeClickable(signInLink, driver);
        signInLink.click();
    }

    public boolean authenticationPageIsDisplayed() {
        waitForPageLoaded(driver);
        return authenticationPage.isDisplayed();
    }

    public void enterUsernameAndPasswordToLogin(String uName, String pass) {
        usernameTextbox.sendKeys(uName);
        passwordTextbox.sendKeys(pass);
        submitLogin.click();
    }

    public boolean signoutLinkIsDisplayed() {
        waitForPageLoaded(driver);
        return signoutLink.isDisplayed();
    }
}
