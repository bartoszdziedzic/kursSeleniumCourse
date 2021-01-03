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

import java.util.GregorianCalendar;
import java.util.Set;

public class ManageCookies {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void beforeEach() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 2);
        driver.manage().window().setSize(new Dimension(2560, 1080));
    }

    @AfterEach
    public void afterEach() {
        driver.quit();
    }

    @Test
    public void gettingAndDeletingCookies() {

        driver.get("http://www.amazon.com");

        Set<Cookie> cookies = driver.manage().getCookies();
        Cookie cookieSessionID = driver.manage().getCookieNamed("ubid-main");
        driver.manage().deleteCookieNamed("ubid-main");
        driver.manage().deleteAllCookies();
        Assertions.assertEquals(0, driver.manage().getCookies().size(), "Cookies are not empty."); //moze sie wywalic bo strona za szybko doda jakies potrzebne ciasteczko
    }

    @Test
    public void addingAndDeletingCookies() {

        driver.get("http://www.amazon.com");

        Cookie newCookie1 = new Cookie("test_cookie1", "test_value1", ".amazon.com", "/",
                new GregorianCalendar(2029,11,30).getTime(), true, true);
        driver.manage().addCookie(newCookie1);

        Cookie newCookie2 = new Cookie("test_cookie2", "test_value2");
        driver.manage().addCookie(newCookie2);
        Assertions.assertEquals(10,driver.manage().getCookies().size(),"Cookie count is different.");

        driver.manage().deleteCookie(newCookie1);
        Assertions.assertEquals(9,driver.manage().getCookies().size(),"Cookie count is different.");
    }
}