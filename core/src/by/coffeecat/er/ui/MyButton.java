package by.coffeecat.er.ui;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

public class MyButton extends ImageButton
{   
    public MyButton(Texture up, Texture down, Texture background)
    {
        super(new SpriteDrawable(new Sprite(up)),
              new SpriteDrawable(new Sprite(down)));

        this.setBackground(new SpriteDrawable(new Sprite(background)));
    }
}
