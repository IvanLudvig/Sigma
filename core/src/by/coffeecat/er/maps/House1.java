package by.coffeecat.er.maps;

import box2dLight.PointLight;
import box2dLight.RayHandler;
import by.coffeecat.er.CollisionListener;
import by.coffeecat.er.Constants;
import by.coffeecat.er.Sigma;
import by.coffeecat.er.MapBodyBuilder;
import by.coffeecat.er.body.Mage;
import by.coffeecat.er.body.MageClass;
import by.coffeecat.screens.SigmaGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class House1 extends Mapa{
	
    public static Array<Body> bodies;
    public static Array<Body> door;

    SigmaGame eg;
    
	public static SpriteBatch batch = new SpriteBatch();
	Sigma game;
	Box2DDebugRenderer renderer;
	Matrix4 debugMatrix;
	float PTM = Constants.PTM;
	
    public void create(Sigma sigma, Stage stage){
    	game = sigma;
    	bounds = 0;
		world = new World(new Vector2(0,0), true);
        renderer = new Box2DDebugRenderer();
        batch = new SpriteBatch();
        map = new TmxMapLoader().load("maps/maphouse1.tmx");
        
        bodies = MapBodyBuilder.buildShapes(map, 100, world, "Object Layer 1");

        door = MapBodyBuilder.buildShapes(map, 100, world, "Ladder");

        
        rayHandler = new RayHandler(world);
        rayHandler.setShadows(false);
        new PointLight(rayHandler, 32, Color.MAGENTA, 1000, 300,600);
        rayHandler.setAmbientLight(1);
        magePos = new Vector2(400, 50);
		game.putWorld(world);
		world.setContactListener(new CollisionListener());
    }

	@Override
    public void render(float delta){
		game.putWorld(world);
        game.gama.camera.position.set(200, 240,0);
        System.out.println(game.mage.getPos());
		//renderer.render(world, game.gama.camera.combined);
    }
	
	
	@Override
	public void dispose(){
		rayHandler.dispose();
		world.dispose();
		batch.dispose();
	}

}
