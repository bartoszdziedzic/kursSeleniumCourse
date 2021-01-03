package NavigationAndClosing;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//Napisz test, który wykona następujące kroki:
//        1. Otworzy stronę główną Wikipedii.
//        2. Następnie otworzy stronę główną Nasa.
//        3. Cofnie się do strony Wikipedii (używając nawigacji wstecz).
//        4. Potwierdź, że driver jest na stronie Wikipedii: porównaj (Assertions.assertEquals()) tytuł
//        strony z oczekiwanym.
//        5. Przejdź do strony Nasa (używając nawigacji naprzód).
//        6. Potwierdź, że driver jest na stronie Nasa: porównaj tytuł strony z oczekiwanym.
//        7. Zamknij okno przeglądarki.
//        8. Zamknij sesję.
//        Pamiętaj o inicjalizacji WebDrivera i podaniu ścieżki do ChromeDrivera.
public class zadanie1 {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void beforeEeach(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 2);
        driver.manage().window().setSize(new Dimension(2560,1080));
    }

    @AfterEach
    public void afterEach(){
        driver.close();
        driver.quit();
    }

    @Test
    public void zadanko1() throws InterruptedException {
        String wikiTitle = "Wikipedia, wolna encyklopedia";
        String nasaTitle = "NASA's SpaceX Crew-1 Mission | NASA";

        driver.get("https://pl.wikipedia.org");
        Thread.sleep(1000);

        driver.get("https://www.nasa.gov");
        Thread.sleep(2000);

        driver.navigate().back();
        wait.until(ExpectedConditions.titleIs(wikiTitle));
        Assertions.assertEquals(wikiTitle,driver.getTitle(),"Title is not wikiTitle.");

        driver.navigate().forward();
        wait.until(ExpectedConditions.titleIs(nasaTitle));
        Assertions.assertEquals(nasaTitle,driver.getTitle(),"Title is not nasaTitle.");
    }
}