package selenium;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class NaverNewsCrawling {

    public static final String webDriverId = "webdriver.chrome.driver";
    public static final String webDriverPath = "chromedriver";
    public static final String url = "https://www.naver.com";
    public static WebDriver webDriver;
    public static Map<Integer, String[]> news = new HashMap<>();
    public static int count = 1;

    public static void main(String[] args) {

        NaverNewsCrawling naverNewsCrawling = new NaverNewsCrawling();
        naverNewsCrawling.latestNewsCrawling();

    }

    /** 최신 뉴스 20개를 크롤링 **/
    public void latestNewsCrawling() {
        try {
            System.setProperty(webDriverId, webDriverPath);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ChromeOptions chromeOptions = new ChromeOptions();
        /** 크롬을 띄우지 않도록 headless 옵션 정의 **/
        chromeOptions.addArguments("headless");

        webDriver = new ChromeDriver(chromeOptions);

        webDriver.get(url);

        /** 검색창 선택 후 크롤링할 키워드 입력 **/
        WebElement findQueryElement = webDriver.findElement(By.name("query"));
        findQueryElement.sendKeys("누리호");

        /** 검색 버튼 선택 **/
        WebElement findSearchBtnElement = webDriver.findElement(By.id("search_btn"));
        findSearchBtnElement.click();

        /** 상단 메뉴 중 '뉴스'를 찾아 선택 **/
        List<WebElement> findMenuElement = webDriver.findElements(By.className("menu"));
        for (int i = 0; i < findMenuElement.size(); i++) {
            if (findMenuElement.get(i).getText().equals("뉴스")) {
                findMenuElement.get(i).click();
                break;
            }
        }

        /** 상당 아이템 중 '최신순'을 찾아 선택 **/
        List<WebElement> findListOptionFilterElement = webDriver.findElements(By.className("item"));
        for (int i = 0; i < findListOptionFilterElement.size(); i++) {
            if (findListOptionFilterElement.get(i).getText().equals("최신순")) {
                findListOptionFilterElement.get(i).click();
                break;
            }
        }

        /** 현재 페이지에서 노출되어있는 뉴스를 크롤링 (10개) **/
        newsCrawling();

        /** 하단 페이지 넘버 중 '2'를 찾아 페이지 2로 이동 **/
        List<WebElement> findPageElement = webDriver.findElements(By.className("btn"));
        for (int i = 0; i < findPageElement.size(); i++) {
            if (findPageElement.get(i).getText().equals("2")) {
                findPageElement.get(i).click();
                break;
            }
        }

        /** 현재 페이지에서 노출되어있는 뉴스를 크롤링 (10개) **/
        newsCrawling();

        /** 크롤링한 20개의 뉴스 데이터를 CSV 파일로 저장 **/
        createCSV();

        /** 크롬 종료 **/
        webDriver.close();
    }

    /** 뉴스의 제목과 링크 데이터를 저장 **/
    public void newsCrawling() {
        List<WebElement> findGroupNewsListElement = webDriver.findElements(By.cssSelector(".news_tit"));
        for (int i = 0; i < findGroupNewsListElement.size(); i++) {
            String[] newsTitleLink = new String[2];
            newsTitleLink[0] = findGroupNewsListElement.get(i).getText().replace(",",".");
            newsTitleLink[1] = findGroupNewsListElement.get(i).getAttribute("href");
            news.put(count, newsTitleLink);
            count++;
        }
    }

    /** 크롤링한 데이터를 CSV 파일로 저장 **/
    public void createCSV() {

        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(getCurrentDateTime()+"_news.csv", false));

            bufferedWriter.write("제목,URL");
            bufferedWriter.newLine();

            for (int i = 0; i < news.size(); i++) {
                bufferedWriter.write(news.get(i+1)[0]+","+news.get(i+1)[1]);
                bufferedWriter.newLine();
            }

            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /** 한국의 현재 날짜와 시간을 계산 **/
    public String getCurrentDateTime() {
        Date today = new Date();
        Locale currentLocale = new Locale("KOREAN", "KOREA");
        String pattern = "yyyy_MM_dd_HH_mm";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern,
            currentLocale);
        return formatter.format(today);
    }

}
