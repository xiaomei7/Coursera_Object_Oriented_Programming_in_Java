
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class coldestTemp {
	public CSVRecord coldestHourInFile(CSVParser parser) {
		/**
		*Input: CSVParser - parser
		*Output: CSVRecord 
		*	- with the coldest temperature in the file
		*/

		//start with coldestSoFar as nothing
		CSVRecord coldestSoFar = null;
		//For each row (currentRow) in the CSV File
		for (CSVRecord currentRow : parser) {
			// use method to compare two records
			coldestSoFar = getColdestOfTwo(currentRow, coldestSoFar);
		}
		//The coldestSoFar is the answer
		return coldestSoFar;
	}

	public void testColdestHourInFile () {
		FileResource fr = new FileResource("nc_weather/2014/weather-2014-05-01.csv");
		CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
		System.out.println("Coldest temperature was " + coldest.get("TemperatureF") +
				   " at " + coldest.get("DateUTC"));
	}

	public String fileWithColdestTemperature() {
		/**
		*Input - Nothing
		*Output - a string 
		*	that is the name of the file from selected files that has the coldest temperature
		*/
		DirectoryResource dr = new DirectoryResource();
		File coldestFile = null;
		// iterate over files 
		for (File f : dr.selectedFiles()) {
			coldestFile = getColdestOfTwoFile(f, coldestFile);
		}

		// the coldestSoFar is the anwser
		return coldestFile.getName();

	}

	public void testFileWithColdestTemperature() {
		String coldestFile = fileWithColdestTemperature();

		FileResource fr = new FileResource("nc_weather/2013/" + coldestFile);
		CSVRecord coldest = coldestHourInFile(fr.getCSVParser());
		System.out.println("Coldest day was in file " + coldestFile);
		System.out.println("Coldest temperature on that day was " + coldest.get("TemperatureF"));
		System.out.println("All the Temperatures on the coldest day were: ");
		for (CSVRecord currentRow : fr.getCSVParser()) {
			System.out.println(currentRow.get("DateUTC") + ": " + currentRow.get("TemperatureF"));
		}

	}
	
	public CSVRecord lowestHumidityInFile(CSVParser parser) {
		/**
		*Input: CSVParser - parser
		*Output: CSVRecord 
		*	- with the lowest humidity in the file
		*/

		CSVRecord lowestSoFar = null;
		//For each row (currentRow) in the CSV File
		for (CSVRecord currentRow : parser) {
			// use method to compare two records
			lowestSoFar = getLowestOfTwo(currentRow, lowestSoFar);
		}

		return lowestSoFar;

	}

	public void testLowestHumidityInFile() {
		FileResource fr = new FileResource("nc_weather/2014/weather-2014-07-22.csv");
		CSVParser parser = fr.getCSVParser();
		CSVRecord csv = lowestHumidityInFile(parser);

		System.out.println("Lowest Humidity was " + csv.get("Humidity") +
				   " at " + csv.get("DateUTC"));
	}

	public CSVRecord lowestHumidityInManyFiles() {
		/**
		*Input: Nothing
		*Output: CSVRecord - has the lowest humidity over all the files.
		*/

		CSVRecord lowestSoFar = null;
		DirectoryResource dr = new DirectoryResource();
		// iterate over files
		for (File f : dr.selectedFiles()) {
			FileResource fr = new FileResource(f);
			// use method to get largest in file.
			CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser());
			// use method to compare two records
			lowestSoFar = getLowestOfTwo(currentRow, lowestSoFar);
		}
		return lowestSoFar;
	}

	public void testLowestHumidityInManyFiles() {
		CSVRecord lowest = lowestHumidityInManyFiles();
		System.out.println("Lowest Humidity was " + lowest.get("Humidity") +
				   " at " + lowest.get("DateUTC"));
	}

	public double averageTemperatureInFile(CSVParser parser) {
		/**
		*Input: CSVParser - parser
		*Output: double - represents the average temperature in the file
		*/

		double totalTemp = 0.0;
		double totalRow = 0.0;

		for (CSVRecord currentRow : parser) {
			totalTemp += Double.parseDouble(currentRow.get("TemperatureF"));
			totalRow ++;
		}

		return totalTemp / totalRow;

	}

	public void testAverageTemperatureInFile() {
		FileResource fr = new FileResource("nc_weather/2013/weather-2013-08-10.csv");
		CSVParser parser = fr.getCSVParser();
		double avgTemp = averageTemperatureInFile(parser);

		System.out.println("Average temperature in file is " + avgTemp);

	}

	public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value) {
		/**
		*Input: 
		*	CSVParser - parser
		*	int - value: value of the humidity
		*Output: 
		*	double - the average temperature of only those temperatures when 
		*		the humidity was greater than or equal to value
		*/

		double totalTemp = 0.0;
		double totalRow = 0.0;
		// check humidity, if it's higher than or equal to value, add to totalTemp
		for (CSVRecord currentRow : parser) {
			if (Double.parseDouble(currentRow.get("Humidity")) >= value ) {
				totalTemp += Double.parseDouble(currentRow.get("TemperatureF"));
				totalRow ++;
			}
		}

		if (totalRow == 0.0) {
			return 0.0;
		}

		return totalTemp / totalRow;

	}

	public void testAverageTemperatureWithHighHumidityInFile() {
		FileResource fr = new FileResource("nc_weather/2013/weather-2013-09-02.csv");
		CSVParser parser = fr.getCSVParser();
		double avgTemp = averageTemperatureWithHighHumidityInFile(parser, 80);

		if (avgTemp == 0.0) {
			System.out.println("No temperatures with that humidity");
		}
		else {
			System.out.println("Average Temp when high Humidity is " + avgTemp);

		}

	}

	public CSVRecord getLowestOfTwo (CSVRecord currentRow, CSVRecord lowestSoFar) {
		if (lowestSoFar == null) {
			lowestSoFar = currentRow;
		}

		else {
			String currentHumidity = currentRow.get("Humidity");
			String lowestHumidity = currentRow.get("Humidity");

			if (currentHumidity.equals("N/A")) {
				return lowestSoFar;
			}
			else if (lowestHumidity.equals("N/A")) {
				lowestSoFar = currentRow;
			}

			else {
				double currentHu = Double.parseDouble(currentRow.get("Humidity"));
				double lowestHu = Double.parseDouble(lowestSoFar.get("Humidity"));
				if (currentHu < lowestHu) {
					lowestSoFar = currentRow;
				}
			}
			
		}
		return lowestSoFar;
	}


	public File getColdestOfTwoFile (File f, File coldestFile) {
		if (coldestFile == null) {
			coldestFile = f;
		}
		else {
			FileResource fr = new FileResource(f);
			CSVRecord currentRow1 = coldestHourInFile(fr.getCSVParser());

			FileResource coldestFr = new FileResource(coldestFile);
			CSVRecord currentRow2 = coldestHourInFile(coldestFr.getCSVParser());

			double temp1 = Double.parseDouble(currentRow1.get("TemperatureF"));
			double temp2 = Double.parseDouble(currentRow2.get("TemperatureF"));

			if (temp1 != -9999 && temp2 != -9999 && temp1 < temp2) {
				coldestFile = f;
			}

		}
		return coldestFile;
	}

	
	public CSVRecord getColdestOfTwo (CSVRecord currentRow, CSVRecord coldestSoFar) {
		//If coldestSoFar is nothing
		if (coldestSoFar == null) {
			coldestSoFar = currentRow;
		}
		//Otherwise
		else {
			double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
			double coldestTemp = Double.parseDouble(coldestSoFar.get("TemperatureF"));
			//Check if currentRow’s temperature < coldestSoFar's
			if (currentTemp < coldestTemp) {
				//If so update coldestSoFar to currentRow
				coldestSoFar = currentRow;
			}
		}
		return coldestSoFar;
	}

}
