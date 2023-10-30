package io.samples.appium.ios;

import com.applitools.eyes.appium.Target;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static io.samples.Wait.waitFor;

class WebiOSHelloWorldEyesTest extends IOSHooks {
    private WebiOSHelloWorldEyesTest() {
        IS_NATIVE = false;
    }

    @Test
    void runIOSWebTest() {
        driver.get("https://applitools.com/helloworld");
        waitFor(2);
        eyes.checkWindow("App launched");
        for (int stepNumber = 0; stepNumber < 2; stepNumber++) {
            By linkText = By.linkText("?diff1");
            driver.findElement(linkText).click();
            eyes.check("step-" + stepNumber, Target.region(linkText).layout());
            waitFor(1);
        }
        driver.findElement(By.tagName("button")).click();
        eyes.check("Click Me", Target.window().layout());
        Assertions.assertTrue(true, "Test completed. Assertions will be done by Applitools");
    }
}
