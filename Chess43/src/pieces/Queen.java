/**
 * 
 */
package pieces;

import java.util.ArrayList;
import java.util.List;

import chess.Chess;

/**
 * @author Deep Kotadia
 * @author Chinmoyi Bhushan
 *
 */
public class Queen extends Piece {

	//@Override
	public static boolean move(String oldPos, String newPos) {
		String piece_oldPos = Chess.board.get(oldPos);
		String piece_newPos = Chess.board.get(newPos);
		
		/*to check if newPos is a box in the bounds of the board*/
		if(Chess.board.containsKey(newPos) == false) {
			//System.out.println("Illegal move, try again");
			return false;
		}
		
		/*to check if valid move for Queen (combination of rook and bishop */
		
		if(  (oldPos.charAt(0)==newPos.charAt(0) || oldPos.charAt(1) == newPos.charAt(1)) ||       
				( Math.abs( oldPos.charAt(0) - newPos.charAt(0) ) == Math.abs((oldPos.charAt(1)) - (newPos.charAt(1))) ) && !(oldPos.equals(newPos))){
			
			//to check if the new position is empty:
			if(Chess.board.get(newPos).equals("  ") || Chess.board.get(newPos).equals("##")) {
				if(isPathEmpty(oldPos, newPos)) {                //TODO isPathEmpty for Queen
					Chess.board.put(newPos, piece_oldPos);
					
					if(Chess.isBlackBox(oldPos.charAt(0), oldPos.charAt(1))) {
						Chess.board.put(oldPos, "##");
					}
					else {
						Chess.board.put(oldPos, "  ");
					}
					return true;
				}
				
				else {
					//System.out.println("Illegal move, try again");
					//need to prompt user to dry a different valid move. 
					return false;
				}
			}     //End of if the new pos is empty.
			
			
			/*when new pos is not empty: */
			else {
				if(piece_oldPos.charAt(0)==piece_newPos.charAt(0)) {
					//System.out.println("Illegal move, try again");          //piece color is the same	
					return false;
				}
				
				else {
					if(isPathEmpty(oldPos,newPos)) {                       //there is a piece at the new position, we need to move there and kill that piece.
						Chess.board.put(newPos, piece_oldPos);
						
						if(Chess.isBlackBox(oldPos.charAt(0), oldPos.charAt(1))) {
							Chess.board.put(oldPos, "##");
						}
						else {
							Chess.board.put(oldPos, "  ");
						}
						return true;
					}
					else {
						//path is not empty
						//System.out.println("Illegal move, try again");
						return false;
					}
				}
			}
									
		   }
		
		
		else {                                                //not a valid move for Queen
			//System.out.println("Illegal move, try again");
			return false;
		}	
					
	}

	//@Override
	public static boolean isPathEmpty(String oldPos, String newPos) {
		
		if ((Math.abs(oldPos.charAt(0) - newPos.charAt(0)) == Math.abs (oldPos.charAt(1) - newPos.charAt(1)))) { //it is a diagonal move:
			return isBishopPathEmpty(oldPos, newPos);
			
		}
		
		else if((oldPos.charAt(0) == newPos.charAt(0) || oldPos.charAt(1) == newPos.charAt(1))) {
			return isRookPathEmpty(oldPos, newPos);
		}

		return false;
	}
	
	public static boolean isBishopPathEmpty(String oldPos, String newPos) {
		List<String> boxes=getBoxesInBetween(oldPos, newPos);
		
		for (String index:boxes) {
			if(!(Chess.board.get(index).equals("##") || Chess.board.get(index).equals("  "))) //box is empty
				return false;
		
		}
		return true;
	}
	
	public static List<String> getBoxesInBetween(String oldPos, String newPos) {
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
	
	public static boolean isRookPathEmpty(String oldPos, String newPos) {
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
		
		if(Chess.board.get(filerank).equals("##") || Chess.board.get(filerank).equals("  ")) { //box is empty
			return true;
		}
		
		return false;
	}

}
