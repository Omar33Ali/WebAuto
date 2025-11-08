package testPackage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utilits.ConfigReader;

import java.time.Duration;

public class TestBase {

    protected WebDriver driver;
    protected ConfigReader configReader;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        // Initialize ConfigReader and load test data
        configReader = new ConfigReader();
//        testData = DataReader.readFromResource("test_data.json");
        driver.get(configReader.getProperty("baseURL"));
        // Maximize the window and set implicit wait
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    @AfterMethod
    public void tearDown() {
        // Close the browser and clean up resources
        if (driver != null) {
            driver.quit();
        }
    }


}
