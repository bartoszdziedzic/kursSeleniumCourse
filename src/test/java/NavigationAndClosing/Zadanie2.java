package NavigationAndClosing;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

//Napisz prosty test lokalizacji. Kroki:
//        1. Przejdź na stronę http://wikipedia.pl
//        2. Napisz trzy asercje:
//        a. porównaj tytuł strony z oczekiwanym;
//        b. porównaj URL strony z oczekiwanym;
//        c. znajdź w konsoli deweloperskiej (F12) w zakładce Elements jakiś fragment źródła
//        strony, który mówi o tym w jakiej wersji językowej jest strona; użyj tego fragmentu
//        źródła do asercji.
//        3. Zmień język strony na hiszpański (By.cssSelector(„a[title=’hiszpański’]”)).
//        4. Napisz trzy asercje:
//        a. porównaj tytuł strony z oczekiwanym;
//        b. porównaj URL strony z oczekiwanym;
//        c. znajdź w konsoli deweloperskiej (F12) w zakładce Elements jakiś fragment źródła
//        strony, który mówi o tym w jakiej wersji językowej jest strona; użyj tego fragmentu
//        źródła do asercji.
//        Pamiętaj o inicjalizacji WebDrivera i zamknięciu sesji na koniec testu.
public class Zadanie2 {
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
        driver.quit();
    }

    @Test
    public void zadanko2() throws InterruptedException {

        String wikiTitlePl = "Wikipedia, wolna encyklopedia";
        String wikiUrlPl = "https://pl.wikipedia.org/wiki/Wikipedia:Strona_g%C5%82%C3%B3wna";
        String wikiPlLangPl = "\"wgULSCurrentAutonym\":\"polski\"";
        String wikiTitleEs = "Wikipedia, la enciclopedia libre";
        String wikiUrlEs = "https://es.wikipedia.org/wiki/Wikipedia:Portada";
        String wikiEsLangEs = "\"wgULSCurrentAutonym\":\"español\"";


        driver.get("http://wikipedia.pl");
        Thread.sleep(1000);
        Assertions.assertEquals(wikiTitlePl, driver.getTitle(), "Titles do not match.");
        Assertions.assertEquals(wikiUrlPl, driver.getCurrentUrl(), "Urls do not match.");
        Assertions.assertTrue(driver.getPageSource().contains(wikiPlLangPl), "Page language is not PL.");


        driver.findElement(By.xpath("//*[@id=\"p-lang\"]/div/ul/li[16]/a")).click();
        Thread.sleep(1000);
        Assertions.assertEquals(wikiTitleEs, driver.getTitle(), "Titles do not match.");
        Assertions.assertEquals(wikiUrlEs, driver.getCurrentUrl(), "Urls do not match.");
        Assertions.assertTrue(driver.getPageSource().contains(wikiEsLangEs), "Page language is not PL.");
    }
}