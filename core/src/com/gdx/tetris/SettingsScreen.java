package com.gdx.tetris;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class SettingsScreen implements Screen {
	final Assets assets;
	final TetrisGDX game;
	Settings settings;
	Stage main;
	Table table;
	Screen thisScreen;
	//Texture background;
	//TextureAtlas buttonAtlas;
	TextButtonStyle textButtonStyle;
	BitmapFont font;
	OrthographicCamera camera;
	Skin skin, button;
	Button RotLeft; 
    Button RotRight;
    Button MoveLeft;
    Button MoveRight;
    Button MoveDown;
    Button Drop;
    Button Hold;
    Button Pause;
    FrameRate rate;
	
	public SettingsScreen(final TetrisGDX game, final Assets assets, final Settings settings) {
		this.game = game;
		this.assets = assets;
		this.settings = settings;
		thisScreen = this;
		main = new Stage();
		rate = new FrameRate();
		Gdx.input.setInputProcessor(main);
		table = new Table();
		table.setFillParent(true);
		main.addActor(table);
		rate = new FrameRate();
		//table.setDebug(true);
		
		skin = new Skin();
		skin.add("default", new LabelStyle(new BitmapFont(), Color.WHITE));
	    	    
	    font = new BitmapFont();
	    button = new Skin();
	    button.addRegions(assets.manager.get(Assets.Buttons));
	    textButtonStyle = new TextButtonStyle();
	    textButtonStyle.font = font;
	    textButtonStyle.up = button.getDrawable("normal");
	    textButtonStyle.down = button.getDrawable("pressed");
	    textButtonStyle.over = button.getDrawable("hover");
	    
	    
	    RotLeft = new TextButton("Change \"Rotate Left\" button", textButtonStyle);
	    RotRight = new TextButton("Change \"Rotate Right\" button", textButtonStyle);
	    MoveLeft = new TextButton("Change \"Move Left\" button", textButtonStyle);
	    MoveRight = new TextButton("Change \"Move Right\" button", textButtonStyle);
	    MoveDown = new TextButton("Change \"Move Down\" button", textButtonStyle);
	    Drop = new TextButton("Change \"Drop\" button", textButtonStyle);
	    Hold = new TextButton("Change \"Hold\" button", textButtonStyle);
	    Pause = new TextButton("Change \"Pause\" button", textButtonStyle);
	    //Adding all ClickListener`s
	    RotLeft.addListener(new ClickListener() {
	        @Override
	        public void clicked(InputEvent event, float x, float y) {
	        	if(Gdx.input.isKeyPressed(Keys.ANY_KEY))
	    	    for(int i = 0; i <= 255; i++) {
	    	    	if(Gdx.input.isKeyPressed(i)) {
	    	    		settings.RotLeft = i;
	    	    		break;
	    	    	}
	    	    	if(i == 255) i = 0;
	    	    }
	    	    settings.SaveSettings();
	    	    update_table();
	        	super.clicked(event, x, y);
	        }
	    });
	    RotRight.addListener(new ClickListener() {
	        @Override
	        public void clicked(InputEvent event, float x, float y) {
	        	if(Gdx.input.isKeyPressed(Keys.ANY_KEY))
	        	for(int i = 0; i <= 255; i++) {
	    	    	if(Gdx.input.isKeyPressed(i)) {
	    	    		settings.RotRight = i;
	    	    		break;
	    	    	}
	    	    	if(i == 255) i = 0;
	    	    }
	    	    settings.SaveSettings();
	    	    update_table();
	        	super.clicked(event, x, y);
	        }
	    });
	    MoveLeft.addListener(new ClickListener() {
	        @Override
	        public void clicked(InputEvent event, float x, float y) {
	        	if(Gdx.input.isKeyPressed(Keys.ANY_KEY))
	        	for(int i = 0; i <= 255; i++) {
	    	    	if(Gdx.input.isKeyPressed(i)) {
	    	    		settings.MoveLeft = i;
	    	    		break;
	    	    	}
	    	    	if(i == 255) i = 0;
	    	    }
	    	    settings.SaveSettings();
	    	    update_table();
	        	super.clicked(event, x, y);
	        }
	    });
	    MoveRight.addListener(new ClickListener() {
	        @Override
	        public void clicked(InputEvent event, float x, float y) {
	        	if(Gdx.input.isKeyPressed(Keys.ANY_KEY))
	        	for(int i = 0; i <= 255; i++) {
	    	    	if(Gdx.input.isKeyPressed(i)) {
	    	    		settings.MoveRight = i;
	    	    		break;
	    	    	}
	    	    	if(i == 255) i = 0;
	    	    }
	    	    settings.SaveSettings();
	    	    update_table();
	        	super.clicked(event, x, y);
	        }
	    });
	    MoveDown.addListener(new ClickListener() {
	        @Override
	        public void clicked(InputEvent event, float x, float y) {
	        	if(Gdx.input.isKeyPressed(Keys.ANY_KEY))
	        	for(int i = 0; i <= 255; i++) {
	    	    	if(Gdx.input.isKeyPressed(i)) {
	    	    		settings.MoveDown = i;
	    	    		break;
	    	    	}
	    	    	if(i == 255) i = 0;
	    	    }
	    	    settings.SaveSettings();
	    	    update_table();
	        	super.clicked(event, x, y);
	        }
	    });
	    Drop.addListener(new ClickListener() {
	        @Override
	        public void clicked(InputEvent event, float x, float y) {
	        	if(Gdx.input.isKeyPressed(Keys.ANY_KEY))
	        	for(int i = 0; i <= 255; i++) {
	    	    	if(Gdx.input.isKeyPressed(i)) {
	    	    		settings.Drop = i;
	    	    		break;
	    	    	}
	    	    	if(i == 255) i = 0;
	    	    }
	    	    settings.SaveSettings();
	    	    update_table();
	        	super.clicked(event, x, y);
	        }
	    });
	    Hold.addListener(new ClickListener() {
	        @Override
	        public void clicked(InputEvent event, float x, float y) {
	        	if(Gdx.input.isKeyPressed(Keys.ANY_KEY))
	        	for(int i = 0; i <= 255; i++) {
	    	    	if(Gdx.input.isKeyPressed(i)) {
	    	    		settings.Hold = i;
	    	    		break;
	    	    	}
	    	    	if(i == 255) i = 0;
	    	    }
	    	    settings.SaveSettings();
	    	    update_table();
	        	super.clicked(event, x, y);
	        }
	    });
	    Pause.addListener(new ClickListener() {
	        @Override
	        public void clicked(InputEvent event, float x, float y) {
	        	if(Gdx.input.isKeyPressed(Keys.ANY_KEY))
	        	for(int i = 0; i <= 255; i++) {
	    	    	if(Gdx.input.isKeyPressed(i)) {
	    	    		settings.Pause = i;
	    	    		break;
	    	    	}
	    	    	if(i == 255) i = 0;
	    	    }
	    	    settings.SaveSettings();
	    	    update_table();
	        	super.clicked(event, x, y);
	        }
	    });
	    //----------------------------
	    Button back = new TextButton("Return to main menu", textButtonStyle);
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
	    //Adding all rebind buttons in the table
	    update_table();
	}
	
	public void update_table() {
		Label blankField = new Label("", skin);
		table.clear();
		table.add(new Label("Hold key you want to bind before pressing on a button", skin)).row();
		table.add(new Label("Current key for \"Rotate Left\" is: " + Keys.toString(settings.RotLeft), skin));
	    table.add(blankField).width(20);
	    table.add(RotLeft).width(300).height(80).row();
	    table.add(new Label("Current key for \"Rotate Right\" is: " + Keys.toString(settings.RotRight), skin));
	    table.add(blankField).width(20);
	    table.add(RotRight).width(300).height(80).row();
	    table.add(new Label("Current key for \"Move Left\" is: " + Keys.toString(settings.MoveLeft), skin));
	    table.add(blankField).width(20);
	    table.add(MoveLeft).width(300).height(80).row();
	    table.add(new Label("Current key for \"Move Right\" is: " + Keys.toString(settings.MoveRight), skin));
	    table.add(blankField).width(20);
	    table.add(MoveRight).width(300).height(80).row();
	    table.add(new Label("Current key for \"Move Down\" is: " + Keys.toString(settings.MoveDown), skin));
	    table.add(blankField).width(20);
	    table.add(MoveDown).width(300).height(80).row();
	    table.add(new Label("Current key for \"Drop\" is: " + Keys.toString(settings.Drop), skin));
	    table.add(blankField).width(20);
	    table.add(Drop).width(300).height(80).row();
	    table.add(new Label("Current key for \"Hold\" is: " + Keys.toString(settings.Hold), skin));
	    table.add(blankField).width(20);
	    table.add(Hold).width(300).height(80).row();
	    table.add(new Label("Current key for \"Pause\" is: " + Keys.toString(settings.Pause), skin));
	    table.add(blankField).width(20);
	    table.add(Pause).width(300).height(80).row();
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
