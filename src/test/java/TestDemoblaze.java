import Core.Setup;
import Pages.MainPage;
import com.codeborne.selenide.Selenide;
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
}
