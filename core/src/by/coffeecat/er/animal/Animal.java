package by.coffeecat.er.animal;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import by.coffeecat.er.Constants;
import by.coffeecat.er.Sigma;
import by.coffeecat.er.body.Mage;

public class Animal extends Actor{

	public static Body body;
	int side = 0;
	
	Animation anim[] = new Animation[4];
	SpriteBatch batch;
	Mage mage = new Mage();
	float PTM = Constants.PTM;
	Sigma game;
	
	public Animal(Sigma game, World world, Stage stage, Vector2 size, SpriteBatch batch) {
		this.batch = batch;
		this.game = game;
		body = mage.createB(world, new Vector2(size.x/PTM, size.y/PTM));
/*		
		anim[0] = Mage.MudrecAnim(new Texture("items/body/mbody/0.png"), 3, 1);
		anim[1] = Mage.MudrecAnim(new Texture("items/body/mbody/1.png"), 3, 1);
		anim[3] = Mage.MudrecAnim(new Texture("items/body/mbody/2.png"), 3, 1);
		anim[2]= Mage.MudrecAnim(new Texture("items/body/mbody/3.png"), 3, 1);
		*/
		
	}
	
	public void draw(){
	   	 TextureRegion currentFrame = anim[side-1].getKeyFrame(mage.stateTime, true);  
	   	 batch.draw(currentFrame, (body.getWorldCenter().x-20/PTM)*PTM,( body.getWorldCenter().y-15/PTM)*PTM, 35,60);
	}

}
