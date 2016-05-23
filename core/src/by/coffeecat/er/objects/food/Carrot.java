package by.coffeecat.er.objects.food;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import by.coffeecat.er.Sigma;

public class Carrot extends Food{
	public Carrot(Sigma game) {
		super(10, game);
		cat = 0;
		texture = new Texture(Gdx.files.internal("items/food/carrot.png"));
		btn = this.getBtn();
		size = new Vector2(20,30);
		cost = 1;
	}
	
}
