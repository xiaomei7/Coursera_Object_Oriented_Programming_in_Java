
import edu.duke.*;

public class WordLengths
{
    public void countWordLengths (FileResource resource, int[] counts) {
        /**
        *Input:
        *   FileResource - resource:
        *   int[] - counts:
        *Output:
        *   Read in the words from resource and count the number of words of each length 
        *   for all the words in resource, storing these counts in the array counts.
        *   1. If a word has a non-letter as the first or last character, 
        *       it should not be counted as part of the word length.
        *   2. For any words equal to or larger than the last index of the counts array, 
        *       count them as the largest size represented in the counts array.
        */

        for(String word : resource.words()) {
            // check if the word's first and last symbol is letter 
            // case1: both first and last symbol are not letters

            // case 0, there is only one non-letter symbol
            if (word.length() == 1 && !Character.isLetter(word.charAt(0))) {
                counts[word.length() - 1]++;
            }
            // case1: both first and last symbol are not letter 
            else if (!Character.isLetter(word.charAt(0)) && 
                !Character.isLetter(word.charAt(word.length() - 1))) {
                if ((word.length() - 2) >= (counts.length - 1)) {
                    counts[counts.length - 1]++;
                }
                else counts[word.length() - 2]++;
            }
            // case2: only first symbol is not letter or only last symbol is not letter
            else if (!Character.isLetter(word.charAt(0)) || 
                !Character.isLetter(word.charAt(word.length() - 1))) {
                if ((word.length() - 1) >= (counts.length - 1)) {
                    counts[counts.length - 1]++;
                }
                else counts[word.length() - 1]++;
            }
            // case3: no non-letter at first or last positon
            else {
                if ((word.length()) >= (counts.length - 1)) {
                    counts[counts.length - 1]++;
                }
                else counts[word.length()]++;
            }           
        }
    }

    public void testCountWordLengths () {
        FileResource resource = new FileResource();
        int[] counts = new int[31];
        countWordLengths(resource, counts);
        for (int i = 1; i < counts.length; i++) {
            if (counts[i] != 0 ) System.out.println("Length " + i + "\t" + counts[i]);
        }
        System.out.println("--------------------");
    }
}
