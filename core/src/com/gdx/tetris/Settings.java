package com.gdx.tetris;

import com.badlogic.gdx.Input.Keys;

public class Settings {
	//Settings for the game
	static int RotLeft = Keys.Z;
	static int RotRight = Keys.X;
	static int MoveLeft = Keys.A;
	static int MoveRight = Keys.D;
	static int MoveDown = Keys.S;
	static int Drop = Keys.SPACE;
	static int Hold = Keys.SHIFT_LEFT;
	static int Pause = Keys.ESCAPE;
	static public int WIDTH = 40; //Width of a block
	static public int ROWS = 23; //Number of rows
	static public int COLS = 10; //Number of cols
	static public int XMIN = 640; //|Position
	static public int YMIN = 75;  //|of map
}
