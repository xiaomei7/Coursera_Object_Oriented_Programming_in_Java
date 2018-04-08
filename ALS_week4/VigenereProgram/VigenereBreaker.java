import java.util.*;
import edu.duke.*;

public class VigenereBreaker {
    public String sliceString(String message, int whichSlice, int totalSlices) {
        /**
        *Input:
        *   String - message: the encrypted message
        *   int - whichSlice: the index the slice should start from
        *   int - totalSlices: the length of the key
        *Output:
        *   String: consisting of every totalSlices-th character from message, 
        *       starting at the whichSlice-th character
        */

        // create a StringBuilder and padd message as input
        StringBuilder input = new StringBuilder(message);
        // create another StringBuilder to hold the output
        StringBuilder output = new StringBuilder();
        // use whichSlice to locate the starting index in sb
        for (int i = whichSlice; i < input.length(); i += totalSlices) {
            // for every index of whichSlice + totalSlices, extract the char and append to output
            output.append(input.charAt(i));
        }
        // return the output 
        return output.toString();
    }

    public int[] tryKeyLength(String encrypted, int klength, char mostCommon) {
        /**
        *Input:
        *   String - encrypted: the encrypted message
        *   int - klength: the key length
        *   char - mostCommon: the most common character in the language of the message
        *Output:
        *   int[]: fill in the key (which is an array of integers) 
        */

        int[] key = new int[klength];
        // for every i in key[]
        for (int i = 0; i < klength; i++) {
            // use sliceString to get the current String that matches the current key
            String currentSlice = sliceString(encrypted, i, klength);
            // use getKey in CaesarCracker and add the key to key[]
            CaesarCracker cc = new CaesarCracker();
            int currentKey = cc.getKey(currentSlice);
            key[i] = currentKey;
        }
        return key;
    }

    public HashSet<String> readDictionary (FileResource fr) {
        /**
        *Input:
        *   FileResource - fr
        *Output:
        *   HashSet<String>: representing the words in a dictionary
        */

        // make a new HashSet of Strings
        HashSet<String> dicWords = new HashSet<String>();
        // read each line in fr (which should contain exactly one word per line)
        for (String line : fr.lines()) {
            // convert that line to lowercase
            // put that line into the HashSet
            dicWords.add(line.toLowerCase());
        }
        // return the HashSet representing the words in a dictionary
        return dicWords;
    }

    public int countWords (String message, HashSet<String> dictionary) {
        /**
        *Input:
        *   String - message
        *   HashSet<String> - dictionary
        *Output:
        *   int: count of how many valid words it found in dictionary 
        */

        // initial countWords to 0
        int countWords = 0;
        // split the message into words (use .split(“\\W+”)), return String[]
        String[] messageSplit = message.split("\\W+");
        // iterate over those words, and see how many appear in the dictionary (lowercase).
        for (int i = 0; i < messageSplit.length; i++) {
            // if one appear, add 1 to countWords
            if (dictionary.contains(messageSplit[i].toLowerCase())) countWords++;
        }
        // return countWords
        return countWords;
    }

    public String breakForLanguage (String encrypted, HashSet<String> dictionary) {
        /**
        *Input:
        *   String - encrypted
        *   HashSet<String> - dictionary
        *Output:
        *   String: decrypted message
        */

        int maxRealWords = 0;
        String maxDecryption = "";
        int rightKeyLength = 0;
        // For each key length (1 - 100), decrypt the message (using VigenereCipher’s decrypt method), 
        for (int keyLength = 1; keyLength <= 100; keyLength++) {
            // get the keySet for current key length
            char mostCommonChar = mostCommonCharIn(dictionary);
            int[] keySet = tryKeyLength(encrypted, keyLength, mostCommonChar);
            VigenereCipher vc = new VigenereCipher(keySet);
            String currentDecryption = vc.decrypt(encrypted);
            // count how many of the “words” in it are real words in English, 
            //  based on the dictionary passed in (use the countWords method you just wrote).
            int currentRealWords = countWords(currentDecryption, dictionary);
            //System.out.println("currentRealWords: " + currentRealWords);
            // figure out which decryption gives the largest count of real words
            if (currentRealWords > maxRealWords) {
                maxRealWords = currentRealWords;
                maxDecryption = currentDecryption;
                rightKeyLength = keyLength;
            }
        }
        // return that String decryption
        int[] rightKey = tryKeyLength(encrypted, rightKeyLength, mostCommonCharIn(dictionary));
        System.out.println("The key length for this language: " + rightKeyLength);
        System.out.print("The key for this language: ");
        for (int i = 0; i < rightKey.length; i++) {
            System.out.print(rightKey[i] + "\t");
        }
        System.out.println("\nThe most valid words for this language is " + maxRealWords);
        System.out.println("\n");
        return maxDecryption;
    }

    public char mostCommonCharIn (HashSet<String> dictionary) {
        /**
        *Input:
        *   HashSet<String> - dictionary
        *Output:
        *   char: most commonly occurring character in dictionary
        */

        // create a HashMap map letter to frequency, called letterCount
        HashMap<Character, Integer> letterCount = new HashMap<Character, Integer>();
        // iterate every word in dictionary, for each word, count letter by letter
        for (String word : dictionary) {
            for (int i = 0; i < word.length(); i++) {
                // if the letter is in letterCount, update the entry + 1
                char currentChar = word.charAt(i);
                if (letterCount.containsKey(currentChar)) {
                    letterCount.put(currentChar, letterCount.get(currentChar) + 1);
                }
                else {
                    // else, add a new entry and count 1
                    letterCount.put(currentChar, 1);
                }
            }
        }
        // for all entries in letterCount, find the max value 
        int maxCount = 0;
        char maxChar = 'a';
        for (Character ch : letterCount.keySet()) {
            if (letterCount.get(ch) > maxCount) {
                maxCount = letterCount.get(ch);
                maxChar = ch;
            }
        }
        // returns the key with max value
        return maxChar;
    }

    public void breakForAllLangs (String encrypted, HashMap<String, HashSet<String>> languages) {
        /**
        *Input:
        *   String - encrypted
        *   HashMap<String, HashSet<String>> - languages:
        *       mapping a String representing the name of a language to 
        *       a HashSet of Strings containing the words in that language
        *Output:
        *   print out the decrypted message as well as the language that you identified for the message
        */

        // create a HashMap map language (String) to most valid words (Integer), called langValidCount
        HashMap<String, Integer> langValidCount = new HashMap<String, Integer>();
        // create a HashMap map language to its decryption (String)
        HashMap<String, String> langDecryption = new HashMap<String, String>();
        // for each language in languages, use breakForLanguage to get the decryption for that language
        for (String lang : languages.keySet()) {
            System.out.println(lang + " ---------------------");
            String currentDecryption = breakForLanguage(encrypted, languages.get(lang));
            // for that decryption, use countWords to get valid words in that language and store in langValidCount
            int currentValidWords = countWords(currentDecryption, languages.get(lang));
            langValidCount.put(lang, currentValidWords);
            langDecryption.put(lang, currentDecryption);
        }
        // in langValidCount, for entry that has max value, print both key and value
        int maxValidWords = 0;
        String maxValidLang = "";
        for (String lang : langValidCount.keySet()) {
            if (langValidCount.get(lang) > maxValidWords) {
                maxValidWords = langValidCount.get(lang);
                maxValidLang = lang;
            }
        }
        System.out.println("Decrypted Language: " + maxValidLang);
        System.out.println("The total valid words in " + maxValidLang + " is " + maxValidWords);
        System.out.println("Here is the decrypted message: ");
        System.out.println(langDecryption.get(maxValidLang));
    }

    public void breakVigenere () {
        System.out.println("breakVigenere =========================");
        // Create a new FileResource using its default constructor
        FileResource fr = new FileResource();
        // Use the asString method to read the entire contents of the file into a String
        String input = fr.asString();

        // make a HashMap mapping Strings to a HashSet of Strings that will 
        //  map each language name to the set of words in its dictionary
        HashMap<String, HashSet<String>> languagesDic = new HashMap<String, HashSet<String>>();
        // read (using your readDictionary method) each of the dictionaries that we have provided
        String[] languages = {"Danish", "Dutch", "English", "French", "German", "Italian", "Portuguese", "Spanish"};
        for (int i = 0; i < languages.length; i++) {
            FileResource dicFr = new FileResource("dictionaries/" + languages[i]);
            HashSet<String> currentDic = readDictionary(dicFr);
            // store the words in the HashMap you made
            languagesDic.put(languages[i], currentDic);
        }

        // call breakForAllLangs
        breakForAllLangs(input, languagesDic);
    }
}
