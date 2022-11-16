package me.grid.engine;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import me.grid.engine.graphics.Screen;
import me.grid.engine.level.Level;

public class Runner extends Canvas implements Runnable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final int SCALE = 1;
	public static final int WIDTH = 1000 * SCALE;
	public static final int HEIGHT = (WIDTH / 16 * 9) * SCALE;

	public static final String NAME = "Engine Window";

	private boolean running = false;

	private JFrame frame;
	public Screen screen;
	public Level currentLevel;

	private BufferedImage Image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) Image.getRaster().getDataBuffer()).getData();

	public Runner() {
		this.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		this.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		this.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

		frame = new JFrame(NAME);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());

		frame.add(this, BorderLayout.CENTER);
		frame.pack();

		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		frame.setVisible(true);

		currentLevel = null;
		
		init();
	}

	public void init() {
		screen = new Screen(WIDTH, HEIGHT, currentLevel);
	}

	public synchronized void start() {
		running = true;
		new Thread(this).start();
	}

	public synchronized void stop() {
		running = false;
	}

	public void tick() {
		screen.Tick();
	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		screen.clear(); // clears the screen

		// FOR NOW JUST SO THAT A TEST COULD BE ADDED
//		for (Entity e : entities) {
//			Sprite sprite = e.getSprite();
//			screen.Draw(sprite, sprite.drawPosition.getX(), sprite.drawPosition.getY(), sprite.getDrawOrder());
//		}
		screen.Render();

		for (int i = 0; i < pixels.length; i++) { // sets the pixel array of this class equal to what is being rendered
			// essentially
			pixels[i] = screen.pixels[i];
		}

		Graphics g = bs.getDrawGraphics();
		g.drawImage(Image, 0, 0, getWidth(), getHeight(), null);
		
//		g.setColor(Color.BLACK); // remove
//		g.fillRect(0, 0, getWidth(), getHeight());

		g.dispose();
		bs.show();

		
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D / 60D;

		int frames = 0;
		int ticks = 0;

		long lastTimer = System.currentTimeMillis();
		double delta = 0;

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;
			boolean shouldRender = true; // for now
			while (delta >= 1) {
				ticks++;
				tick();
				delta--;
				shouldRender = true;
			}
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (shouldRender) {
				frames++;
				render();
			}
			if (System.currentTimeMillis() - lastTimer > 1000) {
				frames = 0;
				ticks = 0;
				lastTimer += 1000;
			}
		}
	}

	public static void main(String[] args) {
		new Runner().start();
	}

}
