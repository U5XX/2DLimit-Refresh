package se.tothelimit.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * A class representing an entity.
 * @author Simon Cedergren <simon@tuxflux.se> and Fredrik Andersson <cfredrikandersson@hotmail.com> *
 */
public class Entity {
	protected float x;
	protected float y;
	protected float height;
	protected float width;
	protected Rectangle bounds;
	private Texture texture;
	
	public Entity(float x, float y, float height, float width, Texture texture){ 
		this.x = x;
		this.y = y;
		this.height = height;
		this.width = width;
		this.texture = texture;
		bounds = new Rectangle(x, y, width, height);
	}
	
	/**
	 * Returns the entitys' X coordinate.
	 * @return the entitys' X coordinate.
	 */
	public float getX(){
		return x;
	}
	
	/**
	 * Returns the entitys' Y coordinate.
	 * @return the entitys' Y coordinate.
	 */
	public float getY(){
		return y;
	}
	
	/**
	 * Sets the entitys' X coordinate.
	 * @param x - the X coordinate
	 */
	public void setX(float x){
		this.x = x;
	}
	
	/**
	 * Sets the entitys' Y coordinate.
	 * @param y - the Y coordinate
	 */
	public void setY(float y){
		this.y = y;
	}

	/**
	 * Returns the width of the entity.
	 * @return the width of the entity.
	 */
	public float getWidth(){
		return width;
	}

	/**
	 * Returns the height of the entity.
	 * @return the height of the entity.
	 */
	public float getHeight(){
		return height;
	}
	
	/**
	 * Renders the entity.
	 * @param batch - the batch that draws the sprite.
	 */
	public void render(SpriteBatch batch) {
		batch.draw(texture, getX(), y, width, height, 0, 0, texture.getWidth(), texture.getHeight(), false, true);
	}
	
}
