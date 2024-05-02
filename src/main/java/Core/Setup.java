package Core;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class Setup {

    public void setup(){
            WebDriverManager.chromedriver().setup();
            Configuration.browser = "chrome";
            Configuration.browserSize = "2560x1440";
    }

    @BeforeTest
        public void init(){
            setup();
        }

    @AfterTest
        public void tearDown(){
            Selenide.closeWebDriver();
        }
}
