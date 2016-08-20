package by.coffeecat.er.body;

import by.coffeecat.er.objects.weapons.Weapon;

import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class Enemy extends Human{
	
	Weapon weap;
	MakeAnimation anim[];

	public Enemy(World world, Stage stage, Weapon weap, MakeAnimation anim[]) {
		super(world, stage);
		this.weap = weap;
	}
	
	public void attack(Human human){
		
	}
	
	

}
