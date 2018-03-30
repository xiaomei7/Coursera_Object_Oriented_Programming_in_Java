import edu.duke.*;

public class CaesarCipher {
    public String encrypt(String input, int key) {
        /**
        *Input:
        *   String - input: the string to encrypt 
        *   int - key： the encrypt key
        *Output:
        *   String: the encrpted message
        */

        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        //Write down the alphabet
        String alphabetUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetLower = "abcdefghijklmnopqrstuvwxyz";
        //Compute the shifted alphabet
        String shiftedAlphabetUpper = alphabetUpper.substring(key) +
        alphabetUpper.substring(0, key);
        String shiftedAlphabetLower = alphabetLower.substring(key) + 
        alphabetLower.substring(0, key);
        //Count from 0 to < length of encrypted, (call it i)
        for(int i = 0; i < encrypted.length(); i++) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = encrypted.charAt(i);
            //Find the index of currChar in the alphabet (call it idx)
            int idx = alphabetLower.indexOf(Character.toLowerCase(currChar));
            // check if the currChar is upper or lower case
            //If currChar is in the alphabet
            if (Character.isLowerCase(currChar) && idx != -1) {
                //Get the idxth character of shiftedAlphabet (newChar)
                char newChar = shiftedAlphabetLower.charAt(idx);
                //Replace the ith character of encrypted with newChar
                encrypted.setCharAt(i, newChar);
            } 

            else if (Character.isUpperCase(currChar) && idx != -1) {
                //Get the idxth character of shiftedAlphabet (newChar)
                char newChar = shiftedAlphabetUpper.charAt(idx);
                //Replace the ith character of encrypted with newChar
                encrypted.setCharAt(i, newChar);
            } 
            
            //Otherwise: do nothing
        }
        //Your answer is the String inside of encrypted
        return encrypted.toString();
    }

    public String encryptTwoKeys (String input, int key1, int key2) {
        /**
        *Input:
        *   String - input: string to encrypt 
        *   int - key1
        *   int - key2
        *Output:
        *   String: encrypted message, with
        *       key1 is used to encrypt every other character with 
        *           the Caesar Cipher algorithm, starting with the first character (index 0)
        *       key2 is used to encrypt every other character, 
        *           starting with the second character (index 1)
        */

        // make a StringBuilder with the input
        StringBuilder sbInput = new StringBuilder(input);
        // create alphabet for both upper and lower cases 
        String alphabetUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphabetLower = "abcdefghijklmnopqrstuvwxyz";
        // create shifted alphabet for both upper and lower cases with key1 and key2
        String shiftedAlphabetUpper1 = alphabetUpper.substring(key1) +
        alphabetUpper.substring(0, key1);
        String shiftedAlphabetLower1 = alphabetLower.substring(key1) + 
        alphabetLower.substring(0, key1);

        String shiftedAlphabetUpper2 = alphabetUpper.substring(key2) +
        alphabetUpper.substring(0, key2);
        String shiftedAlphabetLower2 = alphabetLower.substring(key2) + 
        alphabetLower.substring(0, key2);
        // for every char in the input
        for (int i = 0; i < sbInput.length(); i++) {
            char currChar = sbInput.charAt(i);
            int idx = alphabetLower.indexOf(Character.toLowerCase(currChar));
            // check i
            // if the i is even, use key1
            if (i % 2 == 0) {
                if (Character.isLowerCase(currChar) && idx != -1) {
                    //Get the idxth character of shiftedAlphabet (newChar)
                    char newChar = shiftedAlphabetLower1.charAt(idx);
                    //Replace the ith character of encrypted with newChar
                    sbInput.setCharAt(i, newChar);
                } 
                else if (Character.isUpperCase(currChar) && idx != -1) {
                    //Get the idxth character of shiftedAlphabet (newChar)
                    char newChar = shiftedAlphabetUpper1.charAt(idx);
                    //Replace the ith character of encrypted with newChar
                    sbInput.setCharAt(i, newChar);
                } 
            }
            // if the i is odd, use key2
            else {
                if (Character.isLowerCase(currChar) && idx != -1) {
                    //Get the idxth character of shiftedAlphabet (newChar)
                    char newChar = shiftedAlphabetLower2.charAt(idx);
                    //Replace the ith character of encrypted with newChar
                    sbInput.setCharAt(i, newChar);
                } 
                else if (Character.isUpperCase(currChar) && idx != -1) {
                    //Get the idxth character of shiftedAlphabet (newChar)
                    char newChar = shiftedAlphabetUpper2.charAt(idx);
                    //Replace the ith character of encrypted with newChar
                    sbInput.setCharAt(i, newChar);
                } 
            }
        }
        return sbInput.toString();
    }

    public void testCaesar() {
        int key = 15;
        FileResource fr = new FileResource();
        String message = fr.asString();
        String encrypted = encrypt(message, key);
        System.out.println(encrypted);
        String decrypted = encrypt(encrypted, 26-key);
        System.out.println(decrypted);
    }

    public void testEncryptTwoKeys () {
        int key1 = 2;
        int key2 = 20;
        FileResource fr = new FileResource();
        String message = fr.asString();
        //String encrypted = encryptTwoKeys(message, key1, key2);
        //System.out.println(encrypted);
        String decrypted = encryptTwoKeys(message, 26-key1, 26-key2);
        System.out.println(decrypted);
    }
}

