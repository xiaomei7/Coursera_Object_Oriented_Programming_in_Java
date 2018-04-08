import java.util.*;
import edu.duke.*;

public class Tester {
	public void testTryKeyLength () {
		System.out.println("testTryKeyLength ------------------ ");
		FileResource fr = new FileResource();
		String input = fr.asString();
		VigenereBreaker vb = new VigenereBreaker();

		// make a new FileResource to read from the English dictionary file
        FileResource dicFr = new FileResource("dictionaries/English");
        // use your readDictionary method to read the contents of that file into a HashSet of Strings
        HashSet<String> dic = vb.readDictionary(dicFr);

		int[] key = vb.tryKeyLength(input, 38, 'e');
		VigenereCipher vc = new VigenereCipher(key);
        String currentDecryption = vc.decrypt(input);

		int validWords = vb.countWords(currentDecryption, dic);
        System.out.println("validWords: " + validWords);

		for (int i = 0; i < key.length; i++) {
		    System.out.print(key[i] + "\t");
		}
		System.out.println("\n");
	}
}