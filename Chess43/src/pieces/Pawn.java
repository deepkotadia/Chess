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
public class Pawn implements Piece {

	@Override
	public void move(String oldPos, String newPos) {
		
		String piece_oldPos = Chess.board.get(oldPos);
		String piece_newPos = Chess.board.get(newPos);
		char oldPosalpha = oldPos.charAt(0);
		int oldPosnum = oldPos.charAt(1) - '0';
		char newPosalpha = newPos.charAt(0);
		int newPosnum = newPos.charAt(1) - '0';
		
		/*to check if newPos is a box in the bounds of the board*/
		if(Chess.board.containsKey(newPos) == false) {
			System.out.println("Illegal move, try again");
			return;
		}
		
		if(piece_oldPos.charAt(0) == 'w') { //piece is white
			if(newPosnum <= oldPosnum) {
				System.out.println("Illegal move, try again"); //pawn cannot move backwards or stay at same place
				return;
			}
			
			if(newPosalpha != oldPosalpha) { //not a straight move, has to be a diagonal kill
				if((newPosnum-oldPosnum)!=1 || Math.abs((newPosalpha-oldPosalpha))!=1 || isBoxEmpty(newPosalpha, newPosnum)==true) { 
					System.out.println("Illegal move, try again"); //pawn cannot move diagonal if not a kill
					return;
				}
				if(piece_oldPos.charAt(0) == piece_newPos.charAt(0)) {
					System.out.println("Illegal move, try again");  //piece color is the same, cannot kill
					return;
				}
				
				//move and kill
				Chess.board.put(newPos, piece_oldPos);
				
				if(Chess.isBlackBox(oldPosalpha, oldPosnum)) {
					Chess.board.put(oldPos, "##");
				}
				else {
					Chess.board.put(oldPos, "  ");
				}
				
				if(newPosnum == 8) {
					pawn_promotion(newPosalpha, newPosnum);
				}
				
			}
			
			if(Math.abs(newPosnum - oldPosnum) == 2) { //two steps, has to be in first move
				if(oldPosnum != 2) {
					System.out.println("Illegal move, try again"); //pawn can only move two steps in first move
					return;
				}
				if(!(isPathEmpty(oldPos, newPos))) {
					System.out.println("Illegal move, try again"); //pawn can only move two steps when path is clear
					return;
				}
				
				//move ahead two steps
				Chess.board.put(newPos, piece_oldPos);
				
				if(Chess.isBlackBox(oldPosalpha, oldPosnum)) {
					Chess.board.put(oldPos, "##");
				}
				else {
					Chess.board.put(oldPos, "  ");
				}
			}
			else if(Math.abs(newPosnum - oldPosnum) == 1) { //one step move straight
				//move one step
				Chess.board.put(newPos, piece_oldPos);
				
				if(Chess.isBlackBox(oldPosalpha, oldPosnum)) {
					Chess.board.put(oldPos, "##");
				}
				else {
					Chess.board.put(oldPos, "  ");
				}
				
				if(newPosnum == 8) {
					pawn_promotion(newPosalpha, newPosnum);
				}
				
			}
			else {
				System.out.println("Illegal move, try again"); //pawn cannot move more than two steps
				return;
			}
			
		}
		
		else { //piece is black
			if(newPosnum >= oldPosnum) {
				System.out.println("Illegal move, try again"); //pawn cannot move backwards
				return;
			}
			
			if(newPosalpha != oldPosalpha) { //not a straight move, has to be a diagonal kill
				if((oldPosnum-newPosnum)!=1 || Math.abs((newPosalpha-oldPosalpha))!=1 || isBoxEmpty(newPosalpha, newPosnum)==true) { 
					System.out.println("Illegal move, try again"); //pawn cannot move diagonal if not a kill
					return;
				}
				
				//move and kill
				Chess.board.put(newPos, piece_oldPos);
				
				if(Chess.isBlackBox(oldPosalpha, oldPosnum)) {
					Chess.board.put(oldPos, "##");
				}
				else {
					Chess.board.put(oldPos, "  ");
				}
				
				if(newPosnum == 1) {
					pawn_promotion(newPosalpha, newPosnum);
				}
				
			}
			
			if(Math.abs(newPosnum - oldPosnum) == 2) { //two steps, has to be in first move
				if(oldPosnum != 7) {
					System.out.println("Illegal move, try again"); //pawn can only move two steps in first move
					return;
				}
				if(!(isPathEmpty(oldPos, newPos))) {
					System.out.println("Illegal move, try again"); //pawn can only move two steps when path is clear
					return;
				}
				
				//move ahead two steps
				Chess.board.put(newPos, piece_oldPos);
				
				if(Chess.isBlackBox(oldPosalpha, oldPosnum)) {
					Chess.board.put(oldPos, "##");
				}
				else {
					Chess.board.put(oldPos, "  ");
				}
			}
			else if(Math.abs(newPosnum - oldPosnum) == 1) { //one step move straight
				//just move
				Chess.board.put(newPos, piece_oldPos);
				
				if(Chess.isBlackBox(oldPosalpha, oldPosnum)) {
					Chess.board.put(oldPos, "##");
				}
				else {
					Chess.board.put(oldPos, "  ");
				}
				
				if(newPosnum == 1) {
					pawn_promotion(newPosalpha, newPosnum);
				}
				
			}
			else {
				System.out.println("Illegal move, try again"); //pawn cannot move more than two steps
				return;
			}
		}
		
	}

	@Override
	public boolean isPathEmpty(String oldPos, String newPos) {
		int i;
		int numoldPos = oldPos.charAt(1) - '0';
		int numnewPos = newPos.charAt(1) - '0';
		
		if(numoldPos < numnewPos) { //going forward for white
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
		
		return true;
	}
	
	private boolean isBoxEmpty(char alpha, int num) {
		String filerank = alpha + "" + num;
		
		if(Chess.board.get(filerank).equals("##") || Chess.board.get(filerank).equals("  ")) { //box is empty
			return true;
		}
		
		return false;
	}
	
	
	private void pawn_promotion(char newPosalpha, int newPosnum) {
		//TODO
	}
	

}
