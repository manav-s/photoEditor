package controller;

import java.io.IOException;

/**
 * A set of features for the image manipulation program that will be implemented by a Swing
 * compatible controller. This interface is to be used with a controller and model that supports
 * multiple layers in the program. These methods should reflect respective changes in the
 * chosen view and model.
 */
public interface Features {

  /**
   * Performs actions on both the model and the view to reflect the changes of a blur event.
   * A blur event should blur the image data in the model and update the view.
   */
  void blurImage();

  /**
   * Performs actions on both the model and the view to reflect the changes of a sharpen event.
   * A sharpen event should sharpen the image data in the model and update the view.
   */
  void sharpenImage();

  /**
   * Performs actions on both the model and the view to reflect the changes of a greyscale event.
   * A greyscale event should greyscale the image data in the model and update the view.
   */
  void greyscaleImage();

  /**
   * Performs actions on both the model and the view to reflect the changes of a sepia event.
   * A greyscale event should sepia the image data in the model and update the view.
   */
  void sepiaImage();

  /**
   * Performs actions on both the model and the view to reflect the changes of a duplicate event.
   * A duplicate event should duplicate the layer in the model and update the view.
   */
  void duplicateLayer(String desiredName);

  /**
   * Performs actions on both the model and the view to reflect the changes of a delete event.
   * A delete event should delete the layer in the model and update the view.
   */
  void deleteLayer();

  /**
   * Performs actions on both the model and the view to reflect the changes of a new layer event.
   * A new layer event should add a new layer to the model and update the view.
   */
  void newLayer(String name);

  /**
   * Performs actions on both the model and the view to reflect the changes of a set current event.
   * A set current event should change the "current" layer and update the view.
   */
  void setCurrent(String name);

  /**
   * Performs actions on both the model and the view to reflect the changes of a get current event.
   * A get current name event should return the name of the current layer.
   */
  String getCurrName();

  /**
   * Performs actions on both the model and the view to reflect the changes of a toggle event.
   * A toggle visible event should toggle the visibility of an image in the model and update
   * the view.
   */
  void toggleVisible();

  /**
   * Performs actions on both the model and the view to reflect the changes of a export current
   * event. An export current event should export the current layer in the model.
   */
  void exportCurrentLayer(String name) throws IOException;

  /**
   * Performs actions on both the model and the view to reflect the changes of a export all
   * event. An export all event should export all the layers in the model.
   */
  void exportAll(String path) throws IOException;

  /**
   * Performs actions on both the model and the view to reflect the changes of a import all event.
   * An import all event should import the layers into the model and update the view.
   */
  void importAll(String path) throws IOException;

  /**
   * Performs actions on both the model and the view to reflect the changes of an upload image.
   * An upload event should upload the image data to the model and update the view.
   */
  void uploadImage(String path) throws IOException;
}
