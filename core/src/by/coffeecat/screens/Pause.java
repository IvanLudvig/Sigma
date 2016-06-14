package by.coffeecat.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import by.coffeecat.er.Sigma;

public class Pause extends ScreenAdapter {

	Sigma game;
	
	Stage stage;
	
	TextButton exit;
	TextButton back;
	TextButton savegame;
	Table table;
	
	Skin skin;
	
	Texture bg;
	SpriteBatch batch;
	

	
	public Pause(Sigma gamee) {
		this.game = gamee;
		skin = game.skin;
		stage = game.pauseStage;
		batch = game.batch;
		
		bg = new Texture(Gdx.files.internal("menubg.png"));
		
		table = new Table();
		table.setPosition(300, 360);
		
		exit = new TextButton("Exit", skin);
		back = new TextButton("Back", skin);
		savegame = new TextButton("Save current game", skin);
		
		table.add(savegame);
		table.row();
		table.add(back);
		table.row();
		table.add(exit);
		table.center();
		
		stage.addActor(table);
		
	    back.addListener(new ChangeListener() {
	        @Override
	        public void changed (ChangeEvent event, Actor actor) {
	            game.setScreen(game.gama);
	            game.gama.paused = 0;
	            game.inputMultiplexer.removeProcessor(stage);
	            game.inputMultiplexer.addProcessor(game.gama.stage);
	        }
	    });
	    
	    savegame.addListener(new ChangeListener() {
	        @Override
	        public void changed (ChangeEvent event, Actor actor) {
	            game.profile.save();
	        }
	    });
	    
		exit.addListener(new ChangeListener() {
	        @Override
	        public void changed (ChangeEvent event, Actor actor) {
	            game.setScreen(new Main(game, game.menuStage));
	            game.inputMultiplexer.removeProcessor(stage);
	        }
	    });
	    
        game.inputMultiplexer.addProcessor(stage);
	}
	
	public void render(float delta){
		Gdx.gl.glClearColor(0,0,0,0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
        batch.begin();
        batch.setProjectionMatrix(game.camera.combined);
		batch.draw(bg, 0, 0, 750, 480);
		batch.end();
		
		stage.act(delta);
		stage.draw();
	}

}
