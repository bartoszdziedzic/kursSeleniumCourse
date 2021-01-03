package NavigationAndClosing;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class NavigationAndClosing {
    // obie metody get i navigate robią to samo

    WebDriver driver;

    @BeforeEach
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
    }
    @AfterEach
    public void quit(){
        driver.quit();
    }


    @Test
    public void getMethod() {
        driver.get("http://google.pl");
    }

    @Test
    public void navigate() {
        driver.navigate().to("http://google.pl");
        driver.navigate().to("http://amazon.com");
        driver.navigate().back(); // cofa sie do poprzedniej strony
        driver.navigate().forward(); // do przodu
        driver.navigate().refresh(); // refresh strony
    }
}