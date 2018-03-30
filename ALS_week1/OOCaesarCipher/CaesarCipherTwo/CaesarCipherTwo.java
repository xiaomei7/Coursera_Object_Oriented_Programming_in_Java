import edu.duke.*;

public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int mainKey1;
    private int mainKey2;

    public CaesarCipherTwo (int key1, int key2) {
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1) + alphabet.substring(0, key1);
        shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);
        mainKey1 = key1;
        mainKey2 = key2;
    }

    public String encrypt (String input) {
        /**
        *Input:
        *   String - input: string to encrypt
        *Output:
        *   String: encrypted message, with
        *       key1 is used to encrypt every other character with 
        *           the Caesar Cipher algorithm, starting with the first character (index 0)
        *       key2 is used to encrypt every other character, 
        *           starting with the second character (index 1)
        */

        // make a StringBuilder with the input
        StringBuilder sbInput = new StringBuilder(input);
        
        // for every char in the input
        for (int i = 0; i < sbInput.length(); i++) {
            char currChar = sbInput.charAt(i);
            int idx = alphabet.indexOf(Character.toUpperCase(currChar));
            // check i
            // if the i is even, use key1
            if (i % 2 == 0) {
                if (Character.isLowerCase(currChar) && idx != -1) {
                    //Get the idxth character of shiftedAlphabet (newChar)
                    char newChar = shiftedAlphabet1.toLowerCase().charAt(idx);
                    //Replace the ith character of encrypted with newChar
                    sbInput.setCharAt(i, newChar);
                } 
                else if (Character.isUpperCase(currChar) && idx != -1) {
                    //Get the idxth character of shiftedAlphabet (newChar)
                    char newChar = shiftedAlphabet1.charAt(idx);
                    //Replace the ith character of encrypted with newChar
                    sbInput.setCharAt(i, newChar);
                } 
            }
            // if the i is odd, use key2
            else {
                if (Character.isLowerCase(currChar) && idx != -1) {
                    //Get the idxth character of shiftedAlphabet (newChar)
                    char newChar = shiftedAlphabet2.toLowerCase().charAt(idx);
                    //Replace the ith character of encrypted with newChar
                    sbInput.setCharAt(i, newChar);
                } 
                else if (Character.isUpperCase(currChar) && idx != -1) {
                    //Get the idxth character of shiftedAlphabet (newChar)
                    char newChar = shiftedAlphabet2.charAt(idx);
                    //Replace the ith character of encrypted with newChar
                    sbInput.setCharAt(i, newChar);
                } 
            }
        }
        return sbInput.toString();
    }

    public String decrypt (String input) {
        /**
        *Input:
        *   String - input: string that encrypted with 2 keys
        *Output:
        *   String: decrypted string
        */

        CaesarCipherTwo cct = new CaesarCipherTwo(26 - mainKey1, 26 - mainKey2);
        return cct.encrypt(input);
    }

}

