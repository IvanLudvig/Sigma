package by.coffeecat.er.maps;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

import box2dLight.RayHandler;
import by.coffeecat.er.Sigma;

public abstract class Mapa {
	
	public TiledMap map;
    public RayHandler rayHandler;
    public World world;
    public Vector2 magePos;
	public static Array<Body> door;
    Body door2;
    public int bounds = 1;
	
	public Mapa() {
	}
	
	public void create(Sigma sigma, Stage stage){
	}
	
	public void render(float delta){
	}
	
	public void resize (int width, int height){
	}
	
	public void dispose(){
	}
	

}
