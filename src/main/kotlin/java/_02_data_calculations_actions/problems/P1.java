package java._02_data_calculations_actions.problems;

public class P1 {

    // Try to break this into smaller calculations (pure functions)
    public static void main(String[] args) {

        var words = new String[] { "hello", "world", "java", "programming", "language", "random", "words", "more", "hi", "bye", "computer", "science", "is", "fun" };

        for ( var word : words ) {
            // count the number of consonants in each word
            var consonants = 0;
            for ( var i = 0; i < word.length(); i++ ) {
                var letter = "" + word.charAt(i);
                if ( ! "aeiou".contains(letter) ) {
                    consonants++;
                }
            }

            // print the word and the number of consonants
            System.out.println(word + " has " + consonants + " consonant" + (consonants == 1 ? "" : "s"));

        }
    }

}
