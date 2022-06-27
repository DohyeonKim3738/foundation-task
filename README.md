<div align=center>

![header](https://capsule-render.vercel.app/api?type=waving&color=auto&height=300&section=header&text=Foundation%20Task&fontSize=100)

---

## selenium
### NaverNewsCrawling
    - selenium 을 이용하여 크롬 브라우저에서 네이버 뉴스 20개를 크롤링
    - 뉴스는 최신순으로 크롤링하며, 뉴스의 제목과 링크를 CSV 파일로 저장
    - CSV 파일명을 크롤링한 날짜와 시간으로 저장

<img src="https://img.shields.io/badge/NaverNewsCrawling.gif-000000?style=flat&logo=selenium&logoColor=43B02A"/>
<img width="100%" src="https://user-images.githubusercontent.com/73505910/175875374-eaf0fd62-74dc-48b3-bf0a-0631894d9521.gif"/>

---

## appium
### main.java.appium.utils.DesiredCapabilitiesUtil
    - appium 서버와 연결 시 원하는 기능을 셋팅
    - appium inspector 에서 동일하게 확일할 수 있음

<img src="https://img.shields.io/badge/AppiumInspector.png-000000?style=flat"/>
<img width="100%" src="https://user-images.githubusercontent.com/73505910/175256327-27944b1e-cea2-4e95-bd50-a6078ecf53f5.png"/>

### main.java.appium.test.BaseSetting
    - DesiredCapabilities.java 에 셋팅된 기능으로 appium 서버와 Android device 를 연결

### main.java.appium.test.GoogleNewsSearch
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

### test.java.appium.GoogleNewsTest

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

<img src="https://img.shields.io/badge/GoogleNewsSearch_1.gif-000000?style=flat&logo=google&logoColor=4285F4"/>
<img width="100%" src="https://user-images.githubusercontent.com/73505910/175881033-7fcc19f9-ea7b-497e-995b-941f1bb0e62a.gif"/>
<img src="https://img.shields.io/badge/GoogleNewsSearch_2.gif-000000?style=flat&logo=google&logoColor=4285F4"/>
<img width="100%" src="https://user-images.githubusercontent.com/73505910/175882179-f2344cfa-c694-4d33-bab7-f1ba584f50a6.gif"/>

<a href="https://drive.google.com/file/d/1gQIadWbZqOjlWl_lNU1ray8-nVuuZo2P/view?usp=sharing"><img src="https://img.shields.io/badge/GoogleDrive-FullVideoURL(Click)-blue?style=flat-square&logo=GoogleDrive&logoColor=4285F4"/></a>

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

### main.java.google.calendar.CalendarQuickstart
    - https://developers.google.com/calendar/api/quickstart/java 참조

[//]: # (CalendarQuickstart.gif)
[//]: # (<img width="100%" src=""/>)

</div>