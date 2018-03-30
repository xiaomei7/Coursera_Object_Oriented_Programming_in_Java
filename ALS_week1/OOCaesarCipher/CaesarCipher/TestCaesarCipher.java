
import edu.duke.*;

public class TestCaesarCipher {
    private int[] countLetters (String message) {
        /**
        *Input:
        *   String - message: count letter inside a string
        *Output:
        *   int[]: array of int contains the letter counting/frequency
        */

        
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[26];
        for (int k = 0; k < message.length(); k++) {
            char ch = Character.toLowerCase(message.charAt(k));
            int dex = alphabet.indexOf(ch);
            if (dex != -1) counts[dex]++;
        }
        return counts;
    }

    private int maxIndex (int[] vals) {
        /**
        *Input:
        *   int[] - vals: array of int which used to find index with max value
        *Output:
        *   int: the index with max value in vals
        */

        // initialize the maxDex to 0
        int maxDex = 0;
        // for every element in vals
        for (int k = 0; k < vals.length; k++) {
            // check if the value is bigger than the value inside maxDex
            if (vals[k] > vals[maxDex]) maxDex = k;
        }
        return maxDex;
    }

    private int getKey (String s) {
        /**
        *Input:
        *   String - s
        *Output:
        *   int: key to decrypted s using most frequency letter method
        */

        // call countLetters to get an array of the letter frequencies in String s
        int[] freqs = countLetters(s);
        // use maxIndex to calculate the index of the largest letter frequency
        // which is the location of the encrypted letter ‘e’
        // which leads to the key
        int maxDex = maxIndex(freqs);
        // the key is maxDex - 4; 4 = index of 'e', highest frequency of English letter
        // key is used to encrypt the message, so 26 - key = decrypt key
        int key = maxDex - 4;
        // 4 - maxDex = how much it shift to the left; the gap between e and pretend e
        // 26 - (4 - maxDex) loop around to found the key; how much it shift to the right
        if (maxDex < 4) key = 26 - (4 - maxDex);
        return key;
    }

    private String breakCaesarCipher (String input) {
        // get key
        int key = getKey(input);
        // create a CaesarCipher object 
        CaesarCipher cc = new CaesarCipher(key);
        // 26 - key is the decrypt key
        return cc.decrypt(input);
    }

    public void simpleTests () {
        // read file in as string
        FileResource fr = new FileResource();
        String message = fr.asString();
        // create a CaesarCipher object with key 18
        CaesarCipher cc = new CaesarCipher(15);
        // encrypt the String read in using the CaesarCipher object
        String encrypted = cc.encrypt(message);
        // print the encrypted String
        System.out.println("encrypted string: \n" + encrypted);
        // decrypt the encrypted String using the decrypt method
        String decrypted = cc.decrypt(encrypted);
        System.out.println("decrypted string: \n" + decrypted);
    }

    public void testBreakCaesarCipher () {
        FileResource fr = new FileResource();
        String message = fr.asString();

        CaesarCipher cc = new CaesarCipher(18);
        String encrypted = cc.encrypt(message);

        System.out.println("input to decrypt: \n" + encrypted);
        String decrypted = breakCaesarCipher(message);
        System.out.println("decrypted message: \n" + decrypted);
    }

}
