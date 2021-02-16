package NavigationAndClosing;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Alerts {
    WebDriver driver;
    JavascriptExecutor js;
    WebDriverWait wait;

    @BeforeEach
    public void beforeEach(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        js = (JavascriptExecutor)driver;
        wait = new WebDriverWait(driver,5);
    }

    @AfterEach
    public void afterEach(){driver.quit();}

    @Test
    public void alerts(){
        String javascript = "prompt ('Możesz tutaj coś wpisać:')";
        js.executeScript(javascript);
        wait.until(ExpectedConditions.alertIsPresent());
        String text = driver.switchTo().alert().getText();
        driver.switchTo().alert().sendKeys("Test");
        driver.switchTo().alert().accept();
        js.executeScript(javascript);
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().dismiss();
    }

    @Test
    public void alertsZadanieConfirm(){
        driver.get("http://jsfiddle.net/nm134se7");
        driver.switchTo().frame("result");

        WebElement alertButton = driver.findElement(By.cssSelector("button[onclick='confirmFunction()']"));

        alertButton.click();
        driver.switchTo().alert().accept();
        Assertions.assertEquals("Wybrana opcja to OK!",
                driver.findElement(By.cssSelector("#demo")).getText(),
                "Not expected message.");


        alertButton.click();
        driver.switchTo().alert().dismiss();
        Assertions.assertEquals("Wybrana opcja to Cancel!",
                driver.findElement(By.cssSelector("#demo")).getText(),
                "Not expected message.");
    }

    @Test
    public void alertsZadaniePrompt(){
        driver.get("http://jsfiddle.net/nm134se7");
        driver.switchTo().frame("result");

        WebElement alertButton = driver.findElement(By.cssSelector("button[onclick='promptFunction()']"));
        String imie = "Luke Skywalker";

        alertButton.click();
        driver.switchTo().alert().sendKeys(imie);
        driver.switchTo().alert().accept();
        Assertions.assertEquals("Cześć " + imie + "! Jak leci?",
                driver.findElement(By.cssSelector("#prompt-demo")).getText(),
                "Not expected message.");


        alertButton.click();
        driver.switchTo().alert().dismiss();
        Assertions.assertEquals("Użytkownik anulował akcję.",
                driver.findElement(By.cssSelector("#prompt-demo")).getText(),
                "Not expected message.");
    }
}