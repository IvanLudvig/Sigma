package by.coffeecat.er.objects.clothes;

import com.badlogic.gdx.graphics.g2d.Animation;

import by.coffeecat.er.Sigma;
import by.coffeecat.er.objects.Item;

public class Clothes extends Item{

	public Animation[] chest;
	public Animation[] hat;
	public Animation[] feet;
	
	public Clothes(int def, final Sigma game) {
		this.game = game;
		cat = 1;
	}
	

	@Override
	public void use() {
		game.mage.changeClothes(this);
	}

}
