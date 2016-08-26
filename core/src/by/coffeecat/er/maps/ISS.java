package by.coffeecat.er.maps;

import box2dLight.ConeLight;
import box2dLight.Light;
import box2dLight.RayHandler;
import by.coffeecat.er.CollisionListener;
import by.coffeecat.er.Constants;
import by.coffeecat.er.MapBodyBuilder;
import by.coffeecat.er.Sigma;
import by.coffeecat.screens.SigmaGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;

public class ISS extends Mapa{
    public static Array<Body> bodies;
    public static Array<Body> door;

    SigmaGame eg;
    
	public static SpriteBatch batch = new SpriteBatch();
	Sigma game;
	Matrix4 debugMatrix;
	float PTM = Constants.PTM;
	
	Texture Itxt;
	Image I;
	Music music;
	
	Light[] light = new ConeLight[30];
	
	    
	public void create(Sigma sigma, Stage stage){
	    	game = sigma;
	    	bounds = 1;
			world = new World(new Vector2(0,0), true);
	        batch = new SpriteBatch();
	        map = new TmxMapLoader().load("maps/untitled.tmx");
	        Itxt = new Texture("maps/txt/I.png");
	        I = new Image(Itxt);
	        magePos = new Vector2(500, 600);
	        System.out.println("I've created it. I'm a good class");
	        
	        bodies = MapBodyBuilder.buildShapes(map, 100, world, "Object Layer 1");
	        door = MapBodyBuilder.buildShapes(map, 100, world, "door");
			game.putWorld(world);
			

			world.setContactListener(new CollisionListener());
			//game.gama.camera.position.set(400, 1800, 0);// Всё равно ничего не работает
			
    		music = Gdx.audio.newMusic(Gdx.files.internal("music/I.mp3"));
    		
			I.setSize(800, 480);
			
			I.addAction(Actions.fadeOut(5f));
			stage.addActor(I);
	    }

	    float timer;
	    
		@Override
	    public void render(float delta){
			timer+=Gdx.graphics.getDeltaTime();
			game.putWorld(world);
			I.setPosition(game.gama.camera.position.x-400, game.gama.camera.position.y-240);

			//render
			rayHandler.setCombinedMatrix(game.camera.combined.scale(PTM, PTM, 0)); //не уверен в этой строчке
			rayHandler.updateAndRender();
			
    		music.play();
    		music.setLooping(true);
    		music.setVolume(0.6f);
	    }
		
		public void createLights(){ //вызывается в create
			rayHandler = new RayHandler(world);
			rayHandler.setWorld(world);
			
			for(i = 0;i<light.)
			light = new ConeLight(rayHandler, 32, new Color(1,1,1,0.5f), 12, 200/PTM,600/PTM, -90, 85f);
			light = new ConeLight(rayHandler, 32, new Color(1,1,1,0.5f), 12, 200/PTM,800/PTM, -90, 85f);
			cl = new ConeLight(rayHandler, 32, new Color(1,1,1,0.5f), 12, 200/PTM,400/PTM, -90, 85f);
			
			light.setSoftnessLength(0);
			cl.setSoftnessLength(0);
			
	        rayHandler.setAmbientLight(0.3f);
			rayHandler.setShadows(true);
		}
		
		
		@Override
		public void dispose(){
			rayHandler.dispose();
			world.dispose();
			batch.dispose();
		}

}
