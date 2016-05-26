package by.coffeecat.er.ui;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;

import by.coffeecat.er.Sigma;
import by.coffeecat.er.objects.Item;
import by.coffeecat.er.objects.food.Carrot;

public class Inventory {
	
	Stage stage;
	
	public ArrayList<Array<Item>> maj = new ArrayList<Array<Item>>(5);
	
	//Tables
	//public Table[] table = new Table[4]; //ТАМ 3!!!
	
	public Table table;
	public Window win;
	
	//Table buttons; //Здесь расположить кнопки, им тут понравится
	Table tabl;
	//1 - food
	//2 - clothes
	//3 - weapons
	//4 - potions
	
	//buttons (батоны)
	/*
	ImageButton foodbtn;
	ImageButton clothesbtn;
	ImageButton weaponsbtn;
		*/
	ImageButton open;

	public Image equip;
	
	Sigma game;
	int here = 0;
	Music music;

	public Inventory(ArrayList<Array<Item>> thing, Sigma game, Stage stage) {
		this.stage = stage;
		this.game = game;
		open = makeBtn(new Texture("ui/open.png"));
		

		win = new Window("Inventory", game.skin);
		
		table = new Table();
		table.setSize(300, 200);
		equip = new Image(new TextureRegionDrawable( new TextureRegion((new Texture("ui/equip.png")))));
		win.setSize(300, 300);
		
		addList();
		 
		win.setPosition(game.camera.position.x+480, game.camera.position.y-100);
		win.debug();
		table.debug();
		
		if(thing!=null){
			maj = thing;
		}else{
			System.out.println("вот такие делишки");
			maj.get(1).add(new Carrot(game));
			for (int i = 0; i < 5; i++) {
				  maj.add(new Array<Item>(25));
			}
			
			for(Array<Item> array: maj){
				array.add(null);
			}
		}


		for(int g=0;g<4;g++){   //Переменную g надо поменять!!
			for(int i=0;i<maj.get(g).size;i++){
				if(maj.get(g).get(i)!=null){
					table.add(maj.get(g).get(i).btn).width(40).height(40).top().left();
					//win.add(maj.get(g).get(i).btn).width(40).height(40).top();
					System.out.println("Туттт");
				}
			}
		}
		int f = 0;
		
		
		table.debug();
		win.add(table).width(250).height(200).top();
		win.row();
		win.add(equip).width(50).height(50);
		stage.addActor(win);	
		
		//open.debug();
		stage.addActor(open);

	}
		
		

	public void render(){
		stage.draw();
		stage.act();
		open.setPosition(game.camera.position.x+180+150, game.camera.position.y+200);
		if(here==0){
				win.remove();
    		//win.addAction(Actions.moveTo(game.camera.position.x+250, game.camera.position.y-150, 1f));
		}
		
	}
	
	public void addItem(Item item){
		System.out.println(item.cat);
		System.out.println("No");
		maj.get(item.cat).add(item);

		recreate();
		}
		
		/*
		  		for(int g=0;g<maj.get(item.cat).size;g++){
			if(maj.get(item.cat).get(g)==item){
				table.add(maj.get(item.cat).get(g).btn);
			}
		}
		 */
	public void delItem(Item item){
		for(int i=0;i<maj.size();i++){
			for(int g=0;g<maj.get(i).size;g++){
				if(maj.get(i).get(g)==item){
					maj.get(i).removeIndex(g);
					recreate();
					System.out.println("Yes");
				}
			}
		}
	}
	
	public void recreate(){
		table.reset();
		table.debug();
		for(int g=0;g<4;g++){   //Переменную g надо поменять!!
			for(int i=0;i<maj.get(g).size;i++){
				if(maj.get(g).get(i)!=null){
					table.add(maj.get(g).get(i).btn).width(40).height(40).top().left();
					//win.add(maj.get(g).get(i).btn).width(40).height(40).top();
					System.out.println("Туттт");
				}
			}
		}
	}
	
	
	public ImageButton makeBtn(Texture texture){
		Image im = new Image(texture);
		ImageButtonStyle style = new ImageButtonStyle();
		style.imageUp = im.getDrawable();
		style.up = im.getDrawable();
		style.down = im.getDrawable();
		style.over = im.getDrawable();
		style.pressedOffsetX = 5;
		style.pressedOffsetY = 5;
		ImageButton btn = new ImageButton(style);
		
	       
		return btn;
	}
	
	public void addList(){
	       open.addListener(new ClickListener(){
	            @Override
	            public void clicked(InputEvent event, float x, float y) {
	        		music = Gdx.audio.newMusic(Gdx.files.internal("music/inv.mp3"));
	        		music.play();
	        		music.setLooping(false);
	        		music.setVolume(0.6f);
	            	switch(here){
	            	
	            		case 0:
	            			here=1;
	            			stage.addActor(win);
	            			break;
	            		case 1:
	            			here=0;
	            			break;
	            	}
	            	System.out.println(here);
	            }
	        });

/*
	       foodbtn.addListener(new ClickListener(){
	            @Override
	            public void clicked(InputEvent event, float x, float y) {
	            	last = active;
	            	active = 0;
        			here=1;
	            }
	        });	      
	       
	       clothesbtn.addListener(new ClickListener(){
	            @Override
	            public void clicked(InputEvent event, float x, float y) {
	            	last = active;
	            	active = 1;
        			here=1;
	            }
	        });	       
	       weaponsbtn.addListener(new ClickListener(){
	            @Override
	            public void clicked(InputEvent event, float x, float y) {
	            	last = active;
        			here=1;
	            	active = 2;
	            }
	        });
	
	}
	*/
	}
}


