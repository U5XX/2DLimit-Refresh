package se.tothelimit.Resources;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 * Convenience-class that handles the initialization of- and references to
 * the sounds and music used in the game.
 * 
 * @author Fredrik Andersson.
 *
 */
public class TTLSnd {
	
	public static Sound pressStart;
	public static Sound jump;
	public static Sound boxHit;
	public static Music bgm;
	
	/**
	 * Initializes the Sound-variables.
	 * 
	 * IMPORTANT:
	 * This method needs to be called before any Sounds can be accessed.
	 */
	public static void init(){
		pressStart = Gdx.audio.newSound(Gdx.files.internal("data/snd/start.ogg"));
		jump = Gdx.audio.newSound(Gdx.files.internal("data/snd/jump.ogg"));
		boxHit = Gdx.audio.newSound(Gdx.files.internal("data/snd/boxhit.ogg"));
		bgm = Gdx.audio.newMusic(Gdx.files.internal("data/snd/bgm.ogg"));
		
	}
	
	/**
	 * Disposes of the Sounds.
	 */
	public void dispose(){
		pressStart.dispose();
		bgm.dispose();
		jump.dispose();
		boxHit.dispose();
	}
}
