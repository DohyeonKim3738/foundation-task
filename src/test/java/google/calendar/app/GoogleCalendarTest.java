package google.calendar.app;

import google.calendar.app.test.BaseSetting;
import google.calendar.app.test.GoogleCalendar;
import google.calendar.app.test.GoogleCalendar.Direction;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GoogleCalendarTest {

    public BaseSetting baseSetting;
    public GoogleCalendar googleCalendar;

    @BeforeMethod
    public void setup() {
        baseSetting = new BaseSetting();
    }

    @Test
    public void test() throws InterruptedException {
        googleCalendar = new GoogleCalendar(baseSetting.driver, baseSetting.wait);
        Thread.sleep(2000);
        if (googleCalendar.cFindText("하루를 더 효율적으로 활용해 보세요.")) {
            googleCalendar.swipeScreen(Direction.LEFT);
        }
        googleCalendar.cSelectElement("확인");
        Thread.sleep(1000);
        googleCalendar.cSelectElement("7월");
        Thread.sleep(500);
        while (!googleCalendar.cFindText("12월")) {
            googleCalendar.swipeScreen(Direction.TOP_LEFT);
            Thread.sleep(500);
        }
        googleCalendar.cFindXpath("//android.view.View[@content-desc=\"월요일 26 12월 2022, 12월4\"]").click();
        Thread.sleep(500);
        googleCalendar.tapScreen(Direction.ADD_CALENDAR);
        Thread.sleep(500);
        googleCalendar.tapScreen(Direction.ADD_CALENDAR);
        Thread.sleep(500);
        googleCalendar.cSendKeyElement("제목 추가", "크리스마스 다음날");
        Thread.sleep(500);
        googleCalendar.cSelectElement("저장");
        Thread.sleep(2500);
        googleCalendar.cUiAutomatorTap("크리스마스 다음날");
        Thread.sleep(500);
        googleCalendar.cFindXpath("//android.widget.ImageView[@content-desc=\"옵션 더보기\"]").click();
        Thread.sleep(500);
        googleCalendar.cSelectElement("삭제");
        Thread.sleep(500);
        googleCalendar.cSelectElement("삭제");
        Thread.sleep(5000);
    }

    @AfterMethod
    public void tearDown() {
        baseSetting.driver.quit();
    }

}
