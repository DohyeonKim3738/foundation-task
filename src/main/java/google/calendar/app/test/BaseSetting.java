package google.calendar.app.test;

import google.calendar.app.utils.DesiredCapabilitiesUtil;
import io.appium.java_client.android.AndroidDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseSetting {

    public AndroidDriver driver;
    public WebDriverWait wait;

    public BaseSetting() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilitiesUtil().getDesiredCapabilities();

        try {
            driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
            wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

}

