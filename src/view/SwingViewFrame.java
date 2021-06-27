package view;

import controller.Features;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * This class represents a view of the photo editor application. In particular, this is used in
 * accordance with a model that can support multiple layers. The view is set up with a main central
 * region to view the image to be edited. On the side, there are buttons that show the actions that
 * can be performed.
 */
public class SwingViewFrame extends JFrame implements ISwingView {

  private JButton blurButton;
  private JButton sharpenButton;
  private JButton sepiaButton;
  private JButton greyscaleButton;
  private JButton toggleButton;

  private JButton duplicateButton;
  private JButton deleteButton;
  private JButton newLayerButton;
  private JButton uploadButton;
  private JButton exportCurrent;
  private JButton exportAll;
  private JButton importAll;
  private JPanel layersPanel;

  private JLabel imageLabel;
  private Map<String, JButton> layers = new HashMap<String, JButton>();

  /**
   * Creates a SwingViewFrame object. The given String paramter is the title of the window that will
   * populate.
   *
   * @param caption the String caption title of the window of the application
   */
  public SwingViewFrame(String caption) {
    super(caption);

    setSize(1000, 600);
    setLocation(300, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(true);
    this.setMinimumSize(new Dimension(1000, 600));

    this.setLayout(new BorderLayout());
    JPanel fileActionsFrame = new JPanel();
    JPanel processingFrame = new JPanel();
    JPanel layerActionsFrame = new JPanel();
    layersPanel = new JPanel();
    JPanel leftPanel = new JPanel();
    JPanel rightPanel = new JPanel();

    layerActionsFrame.setLayout(new BoxLayout(layerActionsFrame, BoxLayout.Y_AXIS));
    fileActionsFrame.setLayout(new BoxLayout(fileActionsFrame, BoxLayout.Y_AXIS));
    processingFrame.setLayout(new BoxLayout(processingFrame, BoxLayout.Y_AXIS));
    layersPanel.setLayout(new BoxLayout(layersPanel, BoxLayout.Y_AXIS));
    rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
    leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

    layersPanel.add(new JLabel("Layers"));
    processingFrame.add(new JLabel("Edit image"));
    fileActionsFrame.add(new JLabel("File actions"));

    leftPanel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
    rightPanel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
    layersPanel.setBorder(new EmptyBorder(0, 0, 100, 0));
    layerActionsFrame.setBorder(new MatteBorder(1, 0, 0, 0, Color.lightGray));
    fileActionsFrame.setBorder(new MatteBorder(1, 0, 0, 0, Color.lightGray));
    processingFrame.setBorder(new EmptyBorder(0, 0, 200, 0));

    rightPanel.add(layersPanel);
    rightPanel.add(layerActionsFrame);
    leftPanel.add(processingFrame);
    leftPanel.add(fileActionsFrame);
    imageLabel = new JLabel();
    imageLabel.setHorizontalAlignment(JLabel.CENTER);
    imageLabel.setVerticalAlignment(JLabel.CENTER);

    JPanel imageFrame = new JPanel();

    imageFrame.add(imageLabel);
    JScrollPane imageScrollPane = new JScrollPane(imageFrame);

    this.add(rightPanel, BorderLayout.EAST);
    this.add(imageScrollPane, BorderLayout.CENTER);
    this.add(leftPanel, BorderLayout.WEST);

    blurButton = new JButton("Blur image");
    blurButton.setActionCommand("Blur image");
    processingFrame.add(blurButton);

    sharpenButton = new JButton("Sharpen image");
    sharpenButton.setActionCommand("Sharpen image");
    processingFrame.add(sharpenButton);

    sepiaButton = new JButton("Sepia image");
    sepiaButton.setActionCommand("Sepia image");
    processingFrame.add(sepiaButton);

    greyscaleButton = new JButton("Greyscale image");
    greyscaleButton.setActionCommand("Greyscale image");
    processingFrame.add(greyscaleButton);

    toggleButton = new JButton("Toggle visibility");
    toggleButton.setActionCommand("Toggle visibility");
    processingFrame.add(toggleButton);

    duplicateButton = new JButton("Duplicate layer");
    duplicateButton.setActionCommand("Duplicate layer");
    layerActionsFrame.add(duplicateButton);

    deleteButton = new JButton("Delete layer");
    deleteButton.setActionCommand("Delete layer");
    layerActionsFrame.add(deleteButton);

    newLayerButton = new JButton("New layer");
    newLayerButton.setActionCommand("New layer");
    layerActionsFrame.add(newLayerButton);

    uploadButton = new JButton("Upload image");
    uploadButton.setActionCommand("Upload image");
    fileActionsFrame.add(uploadButton);

    JButton uploadArchive = new JButton("Upload archive");
    uploadArchive.setActionCommand("Upload archive");
    fileActionsFrame.add(uploadArchive);

    exportCurrent = new JButton("Export current");
    exportCurrent.setActionCommand("Export current");
    fileActionsFrame.add(exportCurrent);

    exportAll = new JButton("Export all layers");
    exportAll.setActionCommand("Export all layers");
    fileActionsFrame.add(exportAll);

    importAll = new JButton("Import all layers");
    importAll.setActionCommand("Import all layers");
    fileActionsFrame.add(importAll);

    pack();
    this.setVisible(true);
  }

  @Override
  public void addFeatures(Features features) {
    // photo manipulation
    blurButton.addActionListener(evt -> {
      try {
        features.blurImage();
      } catch (IllegalStateException err) {
        JFrame frame = new JFrame();
        JOptionPane.showMessageDialog(frame, err.getMessage(), "Error blurring layer",
            JOptionPane.ERROR_MESSAGE);
      }
    });

    sharpenButton.addActionListener(evt -> {
      try {
        features.sharpenImage();
      } catch (IllegalStateException err) {
        JFrame frame = new JFrame();
        JOptionPane.showMessageDialog(frame, err.getMessage(), "Error sharpening layer",
            JOptionPane.ERROR_MESSAGE);
      }
    });

    greyscaleButton.addActionListener(evt -> {
      try {
        features.greyscaleImage();
      } catch (IllegalStateException err) {
        JFrame frame = new JFrame();
        JOptionPane.showMessageDialog(frame, err.getMessage(), "Error applying greyscale",
            JOptionPane.ERROR_MESSAGE);
      }
    });

    sepiaButton.addActionListener(evt -> {
      try {
        features.sepiaImage();
      } catch (IllegalStateException err) {
        JFrame frame = new JFrame();
        JOptionPane.showMessageDialog(frame, err.getMessage(), "Error applying sepia",
            JOptionPane.ERROR_MESSAGE);
      }
    });

    toggleButton.addActionListener(evt -> {
      try {
        features.toggleVisible();
      } catch (IllegalStateException err) {
        JFrame frame = new JFrame();
        JOptionPane.showMessageDialog(frame, err.getMessage(), "Error toggling",
            JOptionPane.ERROR_MESSAGE);
      }
    });

    // layer manipulation
    // duplicating button
    duplicateButton.addActionListener(evt -> {
      String name = JOptionPane.showInputDialog("New layer name: ");
      if ((name != null) && name.length() > 0) {
        try {
          features.duplicateLayer(name);
          JButton button = new JButton(name);
          layersPanel.add(button);
          layerAction(features, button, name);
          button.addActionListener(ev -> {
            resetLayerColors();
            layerAction(features, button, name);
          });
          layers.put(name, button);
          button.setVisible(true);
          this.revalidate();
          this.repaint();
        } catch (IllegalArgumentException | IllegalStateException err) {
          JFrame frame = new JFrame();
          JOptionPane.showMessageDialog(frame, err.getMessage(), "Error duplicating layer",
              JOptionPane.ERROR_MESSAGE);
        }
      }
    });

    deleteButton.addActionListener(evt -> {
      try {
        String curr = features.getCurrName();
        if (curr != null) {
          features.deleteLayer();
          JButton button = layers.get(curr);
          layersPanel.remove(button);
          layers.remove(curr);
          String newCurr = features.getCurrName();
          JButton newButton = layers.get(newCurr);
          resetLayerColors();
          layerAction(features, newButton, newCurr);

        } else {
          JFrame frame = new JFrame();
          JOptionPane.showMessageDialog(frame, "No layers to delete",
              "Error deleting layer",
              JOptionPane.ERROR_MESSAGE);
        }

      } catch (IllegalStateException err) {
        JFrame frame = new JFrame();
        JOptionPane.showMessageDialog(frame, err.getMessage(), "Error deleting layer",
            JOptionPane.ERROR_MESSAGE);
      }
    });

    newLayerButton.addActionListener(evt -> {
      String name = JOptionPane.showInputDialog("New layer name: ");
      if ((name != null) && name.length() > 0) {
        try {
          features.newLayer(name);
          JButton button = new JButton(name);
          layersPanel.add(button, 1);
          layerAction(features, button, name);
          button.addActionListener(ev -> {
            resetLayerColors();
            layerAction(features, button, name);
          });
          layers.put(name, button);
          button.setVisible(true);
          this.revalidate();
          this.repaint();
          return;
        } catch (IllegalArgumentException err) {
          JFrame frame = new JFrame();
          JOptionPane.showMessageDialog(frame, err.getMessage(), "Error creating new layer",
              JOptionPane.ERROR_MESSAGE);
        }
      }
    });

    uploadButton.addActionListener(evt -> {
      JFrame frame = new JFrame();
      JFrame errorFrame = new JFrame();
      JFileChooser fc = new JFileChooser();
      if (layers.isEmpty()) {
        JOptionPane.showMessageDialog(errorFrame,
            "Please create a layer to upload an image",
            "Invalid State", JOptionPane.ERROR_MESSAGE);
        return;
      }
      fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
      fc.addChoosableFileFilter(new FileNameExtensionFilter("Images",
          "png", "jpeg", "ppm"));
      fc.setAcceptAllFileFilterUsed(false);
      int res = fc.showOpenDialog(frame);
      if (res == JFileChooser.APPROVE_OPTION) {
        String path = fc.getSelectedFile().getAbsolutePath();
        try {
          features.uploadImage(path);
        } catch (IOException | IllegalStateException | IllegalArgumentException e) {
          JOptionPane.showMessageDialog(errorFrame, e.getMessage(), "IO Error reading file",
              JOptionPane.ERROR_MESSAGE);
        }
      }
    });

    importAll.addActionListener(evt -> {
      JFrame frame = new JFrame();
      JFileChooser fc = new JFileChooser();
      fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
      fc.addChoosableFileFilter(new FileNameExtensionFilter("Text", "txt"));
      fc.setAcceptAllFileFilterUsed(false);
      int res = fc.showOpenDialog(frame);
      if (res == JFileChooser.APPROVE_OPTION) {
        String path = fc.getSelectedFile().getPath();
        try {
          features.importAll(path);
          buttonProcessor(path, features);
        } catch (IllegalStateException | IOException | IllegalArgumentException e) {
          JFrame errorFrame = new JFrame();
          JOptionPane.showMessageDialog(errorFrame, e.getMessage(), "IO Error reading files",
              JOptionPane.ERROR_MESSAGE);
        }
      }
    });

    exportCurrent.addActionListener(evt -> {
      JFrame frame = new JFrame();
      JFileChooser fc = new JFileChooser();
      fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
      fc.addChoosableFileFilter(new FileNameExtensionFilter("Images", "jpg",
          "png", "jpeg", "ppm"));
      fc.setAcceptAllFileFilterUsed(false);
      int res = fc.showSaveDialog(frame);
      if (res == JFileChooser.APPROVE_OPTION) {
        String path = fc.getSelectedFile().getAbsolutePath();
        //try {
        try {
          features.exportCurrentLayer(path);
        } catch (IOException | IllegalStateException | IllegalArgumentException e) {
          JFrame errorFrame = new JFrame();
          JOptionPane.showMessageDialog(errorFrame, e.getMessage(), "Error saving file",
              JOptionPane.ERROR_MESSAGE);
        }
      }
    });

    exportAll.addActionListener(evt -> {
      JFrame frame = new JFrame();
      JFileChooser fc = new JFileChooser();
      fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
      int res = fc.showSaveDialog(frame);
      fc.addChoosableFileFilter(new FileNameExtensionFilter("Text", "txt"));
      fc.setAcceptAllFileFilterUsed(false);
      if (res == JFileChooser.APPROVE_OPTION) {
        String path = fc.getCurrentDirectory().getAbsolutePath();
        try {
          features.exportAll(path);
        } catch (IllegalStateException | IOException e) {
          JFrame errorFrame = new JFrame();
          JOptionPane.showMessageDialog(errorFrame, e.getMessage(), "Error exporting all",
              JOptionPane.ERROR_MESSAGE);
        }
      }
    });
  }

  /**
   * This method is used when new layers are being added to the application. It makes sure that the
   * buttons are added in an appropriate order and when pressed will change the "current" layer of
   * the model.
   *
   * @param path     the String path where the "output.txt" file used by the import all button can
   *                 be found, this file will be used for the order and names of the buttons
   * @param features a Features object or Swing controller where the actions will be performed
   * @throws FileNotFoundException if the file is not found at the given path
   */
  private void buttonProcessor(String path, Features features) throws FileNotFoundException {
    File file = new File(path + "/output.txt");

    // make a list with every word
    Scanner input = new Scanner(file);

    List<String> in = new ArrayList<String>();

    while (input.hasNextLine()) {
      in.add(input.nextLine());
    }

    for (int i = in.size() - 1; i >= 0; i--) {
      String name = in.get(i);
      JButton button = new JButton(name);

      layersPanel.add(button, 1);
      layerAction(features, button, name);
      button.addActionListener(ev -> {
        resetLayerColors();
        layerAction(features, button, name);
      });

      layers.put(name, button);
      button.setVisible(true);
      this.revalidate();
    }
    this.repaint();
  }

  /**
   * Resets all the colors of the buttons. Then, it sets the clicked layer button as the current
   * layer and sets the color as blue to show it’s clicked.
   *
   * @param features a Features object (the Controller) to do the action once the button is pressed
   * @param button   the JButton that is now the current
   * @param name     the String name of the layer that is now the current
   */
  private void layerAction(Features features, JButton button, String name) {
    resetLayerColors();
    features.setCurrent(name);
    button.setBackground(Color.blue);
    button.setOpaque(true);
  }

  @Override
  public void repaintHelpWithImage(BufferedImage img) {
    ImageIcon image = new ImageIcon(img);
    imageLabel.setIcon(image);
    this.revalidate();
    this.repaint();
  }

  @Override
  public void repaintHelp() {
    this.revalidate();
    this.repaint();
  }

  /**
   * This method resets the color of the layer buttons. These are the buttons that correspond to
   * each layer. This method brings every button's color to white (even the current selected one).
   */
  private void resetLayerColors() {
    for (Map.Entry<String, JButton> el : layers.entrySet()) {
      el.getValue().setBackground(Color.white);
      el.getValue().setOpaque(true);
    }
  }
}