package testPackage;
import base.TestBase;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.Api;
import pages.ContactDetailsPage;
import pages.HomePage;
import pages.JobDetails;
import utilits.ConfigReader;
import java.awt.*;
import java.util.List;
import static base.PageBase.generateStreet;

@Epic("Search Functionality")
@Feature("OrangeHRM Employee Management")
public class OrangeHRMTests extends TestBase {

    HomePage homePage;
    SoftAssert softAssert;
    ConfigReader configReader;
    Api api;
    ContactDetailsPage contactDetailsPage;
    JobDetails jobDetails;


    @Story("Add new employee via API, update data, verify in UI, fill Contact Details and Job Details forms")
    @Description("Add new employee via API, update data, verify in UI, fill Contact Details and Job Details forms.")
    @Test()
    public void orangeHRMTest() throws AWTException, InterruptedException {
        homePage = new HomePage(driver);
        softAssert = new SoftAssert();
        configReader = new ConfigReader();
        api = new Api(driver);
        contactDetailsPage = new ContactDetailsPage(driver);
        jobDetails = new JobDetails(driver);


        // Perform login
        String userName = (String) testData.get("userName");
        String passWord = (String) testData.get("passWord");
        homePage.login(userName, passWord);
        Allure.step("Login performed with username: " + userName);
        Allure.step("Login performed with password: " + passWord);

        // Add new employee using API and extract IDs
        List<String> data = api.extractIds(api.addNewEmployeeUsingApi());
        String empId = data.getFirst();
        String employeeId = data.getLast();
        Allure.step("New employee added via API with EmpID: " + empId + " and EmployeeID: " + employeeId);

        // Update employee data using API
        api.updateEmployeeDataUsingApi(empId, employeeId);
        Allure.step("Employee data updated via API for EmpID: " + empId + " and EmployeeID: " + employeeId);

        // Click on PIM button
        homePage.clickPimButton();
        Allure.step("Clicked on PIM button");
        // Verify updated employee data in UI
        homePage.searchByEmployeeId(employeeId);
        homePage.clickSearchButton();
        Allure.step("Searched for employee with EmployeeID: " + employeeId);

        // Navigate to Contact Details
        homePage.navigateToContactDetails();
        // fill Contact Details form
        String workEmail = "mostafaa9" + homePage.randomString(2) + ".qc@company.com";
        String otherEmail = "mostafaa23" + homePage.randomString(2) + ".qc@company.com";
        contactDetailsPage.fillContactDetailsForm(
                generateStreet(), generateStreet(),
                testData.get("city").toString(), testData.get("state").toString(),
                testData.get("zip").toString(), testData.get("homePhone").toString(),
                testData.get("mobile").toString(), testData.get("workPhone").toString(),
                workEmail, otherEmail
        );
        contactDetailsPage.clickSaveChangestButton();
        Allure.step("Saved changes in Contact Details form");

        String expectedMessageUpdatedData = "Successfully Updated";
        softAssert.assertEquals(contactDetailsPage.getToastMessage(), expectedMessageUpdatedData, "Toast message for updated data mismatch!");
        // Add Attachment
        contactDetailsPage.addAttachment();
        String expectedMessageAttachment = "Successfully Saved";
        String expectedExpectedNoOfRecords = "(1) Record Found";
        softAssert.assertEquals(contactDetailsPage.getToastMessage(), expectedMessageAttachment, "Toast message for saving attachment mismatch!");
        softAssert.assertTrue(contactDetailsPage.isOneRecordDisplayedInAttachmentSection(), "Attachment record is not displayed in attachment section!");
        softAssert.assertEquals(contactDetailsPage.getNumberOfRecordsInAttachmentSection(), expectedExpectedNoOfRecords, "Number of records in attachment section mismatch!");

        // click on Job Tab
        homePage.clickJobTab();
        Allure.step("Clicked on Job Tab");
        // fill Job Details form
        jobDetails.fillJobDetails();
        Allure.step("Filled Job Details form");
        Allure.step("Saved changes in Job Details form");
        softAssert.assertEquals(contactDetailsPage.getToastMessage(), expectedMessageUpdatedData, "Toast message for updated data mismatch!");
        softAssert.assertAll();

    }

}
