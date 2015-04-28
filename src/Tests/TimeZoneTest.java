package Tests;
import java.util.Date;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

/**
 * Created by WentaoD on 4/16/15.
 */
public class TimeZoneTest {

    public static void main(String args[]) {
        DateTime t = DateTime.now();
        Date d = t.toDate();

        System.out.println(t);
        System.out.println(t.withZone(DateTimeZone.UTC));
        System.out.println(t.toDateTime(DateTimeZone.UTC));
    }
}
