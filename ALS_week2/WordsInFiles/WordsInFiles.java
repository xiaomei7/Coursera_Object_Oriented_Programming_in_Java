import edu.duke.*;
import java.util.*;
import java.io.*;

public class WordsInFiles {
    private HashMap<String, ArrayList<String>> wordFilenames;

    public WordsInFiles () {
        wordFilenames = new HashMap<String, ArrayList<String>>();
    }

    private void addWordsFromFile (File f) {
        /**
        *Input:
        *   File - f
        *Output:
         *   Add all the words from f into the map wordFilenames
        */

        // for all words in f
        try {
            Scanner input = new Scanner(f);
            while (input.hasNext()) {
                String word = input.next();
                // If a word is not in the map
                if (!wordFilenames.containsKey(word)) {
                    // create a new ArrayList of type String with this word, have the word map to this ArrayList
                    ArrayList<String> fileNames = new ArrayList<String>();
                    fileNames.add(f.getName());
                    wordFilenames.put(word, fileNames);
                }
                // If a word is already in the map
                else {
                    // add the current filename to its ArrayList, unless the filename is already in the ArrayList
                    if (!wordFilenames.get(word).contains(f.getName())) {
                        ArrayList<String> fileNames = wordFilenames.get(word);
                        fileNames.add(f.getName());
                        wordFilenames.put(word, fileNames);
                    }
                }
            }
        }   
        catch (FileNotFoundException ex) {
            System.out.println("Invalid File.");
        }
    }

    private void buildWordFileMap () {
        /**
        * select a group of files, for each file
        * puts all of its words into the map wordFilenames
        * assume that the HashMap has been built
        */

        // clears the map
        wordFilenames.clear();
        // uses a DirectoryResource to select a group of files
        DirectoryResource dr = new DirectoryResource();
        // for each file
        for (File f : dr.selectedFiles()) {
            // puts all of its words into the map by calling the method addWordsFromFile
            addWordsFromFile(f);
        }
        
    }

    private int maxNumber () {
        /**
        *Output:
        *   int: the maximum number of files any word appears in
        *   assumes that the HashMap has already been constructed
        */

        int max = 0;

        // for every key in wordFilenames
        for (String key : wordFilenames.keySet()) {
            // find the # of files in value 
            // if the value is bigger than max
            if (wordFilenames.get(key).size() > max) {
                // replace it
                max = wordFilenames.get(key).size();
            }
        }
        // return max
        return max;
    }

    private ArrayList<String> wordsInNumFiles (int number) {
        /**
        *Input:
        *   int - number 
        *Output:
         *   ArrayList<String>: returns an ArrayList of words that appear in exactly number files
        */

        // create an ArrayList to hold the word
        ArrayList<String> words = new ArrayList<String>();
        // loop over the key of wordFilenames
        for (String key : wordFilenames.keySet()) {
            // for each value, check the size 
            // if the siza equal to number, add key to ArrayList
            if (wordFilenames.get(key).size() == number) {
                words.add(key);
            }
        }
        // return ArrayList 
        return words;
    }

    private void printFilesIn (String word) {
        /**
        *Input:
        *   String - word
        *Output:
        *   prints the names of the files this word appears in
        */

        ArrayList<String> fileNames = wordFilenames.get(word);
        for (int i = 0; i < fileNames.size(); i++) {
            System.out.println(fileNames.get(i));
        }

    }
    
    public void tester () {
        buildWordFileMap();
        int max = maxNumber();
        System.out.println("maximum number of files any word appears in are " + max);
        System.out.println("------------------------");

        ArrayList<String> words = wordsInNumFiles(7);
        System.out.println(words.size());
        System.out.println("------------------------");
        
        
        printFilesIn("tree");

        /*for (String key : wordFilenames.keySet()) {
            System.out.println("Key: " + key);
            printFilesIn(key);
            System.out.println("------------------------");
        }*/
        System.out.println("========================");
    }
}