package NavigationAndClosing;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class WebElementsLoginZadanie extends BaseTestelkaTest{
    String loginEmail = "qwe@qwe.pl";
    String loginUsername = "qwe-1176";
    String loginUsernameWrong = "qwe-11761";
    String loginEmailWrong = "qwe@qweeee.pl";
    String password = "WT52t(6m.OjJ4D:!";
    String passwordWrong = "rhwgrbe";

    private void loginFields(String usernameField, String passwordField){
        driver.findElement(By.cssSelector("input[id='username']")).sendKeys(usernameField);
        driver.findElement(By.cssSelector("input[id='password']")).sendKeys(passwordField);
        driver.findElement(By.cssSelector("button[name='login']")).click();
    }

    @Test
    public void emailLoginSuccessful(){
        loginFields(loginEmail, password);
        String myAccountContent = driver.findElement(
                By.cssSelector("div[class='woocommerce-MyAccount-content']>p"))
                .getText();
        Assertions.assertTrue(myAccountContent.contains(loginUsername),
                "My account page does not have correct data. Expected: "
                + loginUsername + " was not found.");
    }

    @Test
    public void usernameLoginSuccessful(){
        loginFields(loginUsername, password);
        String myAccountContent = driver.findElement(
                By.cssSelector("div[class='woocommerce-MyAccount-content']>p")).
                getText();
        Assertions.assertTrue(myAccountContent.contains(loginUsername),
                "My account page does not have correct data. Expected: "
                + loginUsername + " was not found.");
    }

    @Test
    public void loginUnsuccessfulDueEmail(){
        loginFields(loginEmailWrong, password);
        String errorLogin = driver.findElement(
                By.cssSelector("ul[class='woocommerce-error']>li")).
                getText();
        Assertions.assertTrue(errorLogin.contains("Nieznany adres email."),
                "Login was successful.");
    }

    @Test
    public void loginUnsuccessfulDueLogin(){
        loginFields(loginUsernameWrong, password);
        String errorLogin = driver.findElement(
                By.cssSelector("ul[class='woocommerce-error']>li")).
                getText();
        Assertions.assertTrue(errorLogin.contains("Nieznany użytkownik."),
                "Login was successful.");
    }

    @Test
    public void loginUnsuccessfulDuePassword(){
        loginFields(loginUsername, passwordWrong);
        String errorLogin = driver.findElement(
                By.cssSelector("ul[class='woocommerce-error']>li>a")).
                getText();
        Assertions.assertTrue(errorLogin.contains("Nie pamiętasz hasła?"),
                "Login was successful.");
    }

    @Test
    public void loginUnsuccessfulDueFieldsEmpty(){
        driver.findElement(By.cssSelector("button[name='login']")).click();
        String errorLogin = driver.findElement(
                By.cssSelector("ul[class='woocommerce-error']>li")).
                getText();
        Assertions.assertTrue(errorLogin.contains("Nazwa użytkownika jest wymagana."),
                "Login was successful.");
    }

    @Test
    public void loginUnsuccessfulDuePasswordEmpty(){
        loginFields(loginEmail,"");
        String errorLogin = driver.findElement(
                By.cssSelector("ul[class='woocommerce-error']>li")).
                getText();
        Assertions.assertTrue(errorLogin.contains("Hasło jest puste."),
                "Login was successful.");
    }
}