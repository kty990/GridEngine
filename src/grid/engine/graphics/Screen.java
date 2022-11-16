package me.grid.engine.graphics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import me.grid.engine.entity.Entity;
import me.grid.engine.level.Level;

class DrawComparator implements Comparator<Sprite> {

	@Override
	public int compare(Sprite e1, Sprite e2) {
		if (e1.getDrawOrder() == e2.getDrawOrder()) {
			return 0;
		} else if (e1.getDrawOrder().getValue() > e2.getDrawOrder().getValue()) {
			return 1;
		} else {
			return -1;
		}
	}
}

public class Screen {

	public int width, height;

	public int[] pixels;

	private ArrayList<Sprite> sprites = new ArrayList<Sprite>();
	
	private Level currentLevel;

	public Screen(int width, int height, Level currentLevel) {
		this.width = width;
		this.height = height;
		this.pixels = new int[width * height];
		this.currentLevel = currentLevel;
	}

	public void Draw(Sprite e, int x, int y, DRAW_ORDER drawOrder) {
		if (drawOrder == DRAW_ORDER.NONE) {
			return;
		}
		if (!sprites.contains(e)) {
			sprites.add(e);
//			System.out.println("Sprite with ID {" + Integer.toString(sprite.id) + "} added to draw queue!");
		} else {
			System.out.println("Unable to add sprite");
		}
	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	public void Render() {
		Collections.sort(sprites, new DrawComparator());

		for (Sprite e : sprites) {
			int xOffset = e.drawPosition.getX() * e.SIZE;
			int yOffset = e.drawPosition.getY() * e.SIZE;

			this.renderSprite(xOffset, yOffset, e, false);
		}
	}

	public void Tick() {
	}

	public void renderSprite(int xp, int yp, Sprite sprite, boolean fixed) {
//		if(fixed) {	
//			xp -= xOffset;
//			yp -= yOffset;
//		}
		
		for(int y = 0; y < sprite.getHeight(); y++) {
			int ya = y + yp;
			for(int x = 0; x < sprite.getWidth(); x++) {
				int xa = x + xp;
				if(xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
				int col = sprite.pixels[x + y * sprite.SIZE]; 
				int color = 0xffFF0000; //RED
				if(col != color) pixels[xa+ya * width] = col;
//				pixels[xa + ya * width] = sprite.pixels[x + y * sprite.getWidth()];
			}
		}
		
	}

	public void AddEntity(Entity e) {
		if (!currentLevel.has(e)) {
			currentLevel.add(e);
		}
	}

	public void RemoveEntity(Entity e) {
		currentLevel.remove(e);
	}
}
