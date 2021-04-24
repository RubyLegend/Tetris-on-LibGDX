package com.gdx.tetris;

import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
//import com.badlogic.gdx.math.Rectangle;
//import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
//import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class GameScreen implements Screen{
	//Variables
	final TetrisGDX game;
	GameScreen thisGame;
	Music music;
	//Texture background, mesh, projection, blue, yellow, orange, red, cyan, purple, green;
	Figure a, holding, next1, next2, next3, next4;
	private long lastClickTime = 0, dropTime = 0;
	OrthographicCamera camera;
	private boolean block_hold = false;
	private int score = 0;
	private int lines = 0;
	int dropTimeSpeed = 700000000;
	boolean firstboot = true;
	//Settings for the game
	static public int WIDTH = Settings.WIDTH; //Width of a block
	static public int ROWS = Settings.ROWS +2; //Number of rows
	static public int COLS = Settings.COLS; //Number of cols
	static public int XMIN = Settings.XMIN; //|Position
	static public int YMIN = Settings.YMIN;  //|of map
	//------------------------
	static public int YMAX = YMIN + (ROWS-1)*WIDTH;
	static public int XMAX = XMIN + (COLS-1)*WIDTH;
	private int SCOREPOSX = XMAX + 100, HOLDPOSX = XMIN - 200, nextblockposY;
	static public int [][] Mesh = new int[COLS][ROWS];
	//Window settings
	private static int Wwidth = 1920;
	private static int Wheight = 1080;
	//--------------------
	Stage main;
	Table table;
	TextureAtlas atlas;
	TextButtonStyle textButtonStyle;
	Button start;
	BitmapFont font;
	Skin skin;
	//--------------------
	
	
	
	public GameScreen(final TetrisGDX game) {
		this.game = game;
		main = new Stage();
		table = new Table();
		// create the camera and the SpriteBatch
		music = Gdx.audio.newMusic(Gdx.files.internal("audio.mp3"));
		music.setLooping(true);

		//Generating blocks
		holding = new Figure();
		a = GenerateBlock("none", 1);
		next1 = GenerateBlock(a.type, 0);
		next2 = GenerateBlock(next1.type, 0);
		next3 = GenerateBlock(next2.type, 0);
		next4 = GenerateBlock(next3.type, 0);
		//-----------------------------
		atlas = new TextureAtlas(Gdx.files.internal("textures.pack"));
		nextblockposY = getWheight() - 300;
		
		skin = new Skin();
		skin.add("default", new TextFieldStyle(new BitmapFont(), Color.WHITE, null, null, null));
		TextField hold = new TextField("Holding block:", skin);
		hold.setX(HOLDPOSX);
		hold.setY(getWheight()-150);
		TextField sc = new TextField("Score : " + score, skin);
		sc.setX(SCOREPOSX);
		sc.setY(getWheight()-150);
		TextField nb = new TextField("Next blocks:", skin);
		nb.setX(SCOREPOSX);
		nb.setY(getWheight()-200);
		main.addActor(hold);
		main.addActor(sc);
		main.addActor(nb);
		main.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(1f)));
	}	
	
	public void SaveGameScreen(GameScreen game) {
		thisGame = game;
	}
	
	public Figure GenerateBlock(String prevBlock, int draw) {
		double gen = Math.random()*7;
		if(gen <= 1 && prevBlock != "i") { //i
			return new Figure("i", Mesh, draw);
		}
		else if(gen <= 2 && prevBlock != "l") { //l
			return new Figure("l", Mesh, draw);
		}
		else if(gen <= 3 && prevBlock != "j") { //j
			return new Figure("j", Mesh, draw);
		}
		else if(gen <= 4 && prevBlock != "o") { //o
			return new Figure("o", Mesh, draw);
		}
		else if(gen <= 5 && prevBlock != "s") { //s
			return new Figure("s", Mesh, draw);
		}
		else if(gen <= 6 && prevBlock != "t") { //t
			return new Figure("t", Mesh, draw);
		}
		else if (gen < 7 && prevBlock != "z") { // gen < 140 //z
			return new Figure("z", Mesh, draw);
		}
		else return GenerateBlock(prevBlock, draw);
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
		//music.play();
	}

	private TextureRegion getTexture(int color) {
		switch(color) {
			case 1: return atlas.findRegion("BLUE");
			case 2: return atlas.findRegion("CYAN");
			case 3: return atlas.findRegion("GREEN");
			case 4: return atlas.findRegion("ORANGE");
			case 5: return atlas.findRegion("PURPLE");
			case 6: return atlas.findRegion("RED");
			case 7: return atlas.findRegion("YELLOW");
			case 10: return atlas.findRegion("projection");
		}
		return null;
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		main.act(Gdx.graphics.getDeltaTime());
		
		//-------------------------------------
		main.getBatch().begin();
		
		//if(alpha < 1) {
		//	alpha += 0.02f;
		//	game.batch.setColor(255,255,255,alpha);
		//	game.font.setColor(255,255,255,alpha);
		//}    
		main.getBatch().draw(atlas.findRegion("background"), 0, 0, getWwidth(), getWheight());
		for(int i = 0; i < Mesh.length; i++) {
			for(int j = 0; j < Mesh[i].length; j++) {
				switch (Mesh[i][j]) {
					case 1:
						main.getBatch().draw(atlas.findRegion("BLUE"), XMIN + i*WIDTH, YMIN + j*WIDTH, WIDTH, WIDTH);
						break;
					case 2:
						main.getBatch().draw(atlas.findRegion("CYAN"), XMIN + i*WIDTH, YMIN + j*WIDTH, WIDTH, WIDTH);
						break;
					case 3:
						main.getBatch().draw(atlas.findRegion("GREEN"), XMIN + i*WIDTH, YMIN + j*WIDTH, WIDTH, WIDTH);
						break;
					case 4:
						main.getBatch().draw(atlas.findRegion("ORANGE"), XMIN + i*WIDTH, YMIN + j*WIDTH, WIDTH, WIDTH);
						break;
					case 5:
						main.getBatch().draw(atlas.findRegion("PURPLE"), XMIN + i*WIDTH, YMIN + j*WIDTH, WIDTH, WIDTH);
						break;
					case 6:
						main.getBatch().draw(atlas.findRegion("RED"), XMIN + i*WIDTH, YMIN + j*WIDTH, WIDTH, WIDTH);
						break;
					case 7:
						main.getBatch().draw(atlas.findRegion("YELLOW"), XMIN + i*WIDTH, YMIN + j*WIDTH, WIDTH, WIDTH);
						break;
					case 10:
						main.getBatch().draw(atlas.findRegion("projection"), XMIN + i*WIDTH, YMIN + j*WIDTH, WIDTH, WIDTH);
						break;
					default:
						if(j < Mesh[i].length -2)
							main.getBatch().draw(atlas.findRegion("mesh"), XMIN + i*WIDTH, YMIN + j*WIDTH, WIDTH, WIDTH);
						break;
				}
			}
			
		}
		if(holding.type != null) {
			holding.setXY(HOLDPOSX, getWheight()-250);
			holding.Draw(main.getBatch(), WIDTH, getTexture(holding.color));
		}
		next1.setXY(SCOREPOSX, nextblockposY);
		next1.Draw(main.getBatch(), WIDTH, getTexture(next1.color));
		next2.setXY(SCOREPOSX, nextblockposY - 3*WIDTH);
		next2.Draw(main.getBatch(), WIDTH, getTexture(next2.color));
		next3.setXY(SCOREPOSX, nextblockposY - 6*WIDTH);
		next3.Draw(main.getBatch(), WIDTH, getTexture(next3.color));
		next4.setXY(SCOREPOSX, nextblockposY - 9*WIDTH);
		next4.Draw(main.getBatch(), WIDTH, getTexture(next4.color));
		
		main.getBatch().end();
		main.draw();
		
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
		if(Gdx.input.isKeyJustPressed(Settings.Hold) && !block_hold) {
			block_hold = true;
			String temp = holding.type;
			holding.setFigure(a.type, Mesh, 0);
			a.UnDraw(Mesh);
			if(temp != null) {
				a.setFigure(temp, Mesh, 1);
			}
			else {
			a.setFigure(next1.type, Mesh, 1);
			next1 = next2;
			next2 = next3;
			next3 = next4;
			next4 = GenerateBlock(next3.type, 0);
			}
		}
		if(Gdx.input.isKeyJustPressed(Settings.Pause)) {
			game.setScreen(new PauseScreen(game, thisGame));
			
		}
		if(TimeUtils.nanoTime() - dropTime > dropTimeSpeed) {
			if(a.bottom(Mesh)) { //Change figure, if I can`t move it to the bottom anymore or there is full lines
				block_hold = false;
				CheckLines();
				a.setFigure(next1.type, Mesh, 1);
				next1 = next2;
				next2 = next3;
				next3 = next4;
				next4 = GenerateBlock(next3.type, 0);
				dropTime = TimeUtils.nanoTime();
				if(dropTimeSpeed > 1000000)
					dropTimeSpeed -= 100000;
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
		// TODO Auto-generated method stub
	}

	public static int getWwidth() {
		return Wwidth;
	}

	public static int getWheight() {
		return Wheight;
	}

}
