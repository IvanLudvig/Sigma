package by.coffeecat.light;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Lightning {
	boolean isComplete();
	void update();
	void draw(SpriteBatch batch);
}
