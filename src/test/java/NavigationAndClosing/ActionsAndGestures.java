package NavigationAndClosing;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
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
}