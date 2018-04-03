
import edu.duke.*;
import java.util.*;

public class CharactersInPlay
{
    // create two private ArrayLists
    // store the the names of the characters find
    private ArrayList<String> characters;
    // store the corresponding counts for each character
    private ArrayList<Integer> appearCount;

    public CharactersInPlay () {
        characters = new ArrayList<String>();
        appearCount = new ArrayList<Integer>();

    }

    private void update (String person) {
        /**
        *Input: String - person
        *Output:
        *   update the two ArrayLists, 
        *   adding the character’s name if it is not already there, 
        *   and counting this line as one speaking part for this person.
        */
       
        // if the person is not in character, add it 
        if (!characters.contains(person)) {
            characters.add(person);
            appearCount.add(1);
        }
        // if the person is in character, update corresponding appearCount
        else {
            int idx = characters.indexOf(person);
            int freq = appearCount.get(idx);
            appearCount.set(idx, freq + 1);
        }
    }

    private void findAllCharacters () {
        // clear the appropriate instance variables before each new file 
        characters.clear();
        appearCount.clear();
        // opens a file
        FileResource fr = new FileResource();
        // reads the file line-by-line
        // For each line, if there is a period on the line, extract the possible name of the speaking part
        for (String line : fr.lines()) {
            int periodIdx = line.indexOf(".");
            if (periodIdx != -1) {
                String characterName = line.substring(0, periodIdx);
                // call update to count it as an occurrence for this person
                update(characterName);
            }
        }
    } 

    private void charactersWithNumParts (int num1, int num2) {
        // assume num1 should be less than or equal to num2
        // print out the names of all those characters that have exactly number speaking parts
        // where number is greater than or equal to num1 and less than or equal to num2
        for (int i = 0; i < characters.size(); i++) {
            if (appearCount.get(i) >= num1 && appearCount.get(i) <= num2) {
                System.out.println(characters.get(i) + "\t" + appearCount.get(i));
            }
        }   
    }

    public void tester () {
        System.out.println("===============================");
        // call findAllCharacters
        findAllCharacters();
        // System.out.println("characters size: " + characters.size());
        // System.out.println("appearCount size: " + appearCount.size());
        // for each main character, print out the main character, 
        // followed by the number of speaking parts that character has
        for (int i = 0; i < characters.size(); i++) {
            if (appearCount.get(i) > 50) {
                System.out.println(characters.get(i) + "\t" + appearCount.get(i));
            }
        }
        // test charactersWithNumParts
        System.out.println("--------------------------------");
        charactersWithNumParts(10, 15);
    }
    
}
