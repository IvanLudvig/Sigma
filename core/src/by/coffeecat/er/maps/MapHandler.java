package by.coffeecat.er.maps;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;

import box2dLight.RayHandler;
import by.coffeecat.er.Constants;
import by.coffeecat.er.Sigma;

public class MapHandler {

	public static void setBounds(TiledMap map, OrthographicCamera camera){
		MapProperties prop = map.getProperties();

		int mapWidth = prop.get("width", Integer.class);
		int mapHeight = prop.get("height", Integer.class);
		int tilePixelWidth = prop.get("tilewidth", Integer.class);
		int tilePixelHeight = prop.get("tileheight", Integer.class);

		int mapPixelWidth = mapWidth * tilePixelWidth;
		int mapPixelHeight = mapHeight * tilePixelHeight;
 
		int mapLeft = 0;
 
		int mapRight = 0 + mapPixelWidth;
 
		int mapBottom = 0;
 
		int mapTop = 300 + mapPixelHeight;

		float cameraHalfWidth = 400;
		float cameraHalfHeight = 240;


		float cameraLeft = camera.position.x - cameraHalfWidth;
		float cameraRight = camera.position.x + cameraHalfWidth;
		float cameraBottom = camera.position.y - cameraHalfHeight;
		float cameraTop = camera.position.y + (cameraHalfHeight*2);

		if(mapPixelWidth < camera.viewportWidth)
		{
			camera.position.x = mapRight / 2;

		}
		else if(cameraLeft <= mapLeft)
		{

			camera.position.x = mapLeft + cameraHalfWidth;
		}
		else if(cameraRight >= mapRight)
		{

			camera.position.x = mapRight - cameraHalfWidth;
		}

		if(mapPixelHeight < camera.viewportHeight)
		{
			camera.position.y = mapTop / 2;
		}
		else if(cameraBottom <= mapBottom)
		{
			camera.position.y = mapBottom + cameraHalfHeight;
		}
		else if(cameraTop >= mapTop)
		{
			camera.position.y = mapTop - (cameraHalfHeight*2);
		}

	}
	
	Box2DDebugRenderer renderer;
	Matrix4 debugMatrix;
	float PTM = Constants.PTM;

	public static void renderMap(OrthogonalTiledMapRenderer tiledMapRenderer, SpriteBatch batch, OrthographicCamera camera, World world,
			RayHandler rayHandler){
        tiledMapRenderer.setView(camera);
        tiledMapRenderer.render();
		rayHandler.setCombinedMatrix(camera);
		rayHandler.updateAndRender();

	}
	public static Touchpad tp;
	
	public static void renderPad(Touchpad touchpad, OrthographicCamera camera, Stage stage){
		touchpad.setPosition(camera.position.x-400, camera.position.y-240);
	    touchpad.setBounds(camera.position.x-375, camera.position.y-225, 150, 150);
		stage.act();
		stage.draw();
		tp = touchpad;
	}
}

