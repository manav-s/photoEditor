package model;

/**
 * This interface represents any model of the image processing application. The model is capable of
 * storing at least one image and performing actions on the images. It offers support for filtering
 * and color changing.
 *
 * @param <K> the representation of images used ie. ImageRep
 */
public interface IModel<K> {

  /**
   * Blurs the photo in the model.
   *
   * @throws IllegalStateException if there is no photo uploaded to the model
   */
  void blur() throws IllegalStateException;

  /**
   * Sharpens the photo in the model.
   *
   * @throws IllegalStateException if there is no photo uploaded to the model
   */
  void sharpen() throws IllegalStateException;

  /**
   * Creates a sepia color effect on the photo in the model.
   *
   * @throws IllegalStateException if there is no photo uploaded to the model
   */
  void sepia() throws IllegalStateException;

  /**
   * Creates a greyscale color effect on the photo in the model.
   *
   * @throws IllegalStateException if there is no photo uploaded to the model
   */
  void greyscale() throws IllegalStateException;

  /**
   * Creates a checkerboard image with the desired height of the tiles and amount of tiles.
   *
   * @param heightOfTile the desired height of each tile in pixels
   * @param numTiles     the total amount of desired tiles in the checkerboard
   * @return a checkboard image
   */
  K getCheckerboard(int heightOfTile, int numTiles);

  /**
   * Gives the pixel of the photo in the model.
   *
   * @param row    the row of the pixel
   * @param column the column of the pixel
   * @return the pixel at the correct position
   */
  IPixel getPixelAt(int row, int column);


  /**
   * If no image has been loaded, it returns 0. Otherwise it returns the number of photos.
   *
   * @return
   */
  int numPhoto();

  /**
   * Gives the name of the layer where the index is.
   *
   * @param index the index of the layer in the list of layers
   * @return the String name of the layer
   */
  String getName(int index);
}
