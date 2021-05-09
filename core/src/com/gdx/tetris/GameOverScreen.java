package com.gdx.tetris;

import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    
import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.Scanner; // Import the Scanner class to read text files

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
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
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class GameOverScreen implements Screen {
	final TetrisGDX game;
	Assets assets;
	//------------------
	Screen thisScreen;
	Stage main;
	Table table;
	TextureAtlas buttonAtlas;
	TextButtonStyle textButtonStyle;
	Button restart, menu;
	BitmapFont font;
	Skin skin, button;
	FrameRate rate;
	//Texture background;
	//--------------------
	
	
	public GameOverScreen(final TetrisGDX game, int score, final Assets assets) {
		this.game = game;
		this.assets = assets;
		SaveScreen(this);
		main = new Stage();
		Gdx.input.setInputProcessor(main);
		table = new Table();
		table.setFillParent(true);
		main.addActor(table);
		rate = new FrameRate();
		table.setDebug(true);
		
		skin = new Skin();
		skin.add("default", new LabelStyle(new BitmapFont(), Color.WHITE));
		skin.add("default", new TextFieldStyle(new BitmapFont(), Color.WHITE, null, null, null));
		
		Label blankField = new Label("", skin);
	    
	    font = new BitmapFont();
	    buttonAtlas = new TextureAtlas(Gdx.files.internal("button.pack"));
	    button = new Skin();
	    button.addRegions(assets.manager.get(Assets.Buttons));
	    //button.addRegions(buttonAtlas);
	    textButtonStyle = new TextButtonStyle();
	    textButtonStyle.font = font;
	    textButtonStyle.up = button.getDrawable("normal");
	    textButtonStyle.down = button.getDrawable("pressed");
	    textButtonStyle.over = button.getDrawable("hover");
	    
	    skin.add("default", new TextFieldStyle(new BitmapFont(), Color.WHITE, null, null, null));
		TextArea gameOver = new TextArea("           Game Over", skin);
		TextArea Score = new TextArea("     Your score was: " + score, skin);
	    table.add(gameOver).height(50).row();
	    table.add(Score).height(50).row();
	    
	    restart = new TextButton("Restart game", textButtonStyle);
	    restart.addListener(new ClickListener() {
	        @Override
	        public void clicked(InputEvent event, float x, float y) {
	        	game.setScreen(new Animations(thisScreen, new GameScreen(game, assets), game, 0.02f));
				dispose();
	    		super.clicked(event, x, y);
	        }
	    });
	    table.add(restart).width(300).height(100).row();
	    
	    menu = new TextButton("Main menu", textButtonStyle);
	    menu.addListener(new ClickListener() {
	        @Override
	        public void clicked(InputEvent event, float x, float y) {
	        	MainMenuScreen ms = new MainMenuScreen(game, assets);
	    		ms.SaveScreen(ms);
	    		game.setScreen(new Animations(thisScreen, ms, game, 0.05f));
	    		super.clicked(event, x, y);
	        }
	    });
	    table.add(blankField).height(20).row();
	    table.add(menu).width(300).height(100);
	    
	    //background = new Texture(Gdx.files.internal("bg1.jpg"));
	    
	    try {
	        File scores = new File("scores.txt"), 
	        	 scores2 = new File("temp_scores.txt");
	        //Create and open new temporary score file
	        if (scores2.createNewFile()) {
	            System.out.println("File created: " + scores.getName());
	          } else {
	            System.out.println("File already exists.");
	          }
	        
	        //Read data from prev score file
	        Scanner myReader = new Scanner(scores);
	        FileWriter myWriter = new FileWriter(scores2.getAbsolutePath());
	        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
	        LocalDateTime now = LocalDateTime.now(); 
	        int n = 0;
	        boolean inserted = false;
	        int [] sc = new int [50];
            while (myReader.hasNextLine() && n != 50) {
              String data = myReader.nextLine();
              //Starting from 23rd pos, read number to an array
              int thisScore = 0;
              for(int i = 29; i < data.length(); i++) {
            	  thisScore*=10;
            	  thisScore+=Character.getNumericValue(data.charAt(i));
              }
              sc[n] = thisScore;
              //------------------
              if(thisScore <= score && !inserted) {
            	  myWriter.write(dtf.format(now) + "   Score: " + score + "\n");
            	  inserted = true;
              }
              myWriter.write(data + '\n');
              System.out.println(data);
              n++;
            }
            myReader.close();
	        
            //Re-create it
	        scores.delete();
	        scores2.renameTo(scores);
	        
	        myWriter.close();
	        
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
		main.getBatch().begin();
		main.getBatch().draw(assets.manager.get(Assets.Background).findRegion("bg1"), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		main.getBatch().end();
		main.draw();
		main.act(Gdx.graphics.getDeltaTime());
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
