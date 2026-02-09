package tests;

import config.Setup;
import config.UserModel;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.RegistrationPage;
import services.GmailService;
import utils.Utils;

import java.io.IOException;
import java.util.List;

public class RegisterUserFromCSVTest extends Setup {

    @DataProvider(name = "csvUsers")
    public Object[][] csvUsers() throws IOException {

        List<UserModel> users = Utils.readUsersFromCSV("./src/test/resources/UsersData.csv");

        int n = Math.min(3, users.size());
        Object[][] data = new Object[n][1];

        for (int i = 0; i < n; i++) {
            data[i][0] = users.get(i);
        }
        return data;
    }

    @Test( priority = 14, dataProvider = "csvUsers", description = "Verify multiple users can register using CSV data")
    public void registerUsersFromCSV(UserModel userModel) throws InterruptedException, IOException, ParseException {

        RegistrationPage registrationPage = new RegistrationPage(driver);

        driver.findElement(By.partialLinkText("Register")).click();
        registrationPage.DoReg(userModel);
        Utils.saveJSONData(userModel);

        Thread.sleep(5000);
        GmailService gs=new GmailService();
        String resEmailActual=gs.readEmail();
        System.out.println(resEmailActual);
        String resEmailexpected="Welcome to our platform";
        Assert.assertTrue(resEmailActual.contains(resEmailexpected));
    }
}

