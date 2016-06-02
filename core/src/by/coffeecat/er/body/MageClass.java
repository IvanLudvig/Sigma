package by.coffeecat.er.body;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;

import by.coffeecat.er.Constants;
import by.coffeecat.er.Sigma;
import by.coffeecat.er.magic.Magic;
import by.coffeecat.er.magic.Plasma;
import by.coffeecat.er.objects.clothes.Clothes;
import by.coffeecat.er.objects.clothes.DefChest;

public class MageClass {
	public static Body mage;

	
	//графика
	Animation[][] anim = new Animation[4][4];
	Animation chest[] = new Animation[4];
	Animation body[] = new Animation[4];

	public Clothes chesta;
	public Clothes feeta;
	public Clothes hata;
	
	Animation hatback;
	Animation hatleft;
	Animation hatright;
	Animation hatfront;
	
	Animation feetback;
	Animation feetleft;
	Animation feetright;
	Animation feetfront;
	
	public static Texture[] chesttxt = new Texture[5];
	public static Texture[] hat = new Texture[5];
	public static Texture[] boots = new Texture[5];
	
	float PTM = Constants.PTM;
	private int side=1;
	
	private boolean walking;
	
	public int allhp = 100;
	public int hp = allhp;
	
	public int armour = allhp;
	
	public int allmana = 500;
	public int mana = allmana;
	public float reRate = 1f;
	
	public int allstrength = 300;
	public int strength = allstrength;

	public Magic magico[] = new Magic[10];
	
	Music[] footsteps = new Music[10];
	
	public void create(World world, Sigma game, SpriteBatch batch){
		mage = Mage.createMage(world);

		chesta = new DefChest(game);
		chest = chesta.chest;
		
		body[0] = Mage.MudrecAnim(new Texture("items/body/bodfront.png"), 3, 1);
		body[1] = Mage.MudrecAnim(new Texture("items/body/bodback.png"), 3, 1);
		body[2] = Mage.MudrecAnim(new Texture("items/body/bodleft.png"), 3, 1);
		body[3]= Mage.MudrecAnim(new Texture("items/body/bodright.png"), 3, 1);
		
		for(int i =0; i<footsteps.length;i++){
    		footsteps[i] = Gdx.audio.newMusic(Gdx.files.internal("music/fs/"+i+".mp3"));
    		footsteps[i].setLooping(false);
    		footsteps[i].setVolume(0.1f);
		}
		
		anim[0]=body;
		anim[1]=chest;
		
		magico[1] = new Plasma(world, game, batch);
		game.ui.magicUi(magico[1]);
		
		
	}
	
	float timer;

    public void drawit(SpriteBatch batch){
   	 batch.begin();
   	  
   	 TextureRegion currentFrame = anim[0][side-1].getKeyFrame(Mage.stateTime, true);  
     	
   	   
   	 batch.draw(currentFrame, (mage.getWorldCenter().x-20/PTM)*PTM,( mage.getWorldCenter().y-15/PTM)*PTM, 35,60);
 	   if(chesta!=null){
	   	     TextureRegion currentFrame1 = anim[1][side-1].getKeyFrame(Mage.stateTime, true); 
	       	 batch.draw(currentFrame1, (mage.getWorldCenter().x-20/PTM)*PTM,( mage.getWorldCenter().y-15/PTM)*PTM, 35,60);
 	   }
 	   if(hata!=null){
	   	     TextureRegion currentFrame2 = anim[2][side-1].getKeyFrame(Mage.stateTime, true); 
	       	 batch.draw(currentFrame2, (mage.getWorldCenter().x-20/PTM)*PTM,( mage.getWorldCenter().y-15/PTM)*PTM, 35,60);
	   }
 	   if(feeta!=null){
	   	     TextureRegion currentFrame3 = anim[3][side-1].getKeyFrame(Mage.stateTime, true); 
	       	 batch.draw(currentFrame3, (mage.getWorldCenter().x-20/PTM)*PTM,( mage.getWorldCenter().y-15/PTM)*PTM, 35,60);
	   }
 	   if(walking){
 		   Mage.stateTime += Gdx.graphics.getDeltaTime(); 
 		   footsteps[7].play();
 	   }

   	 batch.end();
   	 updatePlease(Gdx.graphics.getDeltaTime());
    }
    
    float reTime = 0f;
    
    public void updatePlease(float delta){
     	timer+=delta;
     	reTime+=delta;
     	magico[1].render();
     	if(mana>=allmana){
     		mana = allmana;
     	}else{
     		if(reTime>reRate*1.5f){
     			mana+=1;
     			reTime = 0;
     		}
     	}
     	if(hp>allhp){
     		hp = allhp;
     	}
     	
    }
    
    public Touchpad touchpad;
    public void moveMage(Touchpad touchpad, OrthographicCamera camera){
    	//plasma.cast(getPos(), new Vector2(touchpad.getKnobPercentX()*1.2f, touchpad.getKnobPercentY()*1.2f));
    	this.touchpad = touchpad;
      	 mage.setLinearVelocity(new Vector2(touchpad.getKnobPercentX()*1.2f, touchpad.getKnobPercentY()*1.2f));
      	 mage.setTransform(getPos().x/PTM,getPos().y/PTM, 0);

       	 if((touchpad.getKnobPercentY()>touchpad.getKnobPercentX())&&(touchpad.getKnobPercentY()>0.3)){
       		 walking=true;
       		 side = 2;
       	 }

       	 else if((touchpad.getKnobPercentY()<touchpad.getKnobPercentX())&&(touchpad.getKnobPercentY()<-0.3)){
       		 walking=true;
       		 side = 1;
       	 }

       	else if((touchpad.getKnobPercentX()>touchpad.getKnobPercentY())&&(touchpad.getKnobPercentX()>0.3)){
       		 walking=true;
       		 side = 4;
       	 }

       	else if((touchpad.getKnobPercentX()<touchpad.getKnobPercentY())&&(touchpad.getKnobPercentX()<-0.3)){
       		 walking=true;
       		 side = 3;
       	 }
       	 else{
       		 walking =false;
       	 }
       	 if(mage.getPosition().x*PTM>camera.position.x+30){
       		 float lerp = 0.2f;
       		 camera.position.x+=10*lerp;
       	 }
       	 if(mage.getPosition().x*PTM<camera.position.x-30){
       		 float lerp = 0.2f;
       		 camera.position.x-=10*lerp;
       	 }
       	 if(mage.getPosition().y*PTM>camera.position.y+30){
       		 float lerp = 0.2f;
       		 camera.position.y+=10*lerp;
       	 }
       	 if(mage.getPosition().y*PTM<camera.position.y-30){
       		 float lerp = 0.2f;
       		 camera.position.y-=10*lerp;
       	 }
    }
    
    public void setPos(Vector2 pos){
    	mage.setTransform(pos.x/PTM,  pos.y/PTM, 0);
    }
    
    public Vector2 getPos(){
    	Vector2 pos = new Vector2();
    	pos.set(new Vector2(mage.getPosition().x*PTM, mage.getPosition().y*PTM));
    	mage.setTransform(pos.x/PTM,  pos.y/PTM, 0);
    	
    	return pos;
    }
    
    public void minusMana(int amount){
    	mana-=amount;
    }
    public void changeClothes(Clothes brandnew){
    	if(brandnew.chest!=null){
    		System.out.println("New clothes");
    		anim[1]=brandnew.chest;
    		chesta = brandnew;
    	}
    	if(brandnew.hat!=null){
    		System.out.println("New hat");
    		anim[2]=brandnew.hat;
    		hata = brandnew;
    	}
    	if(brandnew.feet!=null){
    		System.out.println("New shoes");
    		anim[3]=brandnew.feet;
    		feeta = brandnew;
    	}
    }

}
