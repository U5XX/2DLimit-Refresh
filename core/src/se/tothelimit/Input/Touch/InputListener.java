package se.tothelimit.Input.Touch;

/**
 * @author Fredrik Andersson <cfredrikandersson@gmail.com>
 */
public interface InputListener {
    void onRelease(float x, float y);

    void onTouch(float x, float y);

    void onLongTouch(float x, float y);

    void onSwipeUp(float x, float y);

    void onSwipeDown(float x, float y);

    void onTap(float x, float y);

    void onSwipeLeft(float lastX, float lastY);

    void onSwipeRight(float lastX, float lastY);
}
