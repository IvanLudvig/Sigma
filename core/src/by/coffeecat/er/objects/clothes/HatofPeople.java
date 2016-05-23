package by.coffeecat.er.objects.clothes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import by.coffeecat.er.Sigma;
import by.coffeecat.er.body.Mage;

public class HatofPeople extends Head{

	public HatofPeople(Sigma game) {
		super(1, game);
		cat = 1;
		texture = new Texture(Gdx.files.internal("items/clothes/hat/hatofking/im.png"));
		hat[0] =  Mage.MudrecAnim(new Texture("items/clothes/hat/hatofking/hat0.png"), 3, 1);
		hat[1] =  Mage.MudrecAnim(new Texture("items/clothes/hat/hatofking/hat1.png"), 3, 1);
		hat[2] = Mage.MudrecAnim( new Texture("items/clothes/hat/hatofking/hat2.png"), 3, 1);
		hat[3]=  Mage.MudrecAnim(new Texture("items/clothes/hat/hatofking/hat3.png"), 3, 1);
		size = new Vector2(23,23);
		cost = 7;
		btn = this.getBtn();
	}



}
