package Pages;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$x;

@Getter
public class ProductInfoPage extends MainPage{
    private final SelenideElement price = $x("//h3[@class='price-container']");
    private final SelenideElement name = $x("//h2[@class='name']");
    private final SelenideElement addToCart = $x("//a[@class='btn btn-success btn-lg']");
    /**
     * Getter for price from product card
     */
    public String getPriceFromProductCard(){
        return price
                .getText()
                .replaceAll("[ *includes tax]","");
    }
    /**
     * Getter for name from product card
     */
    public String getNameFromProductCard(){
        return name.getText();
    }
    /**
     * Method for adding item to the cart
     */
    public ProductInfoPage addToCart(){
        addToCart
                .shouldBe(condition)
                .click();
        return this;
    }
    /**
     * Method for going to the cart page
     */
    public CartPage goToCart(){
        getNavBar()
                .find(text("Cart"))
                .shouldBe(condition)
                .click();
        return new CartPage();
    }
}
