import edu.duke.*;
import org.apache.commons.csv.*;

public class exports {

	public void tester() {
		FileResource fr = new FileResource();
		CSVParser parser = fr.getCSVParser();

		System.out.println("Test countryInfo.");
		System.out.println(countryInfo(parser, "Nauru"));
		System.out.println("------------------------------");

		System.out.println("Test listExportersTwoProducts.");
		parser = fr.getCSVParser();
		listExportersTwoProducts(parser, "cotton", "flowers");
		System.out.println("------------------------------");

		System.out.println("Test numberOfExporters.");
		parser = fr.getCSVParser();
		System.out.println(numberOfExporters(parser, "cocoa"));
		System.out.println("------------------------------");

		System.out.println("Test bigExporters.");
		parser = fr.getCSVParser();
		bigExporters(parser, "$999,999,999,999");
		System.out.println("------------------------------");

		System.out.println("==============================");

	}

	public String countryInfo (CSVParser parser, String country) {
		/**
		*Input: CSVParser - parser, String - country 
		*Output: 
		*	String - information about the country or 
		*	“NOT FOUND” - if there is no information about the country. 
		*/

		// get iterable information from parser by each header it has 
		for (CSVRecord line : parser) {
			if (line.get("Country").equals(country)) {
				// formatted the information in required format into a String 
				// return the formatted String
				return country + ": " + line.get("Exports") + ": " + line.get("Value (dollars)");
			}
		}
		
		// return "NOT FOUND" if no matches 
		return "NOT FOUND";
	}

	public void listExportersTwoProducts (
		CSVParser parser, String exportItem1, String exportItem2) {

		/**
		*Input: CSVParser - parser, String - exportItem1, String - exportItem2
		*Output: prints the names of all the countries 
		*	that have both exportItem1 and exportItem2 as export items
		*/

		// iterate all the lines
		for (CSVRecord line : parser) {
			// find out which line(country) has the exportItem1 nad exportItem2
			if (line.get("Exports").contains(exportItem1) && 
				line.get("Exports").contains(exportItem2)) {
				// if the line fit the requirement, print it out
				System.out.println(line.get("Country"));
			}
		}
	}

	public int numberOfExporters (CSVParser parser, String exportItem) {
		/**
		*Input: CSVParser - parser, String - exportItem
		*Output: int - the number of countries that export exportItem
		*/

		// create a count to hold the number of countries that export exportItem
		int exportCountries = 0;
		// iterate all the lines in the parser
		for (CSVRecord line : parser) {
			// find out if the line contains the exportItem
			if (line.get("Exports").contains(exportItem)) {
				// if the line contains the exportItem, count + 1
				exportCountries ++;
			}
		}

		// return the count 
		return exportCountries;
	}

	public void bigExporters (CSVParser parser, String amount) {
		/**
		*Input: CSVParser - parser, String - amount
		*Output: prints the names of countries and their Value amount 
		*	for all countries whose Value (dollars) string is longer than the amount string
		*/

		// iterate all the lines in the parser
		for (CSVRecord line : parser) {
			// check if the length of the value in column "Value (dollars)" is longer
			//  than the length of the amount
			if (line.get("Value (dollars)").length() > amount.length()) {
				// if it is print out the Country and Value (dollars)
				System.out.println(line.get("Country") + " " + line.get("Value (dollars)"));

			}
		}
	}
}