package google.calendar.app.test;

import google.calendar.app.utils.DesiredCapabilitiesUtil;
import google.calendar.app.utils.ThreadLocalDriver;
import io.appium.java_client.android.AndroidDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseSetting {

    public          AndroidDriver                   driver;
    public          WebDriverWait                   wait;
    public final   ThreadLocalDriver               threadLocalDriver = new ThreadLocalDriver();
    public final   DesiredCapabilitiesUtil         desiredCapabilitiesUtil = new DesiredCapabilitiesUtil();

    public BaseSetting(String udid, String platformVersion) {
        try {
            DesiredCapabilities desiredCapabilities = desiredCapabilitiesUtil.getDesiredCapabilities(udid, platformVersion);
            threadLocalDriver.setTLDriver(new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities));
            wait = new WebDriverWait(threadLocalDriver.getTLDriver(), Duration.ofSeconds(30));
//            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
//            wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

}

