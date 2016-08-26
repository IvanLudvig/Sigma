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
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;

import by.coffeecat.er.Constants;
import by.coffeecat.er.Sigma;
import by.coffeecat.er.magic.Magic;
import by.coffeecat.er.magic.Plasma;
import by.coffeecat.er.objects.clothes.Clothes;
import by.coffeecat.er.objects.clothes.DefChest;
import by.coffeecat.er.objects.weapons.Famas;
import by.coffeecat.er.objects.weapons.Weapon;

public class MageClass {
	public static Body mage;

	
	//графика
	Animation[][] anim = new Animation[5][5];
	Animation chest[] = new Animation[4];
	Animation body[] = new Animation[4];

	public Clothes chesta;
	public Clothes feeta;
	public Clothes hata;
	public Weapon weap;
	
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
	public HumanProperties props;
	Sigma game;
	Vector2 pos;
	
	public void create(World world, Sigma game, SpriteBatch batch, Vector2 pos){
		this.game = game;
		this.pos = pos;
		mage = createMage(world);
		chesta = new DefChest(game);
		chest = chesta.chest;
		weap = new Famas(game);
		anim[4]=this.weap.main;
	
		if(game.profile.gender.equals("Male")){
			body[0] = Mage.MudrecAnim(new Texture("items/body/mbody/0.png"), 3, 1);
			body[1] = Mage.MudrecAnim(new Texture("items/body/mbody/1.png"), 3, 1);
			body[3] = Mage.MudrecAnim(new Texture("items/body/mbody/2.png"), 3, 1);
			body[2]= Mage.MudrecAnim(new Texture("items/body/mbody/3.png"), 3, 1);
		}else{
			body[0] = Mage.MudrecAnim(new Texture("items/body/fbody/0.png"), 3, 1);
			body[1] = Mage.MudrecAnim(new Texture("items/body/fbody/1.png"), 3, 1);
			body[3] = Mage.MudrecAnim(new Texture("items/body/fbody/2.png"), 3, 1);
			body[2]= Mage.MudrecAnim(new Texture("items/body/fbody/3.png"), 3, 1);
		}
		for(int i =0; i<footsteps.length;i++){
    		footsteps[i] = Gdx.audio.newMusic(Gdx.files.internal("music/fs/"+i+".mp3"));
    		footsteps[i].setLooping(false);
    		footsteps[i].setVolume(0.1f);
		}
		
		anim[0]=body;
		anim[1]=chest;
		
		magico[1] = new Plasma(world, game, batch);
		game.ui.magicUi(magico[1]);
		
		props = new HumanProperties(chesta, chesta, chesta, allhp, allhp, allhp, allhp, allhp, allhp, allhp, allhp, magico);
		
		
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
 	   if(weap!=null){
	   	     TextureRegion currentFrame3 = anim[4][side-1].getKeyFrame(Mage.stateTime, true); 
	       	 batch.draw(currentFrame3, (mage.getWorldCenter().x-20/PTM)*PTM,( mage.getWorldCenter().y-15/PTM)*PTM, 35,60);
	   }
 	   if(walking){
 		   Mage.stateTime += Gdx.graphics.getDeltaTime(); 
 		   footsteps[7].play();
 	   }

   	 batch.end();
   	 updatePlease(Gdx.graphics.getDeltaTime());
   	 renderPlasma();
    }
    
    float reTime = 0f;
    
    public void updatePlease(float delta){
     	timer+=delta;
     	reTime+=delta;
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
    
    public void renderPlasma(){
     	magico[1].render();
     	if(weap!=null){
     		weap.render();
     	}
    }
    
    public Touchpad touchpad;
    public Vector2 lookdir = new Vector2(1, 0);
    
    public void moveMage(Touchpad touchpad, OrthographicCamera camera){
    	this.touchpad = touchpad;
    	mage.setTransform(getPos().x/PTM,  getPos().y/PTM, 0);
      	 mage.setLinearVelocity(new Vector2(touchpad.getKnobPercentX()*1.2f, touchpad.getKnobPercentY()*1.2f));
      	 if((Math.abs(touchpad.getKnobPercentX())>0.07)&&(Math.abs(touchpad.getKnobPercentY())>0.07)){
      		 lookdir = new Vector2(touchpad.getKnobPercentX()*1.2f, touchpad.getKnobPercentY()*1.2f);
      	 }
      	 

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
		props = new HumanProperties(chesta, chesta, chesta, allhp, allhp, allhp, allhp, allhp, allhp, allhp, allhp, magico);
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
    	
		props = new HumanProperties(chesta, chesta, chesta, allhp, allhp, allhp, allhp, allhp, allhp, allhp, allhp, magico);
    }
    
    public void applyWp(Weapon weap){
    	this.weap = weap;
		anim[4]=this.weap.main;
    }
    
    public void applyProps(HumanProperties properties){
    	props = properties;
    	
		this.chesta = (Clothes) game.nor.norit(properties.chesta);
		this.feeta = (Clothes) game.nor.norit(properties.feeta);
		this.hata = (Clothes) game.nor.norit(properties.hata);
		
		this.allhp = properties.allhp;
		this.hp = properties.hp;
		this.armour = properties.armour;
		this.allmana = properties.allmana;
		this.mana = properties.mana;
		//this.reRate = reRate;
		this.allstrength = properties.allstrength;
		this.strength = properties.strength;
		
		for(int y = 0; y<properties.magico.length; y++){
			if(properties.magico[y]!=null){
				magico[y] = game.nor.normag(properties.magico[y]);
			}
		}
    }
    
	public  Body createMage(World world){
		BodyDef magic = new BodyDef();
        magic.type = BodyDef.BodyType.DynamicBody;
        magic.position.set(pos.x/Constants.PTM, pos.y/Constants.PTM);
                                                     
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(16/2/ Constants.PTM, 20/2/ Constants.PTM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.friction = 100f;
        fixtureDef.density = 100f;
        fixtureDef.restitution = 0.001f;

        Body mage = world.createBody(magic);
        mage.createFixture(fixtureDef);
        shape.dispose();
        
        return mage;
	}

}
