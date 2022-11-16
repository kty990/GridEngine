package me.grid.engine.entity;

import me.grid.engine.graphics.Sprite;
import me.grid.engine.level.Level;
import me.grid.engine.util.vector2i;

public abstract class Entity {

	private Sprite sprite;
	
	protected vector2i position;
	protected boolean removed = false;
	
	public Entity(Sprite sprite, int x, int y, int width, int height, vector2i drawPosition) {
		this.sprite = sprite;
		sprite.drawPosition.setX(drawPosition.getX());
		sprite.drawPosition.setY(drawPosition.getY());
		position = drawPosition;
	}
	
	public void moveTo(int x, int y) {
		// Remember: TILE BASED GAME, GRID MOVEMENT STRUCTURE
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
	public void Tick() {
		
	}
	
	public vector2i getPosition() {
		return position;
	}
	
	public void init(Level level) {
		
	}
	
	public boolean isRemoved() {
		return this.removed;
	}

}
