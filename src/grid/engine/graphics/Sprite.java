package me.grid.engine.graphics;

import me.grid.engine.util.vector2i;

public class Sprite {

	/*
	 * This class gives 'things' graphics to be displayed on screen using a
	 * spritesheet (see spritesheet class, and spritesheet from resources folder
	 * (package explorer))
	 */

	public final int SIZE;
	private int x, y;
	private int width, height;
	public int[] pixels;
	protected SpriteSheet sheet;

	public vector2i drawPosition;
	private DRAW_ORDER drawOrder;

	protected Sprite(SpriteSheet sheet, int width, int height) {
		drawPosition = new vector2i(0, 0);
		SIZE = (width == height) ? width : -1;
		this.width = width;
		this.height = height;
		this.sheet = sheet;
	}

	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		drawPosition = new vector2i(0, 0);
		SIZE = size;
		this.width = size;
		this.height = size;
		pixels = new int[SIZE * SIZE];
		this.x = x * SIZE;
		this.y = y * SIZE;
		this.sheet = sheet;
		load();
	}

	public Sprite(int width, int height, int color) {
		drawPosition = new vector2i(0, 0);
		SIZE = 0;
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		setColour(color);
	}

	public Sprite(int size, int color) {
		drawPosition = new vector2i(0, 0);
		SIZE = size;
		this.width = size;
		this.height = size;
		pixels = new int[SIZE * SIZE];
		setColour(color);
	}

	public Sprite(int[] pixels, int width, int height) {
		drawPosition = new vector2i(0, 0);
		SIZE = (width == height) ? width : -1;
		this.width = width;
		this.height = height;
		this.pixels = new int[pixels.length];
		for (int i = 0; i < pixels.length; i++) {
			this.pixels[i] = pixels[i];
		}
	}

	private void setColour(int color) {
		for (int i = 0; i < width * height; i++) {
			pixels[i] = color;
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	private void load() { // helps load the sprite into the world
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				pixels[x + y * width] = sheet.pixels[(x + this.x) + ((y + this.y) * sheet.SPRITE_WIDTH)];
			}
		}
	}

	public static Sprite rotate(Sprite sprite, double angle) {
		return new Sprite(rotate(sprite.pixels, sprite.width, sprite.height, angle), sprite.width, sprite.height);
	}

	public static Sprite[] split(SpriteSheet sheet) {
		int amount = (sheet.getWidth() * sheet.getHeight()) / (sheet.SPRITE_WIDTH * sheet.SPRITE_HEIGHT);
		Sprite[] sprites = new Sprite[amount];
		int current = 0;
		int[] pixels = new int[sheet.SPRITE_WIDTH * sheet.SPRITE_HEIGHT];

		for (int yp = 0; yp < sheet.getHeight() / sheet.SPRITE_HEIGHT; yp++) {
			for (int xp = 0; xp < sheet.getWidth() / sheet.SPRITE_WIDTH; xp++) {

				for (int y = 0; y < sheet.SPRITE_HEIGHT; y++) {
					for (int x = 0; x < sheet.SPRITE_WIDTH; x++) {
						int xo = x + xp * sheet.SPRITE_WIDTH;
						int yo = y + yp * sheet.SPRITE_HEIGHT;
						pixels[x + y * sheet.SPRITE_WIDTH] = sheet.getPixels()[xo + yo * sheet.getWidth()]; // ArrayIndexOutOfBoundsException
																											// thrown
																											// here
					}
				}
				sprites[current++] = new Sprite(pixels, sheet.SPRITE_WIDTH, sheet.SPRITE_HEIGHT);
			}
		}
		return sprites;
	}

	private static int[] rotate(int[] pixels, int width, int height, double angle) {
		int[] result = new int[width * height];
		double nx_x = rotX(-angle, 1.0, 0.0);
		double nx_y = rotY(-angle, 1.0, 0.0);
		double ny_x = rotX(-angle, 0.0, 1.0);
		double ny_y = rotY(-angle, 0.0, 1.0);
		double x0 = rotX(-angle, -width / 2.0, -height / 2.0) + width / 2.0;
		double y0 = rotY(-angle, -width / 2.0, -height / 2.0) + height / 2.0;
		for (int y = 0; y < height; y++) {
			double x1 = x0;
			double y1 = y0;
			for (int x = 0; x < width; x++) {
				int xx = (int) x1;
				int yy = (int) y1;
				int col = 0;
				if (xx < 0 || xx >= width || yy < 0 || yy >= height)
					col = 0xff000000;
				else
					col = pixels[xx + yy * width];
				result[x + y * width] = col;
				x1 += nx_x;
				y1 += nx_y;
			}
			x0 += ny_x;
			y0 += ny_y;
		}
		return result;
	}

	private static double rotX(double angle, double x, double y) {
		double cos = Math.cos(angle - Math.PI / 2);
		double sin = Math.sin(angle - Math.PI / 2);
		return x * cos + y * -sin;
	}

	private static double rotY(double angle, double x, double y) {
		double cos = Math.cos(angle - Math.PI / 2);
		double sin = Math.sin(angle - Math.PI / 2);
		return x * sin + y * cos;
	}

	public DRAW_ORDER getDrawOrder() {
		return this.drawOrder;
	}

	public void setDrawOrder(DRAW_ORDER order) {
		this.drawOrder = order;
	}
}
