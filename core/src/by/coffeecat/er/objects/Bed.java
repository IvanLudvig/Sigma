package by.coffeecat.er.objects;

/*
 * Ничего пока не работает. Наверное, стоит забыть об этом... 
 */

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.utils.Array;

import by.coffeecat.er.Sigma;

public class Bed {
	Array<Body> beds;
	Sigma game;

	public Bed(Array<Body> beds, Sigma game) {
		this.beds=beds;
		this.game=game;
	
		System.out.println(beds.size);
	}
	
	public void render(Array<Body> beds, Sigma game){
		if(game.mage!=null){
			for(Body bed : beds){
				System.out.println(bed.getPosition().x*100f);
				if(Math.sqrt(game.mage.getPos().dst(new Vector2(bed.getPosition().x*100f, bed.getPosition().y*100f)))<10){
					System.out.println("BED!!! GO TO SLEEP!!!");
				}else{
					return;
				}
			}
		}
	}

}
