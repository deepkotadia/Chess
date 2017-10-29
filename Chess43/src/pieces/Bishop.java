/**
 * 
 */
package pieces;

import chess.Chess;
import java.lang.Math;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Deep Kotadia
 * @author Chinmoyi Bhushan
 *
 */
public class Bishop extends Piece{

	public Bishop(String value) {
		super(value);
	}


	public boolean isMoveValid(String oldPos, String newPos) {
		String piece_oldPos=Chess.board.get(oldPos).getvalue();
		String piece_newPos=Chess.board.get(newPos).getvalue();
		
		/*to check if newPos is a box in the bounds of the board*/
		if(Chess.board.containsKey(newPos) == false) {
			return false;
		}
		
		//to check if valid move for a bishop:
		if((Math.abs(oldPos.charAt(0) - newPos.charAt(0)) == Math.abs (oldPos.charAt(1) - newPos.charAt(1))) && !(oldPos.equals(newPos))) {
			
			//to check if the newPos is empty:
			if(Chess.board.get(newPos).getvalue().equals("  ") || Chess.board.get(newPos).getvalue().equals("##")) {
				if(isPathEmpty(oldPos, newPos)) {                
					return true;
				}
				
				else {
					//need to prompt user to dry a different valid move
					return false;
				}
			}                               //closing of the if check for newPos being empty. 
			
			/*Color case when newPos is not empty*/
			else {
				if(piece_oldPos.charAt(0)==piece_newPos.charAt(0)) {
					//System.out.println("Illegal move, try again");  //piece color is the same	
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
		
		else {   //illegal move for Bishop
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

	
	public boolean isPathEmpty(String oldPos, String newPos) {
		List<String> boxes=getIndicesInBetween(oldPos, newPos);
		
		for (String index:boxes) {
			if(!(Chess.board.get(index).getvalue().equals("##") || Chess.board.get(index).getvalue().equals("  "))) //box is empty
				return false;
		
		}
		return true;
	}
	
	
	public static List<String> getIndicesInBetween(String oldPos, String newPos) {
		//@requires oldPos and newPos to form a diagonal path.
		//e.g. (A1, C3) is valid. (E4, C6) is valid. (G6, D3) is valid.
		
		List<String> indicesList = new ArrayList<String>();
		
		int x1 = (int)(oldPos.charAt(0)); int x2 = (int)(newPos.charAt(0));
		int y1 = oldPos.charAt(1) - '0'; int y2 = newPos.charAt(1) - '0';
		
		int xGradient = Math.abs(x2 - x1)/(x2 - x1);
		int yGradient = Math.abs(y2 - y1)/(y2 - y1);
		
		for(int i = 1; i < Math.abs(x2 - x1); i++) {
			char nextX = (char)(x1 + i*xGradient);
			int nextY = y1 + i*yGradient;
			
			indicesList.add(Character.toString(nextX) + Integer.toString(nextY) + "");
		}
		
		return indicesList;
	}

}
