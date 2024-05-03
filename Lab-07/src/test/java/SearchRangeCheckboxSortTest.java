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

public class SearchRangeCheckboxSortTest {
    public static WebDriver chrome;
    public static JavascriptExecutor js;

    @BeforeAll
    public static void initializeSelenium() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
        chrome = new ChromeDriver();
        js = (JavascriptExecutor) chrome;
    }

    @Test
    public void searchBar(){
        chrome.get("https://practicesoftwaretesting.com/");
        chrome.manage().window().maximize();
        chrome.findElement(By.cssSelector("*[data-test=\"search-query\"]")).click();
        chrome.findElement(By.cssSelector("*[data-test=\"search-query\"]")).sendKeys("Thor Hammer");
        chrome.findElement(By.cssSelector("*[data-test=\"search-query\"]")).sendKeys(Keys.ENTER);

        WebDriverWait wait = new WebDriverWait(chrome, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[data-test=\"search_completed\"]")));

        String result=chrome.findElement(By.cssSelector("[data-test=\"search_completed\"]")).getText();
        assertEquals("Thor Hammer\n" +
                "$11.14", result);
    }

    @Test
    public void testPriceRange(){
        chrome.get("https://practicesoftwaretesting.com/");
        chrome.manage().window().maximize();
        chrome.findElement(By.cssSelector(".ngx-slider-full-bar > .ngx-slider-span")).click();
        {
            WebElement element = chrome.findElement(By.cssSelector(".ngx-slider-pointer-max"));
            Actions builder = new Actions(chrome);
            builder.moveToElement(element).clickAndHold().perform();
        }
        {
            WebElement element = chrome.findElement(By.cssSelector(".ngx-slider-pointer-max"));
            Actions builder = new Actions(chrome);
            builder.moveToElement(element).perform();
        }
        {
            WebElement element = chrome.findElement(By.cssSelector(".ngx-slider-pointer-max"));
            Actions builder = new Actions(chrome);
            builder.moveToElement(element).release().perform();
        }
        chrome.findElement(By.cssSelector(".ngx-slider-pointer-max")).click();
        chrome.findElement(By.cssSelector(".ngx-slider-selection-bar")).click();
    }

    @Test
    public void testCheckBox(){
        chrome.get("https://practicesoftwaretesting.com/");
        chrome.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(chrome, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"filters\"]/div[2]/ul/div[1]/label/input")));
        chrome.findElement(By.xpath("//*[@id=\"filters\"]/div[2]/ul/div[1]/label/input")).click();
        chrome.findElement(By.xpath("//*[@id=\"filters\"]/div[6]/label/input")).click();

        boolean enabled1=chrome.findElement(By.xpath("//*[@id=\"filters\"]/div[2]/ul/div[1]/label/input")).isEnabled();
        boolean enabled2=chrome.findElement(By.xpath("//*[@id=\"filters\"]/div[6]/label/input")).isEnabled();

        assertTrue(enabled1);
        assertTrue(enabled2);
    }

    @Test
    public void testSorting() {
        chrome.get("https://practicesoftwaretesting.com/");
        chrome.findElement(By.cssSelector("*[data-test=\"sort\"]")).click();
        {
            WebElement dropdown = chrome.findElement(By.cssSelector("*[data-test=\"sort\"]"));
            dropdown.findElement(By.xpath("//option[. = 'Name (A - Z)']")).click();
        }
        chrome.findElement(By.cssSelector("*[data-test=\"sort\"]")).click();
        {
            WebElement dropdown = chrome.findElement(By.cssSelector("*[data-test=\"sort\"]"));
            dropdown.findElement(By.xpath("//option[. = 'Name (Z - A)']")).click();
        }
        chrome.findElement(By.cssSelector("*[data-test=\"sort\"]")).click();
        {
            WebElement dropdown = chrome.findElement(By.cssSelector("*[data-test=\"sort\"]"));
            dropdown.findElement(By.xpath("//option[. = 'Price (High - Low)']")).click();
        }
        chrome.findElement(By.cssSelector("*[data-test=\"sort\"]")).click();
        {
            WebElement dropdown = chrome.findElement(By.cssSelector("*[data-test=\"sort\"]"));
            dropdown.findElement(By.xpath("//option[. = 'Price (Low - High)']")).click();
        }
    }


    @AfterAll
    public static void closeSelenium() {
        chrome.quit();
    }
}
