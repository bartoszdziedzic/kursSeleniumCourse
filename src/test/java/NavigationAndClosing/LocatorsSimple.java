package NavigationAndClosing;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LocatorsSimple {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void beforeEach() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 2);
        driver.manage().window().maximize();
    }

    @AfterEach
    public void afterEach() {
        driver.quit();
    }

    @Test
    public void findElementBy() {

        driver.get("http://www.wikipedia.pl");
        driver.findElement(By.id("searchInput"));
        driver.findElement(By.name("search"));
        driver.findElement(By.className("searchButton"));
        driver.findElement(By.linkText("Wikisłownik"));
        driver.findElement(By.partialLinkText("Wikisł"));

        int numberOfImages = driver.findElements(By.tagName("img")).size();
        System.out.println(numberOfImages);
    }
}