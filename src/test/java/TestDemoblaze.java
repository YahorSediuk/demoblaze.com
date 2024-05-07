import Core.Setup;
import Pages.CartPage;
import Pages.MainPage;
import Pages.ProductInfoPage;
import com.codeborne.selenide.Selenide;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.*;
import static java.lang.Integer.parseInt;

public class TestDemoblaze extends Setup {

    private final String MAIN_PAGE_URL = "https://www.demoblaze.com/";

    private final MainPage mainPage = new MainPage();
    private final ProductInfoPage productInfoPage = new ProductInfoPage();
    private final CartPage cartPage = new CartPage();

    private int randomIndex = (int) (Math.random()*8);
    private int randomIndex2 = (int) (Math.random()*8);

    @Test(description = "Case - Successful user registration")
    public void signUpUser(){
        Selenide.open(MAIN_PAGE_URL);
        mainPage
                .signUser("USERNAME_TEST_USER4","qwerty123457")
                .loginUser("USERNAME_TEST_USER4","qwerty123457");
    }

    @Test(description = "Case - correct product info view")
    public void checkProductCard(){
        Selenide.open(MAIN_PAGE_URL);
        String priceFromCatalog = mainPage
                .getPriceFromCatalog(randomIndex);
        String nameFromCatalog = mainPage
                .getNameFromCatalog(randomIndex);
        String priceFromProductPage = mainPage
                .showProductInfo(randomIndex)
                .getPriceFromProductCard();
        String nameFromProductPage = productInfoPage
                .getNameFromProductCard();
        Assert.assertEquals(priceFromCatalog, priceFromProductPage);
        Assert.assertEquals(nameFromCatalog, nameFromProductPage);
    }

    @Test(description = "Case - adding item to the cart")
    public void checkProductCart(){
        Selenide.open(MAIN_PAGE_URL);
        String nameFromCatalog = mainPage
                .getNameFromCatalog(randomIndex);
        String priceFromCatalog = mainPage
                .getPriceFromCatalog(randomIndex);
        String nameFromCart = mainPage
                .showProductInfo(randomIndex)
                .addToCart()
                .goToCart()
                .getProductNameFromCart(0);
        String priceFromCart = cartPage
                .getProductPriceFromCart(0);
        Assert.assertEquals(nameFromCart, nameFromCatalog);
        Assert.assertEquals("$"+priceFromCart, priceFromCatalog);
    }

    @Test(description = "Case - correct total product sum")
    public void checkTotalSum(){
        Selenide.open(MAIN_PAGE_URL);
        Integer totalSum;
        totalSum=parseInt(mainPage
                .showProductInfo(randomIndex)
                .addToCart()
                .goToCart()
                .getProductPriceFromCart(0));
        mainPage
                .getNavBar()
                .find(text("Home"))
                .click();
        totalSum+=parseInt(mainPage
                .showProductInfo(randomIndex2)
                .addToCart()
                .goToCart()
                .getProductPriceFromCart(1));
        Integer totalFromCartPage = productInfoPage
                .goToCart()
                .getTotalCount();
        Assert.assertEquals(totalSum, totalFromCartPage);
    }

    @Test(description = "Case - contact us")
    public void checkContactUs(){
        Selenide.open(MAIN_PAGE_URL);
        mainPage
                .sendMessageContactUs("email@mail.com", "Name", "Message");
    }
}
