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
	boolean hasMoved;
	
	public Piece(String value) {
		this.value = value;
		hasMoved = false;
	}
	
	public String getvalue() {
		return this.value;
	}
	
	public boolean hasMoved() {
		return this.hasMoved;
	}
	
	public void sethasMoved(boolean hasMoved) {
		this.hasMoved = hasMoved;
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
	
	public boolean isCheck(String filerank) {
		return false;
	}

}
