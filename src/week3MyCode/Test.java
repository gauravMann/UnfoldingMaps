package week3MyCode;

import processing.core.*;

public class Test extends PApplet {
	/* don't what is this for */
	private static final long serialVersionUID = 1L;
	private String URL = "/home/knuth/Documents/Github/UnfoldingMaps/data/palmTrees.jpg";
	private PImage backgroundImg;

	public void setup() {
		size(800, 800);

	}

	public void draw() {
		backgroundImg = loadImage(URL, "jpg");
		backgroundImg.resize(0, height);
		image(backgroundImg, 0, 0);
		
		int[] colors = fillColors(second());
		fill(colors[0], colors[1], colors[2]);
		ellipse(width / 4, height / 4, width / 10, width / 10);

	}

	public static int[] fillColors(float second) {
		int[] rgb = new int[3];

		float diff = Math.abs(30 - second);
		float ratio = diff / 30;

		rgb[0] = (int) (255 * ratio);
		rgb[1] = (int) (255 * ratio);
		rgb[2] = 0;
		return rgb;
	}
}
