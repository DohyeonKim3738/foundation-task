package google.calendar.app.utils;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DesiredCapabilitiesUtil {

    private DesiredCapabilities desiredCapabilities;
    public DesiredCapabilities getDesiredCapabilities(String udid, String platformVersion) {
        desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appium:udid", udid);
        desiredCapabilities.setCapability("appium:platformVersion", platformVersion);
        desiredCapabilities.setCapability("appium:deviceName", "Android Automation Test");
        desiredCapabilities.setCapability("appium:appPackage", "com.google.android.calendar");
        desiredCapabilities.setCapability("appium:appActivity", "com.android.calendar.AllInOneActivity");
        desiredCapabilities.setCapability("appium:intentAction", "android.intent.action.Main");
        desiredCapabilities.setCapability("appium:noReset", false);
        desiredCapabilities.setCapability("appium:fullReset", false);
        desiredCapabilities.setCapability("appium:enforceAppInstall", false);
        desiredCapabilities.setCapability("appium:appWaitActivity", "*");

        /**
         *
         * Support for Legacy Capabilities is deprecated
         *
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "12");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Automation Test");
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.google.android.calendar");
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.android.calendar.AllInOneActivity");
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.INTENT_ACTION, "android.intent.action.Main");
        desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET, "false");
        desiredCapabilities.setCapability(MobileCapabilityType.FULL_RESET, "false");
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.ENFORCE_APP_INSTALL, "false");
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.APP_WAIT_ACTIVITY, "*");
        **/
        return desiredCapabilities;
    }

    public String getUdid() {
        return String.valueOf(desiredCapabilities.getCapability("appium:udid"));
    }

    public int getPlatformVersion() {
        return Integer.parseInt(String.valueOf(desiredCapabilities.getCapability("appium:platformVersion")));
    }

}

