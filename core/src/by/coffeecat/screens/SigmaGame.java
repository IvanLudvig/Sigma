package by.coffeecat.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;

import box2dLight.RayHandler;
import by.coffeecat.er.CollisionListener;
import by.coffeecat.er.Constants;
import by.coffeecat.er.Sigma;
import by.coffeecat.er.body.Mage;
import by.coffeecat.er.body.MageClass;
import by.coffeecat.er.maps.MapHandler;
import by.coffeecat.er.maps.Mapa;
import by.coffeecat.er.objects.Item;

public class SigmaGame extends ScreenAdapter{
	
	//важное
	public static SpriteBatch batch;
	public static World world;
	public static OrthographicCamera camera;
	Viewport viewport; 
	public static Sigma game;
	float PTM = Constants.PTM;
	public static Box2DDebugRenderer renderer;
	public Stage stage;
	public Mapa current;

	//другие переменные
	Matrix4 debugMatrix;
	public Touchpad touchpad;
	int side = 1;
    OrthogonalTiledMapRenderer tiledMapRenderer;
    RayHandler rayHandler;
    public static double mapnum = 1;
    
    public static MageClass mage = new MageClass();
    public Vector2 suba = new Vector2();
    public Body[] deletion = new Body[25];
   
	Texture take;
    TiledMap map;
    
    TextButton pause;
    Skin skin;

   
    public int paused = 0;
    
	public SigmaGame(Sigma sigma, Mapa mapa, Stage stagee) {
		game = sigma;
		batch = game.batch;
		camera = game.camera;
		viewport = game.viewport;
		skin = game.skin;
		viewport.apply(true);
		
		this.stage = stagee;
		touchpad = Mage.createPad(new Vector2(camera.position.x, camera.position.y));
		pause = new TextButton("||", skin);
		pause.setPosition(770, 450);
		
		renderer = new Box2DDebugRenderer();
		take = new Texture("ui/take.png");
		current = mapa;
		current.create(game, this.stage);
		world = game.world;
		
		
        tiledMapRenderer = new OrthogonalTiledMapRenderer(current.map);
		mage.create(world, game, batch);
		world.setContactListener(new CollisionListener());

		mage.setPos(current.magePos);
		
		current.createLights();
		pause.addListener(new ChangeListener() {
	        @Override
	        public void changed (ChangeEvent event, Actor actor) {
	            game.setScreen(new Pause(game));
	            game.inputMultiplexer.removeProcessor(stage);
	            paused = 1;
	        }
	    });
		
		this.stage.addActor(touchpad);
		this.stage.addActor(pause);
		
        game.inputMultiplexer.addProcessor(stage);
	}
	
	 
    Item te;
	@Override
	public void render (float delta) {
		if(paused == 0){
			Gdx.gl.glClearColor(0,0,0,0);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			Gdx.gl.glBlendFunc(GL20.GL_ONE, GL20.GL_ONE_MINUS_SRC_ALPHA);
			 update();
			MapHandler.renderMap(tiledMapRenderer, batch, camera, world, current.rayHandler);
			MapHandler.renderPad(touchpad, camera, stage);
			if(current.bounds==1){
				MapHandler.setBounds(current.map, camera);
			}
		    	
			pause.setPosition(camera.position.x+340, camera.position.y+190);
	    	mage.moveMage(touchpad, camera);
	    	mage.drawit(batch);
			//current.rayHandler.setCombinedMatrix(camera);
			//current.rayHandler.updateAndRender();
			game.putMage(mage);
			current.render(delta);
			renderer.render(world, batch.getProjectionMatrix().cpy().scale(PTM,PTM, 0));
			
		    batch.begin();
		    Array<Body> bodies = new Array<Body>(world.getBodyCount()+50);
		    world.getBodies(bodies);
		    
	    	mage.renderPlasma();
	    	
			stage.draw();
	    	stage.act();

		    for(Body body : bodies){
				if(body.getUserData()!=null){
					if((!body.getUserData().equals(mage.magico[1].one[0])&&(body.getUserData().getClass().getName().contains("by.coffeecat.er.objects")))){
						Item item = (Item)body.getUserData();
						batch.draw(item.texture, body.getWorldCenter().x*PTM-item.size.x, body.getWorldCenter().y*PTM-item.size.y, 
								item.size.x*1.5f, item.size.y*1.5f);
						
						
						suba = new Vector2(body.getPosition().x*PTM, body.getPosition().y*PTM);
						suba.sub(mage.getPos());
						suba.set(Math.abs(suba.x), Math.abs(suba.y));
						camera.unproject(new Vector3(suba.x, suba.y, 0));
						
						
						if((suba.x<50)&&(suba.y<50)){
							batch.draw(take, body.getWorldCenter().x*PTM-item.size.x, body.getWorldCenter().y*PTM-item.size.y, 
									70, 25);
							if(Gdx.input.justTouched()){
								Vector3 tp = new Vector3();
								tp.x = Gdx.input.getX();     
								tp.y = Gdx.input.getY();
								tp.z = 0;
								game.camera.unproject(tp);
								if((Math.abs(new Vector2(tp.x, tp.y).sub(body.getPosition().x*PTM, body.getPosition().y*PTM).x)<30)&&
										(Math.abs(new Vector2(tp.x, tp.y).sub(body.getPosition().x*PTM, body.getPosition().y*PTM).y)<30)){
									System.out.println("ЧАй на столе");
									item.removeFromWorld();
									game.ui.inventory.addItem(item);
								}
							}
						}

					}
				}
			}

		    batch.end();
			/*
			 if(camera.position.x-400>map.getProperties().get("width", Integer.class)*10){
				 camera.position.x=map.getProperties().get("width", Integer.class)*10;
			 }
			 */
		}

	}

	public int count = 0;
	public void delete(Body body){
		deletion[count]=body;
		count++;
	}
 
     public void update(){
		 batch.setProjectionMatrix(camera.combined);
		 //debugMatrix = batch.getProjectionMatrix().cpy().scale(PTM,PTM, 0);
		 camera.update();
		 
		    
		 world.step(1f/60f, 6, 2);
		 
		 for(int i=0;i<deletion.length;i++){
			 if(deletion[i]!=null){
				 if(!world.isLocked()){
					 world.destroyBody(deletion[i]);
					 deletion[i]=null;
				 }
			 }
			 if(i-deletion.length==1){
				 count = 0;
			 }
		 }
		 count = 0;
		    


		    
		 touchpad.setBounds(camera.position.x-375, camera.position.y-225, 150, 150);
			//renderer.render(world, debugMatrix);
	    	
     }
     
     public void changeMap(Mapa change){
    	 current = change;
    	 world = change.world;
         tiledMapRenderer = new OrthogonalTiledMapRenderer(current.map);
         mage.create(world, game, batch);
         camera.position.set(400, 800,0);
         mage.setPos(current.magePos);
 		game.inputMultiplexer.addProcessor(stage);
     }


	@Override
	public void resize (int width, int height){
		viewport.update(width, height);
	}
	
	@Override
	public void dispose(){
		rayHandler.dispose();
		world.dispose();
		batch.dispose();
		stage.dispose();
	}


}
