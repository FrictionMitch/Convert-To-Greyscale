import edu.duke.*;
import java.io.File;
import java.io.Console;

public class GreyscaleConverter {

    public ImageResource makeGrey(ImageResource inImage) {
        //Create a blank image the same size of Image you want to convert
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());

        // For each Pixel in outImage...
        for (Pixel pixel : outImage.pixels()) {
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());

            int average = ((inPixel.getRed() + inPixel.getGreen() + inPixel.getBlue()) / 3);

            pixel.setRed(average);
            pixel.setGreen(average);
            pixel.setBlue(average);

        }
        return outImage;
    }

    public ImageResource makeNegative(ImageResource inImage) {
        ImageResource negativeImage = new ImageResource(inImage.getWidth(), inImage.getHeight());

        for (Pixel pixel : negativeImage.pixels()) {
            int x = pixel.getX();
            int y = pixel.getY();
            Pixel inPixel = inImage.getPixel(x, y);

            pixel.setRed(255 - inPixel.getRed());
            pixel.setGreen(255 - inPixel.getGreen());
            pixel.setBlue(255 - inPixel.getBlue());
        }
        return negativeImage;
    }

    public void doSave() {
        DirectoryResource dir = new DirectoryResource();
        for (File file : dir.selectedFiles()) {
            ImageResource image = new ImageResource(file);
            String fileName = image.getFileName();
            String newName = "Copy_of_" + fileName;
//            image.setFileName(newName);
//            file.getParentFile();
            image.setFileName(file.getParentFile() + "/" + newName);
            image.draw();
            image.save();
        }
    }

    public void selectAndConvert() {
        DirectoryResource dir = new DirectoryResource();
        for (File file : dir.selectedFiles()) {
            ImageResource inImage = new ImageResource(file);
            ImageResource grey = makeGrey(inImage);
            String fileName = inImage.getFileName();
            String newName = "grey_" + fileName;
            grey.setFileName(newName);
            grey.save();
            grey.draw();

        }
    }

    public void negativeConvert() {
        DirectoryResource dir = new DirectoryResource();
        for(File file : dir.selectedFiles()) {
            ImageResource inImage = new ImageResource(file);
            ImageResource negative = makeNegative(inImage);
            File desktop = new File(System.getProperty("user.home"), "Desktop");
            String fileName = inImage.getFileName();
            String newName = "negative_"+fileName;
//            negative.setFileName(file.getParentFile() + "/" + newName);
//            negative.setFileName(newName);
            negative.setFileName(desktop + "/DukeImages/" + newName);
            negative.draw();
            negative.save();
        }
    }
}
