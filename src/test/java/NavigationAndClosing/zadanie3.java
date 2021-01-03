package NavigationAndClosing;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

//Poćwicz pobieranie, tworzenie i usuwanie ciasteczek. Do poniższego zadania użyj strony wikipedia.pl. Wykonaj poniższe akcje:
//        1. Pobierz wszystkie ciasteczka i przy pomocy asercji sprawdź, czy jest ich tyle ile powinno.
//        2. Dodaj swoje ciasteczko i potwierdź asercją, że się dodało.
//        3. Pobierz swoje ciasteczko i użyj asercji, żeby porównać, że nazwa ciasteczka jest taka, jakiej
//        oczekujesz.
//        4. Usuń swoje ciasteczko używając obiektu typu Cookie jako parametru i potwierdź, że zostało
//        usunięte.
//        5. Usuń jakieś ciasteczko używając jego nazwy jako parametru i potwierdź, że zostało
//        usunięte.
//        6. Pobierz dowolne już istniejące ciasteczko i użyj asercji, żeby potwierdzić, że domena,
//        ścieżka i ustawienie flagi HTTP jest takie, jak tego oczekujemy.

public class zadanie3 {
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
    public void zadanko3() throws InterruptedException {

        driver.get("http://wikipedia.pl");
        Thread.sleep(1000);
        Set<Cookie> cookiesCount = driver.manage().getCookies();
        Assertions.assertEquals(4, cookiesCount.size());

        Cookie newCookie = new Cookie("test_cookie", "test_value");
        driver.manage().addCookie(newCookie);
        Assertions.assertEquals(5, driver.manage().getCookies().size(),"Cookie count is invalid.");

        Cookie myCookie = driver.manage().getCookieNamed("test_cookie");
        Assertions.assertEquals(newCookie,myCookie,"Cookies do not match.");

        driver.manage().deleteCookie(myCookie);
        Assertions.assertEquals(4, cookiesCount.size());

        driver.manage().deleteCookieNamed("GeoIP");
        Assertions.assertEquals(4, cookiesCount.size());

        Cookie checkCookie = driver.manage().getCookieNamed("WMF-Last-Access");
        Assertions.assertEquals("pl.wikipedia.org",checkCookie.getDomain(),"Domains not equal.");
        Assertions.assertEquals("/",checkCookie.getPath(),"Paths not equal.");
        Assertions.assertTrue(checkCookie.isHttpOnly(),"Flag not marked.");
    }
}