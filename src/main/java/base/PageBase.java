package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class PageBase {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public PageBase(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    }

    /**
     * Waits for the presence of all elements located by the specified locator.
     *
     * @param locator The By locator to find the elements.
     * @return A list of web elements that are present on the page.
     */
    public List<WebElement> waitForElements(By locator) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    /**
     * Waits for the presence of a single web element located by the specified locator.
     *
     * @param locator The By locator to find the element.
     */
    public void waitForElement(By locator) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * Waits for a web element to be clickable.
     *
     * @param locator The By locator to find the element.
     */
    protected void waitForClickable(By locator) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(locator));
        } catch (TimeoutException e) {
            System.out.println("Element not clickable: " + locator);
        }
    }

    /**
     * Gets the text of a web element located by the specified locator.
     *
     * @param locator The By locator to find the element.
     * @return The text of the web element.
     */
    public String getElementText(By locator) {
        return driver.findElement(locator).getText();
    }

    public void setText(By locator, String text) {
        driver.findElement(locator).sendKeys(text);
    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }
    String randomString(int length) {
        if (length <= 0) return "";
        java.security.SecureRandom rnd = new java.security.SecureRandom();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append((char) ('0' + rnd.nextInt(10)));
        }
        return sb.toString();
    }

    public static class RandomAddressGenerator {
        public static String generateStreet() {
            return "Street-" + UUID.randomUUID().toString().substring(0, 8);
        }
    }




    public void printAllCookies() {
        Set<Cookie> cookies = driver.manage().getCookies();
        System.out.println("=== Browser cookies ===");
        for (Cookie c : cookies) {
            System.out.println(c.getName() + " -> " + c.getValue() + " (httpOnly=" + c.isHttpOnly() + ", domain=" + c.getDomain() + ")");
        }
        System.out.println("=======================");
    }

}
