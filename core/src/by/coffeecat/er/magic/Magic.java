/**
 * Кукушка
 */
package by.coffeecat.er.magic;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;

public abstract class Magic{

	int dam;
	float speed;
	int mana;
	public ImageButton btn;
	public Body body;
	public One one[] =  new One[100];
	public int count;
	
	public Magic() {
	}
	
	public void newOne(int g){
		one[g]= new One(speed,mana,dam, this.count());
	}
	
	public int count(){
		for(int g=0;g<=100;g++){
			if(one[g]!=null){
				if(one[g].count==g){
					continue;
				}else{
					count = g;
					break;
				}
			}
		}
		
		return count;
	}

	
	public abstract void render();
	
	
	
	
}
