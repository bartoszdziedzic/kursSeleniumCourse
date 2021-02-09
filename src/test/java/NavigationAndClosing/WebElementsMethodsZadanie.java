package NavigationAndClosing;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebElementsMethodsZadanie {
    WebDriver driver;

    @BeforeEach
    public void beforeEach(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://fakestore.testelka.pl/metody-na-elementach/");
    }

    @AfterEach
    public void afterEach(){driver.quit();}

    @Test
    public void isMainPageButtonInactive(){
        WebElement mainPageButton = driver.findElement(By.cssSelector("input[name='main-page']"));
        mainPageButton.isEnabled();

    }
}