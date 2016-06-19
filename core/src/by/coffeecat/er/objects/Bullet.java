package by.coffeecat.er.objects;

import com.badlogic.gdx.graphics.Texture;
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
	
	public Body body;
	float PTM = Constants.PTM;
	

	public Bullet(Sigma game) {
		this.game = game;
		body = createBull(game.gama.world, game.gama.mage.getPos());
		batch = game.gama.batch;
		txt = new Texture("items/weapons/bullet.png");
	}
	
	public void render(){
		batch.draw(txt, body.getPosition().x*PTM, body.getPosition().y*PTM);
	}
	
	protected Body createBull(World world, Vector2 pos){
		BodyDef magic = new BodyDef();
        magic.type = BodyDef.BodyType.DynamicBody;
        magic.position.set(pos.x/Constants.PTM, pos.y/Constants.PTM);
                                                     
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(10/2/ Constants.PTM, 5/2/ Constants.PTM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.friction = 1f;
        fixtureDef.density = 1f;
        fixtureDef.restitution = 1f;

        Body bull = world.createBody(magic);
        bull.createFixture(fixtureDef);
        shape.dispose();
        bull.setUserData(this);

        return bull;
	}

}
