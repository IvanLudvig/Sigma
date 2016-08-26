package by.coffeecat.er;

import by.coffeecat.er.body.MageClass;
import by.coffeecat.er.maps.House1;
import by.coffeecat.er.maps.House2;
import by.coffeecat.er.maps.ISS;
import by.coffeecat.er.maps.Mapa;
import by.coffeecat.er.objects.Normalizer;
import by.coffeecat.er.ui.Ui;
import by.coffeecat.screens.SigmaGame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Sigma extends Game {
	
	public static SpriteBatch batch;
	public static World world;
	public OrthographicCamera camera;
	public static Viewport viewport; 
	SigmaGame eg;
	public static BitmapFont font;
	public static Skin skin;
	public static Preferences prefs;
	public static Ui ui;
	public MageClass mage;
	public InputMultiplexer inputMultiplexer;
	public static SigmaGame gama;
	public Mapa maps[] = new Mapa[10];
	
	public Stage ctrl;
	public Stage uistage;
	public static Stage menuStage;
	public static Stage newGameStage;
	public Stage loadGameStage;
	public Stage pauseStage;
	
	public ProfileHandler prohand;
	public Profile profile;
	public Normalizer nor;
	
	
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		world = new World(new Vector2(0,0), false);
		camera = new OrthographicCamera();
		inputMultiplexer = new InputMultiplexer();
		camera.setToOrtho(false);
		maps[0] = new ISS();
		//maps[0].create(this, null);
		maps[1] = new House1();
		//maps[1].create(this, null);
		//maps[2] = new House2(this);
		maps[2] = new House2();
		//game.maps[2].create(game, stage);
		viewport = new StretchViewport(800, 480, camera);
		prohand = new ProfileHandler(this);
		

		profile = new Profile(this, "Ivan", "Male");
		
		viewport.apply(true);
		ctrl = new Stage(viewport);
		uistage = new Stage(viewport);
		menuStage = new Stage(viewport);
		newGameStage = new Stage(viewport);
		loadGameStage = new Stage(viewport);
		pauseStage = new Stage(viewport);
		
		nor = new Normalizer(this);
		//font = new BitmapFont(Gdx.files.internal("dialogues/font.fnt"),Gdx.files.internal("dialogues/font.png"), false);
		skin = new Skin(Gdx.files.internal("skin/uiskin.json"));	
		
		font = skin.getFont("fonta20");
		ui = new Ui(this, ctrl, null);

		gama = new SigmaGame(this, maps[0], uistage);
		setScreen(gama);
		
		inputMultiplexer.addProcessor(ctrl);
		
		Gdx.graphics.requestRendering();
		Gdx.input.setInputProcessor(inputMultiplexer);
	}

	@Override
	public void render () {
		super.render();
		if(mage!=null){
			ui.render(batch, mage.hp, mage.allhp, mage.mana, mage.allmana);
		}
	}
	
	public void putMage(MageClass mage){
		this.mage = mage;
	}
	
	public void putWorld(World world){
		this.world = world;
	}
	
	public void putInpt(InputMultiplexer inputMultiplexer){
		this.inputMultiplexer = inputMultiplexer;
	}
	
	public void saveGame(){
		Json json = new Json();

		String mag = json.toJson(gama.mage);
		String it = json.toJson(ui.inventory.maj);
		String map = json.toJson(gama.current);

		mag = Base64Coder.encodeString( mag );
		it = Base64Coder.encodeString( it );
		map = Base64Coder.encodeString( it );

		FileHandle magf = Gdx.files.local("magf.sav");
		magf.writeString(mag, false);
		
		FileHandle itf = Gdx.files.local("itf.sav");
		itf.writeString(mag, false);
		
		FileHandle mapf = Gdx.files.local("mapf.sav");
		mapf.writeString(mag, false);
	}
	
	public void loadGame(){
		
	}
	
	
	@Override
	public void dispose(){
		world.dispose();
		batch.dispose();
		font.dispose();
	}
	
}
