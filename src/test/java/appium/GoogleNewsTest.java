package appium;

import appium.test.BaseSetting;
import appium.test.GoogleNewsSearch;
import appium.constants.Constants;
import appium.test.GoogleNewsSearch.Direction;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GoogleNewsTest {

    public BaseSetting baseSetting;
    public GoogleNewsSearch googleNewsSearch;

    @BeforeMethod
    public void setup() {
        baseSetting = new BaseSetting();
    }

    @Test
    public void test() {
        googleNewsSearch = new GoogleNewsSearch(baseSetting.driver, baseSetting.wait);
        googleNewsSearch.cSelectElement("동의하고 계속");
        googleNewsSearch.cSelectElement("취소");
        googleNewsSearch.cSendKeyElement("웹 주소 검색 또는 입력", "누리호");
        baseSetting.driver.pressKey(new KeyEvent(AndroidKey.ENTER));
        googleNewsSearch.cSelectElement("뉴스");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        googleNewsSearch.newsCrawling("누리호");
        googleNewsSearch.swipeScreen(Direction.UP);
        googleNewsSearch.newsCrawling("누리호");
        googleNewsSearch.swipeScreen(Direction.UP);
        googleNewsSearch.newsCrawling("누리호");
        googleNewsSearch.getNewsList();
    }

    @AfterMethod
    public void tearDown() {
        baseSetting.driver.quit();
    }

}
