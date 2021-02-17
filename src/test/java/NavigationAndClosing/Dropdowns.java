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
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Dropdowns {
    WebDriver driver;

    @BeforeEach
    public void beforeEach(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
    }

    @AfterEach
    public void afterEach(){driver.quit();}

    @Test
    public void dropdowns(){
        driver.get("https://allegro.pl/");
        driver.findElement(By.cssSelector("button[data-role='accept-consent']")).click();

        WebElement productCategories = driver.findElement(By.cssSelector("[data-role=filters-dropdown-toggle]"));
        Select categoriesDropdown = new Select(productCategories);

        categoriesDropdown.selectByIndex(3);
        categoriesDropdown.selectByValue("/kategoria/firma");
        categoriesDropdown.selectByVisibleText("Motoryzacja");

        Boolean isMultiple = categoriesDropdown.isMultiple();

        List<WebElement> options = categoriesDropdown.getOptions();
    }

    @Test
    public void dropdownsZadanie(){
        String lowestPrice = "2 900,00 zł";
        String highestPrice = "5 399,00 zł";

        driver.get("https://fakestore.testelka.pl/product-category/windsurfing/");
        driver.findElement(By.cssSelector(".woocommerce-store-notice__dismiss-link")).click();

        WebElement sorting = driver.findElements(By.cssSelector("select[name='orderby']")).get(0);
        Select sortingDropdown = new Select(sorting);

        sortingDropdown.selectByValue("price");
        WebElement firstPrice = driver.findElements(By.cssSelector("span.price")).get(0);
        Assertions.assertEquals(lowestPrice,firstPrice.getText(),"First price is not the lowest one.");

        sortingDropdown.selectByValue("price-desc");
        Assertions.assertEquals(highestPrice,firstPrice.getText(),"First price is not the highest one.");
    }
}