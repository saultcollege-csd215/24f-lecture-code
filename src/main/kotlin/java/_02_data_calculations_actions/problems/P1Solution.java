package java._02_data_calculations_actions.problems;

import java.util.List;

public class P1Solution {
    public static void main(String[] args) {

        var words = List.of("hello", "world", "java", "programming", "language", "random", "words", "more", "hi", "bye", "computer", "science", "is", "fun");
        var consonants = words.stream().map(word -> numConsonantsIn(word)).toList();

        for ( int i = 0 ; i < words.size() ; i++ ) {
            System.out.println(words.get(i) + " has " + consonants.get(i) + " " + pluralize("consonant", consonants.get(i)));
        }
    }

    public static int numConsonantsIn(String s) {
        var consonants = 0;
        for ( var i = 0; i < s.length(); i++ ) {
            var letter = "" + s.charAt(i);
            if ( ! "aeiou".contains(letter.toLowerCase()) ) {
                consonants++;
            }
        }
        return consonants;
    }

    public static String pluralize(String s, int count) {
        if ( count == 1 ) {
            return s;
        }

        if ( s.endsWith("y") ) {
            return s.substring(0, s.length() - 1) + "ies";
        } else if ( s.endsWith("s") ) {
            return s + "es";
        } else {
            return s + "s";
        }
    }

}
