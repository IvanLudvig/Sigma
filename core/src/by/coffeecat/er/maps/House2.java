package by.coffeecat.er.maps;

import com.badlogic.gdx.graphics.Color;
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
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;

import box2dLight.PointLight;
import box2dLight.RayHandler;
import by.coffeecat.er.CollisionListener;
import by.coffeecat.er.Constants;
import by.coffeecat.er.Sigma;
import by.coffeecat.er.MapBodyBuilder;
import by.coffeecat.er.body.Ryorik;
import by.coffeecat.er.dialog.Conversation;
import by.coffeecat.screens.SigmaGame;

public class House2 extends Mapa {
	OrthogonalTiledMapRenderer tiledMapRenderer;
	public static Array<Body> bodies;
    public static Array<Body> door;

	SigmaGame eg;

	public static SpriteBatch batch = new SpriteBatch();
	Sigma game;
	Box2DDebugRenderer renderer;
	float PTM = Constants.PTM;

	Conversation conversation;

	Ryorik ryorik;
	// SequenceAction scenarij;
	Stage stage;

	public void create(Sigma sigma, Stage stage) {
		game = sigma;
		this.stage = stage;
		bounds = 0;
		world = new World(new Vector2(0,0), true);
		renderer = new Box2DDebugRenderer();
		batch = new SpriteBatch();
		map = new TmxMapLoader().load("maps/maphouse2.tmx");
		bodies = MapBodyBuilder.buildShapes(map, 100, world, "Object Layer 1");
		door = MapBodyBuilder.buildShapes(map, 100, world, "Door");

		// scenarij = new SequenceAction();
		// scenarij.addAction(sequence(moveTo(400, 300, 1f), moveTo(500, 100,
		// 1f)));
		ryorik = new Ryorik(world, stage);
		ryorik.MoveAct(new Vector2(50, 300));
		ryorik.MoveAct(new Vector2(400, 300));
		ryorik.MoveAct(new Vector2(400, 200));
		ryorik.MoveAct(new Vector2(400, 300));


		tiledMapRenderer = new OrthogonalTiledMapRenderer(map);
		rayHandler = new RayHandler(world);
		rayHandler.setShadows(false);
		new PointLight(rayHandler, 32, Color.MAGENTA, 100, 300, 600);
		rayHandler.setAmbientLight(1);
        magePos = new Vector2(400, 50);
		ryorik.setPosition(new Vector2(50, 150));

		world.setContactListener(new CollisionListener());
	}

	int constart = 0;
	
	public void render(float delta) {
		game.putWorld(world);
        game.gama.camera.position.set(200, 240,0);
		batch.setProjectionMatrix(game.gama.camera.combined);
			

		 if((ryorik.curact>2)&&(constart==0)){
				conversation = new Conversation("dialogues/1.xml", "Ryorik", null, 0, game.inputMultiplexer);
				constart=1;
		 }

		if(constart==1){
			conversation.render();
		}
        game.gama.camera.position.set(200, 240,0);
		game.putWorld(world);

		ryorik.drawit(batch, stage);
		story();
	}

	public void story() {

	}
	
	
	public void dispose(){
		rayHandler.dispose();
		world.dispose();
		batch.dispose();
		stage.dispose();
	}

}
