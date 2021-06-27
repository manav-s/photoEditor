package model;

/**
 * This class is a way to represent an image. In particular, the image is made up of a 2D array of
 * pixels. Each one of these pixels has an integer field for red, green, and blue, to represent the
 * RGB color scheme.
 */
public class Image implements ImageRep {
  private IPixel[][] pixelArray;

  /**
   * Constructs an image object with a 2D array of pixels.
   *
   * @param pixelArray the 2D array of pixels
   * @throws IllegalArgumentException if the array is null or any of its dimensions are zero
   */
  public Image(IPixel[][] pixelArray) throws IllegalArgumentException {

    // array can't be null
    if (pixelArray == null) {
      throw new IllegalArgumentException("The pixel array cannot be null.");
    }

    this.pixelArray = pixelArray;
  }

  @Override
  public IPixel[][] genArray() {

    IPixel[][] dest = new Pixel[this.getHeight()][this.getWidth()];
    for (int i = 0; i < this.getHeight(); i++) {
      for (int j = 0; j < this.getWidth(); j++) {
        dest[i][j] = new Pixel(this.getPixelAt(i, j).getRed(), this.getPixelAt(i, j).getGreen(),
            this.getPixelAt(i, j).getBlue());
      }
    }
    return dest;
  }

  /**
   * Checks to see if the array has proper formatting. In particular, if a 2D array is null or has
   * any zero dimensions, it is not a proper 2D array.
   *
   * @throws IllegalArgumentException if the array is null or has any zero dimension
   */
  private void properArray() throws IllegalArgumentException {
    // array can't be null
    if (pixelArray == null) {
      throw new IllegalArgumentException("The pixel array cannot be null.");
    }

    // array can't have zero height
    if (pixelArray.length == 0) {
      throw new IllegalArgumentException("The height of the array cannot be 0");
    }

    // array can't have zero width
    if (pixelArray[0].length == 0) {
      throw new IllegalArgumentException("The width of the array cannot be 0.");
    }
  }

  @Override
  public void applyFilterToPixel(double[][] kernel, int targX, int targY, IPixel[][] arr) throws
      IllegalArgumentException {

    if (kernel == null || kernel.length != kernel[0].length || kernel.length % 2 != 1) {
      throw new IllegalArgumentException("The kernel is not well-formed");
    }

    if (targX < 0 || targX >= this.pixelArray.length) {
      throw new IllegalArgumentException("TargX is invalid");
    }

    if (targY < 0 || targY >= this.pixelArray[0].length) {
      throw new IllegalArgumentException("TargY is invalid");
    }

    properArray();
    new Image(arr).properArray();

    double newRed = 0;
    double newGreen = 0;
    double newBlue = 0;

    for (int i = 0; i < kernel.length; i++) {
      for (int j = 0; j < kernel[0].length; j++) {
        newRed += kernel[i][j]
            * this.getPixelAt((targX + i - ((kernel.length - 1) / 2)),
            (targY + j - ((kernel.length - 1) / 2))).getRed();

        newGreen += kernel[i][j]
            * this.getPixelAt((targX + i - ((kernel.length - 1) / 2)),
            (targY + j - ((kernel.length - 1) / 2))).getGreen();

        newBlue += kernel[i][j]
            * this.getPixelAt((targX + i - ((kernel.length - 1) / 2)),
            (targY + j - ((kernel.length - 1) / 2))).getBlue();
      }
    }
    arr[targX][targY] = new Pixel(clampingProcesser(newRed, 0, 255),
        clampingProcesser(newGreen, 0, 255),
        clampingProcesser(newBlue, 0, 255));
  }

  @Override
  public void applyColorToPixel(double[][] matrix, int targX, int targY, IPixel[][] arr)
      throws IllegalArgumentException {

    // the matrix can't be null or not a 3x3
    if (matrix == null || matrix.length != 3 || matrix[0].length != 3) {
      throw new IllegalArgumentException("The matrix is invalid.");
    }

    if (targX < 0 || targX >= this.pixelArray.length) {
      throw new IllegalArgumentException("TargX is invalid");
    }

    if (targY < 0 || targY >= this.pixelArray[0].length) {
      throw new IllegalArgumentException("TargY is invalid");
    }

    properArray();
    new Image(arr).properArray();

    double newRed = 0;
    double newGreen = 0;
    double newBlue = 0;

    newRed += ((matrix[0][0] * getPixelAt(targX, targY).getRed())
        + (matrix[0][1] * getPixelAt(targX, targY).getGreen())
        + (matrix[0][2] * getPixelAt(targX, targY).getBlue()));

    newGreen += ((matrix[1][0] * getPixelAt(targX, targY).getRed())
        + (matrix[1][1] * getPixelAt(targX, targY).getGreen())
        + (matrix[1][2] * getPixelAt(targX, targY).getBlue()));

    newBlue += ((matrix[2][0] * getPixelAt(targX, targY).getRed())
        + (matrix[2][1] * getPixelAt(targX, targY).getGreen())
        + (matrix[2][2] * getPixelAt(targX, targY).getBlue()));

    arr[targX][targY] = new Pixel(clampingProcesser(newRed, 0, 255),
        clampingProcesser(newGreen, 0, 255),
        clampingProcesser(newBlue, 0, 255));
  }

  @Override
  public int getHeight() throws IllegalArgumentException {

    return pixelArray.length;
  }

  @Override
  public int getWidth() throws IllegalArgumentException {
    if (pixelArray.length == 0) {
      return 0;
    }

    return pixelArray[0].length;
  }

  @Override
  public IPixel getPixelAt(int row, int column) throws IllegalArgumentException {
    properArray();

    IPixel result;
    try {
      result = new Pixel(this.pixelArray[row][column].getRed(),
          this.pixelArray[row][column].getGreen(),
          this.pixelArray[row][column].getBlue());
    } catch (ArrayIndexOutOfBoundsException err) {
      result = new Pixel(0, 0, 0);
    }
    return result;
  }

  /**
   * Processes the RGB doubles and makes sure they are properly fornmed to be inserted into pixel.
   * If the double is less than the minimum, we return the minimum. If the double is greater than
   * the max, we return the max. Otherwise, we simply return the double as an int (rounded).
   *
   * @param colorVal the double to be processed
   * @param min      the minimum possible values
   * @param max      the maximum possible values
   * @return
   */
  private int clampingProcesser(double colorVal, int min, int max) {
    if (colorVal < min) {
      return min;
    } else if (colorVal > max) {
      return max;
    } else {
      return (int) Math.round(colorVal);
    }
  }

  @Override
  public ImageRep colorPhoto(double[][] matrix) {
    int height = getHeight();
    int width = getWidth();

    IPixel[][] copy = genArray();

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        applyColorToPixel(matrix, i, j, copy);
      }
    }
    return new Image(copy);
  }

  @Override
  public ImageRep filterPhoto(double[][] kernel) {
    int height = getHeight();
    int width = getWidth();

    IPixel[][] copy = genArray();

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        applyFilterToPixel(kernel, i, j, copy);
      }
    }
    return new Image(copy);
  }
}
