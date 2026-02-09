package tests;

import com.github.javafaker.Faker;
import config.Setup;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.ResetPassPage;
import utils.Utils;

import java.io.IOException;
import java.time.Duration;

public class ResetPassTest extends Setup {

    @Test(priority = 2, description = "Verify error is shown for invalid email format in reset password")
    public void ResetPassWithWrongEmail() throws InterruptedException, IOException, ParseException {
        ResetPassPage rtp = new ResetPassPage(driver);
        String email="abcd"+Utils.genarateRandomNumber(10,50)+".com";
        driver.findElement(By.partialLinkText("Reset it here")).click();
        rtp.ResetPassword(email);
        String popupMsg = rtp.getInvalidEmailPopupMessage();
        Assert.assertFalse(popupMsg.isEmpty(),
                "Popup validation message should appear for invalid email.");
        Assert.assertTrue(popupMsg.contains("@"),
                "Popup should mention missing '@'. Actual: " + popupMsg);
    }
    @Test(priority = 3, description = "Verify error is shown for unregistered email in reset password")
    public void ResetPassWithUnregisteredEmail() throws InterruptedException, IOException, ParseException {
        ResetPassPage rtp = new ResetPassPage(driver);
        Faker f = new Faker();
        String email = f.name().firstName() + f.name().lastName() + "@gmail.com";

        rtp.ResetPassword(email);
        String actualError = rtp.getNotRegisteredErrorText();
        Assert.assertEquals(actualError.trim(), "Your email is not registered");
    }

    @Test(priority = 4, description = "Verify reset password link is sent for registered email")
    public void ResetPassWithValidEmail() throws InterruptedException, IOException, ParseException {
        ResetPassPage rtp = new ResetPassPage(driver);
        JSONObject jsonob=Utils.readJSONData("./src/test/resources/User.json");
        rtp.ResetPassword(jsonob.get("email").toString());
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.tagName("p")), "Password reset link sent"
        ));
        String actualError = rtp.isResetLinkSentMessage();
        Assert.assertEquals(actualError.trim(), "Password reset link sent to your email");
    }

}
