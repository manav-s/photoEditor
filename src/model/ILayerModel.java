package model;

/**
 * This represents any model that supports layer functionality when editing images. It should offer
 * functionality to add, delete, export, and import layers.
 *
 * @param <K> the representation of an Image in the model.
 */
public interface ILayerModel<K> extends IModel<K> {

  /**
   * Duplicates an existing layer and adds it to the next index in the list of layers.
   *
   * @param desiredName the name of the new layer
   * @param duplicate   the layer that contains the image to be copied
   */
  void duplicateLayer(String desiredName, String duplicate) throws IllegalArgumentException;

  /**
   * Adds a layer. Takes a String for the name of the new layer. The layer is default set to an
   * empty 2D array of pixels and is made visible.
   *
   * @param name the name of the new layer
   * @throws IllegalArgumentException if the layer name is already taken
   */
  void addLayer(String name) throws IllegalArgumentException;

  /**
   * Sets the given layer to the current one in the model.
   *
   * @param name the String name of the layer that should be the new current layer
   * @throws IllegalArgumentException if the layer does not exist
   */
  void setCurrent(String name) throws IllegalArgumentException;

  /**
   * Deletes a given layer.
   *
   * @param name the String name of the layer to be deleted.
   * @throws IllegalArgumentException if the layer does not exist in the layer stack
   */
  void deleteLayer(String name) throws IllegalArgumentException;

  /**
   * Returns the height of the current layer in pixels.
   *
   * @return the integer height of the current layer in pixels
   */
  int getHeight();

  /**
   * Returns the width of the current layer in pixels.
   *
   * @return the integer width of the current layer in pixels
   */
  int getWidth();

  /**
   * Sets the current layer to the layer with the given index.
   *
   * @param index the index of the new current layer
   * @throws IllegalArgumentException if it doesnt exist
   */
  void setCurrentFromIndex(int index) throws IllegalArgumentException;

  /**
   * Upload a given ImageRep to the layer model. It is added to a new visible layer.
   *
   * @param img the ImageRep that should be added to the model
   */
  void upload(ImageRep img);

  /**
   * Get the name of the current layer in the model.
   *
   * @return the String name of the current layer of the model
   */
  String getCurrName();

  /**
   * Gives the top visible layer of the layer model, unless there are none.
   *
   * @return the ILayer that is on top and visible
   * @throws IllegalStateException if there are no visible layers
   */
  ILayer topVisible() throws IllegalStateException;

  /**
   * Adds a layer to the model with a given name and ImageRep.
   *
   * @param image the ImageRep to be added to the new layer
   * @param name  the name of the new layer
   * @throws IllegalArgumentException if the layer already exists
   */
  void addLayerWithImage(ImageRep image, String name) throws IllegalArgumentException;


  /**
   * This method toggles the visibility of the current layer. If the current layer is visible, after
   * toggling, it will not be visible anymore but still be the current layer. If the current is not
   * visible, it will become visible and stay the current layer.
   */
  void toggleVisible();
}
