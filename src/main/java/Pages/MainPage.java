package Pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebElementCondition;
import lombok.Getter;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

@Getter
public class MainPage {

    private final ElementsCollection navBar = $$x("//ul[@class='navbar-nav ml-auto']//*");
    private final SelenideElement usernameInputSignupForm = $x("//input[@id='sign-username']");
    private final SelenideElement passwordInputSignupForm = $x("//input[@id='sign-password']");
    private final SelenideElement signupButton = $x("//button[@onclick='register()']");
    private final SelenideElement usernameInputLoginForm = $x("//input[@id='loginusername']");
    private final SelenideElement passwordInputLoginForm = $x("//input[@id='loginpassword']");
    private final SelenideElement loginButton = $x("//button[@onclick='logIn()']");
    private final ElementsCollection productHrefs = $$x("//div[@id='tbodyid']//a[@class]");
    private final ElementsCollection priceFromList = $$x("//div[@class='card h-100']//h5");
    WebElementCondition condition = allOf(visible, enabled, clickable);

    /**
     * Getter for price of product from catalog
     */
    public String getPriceFromList(int index){
       return priceFromList.get(index).getText();
    }
    /**
     * Getter for name of product from catalog
     */
    public String getNameFromList(int index){
       return productHrefs.get(index).getText();
    }
    /**
     * Method for `sign up`
     */
    public MainPage signUser(String username, String password){
        navBar
                .find(text("Sign up"))
                .shouldBe(condition)
                .click();
        usernameInputSignupForm
                .shouldBe(condition, value(""))
                .sendKeys(username);
        Assert.assertEquals(usernameInputSignupForm.getValue(), username);
        passwordInputSignupForm
                .shouldBe(condition, value(""))
                .sendKeys(password);
        Assert.assertEquals(passwordInputSignupForm.getValue(), password);
        signupButton
                .shouldBe(condition)
                .click();
        if (Selenide.prompt().contains("Sign up successful.")){}
        else throw new AssertionError("Sign up failed");
        return this;
    }
    /**
     * Method for `log-in`
     */
    public MainPage loginUser (String username, String password){
        navBar
                .find(text("Log in"))
                .shouldBe(condition)
                .click();
        usernameInputLoginForm
                .shouldBe(condition, value(""))
                .sendKeys(username);
                Assert.assertEquals(usernameInputLoginForm.getValue(), username);
        passwordInputLoginForm
                .shouldBe(condition, value(""))
                .sendKeys(password);
                Assert.assertEquals(passwordInputLoginForm.getValue(), password);
        loginButton
                .shouldBe(condition)
                .click();
                Assert.assertEquals(navBar.find(id("nameofuser")).shouldBe(condition).getText(), "Welcome "+username);
        return this;
    }
    /**
     * Method for going to product info page
     */
    public ProductInfoPage showProductInfo(int index){
        productHrefs.filter(condition);
        productHrefs.get(index).click();
        return new ProductInfoPage();
    }
}
