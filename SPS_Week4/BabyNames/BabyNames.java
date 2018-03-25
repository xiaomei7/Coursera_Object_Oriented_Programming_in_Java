/**
 * Print out total number of babies born, as well as for each gender, in a given CSV file of baby name data.
 * 
 * @author Xiaomei Wang
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
import java.util.*;

public class BabyNames {
    public void totalNames (FileResource fr) {
        int totalNames = 0;
        int totalBoysName = 0;
        int totalGirlsName = 0;

        for (CSVRecord rec : fr.getCSVParser(false)) {
            totalNames ++;
            if (rec.get(1).equals("M")) {
                totalBoysName ++;
            }
            else {
                totalGirlsName ++;
            }
        }
        System.out.println("total names = " + totalNames);
        System.out.println("female girls' name = " + totalGirlsName);
        System.out.println("male boys' name = " + totalBoysName);
    }

    public int getRank (int year, String name, String gender) {
        /**
        *Input:
        *   int - year: the year to access
        *   String - name: the name to search
        *   String - gender: the gender of the name to search
        *Output:
        *   int - Rank of the name in the file for the given gender.
        *   If the name is not in the file, then -1 is returned. 
        */

        int totalGirlsName = 0;
        int totalRow = 0;

        // use the year to find which file to access
        String yearf = Integer.toString(year);
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob" + yearf + ".csv");
        // open the file, loop over all the rows
        for (CSVRecord rec : fr.getCSVParser(false)) {
            totalRow ++;

            if (rec.get(1).equals("F")) {
                totalGirlsName ++;
            }

            // if the gender is F, then the line number is the anwser
            if (name.equals(rec.get(0)) && 
                gender.equals(rec.get(1)) && 
                rec.get(1).equals("F")) {
                return totalRow;
            }
            // if the gender is M, then the line number - numGirlsRank is the anwser
            else if (name.equals(rec.get(0)) && 
                gender.equals(rec.get(1)) && 
                rec.get(1).equals("M")){
                    return totalRow - totalGirlsName;
            }   
        }  
        return -1;
    }

    public String getName (int year, int rank, String gender) {
        /**
        *Input:
        *   int - year: the year to access
        *   int - rank: the rank to look for
        *   String - gender: the gender to search
        *Output:
        *   String - the name of the person 
        *   in the file(year) at this rank, for the given gender.
        *   Return "NO NAME" when no rank is found.
        */

        int totalGirlsName = 0;
        int totalRow = 0;

        // use year to find which file to access
        String yearf = Integer.toString(year);
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob" + yearf + ".csv");
        // loop over the file
        for (CSVRecord rec : fr.getCSVParser(false)) {
            totalRow ++;

            if (rec.get(1).equals("F")) {
                totalGirlsName ++;
            }

            // if the gender is F, match rank with row number
            if (gender.equals(rec.get(1)) && 
                rec.get(1).equals("F") && 
                totalRow == rank) {
                return rec.get(0);
            }
            // if the gender is M, match rank with row number - numGirlsRank
            else if (gender.equals(rec.get(1)) && 
                rec.get(1).equals("M") && 
                (totalRow - totalGirlsName) == rank) {
                return rec.get(0);
            }
        }
        return "NO NAME";
    }

    public void whatIsNameInYear (String name, int year, int newYear, String gender) {
        /**
        *Input:
        *   String - name: the name to search
        *   int - year: the original year that name was born
        *   int - newYear: the year to match with new name
        *   String - gender: the gender to search
        *Output:
        *   Print - (name) born in (year) would be (newName) if (she / he) was born in (newYear).
        */

        String genderS = "she";

        if (gender.equals("M")) {
            genderS = "he";
        }

        // in oldFile, find out the rank of the name
        int rank = getRank(year, name, gender);
        // in newFile, use rank to find the newName
        String newName = getName(newYear, rank, gender);
        if (rank == -1 || newName.equals("NO NAME")) {
            System.out.println("NO RESULT FOUND.");
        }
        else {
            // print out the information.
            System.out.println(name + " born in " + year + " would be " + newName + 
            " if " + genderS + " was born in " + newYear);
        }
    }

    public int yearOfHighestRank (String name, String gender) {
        /**
        *Input:
        *   String - name: the name to search
        *   String - gender: the gender to search
        *Output:
        *   int - the year with the highest rank for the name and gender
        *   within multiple files.
        *   Return -1 if the name and gender are not in any of the selected files.
        */

        // choose files to search
        DirectoryResource dr = new DirectoryResource();
        // initial highestRank to store the highest rank for the name
        int highestRank = 0;
        int highestYear = 0;
        int currentRank = 0;
        int year = 0;
        // loop over all the files
        for (File f : dr.selectedFiles()) {   
            year = getYear(f);
            currentRank = getRank(year, name, gender);
          
            // if the highestRank is 0, then use the rank in the first file 
            if (highestRank == 0 && currentRank != -1) {
                highestRank = currentRank;
                highestYear = year;
            }
            // for every loop, compare highestRank with currentRank, keep the higher one
            else if (currentRank != -1 && currentRank < highestRank) {
                highestRank = currentRank;
                highestYear = year;
            }
        }
        // reuturn highest Rank
        if (highestRank == 0) {
            return -1;
        }
        return highestYear;
    }

    public double getAverageRank (String name, String gender) {
        /**
        *Input:
        *   String - name: the name to search
        *   String - gender: the gender to search
        *Output:
        *   double - representing the average rank of the name and gender over the selected files.
        *   Return -1.0 if the name is not ranked in any of the selected files.
        */

        // select files 
        DirectoryResource dr = new DirectoryResource();
        int currentRank = 0;
        double rankCount = 0;
        double totalRank = 0;
        int year = 0;

        // loop each file
        for (File f : dr.selectedFiles()) {
            // record the rank, the file counted 
            year = getYear(f);
            currentRank = getRank(year, name, gender);
            // if the rank does not exists, skip
            if (currentRank != -1) {
                rankCount ++;
                totalRank += currentRank;
            }
        }
        
        // return the average rank
        if (totalRank == 0) {
            return -1.0;
        }
        return totalRank / rankCount;
    }

    public int getTotalBirthsRankedHigher (int year, String name, String gender) {
        /**
        *Input:
        *   int - year: the year to access
        *   String - name: the name to search
        *   String - gender: the gender of the name to search
        *Output:
        *   int - the total number of births of those names with the same gender 
        *   and same year who are ranked higher than name
        */

        // use the year to find which file to access
        String yearf = Integer.toString(year);
        FileResource fr = new FileResource("us_babynames/us_babynames_by_year/yob" + yearf + ".csv");

        int totalBoys = 0;
        int totalGirls = 0;
        int numBorn = 0;
        // open the file, loop over all the rows
        for (CSVRecord rec : fr.getCSVParser(false)) {
            numBorn = Integer.parseInt(rec.get(2));

            // if the gender is F, then the totalGirls is the anwser
            if (name.equals(rec.get(0)) && 
                gender.equals(rec.get(1)) && 
                rec.get(1).equals("F")) {
                return totalGirls;
            }
            // if the gender is M, then the totalBoys is the anwser
            else if (name.equals(rec.get(0)) && 
                gender.equals(rec.get(1)) && 
                rec.get(1).equals("M")){
                return totalBoys;
            }

            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
            }
            else {
                totalGirls += numBorn;
            }
        }  
        return -1;
    }

    public int getYear (File f) {
        /**
        *Input:
        *   File - f: the file to extract year
        *Output:
        *   Int - given a file, extract what year of data it contains
        */

        String fileName = f.getName();
        String year = "";
        int yearI = 0;

        for(int i=0; i<fileName.length(); i++) {  
                if(fileName.charAt(i) >= 48 && fileName.charAt(i) <= 57) {  
                    year += fileName.charAt(i);  
                }  
        }
        yearI = Integer.parseInt(year);
        return yearI;
    }

    public void testTotalBirths () {
        //FileResource fr = new FileResource();
        FileResource fr = new FileResource();
        totalNames(fr);
    }
}
