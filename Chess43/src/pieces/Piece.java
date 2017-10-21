/**
 * 
 */
package pieces;

/**
 * @author Deep Kotadia
 * @author Chinmoyi Bhushan
 *
 */
public interface Piece {
	
	public abstract void move(String oldPos, String newPos);
	public abstract boolean isPathEmpty(String oldPos, String newPos);

}
