package pages;

import base.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.awt.*;

public class ContactDetailsPage extends PageBase {
    By street1TextBox = By.xpath("//label[text()='Street 1']/following::input[1]");
    By street2TextBox = By.xpath("//label[text()='Street 2']/following::input[1]");
    By cityTextBox = By.xpath("//label[text()='City']/following::input[1]");
    By stateTextBox = By.xpath("//label[text()='State/Province']/following::input[1]");
    By zipTextBox = By.xpath("//label[text()='Zip/Postal Code']/following::input[1]");
    By homeTeleTextBox = By.xpath("//label[text()='Home']/following::input[1]");
    By mobileTextBox = By.xpath("//label[text()='Mobile']/following::input[1]");
    By workTextBox = By.xpath("//label[text()='Work']/following::input[1]");
    By workEmailTextBox = By.xpath("//label[text()='Work Email']/following::input[1]");
    By otherEmailTextBox = By.xpath("//label[text()='Other Email']/following::input[1]");
    By saveChanges = By.xpath("(//button[@type='submit'])[1]");
    By addAttachmentButton = By.xpath("//*[@class= 'orangehrm-attachment']//button");
    By browserButton = By.xpath("//*[normalize-space()='Browse']");
    By submitAttachment = By.xpath("(//button[@type='submit'])[2]");
    By toastMessage = By.cssSelector(".oxd-text--toast-message");
    By firstRecordInAttachmentSection = By.cssSelector(".oxd-table-card");
    By recordNo = By.cssSelector(".orangehrm-attachment .oxd-text--span");


    String filePath = "D:\\WebAut\\src\\test\\resources\\Screenshot.png";

    public ContactDetailsPage(WebDriver driver) {
        super(driver);
    }

    public void fillContactDetailsForm(String street1, String street2, String city, String state,
                                       String zip, String homeTele, String mobile,
                                       String work, String workEmail, String otherEmail) {
        enterStreet1(street1);
        enterStreet2(street2);
        enterCity(city);
        enterState(state);
        enterZip(zip);
        enterHomeTelephone(homeTele);
        enterMobile(mobile);
        enterWorkTelephone(work);
        enterWorkEmail(workEmail);
        enterOtherEmail(otherEmail);
    }

    public void addAttachment() throws InterruptedException, AWTException {
        waitForClickable(addAttachmentButton);
        click(addAttachmentButton);
        waitForClickable(browserButton);
        click(browserButton);
        uploadFilesByRobot(filePath);
        waitForElement(submitAttachment);
        click(submitAttachment);
    }

    public void enterStreet1(String street1) {
        waitForElement(street1TextBox);
        setText(street1TextBox, street1);
    }

    public void enterStreet2(String street2) {
        waitForElement(street2TextBox);
        setText(street2TextBox, street2);
    }

    public void enterCity(String city) {
        waitForElement(cityTextBox);
        setText(cityTextBox, city);
    }

    public void enterState(String state) {
        waitForElement(stateTextBox);
        setText(stateTextBox, state);
    }

    public void enterZip(String zip) {
        waitForElement(zipTextBox);
        setText(zipTextBox, zip);
    }


    public void enterHomeTelephone(String homeTele) {
        waitForElement(homeTeleTextBox);
        setText(homeTeleTextBox, homeTele);
    }

    public void enterMobile(String mobile) {
        waitForElement(mobileTextBox);
        setText(mobileTextBox, mobile);
    }

    public void enterWorkTelephone(String work) {
        waitForElement(workTextBox);
        setText(workTextBox, work);
    }

    public void enterWorkEmail(String workEmail) {
        waitForElement(workEmailTextBox);
        setText(workEmailTextBox, workEmail);
    }

    public void enterOtherEmail(String otherEmail) {
        waitForElement(otherEmailTextBox);
        setText(otherEmailTextBox, otherEmail);
    }

    public void clickSaveChangestButton() {
        waitForElement(saveChanges);
        click(saveChanges);
    }

    public String getToastMessage() {
        waitForElement(toastMessage);
        return getElementText(toastMessage);
    }

    public boolean isOneRecordDisplayedInAttachmentSection() {
        waitForElement(firstRecordInAttachmentSection);
        return isElementDisplayed(firstRecordInAttachmentSection);
    }

    public String getNumberOfRecordsInAttachmentSection() {
        waitForElement(recordNo);
        return getElementText(recordNo);
    }


}
