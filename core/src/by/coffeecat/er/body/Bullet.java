package by.coffeecat.er.body;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import by.coffeecat.er.Constants;
import by.coffeecat.er.Sigma;

public class Bullet {
	
	public Body bullet;
	Sigma game;
	SpriteBatch batch;
	Texture txt;
	Sprite sprite;
	
	float PTM = Constants.PTM;
	
	Vector2 pos = new Vector2();
	Vector2 vel = new Vector2();
	int f = 0;
	
	public int life = 1;
	
	public Bullet(Sigma game) {
		this.game = game;
		pos = game.gama.mage.getPos();
		vel = new Vector2(game.gama.mage.lookdir.x*3f, game.gama.mage.lookdir.y*3f);
		if(vel.x>0.75){
			pos.x += 20;
		}
		if(vel.x<-0.75){
			pos.x -= 15;
		}
		if(vel.y>0.75){
			pos.y += 25;
		}
		if(vel.y<-0.75){
			pos.y -= 15;
		}
		bullet = createBull(game.gama.world, pos);
		batch = game.gama.batch;
		txt = new Texture("items/weapons/bullet.png");
		sprite = new Sprite();
		sprite.setTexture(txt);
		sprite.setSize(22, 9);
		bullet.setLinearVelocity(vel);
	}
	
	public void render(){
		if(life==1){
			sprite.setPosition(bullet.getPosition().x*PTM, bullet.getPosition().y*PTM);
			//sprite.rotate(bullet.getAngle());
			batch.begin();
			batch.draw(txt, bullet.getPosition().x*PTM, bullet.getPosition().y*PTM);
			//sprite.draw(batch);
			batch.end();
		}
	}
	
	public void destroy(){
		game.gama.delete(bullet);
		life = 0;
	}
	
	protected Body createBull(World world, Vector2 pos){
		BodyDef magic = new BodyDef();
        magic.type = BodyDef.BodyType.DynamicBody;
        magic.position.set(pos.x/Constants.PTM, pos.y/Constants.PTM);
                                                     
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(5/2/ Constants.PTM, 10/2/ Constants.PTM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.friction = 1f;
        fixtureDef.density = 1f;
        fixtureDef.restitution = 1f;

        Body bull = world.createBody(magic);
        bull.createFixture(fixtureDef);
        bull.setUserData(this);
        shape.dispose();

        return bull;
	}

}
