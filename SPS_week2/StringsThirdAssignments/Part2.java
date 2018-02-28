import edu.duke.*;

public class Part2 {
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
    
    public void testcgRatio () {
        System.out.println("=================================");
        double test1 = cgRatio("ATGCCATAG");
        System.out.println(test1);
    }

}

 