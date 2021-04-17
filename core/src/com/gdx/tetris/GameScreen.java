package com.gdx.tetris;

import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
//import com.badlogic.gdx.math.Rectangle;
//import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
//import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class GameScreen implements Screen{
	//Variables
	final TetrisGDX game;
	//Texture block;
	Music music;
	Texture background, mesh, projection, blue, yellow, orange, red, cyan, purple, green;
	//Rectangle a, b, c, d;
	Figure a;
	public int color;
	private long lastClickTime = 0, dropTime = 0;
	OrthographicCamera camera;
	//private BitmapFont font;
	private int score = 0;
	private int lines = 0;
	private String currBlock, hold;
	private String [] nextBlocks = new String[4];
	//Settings for the game
	static public int WIDTH = Settings.WIDTH; //Width of a block
	static public int ROWS = Settings.ROWS; //Number of rows
	static public int COLS = Settings.COLS; //Number of cols
	static public int XMIN = Settings.XMIN; //|Position
	static public int YMIN = Settings.YMIN;  //|of map
	//------------------------
	static public int YMAX = YMIN + (ROWS-1)*WIDTH;
	static public int XMAX = XMIN + (COLS-1)*WIDTH;
	private int SCOREPOS = XMAX + 100;
	static public int [][] Mesh = new int[COLS][ROWS];
	//Window settings
	private int Wwidth = 1920;
	private int Wheight = 1080;
	
	
	public GameScreen(final TetrisGDX game) {
		this.game = game;
		// create the camera and the SpriteBatch
		music = Gdx.audio.newMusic(Gdx.files.internal("audio.mp3"));
		music.setLooping(true);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Wwidth, Wheight);
		currBlock = GenerateBlock("none");
		for(int i = 0; i < 4; i++) {
			if(i==0)
			nextBlocks[i] = GenerateBlock(currBlock);
			else nextBlocks[i] = GenerateBlock(nextBlocks[i-1]);
		}
		background = new Texture("background.jpg");
		mesh = new Texture("mesh.jpeg");
		blue = new Texture("BLUE.jpeg");
		cyan = new Texture("CYAN.jpeg");
		red = new Texture("RED.jpeg");
		yellow = new Texture("YELLOW.jpeg");
		green = new Texture("GREEN.jpeg");
		purple = new Texture("PURPLE.jpeg");
		orange = new Texture("ORANGE.jpeg");
		projection = new Texture("projection.jpeg");
		a = new Figure(currBlock, Mesh);
	}	
	
	public String GenerateBlock(String prevBlock) {
		double gen = Math.random()*7;
		if(gen <= 1 && prevBlock != "i") { //i
			return "i";
		}
		else if(gen <= 2 && prevBlock != "l") { //l
			return "l";
		}
		else if(gen <= 3 && prevBlock != "j") { //j
			return "j";
		}
		else if(gen <= 4 && prevBlock != "o") { //o
			return "o";
		}
		else if(gen <= 5 && prevBlock != "s") { //s
			return "s";
		}
		else if(gen <= 6 && prevBlock != "t") { //t
			return "t";
		}
		else if (gen < 7 && prevBlock != "z") { // gen < 140 //z
			return "z";
		}
		else return GenerateBlock(prevBlock);
	}
	
	private boolean CheckLines() {
		int [][] newMesh = new int [COLS][ROWS];
		int k = 0;
		boolean deleted = false;
		for(int i = 0; i < ROWS; i++) {
			boolean isLine = true;
			for(int j = 0; j < COLS; j++) {
				if(Mesh[j][i] == 0 || Mesh[j][i] == 10)
					isLine = false;
			}
			if(!isLine) { //Remove line and shift all blocks down
				for(int j = 0; j < COLS; j++) {
					newMesh[j][k] = Mesh[j][i];
				}
				k++;
			}
			else {
				score += 100;
				deleted = true;
			}
		}
 		Mesh = newMesh;
		return deleted;
	}
	@Override
	public void show() {
		// start the playback of the background music
		// when the screen is shown
		music.play();
	}

	@Override
	public void render(float delta) {
		// tell the camera to update its matrices
		camera.update();

		// tell the SpriteBatch to render in the
		// coordinate system specified by the camera.
		game.batch.setProjectionMatrix(camera.combined);

		
		
		//-------------------------------------
		game.batch.begin();
		
		game.batch.draw(background, 0, 0, Wwidth, Wheight);
		for(int i = 0; i < COLS; i++) {
			for(int j = 0; j < ROWS; j++) {
				switch (Mesh[i][j]) {
					case 1:
						game.batch.draw(blue, XMIN + i*WIDTH, YMIN + j*WIDTH, WIDTH, WIDTH);
						break;
					case 2:
						game.batch.draw(cyan, XMIN + i*WIDTH, YMIN + j*WIDTH, WIDTH, WIDTH);
						break;
					case 3:
						game.batch.draw(green, XMIN + i*WIDTH, YMIN + j*WIDTH, WIDTH, WIDTH);
						break;
					case 4:
						game.batch.draw(orange, XMIN + i*WIDTH, YMIN + j*WIDTH, WIDTH, WIDTH);
						break;
					case 5:
						game.batch.draw(purple, XMIN + i*WIDTH, YMIN + j*WIDTH, WIDTH, WIDTH);
						break;
					case 6:
						game.batch.draw(red, XMIN + i*WIDTH, YMIN + j*WIDTH, WIDTH, WIDTH);
						break;
					case 7:
						game.batch.draw(yellow, XMIN + i*WIDTH, YMIN + j*WIDTH, WIDTH, WIDTH);
						break;
					case 10:
						game.batch.draw(projection, XMIN + i*WIDTH, YMIN + j*WIDTH, WIDTH, WIDTH);
						break;
					default:
						game.batch.draw(mesh, XMIN + i*WIDTH, YMIN + j*WIDTH, WIDTH, WIDTH);
						break;
				}
			}
		}
		game.font.draw(game.batch, "Score: " + score + "      type: " + a.type, SCOREPOS, Wheight-150);
		game.font.draw(game.batch, "Next blocks: " + nextBlocks[0] + " " + nextBlocks[1]
				       + " " + nextBlocks[2] + " " + nextBlocks[3] + "        a.x = " + a.a.getX() + " a.y = " + a.a.getY(), SCOREPOS, Wheight - 250);
		game.batch.end();
		
		//--------------------------------------------
		
		if(Gdx.input.isKeyJustPressed(Settings.MoveLeft)) {
			a.moveLeft(Mesh);
		}
		if(Gdx.input.isKeyPressed(Settings.MoveDown) && TimeUtils.nanoTime() - lastClickTime > 60000000) {
			a.moveDown(Mesh);
			lastClickTime = TimeUtils.nanoTime();
		}
		if(Gdx.input.isKeyJustPressed(Settings.MoveRight)) {
			a.moveRight(Mesh);
		}
		if(Gdx.input.isKeyJustPressed(Settings.Drop) && !a.bottom(Mesh)) {
			a.Drop(Mesh);
			dropTime = TimeUtils.nanoTime()-700000000;
		}
		if(Gdx.input.isKeyJustPressed(Settings.RotLeft)) {
			a.test_rotation(true, Mesh);
		}
		if(Gdx.input.isKeyJustPressed(Settings.RotRight)) {
			a.test_rotation(false, Mesh);
		}
		if(TimeUtils.nanoTime() - dropTime > 700000000) {
			if(a.bottom(Mesh)) { //Change figure, if I can`t move it to the bottom anymore or there is full lines
				CheckLines();
				a.setFigure(nextBlocks[0], Mesh);
				currBlock = nextBlocks[0];
				for(int i = 0; i < 3; i++)
					nextBlocks[i] = nextBlocks[i+1];
				nextBlocks[3] = GenerateBlock(nextBlocks[2]);
				dropTime = TimeUtils.nanoTime();
			}
			else {
				a.moveDown(Mesh);
				dropTime = TimeUtils.nanoTime();
			}
		}
	}
	
	@Override
	public void resize(int width, int height) {
		
		Wwidth = width;
		Wheight = height;
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		background.dispose();
		//block.dispose();
	}

}
