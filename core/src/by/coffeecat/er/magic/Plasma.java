package by.coffeecat.er.magic;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.DragListener;

import by.coffeecat.er.Constants;
import by.coffeecat.er.Sigma;
import by.coffeecat.er.ui.Ui;

public class Plasma extends Magic{

	World world;
	TextureAtlas atlas;

	Skin skin;
	Sigma end;
	
	Ui ui;
	public static ImageButtonStyle plasma;
	
	SpriteBatch batch;
	float PTM = Constants.PTM;
	
	
	public Plasma(World world, final Sigma game, SpriteBatch batch) {
		this.world = world;
		end = game;
		dam = 10;
		speed = 1f;
		this.mana = 50;
		ui = end.ui;
		this.batch = batch;

		atlas = new TextureAtlas("peff/plasma/PlasmaAtlas.pack");
		
		skin = new Skin(atlas);
		
		plasma= new ImageButtonStyle();  //Instaciate
		plasma.up = skin.getDrawable("plasma");  //Set image for not pressed button 
		plasma.down = skin.getDrawable("plasmap");  //Set image for pressed
		plasma.over = skin.getDrawable("plamah");  //set image for mouse over
		plasma.pressedOffsetX = 1; 
		plasma.pressedOffsetY = -1;
		
		btn = new ImageButton(plasma);
		btn.setSize(50, 50);
		
        
		btn.addListener(new DragListener() {
		    public void drag(InputEvent event, float x, float y, int pointer) {
		    	btn.moveBy(x - btn.getWidth() / 2, y - btn.getHeight() / 2);
		    }
		    
		    public void dragStop(InputEvent event, float x, float y, int pointer) {
		    	newOne(count);
		    	
		    	Vector2 dest = new Vector2(btn.getX()+game.gama.camera.position.x+200,btn.getY()+game.gama.camera.position.y-210);
		        game.gama.camera.unproject(new Vector3(dest.x,dest.y,0));
		        
		        System.out.println(dest);
		    	one[count].cast(end.mage.getPos(), dest);
		    	btn.addAction(Actions.moveTo(0, 0));
				end.gama.mage.minusMana(mana);
				count++;
		    }

		});

	}
	
	int count = 0;
	
	/*
	public void cast(Vector2 pos, Vector2 dest){
		b[count] = this.createMagic(world, pos, eff[count]);
		angle[count] = (float) Math.atan2(-dest.x, dest.y);
		//b[count].setTransform(pos.x/Constants.PTM, pos.y/Constants.PTM, angle[count]);
		//b[count].applyForceToCenter(1,1, true);
		//body.setLinearVelocity((pos.x/PTM - dest.x/PTM) * 50000, (pos.y/PTM - dest.y/PTM)* 50000);
		//b[count].applyForceToCenter(new Vector2(1,1),  false);
		//eff[count].setPosition(b[count].getPosition().x, b[count].getPosition().y);
	    Vector2 direction = new Vector2(-dest.x/PTM, dest.y/PTM);
	    direction.sub(b[count].getPosition());
	    direction.nor();

	    float speed = 10;
	    b[count].setLinearVelocity(direction.scl(speed));
		count++;
	}
	*/
	
	public void render(){
			for(int g=0;g<count;g++){
				if(one[g]!=null){
					if(one[g].life==1){
						one[g].render();
					}
				}
			}

	}

	
	

}
