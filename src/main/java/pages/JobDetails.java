package pages;

import base.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class JobDetails extends PageBase {

    String dateStr = "2015-15-06";

    By joinedDateInput = By.xpath("//label[text()='Joined Date']/following::input[1]");
    By jobTitleDdl = By.xpath("(//div[contains(@class, 'oxd-select-text--active')])[1]");
    By employmentStatusDdl = By.xpath("(//div[contains(@class, 'oxd-select-text--active')])[5]");
    By subUnitDdl = By.xpath("(//div[contains(@class, 'oxd-select-text--active')])[3]");
    By locationDdl = By.xpath("(//div[contains(@class, 'oxd-select-text--active')])[4]");
    By contractDetailsToggle = By.xpath("(//p[text()='Include Employment Contract Details']/following::span)[1]");
    By contractStartDate = By.xpath("//label[text()='Contract Start Date']/following::input[1]");
    By contractEndDate = By.xpath("//label[text()='Contract End Date']/following::input[1]");
    By saveButton = By.xpath("(//button[@type='submit'])[1]");

    public JobDetails(WebDriver driver) {
        super(driver);
    }

    public void fillJobDetails() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyy-dd-MM");
        String today = LocalDate.now().format(formatter);
        String endDate = LocalDate.now().plusYears(1).format(formatter);

        // Joined Date
        pickJoinedDate(dateStr);
        // Job Title
        selectDropdownOption(driver, jobTitleDdl, "Software Engineer");
        selectDropdownOption(driver, employmentStatusDdl, "Part-Time Internship");
        selectDropdownOption(driver, subUnitDdl, "Quality Assurance");
        selectDropdownOption(driver, locationDdl, "New York Sales Office");
        clickContractDetailsToggle();
        setContractStartDate(today);
        setContractEndDate(endDate);
        clickSaveButton();

    }

    public void pickJoinedDate(String dateStr) {
        waitForElement(joinedDateInput);
        setText(joinedDateInput, dateStr);
    }

    public void clickContractDetailsToggle() {
        waitForElement(contractDetailsToggle);
        click(contractDetailsToggle);
    }

    public void setContractStartDate(String contractStartDateTxt) {
        waitForElement(contractStartDate);
        setText(contractStartDate, contractStartDateTxt);
    }

    public void setContractEndDate(String contractStartDate) {
        waitForElement(contractEndDate);
        setText(contractEndDate, contractStartDate);
    }

    public void clickSaveButton() {
        waitForClickable(saveButton);
        click(saveButton);
    }
}
