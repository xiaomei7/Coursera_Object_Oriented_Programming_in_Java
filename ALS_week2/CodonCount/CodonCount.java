import edu.duke.*;
import java.util.*;

public class CodonCount {
	HashMap<String, Integer> codonCountMap;

	public CodonCount () {
		codonCountMap = new HashMap<String, Integer>();
	}

	private void buildCodonMap (int start, String dna) {
		/**
		*Input:
		*	int - start: value of 0, 1, or 2
		*	String - dna
		*Output:
		*	build a new map of codons mapped to their counts from 
		*	the string dna with the reading frame with the position start (a value of 0, 1, or 2)
		*/

		// make sure your map is empty before building it
		codonCountMap.clear();
		// from the start index, read three symbols at time, store the codon
		for (int startIdx = start; startIdx <= dna.length() - 3; startIdx += 3) {
			String currentCodon = dna.substring(startIdx, startIdx + 3);
			// if the codon exists, add count + 1
			if (codonCountMap.containsKey(currentCodon)) {
				codonCountMap.put(currentCodon, codonCountMap.get(currentCodon) + 1);
			}
			// if the codon does not exist, add codon and count 1
			else {
				codonCountMap.put(currentCodon, 1);
			}
		}
	}

	private String getMostCommonCodon () {
		/**
		*Output:
		*	String: the codon in a reading frame that has the largest count.
		*	Assumes the HashMap of codons to counts has already been built.
		*/

		// for every key in codonCountMap, find the largest value
		int largestCount = 0;
		String mostCommonCodon = "";
		for (String codon : codonCountMap.keySet()) {
			if (codonCountMap.get(codon) > largestCount) {
				largestCount = codonCountMap.get(codon);
				mostCommonCodon = codon;
			}
		}
		return mostCommonCodon;
	}

	private void printCodonCounts (int start, int end) {
		/**
		*Input:
		*	int - start: count
		*	int - end: count
		*Output:
		*	prints all the codons in the HashMap along with their counts 
		*	if their count is between start and end, 
		*	inclusive.
		*/

		for (String codon : codonCountMap.keySet()) {
			if (codonCountMap.get(codon) >= start && 
				codonCountMap.get(codon) <= end) {
				System.out.println(codon + "\t" + codonCountMap.get(codon));
			}
		}
	}

	public void tester () {
		// prompts the user for a file that contains a DNA strand
		FileResource fr = new FileResource();
		// 	(could be upper or lower case letters in the file, 
		//  convert them all to uppercase, since case should not matter)
		String dna = fr.asString().trim().toUpperCase();
		// for each of the three possible reading frames
		for (int i = 0; i <= 2; i++) {
			// builds a HashMap of codons to their number of occurrences in the DNA strand
			buildCodonMap(i, dna);
			// prints the total number of unique codons in the reading frame
			System.out.println("Total number of unique condons are " + codonCountMap.size());
			// prints the most common codon and its count
			String mostCommonCodon = getMostCommonCodon();
			System.out.println("Most common codon is " + mostCommonCodon + 
				", and its count is " + codonCountMap.get(mostCommonCodon));
			// prints the codons and their number of occurrences for those codons 
			//  whose number of occurrences in this reading frame are between two numbers inclusive
			System.out.println("All codons in mandatory range and thier counts: ");
			printCodonCounts(7, 7);
			System.out.println("------------------------");
		}

	}
}