package me.grid.engine.level;

import java.util.ArrayList;
import java.util.List;

import me.grid.engine.entity.Entity;
import me.grid.engine.graphics.Screen;
import me.grid.engine.level.tiles.Tile;
import me.grid.engine.util.vector2i;

public class Level {
	/*
	 * This is essentially an abstract class
	 */

	protected int width, height;
	protected int[] tilesInt;
	protected int[] tiles;
	public static Level spawn = new Level("/levels/spawn_level.png");
	
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	

	public void clearLevel() {
		for(int i = 0; i < entities.size(); i++) {
			entities.remove(i);
		}
	}
	
	public Level(int width, int height) { // generates random level
		this.width = width;
		this.height = height;
		tilesInt = new int[width * height];
		generateLevel();
	}

	public Level(String path) {
		loadLevel(path);
		generateLevel();
	}

	protected void generateLevel() {
		
	}
	
	public boolean has(Entity e) {
		return entities.contains(e);
	}

	public void remove(Entity e) {
		if (entities.contains(e)) {
			entities.remove(e);
		}
	}
	
	protected void removeAll() {
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).isRemoved())
				entities.remove(i);
		}
	}

	protected void loadLevel(String path) {

	}

	public void update() {
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).Tick();
		}

		removeAll();

	}

	@SuppressWarnings("unused")
	private void time() {

	}

	public void add(Entity e) {
		e.init(this);
		entities.add(e);
	}
	
	@SuppressWarnings("unused")
	private double getDistance(vector2i tile, vector2i goal) {
		double dx = tile.getX() - goal.getX();
		double dy = tile.getY() - goal.getY();
		return Math.sqrt(dx * dx + dy * dy);
	}

	public void render(int xScroll, int yScroll, Screen screen) {
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			screen.Draw(e.getSprite(), e.getPosition().getX(), e.getPosition().getY(), e.getSprite().getDrawOrder());//, x, y, drawOrder);entities.get(i).render(screen);
		}
	}

	public boolean tileCollision(int x, int y, int size, int xOffset, int yOffset) { //offset is based on the location of your sprite in your png
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			int xt = (x - c % 2 * size - xOffset) >> 4;
			int yt = (y - c / 2 * size - yOffset) >> 4;
			if (getTile(xt, yt).solid()) solid = true; //semi-buggy if shooting the bottom wall
		}

		return solid;
	}
	
	public List<Entity> getEntities(Entity e, int radius){
		List<Entity> result = new ArrayList<Entity>();
		int ex = (int) e.getPosition().getX();
		int ey = (int) e.getPosition().getY();
		for(int i = 0; i < entities.size(); i++) {
			Entity entity = entities.get(i);
			if(entity.equals(e)) continue;
			int x = (int) entity.getPosition().getX();
			int y = (int) entity.getPosition().getY();
			
			int dx = Math.abs(x - ex);
			int dy = Math.abs(y - ey);
			double distance = Math.sqrt((dx * dx) + (dy * dy));
			if(distance <= radius) result.add(entity);
		}
		return result;
	}
	
	public List<Entity> getAllEntities(){
		return entities;
	}
	
	/*public List<Player> getPlayers(Entity e, int radius){
		List<Player> result = new ArrayList<Player>();
		int ex = (int) e.getPosition().getX();
		int ey = (int) e.getPosition().getY();
		for(int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
			int x = (int) player.getX();
			int y = (int) player.getY();
			int dx = Math.abs(x - ex);
			int dy = Math.abs(y - ey);
			double distance = Math.sqrt((dx * dx) + (dy * dy));
			if(distance <= radius) result.add(player);
		}
		return result;
	}*/
	

	public Tile getTile(int x, int y) {
		return null;
	}

	public void removeEntity(int index) {
		entities.remove(index);
	}
	
	public void removeEntity(Entity e) {
		entities.remove(e);
	}
}
