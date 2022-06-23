package appium.test;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleNewsSearch {

    public AndroidDriver driver;
    public WebDriverWait wait;
    public ArrayList<String> newsList = new ArrayList<>();

    public GoogleNewsSearch(AndroidDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public WebElement cFindElement(String data) {
        try {
            List<WebElement> elementXpath = driver.findElements(By.xpath("//*[@class]"));
            for (int i = 0; i < elementXpath.size(); i++) {
                WebElement element = elementXpath.get(i);
                if (element.getText().equals(data)) {
                    return element;
                }
            }
        } catch (StaleElementReferenceException e) {
            return null;
        }
        return null;
    }

    public void cSelectElement(String data) {
        WebElement webElement = cFindElement(data);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
        } catch (NullPointerException e) {
            cSelectElement(data);
        }
    }

    public void cSendKeyElement(String data, String sendKey) {
        WebElement webElement = cFindElement(data);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.sendKeys(sendKey);
        } catch (NullPointerException e) {
            cSendKeyElement(data, sendKey);
        }
    }

    public void newsCrawling(String keyWord) {
        List<WebElement> findGroupNewsListElement = driver.findElements(By.xpath("//*[@class]"));
        for (int i = 0; i < findGroupNewsListElement.size(); i++) {
            WebElement element = findGroupNewsListElement.get(i);
            if (element.getText().contains(keyWord) && element.getText().length() > 20 && element.getText().length() < 60) {
                newsList.add(element.getText());
            }
        }
    }

    public void getNewsList() {
        for (int i = 0; i < newsList.size(); i++) {
            for (int j = 0; j < newsList.size(); j++) {
                if (i == j) {
                } else if (newsList.get(i).equals(newsList.get(j))) {
                    newsList.remove(j);
                }
            }
            System.out.println("제목 : " + newsList.get(i));
        }
    }

    public void swipeScreen(Direction dir) {
        // Animation default time:
        //  - Android: 300 ms
        //  - iOS: 200 ms
        // final value depends on your app and could be greater
        final int ANIMATION_TIME = 200; // ms

        final int PRESS_TIME = 1500; // ms

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
            default:
                throw new IllegalArgumentException("swipeScreen(): dir: '" + dir + "' NOT supported");
        }

        // execute swipe using TouchAction
        try {
            new TouchAction(driver)
                .press(pointOptionStart)
                // a bit more reliable when we add small wait
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                .moveTo(pointOptionEnd)
                .release().perform();
        } catch (Exception e) {
            System.err.println("swipeScreen(): TouchAction FAILED\n" + e.getMessage());
            return;
        }

        // always allow swipe action to complete
        try {
            Thread.sleep(ANIMATION_TIME);
        } catch (InterruptedException e) {
            // ignore
        }
    }

    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT;
    }

}
