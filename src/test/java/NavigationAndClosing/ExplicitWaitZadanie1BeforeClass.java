package NavigationAndClosing;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

public abstract class ExplicitWaitZadanie1BeforeClass {
    protected WebDriver driver;
    protected WebDriverWait wait;

    By cookieConsentBar = By.cssSelector("a[class*='dismiss-link']");
    By pilatesGroup = By.cssSelector("li.cat-item.cat-item-19 a");
    By product = By.cssSelector("li.post-61");
    By addToCartButton = By.cssSelector("button[name='add-to-cart']");
    By goToCartButton = By.cssSelector("a[title='Zobacz swój koszyk']");
    String correctCoupon = "10procent";
    String incorrectCoupon = "test";

    @BeforeEach
    public void beforeEach(){
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);

        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        driver.get("https://fakestore.testelka.pl/shop/");
        driver.findElement(cookieConsentBar).click();
        driver.findElement(pilatesGroup).click();
        driver.findElement(product).click();
        driver.findElement(addToCartButton).click();
        wait.until(ExpectedConditions.elementToBeClickable(goToCartButton));
        driver.findElement(goToCartButton).click();
    }

    @AfterEach
    public void afterEach(){driver.quit();}

}