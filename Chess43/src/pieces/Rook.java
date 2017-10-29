/**
 * 
 */
package pieces;

/**
 * @author Deep Kotadia
 * @author Chinmoyi Bhushan
 *
 */

import chess.Chess;

public class Rook extends Piece {

	public Rook(String value) {
		super(value);
	}


	//@Override
	public boolean isMoveValid(String oldPos, String newPos) {
		
		/*to check if newPos is a box in the bounds of the board*/
		if(Chess.board.containsKey(newPos) == false) {
			return false;
		}
		
		String piece_oldPos = Chess.board.get(oldPos).getvalue();
		String piece_newPos = Chess.board.get(newPos).getvalue();
		
		/*to check if valid move for a Rook*/
		if((oldPos.charAt(0) == newPos.charAt(0) || oldPos.charAt(1) == newPos.charAt(1)) && !(oldPos.equals(newPos))) {
			
			/*to check if newPos is empty*/
			if(Chess.board.get(newPos).getvalue().equals("  ") || Chess.board.get(newPos).getvalue().equals("##")) {
				if(isPathEmpty(oldPos,newPos)) {
					//valid move, can move
					return true;
				}
				else {
					//path not empty, rook can't jump
					//need to prompt user to try a different valid move
					return false;
				}
			}
			
			/*color case when the newPos is not empty (some piece exists at newPos)*/
			else {
				
				if(piece_oldPos.charAt(0) == piece_newPos.charAt(0)) {
					//piece color is the same	
					return false;
				}
				else {
					if(isPathEmpty(oldPos,newPos)) {     //there is a piece at the new position, we need to move there and kill that piece.
						return true;
					}
					else {
						//path is not empty
						return false;
					}
				}
				
			}
		}
		else {   //illegal move for Rook
			return false;
		}	
		
		
	}
	
	
	public void move(String oldPos, String newPos, char promopiece) {
		Piece piece_oldPos = Chess.board.get(oldPos);
		
		//move piece to newPos
		Chess.board.put(newPos, piece_oldPos);
		
		//make oldPos an empty box
		if(Chess.isBlackBox(oldPos.charAt(0), oldPos.charAt(1)-'0')) {
			Chess.board.put(oldPos, new EmptySquare("##"));
		}
		else {
			Chess.board.put(oldPos, new EmptySquare("  "));
		}
	}
	

	//@Override
	public boolean isPathEmpty(String oldPos, String newPos) {
		if (oldPos.charAt(0) == newPos.charAt(0)) {
			int i;
			int numoldPos = oldPos.charAt(1) - '0';
			int numnewPos = newPos.charAt(1) - '0';
			
			if(numoldPos < numnewPos) { //going forward for white, backward for black
				for (i = numoldPos+1 ; i < numnewPos ; i++) {
					if(!(isBoxEmpty(oldPos.charAt(0), i))) {
						return false;
					}
				}
			}
			else { //going backward for white, forward for black
				for (i = numnewPos+1 ; i < numoldPos ; i++) {
					if(!(isBoxEmpty(oldPos.charAt(0), i))) {
						return false;
					}
				}
			}
		}
		else if(oldPos.charAt(1) == newPos.charAt(1)) {
			char letter;
			char letteroldPos = oldPos.charAt(0);
			char letternewPos = newPos.charAt(0);
			
			if(letteroldPos < letternewPos) { //going right for white, left for black	
				for (letter = (char)(letteroldPos+1) ; letter < letternewPos ; letter++) {
					if(!(isBoxEmpty(letter, oldPos.charAt(1)-'0'))) {
						return false;
					}
				}
			}
			else { //going left for white, right for black
				for (letter = (char)(letternewPos+1) ; letter < letteroldPos ; letter++) {
					if(!(isBoxEmpty(letter, oldPos.charAt(1)-'0'))) {
						return false;
					}
				}
			}
		}
		//at this point, the path is smooth, clear, empty, and good to go!!!
		return true;
	}
	
	private static boolean isBoxEmpty(char alpha, int num) {
		String filerank = alpha + "" + num;
		
		if(Chess.board.get(filerank).getvalue().equals("##") || Chess.board.get(filerank).getvalue().equals("  ")) { //box is empty
			return true;
		}
		
		return false;
	}

}
