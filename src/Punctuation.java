public class Punctuation {
    // The character representing the punctuation
    private final char punctuation;

    // Constructor to create a Punctuation object from a LabStringBuilder sequence
    public Punctuation(LabStringBuilder sequence) {
        // Get the first character from the LabStringBuilder as the punctuation character
        this.punctuation = sequence.toString().charAt(0);
    }

    // Override toString method to represent the Punctuation object as a string
    public String toString() {
        return Character.toString(this.punctuation);
    }

    // Method to get the character representing the punctuation
    public char getPunctuation() {
        return punctuation;
    }
}
