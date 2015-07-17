package se.tothelimit.Resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Convenience-class that handles the initialization of- and references to
 * the images and textures used in the game.
 * 
 * @author Fredrik Andersson.
 *
 */
public class TTLImg {
	
	public static Texture player1;
	public static Texture player2;
	public static Texture background;
	public static Texture bridge;
	public static Texture stars;
	public static Texture houseFront;
	public static Texture houseBack;
	public static Texture player1Idle;
	public static Texture player1Running;
	public static Texture player2Idle;
	public static Texture player2Running;
	public static Texture gameTitle;
	public static Texture player1Start;
	public static Texture player2Start;
	public static Texture player1Ready;
	public static Texture player2Ready;
	public static Texture player1Win;
	public static Texture player2Win;
	public static Texture msg1;
	public static Texture msg2;
	public static Texture msg3;
	public static Texture msgGo;
	public static Texture msgWinner;
	public static Texture box;

	/**
	 * Initializes the Texture-variables.
	 * 
	 * IMPORTANT:
	 * This method needs to be called before any Textures can be accessed.
	 */
	public static void init(){
		player1 = new Texture(Gdx.files.internal("data/img/player.png"));
		player2 = new Texture(Gdx.files.internal("data/img/player2.png"));
		background = new Texture(Gdx.files.internal("data/img/2dlbackground.png"));
		bridge = new Texture(Gdx.files.internal("data/img/bridge.png"));
		stars = new Texture(Gdx.files.internal("data/img/starsheet.png"));
		houseFront = new Texture(Gdx.files.internal("data/img/houseoverlayfront.png"));
		houseBack = new Texture(Gdx.files.internal("data/img/houseoverlayback.png"));
		player1Idle = new Texture(Gdx.files.internal("data/img/player1idle.png"));
		player1Running = new Texture(Gdx.files.internal("data/img/player1running.png"));
		player2Idle = new Texture(Gdx.files.internal("data/img/player2idle.png"));
		player2Running = new Texture(Gdx.files.internal("data/img/player2running.png"));
		gameTitle = new Texture(Gdx.files.internal("data/img/2dltitle.png"));
		player1Start = new Texture(Gdx.files.internal("data/img/p1start.png"));
		player2Start = new Texture(Gdx.files.internal("data/img/p2start.png"));
		player1Ready = new Texture(Gdx.files.internal("data/img/p1ready.png"));
		player2Ready = new Texture(Gdx.files.internal("data/img/p2ready.png"));
		player1Win = new Texture(Gdx.files.internal("data/img/player1win.png"));
		player2Win = new Texture(Gdx.files.internal("data/img/player2win.png"));
		msg1 = new Texture(Gdx.files.internal("data/img/1.png"));
		msg2 = new Texture(Gdx.files.internal("data/img/2.png"));
		msg3 = new Texture(Gdx.files.internal("data/img/3.png"));
		msgGo = new Texture(Gdx.files.internal("data/img/go.png"));
		msgWinner = new Texture(Gdx.files.internal("data/img/winnermsg.png"));
		box = new Texture(Gdx.files.internal("data/img/crate.png"));
	}
	
	/**
	 * Disposes of the Textures.
	 */
	public void dispose(){
		player1.dispose();
		player2.dispose();
		background.dispose();
		bridge.dispose();
		stars.dispose();
		houseFront.dispose();
		houseBack.dispose();
		player1Idle.dispose();
		player1Running.dispose();
		player2Idle.dispose();
		player2Running.dispose();
		gameTitle.dispose();
		player1Start.dispose();
		player2Start.dispose();
		player1Ready.dispose();
		player2Ready.dispose();
		player1Win.dispose();
		player2Win.dispose();
		msgWinner.dispose();
		msg1.dispose();
		msg2.dispose();
		msg3.dispose();
		msgGo.dispose();
		box.dispose();
	}
}
