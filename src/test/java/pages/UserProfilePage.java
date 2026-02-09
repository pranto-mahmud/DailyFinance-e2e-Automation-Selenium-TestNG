package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Utils;

import java.util.List;

public class UserProfilePage {
    @FindBy(css = "[type=button]")
    WebElement menuButton;
    @FindBy(css = "li")
    List<WebElement> menuItems;
    @FindBy(css= "button")
    List<WebElement> editButton;
    @FindBy(id = ":rf:")
    WebElement emailField;
    @FindBy(xpath = "//button[normalize-space()='Update']")
    WebElement updateButton;
    WebDriver driver;

public UserProfilePage(WebDriver driver) {
    PageFactory.initElements(driver,this);
    this.driver=driver;
}


public void updateEmail(String email) throws InterruptedException {
    menuButton.click();
    menuItems.get(0).click();
    editButton.get(1).click();
    Utils.scrolldown(driver, 400);
    emailField.click();
    emailField.sendKeys(Keys.CONTROL, "a");
    emailField.sendKeys(Keys.BACK_SPACE);
    emailField.sendKeys(email);
    updateButton.click();
    Thread.sleep(1000);
    driver.switchTo().alert().accept();
}
public void Dologout() throws InterruptedException {
    menuButton.click();
    menuItems.get(1).click();
}

}
