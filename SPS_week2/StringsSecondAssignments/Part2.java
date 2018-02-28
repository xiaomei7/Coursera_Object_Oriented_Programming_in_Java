import edu.duke.*;

public class Part2 {
    public int howMany (String stringa, String stringb) {
        /**
        *Input: String - stringa, stringb
        *Output: int - how many times stringa appears in stringb
        */

        // check the first occurrence of stringa in stringb
        // after the first occurrence, find the next one
        // repeat untill all the instances are found 
        int occurs = 0;
        int pos = 0;

        while (true) {
            if (stringb.indexOf(stringa, pos) != -1) {
                occurs++;
                pos = stringb.indexOf(stringa, pos) + stringa.length();
            }
            else {
                break;
            }

        }
        
        return occurs;
    }

    public void testHowMany () {
        System.out.println("=================================================================");

        int test1 = howMany("AA", "ATAAAA");
        if (test1 != 2) {
            System.out.println("Error 1.");
        }

        int test2 = howMany("GAA", "ATGAACGAATTGAATC");
        if (test2 != 3) {
            System.out.println("Error 2.");
        }
        
        System.out.println("testHowMany finished.");

        System.out.println("=================================================================");
        
    }
}

 