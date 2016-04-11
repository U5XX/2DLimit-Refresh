package se.tothelimit.Input.Touch.Screens;

import com.badlogic.gdx.Gdx;

import se.tothelimit.Input.Buffer;
import se.tothelimit.Input.Touch.InputListener;
import se.tothelimit.ToTheLimit;

/**
 * @author Fredrik Andersson <cfredrikandersson@gmail.com>
 */
public class WinnerScreenTouchInput implements InputListener {

    @Override
    public void onRelease(float x, float y) {

    }

    @Override
    public void onTouch(float x, float y) {

    }

    @Override
    public void onSwipeUp(float x, float y) {

    }

    @Override
    public void onLongTouch(float x, float y) {

    }

    @Override
    public void onSwipeDown(float x, float y) {

    }

    @Override
    public void onTap(float x, float y) {
        if(x < Gdx.graphics.getWidth() / 2){
            Buffer.write(ToTheLimit.PLAYER1_START);
        }else{
            Buffer.write(ToTheLimit.PLAYER2_START);
        }
    }

    @Override
    public void onSwipeLeft(float lastX, float lastY) {

    }

    @Override
    public void onSwipeRight(float lastX, float lastY) {

    }
}
