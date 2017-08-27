/* PixImage.java */

/**
 *  The PixImage class represents an image, which is a rectangular grid of
 *  color pixels.  Each pixel has red, green, and blue intensities in the range
 *  0...255.  Descriptions of the methods you must implement appear below.
 *  They include a constructor of the form
 *
 *      public PixImage(int width, int height);
 *
 *  that creates a black (zero intensity) image of the specified width and
 *  height.  Pixels are numbered in the range (0...width - 1, 0...height - 1).
 *
 *  All methods in this class must be implemented to complete Part I.
 *  See the README file accompanying this project for additional details.
 */

public class PixImage {

  /**
   *  Define any variables associated with a PixImage object here.  These
   *  variables MUST be private.
   */

	private int width;
	private int height;
	private Pixel [][] pixels;

  /**
   * PixImage() constructs an empty PixImage with a specified width and height.
   * Every pixel has red, green, and blue intensities of zero (solid black).
   *
   * @param width the width of the image.
   * @param height the height of the image.
   */
    // Your solution here.
  public PixImage(int w, int h) {
  	width = w;
  	height = h;
  	pixels = new Pixel [h][w];
  	
  	for (int i = 0; i < height;i++) {
  		for (int j = 0; j < width; j++)
  			pixels[i][j] = new Pixel(i,j);//constructor of Pixel with RGB all set to zero.
  	}
  }

  /**
   * getWidth() returns the width of the image.
   *
   * @return the width of the image.
   */
  public int getWidth() {
    // Replace the following line with your solution.
    return width;
  }

  /**
   * getHeight() returns the height of the image.
   *
   * @return the height of the image.
   */
  public int getHeight() {
    // Replace the following line with your solution.
    return height;
  }

  /**
   * getRed() returns the red intensity of the pixel at coordinate (x, y).
   *
   * @param x the x-coordinate of the pixel.
   * @param y the y-coordinate of the pixel.
   * @return the red intensity of the pixel at coordinate (x, y).
   */
  public short getRed(int x, int y) {
    // Replace the following line with your solution.
    return pixels[y][x].red;//(x,y) coordinate in the image is stored as pixels[y][x]
  }
  

  /**
   * getGreen() returns the green intensity of the pixel at coordinate (x, y).
   *
   * @param x the x-coordinate of the pixel.
   * @param y the y-coordinate of the pixel.
   * @return the green intensity of the pixel at coordinate (x, y).
   */
  public short getGreen(int x, int y) {
    // Replace the following line with your solution.
    return pixels[y][x].green;
  }

  /**
   * getBlue() returns the blue intensity of the pixel at coordinate (x, y).
   *
   * @param x the x-coordinate of the pixel.
   * @param y the y-coordinate of the pixel.
   * @return the blue intensity of the pixel at coordinate (x, y).
   */
  public short getBlue(int x, int y) {
    // Replace the following line with your solution.
    return pixels[y][x].blue;
  }

  /**
   * setPixel() sets the pixel at coordinate (x, y) to specified red, green,
   * and blue intensities.
   *
   * If any of the three color intensities is NOT in the range 0...255, then
   * this method does NOT change any of the pixel intensities.
   *
   * @param x the x-coordinate of the pixel.
   * @param y the y-coordinate of the pixel.
   * @param red the new red intensity for the pixel at coordinate (x, y).
   * @param green the new green intensity for the pixel at coordinate (x, y).
   * @param blue the new blue intensity for the pixel at coordinate (x, y).
   */
  public void setPixel(int x, int y, short red, short green, short blue) {
    // Your solution here.
    if ((x >= 0 && x <= getWidth() -1) && (y >= 0 && y <= getHeight() -1)) 
    	if ((red >=0 && red <= 255) && (green >=0 && green <= 255) && 
    	(blue >=0 && blue <= 255)){
    		pixels[y][x].red = red;
    		pixels[y][x].green = green;
    		pixels[y][x].blue = blue;
    	}
    	else {
    		System.out.println("Color code out of range.");
    		System.exit(0);
    	}
	else System.out.println("Coordinates out of image bound.");
  }

  /**
   * toString() returns a String representation of this PixImage.
   *
   * This method isn't required, but it should be very useful to you when
   * you're debugging your code.  It's up to you how you represent a PixImage
   * as a String.
   *
   * @return a String representation of this PixImage.
   */
  public String toString() {
    // needs to output the transpose the stored [][]
	String str = "[";
    for (int i = 0; i < getWidth(); i++){
    	for (int j = 0; j < getHeight(); j++){
    		str = str + "( " + pixels[j][i].red + ", " + 
    			pixels[j][i].green + ", " + pixels[j][i].blue + " ) ";
    	}
    	str += "\n";
    }
    str = str+ "]";
    return str;
  }

  /**
   * boxBlur() returns a blurred version of "this" PixImage.
   *
   * If numIterations == 1, each pixel in the output PixImage is assigned
   * a value equal to the average of its neighboring pixels in "this" PixImage,
   * INCLUDING the pixel itself.
   *
   * A pixel not on the image boundary has nine neighbors--the pixel itself and
   * the eight pixels surrounding it.  A pixel on the boundary has six
   * neighbors if it is not a corner pixel; only four neighbors if it is
   * a corner pixel.  The average of the neighbors is the sum of all the
   * neighbor pixel values (including the pixel itself) divided by the number
   * of neighbors, with non-integer quotients rounded toward zero (as Java does
   * naturally when you divide two integers).
   *
   * Each color (red, green, blue) is blurred separately.  The red input should
   * have NO effect on the green or blue outputs, etc.
   *
   * The parameter numIterations specifies a number of repeated iterations of
   * box blurring to perform.  If numIterations is zero or negative, "this"
   * PixImage is returned (not a copy).  If numIterations is positive, the
   * return value is a newly constructed PixImage.
   *
   * IMPORTANT:  DO NOT CHANGE "this" PixImage!!!  All blurring/changes should
   * appear in the new, output PixImage only.
   *
   * @param numIterations the number of iterations of box blurring.
   * @return a blurred version of "this" PixImage.
   */
  public PixImage boxBlur(int numIterations) {
  	int num = numIterations;
    // Replace the following line with your solution.
    if (num <= 0) return this;
    else {
    		PixImage blurredImage = new PixImage (getWidth(), getHeight());
    		short tempR = 0;
    		short tempG = 0;
    		short tempB = 0;
    		int maxw = getWidth();
    		int maxh = getHeight();
    	
			//top-left corner, 4 neighbors
    		tempR = (short)((getRed(0,0)+getRed(1,0)+getRed(1,1)+getRed(0,1))/4);
    		tempG = (short)((getGreen(0,0)+getGreen(1,0)+getGreen(1,1)+getGreen(0,1))/4);
    		tempB = (short)((getBlue(0,0)+getBlue(1,0)+getBlue(1,1)+getBlue(0,1))/4);
    		blurredImage.setPixel(0,0,tempR,tempG,tempB);
    		
    		//top-right corner, 4 neighbors
    		tempR = (short)((getRed(maxw - 1,0)+getRed(maxw - 2,0)+
    				getRed(maxw-2,1)+getRed(maxw - 1,1))/4);
    		tempG = (short)((getGreen(maxw - 1,0)+getGreen(maxw - 2,0)+
    				getGreen(maxw-2,1)+getGreen(maxw - 1,1))/4);
    		tempB = (short)((getBlue(maxw - 1,0)+getBlue(maxw - 2,0)+
    				getBlue(maxw-2,1)+getBlue(maxw - 1,1))/4);
    		blurredImage.setPixel(maxw - 1,0, tempR,tempG,tempB);
    		
    		//bottom-left corner, 4 neighbors
    		tempR = (short)((getRed(0, maxh - 1) + getRed(0,maxh - 2) + 
    				getRed(1,maxh - 2) + getRed(1, maxh - 1))/4);
    		tempG = (short)((getGreen(0, maxh - 1) + getGreen(0,maxh - 2) + 
    				getGreen(1,maxh - 2) + getGreen(1, maxh - 1))/4);
    		tempB = (short)((getBlue(0, maxh - 1) + getBlue(0,maxh - 2) + 
    				getBlue(1,maxh - 2) + getBlue(1, maxh - 1))/4);
    		blurredImage.setPixel(0, maxh - 1,tempR,tempG,tempB);

			//bottom-right corner, 4 neighbors
			tempR = (short)((getRed(maxw - 1, maxh - 1) + 
					getRed(maxw - 2, maxh - 1) + 
					getRed(maxw - 2, maxh - 2) + 
					getRed(maxw - 1, maxh - 2))/4);
			tempG = (short)((getGreen(maxw - 1, maxh - 1) + 
					getGreen(maxw - 2, maxh - 1) + 
					getGreen(maxw - 2, maxh - 2) + 
					getGreen(maxw - 1, maxh - 2))/4);
			tempB = (short)((getBlue(maxw - 1, maxh - 1) + 
					getBlue(maxw - 2, maxh - 1) + 
					getBlue(maxw - 2, maxh - 2) + 
					getBlue(maxw - 1, maxh - 2))/4);
    		blurredImage.setPixel(maxw - 1,maxh - 1,tempR,tempG,tempB);
    		
    		//horizontal edges of physical image, not array, 6 neighbors
    		for (int x = 1; x <= maxw - 2; x++){
    			//upper edge
    			tempR = (short)((getRed(x,0) + getRed(x-1,0) + 
    					getRed(x+1,0) + getRed(x-1,1) + 
    					getRed(x,1) + getRed(x+1,1))/6);
    			tempG = (short)((getGreen(x,0) + getGreen(x-1,0) + 
    					getGreen(x+1,0) + getGreen(x-1,1) + 
    					getGreen(x,1) + getGreen(x+1,1))/6);
    			tempB = (short)((getBlue(x,0) + getBlue(x-1,0) + 
    					getBlue(x+1,0) + getBlue(x-1,1) + 
    					getBlue(x,1) + getBlue(x+1,1))/6);
    			blurredImage.setPixel(x,0,tempR, tempG, tempB);
    			
    			//lower edge
    			tempR = (short)((getRed(x-1, maxh - 1) + 
    					getRed(x, maxh - 1) + 
    					getRed(x+1, maxh - 1) + 
    					getRed(x-1, maxh - 2) + 
    					getRed(x,maxh - 2) + 
    					getRed(x+1, maxh - 2))/6);
    			tempG = (short)((getGreen(x-1, maxh - 1) + 
    					getGreen(x, maxh - 1) + 
    					getGreen(x+1, maxh - 1) + 
    					getGreen(x-1, maxh - 2) + 
    					getGreen(x,maxh - 2) + 
    					getGreen(x+1, maxh - 2))/6);
    			tempB = (short)((getBlue(x-1, maxh - 1) + 
    					getBlue(x, maxh - 1) + 
    					getBlue(x+1, maxh - 1) + 
    					getBlue(x-1, maxh - 2) + 
    					getBlue(x,maxh - 2) + 
    					getBlue(x+1, maxh - 2))/6);
    			blurredImage.setPixel(x, maxh - 1, tempR, tempG, tempB);
    		}
    		
    		//vertical edges, 6 neighbors
    		for (int y = 1; y <= getHeight() - 2; y++) {
    			//left edge
    			tempR = (short)((getRed(0, y-1) + getRed(0, y) + 
    					getRed(0, y+1) + getRed(1, y-1) + 
    					getRed(1, y) + getRed(1, y+1))/6);
    			tempG = (short)((getGreen(0, y-1) + getGreen(0, y) + 
    					getGreen(0, y+1) + getGreen(1, y-1) + 
    					getGreen(1, y) + getGreen(1, y+1))/6);
    			tempB = (short)((getBlue(0, y-1) + getBlue(0, y) + 
    					getBlue(0, y+1) + getBlue(1, y-1) + 
    					getBlue(1, y) + getBlue(1, y+1))/6);
    			blurredImage.setPixel(0,y, tempR, tempG, tempB);
    			
    			//right edge
    			tempR = (short)((getRed(maxw-1, y-1) + getRed(maxw-1, y) + 
    					getRed(maxw-1, y+1) + getRed(maxw - 2, y-1) + 
    					getRed(maxw - 2,y) + getRed(maxw - 2,y+1))/6);
    			tempG = (short)((getGreen(maxw-1, y-1) + getGreen(maxw-1, y) + 
    					getGreen(maxw-1, y+1) + getGreen(maxw - 2, y-1) + 
    					getGreen(maxw - 2,y) + getGreen(maxw - 2,y+1))/6);
    			tempB = (short)((getBlue(maxw-1, y-1) + getBlue(maxw-1, y) + 
    					getBlue(maxw-1, y+1) + getBlue(maxw - 2, y-1) + 
    					getBlue(maxw - 2,y) + getBlue(maxw - 2,y+1))/6);
    			blurredImage.setPixel(maxw-1, y, tempR, tempG, tempB);    
    		}
    		
    		//regular pixels with 9 neighbors
    		for (int x = 1; x <= maxw - 2; x++){
    			for (int y = 1; y <= maxh - 2; y++){
    				tempR = (short)((getRed(x-1, y-1) + getRed(x, y-1) + 
    						getRed(x+1, y-1) + getRed(x-1,y) + 
    						getRed(x, y) + getRed(x+1,y) + 
    						getRed(x-1,y+1) + getRed(x,y+1) + getRed(x+1,y+1))/9);
    				tempG = (short)((getGreen(x-1, y-1) + getGreen(x, y-1) + 
    						getGreen(x+1, y-1) + getGreen(x-1,y) + 
    						getGreen(x, y) + getGreen(x+1,y) + 
    						getGreen(x-1,y+1) + getGreen(x,y+1) + getGreen(x+1,y+1))/9);
    				tempB = (short)((getBlue(x-1, y-1) + getBlue(x, y-1) + 
    						getBlue(x+1, y-1) + getBlue(x-1,y) + 
    						getBlue(x, y) + getBlue(x+1,y) + 
    						getBlue(x-1,y+1) + getBlue(x,y+1) + getBlue(x+1,y+1))/9);
    				blurredImage.setPixel(x,y,tempR,tempG,tempB);
    			}
    		}
    		//one numIteration is finished; if more iterations, repeat boxBlur recursively
/*
    		while(numIterations != 0) blurredImage.boxBlur(numIterations);
*/
    		return blurredImage.boxBlur(num-1);
    }
  }

  /**
   * mag2gray() maps an energy (squared vector magnitude) in the range
   * 0...24,969,600 to a grayscale intensity in the range 0...255.  The map
   * is logarithmic, but shifted so that values of 5,080 and below map to zero.
   *
   * DO NOT CHANGE THIS METHOD.  If you do, you will not be able to get the
   * correct images and pass the autograder.
   *
   * @param mag the energy (squared vector magnitude) of the pixel whose
   * intensity we want to compute.
   * @return the intensity of the output pixel.
   */
  private static short mag2gray(long mag) {
    short intensity = (short) (30.0 * Math.log(1.0 + (double) mag) - 256.0);

    // Make sure the returned intensity is in the range 0...255, regardless of
    // the input value.
    if (intensity < 0) {
      intensity = 0;
    } else if (intensity > 255) {
      intensity = 255;
    }
    return intensity;
  }

  /**
   * sobelEdges() applies the Sobel operator, identifying edges in "this"
   * image.  The Sobel operator computes a magnitude that represents how
   * strong the edge is.  We compute separate gradients for the red, blue, and
   * green components at each pixel, then sum the squares of the three
   * gradients at each pixel.  We convert the squared magnitude at each pixel
   * into a grayscale pixel intensity in the range 0...255 with the logarithmic
   * mapping encoded in mag2gray().  The output is a grayscale PixImage whose
   * pixel intensities reflect the strength of the edges.
   *
   * See http://en.wikipedia.org/wiki/Sobel_operator#Formulation for details.
   *
   * @return a grayscale PixImage representing the edges of the input image.
   * Whiter pixels represent stronger edges.
   */
  public PixImage sobelEdges() {
    // create an image with ages doubled
    PixImage reflectedImage = reflect();
    
    //create an output image
    PixImage grayImage = new PixImage(getWidth(), getHeight());
    
    //for each pixel, calculate gradient for R,G,B, then calculate energy for RGB, 
    //then calculate energy for the pixel,    
    //then convert pixel energy to pixel intensity with mag2gray method
    for (int x = 1; x < getWidth() + 1;x++){
    	for (int y = 1; y < getHeight()+1; y++){
    		short dxR = (short)(1*reflectedImage.getRed(x-1,y-1)-1*reflectedImage.getRed(x+1,y-1) + 
    					2*reflectedImage.getRed(x-1,y)-2*reflectedImage.getRed(x+1,y) + 
    					1*reflectedImage.getRed(x-1,y+1)-1*reflectedImage.getRed(x+1,y+1));
    		short dyR = (short)(1*reflectedImage.getRed(x-1,y-1) + 2*reflectedImage.getRed(x,y-1) + 
    					1*reflectedImage.getRed(x+1,y-1) -1*reflectedImage.getRed(x-1,y+1) - 
    					2*reflectedImage.getRed(x,y+1) - 1*reflectedImage.getRed(x+1,y+1));
    		int energyR = (int)Math.pow(dxR,2) + (int)Math.pow(dyR,2);
    		
    		short dxG = (short)(1*reflectedImage.getGreen(x-1,y-1)-1*reflectedImage.getGreen(x+1,y-1) + 
    					2*reflectedImage.getGreen(x-1,y)-2*reflectedImage.getGreen(x+1,y) + 
    					1*reflectedImage.getGreen(x-1,y+1)-1*reflectedImage.getGreen(x+1,y+1));
    		short dyG = (short)(1*reflectedImage.getGreen(x-1,y-1) + 2*reflectedImage.getGreen(x,y-1) + 1*reflectedImage.getGreen(x+1,y-1) -
    					1*reflectedImage.getGreen(x-1,y+1) - 2*reflectedImage.getGreen(x,y+1) - 1*reflectedImage.getGreen(x+1,y+1));
    		int energyG = (int)Math.pow(dxG,2) + (int)Math.pow(dyG,2);
    		
    		short dxB = (short)(1*reflectedImage.getBlue(x-1,y-1)-1*reflectedImage.getBlue(x+1,y-1) + 
    					2*reflectedImage.getBlue(x-1,y)-2*reflectedImage.getBlue(x+1,y) + 
    					1*reflectedImage.getBlue(x-1,y+1)-1*reflectedImage.getBlue(x+1,y+1));
    		short dyB = (short)(1*reflectedImage.getBlue(x-1,y-1) + 2*reflectedImage.getBlue(x,y-1) + 1*reflectedImage.getBlue(x+1,y-1) -
    					1*reflectedImage.getBlue(x-1,y+1) - 2*reflectedImage.getBlue(x,y+1) - 1*reflectedImage.getBlue(x+1,y+1));
    		int energyB = (int)Math.pow(dxB,2) + (int)Math.pow(dyB,2);
    		    		
    		int energyT = energyR + energyG + energyB; 
    		
    		short intensity = mag2gray(energyT);
    		grayImage.setPixel(x-1,y-1,intensity,intensity,intensity);   		
    	}
    }
    return grayImage;
    // Don't forget to use the method mag2gray() above to convert energies to
    // pixel intensities.
  }
  
  public PixImage reflect(){
  	//create a new image with reflected edges
  	PixImage reflectedImage = new PixImage(getWidth() + 2, getHeight() + 2);
  	int maxw = reflectedImage.getWidth();
  	int maxh = reflectedImage.getHeight();
  	//copy pixels from original image to the new image
  	for (int x = 0; x < getWidth(); x++){
  		for (int y = 0; y < getHeight(); y++){
  			reflectedImage.setPixel(x+1, y+1, getRed(x,y), getGreen(x,y), getBlue(x,y));
  		}
  	}
  	
  	//reflect horizontal edges
  	for (int x = 1; x < maxw - 1; x++){
  		reflectedImage.setPixel(x,0,getRed(x-1,0),getGreen(x-1,0),getBlue(x-1,0));
  		reflectedImage.setPixel(x, maxh-1,getRed(x-1, getHeight()-1),getGreen(x-1, getHeight()-1),getBlue(x-1, getHeight()-1));
  	}
  	
  	//reflect vertical edges
  	for (int y = 1; y < maxh -1; y++){
  		reflectedImage.setPixel(0, y,getRed(0,y-1),getGreen(0, y-1),getBlue(0, y-1));  		
  		reflectedImage.setPixel(maxw-1, y,getRed(getWidth()-1, y-1),getGreen(getWidth()-1, y-1),getBlue(getWidth()-1, y-1));
  	}
  	
  	//reflect corners
  	reflectedImage.setPixel(0,0,getRed(0,0),getGreen(0,0),getBlue(0,0));
  	reflectedImage.setPixel(maxw - 1,0, getRed(getWidth()-1, 0),getGreen(getWidth()-1, 0),getBlue(getWidth()-1, 0));
	reflectedImage.setPixel(0, maxh - 1,getRed(0, getHeight()-1),getGreen(0, getHeight()-1),getBlue(0, getHeight()-1));
	reflectedImage.setPixel(maxw-1,maxh - 1,getRed(getWidth()-1,getHeight()-1),getGreen(getWidth()-1,getHeight()-1),getBlue(getWidth()-1,getHeight()-1));
  	
  	return reflectedImage;
  }


  /**
   * TEST CODE:  YOU DO NOT NEED TO FILL IN ANY METHODS BELOW THIS POINT.
   * You are welcome to add tests, though.  Methods below this point will not
   * be tested.  This is not the autograder, which will be provided separately.
   */


  /**
   * doTest() checks whether the condition is true and prints the given error
   * message if it is not.
   *
   * @param b the condition to check.
   * @param msg the error message to print if the condition is false.
   */
  private static void doTest(boolean b, String msg) {
    if (b) {
      System.out.println("Good.");
    } else {
      System.err.println(msg);
    }
  }

  /**
   * array2PixImage() converts a 2D array of grayscale intensities to
   * a grayscale PixImage.
   *
   * @param pixels a 2D array of grayscale intensities in the range 0...255.
   * @return a new PixImage whose red, green, and blue values are equal to
   * the input grayscale intensities.
   */
  private static PixImage array2PixImage(int[][] arr) {
    int width = arr.length;
    int height = arr[0].length;
    PixImage image = new PixImage(width, height);
    
    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        image.setPixel(x, y, (short) arr[x][y], (short) arr[x][y],
                       (short) arr[x][y]);
      }
    }

    return image;
  }

  /**
   * equals() checks whether two images are the same, i.e. have the same
   * dimensions and pixels.
   *
   * @param image a PixImage to compare with "this" PixImage.
   * @return true if the specified PixImage is identical to "this" PixImage.
   */
  public boolean equals(PixImage image) {
    int width = getWidth();
    int height = getHeight();

    if (image == null ||
        width != image.getWidth() || height != image.getHeight()) {
      return false;
    }

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        if (! (getRed(x, y) == image.getRed(x, y) &&
               getGreen(x, y) == image.getGreen(x, y) &&
               getBlue(x, y) == image.getBlue(x, y))) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * main() runs a series of tests to ensure that the convolutions (box blur
   * and Sobel) are correct.
   */
  public static void main(String[] args) {
    // Be forwarned that when you write arrays directly in Java as below,
    // each "row" of text is a column of your image--the numbers get
    // transposed.
    PixImage image1 = array2PixImage(new int[][] { { 0, 10, 240 },
                                                   { 30, 120, 250 },
                                                   { 80, 250, 255 } });
    System.out.println("Testing getWidth/getHeight on a 3x3 image.  " +
                       "Input image:");
    System.out.print(image1);
    doTest(image1.getWidth() == 3 && image1.getHeight() == 3,
           "Incorrect image width and height.");

    System.out.println("Testing blurring on a 3x3 image.");
    doTest(image1.boxBlur(1).equals(
           array2PixImage(new int[][] { { 40, 108, 155 },
                                        { 81, 137, 187 },
                                        { 120, 164, 218 } })),
           "Incorrect box blur (1 rep):\n" + image1.boxBlur(1));

    doTest(image1.boxBlur(2).equals(
           array2PixImage(new int[][] { { 91, 118, 146 },
                                        { 108, 134, 161 },
                                        { 125, 151, 176 } })),
           "Incorrect box blur (2 rep):\n" + image1.boxBlur(2));      
    doTest(image1.boxBlur(2).equals(image1.boxBlur(1).boxBlur(1)),
           "Incorrect box blur (1 rep + 1 rep):\n" +
           image1.boxBlur(2) + image1.boxBlur(1).boxBlur(1));

    System.out.println("Testing edge detection on a 3x3 image.");


    doTest(image1.sobelEdges().equals(
           array2PixImage(new int[][] { { 104, 189, 180 },
                                        { 160, 193, 157 },
                                        { 166, 178, 96 } })),
           "Incorrect Sobel:\n" + image1.sobelEdges());


    PixImage image2 = array2PixImage(new int[][] { { 0, 100, 100 },
                                                   { 0, 0, 100 } });
    System.out.println("Testing getWidth/getHeight on a 2x3 image.  " +
                       "Input image:");
    System.out.print(image2);
    
    doTest(image2.getWidth() == 2 && image2.getHeight() == 3,
           "Incorrect image width and height.");
           
    PixImage image3 = array2PixImage(new int[][] { {0, 100},
                                                   {100, 100},
                                                   {100, 0}});
    System.out.println("Testing getWidth/getHeight on a 3x2 image.  " +
                       "Input image:");
    System.out.print(image3);
    
    doTest(image3.getWidth() == 3 && image3.getHeight() == 2,
           "Incorrect image width and height.");


    PixImage image4 = array2PixImage(new int[][] {{0, 100}});
    System.out.println("Testing getWidth/getHeight on a 2x1 image.  " +
                       "Input image:");
    System.out.print(image4);
    
    doTest(image4.getWidth() == 1 && image4.getHeight() == 2,
           "Incorrect image width and height.");


    System.out.println("Testing blurring on a 2x3 image.");
    doTest(image2.boxBlur(1).equals(
           array2PixImage(new int[][] { { 25, 50, 75 },
                                        { 25, 50, 75 } })),
           "Incorrect box blur (1 rep):\n" + image2.boxBlur(1));
/*
    System.out.println("Testing blurring on a 2x1 image.");
    doTest(image4.boxBlur(1).equals(
           array2PixImage(new int[][] { { 25, 25},
                                        { 25, 25} })),
           "Incorrect box blur (1 rep):\n" + image4.boxBlur(1));
*/


    System.out.println("Testing edge detection on a 2x3 image.");
    doTest(image2.sobelEdges().equals(
           array2PixImage(new int[][] { { 122, 143, 74 },
                                        { 74, 143, 122 } })),
           "Incorrect Sobel:\n" + image2.sobelEdges());
  }
}
