package me.grid.engine.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

	/*
	 * This gives the user access to keyboard controls
	 */

	private int key;

	public boolean[] keys = new boolean[65536]; // 65536 is max for what is needed
	public boolean up, down, left, right, r, e, tab; // Could add more specific keybinds

	public void Tick() {

		up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
		r = keys[KeyEvent.VK_R];
		e = keys[69];
		tab = keys[KeyEvent.VK_TAB];
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() != 69) keys[e.getKeyCode()] = true;
		key = e.getKeyCode();
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() != 69) {
			keys[e.getKeyCode()] = false;
		}

	}

	public void keyTyped(KeyEvent e) {
		if (key == 69) { //e == 69
			keys[69] = true;
		}
	}
}
