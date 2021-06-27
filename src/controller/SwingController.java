package controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import model.ILayerModel;
import model.ImageRep;
import view.ISwingView;

/**
 * The SwingController class is used when certain events are triggered in the view. Once they
 * are triggered, the controller has to update the model to reflect the change and update the view.
 */
public class SwingController implements Features {

  private ILayerModel model;
  private ISwingView view;

  /**
   * This creates a swing controller object. The model field of the class is the parameter provided
   * in the constructor.
   *
   * @param model the ILayerModel that the controller edits data from and retrieves data from
   * @param view  the ISwingView that the controller is writing data to
   */
  public SwingController(ILayerModel model, ISwingView view) {
    this.model = Objects.requireNonNull(model);
    this.view = view;
    this.view.addFeatures(this);
  }

  @Override
  public void toggleVisible() {
    model.toggleVisible();
    repaint();
  }

  @Override
  public void blurImage() {
    model.blur();
    repaint();
  }

  @Override
  public void sharpenImage() {
    model.sharpen();
    repaint();
  }

  @Override
  public void greyscaleImage() {
    model.greyscale();
    repaint();
  }

  @Override
  public void sepiaImage() {
    model.sepia();
    repaint();
  }

  @Override
  public void duplicateLayer(String desiredName) {
    String duplicate = model.getCurrName();
    model.duplicateLayer(desiredName, duplicate);
    repaint();
  }

  @Override
  public void deleteLayer() {
    String layer = model.getCurrName();
    model.deleteLayer(layer);
    repaint();
  }

  @Override
  public void newLayer(String name) {
    model.addLayer(name);
  }

  @Override
  public void setCurrent(String name) {
    model.setCurrent(name);
  }

  @Override
  public void exportCurrentLayer(String name) throws IOException, IllegalArgumentException {
    if (name.endsWith(".ppm")) {
      FileActions.toPPM(name, model);
    } else if (name.endsWith(".png") || name.endsWith(".jpeg")) {
      FileActions.writeImage(model, name);
    } else {
      throw new IllegalArgumentException(
          "Need one of the supported extensions at the end of the filename.");
    }
    repaint();
  }

  @Override
  public void exportAll(String path) throws IOException {
    FileActions.exportAllLayers(path, model);

    repaint();
  }

  @Override
  public void importAll(String path) throws IOException {
    FileActions.importLayers(path, model);

    repaint();
  }

  @Override
  public void uploadImage(String path) throws IOException, IllegalArgumentException {
    ImageRep img;

    if (path.endsWith(".ppm")) {
      img = FileActions.readPPM(path);
    } else if (path.endsWith(".png") || path.endsWith(".jpeg")) {
      img = FileActions.readImage(path);
    } else {
      throw new IllegalArgumentException("Select file with png, jpeg, or ppm extension!");
    }
    model.upload(img);
    System.out.println("Image at " + path + " has been uploaded.");
    repaint();
  }

  @Override
  public String getCurrName() {
    return this.model.getCurrName();
  }


  /**
   * This method repaints the view to reflect the current state of the application after the
   * event has taken place. 
   */
  private void repaint() {
    if (model.getHeight() == 0) {
      view.repaintHelp();
    } else {
      BufferedImage image = FileActions.topVisibleBuffer(model);
      view.repaintHelpWithImage(image);
    }
  }
}
