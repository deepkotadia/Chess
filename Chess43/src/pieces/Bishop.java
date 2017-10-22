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
public class Bishop implements Piece{

	@Override
	public void move(String oldPos, String newPos) {
		String piece_oldPos=Chess.board.get(oldPos);
		String piece_newPos=Chess.board.get(newPos);
		
		/*to check if newPos is a box in the bounds of the board*/
		if(Chess.board.containsKey(newPos) == false) {
			System.out.println("Illegal move, try again");
		}
		
		//to check if valid move for a bishop:
		if((Math.abs(oldPos.charAt(0) - newPos.charAt(0)) == Math.abs (oldPos.charAt(1)-'0' - newPos.charAt(1)-'0')) && !(oldPos.equals(newPos))) {
			
			//to check if the newPos is empty:
			if(Chess.board.get(newPos).equals("  ") || Chess.board.get(newPos).equals("##")) {
				if(isPathEmpty(oldPos, newPos)) {                //TODO isPathEmpty for Bishop
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
			}                               //closing of the if check for newPos being empty. 
			
			/*Color case when newPos is not empty*/
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
		
		else {   //illegal move for Bishop
			System.out.println("Illegal move, try again");
		}	
		
	}

	@Override
	public boolean isPathEmpty(String oldPos, String newPos) {
		//TODO
		
		List<String> boxes=getIndicesInBetween(oldPos, newPos);
		
		for (String index:boxes) {
			if(!(Chess.board.get(index).equals("##") || Chess.board.get(index).equals("  "))) //box is empty
				return false;
		
		}
		return true;
	}
	
	
	public List<String> getIndicesInBetween(String oldPos, String newPos) {
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
