package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    @FindBy(id = "email")
    WebElement emailField;
    @FindBy(id = "password")
    WebElement passwordField;
    @FindBy(css = "[type=submit]")
    WebElement submitButton;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
    public void DoLogin(String email, String password){
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        submitButton.click();
    }

}
