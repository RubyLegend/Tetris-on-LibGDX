package com.gdx.tetris;

import java.util.Stack;

import com.badlogic.gdx.Gdx;
//import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureArray;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
//import com.badlogic.gdx.math.Rectangle;
//import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
//import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class GameScreen implements Screen{
	//Variables
	final TetrisGDX game;
	Music music;
	//Settings for the game
	static public int WIDTH = Settings.WIDTH; //Width of a block
	static public int ROWS = Settings.ROWS +2; //Number of rows
	static public int COLS = Settings.COLS; //Number of cols
	static public int XMIN = Settings.XMIN; //|Position
	static public int YMIN = Settings.YMIN; //|of map
	//------------------------
	static public int YMAX = YMIN + (ROWS-1)*WIDTH;
	static public int XMAX = XMIN + (COLS-1)*WIDTH;
	private int SCOREPOSX = XMAX + 100, HOLDPOSX = XMIN - 200, nextblockposY;
	public int [][] Mesh = new int[COLS][ROWS], newMesh;
	//Window settings
	private static int Wwidth = 1920;
	private static int Wheight = 1080;
	//--------------------
	Figure a, holding, next1, next2, next3, next4;
	private Stack<Integer> rmLines;
	private int rk = -1, rl = COLS-1;
	private long lastClickTime = 0, dropTime = 0, picture_change = 0;
	private float bgAlpha = 1;
	private boolean block_hold = false;
	private boolean GameOver = false;
	private int score = 0;
	private int lines = 0;
	private int level = 0, level_copy = 0;
	private int bg_n;
	int dropTimeSpeed = 700000000;
	//--------------------
	Stage main;
	TextureAtlas atlas;
	TextButtonStyle textButtonStyle;
	Button start;
	BitmapFont font;
	Skin skin;
	FrameRate rate;
	//Texture [] background;
	//--------------------
	TextField hold, sc, nb, ln, lvl;
	Assets assets;
	
	
	public GameScreen(final TetrisGDX game, final Assets assets) {
		this.game = game;
		this.assets = assets;
		main = new Stage();
		
		music = Gdx.audio.newMusic(Gdx.files.internal("audio.mp3"));
		music.setLooping(true);
		rate = new FrameRate();
		
		//background = new Texture [7];
		//background[0] = new Texture(Gdx.files.internal("bg1.jpg"));
		//background[1] = new Texture(Gdx.files.internal("bg2.jpg"));
		//background[2] = new Texture(Gdx.files.internal("bg3.jpg"));
		//background[3] = new Texture(Gdx.files.internal("bg4.jpg"));
		//background[4] = new Texture(Gdx.files.internal("bg5.jpg"));
		//background[5] = new Texture(Gdx.files.internal("bg6.jpg"));
		//background[6] = new Texture(Gdx.files.internal("bg7.jpg"));
		bg_n = 0;
		
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
		hold = new TextField("Holding block:", skin);
		hold.setX(HOLDPOSX);
		hold.setY(getWheight()-180);
		sc = new TextField("Score : " + score, skin);
		sc.setX(SCOREPOSX);
		sc.setY(getWheight()-180);
		ln = new TextField("Lines : " + lines, skin);
		ln.setX(SCOREPOSX);
		ln.setY(getWheight()-200);
		lvl = new TextField("Level : " + level, skin);
		lvl.setX(SCOREPOSX);
		lvl.setY(getWheight()-220);
		nb = new TextField("Next blocks:", skin);
		nb.setX(SCOREPOSX);
		nb.setY(getWheight()-240);
		
		main.addActor(hold);
		main.addActor(sc);
		main.addActor(nb);
		main.addActor(ln);
		main.addActor(lvl);
		
		rmLines = new Stack<Integer> ();
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
	
	private boolean CheckLines() { //Check for full lines and delete it
		newMesh = new int [COLS][ROWS];
		int k = 0;
		int tline = 0;
		boolean deleted = false;
		boolean isLine;
		for(int i = 0; i < ROWS; i++) {
			isLine = true;
			for(int j = 0; j < COLS; j++) {
				if(Mesh[j][i] == 0 || Mesh[j][i] == 10)
					isLine = false;
			}
			if(!isLine) {
				for(int j = 0; j < COLS; j++) {
					newMesh[j][k] = Mesh[j][i];
				}
				k++;
			}
			else {  //Remove line and shift all blocks down (simply skips it from copying to new mesh)
				tline++;
				rmLines.push(i); //Push it to the stack of removing blocks
				
			}
		}
		switch(tline) {
		case 0:{
			Mesh = newMesh;
			break;
		}
		case 1: {
			score += 100;
			break;
		}
		case 2: {
			score += 300;
			break;
		}
		case 3: {
			score += 700;
			break;
		}
		case 4 :{
			score += 1500;
			break;
		}
		}
		lines+=tline;
		if(lines >= 10) {
			lines -= 10;
			level++;
		}
		level_copy = level;
		if(level >= 30) {
			level_copy = 29;
		}
		sc.setText("Score : " + score);
		ln.setText("Lines : " + lines);
		lvl.setText("Level : " + level);
		deleted = true;
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
			case 11: return atlas.findRegion("BLUE_dark");
			case 2: return atlas.findRegion("CYAN");
			case 12: return atlas.findRegion("CYAN_dark");
			case 3: return atlas.findRegion("GREEN");
			case 13: return atlas.findRegion("GREEN_dark");
			case 4: return atlas.findRegion("ORANGE");
			case 14: return atlas.findRegion("ORANGE_dark");
			case 5: return atlas.findRegion("PURPLE");
			case 15: return atlas.findRegion("PURPLE_dark");
			case 6: return atlas.findRegion("RED");
			case 16: return atlas.findRegion("RED_dark");
			case 7: return atlas.findRegion("YELLOW");
			case 17: return atlas.findRegion("YELLOW_dark");
			
			case 10: return atlas.findRegion("projection");
		}
		return null;
	}
	
	public void drawMesh() {
		//Test draw
		//main.getBatch().draw(atlas.findRegion("BLUE"), 0, 0, WIDTH, WIDTH);
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
	}
	
	public void drawBackGround() {
		if(picture_change != 1000) {
			main.getBatch().setColor(255, 255, 255, bgAlpha);
			main.getBatch().draw(assets.manager.get(Assets.Background).findRegion("bg"+(bg_n+1)), 0, 0, getWwidth(), getWheight());
			main.getBatch().setColor(255, 255, 255, 1);
			picture_change+=1;
			if(picture_change == 1000) {
				bgAlpha = 0;
				bg_n++;
				if(bg_n > 6) bg_n-=7;
			}
		}
		else {
			bgAlpha += 1/120f;
			if(bgAlpha >= 1f) {
				picture_change = 0;
				bgAlpha = 1f;
			}
			if(bg_n-1 == -1)
				main.getBatch().draw(assets.manager.get(Assets.Background).findRegion("bg7"), 0, 0, getWwidth(), getWheight());
			else
				main.getBatch().draw(assets.manager.get(Assets.Background).findRegion("bg" + (bg_n)), 0, 0, getWwidth(), getWheight());
			main.getBatch().setColor(255, 255, 255, bgAlpha);
			main.getBatch().draw(assets.manager.get(Assets.Background).findRegion("bg" + (bg_n+1)), 0, 0, getWwidth(), getWheight());
			main.getBatch().setColor(255, 255, 255, 1);
			
		}
	}
	
	public void inputHandle() {
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
			holding.setFigure(a.type, Mesh, 0, true);
			a.UnDraw(Mesh);
			if(temp != null) {
				a.setFigure(temp, Mesh, 1, false);
			}
			else {
			a.setFigure(next1.type, Mesh, 1, false);
			next1 = next2;
			next2 = next3;
			next3 = next4;
			next4 = GenerateBlock(next3.type, 0);
			}
		}
		
		if(Gdx.input.isKeyJustPressed(Settings.Pause)) {
			game.setScreen(new Animations(this, new PauseScreen(game, this, assets), game, 0.1f));
			
		}
		
		if(TimeUtils.nanoTime() - dropTime > dropTimeSpeed - level_copy*(dropTimeSpeed/30)) { //For speed of figures I using something similar to NES speed values
			if(a.bottom(Mesh)) { //Change figure, if I can`t move it to the bottom anymore or there is full lines
				if(a.a.y == YMAX-WIDTH || a.b.y == YMAX-WIDTH || a.c.y == YMAX-WIDTH || a.d.y == YMAX-WIDTH
					|| a.a.y == YMAX || a.b.y == YMAX || a.c.y == YMAX || a.d.y == YMAX) {
					game.setScreen(new Animations(this, new GameOverScreen(game, score, assets), game, 0.01f));
					GameOver = true;
				}
				else {
					if(block_hold) { 
						block_hold = false;
						holding.color-=10;
					}
					CheckLines();
					a.setFigure(next1.type, newMesh, 1, false);
					next1 = next2;
					next2 = next3;
					next3 = next4;
					next4 = GenerateBlock(next3.type, 0);
					dropTime = TimeUtils.nanoTime();
				}
			}
			else {
				a.moveDown(Mesh);
				dropTime = TimeUtils.nanoTime();
			}
		}
	}
	
	@Override
	public void render(float delta) {
		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		main.act(Gdx.graphics.getDeltaTime());
		
		//-------------------------------------
		main.getBatch().begin();
		
		if(rmLines.size() != 0 || rl != COLS - 1) { //While some lines are cleared
			if(rk == -1 || rl == COLS - 1) {
				rl = 0;
				if(rmLines.size() != 0) rk = rmLines.pop();
			}
			else rl++;
			
			Mesh[rl][rk] = 0;
			
			if(rmLines.size() == 0 && rl == COLS - 1) {
				Mesh = newMesh;
			}
			
		}
		
		//Fade background picture
		drawBackGround();
		
		drawMesh();
		
		
		//Draw hold and next blocks
		if(holding.type != null) {
			holding.setXY(HOLDPOSX, getWheight()-280);
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
		rate.update();
		rate.render();
		//--------------------------------------------
		
		//Input handling
		
		if(!GameOver && rmLines.size() == 0 && rl == COLS - 1) { //Not a Game Over or removing lines script
			inputHandle();
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
