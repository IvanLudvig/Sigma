package by.coffeecat.er.maps;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

import box2dLight.PointLight;
import box2dLight.RayHandler;
import by.coffeecat.er.Sigma;
import by.coffeecat.er.MapBodyBuilder;

/**
 * Created by Solange Tereshko on 04/01/2016.
 */
public class Map1 extends Mapa{
    public static Array<Body> bodies;
    public static Array<Body> door;
    Sigma game;

    public void create(Sigma sigma, Stage stage){
    	game = sigma;
    	bounds = 1;
    	world = game.world;
        map = new TmxMapLoader().load("maps/map1.tmx");
        bodies = MapBodyBuilder.buildShapes(map, 100, world, "Object Layer 1");
        door = MapBodyBuilder.buildShapes(map, 100, world, "Door");
        magePos = new Vector2(100, 500);
        
        rayHandler = new RayHandler(world);
        rayHandler.setShadows(false);
        new PointLight(rayHandler, 5, Color.FOREST, 100, 1260,760);
        new PointLight(rayHandler, 10, Color.MAGENTA, 30, 1190,640);
        new PointLight(rayHandler, 10, Color.MAGENTA, 30, 1305,640);
        rayHandler.setAmbientLight(1);
    }
    

    public void render(SpriteBatch batch){
		game.putWorld(world);
    }

}
