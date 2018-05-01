/**
 * 
 */
package spelling;

//import java.util.ArrayList;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * WPTree implements WordPath by dynamically creating a tree of words during a Breadth First
 * Search of Nearby words to create a path between two words. 
 * 
 * @author UC San Diego Intermediate MOOC team
 *
 */
public class WPTree implements WordPath {

	// this is the root node of the WPTree
	private WPTreeNode root;
	// used to search for nearby Words
	private NearbyWords nw; 
	
	// This constructor is used by the Text Editor Application
	// You'll need to create your own NearbyWords object here.
	public WPTree () {
		this.root = null;
		// TODO initialize a NearbyWords object
		Dictionary d = new DictionaryHashSet();
		DictionaryLoader.loadDictionary(d, "data/dict.txt");
		this.nw = new NearbyWords(d);
	}
	
	//This constructor will be used by the grader code
	public WPTree (NearbyWords nw) {
		this.root = null;
		this.nw = nw;
	}
	
	// see method description in WordPath interface
	public List<String> findPath(String word1, String word2) 
	{
	    // TODO: Implement this method.
		
		// Create a queue of WPTreeNodes to hold words to explore
		List<WPTreeNode> queue = new LinkedList<WPTreeNode>();
		// Create a visited set to avoid looking at the same word repeatedly
		HashSet<WPTreeNode> visited = new HashSet<WPTreeNode>();
		// Set the root to be a WPTreeNode containing word1
		root = new WPTreeNode(word1, null);
		// Add the initial word to visited
		visited.add(root);
		// Add root to the queue
		queue.add(root);

		// while the queue has elements and we have not yet found word2
		boolean foundWord2 = false;
		while (!queue.isEmpty() && !foundWord2) {
		  // remove the node from the start of the queue and assign to curr
			WPTreeNode curr = ((LinkedList<WPTreeNode>) queue).pop();
		  // get a list of real word neighbors (one mutation from curr's word)
			List<String> neighbors = nw.distanceOne(curr.getWord(), true);
			List<WPTreeNode> neighborsNode = new ArrayList<WPTreeNode>();
			for (String n : neighbors) {
				neighborsNode.add(new WPTreeNode(n, curr));
			}
		  // for each n in the list of neighbors
			for (WPTreeNode nn : neighborsNode) {
		     // if n is not visited
				if (!visited.contains(nn)) {
		       // add n as a child of curr 
					curr.addChild(nn.getWord());
		       // add n to the visited set
					visited.add(nn);
		       // add the node for n to the back of the queue
					queue.add(nn);
		       // if n is word2
					if (nn.getWord().equals(word2)) return nn.buildPathToRoot();
		          // return the path from child to root
				}
			}
		}

		// return null as no path exists
				
	    return null;
	}
	
	// Method to print a list of WPTreeNodes (useful for debugging)
	private String printQueue(List<WPTreeNode> list) {
		String ret = "[ ";
		
		for (WPTreeNode w : list) {
			ret+= w.getWord()+", ";
		}
		ret+= "]";
		return ret;
	}
	
}

/* Tree Node in a WordPath Tree. This is a standard tree with each
 * node having any number of possible children.  Each node should only
 * contain a word in the dictionary and the relationship between nodes is
 * that a child is one character mutation (deletion, insertion, or
 * substitution) away from its parent
*/
class WPTreeNode {
    
    private String word;
    private List<WPTreeNode> children;
    private WPTreeNode parent;
    
    /** Construct a node with the word w and the parent p
     *  (pass a null parent to construct the root)  
	 * @param w The new node's word
	 * @param p The new node's parent
	 */
    public WPTreeNode(String w, WPTreeNode p) {
        this.word = w;
        this.parent = p;
        this.children = new LinkedList<WPTreeNode>();
    }
    
    /** Add a child of a node containing the String s
     *  precondition: The word is not already a child of this node
     * @param s The child node's word
	 * @return The new WPTreeNode
	 */
    public WPTreeNode addChild(String s){
        WPTreeNode child = new WPTreeNode(s, this);
        this.children.add(child);
        return child;
    }
    
    /** Get the list of children of the calling object
     *  (pass a null parent to construct the root)  
	 * @return List of WPTreeNode children
	 */
    public List<WPTreeNode> getChildren() {
        return this.children;
    }
   
    /** Allows you to build a path from the root node to 
     *  the calling object
     * @return The list of strings starting at the root and 
     *         ending at the calling object
	 */
    public List<String> buildPathToRoot() {
        WPTreeNode curr = this;
        List<String> path = new LinkedList<String>();
        while(curr != null) {
            path.add(0,curr.getWord());
            curr = curr.parent; 
        }
        return path;
    }
    
    /** Get the word for the calling object
     *
	 * @return Getter for calling object's word
	 */
    public String getWord() {
        return this.word;
    }
    
    /** toString method
    *
	 * @return The string representation of a WPTreeNode
	 */
    public String toString() {
        String ret = "Word: "+word+", parent = ";
        if(this.parent == null) {
           ret+="null.\n";
        }
        else {
           ret += this.parent.getWord()+"\n";
        }
        ret+="[ ";
        for(WPTreeNode curr: children) {
            ret+=curr.getWord() + ", ";
        }
        ret+=(" ]\n");
        return ret;
    }

}

