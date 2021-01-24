package NavigationAndClosing;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ExplicitWait {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void beforeEeach(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();
        driver.get("https://www.dunckelfeld.de");
    }

    @AfterEach
    public void afterEach(){
        driver.quit();
    }

    @Test
    public void waitExample(){
        WebElement animation = wait.until(ExpectedConditions.
                presenceOfElementLocated(By.cssSelector(".startanimation")));
        wait.until(ExpectedConditions.stalenessOf(animation));
        driver.findElement(By.cssSelector("a.cc-dismiss")).click();
        driver.findElement(By.cssSelector("a[title='Projekte']")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.
                cssSelector("a[title='UNIVERSAL MUSIC Deutschland']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.
                cssSelector("a[href='#zusammenfassung']"))).click();

        By wireframesBy = By.xpath(".//span[text()='Wireframes']/../span[@class='countup']");
        wait.until(ExpectedConditions.textToBe(wireframesBy,"670"));
    }
}