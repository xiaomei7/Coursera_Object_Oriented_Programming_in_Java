import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
     public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }
    
    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        
        System.out.println("================================================");
        int numPoints = getNumPoints(s);
        System.out.println("There are " + numPoints + " points in the shape");
        
        double averageLen = getAverageLength(s);
        System.out.println("There average length of the shape is " + averageLen);
        
        double longestSide = getLargestSide(s);
        System.out.println("The longest side of the shape is " + longestSide);
        
        double largestX = getLargestX(s);
        System.out.println("The largest X of the shape is " + largestX);

        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        System.out.println("================================================");
    }
    
    public int getNumPoints (Shape s) {
        /**
         * Input: type Shape - s
         * Output: int - number of points in Shape s
         */
        int totalPoints = 0;
        for (Point p : s.getPoints()) {
            totalPoints ++;
        }
        return totalPoints;
    }

    public double getAverageLength(Shape s) {
        /**
         * Input: type Shape - s
         * Output: double - average of all the sides’ lengths in the Shape S
         */
        double averageLength = getPerimeter(s) / getNumPoints(s);
        return averageLength;
    }

    public double getLargestSide(Shape s) {
        /**
         * Input: type Shape - s
         * Output: double - longest sides in the Shape s
         */
        double longestSide = 0.0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);

            if (currDist > longestSide) {
                longestSide = currDist;
            }

        }
        return longestSide;
    }

    public double getLargestX(Shape s) {
        /**
         * Input: type Shape - s
         * Output: double - largest x value over all the points in the Shape s
         */
        double largestX = 0.0;
        for (Point p : s.getPoints()) {
            if (p.getX() > largestX) {
                largestX = p.getX();
            }
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        /**
         * Input: None
         * Output: double - the the largest perimeter over all the files selected
         */
        DirectoryResource dr = new DirectoryResource();
        
        double largestPer = 0.0;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double totalPer = getPerimeter(s);
            if (totalPer > largestPer) {
                largestPer = totalPer;
            }
        }
        return largestPer;
    }

    public String getFileWithLargestPerimeter() {
        /**
         * Input: None
         * Output: String - file name that has the largest perimeter
         */
        
        DirectoryResource dr = new DirectoryResource();
        
        File largestFile = null;
        double largestPer = 0.0;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double totalPer = getPerimeter(s);
            if (totalPer > largestPer) {
                largestPer = totalPer;
                largestFile = f;
            }
        }
        return largestFile.getName();
    }

    public void testPerimeterMultipleFiles() {
        System.out.println("================================================");
        double largestPer = getLargestPerimeterMultipleFiles();
        System.out.println("The largest perimeter of selected files is " + largestPer);
        System.out.println("================================================");
    }

    public void testFileWithLargestPerimeter() {
        System.out.println("================================================");
        String largestFile = getFileWithLargestPerimeter();
        System.out.println("The file that contains the largest perimeter of selected files is " + largestFile);
        System.out.println("================================================");
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        pr.testPerimeter();
    }
}
