import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TTLExtractor {

    // Variables exactly in the format your query expects
    private static final long NEW_CARD_TTL_MILLIS =
            Duration.ofMinutes(30).toMillis();

    private static final long STORED_CARD_TTL_MILLIS =
            Duration.ofHours(12).toMillis();

    public static void main(String[] args) {

        String source = """
                NEW_CARD_TTL_MILLIS = Duration.ofMinutes(30).toMillis();
                STORED_CARD_TTL_MILLIS = Duration.ofHours(12).toMillis();
                """;

        // Regex patterns based on your jq expressions
        Pattern newCardPattern = Pattern.compile(
                "NEW_CARD_TTL_MILLIS\\s*=\\s*Duration\\.ofMinutes\\((?<val>\\d+)\\)\\.toMillis\\(\\)"
        );

        Pattern storedCardPattern = Pattern.compile(
                "STORED_CARD_TTL_MILLIS\\s*=\\s*Duration\\.ofHours\\((?<val>\\d+)\\)\\.toMillis\\(\\)"
        );

        Matcher newCardMatcher = newCardPattern.matcher(source);
        Matcher storedCardMatcher = storedCardPattern.matcher(source);

        if (newCardMatcher.find()) {
            int minutes = Integer.parseInt(newCardMatcher.group("val"));
            System.out.println("NEW_CARD_TTL minutes = " + minutes);
        }

        if (storedCardMatcher.find()) {
            int hours = Integer.parseInt(storedCardMatcher.group("val"));
            System.out.println("STORED_CARD_TTL hours = " + hours);
        }
    }
}
