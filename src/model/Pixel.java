package model;

/**
 * Represents a single pixel in an image. Each pixel has a position (row, column) and a color made
 * up of RGB integers.
 */
public class Pixel implements IPixel {

  private int red;
  private int green;
  private int blue;

  /**
   * Creates a pixel with a row, column, and color that is made up of RGB values.
   *
   * @param red   the red int value
   * @param green the green int value
   * @param blue  the blue int value
   * @throws IllegalArgumentException if the row or column is less than 0, or the red, green, or
   *                                  blue values are less than 0 or greater than 255.
   */
  public Pixel(int red, int green, int blue) throws IllegalArgumentException {

    if (red < 0 || blue < 0 || green < 0) {
      throw new IllegalArgumentException("All the colors need to be 0 or greater.");
    }

    if (red > 255 || blue > 255 || green > 255) {
      throw new IllegalArgumentException("All the colors need to be less than 256.");
    }

    this.red = red;
    this.green = green;
    this.blue = blue;
  }

  @Override
  public int getRed() {
    return this.red;
  }


  @Override
  public int getGreen() {
    return this.green;
  }


  @Override
  public int getBlue() {
    return this.blue;
  }
}



