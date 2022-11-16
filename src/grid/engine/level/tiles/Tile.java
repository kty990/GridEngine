package me.grid.engine.level.tiles;

public class Tile {

	protected boolean isSolid;
	
	public Tile() {
		isSolid = false;
	}
	
	public boolean solid() {
		return isSolid;
	}
	
}
