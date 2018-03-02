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
       
        dna = dna.toUpperCase();

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
            startIndex = dna.indexOf("ATG", minIndex + 3);
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

    public void processGenes (StorageResource sr) {
        /*
        *Input: StorageResource - sr
        *Output: 
        *   - print all the Strings in sr that are longer than 60 characters
        *   - print the number of Strings in sr that are longer than 60 characters
        *   - print the Strings in sr whose C-G-ratio is higher than 0.35
        *   - print the number of strings in sr whose C-G-ratio is higher than 0.35
        *   - print the length of the longest gene in sr
        */

        // create variables to count the number of strings are longer than 9 characters 
        //  or have CG ratio higher than 0,35
        int longGeneCount = 0;
        int highCgCount = 0;

        // create a variable to hold the length of the longest gene
        int longestGene = 0;
        
        int x =0;

        for (String s : sr.data()) {
            System.out.println(x + " " + s);
            // print all the Strings in sr that are longer than 60 characters
            if (s.length() > 60) {
                //System.out.println("Gene that longer than 60 characters: " + s);
                longGeneCount ++;
            }

            // print the Strings in sr whose C-G-ratio is higher than 0.35
            if (cgRatio(s) > 0.35) {
                //System.out.println("Gene that C-G-ratio is higher than 0.35: " + s);
                highCgCount ++;
            }

            // find the longest gene and store the length
            if (s.length() > longestGene) {
                longestGene = s.length();
            }
            
            x++;
        }

        // print the number of Strings in sr that are longer than 60 characters
        System.out.println("The number of Strings in sr that are longer than 60 characters: " + longGeneCount);
        // print the number of strings in sr whose C-G-ratio is higher than 0.35
        System.out.println("The number of strings in sr whose C-G-ratio is higher than 0.35: " + highCgCount);
        // print the length of the longest gene in sr
        System.out.println("The length of the longest gene in sr: " + longestGene);
    }

    public void testGetAllGenes () {
        System.out.println("=========================================");

        StorageResource test1 = getAllGenes("xATGATGTAAyyATGxxTAGyATGxxxyyyTGAATG");
        System.out.println("The number of genes: " + test1.size());
        for (String s : test1.data()) {
            System.out.println(s);
        }
        System.out.println();
    }

    public void testProcessGenes () {
        System.out.println("==========================================");
        // one DNA string that has some genes longer than 9 characters
        System.out.println("test1: ");
        StorageResource test1 = new StorageResource();
        test1.add("ATGxxxyyyxxxTAA");
        processGenes(test1);
        System.out.println("------------------------------------------");

        // one DNA string that has no genes longer than 9 characters
        System.out.println("test2: ");
        StorageResource test2 = new StorageResource();
        test2.add("ATGTGA");
        processGenes(test2);
        System.out.println("------------------------------------------");

        // one DNA string that has some genes whose C-G-ratio is higher than 0.35
        System.out.println("test3: ");
        StorageResource test3 = new StorageResource();
        test3.add("ATGGCATAG");
        processGenes(test3);
        System.out.println("------------------------------------------");

        // one DNA string that has some genes whose C-G-ratio is lower than 0.35
        System.out.println("test4: ");
        StorageResource test4 = new StorageResource();
        test4.add("ATGTTATAG");
        processGenes(test4);
        System.out.println("------------------------------------------");

        // mix
        System.out.println("test5: ");
        StorageResource test5 = new StorageResource();
        test4.add("ATGxxxyyyxxxTAA");
        test4.add("ATGTGA");
        test4.add("ATGGCATAG");
        test4.add("ATGTTATAG");
        test4.add("ATGTCATATAA");
        test4.add("ATGTCGGCTCCGTGA");
        processGenes(test4);
        System.out.println("------------------------------------------");
    }

    public void testAcessFile () {
        System.out.println("==========================================");

        FileResource fr = new FileResource("GRch38dnapart.fa");
        String dna = fr.asString();
        
        StorageResource genes = getAllGenes(dna);
        processGenes(genes);
        
        int ctgCount = 0;
        int startPos = dna.indexOf("CTG");
        while (startPos != -1) {
            startPos = dna.indexOf("CTG", startPos + 1);
            ctgCount ++;

        }
        System.out.println("CTG appears: " + ctgCount + " times");
        System.out.println("The number of genes total: " + genes.size());
    }
    
}

 