package by.coffeecat.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import by.coffeecat.er.Constants;
import by.coffeecat.er.Profile;
import by.coffeecat.er.Sigma;

public class NewGame extends ScreenAdapter{


	public SpriteBatch batch;
	public OrthographicCamera camera;
	Viewport viewport; 
	public Sigma game;
	float PTM = Constants.PTM;
	Stage stage;
	Skin skin = Sigma.skin;
	
	TextField name;
	TextButton create;
	TextButton back;
	Label namelab;
	Label genderlab;
	SelectBox<String> gender;
	
	Profile profile;
	
	Music music;
	
	Texture bg;
	
	public NewGame(Sigma gamee, Music music) {
		this.game = gamee;
		batch = game.batch;
		camera = game.camera;
		camera.setToOrtho(false);
		viewport = game.viewport;
		this.stage = game.newGameStage;
		this.music = music;
		this.music.setVolume(0.4f);
		this.music.setLooping(false);
		this.music.play();
		
		bg = new Texture(Gdx.files.internal("menubg.png"));
		
		
		namelab = new Label("Name:",skin);
		namelab.setPosition(260, 360);
		
		name = new TextField("", skin);
		name.setPosition(310, 350);
		
		genderlab = new Label("Gender:",skin);
		genderlab.setPosition(300, 290);
		
		gender = new SelectBox(skin);
		gender.setSize(75, gender.getHeight());
		gender.setItems(new String[]{"Male", "Female"});
		gender.setPosition(360, 280);
		
		create = new TextButton("Done!", skin);
		create.setPosition(370, 100);
		
		back = new TextButton("Back", skin);
		back.setPosition(300, 100);
		
		this.stage.addActor(name);
		this.stage.addActor(create);
		this.stage.addActor(namelab);
		this.stage.addActor(back);
		this.stage.addActor(gender);
		this.stage.addActor(genderlab);
		
		
	    create.addListener(new ChangeListener() {
	        @Override
	        public void changed (ChangeEvent event, Actor actor) {
	        	if((gender.getSelected()!=null)&&(!name.getText().equals(""))){
		        	profile = new Profile();
		        	profile.gender = gender.getSelected();
		        	profile.newGame(name.getText());
		        	game.setScreen(game.gama);
		        	System.out.println("Clicktion");
		        	//game.profile = profile;
		            //game.setScreen(game.gama);
		            game.inputMultiplexer.removeProcessor(stage);
	        	}
	        }
	    });
	    
	    back.addListener(new ChangeListener() {
	        @Override
	        public void changed (ChangeEvent event, Actor actor) {
	            game.setScreen(new Main(game, game.menuStage));
	            game.inputMultiplexer.removeProcessor(stage);
	        }
	    });
	    
	    
        game.inputMultiplexer.addProcessor(stage);
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0,0,0,0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
        batch.begin();
        batch.setProjectionMatrix(camera.combined);
		batch.draw(bg, 0, 0, 750, 480);
		batch.end();
		
		stage.draw();
		stage.act(delta);
		

	}
	
	@Override
	public void dispose(){
		music.dispose();
		stage.dispose();
	}
	
	

}
