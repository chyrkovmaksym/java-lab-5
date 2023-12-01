public class Sentence {
    // Regular expression separator for splitting into words and punctuation
    static final String SEPARATOR = "\\b\\w+\\b|[\\p{Punct}]";

    // Array to store the tokens (words and punctuation) of the sentence
    private final Object[] tokens;

    // Constructor to create a Sentence object from a LabStringBuilder sentence
    public Sentence(LabStringBuilder sentence) throws Exception {
        // Split the sentence into items using the specified separator regex
        LabStringBuilder[] items = sentence.split(Sentence.SEPARATOR);

        // Parse the items into Word and Punctuation objects and store in the tokens array
        this.tokens = this.parse(items);
    }

    // Private method to parse an array of LabStringBuilder items into Word and Punctuation objects
    private Object[] parse(LabStringBuilder[] items) throws Exception {
        // Initialize an array to store the parsed tokens
        Object[] tokens = new Object[items.length];

        try {
            // Loop through each item and create a Word or Punctuation object for it
            for (int i = 0; i < items.length; i++) {
                LabStringBuilder item = items[i];
                boolean isWord = Word.isWord(item.toString());

                try {
                    // Check if the item is a Word and create the corresponding object
                    if (isWord) {
                        tokens[i] = new Word(item);
                    } else {
                        // Create a Punctuation object for non-word items (punctuation)
                        tokens[i] = new Punctuation(item);
                    }
                } catch (Exception e) {
                    // Handle the exception by logging or rethrowing with a specific message
                    throw new Exception("Error while creating token object: " + e.getMessage());
                }
            }

            // Return the array of parsed tokens
            return tokens;
        } catch (Exception e) {
            // Rethrow the exception with a more specific message
            throw new Exception("Error while parsing items into tokens: " + e.getMessage());
        }
    }


    // Override toString method to represent the Sentence object as a string
    public String toString() {
        // Initialize a LabStringBuilder to build the result string
        LabStringBuilder result = new LabStringBuilder("");

        // Concatenate each token into the result string
        for (Object token : this.tokens) {
            // Add a space before each word to separate words in the sentence
            if (Word.isWord(token.toString())) {
                result.append(" ");
            }

            // Concatenate the token into the result string
            result.append(token);
        }

        // Trim any leading spaces and return the final result string
        return result.toString().trim();
    }

    // Method to get the array of tokens (words and punctuation) in the sentence
    public Object[] getTokens() {
        return tokens;
    }

    // Method to cut a substring from the sentence based on start and end indices
    public String cutSubstring(int start, int end) throws Exception {
        // Get the string representation of the sentence
        String value = this.toString();

        // Check if start and end indices are within valid bounds
        if (start < 0 || start >= value.length() || end < 0 || end >= value.length() || start > end) {
            throw new Exception("Invalid start or end indices for substring extraction");
        }

        try {
            // Extract the first part of the sentence before the start index
            String firstPart = value.substring(0, start);

            // Extract the end part of the sentence after the end index
            // Adjust the end index to exclude the character at the end index
            String endPart = value.substring(end + 1);

            // Concatenate the first and end parts to form the substring
            return firstPart + endPart;
        } catch (Exception e) {
            // Handle any exception that might occur during substring extraction
            throw new Exception("Error while extracting substring: " + e.getMessage());
        }
    }

}
