package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AdminDashboardPage {
    @FindBy(className = "search-box")
    WebElement searchBox;
    @FindBy(css = "table tbody tr")
    List<WebElement> tableRows;


public AdminDashboardPage(WebDriver driver) {
    PageFactory.initElements(driver, this);
    }


    public void findUpdatedGmail(String email) {
    searchBox.sendKeys(email);
    }
}
