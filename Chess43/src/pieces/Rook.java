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

public class Rook implements Piece {

	@Override
	public void move(String oldPos, String newPos) {
		String piece_oldPos=Chess.board.get(oldPos);
		String piece_newPos=Chess.board.get(newPos);
		
		/*to check if valid move for a Rook*/
		if((oldPos.charAt(0)==newPos.charAt(0) || oldPos.charAt(1)==newPos.charAt(1)) && !(oldPos.equals(newPos))) {
			
			/*to check if newPos is empty*/
			if(Chess.board.get(newPos).equals("  ") || Chess.board.get(newPos).equals("##")) {
				if(isPathEmpty(oldPos,newPos)) {
					Chess.board.put(newPos, piece_oldPos);
					
					if(Chess.isBlackBox(oldPos.charAt(0), oldPos.charAt(1))) {
						Chess.board.put(oldPos, "##");
					}
					else {
						Chess.board.put(oldPos, "  ");
					}
				}
				else {
					System.out.println("Illegal move, try again");
					//need to prompt user to dry a different valid move. 
				}
				
			}
			
			/*color case when the newPos is not empty*/
			else {
				if(piece_oldPos.charAt(0)==piece_newPos.charAt(0)) {
					System.out.println("Illegal move, try again");  //piece color is the same	
				}
				else {
					if(isPathEmpty(oldPos,newPos)) {     //there is a piece at the new position, we need to move there and kill that piece.
						Chess.board.put(newPos, piece_oldPos);
						
						if(Chess.isBlackBox(oldPos.charAt(0), oldPos.charAt(1))) {
							Chess.board.put(oldPos, "##");
						}
						else {
							Chess.board.put(oldPos, "  ");
						}
						
						//kill process TODO; check the state of the board here. 	
					}
					else {
						//path is not empty
						System.out.println("Illegal move, try again");
						
					}
				}
				
			}
		}
		else {   //illegal move for Rook
			System.out.println("Illegal move, try again");
		}	
		
		
	}
	

	@Override
	public boolean isPathEmpty(String oldPos, String newPos) {
		if (oldPos.charAt(0)==newPos.charAt(0)) {
			int i=oldPos.charAt(1) - '0';
			
			for (;i<(newPos.charAt(1)-'0');i++) {
				if(!(isBoxEmpty(oldPos.charAt(0), i))) {
					return false;
				}
			}
		}
		else if(oldPos.charAt(1)==newPos.charAt(1)) {
			char letter = oldPos.charAt(0);
			
			for (;letter<(newPos.charAt(0));letter++) {
				if(!(isBoxEmpty(letter, oldPos.charAt(1)-'0'))) {
					return false;
				}
			}
		}
		//at this point, the path is smooth, clear, empty, and good to go!!!
		return true;
	}
	
	private boolean isBoxEmpty(char alpha, int num) {
		String filerank = alpha + "" + num;
		
		if(Chess.board.get(filerank).equals("##") || Chess.board.get(filerank).equals("  ")) { //box is empty
			return true;
		}
		
		return false;
	}

}
