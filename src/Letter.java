import java.util.Objects;

public class Letter {
    // The character value of the Letter
    private final char value;

    // Constructor to create a Letter object from a LabStringBuilder source
    public Letter(LabStringBuilder source) throws Exception {
        // Get the first character from the LabStringBuilder as the value of the Letter
        char value = source.toString().charAt(0);

        // Check if the provided character is a letter; throw an exception if not
        if (!Character.isLetter(value)) {
            throw new Exception("Invalid letter provided. Please provide a letter.");
        }

        // Assign the validated character as the value of the Letter
        this.value = value;
    }

    // Override toString method to represent the Letter as a string
    public String toString() {
        return Character.toString(this.value);
    }

    // Method to get the character value of the Letter
    public char getValue() {
        return value;
    }

    // Method to check equality with another Letter based on their values
    public boolean equals(Letter otherLetter) {
        // Use Objects.equals to compare the values of two Letters
        return Objects.equals(otherLetter.getValue(), this.value);
    }

    // Method to get the lowercase value of the Letter
    public char getLowerValue() {
        return Character.toLowerCase(this.value);
    }
}
