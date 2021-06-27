package model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents an alternate version of the model that is used in the photo processing
 * application. This model differs because it has the support for multiple layers of images. These
 * layers can be visible or invisible. Also, these layers can be manipulated.
 */
public class LayerModel implements ILayerModel<ImageRep> {

  List<ILayer> layers = new ArrayList<ILayer>();
  ILayer current;

  /**
   * This creates an object of the layer model class. The current field is initialized to null,
   * because no "current" layer has been initialized.
   */
  public LayerModel() {
    super();
    this.current = null;
  }

  @Override
  public void blur() throws IllegalStateException {
    checkForNullCurrent();
    double[] row1 = new double[3];
    row1[0] = (1 / 16.0);
    row1[1] = (1 / 8.0);
    row1[2] = (1 / 16.0);

    double[] row2 = new double[3];
    row2[0] = (1 / 8.0);
    row2[1] = (1 / 4.0);
    row2[2] = (1 / 8.0);

    double[] row3 = new double[3];
    row3[0] = (1 / 16.0);
    row3[1] = (1 / 8.0);
    row3[2] = (1 / 16.0);

    double[][] kernel = new double[3][3];
    kernel[0] = row1;
    kernel[1] = row2;
    kernel[2] = row3;

    this.current.filterLayer(kernel);
  }

  @Override
  public void sharpen() throws IllegalStateException {
    checkForNullCurrent();
    double[] row1 = new double[5];
    row1[0] = (-1 / 8.0);
    row1[1] = (-1 / 8.0);
    row1[2] = (-1 / 8.0);
    row1[3] = (-1 / 8.0);
    row1[4] = (-1 / 8.0);

    double[] row2 = new double[5];
    row2[0] = (-1 / 8.0);
    row2[1] = (1 / 4.0);
    row2[2] = (1 / 4.0);
    row2[3] = (1 / 4.0);
    row2[4] = (-1 / 8.0);

    double[] row3 = new double[5];
    row3[0] = (-1 / 8.0);
    row3[1] = (1 / 4.0);
    row3[2] = 1.0;
    row3[3] = (1 / 4.0);
    row3[4] = (-1 / 8.0);

    double[] row4 = new double[5];
    row4[0] = (-1 / 8.0);
    row4[1] = (1 / 4.0);
    row4[2] = (1 / 4.0);
    row4[3] = (1 / 4.0);
    row4[4] = (-1 / 8.0);

    double[] row5 = new double[5];
    row5[0] = (-1 / 8.0);
    row5[1] = (-1 / 8.0);
    row5[2] = (-1 / 8.0);
    row5[3] = (-1 / 8.0);
    row5[4] = (-1 / 8.0);

    double[][] kernel = new double[5][5];
    kernel[0] = row1;
    kernel[1] = row2;
    kernel[2] = row3;
    kernel[3] = row4;
    kernel[4] = row5;

    this.current.filterLayer(kernel);
  }

  @Override
  public void sepia() throws IllegalStateException {
    checkForNullCurrent();
    double[] row1 = new double[3];
    row1[0] = 0.393;
    row1[1] = 0.769;
    row1[2] = 0.189;

    double[] row2 = new double[3];
    row2[0] = 0.349;
    row2[1] = 0.686;
    row2[2] = 0.168;

    double[] row3 = new double[3];
    row3[0] = 0.272;
    row3[1] = 0.534;
    row3[2] = 0.131;

    double[][] matrix = new double[3][3];
    matrix[0] = row1;
    matrix[1] = row2;
    matrix[2] = row3;

    this.current.colorLayer(matrix);
  }

  @Override
  public void greyscale() throws IllegalStateException {
    checkForNullCurrent();
    double[] row1 = new double[3];
    row1[0] = 0.2126;
    row1[1] = 0.7152;
    row1[2] = 0.0722;

    double[] row2 = new double[3];
    row2[0] = 0.2126;
    row2[1] = 0.7152;
    row2[2] = 0.0722;

    double[] row3 = new double[3];
    row3[0] = 0.2126;
    row3[1] = 0.7152;
    row3[2] = 0.0722;

    double[][] matrix = new double[3][3];
    matrix[0] = row1;
    matrix[1] = row2;
    matrix[2] = row3;

    this.current.colorLayer(matrix);
  }

  /**
   * Checks if the "current" field in the model is null.
   *
   * @throws IllegalStateException if the current field is null
   */
  private void checkForNullCurrent() throws IllegalStateException {
    if (current == null || current.getHeight() == 0 || current.getWidth() == 0) {
      throw new IllegalStateException("No image uploaded.");
    }
  }

  /**
   * Processes the indicies when we create a checkerboard image.
   *
   * @param index     the index of the tile
   * @param tileWidth the width in pixels of the tile
   * @returns the proper number of the tile once the tileWidth is considered
   */
  private int getIndices(int index, int tileWidth) {
    double res = (index / tileWidth);
    return (int) res;
  }

  @Override
  public ILayer topVisible() throws IllegalStateException {

    if (layers == null) {
      throw new IllegalStateException("The layers are null.");
    }

    if (layers.size() == 0) {
      throw new IllegalStateException("There are no layers.");
    }

    int layerSize = layers.size();

    for (int i = 0; i < layerSize; i++) {
      if (layers.get(i).isVisible()) {
        return layers.get(i);
      }
    }

    Image image = new Image(new Pixel[1][1]);
    return new Layer(image, "duplicate");
  }

  @Override
  public ImageRep getCheckerboard(int heightOfTile, int numTiles) throws IllegalArgumentException {

    if (heightOfTile <= 0 || numTiles <= 0) {
      throw new IllegalArgumentException("arguments need to be greater than 0");
    }

    // check to see if the number of tiles is a perfect square, if not, throw an exception
    int s = (int) Math.sqrt(numTiles);
    if (s * s != numTiles) {
      throw new IllegalArgumentException("Number of tiles is not a perfect square.");
    }

    Pixel[][] array = new Pixel[s * heightOfTile][s * heightOfTile];

    for (int i = 0; i < array.length; i++) {
      for (int j = 0; j < array[0].length; j++) {
        int sumIndices = getIndices(i, heightOfTile) + getIndices(j, heightOfTile);
        if (sumIndices % 2 == 0) {
          array[i][j] = new Pixel(0, 0, 0);
        } else {
          array[i][j] = new Pixel(255, 255, 255);
        }
      }
    }
    ImageRep pic = new Image(array);
    this.current.addImage(pic);
    return pic;
  }

  @Override
  public IPixel getPixelAt(int row, int column) throws IllegalStateException {
    return this.current.getPixelAt(row, column);
  }

  @Override
  public void toggleVisible() {
    checkForNullCurrent();
    current.toggleVisibility();
  }

  @Override
  public void addLayerWithImage(ImageRep image, String name) throws IllegalArgumentException {

    if (image.getHeight() != current.getHeight()
        || image.getWidth() != current.getWidth()) {
      throw new IllegalArgumentException("The layers must be the same size");
    }

    for (ILayer layer : layers) {
      if (layer.getName().equals(name)) {
        throw new IllegalArgumentException("Layer already exists with that name");
      }
    }
    ILayer layer = new Layer(image, name);
    layers.add(0, layer);
    current = layer;
  }

  @Override
  public void duplicateLayer(String desiredName, String duplicate) throws IllegalArgumentException {
    boolean found = false;

    for (ILayer layer : layers) {
      if (layer.getName().equals(desiredName)) {
        throw new IllegalArgumentException("Layer already exists with that name");
      }
    }

    for (int i = 0; i < layers.size(); i++) {
      if (layers.get(i).getName().equals(duplicate)) {
        layers.add(i + 1, new Layer(new Image(layers.get(i).layerGenArray()), desiredName));
        found = true;
      }
    }
    if (!found) {
      throw new IllegalArgumentException("Layer not found.");
    }
  }

  @Override
  public void addLayer(String name) throws IllegalArgumentException {
    for (ILayer layer : layers) {
      if (layer.getName().equals(name)) {
        throw new IllegalArgumentException("Layer already exists with that name");
      }
    }

    if (layers.size() == 0) {
      ILayer layer = new Layer(name);
      layers.add(layer);
      current = layer;
    } else {

      ILayer layer = new Layer(new Image(new Pixel[current.getHeight()][current.getWidth()]),
          name);
      layers.add(0, layer);
      current = layer;
    }
  }


  @Override
  public void setCurrent(String name) {
    boolean found = false;
    for (ILayer layer : layers) {
      if (layer.getName().equals(name)) {
        current = layer;
        found = true;
      }
    }
    if (!found) {
      throw new IllegalArgumentException("Layer not found");
    }
  }

  @Override
  public void deleteLayer(String name) throws IllegalStateException {

    if (layers.size() <= 1) {
      throw new IllegalStateException("Cannot delete last layer");
    }
    boolean found = false;
    for (int i = 0; i < layers.size(); i++) {
      // if desired name equals name in layer loop
      if (layers.get(i).getName().equals(name)) {
        // iff current is the layer that is wanted to be deleted
        if (current == layers.get(i)) {
          // if this is the last index
          if (i == layers.size() - 1) {
            current = layers.get(i - 1);
          } else {
            current = layers.get(i + 1);
          }
        }
        layers.remove(i);
        found = true;
      }
    }
    if (!found) {
      throw new IllegalStateException("Layer not found");
    }
  }

  @Override
  public int getHeight() {
    return this.current.getHeight();
  }

  @Override
  public int getWidth() {
    return this.current.getWidth();
  }

  @Override
  public void setCurrentFromIndex(int index) {
    if (index >= numPhoto() || index < 0) {
      throw new IllegalArgumentException("The index is invalid");
    }
    current = layers.get(index);
  }

  @Override
  public void upload(ImageRep photo) throws IllegalStateException {
    if (current == null) {
      throw new IllegalStateException("Please choose a layer as current to upload to.");
    }
    current.addImage(photo);
  }

  @Override
  public int numPhoto() {
    if (layers.size() == 0) {
      return 0;
    } else {
      return layers.size();
    }
  }

  @Override
  public String getName(int index) {
    return this.layers.get(index).getName();
  }

  @Override
  public String getCurrName() {
    if (this.current == null) {
      return null;
    }
    return this.current.getName();
  }
}

