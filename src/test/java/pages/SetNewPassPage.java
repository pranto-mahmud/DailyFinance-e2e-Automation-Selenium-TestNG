package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SetNewPassPage {
    @FindBy(id = ":r1:")
    WebElement NewPass;
    @FindBy(id = ":r3:")
    WebElement NewPassconfirm;
    @FindBy(css = "[type=submit]")
    WebElement Submit;
    @FindBy(tagName = "p")
    WebElement resMessage;

    public SetNewPassPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
    public void  SetPass(String pass,String passconfirm) {
        NewPass.sendKeys(pass);
        NewPassconfirm.sendKeys(pass);
        Submit.click();
    }
    public String ResetConfirmMassage() {
        return resMessage.getText();
    }
}
