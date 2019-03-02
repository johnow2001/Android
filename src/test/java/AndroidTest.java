import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidBatteryInfo;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.appmanagement.ApplicationState;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.html5.Location;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;


public class AndroidTest {
    private AndroidDriver driver;

    @Before
    public void setUp() {
        try {
            DesiredCapabilities caps = new DesiredCapabilities();
            //caps.setBrowserName("Chrome");
            caps.setCapability("platformName", "Android");
            caps.setCapability("platformVersion", "6.0");
            caps.setCapability("deviceName", "21019afe");
            caps.setCapability("appPackage", "com.sec.android.app.popupcalculator");
            caps.setCapability("appActivity", "com.sec.android.app.popupcalculator.Calculator");
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), caps);
            //driver.toggleWifi();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void testWeb() {
        driver.get("https://www.gov.uk");
        System.out.println(driver.getPageSource());

        ScreenOrientation orientation = driver.getOrientation();
        System.out.println("orientation = ".concat(orientation.value()));

        System.out.println("Current Activity = ".concat(driver.currentActivity()));

        try {
            Location loc = driver.location();
            System.out.println("Loc = ".concat(loc.toString()));
        }
        catch (Exception e){
            System.out.println("No Location\n" + e.getMessage());
        }


        Activity activity = new Activity("com.sec.android.app.launcher", "com.android.launcher2.Launcher");
        driver.startActivity(activity);
        System.out.println("Current Activity = ".concat(driver.currentActivity()));
        System.out.println("Current Package = ".concat(driver.getCurrentPackage()));

        ApplicationState state = driver.queryAppState("com.sec.android.app.launcher");
        System.out.println("Package State = ".concat(state.toString()));

        /*
        Map<String, String> appString = driver.getAppStringMap("en");
        Set<Map.Entry<String, String>> set = appString.entrySet();
        for(Map.Entry<String, String> m : set) {
            System.out.println(m.getKey().concat(" : ").concat(m.getValue()));
        }
        */

        Map<String, Object> settingsString = driver.getSettings();
        Set<Map.Entry<String, Object>> set = settingsString.entrySet();
        for(Map.Entry<String, Object> m : set) {
            System.out.println(m.getKey().concat(" : ") + (m.getValue()));
        }



    }


    @Test
    public void testApplication(){
        System.out.println("Current Activity = ".concat(driver.currentActivity()));
        System.out.println("Current Package = ".concat(driver.getCurrentPackage()));

        WebElement eight = driver.findElement(By.id("8"));
        eight.click();

        //driver.findElement(By.id("com.sec.android.app.popupcalculator:id/bt_add")).click();
        MobileElement add = (MobileElement)driver.findElementByAccessibilityId("plus");
        add.click();

        driver.findElement(By.id("com.sec.android.app.popupcalculator:id/bt_05")).click();
        driver.findElement(By.id("com.sec.android.app.popupcalculator:id/bt_equal")).click();

        WebElement result = driver.findElement(By.id("com.sec.android.app.popupcalculator:id/txtCalc"));
        assertEquals("8+5\n=13. Editing.", result.getText());

    }

    @Test
    public void testAppData(){
        List<String> types = driver.getSupportedPerformanceDataTypes();
        for(String type : types){
            System.out.println("\n" + type + "\n");
        }

        List<List<Object>> battery = driver.getPerformanceData("com.sec.android.app.popupcalculator", "batteryinfo", 5);
        Object o[];
        o = battery.toArray();
        for(int i=0; i< o.length; i++){
            System.out.println(o[i]);
        }

        for(Object object : battery){
            System.out.println(object);
        }

        for(int i =0; i< battery.size(); i++){
            for(int j=0; i < battery.size(); i++){
                System.out.println(battery.get(i).get(j));
            }
        }

    }

    @Test
    public void test2App(){
        //Test code here
    }

    @Test
    public void test3App(){
        //Test code here
    }

    @Test
    public void test4App(){
        //Test code here
    }

    @Test
    public void test5App(){
        //Test code here
    }


    @After
    public void afterTest() {
        //driver.close();
        driver.quit();
        //driver.toggleWifi();
    }
}
