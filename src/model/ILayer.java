package model;

/**
 * This interface represents any possible class that is going to represent a layer in the photo
 * processing application. The layer should be composed of an image or be empty. The layer is to be
 * used in any model that supports the layer feature. The layer should also support
 * visible/invisible functionality.
 */
public interface ILayer {

  /**
   * Adds an image to the layer it is called on.
   *
   * @param photo the image that will be added
   */
  void addImage(ImageRep photo);

  /**
   * Returns the name of the layer.
   *
   * @return the String name of the layer
   */
  String getName();

  /**
   * Gives the width of the layer in pixels.
   *
   * @return the integer width of the layer in pixels
   */
  int getWidth();

  /**
   * Gives the height of the layer in pixels.
   *
   * @return the integer height of the layer in pixels
   */
  int getHeight();

  /**
   * Applies a filter to the ImageRep of the layer. The filter that is applied corresponds to the
   * kernel provided in the parameter, ie. blur or sharpen.
   *
   * @param kernel the filter kernel to be applied
   */
  void filterLayer(double[][] kernel);

  /**
   * Applies a color filter to the ImageRep of the layer. The filter that is applied corresponds to
   * the kernel provided in the parameter, ie. sepia or greyscale.
   *
   * @param matrix the color matrix
   */
  void colorLayer(double[][] matrix);

  /**
   * Shows whether the layer is visible or not.
   *
   * @return the boolean value of the visible field of the layer
   */
  boolean isVisible();

  /**
   * Gives the Pixel at the current position.
   *
   * @param i the row of the pixel in the image
   * @param j the column of the pixel in the image
   * @return Pixel
   */
  IPixel getPixelAt(int i, int j);

  /**
   * Generates the 2D array that the layer corresponds to.
   *
   * @return the IPixel array stored in the layer
   */
  IPixel[][] layerGenArray();


  /**
   * This method will toggle the visibility of the layer. In other words, if it is visible it will
   * become invisible, and vice versa.
   */
  void toggleVisibility();
}
