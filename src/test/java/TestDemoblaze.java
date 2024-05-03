import Core.Setup;
import Pages.MainPage;
import Pages.ProductInfoPage;
import com.codeborne.selenide.Selenide;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestDemoblaze extends Setup {

    private final String MAIN_PAGE_URL = "https://www.demoblaze.com/";

    @Test(description = "Case - Successful user registration")
    public void signUpUser(){
        Selenide.open(MAIN_PAGE_URL);
        new MainPage()
                .signUser("USERNAME_TEST_USER4","qwerty12345")
                .loginUser("USERNAME_TEST_USER4","qwerty12345");
    }
    @Test(description = "Case - correct product info view")
    public void checkProductCard(){
        Selenide.open(MAIN_PAGE_URL);
        MainPage mainPage = new MainPage();
        ProductInfoPage productInfoPage = new ProductInfoPage();
        int randomIndex = (int) (Math.random()*8);
        String priceFromCatalog = mainPage
                .getPriceFromList(randomIndex);
        String nameFromCatalog = mainPage
                .getNameFromList(randomIndex);
        String priceFromProductPage = mainPage
                .showProductInfo(randomIndex)
                .getPriceFromProductCard();
        String nameFromProductPage = productInfoPage
                .getNameFromProductCard();
        Assert.assertEquals(priceFromCatalog, priceFromProductPage);
        Assert.assertEquals(nameFromCatalog, nameFromProductPage);
    }
}
