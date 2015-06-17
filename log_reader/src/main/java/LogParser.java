import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ovidiucb
 */
public class LogParser {
    public static LogData parseEntry(String entry) {
        LogData logData = null;

        Pattern entryPattern = Pattern.compile(getAccessLogRegex(),Pattern.CASE_INSENSITIVE | Pattern.DOTALL);
        Matcher entryMatcher = entryPattern.matcher(entry);;

        if(entryMatcher.matches()) {
            logData = new LogData();

            logData.setIpAddress(entryMatcher.group(1));
            logData.setStatusCode(entryMatcher.group(6));
            logData.setTraffic(entryMatcher.group(7));
        }

        return logData;
    }

    private static String getAccessLogRegex()
    {
        String regex1 = "^([\\d.]+)"; // Client IP
        String regex2 = " (\\S+)"; // -
        String regex3 = " (\\S+)"; // -
        String regex4 = " \\[([\\w:/]+\\s[+\\-]\\d{4})\\]"; // Date
        String regex5 = " \"(.+?)\""; // request method and url
        String regex6 = " (\\d{3})"; // HTTP code
        String regex7 = " (\\d+|(.+?))"; // Number of bytes
        String regex8 = " \"([^\"]+|(.+?))\""; // Referer
        String regex9 = " \"([^\"]+|(.+?))\""; // Agent

        return regex1 + regex2 + regex3 + regex4 + regex5 + regex6 + regex7 + regex8 + regex9;
    }
}
