package by.coffeecat.er.objects.weapons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.utils.Array;

import by.coffeecat.er.Sigma;
import by.coffeecat.er.body.Bullet;
import by.coffeecat.er.objects.Item;

public class Weapon extends Item{

	public Animation[] main;
	public Animation[] aim;
	public int speed;
	public int bps;
	public int ammo = 30;
	public int maxammo;
	public int clip;
	public Array<Bullet> bullets = new Array<Bullet>(100);
	Music[] sound = new Music[3];
	int g = 0;
	
	public Weapon(int speed, int bps, int ammo, int maxammo, int clip, final Sigma game) {
		this.game = game;
		main = new Animation[5];
		this.speed = speed;
		this.bps = bps;
		this.ammo = ammo;
		this.maxammo = maxammo;
		this.clip = clip;
		for(int f=0;f<sound.length;f++){
			sound[f] = Gdx.audio.newMusic(Gdx.files.internal("items/weapons/rifle.mp3"));
			sound[f].setVolume(0.2f);
			sound[f].setLooping(false);
		}
	}
	

	@Override
	public void use() {
		game.gama.mage.applyWp(this);
	}
	
	public void reload(){
		if(ammo>maxammo){
			int tmp = 0;
			//tmp = maxammo-clip;
			ammo-=maxammo-clip;
			clip+=maxammo-clip;
		}else{
			clip+=ammo;
			ammo = 0;
		}
	}
	
	public void render(){
		for(Bullet bullet : bullets){
			if(bullet!=null){
				bullet.render();
			}
		}
	}
	
	public void sort(){
		for(Bullet bullet : bullets){
			if(bullet.life == 0){
				for(int g = 0;g<bullets.size;g++){
					if(bullets.get(g).equals(bullet)){
						bullets.removeIndex(g);
					}
				}
			}
		}
	}
	
	public void shoot(){
		if(clip>0){
			sort();
			clip-=1;
			bullets.add(new Bullet(game));
			System.out.println("JL");
			sound[g].play();
			g++;
			if(g>=3){
				g=0;
			}
		}
	}

}
