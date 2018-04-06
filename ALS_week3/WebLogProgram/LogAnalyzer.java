
/**
 * Write a description of class LogAnalyzer here.
 * 
 * @author Xiaomei Wang
 * @version (a version number or a date)
 */

import java.util.*;
import edu.duke.*;

public class LogAnalyzer
{
    private ArrayList<LogEntry> records;
    private ArrayList<String> uniqueIps;
     
    public LogAnalyzer() {
        // initialize records to an empty ArrayList
        records = new ArrayList<LogEntry>();
        uniqueIps = new ArrayList<String>();
    }
        
    public void readFile(String filename) {
        // create a FileResource
        FileResource fr = new FileResource(filename);
        // iterate over all the lines in the file
        for (String line : fr.lines()) {
            // For each line, create a LogEntry and store it in the records ArrayList
            LogEntry le = WebLogParser.parseEntry(line);
            records.add(le);
        }  
    }
        
    public void printAll() {
        for (LogEntry le : records) {
            System.out.println(le);
        }
    }

    public int countUniqueIPs () {
        /**
        *Output:
        *   return an integer representing the number of unique IP addresses
        *   assume that the instance variable records already has its ArrayList of Strings read in from a file
        */

        // access records, for each LogEntry in records
        for (LogEntry le : records) {
            // access ip address of each LogEntry using getIpAddress
            String currentIp = le.getIpAddress();
            // check ip the ip address exists in uniqueIps
            // if the ip address does not exist, add the ip to uniqueIps
            if (!uniqueIps.contains(currentIp)) uniqueIps.add(currentIp);            
            // else, pass
        }
        // return uniqueIps.size()
        return uniqueIps.size();
    }    

    public void printAllHigherThanNum (int num) {
        /**
        *Input:
        *   int - num
        *Output:
        *   examine all the web log entries in records and 
        *   PRINT those LogEntrys that have a status code greater than num
        */

        // for every LogEntry in records
        for (LogEntry le : records) {
            // get the status code using getStatusCode
            int currentCode = le.getStatusCode();
            // check if the status code is greater than num
            if (currentCode > num) {
                // if it is, print the LogEntry
                System.out.println(le);
            } 
        }  
    } 

    public ArrayList<String> uniqueIPVisitsOnDay (String someday) {
        /**
        *Input:
        *   String - someday: foramt "MMM DD" such as "Apr 22"
        *Output:
        *   ArrayList<String>: unique IP addresses that had access on the given day
        */

        // create an ArrayList to store the unique ip address on a given day
        ArrayList<String> uniqueIpDay = new ArrayList<String>();
        // for every LogEntry in records, get the Date using getAccessTime
        for (LogEntry le : records) {
            // convert Date object to String using toString()
            String dayFormat = le.getAccessTime().toString().substring(4, 10);
            // check if the date in the log matches someday 
            if (dayFormat.equals(someday)) {
                // if it matches, check if the ip address are inside uniqueIpDay
                if (!uniqueIpDay.contains(le.getIpAddress())) {
                    // if not, add the ip
                    uniqueIpDay.add(le.getIpAddress());
                }
            }
        }
        // return the uniqueIpDay
        return uniqueIpDay;
    }

    public int countUniqueIPsInRange (int low, int high) {
        /**
        *Input:
        *   int - low
        *   int - high
        *Output:
        *   int: number of unique IP addresses in records
        *   that have a status code in the range from low to high, inclusive
        */

        // create an ArrayList to store the unique IP addresses that are in range
        ArrayList<String> uniqueIpRange = new ArrayList<String>();
        // for every LogEntry in records 
        for (LogEntry le : records) {
            // get the status code using getStatusCode()
            int currentCode = le.getStatusCode();
            // check if the status code is in range of (low, high)
            if (currentCode >= low && currentCode <= high) {
                // if the code is in range, check if the ip already exists in uniqueIpRange
                if (!uniqueIpRange.contains(le.getIpAddress())) {
                    // if not, add the ip
                    uniqueIpRange.add(le.getIpAddress());
                }
            } 
        }
        // return the size of uniqueIpRange
        return uniqueIpRange.size();
    }

    public HashMap<String, Integer> countVisitsPerIP () {
        /**
        *Output:
        *   HashMap<String, Integer>: maps an IP address to the number of times that IP address appears in records, 
        *   meaning the number of times this IP address visited the website
        */

        // create a HashMap visitedIpTimes to store ips and visited times
        HashMap<String, Integer> visitedIpTimes = new HashMap<String, Integer>();
        // for every LogEntry in records
        for (LogEntry le : records) {
            // extract the ip, check if the ip is in visitedIpTimes 
            String currentIp = le.getIpAddress();
            if (!visitedIpTimes.containsKey(currentIp)) {
                // if not, add the ip and count 1
                visitedIpTimes.put(currentIp, 1);
            }
            else {
                // else, update the ip with count + 1
                visitedIpTimes.put(currentIp, visitedIpTimes.get(currentIp) + 1);
            }
        }
        // return the HashMap
        return visitedIpTimes;
    }

    public int mostNumberVisitsByIP (HashMap<String, Integer> ipCount) {
        /**
        *Input:
        *   HashMap<String, Integer> - ipCount:
        *       maps an IP address to the number of times that IP address appears in the web log file
        *Output:
        *   int: the maximum number of visits by a single IP address
        */

        // set the maxVisit by 0
        int maxVisit = 0;
        // for every ip in ipCount
        for (String ip : ipCount.keySet()) {
             // check the count, if the count > maxVisit, replace maxVisit with count
            if (ipCount.get(ip) > maxVisit) maxVisit = ipCount.get(ip);
        }
        // return maxVisit
        return maxVisit;
    }

    public ArrayList<String> iPsMostVisits (HashMap<String, Integer> ipCount) {
        /**
        *Input:
        *   HashMap<String, Integer> - ipCount:
        *       maps an IP address to the number of times that IP address appears in the web log file
        *Output:
        *   ArrayList<String>: IP addresses that all have the maximum number of visits
        */

        // create an ArrayList maxVisitIps
        ArrayList<String> maxVisitIps = new ArrayList<String>();
        // pass ipCount to mostNumberVisitsByIP to get the maxVisit
        int maxVisit = mostNumberVisitsByIP(ipCount);
        // for every value in ipCount, check if it equals maxVisit
        for (String ip : ipCount.keySet()) {
            // if it is, add the key to maxVisitIps
            if (ipCount.get(ip) == maxVisit) maxVisitIps.add(ip);
        }
        // return the ArrayList
        return maxVisitIps;
    }

    public HashMap<String, ArrayList<String>> iPsForDays () {
        /**
        *Output:
        *   HashMap<String, ArrayList<String>>: 
        *       uses records and maps days from web logs to an ArrayList of IP addresses
        *       that occurred on that day (including repeated IP addresses)
        *       day formatted "MMM DD" such as "Apr 22"
        */

        // create a HashMap to map days to ArrayLists of IP addresses
        HashMap<String, ArrayList<String>> dayIps = new HashMap<String, ArrayList<String>>();
        // for each LogEntry in records, get date (convert to formatted) and ip 
        for (LogEntry le : records) {
            String currentDay = le.getAccessTime().toString().substring(4, 10);
            String currentIp = le.getIpAddress();
            // for each date, check if it's in the HashMap
            if (!dayIps.containsKey(currentDay)) {
                // if not, add a new date and the current ip
                ArrayList<String> ips = new ArrayList<String>();
                ips.add(currentIp);
                dayIps.put(currentDay, ips);
            }
            else {
                // else, update ip
                ArrayList<String> ips = dayIps.get(currentDay);
                ips.add(currentIp);
                dayIps.put(currentDay, ips);
            }
        }
        // return the HashMap
        return dayIps;
    }

    public String dayWithMostIPVisits (HashMap<String, ArrayList<String>> dayIps) {
        /**
        *Input:
        *   HashMap<String, ArrayList<String>> - dayIps: 
        *       maps days from web logs to an ArrayList of IP addresses
        *       that occurred on that day (including repeated IP addresses)
        *       day formatted "MMM DD" such as "Apr 22"
        *Output:
        *   String: the day that has the most IP address visits
        */

        // create a String to store most visits day
        String maxDay = "";
        // create an int to store max visit
        int maxVisit = 0;
        // for every key in dayIps, check the size of the value
        for (String day : dayIps.keySet()) {
            // if the size of the value > max visit, replace max visit with size of the value
            if (dayIps.get(day).size() > maxVisit) {
                maxVisit = dayIps.get(day).size();
                // also replace max day with key
                maxDay = day;
            }
        }     
        // return max day
        return maxDay;
    }

    public ArrayList<String> iPsWithMostVisitsOnDay (
        HashMap<String, ArrayList<String>> dayIps, String day) {
        /**
        *Input:
        *   HashMap<String, ArrayList<String>> - dayIps: 
        *       maps days from web logs to an ArrayList of IP addresses
        *       that occurred on that day (including repeated IP addresses)
        *       day formatted "MMM DD" such as "Apr 22"
        *   String - day: a day in the format “MMM DD”
        *Output:
        *   ArrayList<String>: IP addresses that had the most accesses on the given day
        */

        // in dayIps, use day to access all the ips visited that day
        ArrayList<String> ipInDay = dayIps.get(day);
        // create a HashMap, map ip to count, called ipCountDay
        HashMap<String, Integer> ipCountDay = new HashMap<String, Integer>();
        // for every ip in the day, check if it's in ipCountDay
        for (String ip : ipInDay) {
            // if not, add the ip and count 1
            if (!ipCountDay.containsKey(ip)) ipCountDay.put(ip, 1);
            // else, add the count by 1
            else ipCountDay.put(ip, ipCountDay.get(ip) + 1); 
        }
        // use iPsMostVisits to get an ArrayList of IP addresses that 
        ArrayList<String> mostVisitIps = iPsMostVisits(ipCountDay);
        //  all have the maximum number of visits and return it
        return mostVisitIps;
    }
}
