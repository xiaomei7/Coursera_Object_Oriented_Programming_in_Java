import edu.duke.*;

public class Part3 {
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

    public int countGenes (String dna) {
        /**
        *Input: String - dna
        *Output: int - number of genes found in dna
        */

        int startIndex = dna.indexOf("ATG");
        int minIndex = 0;
        int geneCount = 0;

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
                geneCount ++;
            }
            // if the gene does not exist, break out the loop
            else {
                break;
            }

            // update the position index and find the next gene
            startIndex = dna.indexOf("ATG", minIndex + 1);
        }

        return geneCount;
    }

    public void testCountGenes () {
        System.out.println("=================================================================");

        int test1 = countGenes("ATGTAAGATGCCCTAGT");
        if (test1 != 2) {
            System.out.println("Error 1.");
        }

        int test2 = countGenes("ATGTAAATGTGAATGTAG");
        if (test2 != 3) {
            System.out.println("Error 2.");
        }
        
        System.out.println("testCountGenes finished.");

        System.out.println("=================================================================");
        
    }
}

 