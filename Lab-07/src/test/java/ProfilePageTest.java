import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProfilePageTest {
    public static WebDriver chrome;
    public static JavascriptExecutor js;

    @BeforeAll
    public static void initializeSelenium() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        chrome = new ChromeDriver();
        js = (JavascriptExecutor) chrome;
    }

    @Test
    public void testProfilePage() {
        chrome.get("https://practicesoftwaretesting.com/#/auth/login");
        chrome.manage().window().maximize();
        chrome.get("https://practicesoftwaretesting.com/#/auth/login");
        chrome.findElement(By.id("email")).sendKeys("customer@practicesoftwaretesting.com");
        chrome.findElement(By.id("password")).sendKeys("welcome01");
        chrome.findElement(By.cssSelector("*[data-test=\"login-submit\"]")).click();

        WebDriverWait wait = new WebDriverWait(chrome, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("*[data-test=\"nav-profile\"]")));
        chrome.findElement(By.cssSelector("*[data-test=\"nav-profile\"]")).click();
        chrome.findElement(By.id("phone")).sendKeys("014425872668");
        chrome.findElement(By.cssSelector("*[data-test=\"update-profile-submit\"]")).click();

        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("/html/body/app-root/div/app-profile/div/form/div/div/div/div"), "Thanks for your message! We will contact you shortly."));

        String message = chrome.findElement(By.xpath("/html/body/app-root/div/app-profile/div/form/div/div/div/div")).getText();

        assertEquals("Your profile is successfully updated!", message);

    }

    @AfterAll
    public static void closeSelenium() {
        chrome.quit();
    }
}
