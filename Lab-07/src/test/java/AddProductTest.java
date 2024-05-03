import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)


public class AddProductTest {
    private static WebDriver chrome;
    private static JavascriptExecutor js;
    private static String url;

    private static String email;
    private static String password;

    private static void login(){
        chrome.get("https://practicesoftwaretesting.com/#/auth/login");
        WebDriverWait wait=new WebDriverWait(chrome, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("email")));
        WebElement username=chrome.findElement(By.id("email"));
        WebElement password=chrome.findElement(By.id("password"));
        WebElement loginButton=chrome.findElement(By.xpath("/html/body/app-root/div/app-login/div/div/div/form/div[3]/input"));
        username.sendKeys(AddProductTest.email);
        password.sendKeys(AddProductTest.password);
        Actions actions=new Actions(chrome);
        actions.moveToElement(loginButton).click().pause(Duration.ofSeconds(2)).perform();
    }

    private static void goToProductPage(){
        chrome.get("https://practicesoftwaretesting.com/#/");
        WebDriverWait wait = new WebDriverWait(chrome, Duration.ofSeconds(10));
        WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/div/app-overview/div[3]/div[2]/div[1]/a[1]")));
        Actions actions=new Actions(chrome);
        actions.moveToElement(element).click().pause(Duration.ofSeconds(2)).perform();
        url=chrome.getCurrentUrl();
    }

    @BeforeAll
    public static void initialization() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        AddProductTest.js = (JavascriptExecutor) AddProductTest.chrome;
        AddProductTest.email="customer@practicesoftwaretesting.com";
        AddProductTest.password="welcome01";
        AddProductTest.login();
        AddProductTest.goToProductPage();
    }

    @Test
    public void addToCartCartComponentTitle() {
        chrome.get(url);
        WebDriverWait wait = new WebDriverWait(chrome, Duration.ofSeconds(10));
        WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"btn-increase-quantity\"]/i")));
        Actions actions = new Actions(chrome);
        actions.moveToElement(element).click().pause(Duration.ofSeconds(2)).perform();
        WebElement addToCart=chrome.findElement(By.xpath("//*[@id=\"btn-add-to-cart\"]"));
        actions.moveToElement(addToCart).click().pause(Duration.ofSeconds(2)).perform();
        WebDriverWait waitAgain = new WebDriverWait(chrome, Duration.ofSeconds(10));
        WebElement visibleCart=waitAgain.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"lblCartCount\"]")));
        actions.moveToElement(visibleCart).click().pause(Duration.ofSeconds(5)).perform();
        WebElement cartComponentTitle=chrome.findElement(By.xpath("/html/body/app-root/div/app-checkout/aw-wizard/div/aw-wizard-step[1]/app-cart/div/table/tbody/tr/td[1]/span"));
        String actualTitle=cartComponentTitle.getText();
        String expectedTitle="Combination Pliers";
        assertTrue(actualTitle.contains(expectedTitle));
    }

    @Test
    public void paymentBuyNowPayLater(){
        WebElement state=chrome.findElement(By.id("state"));
        state.sendKeys("RandomState");
        WebElement postcode=chrome.findElement(By.id("postcode"));
        postcode.sendKeys("1218");
        WebElement proceedToPaymentButton=chrome.findElement(By.xpath("/html/body/app-root/div/app-checkout/aw-wizard/div/aw-wizard-step[3]/app-address/div/div/div/div/button"));
        Actions actions=new Actions(chrome);
        actions.moveToElement(proceedToPaymentButton).click().pause(Duration.ofSeconds(2)).perform();
        WebElement paymentButton=chrome.findElement(By.xpath("/html/body/app-root/div/app-checkout/aw-wizard/div/aw-wizard-completion-step/app-payment/div/div/div/div/button"));
        actions.moveToElement(paymentButton).click().pause(Duration.ofSeconds(2)).perform();
        WebElement select=chrome.findElement(By.xpath("//*[@id=\"payment-method\"]"));
        select.sendKeys("Buy Now Pay Later");
        WebElement installments=chrome.findElement(By.xpath("//*[@id=\"monthly_installments\"]"));
        installments.sendKeys("3 monthly installments");
        WebElement payButton=chrome.findElement(By.xpath("/html/body/app-root/div/app-checkout/aw-wizard/div/aw-wizard-completion-step/app-payment/div/div/div/div/button"));
        actions.moveToElement(payButton).click().pause(Duration.ofSeconds(2)).perform();
        WebElement paymentConfirmation=chrome.findElement(By.xpath("/html/body/app-root/div/app-checkout/aw-wizard/div/aw-wizard-completion-step/app-payment/div/div/div/form/div[3]/div"));
        assertEquals("Payment was successful",paymentConfirmation.getText());
    }

    @Test
    public void paymentCreditCard() {
        chrome.get(url);
        WebDriverWait wait = new WebDriverWait(chrome, Duration.ofSeconds(10));
        WebElement element=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"btn-increase-quantity\"]/i")));
        Actions actions = new Actions(chrome);
        actions.moveToElement(element).click().pause(Duration.ofSeconds(2)).perform();
        WebElement addToCart=chrome.findElement(By.xpath("//*[@id=\"btn-add-to-cart\"]"));
        actions.moveToElement(addToCart).click().pause(Duration.ofSeconds(2)).perform();
        WebDriverWait waitAgain = new WebDriverWait(chrome, Duration.ofSeconds(10));
        WebElement visibleCart=waitAgain.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"lblCartCount\"]")));
        actions.moveToElement(visibleCart).click().pause(Duration.ofSeconds(5)).perform();
        WebElement proceedToCheckout=chrome.findElement(By.xpath("/html/body/app-root/div/app-checkout/aw-wizard/div/aw-wizard-step[1]/app-cart/div/div/button"));
        actions.moveToElement(proceedToCheckout).click().pause(Duration.ofSeconds(2)).perform();
        WebElement checkoutButton=chrome.findElement(By.xpath("/html/body/app-root/div/app-checkout/aw-wizard/div/aw-wizard-step[2]/app-login/div/div/div/div/button"));
        actions.moveToElement(checkoutButton).click().pause(Duration.ofSeconds(2)).perform();
        WebElement state=chrome.findElement(By.id("state"));
        state.sendKeys("Dhaka");
        WebElement postcode=chrome.findElement(By.id("postcode"));
        postcode.sendKeys("1218");
        WebElement proceedToPaymentButton=chrome.findElement(By.xpath("/html/body/app-root/div/app-checkout/aw-wizard/div/aw-wizard-step[3]/app-address/div/div/div/div/button"));
        actions.moveToElement(proceedToPaymentButton).click().pause(Duration.ofSeconds(2)).perform();
        WebElement paymentButton=chrome.findElement(By.xpath("/html/body/app-root/div/app-checkout/aw-wizard/div/aw-wizard-completion-step/app-payment/div/div/div/div/button"));
        actions.moveToElement(paymentButton).click().pause(Duration.ofSeconds(2)).perform();
        WebElement select=chrome.findElement(By.xpath("//*[@id=\"payment-method\"]"));
        select.sendKeys("Credit Card");
        WebElement cardNumber=chrome.findElement(By.xpath("/html/body/app-root/div/app-checkout/aw-wizard/div/aw-wizard-completion-step/app-payment/div/div/div/form/div[2]/input[1]"));
        cardNumber.sendKeys("0000-0000-0000-0000");
        WebElement expiration=chrome.findElement(By.xpath("/html/body/app-root/div/app-checkout/aw-wizard/div/aw-wizard-completion-step/app-payment/div/div/div/form/div[2]/input[2]"));
        expiration.sendKeys("12/2025");
        WebElement cvc=chrome.findElement(By.xpath("/html/body/app-root/div/app-checkout/aw-wizard/div/aw-wizard-completion-step/app-payment/div/div/div/form/div[2]/input[3]"));
        cvc.sendKeys("123");
        WebElement cardHolder=chrome.findElement(By.xpath("/html/body/app-root/div/app-checkout/aw-wizard/div/aw-wizard-completion-step/app-payment/div/div/div/form/div[2]/input[4]"));
        cardHolder.sendKeys("Jane Doe");
        WebElement payButton=chrome.findElement(By.xpath("/html/body/app-root/div/app-checkout/aw-wizard/div/aw-wizard-completion-step/app-payment/div/div/div/div/button"));
        actions.moveToElement(payButton).click().pause(Duration.ofSeconds(2)).perform();
        WebElement paymentConfirmation=chrome.findElement(By.xpath("/html/body/app-root/div/app-checkout/aw-wizard/div/aw-wizard-completion-step/app-payment/div/div/div/form/div[3]/div"));
        assertEquals("Payment was successful",paymentConfirmation.getText());
    }

    @AfterAll
    public static void tearDown() {
        chrome.quit();
    }
}