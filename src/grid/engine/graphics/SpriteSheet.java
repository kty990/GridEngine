package me.grid.engine.graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	
	private String path;
	public final int SIZE;
	public final int SPRITE_WIDTH, SPRITE_HEIGHT;
	public int[] pixels;
	private int width, height;
	
	
	private Sprite[] sprites;
	
	public SpriteSheet(String path, int size) {
		this.path = path;
		SIZE = size;
		SPRITE_WIDTH = size;
		SPRITE_HEIGHT = size;
		pixels = new int[SIZE * SIZE];
		load();
	}
	
	public SpriteSheet(String path, int width, int height) {
		this.path = path;
		SIZE = -1;
		SPRITE_WIDTH = width;
		SPRITE_HEIGHT = height;
		pixels = new int[SPRITE_WIDTH * SPRITE_HEIGHT];
		load();
	}
	
	public SpriteSheet(SpriteSheet sheet, int x, int y, int width, int height, int spriteSize) {
		int xx = x * spriteSize;
		int yy = y * spriteSize;
		int w = width * spriteSize;
		int h = height * spriteSize;
		if(width == height) SIZE = width;
		else SIZE = -1;
		SPRITE_WIDTH = w;
		SPRITE_HEIGHT = h;
		pixels = new int[w * h];
		for(int y0 = 0; y0 < h; y0++) {
			int yp = yy + y0;
			for(int x0 = 0; x0 < w; x0++) {
				int xp = xx + x0;
				pixels[x0 + y0 * w] = sheet.pixels[xp + yp * sheet.SPRITE_WIDTH];
			}
		}
		
		int frame = 0;
		sprites = new Sprite[width * height];
		for(int ya = 0; ya < height; ya++) {
			for(int xa = 0; xa < width; xa++) {
				int[] spritePixels = new int[spriteSize * spriteSize];
				for(int y0 = 0; y0 < spriteSize; y0++) {
					for(int x0 = 0; x0 < spriteSize; x0++) {
						spritePixels[x0 + y0 * spriteSize] = pixels[(x0 + xa * spriteSize) + (y0 + ya * spriteSize) * SPRITE_WIDTH]; //something must be messed up here
					}
				}
				Sprite sprite = new Sprite(spritePixels, spriteSize, spriteSize);
				sprites[frame++] = sprite;
			}
		}
		
	}
	
	public Sprite[] getSprites() {
		return sprites;
	}
	
	public int[] getPixels() {
		return pixels;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void load() {
		try {
			System.out.print("Trying to load: " + System.getProperty("user.dir") + "\\resources\\" + this.path + "... ");
			
			File file = new File(System.getProperty("user.dir") + "\\resources\\" + this.path);
		    FileInputStream fis = new FileInputStream(file);  
		    BufferedImage image = ImageIO.read(fis); //reading the image file  
		    
			System.out.println(" succeeded!");
			width = image.getWidth();
			height = image.getHeight();
			pixels = image.getRGB(0, 0, width, height, null, 0, width);
		} catch (IOException e) {
			System.err.println(" IOException occured!");
			e.printStackTrace();
		} catch (ArrayIndexOutOfBoundsException r) {
			System.err.println("ARRAYINDEXOUTOFBOUNDS");
			r.printStackTrace();
		} catch (NullPointerException l) {
			System.err.println("NULLPOINTER");
			l.printStackTrace();
		} catch (ExceptionInInitializerError p) {
			System.err.println("INIT");
			p.printStackTrace();
		} catch (IllegalArgumentException o) { //current error
			System.err.println("ILLEGAL");
			o.printStackTrace();
		} catch (Exception i) {
			i.printStackTrace();
		}
	}
	
}
