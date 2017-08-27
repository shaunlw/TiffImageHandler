public class Pixel{
	public int x;
	public int y;
	public short red;
	public short green;
	public short blue;
	
	public Pixel(){
		red = 0;
		green = 0;
		blue = 0;
		x = 0;
		y = 0;
	}
	
	public Pixel(int x, int y){
		red = 0;
		green = 0;
		blue = 0;
		this.x = x;
		this.y = y;
	}
	
	public Pixel(int x, int y, short red, short green, short blue){
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.x = x;
		this.y = y;
	}
}