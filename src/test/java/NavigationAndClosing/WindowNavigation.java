package NavigationAndClosing;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class WindowNavigation {
    WebDriver driver;

    @BeforeEach
    public void beforeEach(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.get("https://testelka.pl/blog/");
    }

    @AfterEach
    public void afterEach(){driver.quit();}

    @Test
    public void navigatingWindows(){
        driver.findElement(By.cssSelector("a[href*='youtube']")).click();
        Set <String> windows = driver.getWindowHandles();
        String parentWindow = driver.getWindowHandle();
        windows.remove(parentWindow);
        String secondWindow = windows.iterator().next();
        driver.switchTo().window(secondWindow);
        String activeWindow = driver.getWindowHandle();
        driver.findElement(By.cssSelector("div>#logo-icon")).click();
        driver.switchTo().window(parentWindow);
    }
}