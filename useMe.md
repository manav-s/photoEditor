# ** Using the Image Processing Application ** #

### Running the application ###

To run the application, double-click on the JAR file located inside the res folder. You can also run
the Main class by opening the project in an IDE.

Running the JAR straight out of the package will launch it in GUI mode. To use it in script mode,
navigate to the Main class in the controller and follow the instructions for launching it in script mode.

The instructions are as follows: "However, if you would like to use the scripting method uncomment
line 29 & 30, and comment out line 33 & line 34."

### Upload ###

When trying to upload a file, to start the upload process you must first use the "upload"
keyword. Then, you must give the program the "filename.extenstion" of the photo you wish to upload
to the model. Note, you must have a layer created beforehand.

In the GUI, you can press the "Upload image" button. The image will be uploaded to the current
layer.

### Set ###

When trying to change the current layer, start the processing by using the keyword "set". Then, you
must provide the name of the layer you would like to transform into the current layer.

In the GUI, you can simply press the name of the layer that you would like to make the current.

### Blur ###

When trying to blur the current layer, start the processing by using the keyword "blur". The layer
that is current will be blurred. Note, a layer must be previously designated as current.

In the GUI, you can simply press the "blur" button on the left panel to blur the current image.

### Sharpen ###

When trying to sharpen the current layer, start the processing by using the keyword "sharpen". The
layer that is current will be sharpened. Note, a layer must be previously designated as current.

In the GUI, you can simply press the "sharpen" button on the left panel to sharpen the current
image.

### Greyscale ###

When trying to greyscale the current layer, start the processing by using the keyword "greyscale".
The layer that is current will be greyscaled. Note, a layer must be previously designated as
current.

In the GUI, you can simply press the "greyscale" button on the left panel to greyscale the current
image.

### Sepia ###

When trying to sepia the current layer, start the processing by using the keyword "sepia". The layer
that is current will be "sepia"-ed. Note, a layer must be previously designated as current.

In the GUI, you can simply press the "sepia" button on the left panel to sepia the current image.

### New Layer ###

When trying to add a new layer, start the process by using the keyword "newlayer". Then, you must
input the name of the new layer. Note, the layer name cannot be the same as an existing layer.

In the GUI, you can simply press the "new layer" button to add a new layer. The program will then
prompt you for a name for this new layer. Press "okay" when you are done.

### Duplicate ###

When trying to duplicate a layer, start the process by using the keyword "duplicate". Now, input the
name of the new layer and then the name of the layer you would like to duplicate.

In the GUI, you can simply press the "Duplicate layer" button to duplicate the current. The program
will then prompt you for a name for this new layer. Press "okay" when you are done.

### Delete ###

When trying to delete a layer, start the process by using the keyword "delete". Then, input the name
of the layer you would like deleted.

In the GUI, you can simply press the "Delete layer" button to delete the current layer. The program
will automatically remove the layer and its button.

### Export ###

When trying to export the topmost visible layer, start by using the "export" keyword. Then input the
name that you would like to export the file as, including the extension.

In the GUI, you can simply press the "Export current" button to export the current layer. The
program will then prompt you to specify the path and name of the file. Currently, the program only
supports .png, .jpeg, and .ppn export.

### Export All ###

When exporting all the layers, start the process by using the "exportall" keyword. This will take
all the layers, put them into a custom folder along with a text file (output.txt)
with the layer names in order.

In the GUI, you can simply press the "Export all layers" button to export all the layers. The
program will then prompt you to specify the path of the folder that will be made. The files will be
exported to the given folder in a special format with a "output.txt" file that details the order of
the layers in the program.

### Import All ###

When importing a series of layers, start the process by using the "importall" keyword. Then, provide
the path where the program can find all the individual layer files and the output.txt file with the
names of the layers in order.

In the GUI, you can simply press the "Import all layers" button to export all the layers. The
program will then prompt you to specify the path of the folder that will be made. The files will be
exported to the given folder in a special format with a "output.txt" file that details the order of
the layers in the program.



