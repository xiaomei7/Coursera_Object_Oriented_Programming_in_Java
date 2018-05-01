package spelling;

import java.util.LinkedList;

/**
 * A class that implements the Dictionary interface using a LinkedList
 * 1. ignoring the case of words; store all words as lower case words
 * 2. converted to lower case before you put them in
 * 3. convert a word to lower case before it checks to see whether it is in the Dictionary
 */
public class DictionaryLL implements Dictionary 
{

	private LinkedList<String> dict;
	
    // TODO: Add a constructor
	public DictionaryLL () {
		dict = new LinkedList<String>();
	}


    /** Add this word to the dictionary.  Convert it to lowercase first
     * for the assignment requirements.
     * @param word The word to add
     * @return true if the word was added to the dictionary 
     * (it wasn't already there). */
    public boolean addWord(String word) {
    	// TODO: Implement this method
    	// convert word to lower case
    	// check if the word is already exist
    		// if it is, return false
    	if (dict.contains(word.toLowerCase())) return false;
    		// else, add word to dict, return true
        return dict.add(word.toLowerCase());
    }


    /** Return the number of words in the dictionary */
    public int size()
    {
        // TODO: Implement this method
        return dict.size();
    }

    /** Is this a word according to this dictionary? */
    public boolean isWord(String s) {
        //TODO: Implement this method
    	// convert s to lowercase
    	// check if s is in dict
    		// if it is, return true
    		// else return false
        return dict.contains(s.toLowerCase());
    }

    
}
