import io.appium.java_client.android.AndroidDriver;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.Set;

public class MemoTest {

    private static AndroidDriver driver;

    @BeforeClass
    public static void setUp() {
        try {
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setCapability("platformName", "Android");
            caps.setCapability("platformVersion", "6.0");
            caps.setCapability("deviceName", "21019afe");
            caps.setCapability("appPackage", "com.samsung.android.app.memo");
            caps.setCapability("appActivity", "com.samsung.android.app.memo.Main");
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
            driver.rotate(ScreenOrientation.PORTRAIT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void test1AddMemo() {
        try {
            driver.findElementByAccessibilityId("Create memo").click();

            WebElement title = driver.findElementById("com.samsung.android.app.memo:id/editor_title");
            title.click();
            title.sendKeys("Test Memo 1");
            WebElement addMemo = driver.findElementById("com.samsung.android.app.memo:id/editor");
            addMemo.click();
            addMemo.sendKeys("Test 1234567890");
            driver.findElementByAccessibilityId("Save").click();

            driver.findElementByAccessibilityId("Create memo").click();

            title = driver.findElementById("com.samsung.android.app.memo:id/editor_title");
            title.click();
            title.sendKeys("Test Memo 2");
            addMemo = driver.findElementById("com.samsung.android.app.memo:id/editor");
            addMemo.click();
            addMemo.sendKeys("Test 1234567890");
            driver.findElementByAccessibilityId("Save").click();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void test2DeleteMemo() {
        try {
            driver.rotate(ScreenOrientation.LANDSCAPE);
            driver.findElementByAccessibilityId("More options").click();
            driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[2]/android.widget.RelativeLayout/android.widget.TextView").click();
            Thread.sleep(3000);
            driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.view.ViewGroup/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout[1]/android.widget.ListView/android.widget.RelativeLayout[1]/android.widget.CheckBox").click();
            driver.findElementByXPath("/hierarchy/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[2]/android.view.ViewGroup/android.widget.FrameLayout/android.widget.RelativeLayout/android.widget.LinearLayout[1]/android.widget.ListView/android.widget.RelativeLayout[2]/android.widget.CheckBox").click();
            driver.findElementByAccessibilityId("Done").click();
            driver.rotate(ScreenOrientation.PORTRAIT);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void test3GetLogs(){
        Set<String> logs = driver.manage().logs().getAvailableLogTypes();
        for(String str : logs){
            System.out.println("Log Type = " + str);
        }
    }


    @AfterClass
    public static void afterTest() {
        driver.quit();
    }
}
