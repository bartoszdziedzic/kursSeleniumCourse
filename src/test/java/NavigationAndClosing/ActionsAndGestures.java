package NavigationAndClosing;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ActionsAndGestures {
    WebDriver driver;
    Actions actions;

    @BeforeEach
    public void beforeEach(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        actions = new Actions(driver);
    }

    @AfterEach
    public void afterEach(){driver.quit();}

    @Test
    public void clickExample(){
        driver.get("https://jqueryui.com/selectable/#default");
        actions.moveByOffset(1151,534).click().build().perform();
    }

    @Test
    public void doubleClickExample(){
        driver.get("https://www.plus2net.com/javascript_tutorial/ondblclick-demo.php");
        actions.moveByOffset(277,86).doubleClick().build().perform();
    }

    @Test
    public void contextClickExample(){
        driver.get("https://swisnl.github.io/jQuery-contextMenu/demo.html");
        actions.moveByOffset(417,208).contextClick().build().perform();
    }

    @Test
    public void sendKeysExample(){
        driver.get("https://fakestore.testelka.pl/moje-konto/");
        driver.findElement(By.cssSelector(".woocommerce-store-notice__dismiss-link")).click();

        WebElement login = driver.findElement(By.cssSelector("#username"));
        actions.sendKeys(login,"qwe-1176").build().perform();
        // actions.sendKeys(login, Keys.SHIFT, "qwe-1176").build().perform(); WPISZE Z SHIFTEM
    }

    @Test
    public void keyUpDownExample(){
        driver.get("https://jqueryui.com/selectable/#default");

        driver.switchTo().frame(0);
        List<WebElement> items = driver.findElements(By.cssSelector(".ui-selectable>li"));

        actions.keyDown(Keys.CONTROL).click(items.get(0)).click(items.get(1)).click(items.get(2)).keyUp(Keys.CONTROL).build().perform();
    }
}