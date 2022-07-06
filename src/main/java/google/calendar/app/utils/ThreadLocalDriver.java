package google.calendar.app.utils;

import io.appium.java_client.android.AndroidDriver;

public class ThreadLocalDriver {
    private static final ThreadLocal<AndroidDriver> tlDriver = new ThreadLocal<>();

    public static synchronized void setTLDriver(AndroidDriver driver) {
        tlDriver.set(driver);
    }

    public static synchronized AndroidDriver getTLDriver() {
        return tlDriver.get();
    }
}
