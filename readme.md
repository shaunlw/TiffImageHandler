This program blurs and detects the edge of tiff images.

# To compile:

javac -cp "jai_core.jar:jai_codec.jar" *.java

# To blur an imag

java Blur image.tiff 3

# To detect edges of a tiff image:

java Sobel image.tiff 5

In both commands, the integer parameter indicates number of iterations (default 1 if left unspecified).


