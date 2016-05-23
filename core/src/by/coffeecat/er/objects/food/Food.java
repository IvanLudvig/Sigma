package by.coffeecat.er.objects.food;

import by.coffeecat.er.Sigma;
import by.coffeecat.er.objects.Item;

public class Food extends Item{
	
	public int restore;
	Sigma game;
	
	public Food(int rest, final Sigma game) {
		restore = rest;
		this.game = game;
		cat = 0;
	}
	
	@Override
	public void use() {
		game.mage.mana+=5;
		game.mage.hp+=restore;
		delete();
	}
	
	
	
}

