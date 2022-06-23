package appium.utils;

import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DesiredCapabilitiesUtil {

    public DesiredCapabilities getDesiredCapabilities() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "12");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Automation Test");
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.android.chrome");
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.google.android.apps.chrome.Main");
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.INTENT_ACTION, "android.intent.action.Main");
        desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET, "false");
        desiredCapabilities.setCapability(MobileCapabilityType.FULL_RESET, "false");
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.ENFORCE_APP_INSTALL, "false");
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY, "*");
        return desiredCapabilities;
    }

}
