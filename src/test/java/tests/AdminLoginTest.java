package tests;

import config.Setup;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.AdminDashboardPage;
import pages.LoginPage;
import utils.Utils;

import java.io.IOException;
import java.time.Duration;

public class AdminLoginTest extends Setup {
    @Test(priority = 12, description = "Verify admin can login using secure terminal credentials")
    public void AdminLogin() {
       LoginPage loginPage = new LoginPage(driver);
        String email=System.getProperty("email");
       String password=System.getProperty("password");
       loginPage.DoLogin(email, password);
    }
    @Test(priority = 13, description ="Verify updated user email appears in admin dashboard search")
    public void SearchUpdatedGmail() throws IOException, ParseException {
        AdminDashboardPage adminDashboardPage = new AdminDashboardPage(driver);
        JSONObject obj = Utils.readJSONData("./src/test/resources/User.json");
        String email = obj.get("email").toString();
        adminDashboardPage.findUpdatedGmail(email);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        By emailCellLocator = By.xpath("//table//tbody//tr/td[3][contains(normalize-space(),'" + email + "')]");
        WebElement emailCell = wait.until(ExpectedConditions.visibilityOfElementLocated(emailCellLocator));
        String actualEmail = emailCell.getText().trim();
        Assert.assertEquals(actualEmail, email, "Searched email does not match in admin table");
        System.out.println(actualEmail);
    }
}