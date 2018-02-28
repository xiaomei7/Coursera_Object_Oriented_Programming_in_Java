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

    public StorageResource getAllGenes (String dna) {
        /**
        *Input: String - dna
        *Output: StorageResource - list of genes
        */

        // create an empty StorageResource called geneList to store genes
        StorageResource geneList = new StorageResource();

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
                geneList.add(dna.substring(startIndex, minIndex + 3));
            }
            // if the gene does not exist, break out the loop
            else {
                break;
            }

            // update the position index and find the next gene
            startIndex = dna.indexOf("ATG", minIndex + 1);
        }
        return geneList;
    }

    public double cgRatio (String dna) {
        /*
        *Input: String - dna
        *Output: double - the ratio of C’s and G’s in dna
        *   as a fraction of the entire strand of DNA
        */

        // take dna and count the number of C and G, called cgCount
        double cgCount = 0;
        int posc = 0; 
        int posg = 0;

        while (true) {
            if (dna.indexOf('C', posc) != -1) {
                cgCount ++;
                posc = dna.indexOf('C', posc) + 1;
            }
            else {
                break;
            }
        }

        while (true) {
            if (dna.indexOf('G', posg) != -1) {
                cgCount ++;
                posg = dna.indexOf('G', posg) + 1;
            }
            else {
                break;
            }
        }
        // divide cgCount to the length of dna
        double cgratio = cgCount / dna.length();
        // return the ratio

        return cgratio;

    }

    public void testGetAllGenes () {
        System.out.println("=========================================");

        StorageResource test1 = getAllGenes("ATGTAAATGTAGATGTGA");
        for (String s : test1.data()) {
            System.out.println(s);
        }
        System.out.println();
    }

    
}

 