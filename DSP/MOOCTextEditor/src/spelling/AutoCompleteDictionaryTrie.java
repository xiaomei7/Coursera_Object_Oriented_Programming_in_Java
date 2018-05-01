package spelling;

import java.util.List;
import java.util.Set;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author Xiaomei Wang
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should convert the 
	 * string to all lower case before you insert it. 
	 * 
	 * This method adds a word by creating and linking the necessary trie nodes 
	 * into the trie, as described outlined in the videos for this week. It 
	 * should appropriately use existing nodes in the trie, only creating new 
	 * nodes when necessary. E.g. If the word "no" is already in the trie, 
	 * then adding the word "now" would add only one additional node 
	 * (for the 'w').
	 * 
	 * @return true if the word was successfully added or false if it already exists
	 * in the dictionary.
	 */
	public boolean addWord(String word)
	{
	    //TODO: Implement this method.
		// convert word to lower case
		// loop through all the char in word
		TrieNode currNode = root;
		TrieNode next = null;
		for (Character c : word.toLowerCase().toCharArray()) {
			// check if the first node has the first character in word
			// if it is, go to next node
			next = currNode.insert(c);
			if (next != null) currNode = next;
			else currNode = currNode.getChild(c);
		}
		// if next is null and there is a text in currNode, that means the word is already in the trie, so return false
		if (next == null && currNode.endsWord()) return false;
	
		currNode.setEndsWord(true); 
		
 		size++;
	    return true;
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    //TODO: Implement this method
		
	    return size;
	}
	
	
	/** Returns whether the string is a word in the trie, using the algorithm
	 * described in the videos for this week. */
	@Override
	public boolean isWord(String s) 
	{
	    // TODO: Implement this method
		
		// the empty string is not a word!
		if (s.equals("")) return false;
		
		TrieNode currNode = root;
		for (Character c : s.toLowerCase().toCharArray()) {
			currNode = currNode.getChild(c);
			if (currNode == null) return false;
		}
		if (currNode.endsWord()) return true;
		return false;
	}

	/** 
     * Return a list, in order of increasing (non-decreasing) word length,
     * containing the numCompletions shortest legal completions 
     * of the prefix string. All legal completions must be valid words in the 
     * dictionary. If the prefix itself is a valid word, it is included 
     * in the list of returned words. 
     * 
     * The list of completions must contain 
     * all of the shortest completions, but when there are ties, it may break 
     * them in any order. For example, if there the prefix string is "ste" and 
     * only the words "step", "stem", "stew", "steer" and "steep" are in the 
     * dictionary, when the user asks for 4 completions, the list must include 
     * "step", "stem" and "stew", but may include either the word 
     * "steer" or "steep".
     * 
     * If this string prefix is not in the trie, it returns an empty list.
     * 
     * @param prefix The text to use at the word stem
     * @param numCompletions The maximum number of predictions desired.
     * @return A list containing the up to numCompletions best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 // TODO: Implement this method
    	 // This method should implement the following algorithm:
    	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    	 //    empty list
    	 // 2. Once the stem is found, perform a breadth first search to generate completions
    	 //    using the following algorithm:
    	 //    Create a queue (LinkedList) and add the node that completes the stem to the back
    	 //       of the list.
    	 //    Create a list of completions to return (initially empty)
    	 //    While the queue is not empty and you don't have enough completions:
    	 //       remove the first Node from the queue
    	 //       If it is a word, add it to the completions list
    	 //       Add all of its child nodes to the back of the queue
    	 // Return the list of completions
    	 
    	 List<String> predictWords = new LinkedList<String>();
    	 
    	 // for each char in prefix, find the position of the stem, return empty list if not found
    	 TrieNode stemNode = root;
    	 for (Character c : prefix.toLowerCase().toCharArray()) {
    		 stemNode = stemNode.getChild(c);
    		 if (stemNode == null) return predictWords;
    	 }
    	 // create a queue to help perform breadth first search algorithm
    	 LinkedList<TrieNode> queue = new LinkedList<TrieNode>();
    	 queue.add(stemNode);
    	 int numAdd = 0;
    	 while(!queue.isEmpty() && numAdd < numCompletions) {
    		 TrieNode currNode = queue.pop();
    		 if (currNode.endsWord()) {
    			 predictWords.add(currNode.getText());
    			 numAdd++;
    		 }
    		 for (Character c : currNode.getValidNextCharacters()) {
    			 queue.add(currNode.getChild(c));
    		 }
    	 }
         return predictWords;
     }

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	

	
}