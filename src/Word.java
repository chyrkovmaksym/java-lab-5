public class Word {
    // Regular expression separator for splitting into letters (word characters)
    static final String SEPARATOR = "\\w";

    // Array to store the letters of the word
    private final Letter[] letters;

    // Constructor to create a Word object from a LabStringBuilder source
    public Word(LabStringBuilder source) throws Exception {
        // Split the source into tokens using the word character separator
        LabStringBuilder[] tokens = source.split(SEPARATOR);

        // Parse the tokens into Letter objects and store in the letters array
        this.letters = this.parse(tokens);
    }

    // Private method to parse an array of LabStringBuilder tokens into Letter objects
    private Letter[] parse(LabStringBuilder[] tokens) throws Exception {
        // Initialize an array to store the parsed letters
        Letter[] letters = new Letter[tokens.length];

        try {
            // Loop through each token and create a Letter object for it
            for (int i = 0; i < tokens.length; i++) {
                LabStringBuilder token = tokens[i];

                try {
                    // Create a Letter object from the LabStringBuilder token
                    Letter letter = new Letter(token);

                    // Store the created Letter object in the array
                    letters[i] = letter;
                } catch (Exception e) {
                    // Handle the exception by logging or rethrowing with a specific message
                    throw new Exception("Error while creating Letter object: " + e.getMessage());
                }
            }

            // Return the array of parsed letters
            return letters;
        } catch (Exception e) {
            // Rethrow the exception with a more specific message
            throw new Exception("Error while parsing tokens into letters: " + e.getMessage());
        }
    }


    // Override toString method to represent the Word as a string
    public String toString() {
        // Initialize a LabStringBuilder to build the result string
        LabStringBuilder result = new LabStringBuilder("");

        // Concatenate each letter into the result string
        for (Letter letter : this.letters) {
            result.append(letter);
        }

        // Return the final result string
        return result.toString();
    }

    // Static method to check if a given token represents a word (word characters only)
    public static boolean isWord(String token) {
        return token.matches("\\w+");
    }

    // Method to get the array of letters in the word
    public Letter[] getLetters() {
        return letters;
    }
}
