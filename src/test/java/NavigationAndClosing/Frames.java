package NavigationAndClosing;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;

public class Frames {
    WebDriver driver;
    By cookieConsent = By.cssSelector("a[class*='dismiss-link']");

    @BeforeEach
    public void beforeEach(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://fakestore.testelka.pl/cwiczenia-z-ramek/");
        driver.findElement(cookieConsent).click();
    }

    @AfterEach
    public void afterEach(){driver.quit();}

    @Test
    public void framesTest(){

        driver.switchTo().frame("main-frame");
        //sprawdzamy czy przycisk jest nieaktywny
        Assertions.assertFalse(driver.findElement(By.cssSelector("input[name='main-page']"))
                .isEnabled(),"Main page button is enabled.");

        driver.switchTo().frame("image");
        //sprawdzamy czy obrazek nawiguje do strony głównej
        WebElement imgMainPageLink = driver.findElement(By.xpath(".//img[@alt='Wakacje']/..")); // "/.." nawiguje w górę do rodzica
        Assertions.assertEquals("https://fakestore.testelka.pl/",
                imgMainPageLink.getAttribute("href"));

        driver.switchTo().frame(0);
        //sprawdzamy czy button jest aktywny
        WebElement bottomMainPageButton = driver.findElement(By.cssSelector(".button"));
        Assertions.assertTrue(bottomMainPageButton.isEnabled(),"Main page button is disabled.");

        //sprawdzamy czy po kliknięciu na dolny MainPage a potem Wspinaczka widoczne jest logo
        bottomMainPageButton.click();
        driver.switchTo().parentFrame().switchTo().parentFrame();
        driver.findElement(By.cssSelector("a[name='climbing']")).click();
        Assertions.assertTrue(driver.findElement(By.cssSelector(".custom-logo")).isDisplayed());
    }
}