package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import utils.Utils;

import java.util.List;


public class AddItemPage {
    @FindBy(className = "add-cost-button")
    WebElement addCostButton;
    @FindBy(id = "itemName")
    WebElement itemName;
    @FindBy(xpath = "//button[text()='+']")
    WebElement addQuantityButton;
    @FindBy(id = "amount")
    WebElement amount;
    @FindBy(id = "purchaseDate")
    WebElement purchaseDate;
    @FindBy(id = "month")
    WebElement dropdownMonth;
    @FindBy(id = "remarks")
    WebElement remarks;
    @FindBy(className = "submit-button")
    WebElement submitButton;

    @FindBy(css = "table tbody tr")
    List<WebElement> tableRows;

    WebDriver driver;


    public AddItemPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver=driver;
    }

    public void addItemAllFields(String itemname, String Amount, String day,String month,String year,String Month, String itemRemarks) throws InterruptedException {
        addCostButton.click();
        itemName.sendKeys(itemname);
        for (int i=0;i<4;i++){
        addQuantityButton.click();
        }
        amount.sendKeys(Amount);
        purchaseDate.sendKeys(day,month,year);
        Select select = new Select(dropdownMonth);
        select.selectByVisibleText(Month);
        remarks.sendKeys(itemRemarks);
        Utils.scrolldown(driver, 500);
        submitButton.click();
        Thread.sleep(1000);
        driver.switchTo().alert().accept();

    }

    public void addItemMandatoryFields(String itemname, String Amount,String day,String month,String year) throws InterruptedException {
        addCostButton.click();
        itemName.sendKeys(itemname);
        amount.sendKeys(Amount);
        purchaseDate.sendKeys(day,month,year);
        Utils.scrolldown(driver, 500);
        submitButton.click();
        Thread.sleep(1000);
        driver.switchTo().alert().accept();
    }



    public boolean isItemPresentInTable(String expectedItemName) {

        for (WebElement row : tableRows) {

            WebElement itemCell = row.findElement(By.xpath("./td[1]"));
            String actualItemName = itemCell.getText().trim();

            if (actualItemName.equalsIgnoreCase(expectedItemName.trim())) {
                return true;
            }
        }
        return false;
    }

}
