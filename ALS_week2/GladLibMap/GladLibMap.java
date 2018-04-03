import edu.duke.*;
import java.util.*;

public class GladLibMap {
    // maps a String representing a category to an ArrayList of words in that category
    private HashMap<String, ArrayList<String>> myMap;

    private ArrayList<String> usedWords;
    private ArrayList<String> usedLabel;
    
    private Random myRandom;
    
    private static String dataSourceURL = "http://dukelearntoprogram.com/course3/data";
    private static String dataSourceDirectory = "data";
    
    public GladLibMap(){
        myMap = new HashMap<String, ArrayList<String>>();
        usedLabel = new ArrayList<String>();
        initializeFromSource(dataSourceDirectory);
        usedWords = new ArrayList<String>();
        myRandom = new Random();
    }
    
    public GladLibMap(String source){
        myMap = new HashMap<String, ArrayList<String>>();
        usedLabel = new ArrayList<String>();
        initializeFromSource(source);
        usedWords = new ArrayList<String>();
        myRandom = new Random();
    }
    
    private void initializeFromSource(String source) {
        // create an Array of categories
        String[] categories = {"adjective", "noun", "color", "country", "name", "animal", "timeframe", "verb", "fruit"};
        // iterate over this Array
        // For each category
        for (int i = 0; i < categories.length; i++) {
            // read in the words from the associated file
            // create an ArrayList of the words (using the method readIt)
            // put the category and ArrayList into the HashMap
            myMap.put(categories[i], readIt(source + "/" + categories[i] +".txt"));
        }
    }
    
    private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
    }
    
    private String getSubstitute(String label) {
        if (label.equals("number")){
            return ""+myRandom.nextInt(50)+5;
        }
        // call randomFrom that passes the appropriate ArrayList from myMap
        return randomFrom(myMap.get(label));
    }
    
    private String processWord(String w){
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub = getSubstitute(w.substring(first+1,last));
        if (!usedLabel.contains(w.substring(first+1,last))) usedLabel.add(w.substring(first+1,last));
        // Once it finds a word to use, check to see if that word has been used before or not
        while (usedWords.contains(sub)) {
            sub = getSubstitute(w.substring(first+1,last));
        }

        usedWords.add(sub);

        return prefix+sub+suffix;
    }
    
    private void printOut(String s, int lineWidth){
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth){
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
    
    private String fromTemplate(String source){
        String story = "";
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String word : resource.words()){
                story = story + processWord(word) + " ";
            }
        }
        return story;
    }
    
    private ArrayList<String> readIt(String source){
        ArrayList<String> list = new ArrayList<String>();
        if (source.startsWith("http")) {
            URLResource resource = new URLResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        else {
            FileResource resource = new FileResource(source);
            for(String line : resource.lines()){
                list.add(line);
            }
        }
        return list;
    }

    private int totalWordsInMap () {
        int totalWords = 0;
        // returns the total number of words in all the ArrayLists in the HashMap
        for (String key : myMap.keySet()) {
            totalWords += myMap.get(key).size();
        }
        return totalWords;
    }

    private int totalWordsConsidered () {
        int totalConsidered = 0;
        //  keep track of the categories used in solving the GladLib by usedLabel
        // for every categories used, add the size of ArrayList to total
        for (int i = 0; i < usedLabel.size(); i++) {
            totalConsidered += myMap.get(usedLabel.get(i)).size();
        }
        return totalConsidered;
    }
    
    public void makeStory(){
        usedWords.clear();
        usedLabel.clear();
        System.out.println("\n");
        String story = fromTemplate("datalong/madtemplate3.txt");
        printOut(story, 60);
        System.out.println("\nthere are " + usedWords.size() + " words replaced.");

        // call totalWordsInMap and print out the total number of words that were possible to pick from.
        System.out.println("the total number of words that were possible to pick from are " + totalWordsInMap());
        System.out.println("the total number of words considered are " + totalWordsConsidered());
    }
    
}
