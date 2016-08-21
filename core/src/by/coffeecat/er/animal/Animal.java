package by.coffeecat.er.animal;

import by.coffeecat.er.Constants;
import by.coffeecat.er.Sigma;
import by.coffeecat.er.body.MakeAnimation;

import com.badlogic.gdx.Gdx;
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
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.DelayAction;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public class Animal extends Actor{

	Body body;
	int side = 0;
	Vector2 size;
	float speed;
	
	MakeAnimation anim[] = new MakeAnimation[5];
	SpriteBatch batch;
	float PTM = Constants.PTM;
	Sigma game;
	
	Vector2 pos;
	boolean walking = false;
	
	SequenceAction scenarij = new SequenceAction();
	MoveToAction action;
	
	Vector2[] actpos = new Vector2[100];
	int allActions = 0;
	
	public Animal(Sigma game, World world, Stage stage, Vector2 size, SpriteBatch batch, Vector2 pos, float speed) {
		super();
		this.setPosition(pos.x*PTM, pos.y*PTM);
		this.batch = batch;
		this.game = game;
		this.pos = pos;
		this.size = size;
		this.speed = speed;
		
		body = createB(world, size, pos);
		moveAbout();
/*		
		this.anim[0] = new MakeAnimation(new Texture("items/body/mbody/0.png"), 3, 1);
		this.anim[1] = new MakeAnimation(new Texture("items/body/mbody/1.png"), 3, 1);
		this.anim[3] = new MakeAnimation(new Texture("items/body/mbody/2.png"), 3, 1);
		this.anim[2]= new MakeAnimation(new Texture("items/body/mbody/3.png"), 3, 1);
		
		this.speed = 0.1f;
		*/
		/*
		this.addAction(Actions.sequence(Actions.moveBy(0, 100, 1f/speed), Actions.moveBy(0, -100, 1f/speed),
				Actions.delay(0.75f), Actions.moveBy(0, -100, 1f/speed), Actions.moveBy(0, -100, 1f/speed)));
				*/
		
		this.addAction(scenarij);
		stage.addActor(this);
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
	   	 batch.draw(currentFrame, (body.getWorldCenter().x-25/PTM)*PTM,( body.getWorldCenter().y-15/PTM)*PTM,
	   			 size.x*PTM, size.y*PTM);
		 batch.end();

		//MoveTo();
		
}
	
	MoveToAction moveaction;
	boolean aip = false;
	int odinakovo = 0;
	public int curact = 0;
	Vector2 posact;
	
	public void MoveTo(){

		if(curact+1<=allActions){
			//System.out.println(actpos[curact]);
			//Vector2 pos = new Vector2(actpos[allActions-scenarij.getActions().size]);
			posact = new Vector2(actpos[curact]);
			
			if((Math.abs(getPos().y-posact.y)<=0.1)&&(Math.abs(getPos().x-posact.x)<=0.1)){
				curact+=1;
			}else{
			
				
			}
			
			if(Math.abs(getPos().x-posact.x)>=Math.abs(getPos().y-posact.y-10)){
				if (getPos().x<=posact.x){
					side = 3;
				}else if(getPos().x>posact.x){		 
					side = 2;
				}
			}else if(Math.abs(getPos().y-posact.y-10)>=Math.abs(getPos().x-posact.x)){
				if (getPos().y>=posact.y){
					side = 0;
				}else if(getPos().y<posact.y){
					side = 1;
				}
		
		}

	}
		
		if((Math.abs(getPos().y-posact.y)<=10)&&(Math.abs(getPos().x-posact.x)<=10)){
			walking = false;
		}else{
			walking = true;
		}
	}
	
	public void moveAbout(){
		//MoveAct(new Vector2(pos.x*PTM+30, pos.y*PTM+10));
		/*
		seq(new Vector2(-30, 0));
		seq(new Vector2(0, -10));
		seq(new Vector2(+30, 0));
		seq(new Vector2(30, 10));
		seq(new Vector2(-30, 0));
		seq(new Vector2(0, -10));
		seq(new Vector2(+30, 0));
		*/
		//WaitAct(11);
	}
	
	public void MoveAct(Vector2 pos){
		MoveToAction ma = new MoveToAction();
		ma.setPosition(pos.x, pos.y);
		ma.setDuration(1/speed);
		scenarij.addAction(ma);
		actpos[allActions] = pos;
		allActions+=1;
	}
	
	public void seq(Vector2 by){
		SequenceAction sequence = new SequenceAction(Actions.moveBy(by.x, by.y, 1f/speed));
		scenarij.addAction(sequence);
		actpos[allActions] = pos;
		allActions+=1;
	}
	
	public void WaitAct(int delay){
		DelayAction ma = new DelayAction();;
		ma.setDuration(delay);
		ma.setTime(delay);
		scenarij.addAction(ma);
		actpos[allActions] = new Vector2(delay, 1000000);
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
        shape.setAsBox(size.x/2-10/PTM, size.y/2-20/PTM);

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


