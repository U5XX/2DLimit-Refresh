package se.tothelimit.Input.Touch;

/**
 * @author Fredrik Andersson <cfredrikandersson@gmail.com>
 */

import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;

import java.util.logging.Logger;

public class InputHandler extends GestureDetector {
  private static final Logger lgr = Logger.getLogger(InputHandler.class.getName());

  private static final int MAX_TOUCHES = 20; // The maximum amount that LibGDX can handle.
  private static final int SWIPE_THRESHOLD = 50; // Controls the "sensitivity" of the swipes.

  private InputListener inputListener;
  private static Vector2[] activeTouches;

  public InputHandler(InputListener inputListener) {
    super(new DirectionGestureListener(inputListener));
    this.inputListener = inputListener;
    activeTouches = new Vector2[MAX_TOUCHES];
  }

  private static Vector2 getTouch(int pointer){
    if(null == activeTouches[pointer]){
      activeTouches[pointer] = new Vector2();
    }
    return activeTouches[pointer];
  }

  private static void removeTouch(int pointer){
    activeTouches[pointer] = null;
  }

  @Override
  public boolean touchUp(float x, float y, int pointer, int button) {
    removeTouch(pointer);
    inputListener.onRelease(x, y);
    return super.touchUp(x, y, pointer, button);
  }


  private static class DirectionGestureListener extends GestureAdapter {
    private InputListener inputListener;

    public DirectionGestureListener(InputListener inputListener) {
      this.inputListener = inputListener;
    }

    @Override
    public boolean touchDown(float x, float y, int pointer, int button) {
      Vector2 newTouch = getTouch(pointer);
      newTouch.set(x, y);
      inputListener.onTouch(x, y);
      return super.touchDown(x, y, pointer, button);
    }

    @Override
    public boolean longPress(float x, float y) {
      inputListener.onLongTouch(x, y);
      return super.longPress(x, y);
    }

    @Override
    public boolean tap(float x, float y, int count, int button) {
      inputListener.onTap(x, y);
      return super.tap(x, y, count, button);
    }
  }

  @Override
  public boolean touchDragged(float x, float y, int pointer) {
    Vector2 oldTouch = getTouch(pointer);
    Vector2 newTouch = new Vector2(x, y);
    Vector2 delta = newTouch.cpy().sub(oldTouch);

    if(delta.y > SWIPE_THRESHOLD){
      inputListener.onSwipeDown(x, y);
    }else if(delta.y < -SWIPE_THRESHOLD){
      inputListener.onSwipeUp(x, y);
    }

    if(delta.x > SWIPE_THRESHOLD){
      inputListener.onSwipeRight(x, y);
    }else if(delta.x < -SWIPE_THRESHOLD){
      inputListener.onSwipeLeft(x, y);
    }

    oldTouch.set(newTouch);
    return super.touchDragged(x, y, pointer);
  }
}
