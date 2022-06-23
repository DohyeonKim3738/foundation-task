package appium;

import appium.test.BaseSetting;
import appium.test.NaverNewsSearch;
import appium.constants.Constants;
import appium.test.NaverNewsSearch.Direction;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class NaverNewsTest {

    public BaseSetting baseSetting;
    public NaverNewsSearch naverNewsSearch;
    public Constants constants;

    @BeforeMethod
    public void setup() {
        baseSetting = new BaseSetting();
    }

    @Test
    public void test() {
        naverNewsSearch = new NaverNewsSearch(baseSetting.driver, baseSetting.wait);
        naverNewsSearch.cSelectElement("동의하고 계속");
        naverNewsSearch.cSelectElement("취소");
        naverNewsSearch.cSendKeyElement("웹 주소 검색 또는 입력", "누리호");
        baseSetting.driver.pressKey(new KeyEvent(AndroidKey.ENTER));
        naverNewsSearch.cSelectElement("뉴스");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        naverNewsSearch.newsCrawling("누리호");
        naverNewsSearch.swipeScreen(Direction.UP);
        naverNewsSearch.newsCrawling("누리호");
        naverNewsSearch.swipeScreen(Direction.UP);
        naverNewsSearch.newsCrawling("누리호");
        naverNewsSearch.getNewsList();
    }

    @AfterMethod
    public void tearDown() {
        baseSetting.driver.quit();
    }

}
