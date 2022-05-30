package HelloWorld;

import Utilities.Wait;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

class Appium_Native_Calc_Test {

    private AppiumDriver<WebElement> driver;
    private final String APPIUM_SERVER_URL = "http://localhost:4723/wd/hub";

    @BeforeEach
    void setUp(TestInfo testInfo) throws
                                  MalformedURLException {
        System.out.println("Test - " + testInfo.getDisplayName());
        System.out.println(String.format("Create AppiumDriver for - %s",
                                         APPIUM_SERVER_URL));

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,
                                   "UiAutomator2");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,
                                   "Android");
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,
                                   "Android");
        capabilities.setCapability("autoGrantPermissions",
                                   true);
        capabilities.setCapability("fullReset",
                                   true);
        capabilities.setCapability("app",
                                   new File("./sampleApps/AndroidCalculator.apk").getAbsolutePath());
        capabilities.setCapability("appPackage",
                                   "com.android2.calculator3");
        capabilities.setCapability("appActivity",
                                   "com.android2.calculator3.Calculator");
        driver = new AppiumDriver<>(new URL(APPIUM_SERVER_URL),
                                    capabilities);
        System.out.println(String.format("Created AppiumDriver for - %s",
                                         APPIUM_SERVER_URL));

        handleUpgradePopup();
    }

    private void handleUpgradePopup() {
        Wait.waitFor(1);
        MobileElement upgradeAppNotificationElement = (MobileElement) driver.findElementById("android:id/button1");
        if (null != upgradeAppNotificationElement) {
            upgradeAppNotificationElement.click();
            Wait.waitFor(1);
        }
        MobileElement gotItElement = (MobileElement) driver.findElementById("com.android2.calculator3:id/cling_dismiss");
        if (null != gotItElement) {
            gotItElement.click();
            Wait.waitFor(1);
        }
    }

    @Test
    public void calcTest() {
        int p1 = 3;
        int p2 = 5;
        driver.findElement(By.id("digit" + p1))
              .click();
        driver.findElement(By.id("plus"))
              .click();
        driver.findElement(By.id("digit" + p2))
              .click();
        driver.findElement(By.id("equal"))
              .click();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
