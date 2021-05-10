package com.gdx.tetris;

import java.time.format.DateTimeFormatter;  
import java.time.LocalDateTime;    
import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors
import java.io.FileWriter;
import java.util.Scanner; // Import the Scanner class to read text files

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
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
		thisScreen = this;
		main = new Stage();
		Gdx.input.setInputProcessor(main);
		table = new Table();
		table.setFillParent(true);
		main.addActor(table);
		rate = new FrameRate();
		//table.setDebug(true);
		
		skin = new Skin();
		skin.add("default", new LabelStyle(new BitmapFont(), Color.WHITE));
		skin.add("default", new TextFieldStyle(new BitmapFont(), Color.WHITE, null, null, null));
		
		Label blankField = new Label("", skin);
	    
	    font = new BitmapFont();

	    button = new Skin();
	    button.addRegions(assets.manager.get(Assets.Buttons));
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
	        	game.setScreen(new Animations(thisScreen, new GameScreen(game, assets, new Settings()), game, 0.02f));
				dispose();
	    		super.clicked(event, x, y);
	        }
	    });
	    table.add(restart).width(300).height(100).row();
	    
	    menu = new TextButton("Main menu", textButtonStyle);
	    menu.addListener(new ClickListener() {
	        @Override
	        public void clicked(InputEvent event, float x, float y) {

	    		game.setScreen(new Animations(thisScreen, new MainMenuScreen(game, assets), game, 0.05f));
	    		super.clicked(event, x, y);
	        }
	    });
	    table.add(blankField).height(20).row();
	    table.add(menu).width(300).height(100);
	    //Writing score
	    try {
	        File scores = new File("scores.txt");
	        //Read data from prev score file                                                                                          
	        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");   
	        LocalDateTime now = LocalDateTime.now(); 
		    int n = 0;
		    int [] sc = new int [50];
	        if(scores.exists()) {
			    Scanner myReader = new Scanner(scores);   
			    boolean inserted = false;
		        while (myReader.hasNextLine() && n != 50) {
		          String data = myReader.nextLine();
		          //Starting from 23rd pos, read number to an array
		          int thisScore = 0;
		          for(int i = 29; i < data.length(); i++) {
		        	  thisScore*=10;
		        	  thisScore+=Character.getNumericValue(data.charAt(i));
		          }
		          //------------------
		          if(thisScore <= score && !inserted) {
		        	  sc[n] = score;
		        	  sc[n+1] = thisScore;
		        	  n++;
		        	  inserted = true;
		          }
		          else sc[n] = thisScore;
		          System.out.println(data);
		          n++;
		        }
		        if(n == 0) {
		        	sc[n] = score;
		        	n++;
		        }
		        myReader.close();
	        }
	        else System.out.println("Scores file doesn`t exists yet.");
            //Re-create it
	        System.out.println("Deleting scores.txt and re-creating it...\n");
	        if(scores.exists()) scores.delete();
	        if (scores.createNewFile()) {
	            System.out.println("Scores: File created: " + scores.getName());
	          } else {
	            System.out.println("Scores: File already exists.");
	          }
	        FileWriter myWriter = new FileWriter(scores.getAbsolutePath()); 
	        for(int i = 0; i < n; i++) {
	        	myWriter.write(dtf.format(now) + "   Score: " + sc[i] + "\n");
	        }
	        myWriter.close();
            System.out.println("Done.\n");
	         
	    } catch (IOException e) {
	          System.out.println("Scores: An error occurred.");
	          e.printStackTrace();
	    }
	    
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
