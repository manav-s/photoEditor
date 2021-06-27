package controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.imageio.ImageIO;
import model.ILayer;
import model.ILayerModel;
import model.IPixel;
import model.Image;
import model.ImageRep;
import model.Pixel;

/**
 * This class represents the file actions that can be performed within the image processing model.
 * The class supports import and export of png, jpeg, and ppm filetypes to a model. Also, it is
 * capable of importing and exporting multilayer images (that will reside in a folder that has a
 * detailed text file).
 */
public class FileActions {

  /**
   * This will read an image of popular filetypes (ie. png or jpeg) without alpha channels. It will
   * turn it into an ImageRep so it can be utilized by the model, view, and controller.
   *
   * @param file the file where the image is located
   * @return the ImageRep that represents the given image in the model
   * @throws IOException if there is any error writing the data
   */
  public static ImageRep readImage(String file) throws IOException {
    try {
      FileInputStream fileStream = new FileInputStream(file);
      BufferedImage input = ImageIO.read(fileStream);
      fileStream.close();

      Pixel[][] arr = new Pixel[input.getHeight()][input.getWidth()];

      for (int i = 0; i < input.getHeight(); i++) {
        for (int j = 0; j < input.getWidth(); j++) {
          int colorCode = input.getRGB(j, i);
          Color color = new Color(colorCode);
          arr[i][j] = new Pixel(color.getRed(), color.getGreen(), color.getBlue());
        }
      }
      return new Image(arr);
    } catch (IOException err) {
      throw new IOException("Error reading image");
    }
  }


  /**
   * This will write an image of popular filetypes (ie. png or jpeg) without alpha channels. It will
   * take a 2D pixel array and turn it into a picture using the provided dimensions.
   *
   * @param name The name of the picture
   * @throws IOException if the image can't be written
   */
  public static void writeImage(ILayerModel model, String name) throws
      IOException, IllegalArgumentException {

    int rows = model.topVisible().getHeight();
    int columns = model.topVisible().getWidth();

    BufferedImage bufferedImage = new BufferedImage(columns, rows, BufferedImage.TYPE_INT_RGB);

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        Color color = new Color(model.topVisible().getPixelAt(i, j).getRed(),
            model.topVisible().getPixelAt(i, j).getGreen(),
            model.topVisible().getPixelAt(i, j).getBlue());
        bufferedImage.setRGB(j, i, color.getRGB());
      }
    }

    String ext = name.substring(name.indexOf(".") + 1);
    if (!ext.equals("png") || !ext.equals("jpeg")) {
      throw new IllegalArgumentException("Need to use png, ppm, or jpeg extension");
    }

    FileOutputStream output = new FileOutputStream(name);
    ImageIO.write(bufferedImage, ext, output);
    output.close();
  }

  /**
   * Turns the current image in the model to a PPM file on the disk. The name of the file will be
   * the name provided plus the ".ppm" extension.
   *
   * @param fileName the name of the new file
   * @param model    the model where the image lives as "current"
   * @throws IOException           if theres an error writing the file
   * @throws IllegalStateException if theres no image
   */
  public static void toPPM(String fileName, ILayerModel model) throws IOException,
      IllegalArgumentException {

    if (!fileName.endsWith(".ppm")) {
      throw new IllegalArgumentException("The name must end in .ppm");
    }

    Appendable appendable = new StringBuilder();

    if (model.getHeight() == 0 || model.getWidth() == 0) {
      throw new IllegalStateException("There is no image in the model");
    }

    try {
      appendable.append("P3\n" + model.getWidth() + " "
          + model.getHeight() + "\n" + "255\n");

      for (int i = 0; i < model.getHeight(); i++) {
        for (int j = 0; j < model.getWidth(); j++) {
          IPixel curr = model.getPixelAt(i, j);
          appendable.append(curr.getRed() + "\n");
          appendable.append(curr.getGreen() + "\n");
          appendable.append(curr.getBlue() + "\n");
        }
      }
    } catch (IOException err) {
      throw new IOException("Error appending to appendable");
    }

    BufferedWriter out = null;

    try {
      File file = new File(fileName);
      out = new BufferedWriter(new FileWriter(file));
      out.write(appendable.toString());
    } catch (IOException e) {
      throw new IOException("Error writing file");
    } finally {
      if (out != null) {
        out.close();
      }
    }
  }

  /**
   * Reads a PPM file off the disk and converts it to the ImageRep format that can be used by the
   * model, view or controller. The path of the file is provided in the String parameter.
   *
   * @param file the path of the file
   * @return an ImageRep that is usable by the model
   * @throws IOException              if there is an error writing the data
   * @throws IllegalArgumentException if the file is not found
   */
  public static ImageRep readPPM(String file) throws IOException, IllegalArgumentException {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(file));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("File not found");
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();

      if (s.length() > 0) {
        if (s.charAt(0) != '#') {
          builder.append(s + System.lineSeparator());
        }
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());
    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      throw new IllegalArgumentException("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    System.out.println("Height of image: " + height);
    Pixel[][] pixelArray = new Pixel[height][width];
    int maxValue = sc.nextInt();
    System.out.println("Maximum value of a color in this file (usually 255): " + maxValue);

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        Pixel pixel = new Pixel(r, g, b);
        pixelArray[i][j] = pixel;
      }
    }
    return new Image(pixelArray);
  }

  /**
   * Exports all the layers of the model in a custom folder as PPMs with a text file that details
   * the order of the layers.
   *
   * @param model the model where the layers reside
   * @throws IOException if there is an error writing the data
   */
  public static void exportAllLayers(String path, ILayerModel model) throws IOException {
    int numOfLayers = model.numPhoto();
    String result = "";

    // creates the folder for the files, and write result to it
    String time = ZonedDateTime.now(ZoneId.systemDefault())
        .format(DateTimeFormatter.ofPattern("uuuu.MM.dd.HH.mm.ss"));
    String folderName = time;
    File folder = new File(path + "/" + folderName);
    folder.mkdir();
    System.out.println("Folder created at " + path + ".");

    for (int i = 0; i < numOfLayers; i++) {
      result += model.getName(i) + '\n';
      model.setCurrentFromIndex(i);
      String fileName = path + "/" + folderName + "/" + model.getName(i) + ".ppm";
      toPPM(fileName, model);
    }

    Path textPath = Paths.get(path + "/" + folderName + "/output.txt");
    Files.writeString(textPath, result, StandardCharsets.UTF_8);
    System.out.println("Export completed.");
  }

  /**
   * Imports multiple layers from a text file.
   *
   * @param path  path of file
   * @param model model
   * @throws IOException if error reading file
   */
  public static void importLayers(String path, ILayerModel model) throws IOException {
    // navigate to the output.txt file
    File file = new File(path + "/output.txt");

    // make a list with every word
    Scanner input = new Scanner(file);

    List<String> in = new ArrayList<String>();

    while (input.hasNextLine()) {
      in.add(input.nextLine());
    }

    input.close();

    for (int i = in.size() - 1; i >= 0; i--) {
      String fileName = path + "/" + in.get(i) + ".ppm";
      ImageRep img = readPPM(fileName);
      model.addLayer(in.get(i));
      model.upload(img);
      System.out.println("Layer " + in.get(i) + " has been uploaded.");
    }
  }

  /**
   * This method takes the top visible layer of the model and creates a BufferedImage from the data
   * stored within the Pixels in the image of the layer.
   *
   * @param model the model where the data is retrieved from
   * @return the BufferedImage made from the data of the model provided
   */
  public static BufferedImage topVisibleBuffer(ILayerModel model) {
    ILayer topVisible = model.topVisible();
    int width = topVisible.getWidth();
    int height = topVisible.getHeight();

    if (height == 1 || width == 1) {
      BufferedImage bufferedImageEmpty = new BufferedImage(width, height,
          BufferedImage.TYPE_INT_RGB);
      Color color = new Color(0, 0, 0);
      bufferedImageEmpty.setRGB(0, 0, color.getRGB());
      return bufferedImageEmpty;
    } else {

      BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          Color color = new Color(model.topVisible().getPixelAt(i, j).getRed(),
              topVisible.getPixelAt(i, j).getGreen(),
              topVisible.getPixelAt(i, j).getBlue());
          bufferedImage.setRGB(j, i, color.getRGB());
        }
      }
      return bufferedImage;
    }
  }
}
