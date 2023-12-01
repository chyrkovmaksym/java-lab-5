import java.util.Arrays;

public class Text {
    // Regular expression for sentence separation based on common punctuation
    static final String SEPARATOR = "([^.!?\\\\s][^.!?]*[.!?])";

    // Array to store parsed sentences
    private final Sentence[] sentences;

    // Constructor for creating a Text object from a source string
    public Text(String source) throws Exception {
        try {
            // Create a LabStringBuilder to manipulate the source text
            LabStringBuilder text = new LabStringBuilder(source);

            // Replace multiple spaces and tabs with a single space
            text.replaceAll("[\\s\\t]+", " ");

            // Split the text into tokens using the specified separator regex
            LabStringBuilder[] tokens = text.split(Text.SEPARATOR);

            // Parse the tokens into Sentence objects and store in the sentences array
            this.sentences = this.parse(tokens);
        } catch (Exception e) {
            // Rethrow the exception if needed
            throw new Exception("Error while processing text: " + e.getMessage());
        }
    }

    // Private method to parse an array of LabStringBuilder tokens into Sentence objects
    private Sentence[] parse(LabStringBuilder[] tokens) throws Exception {
        try {
            // Initialize an array to store the parsed sentences
            Sentence[] sentences = new Sentence[tokens.length];

            // Loop through each token and create a Sentence object for it
            for (int i = 0; i < tokens.length; i++) {
                LabStringBuilder token = tokens[i];
                Sentence sentence = new Sentence(token);

                // Store the created Sentence object in the array
                sentences[i] = sentence;
            }

            // Return the array of parsed sentences
            return sentences;
        } catch (Exception e) {
            // Rethrow the exception if needed
            throw new Exception("Error while parsing tokens: " + e.getMessage());
        }
    }

    // Override toString method to represent the Text object as a string
    public String toString() {
        // Initialize a LabStringBuilder to build the result string
        LabStringBuilder result = new LabStringBuilder("");

        // Concatenate each sentence followed by a space into the result string
        for (Sentence sentence : this.sentences) {
            result.append(sentence + " ");
        }

        // Trim any trailing spaces and return the final result string
        return result.toString().trim();
    }

    // Method to extract a substring from the text based on start and end letters
    public String extractSubstring(Letter startLetter, Letter endLetter) throws Exception {
        // Initialize a StringBuilder to build the resulting substring
        StringBuilder res = new StringBuilder();

        try {
            // Iterate through each sentence in the Text
            for (Sentence sentence : this.sentences) {
                // Initialize variables to track substrings and characters
                int startIndex = 0, endIndex = 0, allChars = 0;
                boolean foundFirst = false;

                // Get the tokens (words and punctuation) of the current sentence
                Object[] tokens = sentence.getTokens();

                // Iterate through each token in the sentence
                for (Object token : tokens) {
                    // Check if the token is a Word
                    if (Word.isWord(token.toString())) {
                        // Get the array of Letters from the Word
                        Letter[] letters = ((Word) token).getLetters();

                        // Iterate through each Letter in the Word
                        for (int j = 0; j < letters.length; j++) {
                            // Check if the current Letter is the startLetter and not found yet
                            if (letters[j].getLowerValue() == startLetter.getLowerValue() && !foundFirst) {
                                // Update the startIndex and mark the startLetter as found
                                startIndex = allChars + j;
                                foundFirst = true;
                                continue;
                            }

                            // Check if the current Letter is the endLetter
                            if (letters[j].getLowerValue() == endLetter.getLowerValue()) {
                                // Update the endIndex
                                endIndex = allChars + j;
                            }
                        }
                        // Increment the count of words in the sentence
                        allChars += 1;
                    }
                    // Increment the count of characters based on the token length
                    allChars += token.toString().length();
                }

                // Cut the substring from the current sentence based on start and end indices
                String currentSentence = sentence.cutSubstring(startIndex, endIndex);

                // Append the substring from the current sentence to the result
                res.append(currentSentence);
            }

            // Convert the StringBuilder to a String and return the final result
            return res.toString();
        } catch (Exception e) {
            // Rethrow the exception with a more specific message
            throw new Exception("Error while extracting substring: " + e.getMessage());
        }
    }

}
