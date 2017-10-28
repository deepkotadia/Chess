/**
 * 
 */
package pieces;

import chess.Chess;

/**
 * @author Deep Kotadia
 * @author Chinmoyi Bhushan
 *
 */
public class King extends Piece{

	public static boolean isMoveValid(String oldPos, String newPos) {
		String piece_oldPos = Chess.board.get(oldPos);
		String piece_newPos = Chess.board.get(newPos);
		
		/*to check if newPos is a box in the bounds of the board*/
		if(Chess.board.containsKey(newPos) == false) {
			return false;
		}
		
		/*to check if Valid move for king*/
		
		int x= Math.abs((oldPos.charAt(0))- (newPos.charAt(0)));
		int y= Math.abs( (oldPos.charAt(1)) - (newPos.charAt(1)) );
		
		if( (x==0 && y==1) || (x==1 && y==0) || (x==y && x==1 && y==1)) {
			
			//to check if the newpos is empty
			if(Chess.board.get(newPos).equals("  ") || Chess.board.get(newPos).equals("##")) {
				if(!(isCheck(oldPos, newPos))) {
					return true;
					
				}
				
				else {
					//need to prompt user to try a different valid move. 
					return false;
				}
				
			}
			
			/*if new pos is not empty */
			else {
				
				if(piece_oldPos.charAt(0)==piece_newPos.charAt(0)) {
					return false;  //piece color is the same	
				}
				
				else {
					if(!isCheck(oldPos,newPos)) {     //there is a piece at the new position, we need to move there and kill that piece
						return true;
					}
					else {
						//path is not empty
						return false;
					}
				}	
			}
			
		}
		
		else {   //illegal move for King
			return false;
		}
			
	}
	
	
	public static void move(String oldPos, String newPos) {
		String piece_oldPos = Chess.board.get(oldPos);
		
		//move piece to newPos
		Chess.board.put(newPos, piece_oldPos);
		
		//make oldPos an empty box
		if(Chess.isBlackBox(oldPos.charAt(0), oldPos.charAt(1)-'0')) {
			Chess.board.put(oldPos, "##");
		}
		else {
			Chess.board.put(oldPos, "  ");
		}
	}
	
	
	public static boolean isCheck(String oldPos, String newPos) {
		//TODO
		
		return false;
	}
	
	
	
	public static boolean isPathEmpty(String oldPos, String newPos) {
		return false;
	}

}
