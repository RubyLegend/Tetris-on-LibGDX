package com.gdx.tetris;

import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class ScoreBoardScreen implements Screen{
	final TetrisGDX game;
	final Assets assets;
	Screen thisScreen;
	Stage main;
	Table table;
	//Texture background, logo;
	//TextureAtlas atlas, buttonAtlas;
	TextButtonStyle textButtonStyle;
	Button back;
	BitmapFont font;
	OrthographicCamera camera;
	Skin skin, button;
	FrameRate rate;
	
	public ScoreBoardScreen(final TetrisGDX game, final MainMenuScreen prevScreen, final Assets assets) {
		this.game = game;
		this.assets = assets;
		SaveScreen(this);
		main = new Stage();
		Gdx.input.setInputProcessor(main);
		table = new Table();
		table.setFillParent(true);
		main.addActor(table);
		rate = new FrameRate();
		//table.setDebug(true);
		//atlas = new TextureAtlas(Gdx.files.internal("textures.pack"));
		
		skin = new Skin();
		skin.add("default", new LabelStyle(new BitmapFont(), Color.WHITE));
		skin.add("default", new TextFieldStyle(new BitmapFont(), Color.WHITE, null, null, null));
		
		//background = new Texture(Gdx.files.internal("bg1.jpg"));
		//logo = new Texture(Gdx.files.internal("tetris.png"));
		//Label blankField = new Label("", skin);
	    
	    font = new BitmapFont();
	    //buttonAtlas = new TextureAtlas(Gdx.files.internal("button.pack"));
	    button = new Skin();
	    button.addRegions(assets.manager.get(Assets.Buttons));
	    textButtonStyle = new TextButtonStyle();
	    textButtonStyle.font = font;
	    textButtonStyle.up = button.getDrawable("normal");
	    textButtonStyle.down = button.getDrawable("pressed");
	    textButtonStyle.over = button.getDrawable("hover");
	    
	    back = new TextButton("Return to main menu", textButtonStyle);
	    back.setX(5);
	    back.setY(Gdx.graphics.getHeight()-50-5);
	    back.setWidth(300);
	    back.setHeight(50);
	    main.addActor(back);

	    back.addListener(new ClickListener() {
	        @Override
	        public void clicked(InputEvent event, float x, float y) {
	        	game.setScreen(new Animations(thisScreen, new MainMenuScreen(game, assets), game, 0.02f)); //Check for removing creating new main menu (with new(...))
	        	super.clicked(event, x, y);
	        }
	    });
	    
	    try {
	        File scores = new File("scores.txt");
	        if (scores.createNewFile()) {
	            System.out.println("File created: " + scores.getName());
	          } else {
	            System.out.println("File already exists.");
	            Scanner myReader = new Scanner(scores);
	            int n = 0;
	            while (myReader.hasNextLine() && n != 50) {
	              String data = myReader.nextLine();
	              table.add(new TextField(data, skin)).width(data.length()*7).row();
	              System.out.println(data);
	              n++;
	            }
	            myReader.close();
	          }
	    } catch (IOException e) {
	          System.out.println("An error occurred.");
	          e.printStackTrace();
	    }
	    
	}
	
	public void SaveScreen(Screen screen) {
		thisScreen = screen;
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		main.getBatch().begin();
		main.getBatch().draw(assets.manager.get(Assets.Background).findRegion("bg1"), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		main.getBatch().end();
		main.act(Gdx.graphics.getDeltaTime());
		main.draw();
		rate.update();
		rate.render();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
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

}
