package by.coffeecat.er.objects.clothes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.Vector2;

import by.coffeecat.er.Sigma;
import by.coffeecat.er.body.Mage;

public class DefChest extends Chest{

	public DefChest(Sigma game) {
		super(6, game);
		cat = 1;
		texture = new Texture(Gdx.files.internal("items/clothes/deffch.png"));
		chest =  new Animation[5];
		chest[0] =  Mage.MudrecAnim(new Texture("items/clothes/deffront.png"), 3, 1);
		chest[1] =  Mage.MudrecAnim(new Texture("items/clothes/defback.png"), 3, 1);
		chest[2] =  Mage.MudrecAnim(new Texture("items/clothes/defleft.png"), 3, 1);
		chest[3]= Mage.MudrecAnim( new Texture("items/clothes/defright.png"), 3, 1);
		size = new Vector2(23,23);
		cost = 15;
		btn = this.getBtn();
	}

}
