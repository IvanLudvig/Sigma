package by.coffeecat.er.objects.clothes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import by.coffeecat.er.Sigma;
import by.coffeecat.er.body.Mage;

public class Star extends Chest{

	public Star(Sigma game) {
		super(7, game);
		cat = 1;
		texture = new Texture(Gdx.files.internal("items/clothes/chest/star/im.png"));
		chest[0] =  Mage.MudrecAnim(new Texture("items/clothes/chest/star/star0.png"), 3, 1);
		chest[1] =  Mage.MudrecAnim(new Texture("items/clothes/chest/star/star1.png"), 3, 1);
		chest[2] = Mage.MudrecAnim( new Texture("items/clothes/chest/star/star2.png"), 3, 1);
		chest[3]=  Mage.MudrecAnim(new Texture("items/clothes/chest/star/star3.png"), 3, 1);
		size = new Vector2(23,23);
		cost = 23;
		btn = this.getBtn();
	}

}
