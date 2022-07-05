<div align=center>

![header](https://capsule-render.vercel.app/api?type=waving&color=auto&height=300&section=header&text=Foundation%20Task&fontSize=100)

---
---

## selenium
### NaverNewsCrawling.java
    - selenium 을 이용하여 크롬 브라우저에서 네이버 뉴스 20개를 크롤링
    - 뉴스는 최신순으로 크롤링하며, 뉴스의 제목과 링크를 CSV 파일로 저장
    - CSV 파일명을 크롤링한 날짜와 시간으로 저장

<img src="https://img.shields.io/badge/NaverNewsCrawling.gif-000000?style=flat&logo=selenium&logoColor=43B02A"/>
-
<img width="100%" src="https://user-images.githubusercontent.com/73505910/175875374-eaf0fd62-74dc-48b3-bf0a-0631894d9521.gif"/>

---
---

## appium
### main.java.appium.utils.DesiredCapabilitiesUtil.java
    - appium 서버와 연결 시 원하는 기능을 셋팅
    - appium inspector 에서 동일하게 확일할 수 있음

<img src="https://img.shields.io/badge/AppiumInspector.png-000000?style=flat"/>
-
<img width="100%" src="https://user-images.githubusercontent.com/73505910/175256327-27944b1e-cea2-4e95-bd50-a6078ecf53f5.png"/>

### main.java.appium.test.BaseSetting.java
    - DesiredCapabilities.java 에 셋팅된 기능으로 appium 서버와 Android device 를 연결

### main.java.appium.test.GoogleNews.java
    - 모바일 앱 동작 시 필요한 기능을 Custom 함

<div align=left>

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
</div>

### test.java.appium.GoogleNewsTest.java

<div align=left>

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

</div>

<img src="https://img.shields.io/badge/GoogleNewsTest_1.gif-000000?style=flat&logo=google&logoColor=4285F4"/>
-
<img width="100%" src="https://user-images.githubusercontent.com/73505910/175881033-7fcc19f9-ea7b-497e-995b-941f1bb0e62a.gif"/>

<img src="https://img.shields.io/badge/GoogleNewsTest_2.gif-000000?style=flat&logo=google&logoColor=4285F4"/>
-
<img width="100%" src="https://user-images.githubusercontent.com/73505910/175882179-f2344cfa-c694-4d33-bab7-f1ba584f50a6.gif"/>

<a href="https://drive.google.com/file/d/1gQIadWbZqOjlWl_lNU1ray8-nVuuZo2P/view?usp=sharing"><img src="https://img.shields.io/badge/GoogleDrive-GoogleNewsTest_Full_Video_URL(Click)-blue?style=flat-square&logo=GoogleDrive&logoColor=4285F4"/></a>

---
---

## google
구글 캘린더 API 다루기
### https://console.cloud.google.com/

<div align=left>

    Setting
    1. 라이브러리
      Google Calendar API 추가
    2. API 및 서비스 > 사용자 인증 정보
      사용자 인증 정보 만들기 > OAuth 2.0 클라이언트 ID 생성
      데스크톱 앱
    3. OAuth 클라이언트 json 다운로드
      src > main > resources > credentials.json 으로 저장 (이름 변경 가능)

</div>

### main.java.google.calendar.api.CalendarQuickstart.java
    - 제헌절, 광복절, 추석, 빼빼로데이를 insertEvent() 를 통해 개인 일정에 추가
    - 이미 생성되어있는지 checkEvent() 를 통해 checking 후 추가
    - 현시점으로 부터 최대 20개까지 개인 일정을 출력
    - deleteEvent() 를 통해 개인 일정 삭제
    * https://developers.google.com/calendar/api/quickstart/java 참조

---

구글 캘린더 앱 다루기
### main.java.google.calendar.app.utils.DesiredCapabilitiesUtils.java
    - appium 서버와 연결 시 원하는 기능을 셋팅

### main.java.google.calendar.app.test.BaseSetting.java
    - DesiredCapabilities.java 에 셋팅된 기능으로 appium 서버와 Android device 를 연결

### main.java.google.calendar.app.test.GoogleCalendar.java
    - 구글캘린더 앱 동작 시 필요한 기능을 Custom 함

<div align=left>

#### cFindText(String data)
    - parameter 로 입력받은 data 가 현재 화면에 존재하는지 찾음
    - 찾을 경우 true 를 return, 못 찾을 경우 false 를 return
    - 못 찾을 경우 재 검색 (총 3회)
#### cFindXpath(String xpath)
    - parameter 로 입력받은 xpath 가 현재 화면에 존재하는지 찾음
    - 찾을 경우 element 를 return, 못 찾을 경우 null 을 return
    - 못 찾을 경우 재 검색 (총 3회)
#### cFindElement(String data)
    - parameter 로 입력받은 data 가 현재 화면에 존재하는지 찾음
    - 찾을 경우 element 를 return, 못 찾을 경우 null 을 return
#### cSelectElement(String data)
    - parameter 로 입력받은 data 를 cFindElement method 에 인자로 넘겨,
    - return 값이 element 일 경우 해당 element 를 click(선택)함
#### cSendKeyElement(String data, String sendKey)
    - parameter 로 입력받은 data 를 cFindElement method 에 인자로 넘겨,
    - return 값이 element 일 경우 해당 element 에 sendKey 를 input(입력)함
#### cUiAutomatorTap(String data)
    - parameter 로 입력받은 data 를 현재 화면에 존재하는지 찾음
    - 찾을 경우 해당 data 의 location point 를 tap(click)함
#### checkJsonException(JSONArray jsonArray, int index, String key, String data)
    - JSONArray 에서 key 가 없는 경우 JSONException 이 발생
    - JSONException 을 발생할 경우에 대한 Exception 처리를 위한 method
#### tapScreen(Direction dir)
    - Direction 에 정의한 enum 을 parameter 로 받을 경우 해당 위치를 tap(click)함
    - ADD_CALENDAR
#### swipeScreen(Direction dir)
    - UP, DOWN, LEFT, RIGHT, TOP_LEFT, TOP_RIGHT, BOTTOM_LEFT, BOTTIM_RIGHT 로 스크롤

</div>

### main.java.google.calendar.app.manager.PageSourceParser.java
    - driver.getPageSource() 에서 return 받은 XML 데이터를 JSON data 로 parsing 함

<div align=left>

#### sourceParser(String pageSource)
    - parameter 로 입력받은 pageSource XML data 를 JSONObject 로 변환
    - 변환된 JSON data 를 childObjectCase() 에 전달 후 JSONArray 로 변환
#### childObjectCase(JSONObject target)
    - parameter 로 입력받은 JSONObject data 내부의 JSONObject 와 JSONArray 로 구분하여 반환
#### childArrayCase(JSONArray target)
    - parameter 로 입력받은 JSONArray data 내부의 JSONObject 와 JSONArray 로 구분하여 반환
#### check(Iterator keys, String key1, String key2)
    - parameter 로 입력받은 Iterator data 에서 key1 과 key2 data 가 포함되어 있는지 확인
    - 포함되어 있으면 해당 data 를 return, 미 포함 시 빈 String return

</div>

<img src="https://img.shields.io/badge/GoogleCalendarTest_1.gif-000000?style=flat&logo=google&logoColor=4285F4"/>
-
<img width="100%" src="https://user-images.githubusercontent.com/73505910/177082166-26b4ed17-8703-48a4-a281-87ebe5c77664.gif"/>

<img src="https://img.shields.io/badge/GoogleCalendarTest_2.gif-000000?style=flat&logo=google&logoColor=4285F4"/>
-
<img width="100%" src="https://user-images.githubusercontent.com/73505910/177082425-ba202f4c-5226-4986-8f8f-af2e0a28df44.gif"/>

<img src="https://img.shields.io/badge/GoogleCalendarTest_3.gif-000000?style=flat&logo=google&logoColor=4285F4"/>
-
<img width="100%" src="https://user-images.githubusercontent.com/73505910/177082732-ddc29abf-7753-4cfd-a57a-b15dce8e1e94.gif"/>

<img src="https://img.shields.io/badge/GoogleCalendarTest_4.gif-000000?style=flat&logo=google&logoColor=4285F4"/>
-
<img width="100%" src="https://user-images.githubusercontent.com/73505910/177082546-ab31714d-7049-416f-a572-f1446da75587.gif"/>

<a href="https://drive.google.com/file/d/1TmDu9wOO9s1TGMiTGH_iW_cfk1_LcqJR/view?usp=sharing"><img src="https://img.shields.io/badge/GoogleDrive-GoogleCalendarTest_Full_Video_URL_(Click)-blue?style=flat-square&logo=GoogleDrive&logoColor=4285F4"/></a>

</div>