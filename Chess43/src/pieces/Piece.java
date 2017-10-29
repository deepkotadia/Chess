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
	
	String value;
	
	public Piece(String value) {
		this.value = value;
	}
	
	public String getvalue() {
		return this.value;
	}
	
	public void move(String oldPos, String newPos, char promopiece) {
		return;
	}
	
	public boolean isMoveValid(String oldPos, String newPos) {
		return true;
	}
	
	public boolean isPathEmpty(String oldPos, String newPos) {
		return true;
	}

}
