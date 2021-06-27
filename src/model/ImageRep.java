package model;

/**
 * This represents any representation of an image. The representation must contain information the
 * pixels of the image so we can perform operations on the pixels.
 */
public interface ImageRep {

  /**
   * Applies a filter of the given kernel to an image. We take a row and column for the pixel, as
   * well as an arr (Array) to write our new Pixel value to (to prevent writing to the original).
   *
   * @param kernel the kernel of the filter
   * @param targX  the row of the pixel to be changed
   * @param targY  the column of the pixel to be changed
   * @param arr    where the end value will be placed
   * @throws IllegalArgumentException if the array of the image is null or has 0 dimensions
   */
  void applyFilterToPixel(double[][] kernel, int targX, int targY, IPixel[][] arr);

  /**
   * Applies a color of the given matrix to an image.
   *
   * @param matrix the kernel of the filter
   * @param targX  the row of the pixel to be changed
   * @param targY  the column of the pixel to be changed
   * @param arr    where the end value will be placed
   * @throws IllegalArgumentException if the array of the image is null or has 0 dimensions
   */
  void applyColorToPixel(double[][] matrix, int targX, int targY, IPixel[][] arr);


  /**
   * Gives the height of the image.
   *
   * @return the height of the image in integer.
   * @throws IllegalArgumentException if the array of the image is null or has 0 dimensions
   */
  int getHeight();

  /**
   * Gives the width of the image.
   *
   * @return the width of the image in integer
   * @throws IllegalArgumentException if the array of the image is null or has 0 dimensions
   */
  int getWidth();

  /**
   * Gives the pixel at a certain position of the image.
   *
   * @param row    the row of the pixel we want
   * @param column the column of the pixel
   * @return the IPixel at the certain position
   * @throws IllegalArgumentException if the array of the image is null or has 0 dimensions
   */
  IPixel getPixelAt(int row, int column);

  /**
   * Generates the pixel array of the image stored inside the ImageRep.
   *
   * @return the 2D IPixel array of the ImageRep.
   */
  IPixel[][] genArray();

  /**
   * Filters the photo in the ImageRep off the given custom kernel.
   *
   * @param kernel the data stored within the kernel
   * @return the ImageRep once the filter is applied
   */
  ImageRep filterPhoto(double[][] kernel);

  /**
   * Colors the photo in the ImageRep off the given custom kernel.
   *
   * @param matrix the data stored within the kernel
   * @return the ImageRep once the color is applied
   */
  ImageRep colorPhoto(double[][] matrix);
}
