package Pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebElementCondition;
import lombok.Getter;
import org.openqa.selenium.devtools.v85.systeminfo.model.ImageDecodeAcceleratorCapability;
import org.testng.Assert;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

@Getter
public class MainPage {

    private final ElementsCollection navBar = $$x("//ul[@class='navbar-nav ml-auto']//*");
    private final ElementsCollection productHrefs = $$x("//div[@id='tbodyid']//a[@class]");
    private final ElementsCollection priceFromList = $$x("//div[@class='card h-100']//h5");
    private final ElementsCollection modalWindow = $$x("//div[@class='modal-content']//*");
    WebElementCondition condition = allOf(visible, enabled, clickable);
    /**
     * Getter for price of product from catalog
     */
    public String getPriceFromCatalog(int index){
       return priceFromList.get(index).getText();
    }
    /**
     * Getter for name of product from catalog
     */
    public String getNameFromCatalog(int index){
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
        modalWindow
                .find(id("sign-username"))
                .shouldBe(condition, value(""))
                .sendKeys(username);
        modalWindow
                .find(id("sign-password"))
                .shouldBe(condition, value(""))
                .sendKeys(password);
        modalWindow
                .find(attribute("onclick", "register()"))
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
        modalWindow
                .find(id("loginusername"))
                .shouldBe(condition, value(""))
                .sendKeys(username);
        modalWindow
                .find(id("loginpassword"))
                .shouldBe(condition, value(""))
                .sendKeys(password);
        modalWindow
                .find(attribute("onclick", "logIn()"))
                .shouldBe(condition)
                .click();
                Assert.assertEquals(navBar.find(id("nameofuser")).shouldBe(condition).getText(), "Welcome "+username);
        return this;
    }
    /**
     * Method for going to the product info page
     */
    public ProductInfoPage showProductInfo(int index){
        productHrefs.filter(condition);
        productHrefs.get(index).click();
        return new ProductInfoPage();
    }
    /**
     * Method for sending `contact us` message
     */
    public MainPage sendMessageContactUs(String email, String contactName, String message){
        navBar
                .find(text("Contact"))
                .shouldBe(condition)
                .click();
        modalWindow
                .find(id("recipient-email"))
                .shouldBe(condition)
                .sendKeys(email);
        modalWindow
                .find(id("recipient-name"))
                .shouldBe(condition, value(""))
                .sendKeys(contactName);
        modalWindow
                .find(id("message-text"))
                .shouldBe(condition, value(""))
                .sendKeys(message);
        modalWindow
                .find(attribute("onclick", "send()"))
                .shouldBe(condition)
                .click();
        if (Selenide.prompt().contains("Thanks for the message!!")){}
        else throw new AssertionError("Message sending error");
        return this;
    }
}
