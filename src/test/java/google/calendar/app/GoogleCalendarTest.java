package google.calendar.app;

import google.calendar.app.constants.Constants;
import google.calendar.app.test.BaseSetting;
import google.calendar.app.test.GoogleCalendar;
import google.calendar.app.test.GoogleCalendar.Direction;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class GoogleCalendarTest {

    public BaseSetting baseSetting;
    public GoogleCalendar googleCalendar;
    public Constants constants;

    @BeforeMethod
    @Parameters({ "udid", "platformVersion" })
    public void setup(@Optional String udid, @Optional String platformVersion) {
        baseSetting = new BaseSetting(udid, platformVersion);
    }


    /**
     *  1. 구글 캘린더 앱을 초기 값으로 실행
     *  2. 금월을 계산하여 선택 (ex : 금일이 6월 14일이면 6월을 return)
     *  3. 12월까지 왼쪽으로 스와이프
     *  4. 12월 26일을 선택
     *  5. 우측 하단 + 아이콘 선택 후 일정 선택
     *  6. 일정 제목에 "크리스마스 다음날" 입력 후 저장
     *  7. 저장한 "크리스마스 다음날" 선택
     *  8. 우측 상단 옵션 선택 후 삭제
     * **/
    @Test
    public void test() throws InterruptedException {
        googleCalendar = new GoogleCalendar(baseSetting.threadLocalDriver.getTLDriver(), baseSetting.wait);
        Thread.sleep(2000);
        if (googleCalendar.cFindText(baseSetting.desiredCapabilitiesUtil.getUdid(), "하루를 더 효율적으로 활용해 보세요.")) {
            while (!googleCalendar.cFindText(baseSetting.desiredCapabilitiesUtil.getUdid(), "확인")) {
                googleCalendar.swipeScreen(baseSetting.desiredCapabilitiesUtil.getUdid(), Direction.TOP_LEFT);
                Thread.sleep(500);
            }
        }
        googleCalendar.cSelectElement(baseSetting.desiredCapabilitiesUtil.getUdid(), "확인");
        Thread.sleep(1000);
        googleCalendar.cSelectElement(baseSetting.desiredCapabilitiesUtil.getUdid(), googleCalendar.getCurrentMonth());
        Thread.sleep(500);
        while (!googleCalendar.cFindText(baseSetting.desiredCapabilitiesUtil.getUdid(), "12월")) {
            googleCalendar.swipeScreen(baseSetting.desiredCapabilitiesUtil.getUdid(), Direction.TOP_LEFT);
            Thread.sleep(500);
        }
        if (baseSetting.desiredCapabilitiesUtil.getPlatformVersion() < 11) {
            googleCalendar.cFindXpath(constants.event26dayXpath_10).click();
            Thread.sleep(500);
            googleCalendar.tapScreen(baseSetting.desiredCapabilitiesUtil.getUdid(), Direction.ADD_CALENDAR);
            Thread.sleep(500);
            googleCalendar.tapScreen(baseSetting.desiredCapabilitiesUtil.getUdid(), Direction.ADD_CALENDAR);
            Thread.sleep(500);
            googleCalendar.cSendKeyElement(baseSetting.desiredCapabilitiesUtil.getUdid(), "제목 입력", "크리스마스 다음날");
            Thread.sleep(500);
            googleCalendar.cSelectElement(baseSetting.desiredCapabilitiesUtil.getUdid(), "저장");
            Thread.sleep(2500);
            googleCalendar.cPageSourceTap(baseSetting.desiredCapabilitiesUtil.getUdid(), "크리스마스 다음날");
            Thread.sleep(500);
            googleCalendar.cPageSourceTap(baseSetting.desiredCapabilitiesUtil.getUdid(), "크리스마스 다음날");
            Thread.sleep(500);
            googleCalendar.cFindXpath(constants.eventInOptionXpath).click();
            Thread.sleep(500);
            googleCalendar.cSelectElement(baseSetting.desiredCapabilitiesUtil.getUdid(), "삭제");
            Thread.sleep(500);
            googleCalendar.cSelectElement(baseSetting.desiredCapabilitiesUtil.getUdid(), "삭제");
            Thread.sleep(5000);
        } else {
            googleCalendar.cFindXpath(constants.event26dayXpath).click();
            Thread.sleep(500);
            googleCalendar.tapScreen(baseSetting.desiredCapabilitiesUtil.getUdid(), Direction.ADD_CALENDAR);
            Thread.sleep(500);
            googleCalendar.tapScreen(baseSetting.desiredCapabilitiesUtil.getUdid(), Direction.ADD_CALENDAR);
            Thread.sleep(500);
            googleCalendar.cSendKeyElement(baseSetting.desiredCapabilitiesUtil.getUdid(), "제목 추가", "크리스마스 다음날");
            Thread.sleep(500);
            googleCalendar.cSelectElement(baseSetting.desiredCapabilitiesUtil.getUdid(), "저장");
            Thread.sleep(2500);
            googleCalendar.cPageSourceTap(baseSetting.desiredCapabilitiesUtil.getUdid(), "크리스마스 다음날");
            Thread.sleep(500);
            googleCalendar.cFindXpath(constants.eventInOptionXpath).click();
            Thread.sleep(500);
            googleCalendar.cSelectElement(baseSetting.desiredCapabilitiesUtil.getUdid(), "삭제");
            Thread.sleep(500);
            googleCalendar.cSelectElement(baseSetting.desiredCapabilitiesUtil.getUdid(), "삭제");
            Thread.sleep(5000);
        }
    }

    @AfterMethod
    public void tearDown() {
        baseSetting.threadLocalDriver.getTLDriver().quit();
    }

}
