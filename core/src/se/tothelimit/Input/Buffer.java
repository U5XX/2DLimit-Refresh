package se.tothelimit.Input;

import java.util.ArrayList;

/**
 * A buffer that buffers the bluetooth input.
 * 
 * @author Simon Cedergren <simon@tuxflux.se>
 */
public class Buffer {

	private static ArrayList<Integer> buffer;
	private static ArrayList<Integer> returnBuffer;

	/**
	 * Call this method in onCreate for the Activity. This method initiates the
	 * arraylists.
	 */
	public static void init() {
		buffer = new ArrayList<Integer>(5);
		returnBuffer = new ArrayList<Integer>(5);
	}

	/**
	 * Returns the current buffer and clears it.
	 * 
	 * @return the current buffer.
	 */
	public static ArrayList<Integer> read() {
		returnBuffer.clear();
		for (Integer integer : buffer)
			returnBuffer.add(integer);
		buffer.clear();
		return returnBuffer;
	}

	/**
	 * Writes an int to the buffer.
	 * 
	 * @param value
	 *            - the pin that Arre sends. For now it's 8 or 9.
	 */
	public static void write(int value) {
		buffer.add(value);
	}

	/**
	 * Clears the buffer.
	 */
	public static void clear() {
		buffer.clear();
	}
}
