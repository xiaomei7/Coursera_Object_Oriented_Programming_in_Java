import edu.duke.*;

public class Part2 {
    public String findSimpleGene (String dna, String startCodon, String stopCodon) {
        /**
        *Input: String - dna, startCodon, stopCodon
        *Output: String - Gene
        */
        
       String startCodonM = "";
       String stopCodonM = "";
       if (dna.toUpperCase().equals(dna)) {
           startCodonM = startCodon.toUpperCase();
           stopCodonM = stopCodon.toUpperCase();
       }
       else if (dna.toLowerCase().equals(dna)) {
           startCodonM = startCodon.toLowerCase();
           stopCodonM = stopCodon.toLowerCase();
       }
        
 
        // Finds the index position of the start codon “ATG”. If there is no “ATG”, return the empty string.
        int startIndex = dna.indexOf(startCodonM);
        if (startIndex == -1) {
            return "";
        }

        // Finds the index position of the first stop codon “TAA” appearing after the “ATG” that was found. 
        // If there is no such “TAA”, return the empty string.
        int endIndex = dna.indexOf(stopCodonM, startIndex+3);
        if (endIndex == -1) {
            return "";
        }

        // If the length of the substring between the “ATG” and “TAA” is a multiple of 3, 
        // then return the substring that starts with that “ATG” and ends with that “TAA”.
        if (dna.substring(startIndex, endIndex+3).length() % 3 != 0) {
            return "";
        }
        
        return dna.substring(startIndex, endIndex+3);
    }

    public void testSimpleGene () {
        System.out.println("================================================");
        // DNA with no “ATG”
        String test_dna1 = "TAGATAA";
        System.out.println("DNA: " + test_dna1);
        System.out.println("Result: " + findSimpleGene(test_dna1, "ATG", "TAA"));

        // DNA with no “TAA”
        String test_dna2 = "TAGAATGATAT";
        System.out.println("DNA: " + test_dna2);
        System.out.println("Result: " + findSimpleGene(test_dna2, "ATG", "TAA"));

        // DNA with ATG, TAA and the substring between them is not a multiple of 3
        String test_dna3 = "AATGAGTTGAATTTTAA";
        System.out.println("DNA: " + test_dna3);
        System.out.println("Result: " + findSimpleGene(test_dna3, "ATG", "TAA"));

        // DNA with ATG, TAA and the substring between them is a multiple of 3 (a gene)
        String test_dna4 = "AATGAGTTGAATTTAA";
        System.out.println("DNA: " + test_dna4);
        System.out.println("Result: " + findSimpleGene(test_dna4, "ATG", "TAA"));
        
        // DNA with ATG, TAA and the substring between them is a multiple of 3 (a gene)
        // Lower case scenario 
        String test_dna5 = "atcgatgtcataag";
        System.out.println("DNA: " + test_dna5);
        System.out.println("Result: " + findSimpleGene(test_dna5, "atg", "taa"));
        
        // DNA with ATG, TAA and the substring between them is a multiple of 3 (a gene)
        // Lower case scenario with upper case codons
        String test_dna6 = "atcgatgtcataag";
        System.out.println("DNA: " + test_dna6);
        System.out.println("Result: " + findSimpleGene(test_dna6, "ATG", "TAA"));
    }
}

 