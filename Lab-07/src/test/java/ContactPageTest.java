import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class ContactPageTest {
    private static WebDriver chrome;
    private static JavascriptExecutor js;

    @BeforeAll
    public static void initialization(){
        System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
        ChromeOptions options=new ChromeOptions();
        ContactPageTest.chrome=new ChromeDriver(options);
        ContactPageTest.js=(JavascriptExecutor) ContactPageTest.chrome;
    }

    @AfterEach
    public void refreshPage(){
        chrome.navigate().refresh();
    }

    @Test
    public void testSubmission(){
        chrome.get("https://practicesoftwaretesting.com/#/contact");
        WebElement firstName=chrome.findElement(By.id("first_name"));
        WebElement lastName=chrome.findElement(By.id("last_name"));
        WebElement email=chrome.findElement(By.id("email"));
        WebElement subject=chrome.findElement(By.id("subject"));
        WebElement message=chrome.findElement(By.id("message"));
        WebElement attachment=chrome.findElement(By.id("attachment"));
        WebElement submit=chrome.findElement(By.xpath("/html/body/app-root/div/app-contact/div/div/div/form/div/div[2]/div[4]/input"));
        firstName.sendKeys("Jane");
        lastName.sendKeys("Doe");
        email.sendKeys("customer@practicesoftwaretesting.com");
        subject.sendKeys("webmaster");
        message.sendKeys("Helloooooooooooooooooooooooooooooooooooooooooooooooooooooooooooooo");
        attachment.sendKeys("E:\\test.txt");
        submit.click();
        WebDriverWait wait=new WebDriverWait(chrome, Duration.ofSeconds(10));
        WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/div/app-contact/div/div/div/div")));
        String text=element.getText();
        assertEquals("Thanks for your message! We will contact you shortly.",text);

    }

    @AfterAll
    public static void closeSelenium(){
        chrome.quit();
    }
}