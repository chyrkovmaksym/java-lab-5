import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        try {
            // Input text for processing
            String inputText = "This is a sample text. It contains some sentences! The text needs processing?";

            // Create a Text object to process the input text
            Text text = new Text(inputText);

            // Create start and end letters for substring extraction
            LabStringBuilder a = new LabStringBuilder("t");
            LabStringBuilder b = new LabStringBuilder("t");
            Letter startLetter = new Letter(a);
            Letter endLetter = new Letter(b);

            // Extract and print the substring based on start and end letters
            System.out.println(text.extractSubstring(startLetter, endLetter));
        } catch (Exception e) {
            // Handle any exceptions that may occur during processing
            throw new Exception(e);
        }
    }
}