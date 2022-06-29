package appium;

import appium.test.BaseSetting;
import appium.test.GoogleNews;
import appium.test.GoogleNews.Direction;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GoogleNewsTest {

    public BaseSetting baseSetting;
    public GoogleNews googleNews;

    @BeforeMethod
    public void setup() {
        baseSetting = new BaseSetting();
    }

    @Test
    public void test() {
        googleNews = new GoogleNews(baseSetting.driver, baseSetting.wait);
        googleNews.cSelectElement("동의하고 계속");
        googleNews.cSelectElement("취소");
        googleNews.cSendKeyElement("웹 주소 검색 또는 입력", "누리호");
        baseSetting.driver.pressKey(new KeyEvent(AndroidKey.ENTER));
        googleNews.cSelectElement("뉴스");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        googleNews.newsCrawling("누리호");
        googleNews.swipeScreen(Direction.UP);
        googleNews.newsCrawling("누리호");
        googleNews.swipeScreen(Direction.UP);
        googleNews.newsCrawling("누리호");
        googleNews.getNewsList();
    }

    @AfterMethod
    public void tearDown() {
        baseSetting.driver.quit();
    }

}
