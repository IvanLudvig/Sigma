package by.coffeecat.er.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

import by.coffeecat.er.Constants;
import by.coffeecat.er.Sigma;

public abstract class Item{
	
	public Item() {
	}


	public Texture texture;
	public Vector2 pos;
	public int weight;
	Drawable dr;
	ImageButtonStyle style;
	public Image im;
	public ImageButton btn;
	public Body body;
	public int cat = 0;
	public Sigma game;
	public Vector2 size = new Vector2(20,25);
	public int cost = 1;

	public boolean dragged = false;
	
	public ImageButton getBtn(){
		im = new Image(texture);
		style = new ImageButtonStyle();
		style.imageUp = im.getDrawable();
		style.up = im.getDrawable();
		style.down = im.getDrawable();
		style.over = im.getDrawable();
		style.pressedOffsetX = 5;
		style.pressedOffsetY = 5;
		final ImageButton btn = new ImageButton(style);
		btn.addListener(new DragListener() {
		    public void drag(InputEvent event, float x, float y, int pointer) {
		    	btn.moveBy(x - btn.getWidth() / 2, y - btn.getHeight() / 2);
		    	
		    	if(new Rectangle(
		    			game.ui.inventory.equip.getX()-65,
		    			game.ui.inventory.equip.getY()-65,50,50)
		    			.overlaps(new Rectangle(btn.getX(), btn.getY(), 25, 25))){ 
		    		item().use();
		    		System.out.println("used");
		    	}
		    	
		    }
		    public void dragStop(InputEvent event, float x, float y, int pointer) {
		    	if(new Rectangle(game.ui.inventory.equip.getX()-65, 
		    			game.ui.inventory.equip.getY()-65,50,50).overlaps(new Rectangle(btn.getX(), btn.getY(), 25, 25))){ 
		    		btn.setPosition(btn.getX(), btn.getY()+50);
		    		System.out.println("keep out");
		    	}
		    	
		    	
		    	if(!(new Rectangle(game.ui.inventory.win.getX()-35, game.ui.inventory.win.getY()-35,
		    			game.ui.inventory.win.getWidth(), game.ui.inventory.win.getHeight())
		    			.overlaps(new Rectangle(btn.getX()+game.ui.inventory.win.getX()
		    					,btn.getY()+game.ui.inventory.win.getY(), 25, 25)))){
		    		delete();
		    		bodylize(game.gama.world, game.gama.mage.getPos());
		}
		    }
		});
		
		return btn;
	}
	
	public abstract void use();
	public Item item(){
		return this;
	}
	
	
	public Body getBody(){
		return body;
	}
	public void delete(){
		game.ui.inventory.delItem(this);
	}
	public void removeFromWorld(){
		game.gama.world.destroyBody(body);
	}
	
	public void bodylize(World world, Vector2 pos){
		BodyDef itdef = new BodyDef();
        itdef.type = BodyDef.BodyType.DynamicBody;
        itdef.position.set(pos.x/Constants.PTM, pos.y/Constants.PTM);
                                                     
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(size.x/2/ Constants.PTM, size.y/2/ Constants.PTM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.friction = 100f;
        fixtureDef.density = 100f;
        fixtureDef.restitution = 0.001f;

        Body item = world.createBody(itdef);
        item.setUserData(this);
        item.createFixture(fixtureDef);
        shape.dispose();
        
        body = item;
	}
	
}
