package Pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.Getter;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;
import static java.lang.Integer.parseInt;

@Getter
public class CartPage extends MainPage{
    private final SelenideElement totalCount = $x("//h3[@id='totalp']");
    private final ElementsCollection productsInCart = $$x("//tr[@class='success']");
    /**
     * Getter by index for product name from cart
     */
    public String getProductNameFromCart(int productIndex){
        return productsInCart
                .get(productIndex).$x("td[2]")
                .getText();
    }
    /**
     * Getter by index for product price from cart
     */
    public String getProductPriceFromCart(int productIndex){
        return productsInCart
                .get(productIndex).$x("td[3]")
                .getText();
    }
    public Integer getTotalCount(){
        return parseInt(totalCount.getText());
    }
}
