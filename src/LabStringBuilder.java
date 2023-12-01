import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LabStringBuilder {
    // StringBuilder to store the content of LabStringBuilder
    private final StringBuilder content;

    // Constructor to create a LabStringBuilder object from a source string
    public LabStringBuilder(String source) {
        // Initialize the content StringBuilder with the provided source
        this.content = new StringBuilder(source);
    }

    // Method to split the LabStringBuilder content into an array of LabStringBuilder objects
    public LabStringBuilder[] split(String separator) {
        // ArrayList to store the result LabStringBuilder objects
        ArrayList<LabStringBuilder> result = new ArrayList<>();

        // Create a regex Pattern based on the provided separator
        Pattern pattern = Pattern.compile(separator);

        // Create a Matcher for the LabStringBuilder content using the regex Pattern
        Matcher matcher = pattern.matcher(this.content.toString());

        // Iterate through matches and add each substring as a LabStringBuilder to the result
        while (matcher.find()) {
            // Extract the matched substring
            String token = this.content.substring(matcher.start(), matcher.end());

            // Add a new LabStringBuilder object for the substring to the result
            result.add(new LabStringBuilder(token));
        }

        // Convert the result ArrayList to an array and return
        return result.toArray(new LabStringBuilder[0]);
    }

    // Method to append an object to the LabStringBuilder content
    public void append(Object obj) {
        // Append the provided object to the content StringBuilder
        this.content.append(obj);
    }

    // Method to replace all occurrences of a regex pattern with a replacement string
    public void replaceAll(String regex, String replacement) {
        // Create a regex Pattern based on the provided regex
        Pattern pattern = Pattern.compile(regex);

        // Create a Matcher for the LabStringBuilder content using the regex Pattern
        Matcher matcher = pattern.matcher(this.content.toString());

        // Offset to adjust for changes in length during replacement
        int offset = 0;

        // Iterate through matches and replace each occurrence with the replacement string
        while (matcher.find()) {
            // Calculate start and end indices of the matched substring
            int start = matcher.start() + offset;
            int end = matcher.end() + offset;

            // Replace the matched substring with the replacement string
            this.content.replace(start, end, replacement);

            // Adjust offset for changes in length
            offset += replacement.length() - (end - start);
        }
    }

    // Override toString method to represent the LabStringBuilder object as a string
    public String toString() {
        return this.content.toString();
    }
}
