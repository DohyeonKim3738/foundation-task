package google.calendar.app.test;

import google.calendar.app.manager.PageSourceParser;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleCalendar {

    public AndroidDriver driver;
    public WebDriverWait wait;
    public PageSourceParser pageSourceParser;
    public int checkCount = 0;

    public GoogleCalendar(AndroidDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        pageSourceParser = new PageSourceParser();
    }

    public boolean cFindText(String udid, String data) {
        try {
            List<WebElement> elementXpath = driver.findElements(By.xpath("//*[@class]"));
            for (int i = 0; i < elementXpath.size(); i++) {
                WebElement element = elementXpath.get(i);
                if (element.getText().equals(data)) {
                    System.out.printf("[" + udid + "] found data {%s} \n", data);
                    return true;
                }
            }
        } catch (StaleElementReferenceException e) {
            return cFindText(udid, data);
        }
        if (checkCount == 3) {
            checkCount = 0;
            return false;
        } else {
            checkCount++;
            cFindText(udid, data);
        }
        return false;
    }

    public WebElement cFindXpath(String xpath) {
        for (int i = 0; i < 3; i++) {
            WebElement element = driver.findElement(By.xpath(xpath));
            return element;
        }
        return null;
    }

    public WebElement cFindElement(String udid, String data) {
        try {
            List<WebElement> elementXpath = driver.findElements(By.xpath("//*[@class]"));
            for (int i = 0; i < elementXpath.size(); i++) {
                WebElement element = elementXpath.get(i);
                if (element.getText().equals(data)) {
                    System.out.printf("[" + udid + "] found data {%s} \n", data);
                    return element;
                }
            }
        } catch (StaleElementReferenceException e) {
            return null;
        }
        return null;
    }

    public void cSelectElement(String udid, String data) {
        WebElement webElement = cFindElement(udid, data);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(webElement));
            System.out.printf("[" + udid + "] click data {%s} \n", data);
            webElement.click();
        } catch (NullPointerException e) {
            cSelectElement(udid, data);
        }
    }

    public void cSendKeyElement(String udid, String data, String sendKey) {
        WebElement webElement = cFindElement(udid, data);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(webElement));
            System.out.printf("[" + udid + "] sendKey data {%s, %s} \n", data, sendKey);
            webElement.sendKeys(sendKey);
        } catch (NullPointerException e) {
            cSendKeyElement(udid, data, sendKey);
        }
    }

    public void cPageSourceTap(String udid, String data) {
        JSONArray jsonArray = pageSourceParser.sourceParser(driver.getPageSource());
        PointOption pointOption;
        for (int i = jsonArray.length() -1; i >= 0; i--) {
            if (checkJsonException(jsonArray, i, "text", data) || checkJsonException(jsonArray, i, "content-desc", data))  {
                String[] xyValue = jsonArray.getJSONObject(i).getString("bounds").split("]");
                for (int j = 0; j < xyValue.length; j++) {
                    xyValue[j] = xyValue[j].substring(1);
                }
                String[] firstPoint = xyValue[0].split(",");
                String[] secondPoint = xyValue[1].split(",");
                int pointFirst = (Integer.parseInt(firstPoint[0]) + Integer.parseInt(secondPoint[0])) / 2;
                int pointSecond = (Integer.parseInt(firstPoint[1]) + Integer.parseInt(secondPoint[1])) / 2;
                pointOption = PointOption.point(pointFirst, pointSecond);
                try {
                    System.out.printf("[" + udid + "] click data {%s} \n", data);
                    new TouchAction(driver)
                        .tap(pointOption)
                        .perform();
                } catch (Exception e) {
                    System.err.println("[" + udid + "] cUiAutomatorTap(): TouchAction FAILED\n" + e.getMessage());
                    return;
                }
            }
        }
    }

    public boolean checkJsonException(JSONArray jsonArray, int index, String key, String data) {
        try {
            if (jsonArray.getJSONObject(index).get(key).equals(data)) {
                return true;
            }
            if (jsonArray.getJSONObject(index).get(key).toString().contains(data)) {
                return true;
            }
        } catch (JSONException e) {
            return false;
        }
        return false;
    }

    public void tapScreen(String udid, Direction dir) {

        final int ANIMATION_TIME = 300; // ms

        Dimension dims = driver.manage().window().getSize();
        PointOption pointOption;

        switch (dir) {
            case ADD_CALENDAR: // tap of add calendar widget
                pointOption = PointOption.point(dims.width - 120,dims.height - 150);
                break;
            default:
                throw new IllegalArgumentException("[" + udid + "] tapScreen(): dir: '" + dir + "' NOT supported");
        }

        try {
            System.out.printf("[" + udid + "] tap {%s} \n", dir);
            new TouchAction(driver)
                .tap(pointOption)
                .perform();
        } catch (Exception e) {
            System.err.println("[" + udid + "] tapScreen(): TouchAction FAILED\n" + e.getMessage());
            return;
        }

        // always allow swipe action to complete
        try {
            Thread.sleep(ANIMATION_TIME);
        } catch (InterruptedException e) {
            // ignore
        }
    }

    public void swipeScreen(String udid, Direction dir) {
        // Animation default time:
        //  - Android: 300 ms
        //  - iOS: 200 ms
        // final value depends on your app and could be greater
        final int ANIMATION_TIME = 300; // ms

        final int PRESS_TIME = 300; // ms

        int edgeBorder = 10; // better avoid edges
        PointOption pointOptionStart, pointOptionEnd;

        // init screen variables
        Dimension dims = driver.manage().window().getSize();

        // init start point = center of screen
        pointOptionStart = PointOption.point(dims.width / 2, dims.height / 2);

        switch (dir) {
            case DOWN: // center of footer
                pointOptionEnd = PointOption.point(dims.width / 2, dims.height - edgeBorder);
                break;
            case UP: // center of header
                pointOptionEnd = PointOption.point(dims.width / 2, edgeBorder);
                break;
            case LEFT: // center of left side
                pointOptionEnd = PointOption.point(edgeBorder, dims.height / 2);
                break;
            case RIGHT: // center of right side
                pointOptionEnd = PointOption.point(dims.width - edgeBorder, dims.height / 2);
                break;
            case TOP_LEFT: // top center of left side
                pointOptionStart = PointOption.point((dims.width / 2 + dims.width / 4), dims.height / 4);
                pointOptionEnd = PointOption.point(edgeBorder, dims.height / 4);
                break;
            case TOP_RIGHT: // top center of right side
                pointOptionStart = PointOption.point(dims.width / 4, dims.height / 4);
                pointOptionEnd = PointOption.point(dims.width - edgeBorder, dims.height / 4);
                break;
            case BOTTOM_LEFT: // bottom center of left side
                pointOptionStart = PointOption.point((dims.width / 2 + dims.width / 4), (dims.height / 2 + dims.height / 4));
                pointOptionEnd = PointOption.point(edgeBorder, (dims.height / 2 + dims.height / 4));
                break;
            case BOTTOM_RIGHT: // bottom center of right side
                pointOptionStart = PointOption.point(dims.width / 4, (dims.height / 2 + dims.height / 4));
                pointOptionEnd = PointOption.point(dims.width - edgeBorder, (dims.height / 2 + dims.height / 4));
                break;
            default:
                throw new IllegalArgumentException("[" + udid + "] swipeScreen(): dir: '" + dir + "' NOT supported");
        }

        // execute swipe using TouchAction
        try {
            System.out.printf("[" + udid + "] swipe {%s} \n", dir);
            new TouchAction(driver)
                .press(pointOptionStart)
                // a bit more reliable when we add small wait
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                .moveTo(pointOptionEnd)
                .release().perform();
        } catch (Exception e) {
            System.err.println("[" + udid + "] swipeScreen(): TouchAction FAILED\n" + e.getMessage());
            return;
        }

        // always allow swipe action to complete
        try {
            Thread.sleep(ANIMATION_TIME);
        } catch (InterruptedException e) {
            // ignore
        }
    }

    public String getCurrentMonth() {
        Date today = new Date();
        Locale currentLocale = new Locale("KOREAN", "KOREA");
        String pattern = "MM";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern,
            currentLocale);

        switch (formatter.format(today)) {
            case "01":
                return "1월";
            case "02":
                return "2월";
            case "03":
                return "3월";
            case "04":
                return "4월";
            case "05":
                return "5월";
            case "06":
                return "6월";
            case "07":
                return "7월";
            case "08":
                return "8월";
            case "09":
                return "9월";
            case "10":
                return "10월";
            case "11":
                return "11월";
            case "12":
                return "12월";
        }
        return null;
    }

    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT,
        TOP_LEFT,
        TOP_RIGHT,
        BOTTOM_LEFT,
        BOTTOM_RIGHT,
        ADD_CALENDAR;
    }

}
