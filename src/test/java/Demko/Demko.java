package Demko;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Demko {

    WebDriver driver;


    @Test
    public void demoTest() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
        driver = new ChromeDriver(); //ta linijka otwiera przeglądarkę

        driver.navigate().to("https://google.pl");

        Thread.sleep(1000);
        driver.switchTo().frame(0);
        WebElement agree = driver.findElement(By.id("introAgreeButton"));
        Thread.sleep(1000);
        agree.click();
        Thread.sleep(1000);

        driver.switchTo().parentFrame();
        WebElement searchField = driver.findElement(By.cssSelector("[title='Szukaj']"));
        String searchEntry = "wszechświaty równoległe";
        String title = "Wieloświat – Wikipedia, wolna encyklopedia";

        searchField.sendKeys(searchEntry);
        searchField.submit();
        driver.findElement(By.xpath(".//*[text()='" + title + "']")).click();

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.titleIs(title));

        String entryUrl = "https://pl.wikipedia.org/wiki/Wielo%C5%9Bwiat";
        Assertions.assertEquals(entryUrl,driver.getCurrentUrl(),"URL is not correct");

        driver.quit();
    }
}