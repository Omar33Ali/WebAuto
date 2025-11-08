package pages;

import base.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class HomePage extends PageBase {


    By usernameTextBox = By.xpath("//input[@name='username']");
    By passwordTextBox = By.xpath("//input[@name='password']");
    By loginButton = By.xpath("//button[@type='submit']");
    By pimButton = By.xpath("(//a[@class='oxd-main-menu-item'])[2]");
    By employeeIdTextBox = By.xpath("//label[text()='Employee Id']/following::input[1]");
    By searchButton = By.xpath("(//button[@type='submit'])[1]");
    By editButton = By.xpath("(//*[@class='oxd-table-cell-actions']/button)[1]");
    By contactDetailsTab = By.xpath("//a[text()='Contact Details']");
    By jobTab = By.xpath("//a[text()='Job']");


    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    public void navigateToContactDetails() {
        clickEditButton();
        clickContactDetailsTab();
    }

    private void enterUsername(String username) {
        waitForElement(usernameTextBox);
        setText(usernameTextBox, username);
    }

    private void enterPassword(String password) {
        waitForElement(passwordTextBox);
        setText(passwordTextBox, password);
    }

    private void clickLoginButton() {
        waitForClickable(loginButton);
        click(loginButton);
    }

    public void clickPimButton() {
        waitForElement(pimButton);
        click(pimButton);
    }


    public void searchByEmployeeId(String employeeId) {
        waitForElement(employeeIdTextBox);
        setText(employeeIdTextBox, employeeId);

    }

    public void clickSearchButton() {
        waitForElement(searchButton);
        click(searchButton);
    }

    public void clickEditButton() {
        waitForElement(editButton);
        click(editButton);
    }

    public void clickContactDetailsTab() {
        waitForElement(contactDetailsTab);
        click(contactDetailsTab);
    }

    public void clickJobTab() {
        waitForElement(jobTab);
        click(jobTab);
    }


}
