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

public class DragAndDrop {
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
    public void dragAndDropExample1(){

        driver.get("http://marcojakob.github.io/dart-dnd/detection_only/");
        WebElement draggableElement = driver.findElement(By.cssSelector(".draggable"));
        //actions.clickAndHold(draggableElement).moveByOffset(1283,466).release().build().perform();
        actions.dragAndDropBy(draggableElement,20,20).build().perform();
    }

    @Test
    public void dragAndDropExample2(){

        driver.get("http://marcojakob.github.io/dart-dnd/nested_dropzones/");
        WebElement draggableElement = driver.findElement(By.cssSelector(".draggable"));
        WebElement outerDropzone = driver.findElement(By.cssSelector(".dropzone-outer>span"));
        WebElement innerDropzone = driver.findElement(By.cssSelector(".dropzone-inner"));

        actions.dragAndDrop(draggableElement,outerDropzone).build().perform();
        actions.dragAndDrop(draggableElement,innerDropzone).build().perform();
    }
}