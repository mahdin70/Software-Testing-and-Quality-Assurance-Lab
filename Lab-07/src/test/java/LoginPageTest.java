import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginPageTest {
    public static WebDriver chrome;
    public static JavascriptExecutor js;

    @BeforeAll
    public static void initializeSelenium() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        chrome = new ChromeDriver();
        js = (JavascriptExecutor) chrome;
    }

    @Test
    public void testLogin() {
        chrome.get("https://practicesoftwaretesting.com/#/auth/login");
        WebDriverWait wait=new WebDriverWait(chrome, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        WebElement username=chrome.findElement(By.id("email"));
        WebElement password=chrome.findElement(By.id("password"));
        WebElement loginButton=chrome.findElement(By.xpath("/html/body/app-root/div/app-login/div/div/div/form/div[3]/input"));
        username.sendKeys("customer@practicesoftwaretesting.com");
        password.sendKeys("welcome01");
        Actions actions=new Actions(chrome);
        actions.moveToElement(loginButton).click().pause(Duration.ofSeconds(2)).perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"menu\"]")));
        String actualUrl=chrome.getCurrentUrl();
        String expectedUrl="https://practicesoftwaretesting.com/#/account";
        assertEquals(expectedUrl,actualUrl);
    }

    @Test
    public void testForgetPass(){
        chrome.get("https://practicesoftwaretesting.com/#/auth/forgot-password");
        chrome.manage().window().maximize();
        chrome.findElement(By.id("email")).sendKeys("mukit@gmail.com");
        chrome.findElement(By.cssSelector("*[data-test=\"forgot-password-submit\"]")).click();

    }

    @AfterAll
    public static void closeSelenium() {
        chrome.quit();
    }
}
