package by.coffeecat.er.body;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad.TouchpadStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;

import by.coffeecat.er.Constants;

public class Mage {

	public static Body createMage(World world){
		BodyDef magic = new BodyDef();
        magic.type = BodyDef.BodyType.DynamicBody;
        magic.position.set(1000/Constants.PTM, 480/Constants.PTM);
                                                     
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(20/2/ Constants.PTM, 20/2/ Constants.PTM);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.friction = 100f;
        fixtureDef.density = 100f;
        fixtureDef.restitution = 0.001f;

        Body mage = world.createBody(magic);
        mage.createFixture(fixtureDef);
        shape.dispose();
        
        return mage;
	}
	
	public static Touchpad touchpad;
	
	public static Touchpad createPad(Vector2 pos){
		TouchpadStyle touchpadStyle;
		Skin touchpadSkin;
		Drawable touchBackground;
		Drawable touchKnob;
		Texture blockTexture;
		Sprite blockSprite;
		float blockSpeed;
		//Create a touchpad skin	
		touchpadSkin = new Skin();
		//Set background image
		touchpadSkin.add("touchBackground", new Texture("pad/touchBackground.png"));
		//Set knob image
		touchpadSkin.add("touchKnob", new Texture("pad/touchKnob.png"));
		//Create TouchPad Style
		touchpadStyle = new TouchpadStyle();
		//Create Drawable's from TouchPad skin
		touchBackground = touchpadSkin.getDrawable("touchBackground");
		touchKnob = touchpadSkin.getDrawable("touchKnob");
		//Apply the Drawables to the TouchPad Style
		touchpadStyle.background = touchBackground;
		touchpadStyle.knob = touchKnob;
		//Create new TouchPad with the created style
		touchpad = new Touchpad(10, touchpadStyle);
		//setBounds(x,y,width,height)

        touchpad.setBounds(pos.x-375, pos.y-225, 150, 150);

		//Create a Stage and add TouchPad
		//Create block sprite
		blockTexture = new Texture(Gdx.files.internal("pad/block.png"));
		blockSprite = new Sprite(blockTexture);
		//Set position to centre of the screen
		blockSprite.setPosition(Gdx.graphics.getWidth()/2-blockSprite.getWidth()/2, Gdx.graphics.getHeight()/2-blockSprite.getHeight()/2);

		blockSpeed = 5;
		
		return touchpad;
		
		
	}
	

    static SpriteBatch                     spriteBatch;            // #6

    static float stateTime;  
    
	public static Animation MudrecAnim(Texture walkSheet,int FRAME_COLS, final int FRAME_ROWS ){
		Animation anim;
        TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth()/FRAME_COLS, walkSheet.getHeight()/FRAME_ROWS);              // #10
        TextureRegion[] walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                walkFrames[index++] = tmp[i][j];
            }
        }
        anim = new Animation(0.15f, walkFrames);      // #11
        spriteBatch = new SpriteBatch();                // #12
		
		return anim;
	}

}
