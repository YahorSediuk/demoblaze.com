package Pages;

import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$x;

@Getter
public class ProductInfoPage {
    private final SelenideElement price = $x("//h3[@class='price-container']");
    private final SelenideElement name = $x("//h2[@class='name']");
    private final SelenideElement addToCart = $x("//a[@class='btn btn-success btn-lg']");
    /**
     * Getter for price from product card
     */
    public String getPriceFromProductCard(){
        return price.getText().replaceAll("[ *includes tax]","");
    }
    /**
     * Getter for name from product card
     */
    public String getNameFromProductCard(){
        return name.getText();
    }


}
