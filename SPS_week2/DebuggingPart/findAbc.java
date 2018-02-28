import edu.duke.*;

public class findAbc {
    public void findAbc(String input) {
        int index = input.indexOf("abc");
        while (true) {
            if (index == -1 || index >= input.length() - 3) {
                break;
            }
            
            System.out.println("index " + index);
            String found = input.substring(index+1, index+4);
            System.out.println(found);
            index = input.indexOf("abc", index+3);
            System.out.println("index after updating " + index);
        }
    }
       public void test() {
        System.out.println("==============================");
        //no code yet
        //findAbc("abcd");
        //findAbc("abcdabc");
        
        // note, the 2nd index could be the length of the string
        // System.out.println("abcabc".substring(3, 6));

        // bcd, bca, bca
        // findAbc("abcdkfjsksioehgjfhsdjfhksdfhuwabcabcajfieowj");
        
        findAbc("abcabcabcabca");
        System.out.println("==============================");

    }
}

 