package by.coffeecat.er.maps;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;

import box2dLight.PointLight;
import box2dLight.RayHandler;
import by.coffeecat.er.CollisionListener;
import by.coffeecat.er.Constants;
import by.coffeecat.er.MapBodyBuilder;
import by.coffeecat.er.Sigma;
import by.coffeecat.screens.SigmaGame;

public class Where extends Mapa{

    public static Array<Body> bodies;
    public static Array<Body> door;

    SigmaGame eg;
    
	public static SpriteBatch batch = new SpriteBatch();
	Sigma game;
	Matrix4 debugMatrix;
	float PTM = Constants.PTM;
	
	Texture Itxt;
	Image I;
	
	    public void create(Sigma sigma, Stage stage){
	    	game = sigma;
	    	bounds = 1;
			world = new World(new Vector2(0,0), true);
	        batch = new SpriteBatch();
	        map = new TmxMapLoader().load("maps/first.tmx");
	        Itxt = new Texture("maps/txt/I.png");
	        I = new Image(Itxt);
	        magePos = new Vector2(400, 100);
	        System.out.println("I've created it. I'm a good class");
	        
	        bodies = MapBodyBuilder.buildShapes(map, 100, world, "Object Layer 1");
	        door = MapBodyBuilder.buildShapes(map, 100, world, "Ladder");
			game.putWorld(world);
			
	        rayHandler = new RayHandler(world);
	        rayHandler.setShadows(false);
	        new PointLight(rayHandler, 16, Color.WHITE, 50, 200, 520);
	        new PointLight(rayHandler, 30, Color.WHITE, 50, 200, 540);
	        rayHandler.setAmbientLight(1);

			world.setContactListener(new CollisionListener());
			//game.gama.camera.position.set(400, 1800, 0);// Всё равно ничего не работает
			
			I.setSize(800, 480);
			
			I.addAction(Actions.fadeOut(5f));
			stage.addActor(I);
	    }

		@Override
	    public void render(float delta){
			game.putWorld(world);
			I.setPosition(game.gama.camera.position.x-400, game.gama.camera.position.y-240);
	    }
		
		
		@Override
		public void dispose(){
			rayHandler.dispose();
			world.dispose();
			batch.dispose();
		}

}
