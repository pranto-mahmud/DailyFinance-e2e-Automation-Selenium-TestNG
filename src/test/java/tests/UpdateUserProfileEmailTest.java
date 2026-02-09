package tests;

import config.Setup;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.UserProfilePage;
import utils.Utils;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;

public class UpdateUserProfileEmailTest extends Setup {

    private String email;
    private String password;
    private String NewEmail;

    @Test(priority = 9, description = "Verify user can update email from profile")
    public void updateEmailNdLogout() throws IOException, ParseException, InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        JSONObject obj = Utils.readJSONData("./src/test/resources/User.json");
        email = obj.get("email").toString();
        password = obj.get("password").toString();
        loginPage.DoLogin(email, password);
        UserProfilePage userProfilePage = new UserProfilePage(driver);
        NewEmail="prantosm33+"+Utils.genarateRandomNumber(1000,4000)+"@gmail.com";
        userProfilePage.updateEmail(NewEmail);
        userProfilePage.Dologout();
        Object parsed = new JSONParser().parse(new FileReader("./src/test/resources/User.json"));
        JSONArray users = (JSONArray) parsed;
        JSONObject lastUser = (JSONObject) users.get(users.size() - 1);
        lastUser.put("email", NewEmail);
        FileWriter fileWriter = new FileWriter("./src/test/resources/User.json");
        fileWriter.write(users.toJSONString());
        fileWriter.flush();
        fileWriter.close();
    }
    @Test(priority = 10, description = "Varify that user cannot log in with previous email")
    public void loginWithPreviousEmail() throws IOException, ParseException, InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.DoLogin(email,password);
        WebElement element=driver.findElement(By.cssSelector("p"));
        String text=element.getText();
        Assert.assertEquals(text,"Invalid email or password");

    }
    @Test(priority = 11, description = "Varify that user can log in with updated email")
    public void loginWithUpdatedEmail() throws IOException, ParseException, InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        driver.findElement(By.id("email")).sendKeys(Keys.CONTROL+"a",Keys.BACK_SPACE);
        driver.findElement(By.id("password")).sendKeys(Keys.CONTROL+"a",Keys.BACK_SPACE);
        loginPage.DoLogin(NewEmail, password);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.urlContains("/user"));
        Assert.assertTrue(driver.getCurrentUrl().contains("/user"), "Login failed: Dashboard URL not loaded");
        UserProfilePage userProfilePage = new UserProfilePage(driver);
        userProfilePage.Dologout();
    }

}
