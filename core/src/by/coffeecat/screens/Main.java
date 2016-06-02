package by.coffeecat.screens;

import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import by.coffeecat.er.Constants;
import by.coffeecat.er.Sigma;
import by.coffeecat.light.Art;
import by.coffeecat.light.LightningBolt;

public class Main extends ScreenAdapter {
	
	Vector<LightningBolt> bolts = new Vector<LightningBolt>();

	public SpriteBatch batch;
	public World world;
	public OrthographicCamera camera;
	Viewport viewport; 
	public Sigma game;
	float PTM = Constants.PTM;
	Stage stage;
	Skin skin = Sigma.skin;
	
	TextButton newgame;
	TextButton load;
	
	public Texture bg;
	static Music music;
	
	public Main(Sigma sigma, Stage staga) {
		this.game = sigma;
		batch = game.batch;
		camera = game.camera;
		camera.setToOrtho(false);
		viewport = game.viewport;
		stage = game.menuStage;
		
		bg = new Texture(Gdx.files.internal("menubg.png"));
		music = Gdx.audio.newMusic(Gdx.files.internal("music/menu.mp3"));
		music.play();
		music.setLooping(true);
		music.setVolume(0.4f);
		
		newgame = new TextButton("New Game", skin);
		newgame.setText("New Game");
		newgame.setPosition(330, 340);
		
		load = new TextButton("Load Game", skin);
		load.setText("Load Game");
		load.setPosition(330, 240);
		
		/*
		Art.load(assetManager);
		assetManager.finishLoading();
		Art.assignResource(assetManager);
		*/

	    newgame.addListener(new ChangeListener() {
	        @Override
	        public void changed (ChangeEvent event, Actor actor) {
	            game.setScreen(new NewGame(game, music));
	            System.out.println("from main with evilness");
	            game.inputMultiplexer.removeProcessor(stage);
	        }
	    });
	    
	    load.addListener(new ChangeListener() {
	        @Override
	        public void changed (ChangeEvent event, Actor actor) {
	            game.setScreen(game.gama);
	            System.out.println("from main with evilness");
	            music.stop();
	            game.inputMultiplexer.removeProcessor(stage);
	        }
	    });
	    
		stage.addActor(newgame);
		stage.addActor(load);
		
        game.inputMultiplexer.addProcessor(stage);
		
	}
	
	@Override
	public void render(float delta) {
		/*
		if(Gdx.input.justTouched() ) {
			int i = (int)(Math.random()*3)+1;
			for(int b = 0;b<i;b++){
				bolts.add(new LightningBolt(new Vector2((float)Math.random()*800, (float)Math.random()*480), 
						new Vector2(Gdx.input.getX(), Gdx.input.getY())));
			}
		}
		*/
		Gdx.gl.glClearColor(0,0,0,0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.setProjectionMatrix(camera.combined);
		batch.draw(bg, 0, 0, 750, 480);
		batch.end();
		//updateBolts();
		//drawScene();
		//removeCompleted();
		stage.draw();
		stage.act(delta);
	}

	private void updateBolts(){
		for(int i=0; i<bolts.size(); i++){
			bolts.get(i).update();
		}
	}
	private void removeCompleted() {
		int count = bolts.size();
		for(int i=0; i<count; i++){
			if (bolts.get(i).isComplete()){
				bolts.remove(i);
				count--;
			}
		}
	}

	void drawScene(){
		Gdx.gl.glClearColor(0,0,0,0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
        batch.begin();
        batch.setProjectionMatrix(camera.combined);
		batch.draw(bg, 0, 0, 750, 480);
		
		for(int i=0; i<bolts.size(); i++){
			bolts.get(i).draw(batch);
		}
		
		batch.end();
	}
	
	@Override
	public void dispose(){
		music.dispose();
		stage.dispose();
	}
	

}
