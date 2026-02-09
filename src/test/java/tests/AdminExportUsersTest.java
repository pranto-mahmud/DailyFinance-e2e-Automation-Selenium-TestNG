package tests;

import config.Setup;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

import java.io.FileWriter;
import java.time.Duration;
import java.util.List;

public class AdminExportUsersTest extends Setup {

    @Test(priority =15,description = "Varify login as admin and export all users from admin dashboard table to text file")
    public void exportAllUsers() throws Exception {

        String adminEmail = System.getProperty("email");
        String adminPassword = System.getProperty("password");

        Assert.assertNotNull(adminEmail, "ADMIN_EMAIL not set in terminal");
        Assert.assertNotNull(adminPassword, "ADMIN_PASSWORD not set in terminal");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.DoLogin(adminEmail, adminPassword);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//table")));

        List<WebElement> rows = driver.findElements(By.xpath("//table/tbody/tr"));
        Assert.assertTrue(rows.size() > 0, "No users found in admin table");

        String filePath = "./src/test/resources/All_users_list.txt";
        FileWriter writer = new FileWriter(filePath);
        writer.write("USERS LIST\n");
        writer.write("===========================\n\n");
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));

            String firstName = cells.get(0).getText().trim();
            String lastName = cells.get(1).getText().trim();
            String email = cells.get(2).getText().trim();
            String phone = cells.get(3).getText().trim();
            String address = cells.get(4).getText().trim();
            String gender = cells.get(5).getText().trim();
            String regDate = cells.get(6).getText().trim();

            writer.write(
                    firstName + " " + lastName +
                            " | " + email +
                            " | " + phone +
                            " | " + address +
                            " | " + gender +
                            " | " + regDate +
                            System.lineSeparator()
            );
        }

        writer.flush();
        writer.close();
    }
}
