package by.coffeecat.er.objects;

import java.util.ArrayList;

import com.badlogic.gdx.utils.Array;

import by.coffeecat.er.Sigma;
import by.coffeecat.er.magic.Magic;
import by.coffeecat.er.magic.Plasma;
import by.coffeecat.er.objects.clothes.Cool;
import by.coffeecat.er.objects.clothes.DefChest;
import by.coffeecat.er.objects.clothes.Dragon;
import by.coffeecat.er.objects.clothes.HatofPeople;
import by.coffeecat.er.objects.clothes.Star;
import by.coffeecat.er.objects.food.Carrot;

public class Normalizer {

	
	Sigma game;
	
	public Normalizer(Sigma game) {
		this.game = game;
	}
	
	public ArrayList<Array<Item>> nor(ArrayList<Array<String>> majs){
		ArrayList<Array<Item>> maj = new ArrayList<Array<Item>>(25);
		for (int i = 0; i < 5; i++) {
			  maj.add(new Array<Item>(25));
		}
		for(Array<Item> array: maj){
			for (int i = 0; i < 25; i++) {
				array.add(null);
			}
		}
		
		for(int g=0;g<4;g++){   
			for(int i=0;i<maj.get(g).size;i++){
				if(maj.get(g).get(i)!=null){
					maj.get(g).add(norit(majs.get(g).get(i)));
				}
			}
		}
		
		return maj;
	}
	
	Item item;
	
	public Item norit(String itemstr){
		switch(itemstr){
		case "Cool": item = new Cool(game);
		break;
		case "Carrot": item = new Carrot(game);
		break;
		case "DefChest": item = new DefChest(game);
		break;
		case "Dragon": item = new Dragon(game);
		break;
		case "HatofPeople": item = new HatofPeople(game);
		break;
		case "Star": item = new Star(game);
		break;
		}
		
		return item;
	}
	
	Magic magic;
	
	public Magic normag(String magicstr){
		switch(magicstr){
		case "Plasma": magic = new Plasma(game.gama.world, game, game.gama.batch);
		break;
		}
		
		return magic;
	}

}
