package model;

/**
 * Represents a pixel of an ImageRep.
 */

public interface IPixel {

  /**
   * Returns the magnitude of red in the RGB color.
   *
   * @return the number of the magnitude of the red of the pixel
   */
  int getRed();

  /**
   * Returns the magnitude of green in the RGB color.
   *
   * @return the number of the magnitude of the green of the pixel
   */
  int getBlue();

  /**
   * Returns the magnitude of blue in the RGB color.
   *
   * @return the number of the magnitude of the blue of the pixel
   */
  int getGreen();
}
