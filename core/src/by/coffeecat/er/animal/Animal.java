package by.coffeecat.er.animal;

import by.coffeecat.er.Constants;
import by.coffeecat.er.Sigma;
import by.coffeecat.er.body.Mage;
import by.coffeecat.er.body.MakeAnimation;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public class Animal extends Actor{

	Body body;
	int side = 0;
	
	MakeAnimation anim[] = new MakeAnimation[4];
	SpriteBatch batch;
	float PTM = Constants.PTM;
	Sigma game;
	
	Vector2 pos;
	boolean walking = false;
	
	SequenceAction scenarij = new SequenceAction();
	MoveToAction action;
	
	Vector2[] actpos = new Vector2[100];
	int allActions = 0;
	
	public Animal(Sigma game, World world, Stage stage, Vector2 size, SpriteBatch batch, Vector2 pos) {
		super();
		this.batch = batch;
		this.game = game;
		this.pos = pos;
		body = createB(world, new Vector2(size.x/PTM, size.y/PTM), pos);
/*		
		this.anim[0] = new MakeAnimation(new Texture("items/body/mbody/0.png"), 3, 1);
		this.anim[1] = new MakeAnimation(new Texture("items/body/mbody/1.png"), 3, 1);
		this.anim[3] = new MakeAnimation(new Texture("items/body/mbody/2.png"), 3, 1);
		this.anim[2]= new MakeAnimation(new Texture("items/body/mbody/3.png"), 3, 1);
		*/
		
	}
	
	/*
	public void draw(){
	   	 TextureRegion currentFrame = anim[side-1].getKeyFrame(mage.stateTime, true);  
	   	 batch.draw(currentFrame, (body.getWorldCenter().x-20/PTM)*PTM,( body.getWorldCenter().y-15/PTM)*PTM, 35,60);
	}
	*/
	
	float delta;
	
	public void drawit(SpriteBatch batch, Stage stage){
		body.setTransform(this.getX()/PTM,  this.getY()/PTM, 0);
		delta = Gdx.graphics.getDeltaTime();
		
		stage.act(delta);
		stage.draw();

		batch.begin();

   		 if(walking){
   			 anim[side].stateTime += Gdx.graphics.getDeltaTime(); 
   		 }
       	
  	     TextureRegion currentFrame = anim[side].getAnim().getKeyFrame(anim[side].stateTime, true);  
	   	 batch.draw(currentFrame, (body.getWorldCenter().x-20/PTM)*PTM,( body.getWorldCenter().y-15/PTM)*PTM, 35,60);
		 batch.end();

		MoveTo();
		
		stage.addActor(this);
}
	
	MoveToAction moveaction;
	boolean aip = false;
	int odinakovo = 0;
	public int curact = 0;
	
	public void MoveTo(){

		if(curact+1<=allActions){
			System.out.println(actpos[curact]);
			//Vector2 pos = new Vector2(actpos[allActions-scenarij.getActions().size]);
			pos = new Vector2(actpos[curact]);
			
			if((Math.abs(getPos().y-pos.y)<=10)&&(Math.abs(getPos().x-pos.x)<=10)){
				curact+=1;
			}
			if(Math.abs(getPos().x-pos.x)>=Math.abs(getPos().y-pos.y-10)){
				if (getPos().x<=pos.x){
					side = 4;
				}else if(getPos().x>pos.x){		 
					side = 3;
				}
			}else if(Math.abs(getPos().y-pos.y-10)>=Math.abs(getPos().x-pos.x)){
				if (getPos().y>=pos.y){
					side = 1;
				}else if(getPos().y<pos.y){
					side = 2;
				}
		
		}

	}
		
		if((Math.abs(getPos().y-pos.y)<=10)&&(Math.abs(getPos().x-pos.x)<=10)){
			walking = false;
		}else{
			walking = true;
		}
	}
	
	public void MoveAct(Vector2 pos){
		MoveToAction ma = new MoveToAction();
		ma.setPosition(pos.x, pos.y);
		ma.setDuration(3f);
		scenarij.addAction(ma);
		actpos[allActions] = pos;
		allActions+=1;
	}
	
	float deltaTime;
	
	
	public int getSide(){
		return side;
	}

    public void setPosition(Vector2 pos){
    	this.setPosition(pos.x,  pos.y, 0);
    }
   
    
    public Vector2 getPos(){
    	Vector2 pos = new Vector2();
    	pos.set(new Vector2(body.getPosition().x*PTM, body.getPosition().y*PTM));
    	return pos;
    }
    
	public Body createB(World world, Vector2 size, Vector2 position){
		BodyDef magic = new BodyDef();
        magic.type = BodyDef.BodyType.DynamicBody;
        magic.position.set(position.x, position.y);
                                                     
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(size.x, size.y);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.friction = 100f;
        fixtureDef.density = 100f;
        fixtureDef.restitution = 0.001f;

        Body animalbody = world.createBody(magic);
        animalbody.createFixture(fixtureDef);
        shape.dispose();
        
        return animalbody;
	}

}


