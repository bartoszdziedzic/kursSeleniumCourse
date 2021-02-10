package NavigationAndClosing;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;

public class WebElementsMethodsZadanie {
    WebDriver driver;

    @BeforeEach
    public void beforeEach(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://fakestore.testelka.pl/metody-na-elementach");
    }

    @AfterEach
    public void afterEach(){driver.quit();}

    @Test
    public void elementsStatuses(){
        WebElement mainPageButton = driver.findElement(By.cssSelector("input[name='main-page']"));
        WebElement notVisibleButton = driver.findElement(By.cssSelector("[name='sailing']"));
        List<WebElement> yellowButtons = driver.findElements(By.cssSelector("a.button"));
        WebElement selectedCheckbox = driver.findElement(By.cssSelector("[name='selected-checkbox']"));
        WebElement unselectedCheckbox = driver.findElement(By.cssSelector("[name='not-selected-checkbox']"));
        WebElement selectedRadioBtn = driver.findElement(By.cssSelector("[name='selected-radio']"));
        WebElement unselectedRadioBtn = driver.findElement(By.cssSelector("[name='not-selected-radio']"));
        List<WebElement> classButtonElements = driver.findElements(By.cssSelector("[class='button']"));


        Assertions.assertFalse(mainPageButton.isEnabled(),"Main page button is enabled.");

        Assertions.assertFalse(notVisibleButton.isDisplayed(),"Invisible button is visible.");

        for (WebElement button:yellowButtons) {
            String color = button.getCssValue("background-color");
            Assertions.assertEquals("rgba(245, 233, 101, 1)",color,"Color not as expected");
        }

        Assertions.assertTrue(selectedCheckbox.isSelected(),"Checkbox is not selected");
        Assertions.assertFalse(unselectedCheckbox.isSelected(),"Checkbox is selected");
        Assertions.assertTrue(selectedRadioBtn.isSelected(),"Radiobtn is not selected");
        Assertions.assertFalse(unselectedRadioBtn.isSelected(),"Radiobtn is selected");

        for (WebElement classButtonElement:classButtonElements) {
            Assertions.assertEquals("a",classButtonElement.getTagName(),"Tag is not 'a'");
        }
    }
}