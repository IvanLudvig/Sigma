package by.coffeecat.screens;

import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
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

	public static SpriteBatch batch;
	public static World world;
	public static OrthographicCamera camera;
	Viewport viewport; 
	public static Sigma game;
	float PTM = Constants.PTM;
	Stage stage;
	static Skin skin = Sigma.skin;
	
	TextButton newgame;
	TextButton load;
	
	Music music;
	
	public Main(Sigma sigma) {
		game = sigma;
		// Create assets manager
		AssetManager assetManager = new AssetManager();
		batch = game.batch;
		world = game.world;
		camera = game.camera;
		viewport = game.viewport;
		stage = new Stage(viewport);
		
		music = Gdx.audio.newMusic(Gdx.files.internal("music/menu.mp3"));
		music.play();
		music.setLooping(true);
		music.setVolume(0.4f);
		
		newgame = new TextButton("New Game", skin);
		newgame.setText("New Game");
		newgame.setPosition(300, 340);
		
		load = new TextButton("Load Game", skin);
		load.setText("Load Game");
		load.setPosition(300, 240);
		
		Art.load(assetManager);
		assetManager.finishLoading();
		Art.assignResource(assetManager);

		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	    newgame.addListener(new ChangeListener() {
	        @Override
	        public void changed (ChangeEvent event, Actor actor) {
	            game.setScreen(game.gama);
	            music.stop();
	        }
	    });
	    
	    load.addListener(new ChangeListener() {
	        @Override
	        public void changed (ChangeEvent event, Actor actor) {
	            game.setScreen(game.gama);
	            music.stop();
	        }
	    });
	    
		stage.addActor(newgame);
		stage.addActor(load);
		
		game.inputMultiplexer.addProcessor(stage);
	}
	
	@Override
	public void render(float delta) {
		if(Gdx.input.justTouched() ) {
			int i = (int)(Math.random()*3)+1;
			for(int b = 0;b<i;b++){
				bolts.add(new LightningBolt(new Vector2((float)Math.random()*800, (float)Math.random()*480), 
						new Vector2(Gdx.input.getX(), Gdx.input.getY())));
			}
		}
		
		
		// draw the Scene
		updateBolts();
		drawScene();
		removeCompleted();
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

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
        camera.update();
        
        // Start rendering
        batch.begin();
        
        batch.setProjectionMatrix(camera.combined);

		
		for(int i=0; i<bolts.size(); i++){
			bolts.get(i).draw(batch);
		}
		batch.end();
		return;
	}
	
	@Override
	public void dispose(){
		music.dispose();
	}
	

}
