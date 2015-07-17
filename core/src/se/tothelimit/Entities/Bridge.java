package se.tothelimit.Entities;

import com.badlogic.gdx.graphics.Texture;

/**
 * Class representing a bridge. 
 * @author Simon Cedergren <simon@tuxflux.se> and Fredrik Andersson <cfredrikandersson@hotmail.com>
 */
public class Bridge extends MovingEntity{

	public Bridge(float x, float y, float width, float height, Texture texture) {
		super(x, y, height, width, texture);
	}
}
