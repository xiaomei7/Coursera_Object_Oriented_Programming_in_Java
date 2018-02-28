import edu.duke.*;

public class Part1 {
    public int findStopCodon (String dna, int startIndex, String stopCodon) {
        /**
        *Input: String - dna, int - startIndex, String - stopCodon
        *Output: int -  
        *   index of the first occurrence of stopCodon that appears past startIndex and 
        *   is a multiple of 3 away from startIndex
        */

        // find the stopCodon after the startIndex in dna
        int stopIndex = dna.indexOf(stopCodon, startIndex);

        while (stopIndex != -1) {
            // check is the length between the startIndex and stopCodon+3 is multiple of 3
            if (dna.substring(startIndex, stopIndex + 3).length() % 3 == 0) {
                // if yes, return the index of the stopCodon
                return stopIndex;
            }

            else {
                // if no, find next stopCodon
                stopIndex = dna.indexOf(stopCodon, stopIndex + 1);
            }
        }
        
        
        // if no valid stiopCodon exists, returns the length of the dna
        return dna.length();
    }

    public String findGene (String dna) {
        /**
        *Input: String - dna
        *Output: String - valid gene or empty string
        */

        // Find the index of the first occurrence of the start codon “ATG”. 
        int startIndex = dna.indexOf("ATG");
        // If there is no “ATG”, return the empty string.
        if (startIndex == -1) {
            return "";
        }

        // Find the index of the first occurrence of the stop codon “TAA” 
        //  after the first occurrence of “ATG” that is a multiple of three away from the “ATG”.
        int taaIndex = findStopCodon(dna, startIndex, "TAA");

        // Find the index of the first occurrence of the stop codon “TAG” 
        //  after the first occurrence of “ATG” that is a multiple of three away from the “ATG”. 
        int tagIndex = findStopCodon(dna, startIndex, "TAG");

        // Find the index of the first occurrence of the stop codon “TGA” 
        //  after the first occurrence of “ATG” that is a multiple of three away from the “ATG”.
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");

        // Return the gene formed from the “ATG” and the closest stop codon that is a multiple of three away. 
        // If there is no valid stop codon and therefore no gene, return the empty string.
        int minIndex = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));
        if (minIndex < dna.length()) {
            return dna.substring(startIndex, minIndex + 3);
        }

        return "";
    }

    public void printAllGenes (String dna) {
    	int startIndex = dna.indexOf("ATG");
    	int minIndex = 0;

    	while (true) {
    		// find the gene in dna
    		if (startIndex == -1) {
            	break;
        	}
        	
        	int taaIndex = findStopCodon(dna, startIndex, "TAA");
        	int tagIndex = findStopCodon(dna, startIndex, "TAG");
        	int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        	minIndex = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));

    		// if the gene exists, print it out
    		if (minIndex < dna.length()) {
	            System.out.println(dna.substring(startIndex, minIndex + 3));
	        }
    		// if the gene does not exist, break out the loop
    		else {
    			break;
    		}

    		// update the position index and find the next gene
    		startIndex = dna.indexOf("ATG", minIndex + 1);
    	}
    }

    public void testFindStopCodon () {
        int test1 = findStopCodon("xxxxxxTAAxxx", 0, "TAA");
        if (test1 != 6) {
            System.out.println("test failed. Code 1.");
        }

        int test2 = findStopCodon("xxxyyTGAxy", 0, "TGA");
        if (test2 != 10) {
            System.out.println("test failed. Code 2.");
        }

        int test3 = findStopCodon("xyATGxxxyyyzzzTAGxxx", 2, "TAG");
        if (test3 != 14) {
            System.out.println("test failed. Code 3.");
        }

        System.out.println("findStopCodon test finished.");
        System.out.println("====================================");
    }

    public void testFindGene () {
        // DNA with no “ATG”
        String test1 = findGene("TTAGATAATGGA");
        if (test1 != "") {
            System.out.println("test failed. Code 1.");
        }

        // DNA with “ATG” and one valid stop codon
        String test2 = findGene("TTATGTAATGGA");
        if (!test2.equals("ATGTAA")) {
            System.out.println("test failed. Code 2.");
        }

        // DNA with “ATG” and multiple valid stop codons
        String test3 = findGene("TTAGATGATAGAATAGTGA");
        if (!test3.equals("ATGATAGAATAG")) {
            System.out.println("test failed. Code 3.");
        }

        // DNA with “ATG” and no valid stop codons
        String test4 = findGene("TATGATAATGGA");
        if (test4 != "") {
            System.out.println("test failed. Code 4.");
        }
        
        System.out.println("findGene test finished.");
        System.out.println("====================================");
    }
}

 