package se.tothelimit.Render;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

import se.tothelimit.Entities.Bridge;
import se.tothelimit.Entities.Player;
import se.tothelimit.Logic.WinnerScreenWorld;
import se.tothelimit.Resources.TTLImg;
import se.tothelimit.ToTheLimit;

/**
 * A class that handles the rendering for the winner screen.
 *
 * @author Fredrik Andersson
 */
public class WinnerScreenRenderer {

    private WinnerScreenWorld world;
    private Player player;
    private ArrayList<Bridge> bridge;

    public static OrthographicCamera cam;
    private SpriteBatch batch;

    /**
     * Basic constructor
     *
     * @param world The associated WinnerScreenWorld-object.
     */
    public WinnerScreenRenderer(WinnerScreenWorld world) {
        this.world = world;

        cam = new OrthographicCamera();
        cam.setToOrtho(true, ToTheLimit.WIDTH, ToTheLimit.HEIGHT);
        cam.update();

        batch = new SpriteBatch();
        batch.setProjectionMatrix(cam.combined);

    }

    /**
     * Renders the screen and all that's on it.
     */
    public void render() {
        // Clears the screen
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        player = world.getPlayer();
        bridge = world.getBridge();

        // Points the camera at the right spot
        cam.zoom = 0.5f;
        cam.position.set(player.getX() + (player.getWidth() / 2),
                (ToTheLimit.HEIGHT / 2), 0);
        cam.update();
        batch.setProjectionMatrix(cam.combined);

        batch.begin();

        // Draws the background
        batch.draw(TTLImg.background, 0, 0, ToTheLimit.WIDTH,
                ToTheLimit.HEIGHT, 0, 0, TTLImg.background.getWidth(),
                TTLImg.background.getHeight(), false, true);

        // Draws the bridges
        for (Bridge b : bridge) {
            b.render(batch);
        }

        // Draws the winning-text
        batch.draw(TTLImg.msgWinner, cam.position.x - (ToTheLimit.WIDTH / 2)
                        + 3f, 40, TTLImg.msgWinner.getWidth(),
                TTLImg.msgWinner.getHeight(), 0, 0,
                TTLImg.msgWinner.getWidth(), TTLImg.msgWinner.getHeight(),
                false, true);

        // Draws the player
        player.render(batch);

        batch.end();
    }
}
