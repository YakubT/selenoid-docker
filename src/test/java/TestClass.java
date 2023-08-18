import org.openqa.selenium.By;;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class TestClass {

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.setCapability("browserVersion", "115.0.5790.102");
        options.setCapability("selenoid:options", new HashMap<String, Object>() {{
            /* How to add test badge */
            put("name", "Test badge...");

            /* How to set session timeout */
            put("sessionTimeout", "15m");

            /* How to set timezone */
            put("env", new ArrayList<String>() {{
                add("TZ=UTC");
            }});

            /* How to add "trash" button */
            put("labels", new HashMap<String, Object>() {{
                put("manual", "true");
            }});

            /* How to enable video recording */
            put("enableVideo", false);
        }});
        try {
            driver = new RemoteWebDriver(new URL("http://selenoid:4444/wd/hub"),options);
        } catch (MalformedURLException e) {
            System.out.println(e);
        }
        driver.manage().window().maximize();
    }

    @Test
    public void test() {
        driver.get("https://www.google.com/");
        Assert.assertTrue(driver.findElement(By.xpath("//textarea")).isDisplayed());
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
