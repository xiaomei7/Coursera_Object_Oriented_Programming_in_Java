import edu.duke.*;
import java.lang.Math;

public class CaesarBreaker {
    public int[] countLetters (String message) {
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

    public int maxIndex (int[] vals) {
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

    public String decrypt (String encrypted) {
        // create a new CaesarCipher class 
        CaesarCipher cc = new CaesarCipher();
        // get key
        int key = getKey(encrypted);
        // 26 - key is the decrypt key
        return cc.encrypt(encrypted, 26 - key);
    }

    public String halfOfString (String message, int start) {
        /**
        *Input:
        *   String - message
        *   int - start: start position in the message
        *Output:
        *   String: every other character from message starting with the start position
        */

        StringBuilder halfString = new StringBuilder();

        for (int k = start; k < message.length(); k += 2) {
            halfString.append(message.charAt(k));
        }
        return halfString.toString();
    }

    public int getKey (String s) {
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

    public String decryptTwoKeys (String encrypted) {
        /**
        *Input:
        *   String - encrypted: string that encrypted with 2 keys
        *Output:
        *   String: decrypted string
        */

        // use halfOfString to divide encrypted into two strings
        String firstHalf = halfOfString(encrypted, 0);
        String secondHalf = halfOfString(encrypted, 1);
        // for each string, use decrypt to decryt the message
        String firstHalfDecrypted = decrypt(firstHalf);
        String secondHalfDecrypted = decrypt(secondHalf);
        // print the two keys find
        System.out.println("Key1: " + getKey(firstHalf));
        System.out.println("Key2: " + getKey(secondHalf));
        // combine the two strings and return it
        StringBuilder wholeString = new StringBuilder();

        for (int k = 0; 
            k < Math.max(firstHalfDecrypted.length(), secondHalfDecrypted.length()); 
            k++) {
            if (k < firstHalf.length()) {
                wholeString.append(firstHalfDecrypted.charAt(k));
            }
            if (k < secondHalf.length()) {
                wholeString.append(secondHalfDecrypted.charAt(k));
            }
        }

        return wholeString.toString();

    }

    public void testDecryptTwoKeys () {
        // int key1 = 23, key2 = 2;

        FileResource fr = new FileResource();
        String message = fr.asString();

        //CaesarCipher cc = new CaesarCipher();

        //String encrypted = cc.encryptTwoKeys(message, key1, key2);
        //System.out.println(encrypted);

        String decrypted = decryptTwoKeys(message);
        System.out.println(decrypted);
    }

    public void testDecrypt () {
        int key = 1;

        FileResource fr = new FileResource();
        String message = fr.asString();

        CaesarCipher cc = new CaesarCipher();

        String encrypted = cc.encrypt(message, key);
        System.out.println(encrypted);

        String decrypted = decrypt(encrypted);
        System.out.println(decrypted);
    }
    
}

