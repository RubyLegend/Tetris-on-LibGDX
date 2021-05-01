package com.gdx.tetris;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
//import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


public class MainMenuScreen extends ApplicationAdapter implements Screen{
	final TetrisGDX game;
	Screen thisScreen;
	Stage main;
	Table table;
	TextureAtlas atlas, buttonAtlas;
	TextButtonStyle textButtonStyle;
	Button start;
	BitmapFont font;
	OrthographicCamera camera;
	Skin skin, button;
	float alpha = 0;
	FrameRate rate;
	
	public MainMenuScreen(final TetrisGDX game) {
		this.game = game;
		SaveScreen(this);
		main = new Stage();
		Gdx.input.setInputProcessor(main);
		table = new Table();
		table.setFillParent(true);
		main.addActor(table);
		rate = new FrameRate();
		//table.setDebug(true);
		atlas = new TextureAtlas(Gdx.files.internal("textures.pack"));
		
		skin = new Skin();
		skin.add("default", new LabelStyle(new BitmapFont(), Color.WHITE));
		skin.add("default", new TextFieldStyle(new BitmapFont(), Color.WHITE, null, null, null));
		
		Label blankField = new Label("", skin);
	    
	    font = new BitmapFont();
	    buttonAtlas = new TextureAtlas(Gdx.files.internal("button.pack"));
	    button = new Skin();
	    button.addRegions(buttonAtlas);
	    textButtonStyle = new TextButtonStyle();
	    textButtonStyle.font = font;
	    textButtonStyle.up = button.getDrawable("normal");
	    textButtonStyle.down = button.getDrawable("pressed");
	    textButtonStyle.over = button.getDrawable("hover");
	    start = new TextButton("Start the game!", textButtonStyle);
	    
	    table.add(blankField).height(600).row();
	    table.add(blankField).width(60);
	    table.add(start).width(300).height(100);

	    main.addAction(Actions.sequence(Actions.alpha(0), Actions.fadeIn(1.5f)));
	    start.addListener(new ClickListener() {
	        @Override
	        public void clicked(InputEvent event, float x, float y) {
	    	    game.setScreen(new Animations(thisScreen, new GameScreen(game), game, 0.02f));
	        	super.clicked(event, x, y);
	        }
	    });

	}
	
	public void SaveScreen(Screen screen) {
		thisScreen = screen;
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		main.act(Gdx.graphics.getDeltaTime());
		main.getBatch().begin();
		main.getBatch().draw(atlas.findRegion("tetris"), 0, 0, 1920, 1080);
		main.getBatch().end();
		main.draw();
		rate.update();
		rate.render();
	}

	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		main.getViewport().update(width, height, true);
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
		main.dispose();
	}

}
