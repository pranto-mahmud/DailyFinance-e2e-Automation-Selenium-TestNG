package pages;

import config.UserModel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class RegistrationPage {
 @FindBy(id = "firstName")
 WebElement txtFirstName;
 @FindBy(id = "lastName")
 WebElement txtLastName;
 @FindBy(id = "email")
 WebElement txtemail;
 @FindBy(name = "password")
 WebElement txtPassword;
 @FindBy(id = "phoneNumber")
 WebElement txtPhoneNumber;
 @FindBy(id = "address")
 WebElement txtAddress;
 @FindBy(css = "[type=radio]")
 List<WebElement> rbGender;
 @FindBy(css ="[type=checkbox]")
 WebElement ChkAgrement;
 @FindBy(id = "register")
 WebElement btnRegister;


public RegistrationPage (WebDriver driver) {
    PageFactory.initElements(driver, this);
}
public void DoReg(UserModel userModel) throws InterruptedException {
 txtFirstName.sendKeys(userModel.getFirstname());
 txtLastName.sendKeys(userModel.getLastname());
 txtemail.sendKeys(userModel.getEmail());
 txtPassword.sendKeys(userModel.getPassword());
 txtPhoneNumber.sendKeys(userModel.getPhonenumber());
 txtAddress.sendKeys(userModel.getAddress());
 rbGender.get(0).click();
 ChkAgrement.click();
 btnRegister.click();
}
}

