package me.grid.engine.entity;

import me.grid.engine.graphics.Sprite;
import me.grid.engine.util.vector2i;

public class Player extends Entity {
	
	private boolean movingDown = true;
	
	public Player(Sprite sprite, int x, int y, int width, int height, vector2i drawPosition) {
		super(sprite, x, y, width, height, drawPosition);
		position = drawPosition;
		position.setX(0); // TESTING
		position.setY(0); // TESTING
	}
	
	/**
	 * TODO:
	 * 		1. Add movement system // either text based, buttons on screen, or keyboard
	 * 		2. Attacking
	 */
	
	public void Tick() {
		// TESTING START
		if (position.getX() < 10 && movingDown) {
			position.incrementX();
			position.incrementY();
			if (position.getX() >= 10) {
				movingDown = false;
			}
		} else if (!movingDown) {
			position.decrementX();
			position.decrementY();
			if (position.getX() <= 0) {
				movingDown = true;
			}
		}
		System.out.println("movingDown: " + Boolean.toString(movingDown));
		position.display();
		// TESTING END
		this.getSprite().drawPosition.set(position);
	}

}
