
import edu.duke.*;
import java.util.ArrayList;

public class WordFrequencies
{
    // The kth position in myFreqs represents the number of times the kth word in myWords occurs in the file
    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;
    
    public WordFrequencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }
    
    public void findUnique(){ 
        /**
        *Find unique words from a file and record the words and their frequencies inside ArrayLists
        */

        //clear both myWords and myFreqs, using the .clear() method
        // .clear() removes all the elements of the list
        myWords.clear();
        myFreqs.clear();

        // selects a file and then iterates over every word in the file
        FileResource resource = new FileResource();
        
        for(String s : resource.words()){
            s = s.toLowerCase();
            // putting the unique words found into myWords (lower case version)
            int index = myWords.indexOf(s);
            // if first encounter the word, append word and freqs to the end of the ArrayList
            if (index == -1){
                myWords.add(s);
                myFreqs.add(1);
            }
            // otherwise, only update the frequency
            else {
                int freq = myFreqs.get(index);
                myFreqs.set(index,freq+1);
            }
        }
    }
    
    public void tester(){
        System.out.println("===============================");
        // call findUnique
        findUnique();
        //  print out the number of unique words
        System.out.println("# unique words: " + myWords.size());
        // for each unique word, print the frequency of each word and the word
        /*for (int i = 0; i < myFreqs.size(); i++) {
            System.out.println(myFreqs.get(i) + "\t" + myWords.get(i));
        }*/

        int index = findIndexOfMax();
        System.out.println("max word/freq: " + myWords.get(index) + " " + myFreqs.get(index));
    }

    public int findIndexOfMax(){
        int max = myFreqs.get(0);
        int maxIndex = 0;
        for(int k=0; k < myFreqs.size(); k++){
            if (myFreqs.get(k) > max){
                max = myFreqs.get(k);
                maxIndex = k;
            }
        }
        return maxIndex;
    }
}
