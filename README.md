# Foundation Task

## selenium
### NaverNewsCrawling
    - selenium 을 이용하여 크롬 브라우저에서 네이버 뉴스 20개를 크롤링
    - 뉴스는 최신순으로 크롤링하며, 뉴스의 제목과 링크를 CSV 파일로 저장
    - CSV 파일명을 크롤링한 날짜와 시간으로 저장

## appium
### main.java.appium.utils.DesiredCapabilitiesUtil
    - appium 서버와 연결 시 원하는 기능을 셋팅
    - appium inspector 에서 동일하게 확일할 수 있음
![AppiumInspector](https://user-images.githubusercontent.com/73505910/175256327-27944b1e-cea2-4e95-bd50-a6078ecf53f5.png)

### main.java.appium.test.BaseSetting
    - DesiredCapabilities.java 에 셋팅된 기능으로 appium 서버와 Android device 를 연결

### main.java.appium.test.GoogleNewsSearch
    - 모바일 앱 동작 시 필요한 기능을 Custom 함
#### cFindElement
- parameter 로 입력받은 data 가 현재 화면에 존재하는지 찾음
- 찾을 경우 element 를 return, 못 찾을 경우 null 을 return
#### cSelectElement
- parameter 로 입력받은 data 를 cFindElement method 에 인자로 넘겨,
- return 값이 element 일 경우 해당 element 를 click(선택)함
#### cSendKeyElement
- parameter 로 입력받은 data 를 cFindElement method 에 인자로 넘겨,
- return 값이 element 일 경우 해당 element 에 sendKey 를 input(입력)함
#### newsCrawling
- 현재 화면에서 검색한 keyWord 를 포함한 뉴스 제목을 모두 저장
#### getNewsList
- 저장된 뉴스 제목 데이터 값에서 중복 데이터를 제거하며 제목을 출력함
#### swipeScreen
- UP, DOWN, LEFT, RIGHT 로 스크롤
### test.java.appium.GoogleNewsTest
    - appium 을 이용하여 크롬 앱에서 뉴스 제목 10개를 크롤링
    - TestNG 를 사용하여, @BeforeMethod @Test @AfterMethod 로 구성하여 시나리오를 작성
    - 실행 순서
    - @BeforeMethod  ->  @Test  ->  @AfterMethod
- @BeforeMethod
  - BaseSetting 을 초기화하여 appium 서버와 Android device 를 setup 함
- @Test
  - BaseSetting 에서 driver 와 wait 값을 넘겨받아 GoogleNewsSearch 를 초기화함
  - keyWord 를 입력하여 검색하고, 뉴스 탭으로 이동
  - keyWord 를 포함하고 있는 뉴스 제목을 크롤링하여 저장 및 출력
- @AfterMethod
  - driver 종료