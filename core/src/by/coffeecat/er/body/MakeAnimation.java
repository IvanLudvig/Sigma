package by.coffeecat.er.body;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class MakeAnimation {

    static float stateTime;  
	Animation anim;
    
	public MakeAnimation(Texture walkSheet,int FRAME_COLS, final int FRAME_ROWS ){
        TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth()/FRAME_COLS, walkSheet.getHeight()/FRAME_ROWS);              // #10
        TextureRegion[] walkFrames = new TextureRegion[FRAME_COLS * FRAME_ROWS];
        int index = 0;
        for (int i = 0; i < FRAME_ROWS; i++) {
            for (int j = 0; j < FRAME_COLS; j++) {
                walkFrames[index++] = tmp[i][j];
            }
        }
        anim = new Animation(0.25f, walkFrames);      // #11    
	}
	
	public Animation getAnim(){
		return anim;
	}
}
