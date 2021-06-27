package controller;

import java.io.IOException;
import model.ILayerModel;
import model.LayerModel;
import view.SwingViewFrame;

/**
 * This class is how the client would run the image processing program. As its written right now, we
 * will use the model that supports all multi-layer features.
 */
public class Main {

  /**
   * To run the image processing program, run this method. The default setting for the main method
   * is the GUI. However, if you would like to use the scripting method uncomment line 28 & 31,
   * and comment out line 33 & line 34.
   *
   * @param args the arguments provided to the controller
   * @throws IOException if there is an error writing data
   */
  public static void main(String[] args) {

    ILayerModel model = new LayerModel();
    SwingViewFrame view = new SwingViewFrame("Photo Editor");
    SwingController controller = new SwingController(model, view);
  }
}
