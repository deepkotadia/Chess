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
public class King implements Piece{

	@Override
	public void move(String oldPos, String newPos) {
		// TODO
		String piece_oldPos = Chess.board.get(oldPos);
		String piece_newPos = Chess.board.get(newPos);
		
		/*to check if newPos is a box in the bounds of the board*/
		if(Chess.board.containsKey(newPos) == false) {
			System.out.println("Illegal move, try again");
		}
		
		/*to check if Valid move for king*/
		
		int x= Math.abs((int)(oldPos.charAt(0))- (int)(newPos.charAt(0)));
		int y= Math.abs( (oldPos.charAt(1) - '0') - (newPos.charAt(1)-'0') );
		
		if( (x==0 && y==1) || (x==1 && y==0) || (x==y && x==1 && y==1)) {
			
			//to check if the newpos is empty
			if(Chess.board.get(newPos).equals("  ") || Chess.board.get(newPos).equals("##")) {
				if(!(isCheck(oldPos, newPos))) { //TODO 
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
			
			/*if new pos is not empty */
			else {
				
				if(piece_oldPos.charAt(0)==piece_newPos.charAt(0)) {
					System.out.println("Illegal move, try again");  //piece color is the same	
				}
				
				else {
					if(!isCheck(oldPos,newPos)) {     //there is a piece at the new position, we need to move there and kill that piece.
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

	
	
	public boolean isCheck(String oldPos, String newPos) {
		//TODO
		
		return false;
	}
	
	
	@Override
	public boolean isPathEmpty(String oldPos, String newPos) {
		// TODO Auto-generated method stub
		return false;
	}

}
