# **Model**

### ImageRep
Represents any representation of an image that contains pixels. This interface contains methods 
that allows the client to obtain information about the ImageRep
without allowing them to mutate the Pixel data.

#### Image
An Image is an ImageRep made up of a 2d array of pixels.

### IPixel
Represents a pixel that makes up an ImageRep.

#### Pixel
Represents a single pixel in an image that contains RGB values (red, green and blue).

### IModel
The interface of a SimplePhotoModel that is parameterized over the image, allowing you to pass 
in whatever ImageRep you'd like.

#### SimplePhotoModel
Representation of a platform that allows you to get data from and modify an image that consists 
of a 2D array of IPixels.

###ILayer
Represents any representation of a layer in this photo processing application. ILayer also consists
of methods that allows the client to obtain information as well as manipulate some data within the
layer, including an image.
####Layer
Represents a layer in an image processing application that has a name, contains an ImageRep, and has
a boolean that determines whether or not the layer is visible. The client is able to get information
about the layer as well as make desired changes to manipulate the layer.

###ILayerModel
Represents a model that supports layer functionality when editing images.

- Add layers
- Delete layers
- Import layers
- export layers
- duplicate layers
- upload image to layer

####LayerModel
This class represents an alternate version of the model that is used in the photo processing
application. This model differs because it has the support for multiple layers of images.
These layers can be visible or invisible. Also, these layers can be manipulated, imported, 
and exported.


# **View**
###IView
This interface represents any text-based view of the image processing application. It is capable
of showing the layers of the current model, and having messages rendered to it.
####PhotoView
This class represents a text-based view of the image processing application. It supports
the ability to render a message and status of the model to the appendable that is stored
in its field. This allows for potential logging of script outputs.
###ISwingView
This interface represents any view for the layer version of the photo editor program.
It holds methods that can be used to add action listeners to the view and update the state of
the view, depending on events.
####SwingViewFrame
This class represents a view of the photo editor application. In particular, this is used in
accordance with a model that can support multiple layers. The view is set up with a main central
region to view the image to be edited. On the side, there are buttons that show the actions that
can be performed.

# **Controller**
####FileActions
This class represents the file actions that can be performed within the image processing model.
The class supports import and export of png, jpeg, and ppm filetypes to/from a model. Also, it is
capable of importing and exporting multilayer images (that will reside in a folder that has a
detailed text file). Storing this in the controller allows us to keep the model independent of IO 
operations.
###IController
This represents the controller interface of the photo processing application. It holds methods
that are responsible for connecting the model and view. These methods will parse through input to
perform the actions that the users specify.
####PhotoController
This class represents the controller for the image processing program. It holds methods
that are responsible for connecting the model and view. These methods will parse through input to
perform the actions that the users specify.
###Features
This interface represents a set of features for the image manipulation program that will be 
implemented by a Swing compatible controller. This interface is to be used with a controller and 
model that supports multiple layers in the program. These methods should reflect respective changes 
in the chosen view and model.
####SwingController
The SwingController class is used when certain events are triggered in the view. Once they
are triggered, the controller has to update the model to reflect the change and update the view.
