package test.com.anyun.cloud.demo.api.node;

import com.anyun.common.lang.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.TemporalQueries;
import java.util.Date;
import java.util.TimeZone;

/**
 * @auth TwitchGG <twitchgg@yahoo.com>
 * @since 1.0.0 on 2017/6/6
 */
public class TestGson {
    public static void main(String[] args) throws ParseException {
        String format = "2017-06-06T19:32:24.61585706+08:00";
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSSSXXX");
        LocalDateTime time = LocalDateTime.parse(format,dateFormat);
        System.out.println(time.getNano());
    }
}
