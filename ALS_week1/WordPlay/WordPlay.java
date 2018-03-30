import edu.duke.*;

public class WordPlay {
    public Boolean isVowel (char ch) {
        /**
        *Input:
        *   char - ch: character to determine if it is vowel
        *Output:
        *   Boolean: true if ch is a vowel (one of 'a', 'e', 'i', 'o', or 'u' or the uppercase versions) 
        *   and false otherwise.
        */

        // convert ch to lower cases
        char conch = Character.toLowerCase(ch);
        // store vowels in a String vowels
        String vowels = "aeiou";
        // check if the converted ch is in vowels
        // if it is, return ture
        if (vowels.indexOf(conch) != -1) return true;
        // if it is not, return false
        return false;
    }

    public String replaceVowels (String phrase, char ch) {
        /**
        *Input:
        *   String - phrase: string to alter
        *   char - ch: charater to replace in the String phrase
        *Output:
        *   String: the string phrase with all the vowels (uppercase or lowercase) replaced by ch
        */

        // make a StringBuilder with phrase
        StringBuilder sbPhrase = new StringBuilder(phrase);

        // for every char in phrase, check if the char is vowel using isVowel
        for (int i = 0; i < phrase.length(); i++) {
            if (isVowel(sbPhrase.charAt(i)) == true) {
                // if it is, replace the char with ch
                sbPhrase.setCharAt(i, ch);
            } 
            // if not, leave it be 
        }
        return sbPhrase.toString();
    }

    public String emphasize (String phrase, char ch) {
        /**
        *Input:
        *   String - phrase: string to alter
        *   char - ch: charater to replace in the String phrase
        *Output:
        *   String: the string phrase but with the Char ch (upper- or lowercase) replaced by
        *       ‘*’ if it is in an odd number location in the string (even index)
        *       ‘+’ if it is in an even number location in the string (odd index)
        */

        // make a StringBuilder with phrase to alter it
        StringBuilder sbPhrase = new StringBuilder(phrase);
        char chl = Character.toLowerCase(ch);
        // for every char in phrase, check if it's equals ch
        for (int i = 0; i < sbPhrase.length(); i++) {
            // if it is, check if it is in the even idex or odd index
            char currchar = Character.toLowerCase(sbPhrase.charAt(i));
            if (currchar == chl) {
                // if it's in even index, replace ch with *
                if (i % 2 == 0) {
                    sbPhrase.setCharAt(i, '*');
                }
                else {
                    // if it's in odd index, replace ch with +
                    sbPhrase.setCharAt(i, '+');
                } 
            }   
        }
        return  sbPhrase.toString();
    }

    public void testIsVowel () {
        char test1 = 'a';
        char test2 = 'O';
        char test3 = 'y';
        char test4 = 'X';

        Boolean result1 = isVowel(test1);
        if (result1 != true) System.out.println("test failed 1.");
        Boolean result2 = isVowel(test2);
        if (result2 != true) System.out.println("test failed 2.");  
        Boolean result3 = isVowel(test3);
        if (result3 != false) System.out.println("test failed 3.");  
        Boolean result4 = isVowel(test4);
        if (result4 != false) System.out.println("test failed 4.");  
        System.out.println("test finished.");
    } 
}

