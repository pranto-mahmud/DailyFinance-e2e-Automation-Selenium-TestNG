package pages;

import config.UserModel;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResetPassPage {
    @FindBy(css = "[type=email]")
    WebElement email;
    @FindBy(css = "[type=submit]")
    WebElement  submit;
    @FindBy(tagName = "p")
    WebElement responseMessage;

    public ResetPassPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
    public void ResetPassword(String Email) {
        email.click();
        email.sendKeys(Keys.CONTROL, "a");
        email.sendKeys(Keys.BACK_SPACE);
        email.sendKeys(Email);
        submit.click();
    }
    public String getInvalidEmailPopupMessage() {
        String msg = email.getDomProperty("validationMessage"); // selenium 4
        return msg == null ? "" : msg;
    }
    public String getNotRegisteredErrorText(){
        return responseMessage.getText();
    }
    public String isResetLinkSentMessage(){
        return responseMessage.getText();
    }

}

