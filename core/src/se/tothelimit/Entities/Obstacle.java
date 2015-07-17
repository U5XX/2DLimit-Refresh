package se.tothelimit.Entities;

import java.util.Random;

import com.badlogic.gdx.graphics.Texture;

/**
 * Entity class representing an obstacle. In this game
 * the obstacle is supposed to be a wooden crate/box.
 * @author Fredrik Andersson
 *
 */
public class Obstacle extends Entity {
	
	private Random rand;

	/**
	 * Basic entity constructor.
	 * @param x The x coordinate
	 * @param y The y coordinate
	 * @param height The height of the obstacle
	 * @param width The width of the obstacle
	 * @param texture The texture of the obstacle
	 */
	public Obstacle(float x, float y, float height, float width, Texture texture) {
		super(x, y, height, width, texture);
		
		this.rand = new Random();
	}
	
	/**
	 * Moves the obstacle 512-1023 pixels to the right.
	 * This allows the game to utilize only one obstacle
	 * instead of creating new ones.
	 */
	public void move(){
		x += rand.nextInt(512) + 512;
	}
}
