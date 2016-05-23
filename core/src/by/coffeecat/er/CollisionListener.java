package by.coffeecat.er;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;

import by.coffeecat.er.magic.One;
import by.coffeecat.er.objects.Item;
import by.coffeecat.screens.SigmaGame;

/**
 * Created by Solange Tereshko on 03/01/2016.
 */
public class CollisionListener implements ContactListener {
	
	Sigma game = SigmaGame.game;
	Texture take = new Texture("ui/take.png");
	float PTM = Constants.PTM;
	SpriteBatch batch = game.batch;

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }


    @Override
    public void beginContact(Contact contact) {
    	/*
		if(Map1.door!=null){
    		for(Body dver : Map1.door){
    			if((contact.getFixtureA().getBody() == dver)&&(contact.getFixtureB().getBody()==game.mage.mage)){
    				game.inputMultiplexer.clear();
    				game.inputMultiplexer.addProcessor(game.ui.stage);
    				game.gama.changeMap(game.maps[2]);
    			}
    		}
		}
    		if(House1.door!=null){
        		for(Body ladder : House1.door){
        			if((contact.getFixtureA().getBody() == ladder)&&(contact.getFixtureB().getBody()==MageClass.mage)){
           				System.out.println("ololol");
        				game.inputMultiplexer.clear();
        				game.inputMultiplexer.addProcessor(game.ui.stage);
        				game.gama.changeMap(game.maps[2]);
        			}
        		}
    		}
    		*/
    	if(contact.getFixtureB().getBody().getUserData()!=null){
    		if((contact.getFixtureB().getBody().getUserData().getClass().getName().contains("by.coffeecat.er.objects")&&
    				(contact.getFixtureA().getBody()==game.gama.mage.mage))){
				Item item = (Item)contact.getFixtureB().getBody().getUserData();
				//game.ui.inventory.addItem(item);
    		}
    		if(contact.getFixtureB().getBody().getUserData().getClass().getName().contains("by.coffeecat.er.magic")){
				One one = (One)contact.getFixtureB().getBody().getUserData();
				one.destroy();
				//game.ui.inventory.addItem(item);
    		}
    	}
    		/*
    		if(House2.door!=null){
        		for(Body ladder : House2.door){
        			if((contact.getFixtureA().getBody() == ladder)&&(contact.getFixtureB().getBody()==game.mage.mage)){
        				game.inputMultiplexer.clear();
        				game.inputMultiplexer.addProcessor(game.ui.stage);
        				game.gama.changeMap(game.maps[1]);
        			}
        		}
    		}
    		*/
    }

};