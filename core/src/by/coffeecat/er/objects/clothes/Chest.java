package by.coffeecat.er.objects.clothes;

import com.badlogic.gdx.graphics.g2d.Animation;

import by.coffeecat.er.Sigma;

public class Chest extends Clothes{

	public Chest(int def, Sigma game) {
		super(def, game);
		chest = new Animation[5];
		cat = 1;
	}

}
