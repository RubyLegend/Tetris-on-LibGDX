package com.gdx.tetris;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Assets {
	public AssetManager manager = new AssetManager();
    public static AssetDescriptor<TextureAtlas> Textures;
	public static AssetDescriptor<TextureAtlas> Buttons;
	public static AssetDescriptor<Texture> logo;
	public static AssetDescriptor<TextureAtlas> Background;
    
	public Assets() {
		Textures = new AssetDescriptor<TextureAtlas>("textures.pack", TextureAtlas.class);
    	Buttons = new AssetDescriptor<TextureAtlas>("button.pack", TextureAtlas.class);
    	logo = new AssetDescriptor<Texture>("tetris.png", Texture.class);
    	Background = new AssetDescriptor<TextureAtlas>("backgrounds.pack", TextureAtlas.class);
	}
	
	public void load()
    {
        
    	manager.load(Textures);
    	manager.load(Buttons);
    	manager.load(logo);
    	manager.load(Background);
    }

    public void dispose()
    {
    	manager.dispose();
    }
}
