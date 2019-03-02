import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;

public class PensionWebTest {

    private static AndroidDriver<MobileElement> driver;

    @BeforeClass
    public static void setUp() {
        try {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setBrowserName("Chrome");
            caps.setCapability("platformName", "Android");
            caps.setCapability("platformVersion", "6.0");
            caps.setCapability("deviceName", "21019afe");
            driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
            //driver.toggleWifi();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testBenefits() {
        driver.get("https://www.gov.uk/");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.titleContains("GOV"));
        assertEquals("https://www.gov.uk/", driver.getCurrentUrl());


        MobileElement housing = driver.findElementByPartialLinkText("Housing and");
        System.out.println(housing.getText());

        driver.findElementByPartialLinkText("Benefits").click();
        wait.until(ExpectedConditions.urlContains("browse"));

        Set<String> contexts = driver.getContextHandles();
        for (String ctxt : contexts) {
            System.out.println(ctxt);
        }

        Object[] c = contexts.toArray();
        driver.context((String) c[0]);

        List<MobileElement> txt = driver.findElementsByClassName("android.view.View");
        for (int i = 0; i < txt.size(); i++) {
            System.out.println("Element = " + txt.get(i).getText());
            if (txt.get(i).getText().contains("How benefits work")) {
                txt.get(i).click();
                break;
            }
        }

        driver.context((String) c[1]);
        driver.findElementByPartialLinkText("Benefits calculators").click();
        wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("entitledto"))).click();
    }


    @AfterClass
    public static void afterTest() {
        //driver.toggleWifi();
        driver.quit();
    }
}
