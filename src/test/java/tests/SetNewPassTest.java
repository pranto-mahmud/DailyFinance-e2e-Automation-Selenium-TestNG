package tests;

import config.Setup;
import netscape.javascript.JSObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SetNewPassPage;
import services.GmailService;
import utils.Utils;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SetNewPassTest extends Setup {
   @Test(priority = 5, description = "Verify user can reset password by retrieve Reset Password Link and set new Password")
    public void SetNewPass() throws IOException, ParseException {
        GmailService gs=new GmailService();
        String link= gs.resetpasslink();
        driver.get(link);
        SetNewPassPage npt = new SetNewPassPage(driver);
        String pass="pranto123";
        npt.SetPass(pass,pass);
        String actualError = npt.ResetConfirmMassage();
        Assert.assertEquals(actualError.trim(), "Password reset successfully");
       Object parsed = new JSONParser().parse(new FileReader("./src/test/resources/User.json"));
       JSONArray users = (JSONArray) parsed;
       JSONObject lastUser = (JSONObject) users.get(users.size() - 1);
       lastUser.put("password", pass);
       FileWriter fileWriter = new FileWriter("./src/test/resources/User.json");
       fileWriter.write(users.toJSONString());
       fileWriter.flush();
       fileWriter.close();

    }
}
