package by.coffeecat.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import by.coffeecat.er.Constants;
import by.coffeecat.er.Profile;
import by.coffeecat.er.Sigma;

public class LoadGame extends ScreenAdapter{

	Sigma game;
	
	public SpriteBatch batch;
	public OrthographicCamera camera;
	Viewport viewport; 
	float PTM = Constants.PTM;
	Stage stage;
	Skin skin = Sigma.skin;
	
	TextButton load;
	TextButton back;
	Label proflab;
	SelectBox<String> prof;
	
	Profile profile;
	
	Texture bg;
	
	public LoadGame(Sigma gamee) {
		this.game = gamee;
		batch = game.batch;
		camera = game.camera;
		camera.setToOrtho(false);
		viewport = game.viewport;
		this.stage = game.loadGameStage;
		stage.clear();
		viewport.apply(true);
		
		bg = new Texture(Gdx.files.internal("menubg.png"));
		
		
		proflab = new Label("Game:",skin);
		proflab.setPosition(300, 290);
		
		prof = new SelectBox(skin);
		prof.setSize(75, prof.getHeight());
		prof.setItems(new String[]{"DD","F"});
		prof.setPosition(360, 280);
		
		load = new TextButton("Load!", skin);
		load.setPosition(370, 100);
		
		back = new TextButton("Back", skin);
		back.setPosition(300, 100);
		
		this.stage.addActor(load);
		this.stage.addActor(back);
		this.stage.addActor(prof);
		this.stage.addActor(proflab);
		
		
	    load.addListener(new ChangeListener() {
	        @Override
	        public void changed (ChangeEvent event, Actor actor) {
	        	//if((prof.getSelected()!=null)&&(!name.getText().equals(""))){
		        	profile = game.prohand.getProfiles().get(prof.getSelectedIndex());
		        	profile.load();
		        	game.profile = profile;
		    		game.gama = new SigmaGame(game, game.maps[0], game.uistage);
		        	game.setScreen(game.gama);
		        	System.out.println("Clicktion");
		        	//game.profile = profile;
		            //game.setScreen(game.gama);
		            game.inputMultiplexer.removeProcessor(stage);
	        }
	    });
	    
	    back.addListener(new ChangeListener() {
	        @Override
	        public void changed (ChangeEvent event, Actor actor) {
	            game.setScreen(new Main(game, game.menuStage));
	            game.inputMultiplexer.removeProcessor(stage);
	            stage.clear();
	        }
	    });
	    
	    
        game.inputMultiplexer.addProcessor(stage);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0,0,0,0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
        batch.begin();
        batch.setProjectionMatrix(camera.combined);
		batch.draw(bg, 0, 0, 750, 480);
		batch.end();
		
		stage.draw();
		stage.act(delta);
		

	}
	
	@Override
	public void dispose(){
		stage.dispose();
	}
	
}

