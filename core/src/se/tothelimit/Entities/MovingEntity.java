package se.tothelimit.Entities;

import com.badlogic.gdx.graphics.Texture;

/**
 * A class representing a moving entity.
 * @author Simon Cedergren <simon@tuxflux.se> and Fredrik Andersson <cfredrikandersson@hotmail.com> *
 */
public class MovingEntity extends Entity {

	protected float dx;
	protected float dy;

	public MovingEntity(float x, float y, float height, float width,
			Texture texture) {
		super(x, y, height, width, texture);

		dx = 0;
		dy = 0;
	}

	/**
	 * Updates the moving entitys' X value.
	 */
	public void update() {
		x += dx;
	}

	/**
	 * Returns the horizontal "speed" of the moving entity.
	 * @return the horizontal "speed" of the moving entity.
	 */
	public float getDx() {
		return dx;
	}

	/**
	 * Returns the vertical "speed" of the moving entity.
	 * @return the vertical "speed" of the moving entity.
	 */
	public float getDy() {
		return dy;
	}

	/**
	 * Sets the horizontal "speed" of the moving entity.
	 * @param dx - the horizontal "speed" of the moving entity.
	 */
	public void setDx(float dx) {
		this.dx = dx;
	}

}
