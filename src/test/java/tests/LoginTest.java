package tests;

import config.Setup;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.TestNG;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.Utils;

import java.io.IOException;
import java.time.Duration;

public class LoginTest extends Setup {
    @Test(priority = 6, description = "Verify user can login with new password")
    public void UserLogin() throws IOException, ParseException {
        LoginPage loginPage = new LoginPage(driver);
        JSONObject obj = Utils.readJSONData("./src/test/resources/User.json");
        String email = obj.get("email").toString();
        String password = obj.get("password").toString();
        loginPage.DoLogin(email, password);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(d -> {
            String t = (String) ((JavascriptExecutor) d)
                    .executeScript("return window.localStorage.getItem('authToken');");
            return t != null && !t.isEmpty();
        });

        String token = (String) js.executeScript("return window.localStorage.getItem('authToken');");

        Utils.setToken("./src/test/resources/LocalStorage.json", token);

    }
}
