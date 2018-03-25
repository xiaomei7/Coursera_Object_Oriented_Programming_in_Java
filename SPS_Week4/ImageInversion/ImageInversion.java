/**
 * Convert any number of images to its invert version.
 * 
 * @author Xiaomei Wang
 */
import edu.duke.*;
import java.io.*;

public class ImageInversion {
	//I started with the image I wanted (inImage)
	public ImageResource makeInvert(ImageResource inImage) {
		//I made a blank image of the same size
		ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
		//for each pixel in outImage
		for (Pixel pixel: outImage.pixels()) {
			//look at the corresponding pixel in inImage
			Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
			//compute outImage's rbg by 255 - inPixel's red || inPixel's blue || inPixel's green
			int outRed = 255 - inPixel.getRed();
			int outBlue = 255 - inPixel.getBlue();
			int outGreen = 255 - inPixel.getGreen();
			//set pixel's red to outRed
			pixel.setRed(outRed);
			//set pixel's blue to outBlue
			pixel.setBlue(outBlue);
			//set pixel's green to outGrenn
			pixel.setGreen(outGreen);
		}
		//outImage is your answer
		return outImage;
	}

	public void selectAndInvert () {
		DirectoryResource dr = new DirectoryResource();
		for (File f : dr.selectedFiles()) {
			ImageResource inImage = new ImageResource(f);
			ImageResource invert = makeInvert(inImage);

			String fname = inImage.getFileName();
			String newName = "inverted-" + fname;
			invert.setFileName(newName);
			invert.draw();
			invert.save();
		}
	}

}
