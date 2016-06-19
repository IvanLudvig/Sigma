package by.coffeecat.er.objects.weapons;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.utils.Array;

import by.coffeecat.er.Sigma;
import by.coffeecat.er.objects.Bullet;
import by.coffeecat.er.objects.Item;

public class Weapon extends Item{

	public Animation[] main;
	public Animation[] aim;
	public int speed;
	public int bps;
	public int ammo;
	public int maxammo;
	public int clip;
	public Array<Bullet> bullets = new Array<Bullet>(100);
	
	public Weapon(int speed, int bps, int ammo, int maxammo, int clip, final Sigma game) {
		this.game = game;
		main = new Animation[5];
		bullets.add(null);
	}
	

	@Override
	public void use() {
		shoot();
	}
	
	public void reload(){
		if(ammo>=maxammo){
			clip+=maxammo-ammo;
		}
	}
	
	public void render(){
		for(Bullet bullet : bullets){
			if(bullet!=null){
				bullet.render();
			}
		}
	}
	
	public void shoot(){
		clip-=1;
		game.gama.mage.applyWp(this);
		bullets.add(new Bullet(game));
		System.out.println("JL");
		for(Bullet bullet : bullets){
			if(bullet!=null){
				bullet.render();
			}
		}
	}

}
