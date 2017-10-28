/**
 * 
 */
package pieces;

/**
 * @author Deep Kotadia
 * @author Chinmoyi Bhushan
 *
 */
public abstract class Piece {
	
	public static void move(String oldPos, String newPos) {
		return;
	}
	
	public static boolean isMoveValid(String oldPos, String newPos) {
		return true;
	}
	
	public static boolean isPathEmpty(String oldPos, String newPos) {
		return true;
	}

}
