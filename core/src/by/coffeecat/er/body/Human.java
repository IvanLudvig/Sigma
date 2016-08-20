package by.coffeecat.er.body;

import by.coffeecat.er.Constants;
import by.coffeecat.er.Sigma;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
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
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;

public class Human extends Actor {
	
	MakeAnimation[] anim = new MakeAnimation[5];
	
	static Body body;
	
	float PTM = Constants.PTM;
	
	private static int side = 1;
	
	private boolean walking = false;
	
	Stage stage;
	World world;
	
	SequenceAction scenarij = new SequenceAction();
	MoveToAction action;
	
	Vector2[] actpos = new Vector2[100];
	int allActions = 0;
	
	Sigma end;
	Preferences prefs = end.prefs;
	
	public Human(World world, Stage stage){
		//this.anim = anim;
		this.stage = stage;
		this.world = world;
	}
	
	float delta = Gdx.graphics.getDeltaTime();
	
	public void drawit(SpriteBatch batch, Stage stage){
		body.setTransform(this.getX()/PTM,  this.getY()/PTM, 0);
		delta = Gdx.graphics.getDeltaTime();
		
		stage.act(delta);
		stage.draw();

		batch.begin();

  	     TextureRegion currentFrame = anim[side].getAnim().getKeyFrame(anim[side].stateTime, true);  
   		 if(walking){
   			 anim[side].stateTime += Gdx.graphics.getDeltaTime(); 
   		 }
       	
   		batch.draw(currentFrame, (body.getWorldCenter().x-20/PTM)*PTM,( body.getWorldCenter().y-30/PTM)*PTM, 35,60);
		batch.end();

		MoveTo();
		
}
	
	MoveToAction moveaction;
	boolean aip = false;
	int odinakovo = 0;
	public int curact = 0;
	Vector2 pos = new Vector2();
	
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
	
	
	public static int getSide(){
		return side;
	}

	public Body createHuman(World world){
		BodyDef magic = new BodyDef();
        magic.type = BodyDef.BodyType.DynamicBody;
        magic.position.set(1200/Constants.PTM, 500/Constants.PTM);
                                                     
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(35/2/ Constants.PTM, 60/2/ Constants.PTM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.friction = 1f;
        fixtureDef.density = 0.01f;
        fixtureDef.restitution = 10f;

        Body ryorik = world.createBody(magic);
        ryorik.createFixture(fixtureDef);
        shape.dispose();
        
        this.setWidth(35);
        this.setHeight(60);
        this.setBounds(0, 0, this.getWidth(), this.getHeight());
        
        return ryorik;
	}
	
    public void setPosition(Vector2 pos){
    	this.setPosition(pos.x,  pos.y, 0);
    }
   
    
    public Vector2 getPos(){
    	Vector2 pos = new Vector2();
    	pos.set(new Vector2(body.getPosition().x*PTM, body.getPosition().y*PTM));
    	return pos;
    }

}

