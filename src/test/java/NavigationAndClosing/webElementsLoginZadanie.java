package NavigationAndClosing;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class webElementsLoginZadanie {
    WebDriver driver;
    String loginEmail = "qwe@qwe.pl";
    String loginUsername = "qwe-1176";
    String loginUsernameWrong = "qwe-11761";
    String loginEmailWrong = "qwe@qweeee.pl";
    String password = "WT52t(6m.OjJ4D:!";
    String passwordWrong = "WT52t(6m.OjJ4D:!q";

    private void loginFields(String usernameField, String passwordField){
        driver.findElement(By.cssSelector("input[id='username']")).sendKeys(usernameField);
        driver.findElement(By.cssSelector("input[id='password']")).sendKeys(passwordField);
        driver.findElement(By.cssSelector("button[name='login']")).click();
    }

    @BeforeEach
    public void beforeEach(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(2560,1080));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.get("https://fakestore.testelka.pl/moje-konto/");
    }

    @AfterEach
    public void afterEach(){
        driver.quit();
    }

    @Test
    public void emailLoginSuccessful(){
        loginFields(loginEmail,password);
        String myAccountContent = driver.findElement(By.cssSelector("div[class='woocommerce-MyAccount-content']>p")).getText();
        Assertions.assertTrue(myAccountContent.contains(loginUsername), "My account page does not have correct data. Expected: " + loginUsername + " was not found.");
    }

    @Test
    public void usernameLoginSuccessful(){
        loginFields(loginUsername,password);
        String myAccountContent = driver.findElement(By.cssSelector("div[class='woocommerce-MyAccount-content']>p")).getText();
        Assertions.assertTrue(myAccountContent.contains(loginUsername), "My account page does not have correct data. Expected: " + loginUsername + " was not found.");
    }

    @Test
    public void loginUnsuccessfulDueEmail(){
        loginFields(loginEmailWrong,password);
        String errorLogin = driver.findElement(By.cssSelector("ul[class='woocommerce-error']>li")).getText();
        Assertions.assertTrue(errorLogin.contains("Nieznany adres email."), "Login was successful.");
    }

    @Test
    public void loginUnsuccessfulDueLogin(){
        loginFields(loginUsernameWrong,password);
        String errorLogin = driver.findElement(By.cssSelector("ul[class='woocommerce-error']>li")).getText();
        Assertions.assertTrue(errorLogin.contains("Nieznany użytkownik."), "Login was successful.");
    }

    @Test
    public void loginUnsuccessfulDuePassword(){
        loginFields(loginUsername,passwordWrong);
        String errorLogin = driver.findElement(By.cssSelector("ul[class='woocommerce-error']>li>a")).getText();
        Assertions.assertTrue(errorLogin.contains("Nie pamiętasz hasła?"), "Login was successful.");
    }

    @Test
    public void loginUnsuccessfulDueFieldsEmpty(){
        driver.findElement(By.cssSelector("button[name='login']")).click();
        String errorLogin = driver.findElement(By.cssSelector("ul[class='woocommerce-error']>li")).getText();
        Assertions.assertTrue(errorLogin.contains("Nazwa użytkownika jest wymagana."), "Login was successful.");
    }

    @Test
    public void loginUnsuccessfulDuePasswordEmpty(){
        driver.findElement(By.cssSelector("input[id='username']")).sendKeys(loginEmail);
        driver.findElement(By.cssSelector("button[name='login']")).click();
//        Tutaj próbowałem zrobić coś takieg:
//        loginFields(loginUsername,null);
//        ale wpisuje mi tego nulla więc nie bardzo wiedziałem jak to ugryźć dlatego skorzystałem z tych 2 linijek które są wyżej
        String errorLogin = driver.findElement(By.cssSelector("ul[class='woocommerce-error']>li")).getText();
        Assertions.assertTrue(errorLogin.contains("Hasło jest puste."), "Login was successful.");
    }
}