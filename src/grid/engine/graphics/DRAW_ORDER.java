package me.grid.engine.graphics;

public enum DRAW_ORDER {
	NONE(-1), BACKGROUND(0), CHARACTER(1), FOREGROUND(2);

	private final int value;

	private DRAW_ORDER(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
