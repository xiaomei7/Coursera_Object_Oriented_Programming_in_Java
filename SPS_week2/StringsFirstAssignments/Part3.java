import edu.duke.*;

public class Part3 {
    public Boolean twoOccurrences (String stringa, String stringb) {
        /**
        *Input: String - stringa, stringb
        *Output: Boolean - 
        *   Ture (stringa appears at least twice in stringb) or
        *   False (otherwise)
        */

        if (stringb.indexOf(stringa) != -1) {
            int secondIndex = stringb.indexOf(stringa);
            if (stringb.indexOf(stringa, secondIndex + stringa.length()) != -1) {
                return true;
            }
        }

       return false;
    }

    public String lastPart (String stringa, String stringb) {
        /**
        *Input: String - stringa, stringb
        *Output: String - 
        *   the part of stringb that follows stringa if there is the first occurrence of stringa in stringb
        *   stringb if stringa does not occur in stringb
        */  
        
        if (stringb.indexOf(stringa) != -1) {
            int reIndex = stringb.indexOf(stringa);
            return stringb.substring(reIndex + stringa.length(), stringb.length());
        }
        
        return stringb;
    }

    public void testing () {
        System.out.println("================================================");
        System.out.println("Test of function twoOccurrences.");

        String teststringa1 = "by";
        String teststringb1 = "A story by Abby Long";
        System.out.println("Expect True and get " + twoOccurrences(teststringa1, teststringb1));
        System.out.println();

        String teststringa2 = "a";
        String teststringb2 = "banana";
        System.out.println("Expect True and get " + twoOccurrences(teststringa2, teststringb2));
        System.out.println();

        String teststringa3 = "atg";
        String teststringb3 = "ctgtatgta";
        System.out.println("Expect False and get " + twoOccurrences(teststringa3, teststringb3));
        System.out.println();

        System.out.println("================================================");
        System.out.println("Test of function lastPart.");

        String testlasta1 = "an";
        String testlastb1 = "banana";
        System.out.println("Expect 'ana' and get " + lastPart(testlasta1, testlastb1));
        System.out.println();

        String testlasta2 = "zoo";
        String testlastb2 = "forest";
        System.out.println("Expect 'forest' and get " + lastPart(testlasta2, testlastb2));
        System.out.println();

        
    }
}

 