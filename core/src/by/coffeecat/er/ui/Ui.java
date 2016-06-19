package by.coffeecat.er.ui;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;

import by.coffeecat.er.Sigma;
import by.coffeecat.er.magic.Magic;
import by.coffeecat.er.objects.Item;
import by.coffeecat.er.objects.clothes.Cool;
import by.coffeecat.er.objects.clothes.DefChest;
import by.coffeecat.er.objects.clothes.HatofPeople;
import by.coffeecat.er.objects.food.Carrot;
import by.coffeecat.er.objects.weapons.Famas;

public class Ui {

	Texture health;
	Texture mana;
	BitmapFont font;
	public static OrthographicCamera camera;
	public static Viewport viewport; 
	
	Sigma end;
	public static Stage stage;
	public Inventory inventory;
	Sigma game;
	Table l;
	//Maja[] maj = new Maja[4];
	public ArrayList<Array<Item>> maj = new ArrayList<Array<Item>>(5);
	
	public Ui(Sigma game, Stage stage, ArrayList<Array<Item>> inven) {
		end = game;
		if(inven!=null){
			maj=inven;
		}
		for (int i = 0; i < 5; i++) {
			  maj.add(new Array<Item>(25));
		}
		for(Array<Item> array: maj){
			array.add(null);
		}
		maj.get(0).add(new Carrot(game));
		maj.get(1).add(new DefChest(game));
		maj.get(1).add(new Cool(game));
		maj.get(1).add(new HatofPeople(game));
		maj.get(3).add(null);
		maj.get(2).add(new Famas(game));
		health = new Texture("ui/health.png");
		mana = new Texture("ui/mana.png");
		font = game.font;
		viewport = game.viewport;
		this.stage = stage;
		this.game = game;
		camera = game.camera;
		l = new Table();
		inventory = new Inventory(maj, game, stage);
		stage.addActor(l);
	}
	
	public void render(SpriteBatch batch, int hp, int allhp, int mana, int allmana){
		if(game.gama.paused == 0){
			l.setPosition(camera.position.x+200, camera.position.y-210);
			inventory.render();
			batch.setProjectionMatrix(camera.combined);
			camera.update();
			batch.begin();
			batch.draw(health, camera.position.x-385, camera.position.y+210, hp*2, 25);
			batch.draw(this.mana, camera.position.x-385, camera.position.y+185, mana/2, 25);
			font.draw(batch,hp+"/"+allhp+"hp" , camera.position.x-385, camera.position.y+230);
			font.draw(batch,mana+"/"+allmana+"mana" , camera.position.x-385, camera.position.y+205);
			batch.end();
		}
	
	}
	
	public void magicUi(Magic magic){
		l.add(magic.btn).width(50).height(50);
	}

}
