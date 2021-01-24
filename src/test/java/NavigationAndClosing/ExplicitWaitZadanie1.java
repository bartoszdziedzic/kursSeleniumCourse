package NavigationAndClosing;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ExplicitWaitZadanie1 extends ExplicitWaitZadanie1BeforeClass{

    private void applyCoupon(String coupon){
        By couponCodeField = By.cssSelector("input[name='coupon_code']");
        By applyCouponButton = By.cssSelector("button[name='apply_coupon']");
        driver.findElement(couponCodeField).sendKeys(coupon);
        driver.findElement(applyCouponButton).click();
    }

    private void waitForProcessingEnd(){
        By blockedUI = By.cssSelector("div.blockUI");
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(blockedUI, 0));
        wait.until(ExpectedConditions.numberOfElementsToBe(blockedUI,0));
    }

    private String getAlertText(){
        By alert = By.cssSelector("[role='alert']");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(alert)).getText();
    }

    @Test
    public void emptyCouponTest(){
        applyCoupon("");
        Assertions.assertEquals(getAlertText(),"Proszę wpisać kod kuponu.",
                "Alert message not as expected.");
    }

    @Test
    public void incorrectCouponTest(){
        applyCoupon(incorrectCoupon);
        Assertions.assertEquals(getAlertText(),"Kupon \""
                + incorrectCoupon + "\" nie istnieje!","Alert message not as expected.");
    }

    @Test
    public void correctCouponTest(){
        applyCoupon(correctCoupon);
        Assertions.assertEquals(getAlertText(),"Kupon został pomyślnie użyty.",
                "Alert message not as expected.");
    }

    @Test
    public void addingCouponWhenAlreadyAppliedTest(){
        applyCoupon(correctCoupon);
        waitForProcessingEnd();
        applyCoupon(correctCoupon);
        waitForProcessingEnd();
        Assertions.assertEquals(getAlertText(),"Kupon został zastosowany!",
                "Alert message not as expected.");
    }

    @Test
    public void removingCouponTest(){
        applyCoupon(correctCoupon);
        waitForProcessingEnd();
        By removeLink = By.cssSelector("a.woocommerce-remove-coupon");
        wait.until(ExpectedConditions.elementToBeClickable(removeLink)).click();
        waitForProcessingEnd();
        Assertions.assertEquals(getAlertText(),"Kupon został usunięty.",
                "Alert message not as expected.");
    }
}