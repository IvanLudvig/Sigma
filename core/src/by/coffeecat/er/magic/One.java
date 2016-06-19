package by.coffeecat.er.magic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
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
	public ParticleEffect smoke;
	SpriteBatch batch;
	
	float angle;
	float speed;
	float mana;
	float dam;
	float PTM = Constants.PTM;
	float timer = 0f;
	Sigma game;
	FrameBuffer particleBuffer;
	Music music;
	
	int life = 1;
	public int count;
	
	public One(float mana, float speed, float dam, int count) {
		this.count = count;
		batch = game.gama.batch;
		effect=new ParticleEffect();
		smoke=new ParticleEffect();
		effect.load(Gdx.files.internal("peff/plasma/1.p"), 
		          Gdx.files.internal("peff/plasma"));
		smoke.load(Gdx.files.internal("peff/plasma/dym.p"), 
		          Gdx.files.internal("peff/plasma"));
		effect.start();
		smoke.start();
		particleBuffer = new FrameBuffer(Format.RGBA8888,
				Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
	}
	
	Vector2 pos = new Vector2();
	
	public void cast(Vector2 pos, Vector2 dest){
		if(game.gama.mage.mana>=mana){
			if(dest.y>pos.y){
				pos.y+=10;
			}
			this.pos = pos;
			music = Gdx.audio.newMusic(Gdx.files.internal("peff/plasma/sound.mp3"));
			music.play();
			music.setLooping(false);
			music.setVolume(0.6f);
			
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
		}else{
			
		}
	}
	
	public void render(){
		effect.setPosition(body.getPosition().x*Constants.PTM, body.getPosition().y*Constants.PTM);
		smoke.setPosition(pos.x, pos.y);
	    body.setTransform(body.getPosition(), angle);
	    batch.setBlendFunction(GL20.GL_ONE, GL20.GL_ONE_MINUS_SRC_ALPHA);
		effect.draw(batch, Gdx.graphics.getDeltaTime());
		smoke.draw(batch, Gdx.graphics.getDeltaTime());
		smoke.allowCompletion();
		timer+=Gdx.graphics.getDeltaTime();
	}
	
	public void destroy(){
		if(timer>1f){
			effect.allowCompletion();
			smoke.allowCompletion();
			game.gama.delete(body);
			effect.dispose();
			smoke.dispose();
			life = 0;
		}
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
