import edu.duke.*;

public class Part3 {
    public void processGenes (StorageResource sr) {
        /*
        *Input: StorageResource - sr
        *Output: 
        *   - print all the Strings in sr that are longer than 9 characters
        *   - print the number of Strings in sr that are longer than 9 characters
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

        for (String s : sr.data()) {
            // print all the Strings in sr that are longer than 9 characters
            if (s.length() > 9) {
                System.out.println("Gene that longer than 9 characters: " + s);
                longGeneCount ++;
            }

            // print the Strings in sr whose C-G-ratio is higher than 0.35
            if (cgRatio(s) > 0.35) {
                System.out.println("Gene that C-G-ratio is higher than 0.35: " + s);
                highCgCount ++;
            }

            // find the longest gene and store the length
            if (s.length() > longestGene) {
                longestGene = s.length();
            }
        }

        // print the number of Strings in sr that are longer than 9 characters
        System.out.println("The number of Strings in sr that are longer than 9 characters: " + longGeneCount);
        // print the number of strings in sr whose C-G-ratio is higher than 0.35
        System.out.println("The number of strings in sr whose C-G-ratio is higher than 0.35: " + highCgCount);
        // print the length of the longest gene in sr
        System.out.println("The length of the longest gene in sr: " + longestGene);
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

}

 