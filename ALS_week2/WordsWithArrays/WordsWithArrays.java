import java.util.*;

import edu.duke.*;

public class WordsWithArrays {
    
    StorageResource myWords;
    
    public WordsWithArrays() {
        myWords = new StorageResource();
    }
	
    public void readWords(){
        myWords.clear();
        FileResource resource = new FileResource();
        for(String word : resource.words()){
            myWords.add(word.toLowerCase());
        }
    }
    
    public boolean contains(String[] list, String word, int numStored){
        for(int k=0; k < numStored; k++){
            if (list[k].equals(word)){
                return true;
            }
        }
        return false;
    }
    
    public int countDifferentArray(){
        int numStored = 0;
        String[] words = new String[myWords.size()];
        for(String s : myWords.data()){
             if (! contains(words,s, numStored)){
                 words[numStored] = s;
                 numStored++;
             }
        }
        return numStored;
    }
    
    public void tester(){
        readWords();
        System.out.println("number of words read: "+myWords.size());
        int unique = countDifferentArray();
        System.out.println("array count "+unique);
    }
    

}
