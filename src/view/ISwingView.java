package view;

import controller.Features;
import java.awt.image.BufferedImage;

/**
 * This interface represents any view for the layer version of the photo editor program.
 * It holds methods that can be used to add action listeners to the view and update the state of
 * the view, depending on events.
 */
public interface ISwingView {

  /**
   * This method wiil create action listeners and responses to an event within the view. The
   * Features parameter is to figure oyt what sort of action the events in the view should hold.
   * @param features a Features object that holds possible functionality of the controller
   */
  void addFeatures(Features features);

  /**
   * This method will update the view because a new image has been added to the stack of layers.
   * The new image will be shown as a result of this method being called.
   *
   * @param img the BufferedImage to be shown
   */
  void repaintHelpWithImage(BufferedImage img);

  /**
   * This method will update the view. This version of the repaint does not need to show a new
   * image in the view. The image that was being shown before will continue to be shown.
   */
  void repaintHelp();
}
