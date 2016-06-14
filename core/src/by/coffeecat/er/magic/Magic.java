/**
 * Кукушка
 */
package by.coffeecat.er.magic;

import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;

public abstract class Magic{

	int dam;
	float speed;
	int mana;
	public  ParticleEffect eff[] = new ParticleEffect[20];
	public ImageButton btn;
	public Body body;
	public One one[] =  new One[100];
	
	public Magic() {
	}
	
	public void newOne(int g){
		one[g]= new One(speed,mana,dam);
	}

	
	public abstract void render();
	
	
	
	
}
