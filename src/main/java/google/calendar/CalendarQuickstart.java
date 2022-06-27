package google.calendar;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.Events;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

/* class to demonstarte use of Calendar events list API */
public class CalendarQuickstart {
    /** Application name. */
    private static final String APPLICATION_NAME = "Google Calendar API Java Quickstart";
    /** Global instance of the JSON factory. */
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    /** Directory to store authorization tokens for this application. */
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR);
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";

    /**
     * Creates an authorized Credential object.
     * @param HTTP_TRANSPORT The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */
    private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
        // Load client secrets.
        InputStream in = CalendarQuickstart.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
            HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
            .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
            .setAccessType("offline")
            .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        Credential credential = new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
        //returns an authorized Credential object.
        return credential;
    }

    public static void main(String... args) throws IOException, GeneralSecurityException {
        // Build a new authorized API client service.
        final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        Calendar service = new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT))
            .setApplicationName(APPLICATION_NAME)
            .build();

        // Insert Events
        /** 제헌철 **/
        if (checkEvent(service, "제헌절", "2022-07-17T09:00:00.000+09:00")) {
            insertEvent(service,
                "제헌절",
                "제헌절 입니다.",
                "2022-07-17T09:00:00.000+09:00",
                "2022-07-17T18:00:00.000+09:00");
        }

        /** 광복절 **/
        if (checkEvent(service, "광복절", "2022-08-15T09:00:00.000+09:00")) {
            insertEvent(service,
                "광복절",
                "광복절 입니다.",
                "2022-08-15T09:00:00.000+09:00",
                "2022-08-15T18:00:00.000+09:00");
        }

        /** 추석 **/
        if (checkEvent(service, "추석", "2022-09-09T09:00:00.000+09:00")) {
            insertEvent(service,
                "추석",
                "추석 연휴 입니다.",
                "2022-09-09T09:00:00.000+09:00",
                "2022-09-11T18:00:00.000+09:00");
        }

        /** 빼빼로 데이 **/
        if (checkEvent(service, "빼빼로데이", "2022-11-11T11:11:11.000+09:00")) {
            insertEvent(service,
                "빼빼로데이",
                "빼빼로데이~!!!",
                "2022-11-11T11:11:11.000+09:00",
                "2022-11-11T23:11:11.000+09:00");
        }

        // List the next 10 events from the primary calendar.
        DateTime now = new DateTime(System.currentTimeMillis());
        Events events = service.events().list("primary")
            .setMaxResults(20)
            .setTimeMin(now)
            .setOrderBy("startTime")
            .setSingleEvents(true)
            .execute();
        List<Event> items = events.getItems();
        if (items.isEmpty()) {
            System.out.println("No upcoming events found.");
        } else {
            System.out.println("Upcoming events");
            for (Event event : items) {
                DateTime start = event.getStart().getDateTime();
                DateTime end = event.getEnd().getDateTime();
                if (start == null) {
                    start = event.getStart().getDate();
                }
                if (end == null) {
                    end = event.getEnd().getDate();
                }
                System.out.printf("%s (%s)\n", event.getSummary() , start + " ~ " + end);
                System.out.println("Description : " + event.getDescription());
            }
        }

    }

    private static boolean checkEvent(Calendar service, String summary, String startDate) throws IOException {
        DateTime now = new DateTime(System.currentTimeMillis());
        Events events = service.events().list("primary")
            .setTimeMin(now)
            .setOrderBy("startTime")
            .setSingleEvents(true)
            .execute();
        List<Event> items = events.getItems();
        if (items.isEmpty()) {
            System.out.println("No upcoming events found.");
            return false;
        } else {
            for (Event event : items) {
                DateTime start = event.getStart().getDateTime();
                if (event.getSummary().equals(summary) && start.toString().contains(startDate)) {
                    System.out.printf("Already created event. : %s\n", event.getSummary() + ", " + start);
                    return false;
                }
            }
        }
        System.out.println("Check complete.");
        return true;
    }

    private static void insertEvent(Calendar service, String summary, String description, String startDate, String endDate) throws IOException {
        Event event = new Event()
            .setSummary(summary)
            .setDescription(description);

        DateTime startDateTime = new DateTime(startDate);
        EventDateTime start = new EventDateTime()
            .setDateTime(startDateTime);
        event.setStart(start);

        DateTime endDateTime = new DateTime(endDate);
        EventDateTime end = new EventDateTime()
            .setDateTime(endDateTime);
        event.setEnd(end);

        String calendarId = "primary";

        event = service.events().insert(calendarId, event).execute();
        System.out.printf("Event created: %s\n", event.getHtmlLink());
    }

    private static void deleteEvent(Calendar service, String event) throws IOException {
        String calendarId = "primary";
        System.out.printf("Event deleted: %s\n", service.events().delete(calendarId, event).execute());
    }
}