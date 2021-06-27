package model;

/**
 * This class represents a layer in the photo processing code model that uses. It is capable
 * of holding one ImageRep object, and can be visible or not. It also has a name.
 */
public class Layer implements ILayer {

  private ImageRep photo;
  private String name;
  private boolean isVisible = true;

  /**
   * Creates a layer object with a given ImageRep and name.
   *
   * @param photo the photo that is stored inside the layer
   * @param name  the name of the layer
   */
  public Layer(ImageRep photo, String name) {
    this.photo = photo;
    this.name = name;
  }

  /**
   * Creates a new layer with a given String name. The ImageRep field is initialized as a 2D pixel
   * array of size 0 by 0.
   *
   * @param name the name of the layer
   */
  public Layer(String name) {
    IPixel[][] array = new Pixel[0][0];
    this.photo = new Image(array);
    this.name = name;
  }

  @Override
  public void addImage(ImageRep photo) {
    this.photo = photo;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public int getWidth() {
    return this.photo.getWidth();
  }

  @Override
  public int getHeight() {
    return this.photo.getHeight();
  }

  @Override
  public void filterLayer(double[][] kernel) {
    this.photo = photo.filterPhoto(kernel);
  }

  @Override
  public void colorLayer(double[][] matrix) {
    this.photo = photo.colorPhoto(matrix);
  }

  @Override
  public boolean isVisible() {
    return this.isVisible;
  }

  @Override
  public IPixel getPixelAt(int i, int j) {
    return this.photo.getPixelAt(i, j);
  }

  @Override
  public IPixel[][] layerGenArray() {
    return this.photo.genArray();
  }

  @Override
  public void toggleVisibility() {
    if (this.isVisible) {
      this.isVisible = false;
    } else {
      this.isVisible = true;
    }
  }
}
