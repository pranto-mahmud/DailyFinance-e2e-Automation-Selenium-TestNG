package tests;

import com.github.javafaker.Faker;
import config.Setup;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.AddItemPage;
import utils.Utils;

import java.io.IOException;
import java.time.Duration;

public class AddItemTest extends Setup {

   @Test(priority = 7, description = "Varify that user can add item only for all fields")
       public void additemallfields() throws InterruptedException, IOException, ParseException {
        String token = Utils.getToken("./src/test/resources/LocalStorage.json");
        driver.get("https://dailyfinance.roadtocareer.net/");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.localStorage.setItem('authToken', arguments[0]);", token);
        driver.navigate().refresh();
        driver.get("https://dailyfinance.roadtocareer.net/user");
        AddItemPage addItemPage = new AddItemPage(driver);
        Faker faker = new Faker();
        String Firstitem="Item " +faker.commerce().productName();
        String amount="" + Utils.genarateRandomNumber(10, 50);
        String rmarks="Best";
        addItemPage.addItemAllFields(Firstitem,amount,"04","05","2026","May",rmarks);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("table")));
        Assert.assertTrue(addItemPage.isItemPresentInTable(Firstitem),
                "Item 1 is NOT present in the table");

}

    @Test(priority = 8, description = "Varify that user can add item only for mandatory fields")
    public void additemmandatoryfields() throws InterruptedException, IOException, ParseException {
        AddItemPage addItemPage = new AddItemPage(driver);
        Faker faker = new Faker();
        String Secondtitem="Item " +faker.commerce().productName();
        String amount="" + Utils.genarateRandomNumber(10, 50);
        addItemPage.addItemMandatoryFields(Secondtitem,amount,"04","02","2026");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("table")));
        Assert.assertTrue(addItemPage.isItemPresentInTable(Secondtitem),
                "Item 2 is NOT present in the table");
    }


}
