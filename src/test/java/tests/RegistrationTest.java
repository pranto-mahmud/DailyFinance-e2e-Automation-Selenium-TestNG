package tests;

import com.github.javafaker.Faker;
import config.Setup;
import config.UserModel;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.RegistrationPage;
import services.GmailService;
import utils.Utils;

import java.io.IOException;

public class RegistrationTest extends Setup {
    @Test(priority =1,description = "Verify successful user registration and confirmation email delivery")
    public void UserRegistration() throws InterruptedException, IOException, ParseException {
        RegistrationPage registrationPage = new RegistrationPage(driver);
        Faker faker = new Faker();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = "prantosm33+"+ Utils.genarateRandomNumber(100,500) +"@gmail.com";
        String password = "12345";
        String phoneNumber = "0171"+Utils.genarateRandomNumber(1000000,9999999);
        String adress = faker.address().fullAddress();
        driver.findElement(By.partialLinkText("Register")).click();
        UserModel userModel=new UserModel();
        userModel.setFirstname(firstName);
        userModel.setLastname(lastName);
        userModel.setEmail(email);
        userModel.setPassword(password);
        userModel.setPhonenumber(phoneNumber);
        userModel.setAddress(adress);
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
