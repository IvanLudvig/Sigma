package by.coffeecat.er.objects.weapons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

import by.coffeecat.er.Sigma;
import by.coffeecat.er.body.Mage;

public class Famas extends Weapon{

	public Famas(Sigma game) {
		super(2, 3, 95, 35, 0, game);
		texture = new Texture(Gdx.files.internal("items/weapons/famas/im.png"));
		main[0] =  Mage.MudrecAnim(new Texture("items/weapons/famas/0.png"), 3, 1);
		main[1] =  Mage.MudrecAnim(new Texture("items/weapons/famas/1.png"), 3, 1);
		main[2] = Mage.MudrecAnim( new Texture("items/weapons/famas/2.png"), 3, 1);
		main[3]=  Mage.MudrecAnim(new Texture("items/weapons/famas/3.png"), 3, 1);
		size = new Vector2(23,23);
		cost = 23;
		btn = this.getBtn();
	}


}
