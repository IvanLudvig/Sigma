package by.coffeecat.er.animal;

import by.coffeecat.er.Sigma;
import by.coffeecat.er.body.Mage;
import by.coffeecat.er.body.MakeAnimation;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Turtle extends Animal{

	public Turtle(Sigma game, World world, Stage stage, Vector2 size,
			SpriteBatch batch, Vector2 pos) {
		super(game, world, stage, size, batch, pos);
		
		this.anim[0] = new MakeAnimation(new Texture("characters/turtle/0.png"), 4, 1);
		this.anim[1] = new MakeAnimation(new Texture("characters/turtle/1.png"), 4, 1);
		this.anim[3] = new MakeAnimation(new Texture("characters/turtle/2.png"), 4, 1);
		this.anim[2]= new MakeAnimation(new Texture("characters/turtle/3.png"), 4, 1);
	}


}
