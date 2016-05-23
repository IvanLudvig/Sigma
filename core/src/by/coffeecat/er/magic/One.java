package by.coffeecat.er.magic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import by.coffeecat.er.Constants;
import by.coffeecat.er.Sigma;

public class One {
	
	public Body body;
	public ParticleEffect effect;
	SpriteBatch batch;
	
	float angle;
	float speed;
	float mana;
	float dam;
	float PTM = Constants.PTM;
	
	Sigma game;
	
	public int delete = 0;
	
	public One(float mana, float speed, float dam) {
		batch = game.gama.batch;
		effect=new ParticleEffect();
		effect.load(Gdx.files.internal("peff/plasma/1.p"), 
		          Gdx.files.internal("peff/plasma"));
		effect.start();
	}
	
	public void cast(Vector2 pos, Vector2 dest){
		if(dest.y>pos.y){
			pos.y+=10;
		}
		body = this.createMagic(game.gama.world, pos, effect);
		angle = (float) Math.atan2(dest.x, dest.y);
		//b[count].setTransform(pos.x/Constants.PTM, pos.y/Constants.PTM, angle[count]);
		//b[count].applyForceToCenter(1,1, true);
		//body.setLinearVelocity((pos.x/PTM - dest.x/PTM) * 50000, (pos.y/PTM - dest.y/PTM)* 50000);
		//b[count].applyForceToCenter(new Vector2(1,1),  false);
		//eff[count].setPosition(b[count].getPosition().x, b[count].getPosition().y);
	    Vector2 direction = new Vector2(dest.x/PTM, dest.y/PTM);
	    direction.sub(new Vector2(pos.x/PTM, pos.y/PTM));
	    direction.nor();

	    float speed = 10;
	    
	    body.setTransform(body.getPosition(), angle);
	    body.applyForceToCenter(direction, true);
	}
	
	public void render(){
		effect.setPosition(body.getPosition().x*Constants.PTM, body.getPosition().y*Constants.PTM);
	    body.setTransform(body.getPosition(), angle);
		effect.draw(batch, Gdx.graphics.getDeltaTime());
		if(game.gama.world.isLocked()==false){
			if(delete==1){
				game.gama.world.destroyBody(body);
	    		System.out.println("minus plasma");
			}
		}
		
	}
	
	public void destroy(){
		delete = 1;
	}
	
	protected Body createMagic(World world, Vector2 pos, ParticleEffect effect){
		BodyDef magic = new BodyDef();
        magic.type = BodyDef.BodyType.DynamicBody;
        magic.position.set(pos.x/Constants.PTM, pos.y/Constants.PTM);
                                                     
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(10/2/ Constants.PTM, 10/2/ Constants.PTM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.friction = 1f;
        fixtureDef.density = 1f;
        fixtureDef.restitution = 1f;

        Body spell = world.createBody(magic);
        spell.createFixture(fixtureDef);
        shape.dispose();
        spell.setUserData(this);

        return spell;
	}

}
