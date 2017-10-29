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
public class Pawn extends Piece {

	public Pawn(String value) {
		super(value);
	}


	//@Override
	public boolean isMoveValid(String oldPos, String newPos) {
		
		String piece_oldPos = Chess.board.get(oldPos).getvalue();
		String piece_newPos = Chess.board.get(newPos).getvalue();
		char oldPosalpha = oldPos.charAt(0);
		int oldPosnum = oldPos.charAt(1) - '0';
		char newPosalpha = newPos.charAt(0);
		int newPosnum = newPos.charAt(1) - '0';
		
		/*to check if newPos is a box in the bounds of the board*/
		if(Chess.board.containsKey(newPos) == false) {
			return false;
		}
		
		if(piece_oldPos.charAt(0) == 'w') { //piece is white
			if(newPosnum <= oldPosnum) {
				//pawn cannot move backwards or stay at same place
				return false;
			}
			
			if(newPosalpha != oldPosalpha) { //not a straight move, has to be a diagonal kill
				if((newPosnum-oldPosnum)!=1 || Math.abs((newPosalpha-oldPosalpha))!=1 || isBoxEmpty(newPosalpha, newPosnum)==true) { 
					//pawn cannot move diagonal if not a kill
					return false;
				}
				if(piece_oldPos.charAt(0) == piece_newPos.charAt(0)) {
					//piece color is the same, cannot kill
					return false;
				}
				
				//move and kill
				return true;
				
			}
			
			if(Math.abs(newPosnum - oldPosnum) == 2) { //two steps, has to be in first move
				if(oldPosnum != 2) {
					//pawn can only move two steps in first move
					return false;
				}
				if(!(isPathEmpty(oldPos, newPos))) {
					//pawn can only move two steps when path is clear
					return false;
				}
				
				return true;
			}
			else if(Math.abs(newPosnum - oldPosnum) == 1) { //one step move straight
				//move one step
				return true;
				
			}
			else {
				//pawn cannot move more than two steps
				return false;
			}
			
		}
		
		else { //piece is black
			if(newPosnum >= oldPosnum) {
				//pawn cannot move backwards
				return false;
			}
			
			if(newPosalpha != oldPosalpha) { //not a straight move, has to be a diagonal kill
				if((oldPosnum-newPosnum)!=1 || Math.abs((newPosalpha-oldPosalpha))!=1 || isBoxEmpty(newPosalpha, newPosnum)==true) { 
					//pawn cannot move diagonal if not a kill
					return false;
				}
				
				return true;
				
			}
			
			if(Math.abs(newPosnum - oldPosnum) == 2) { //two steps, has to be in first move
				if(oldPosnum != 7) {
					//pawn can only move two steps in first move
					return false;
				}
				if(!(isPathEmpty(oldPos, newPos))) {
					//pawn can only move two steps when path is clear
					return false;
				}
				
				//move ahead two steps
				return true;
			}
			else if(Math.abs(newPosnum - oldPosnum) == 1) { //one step move straight
				//just move
				return true;
				
			}
			else {
				//pawn cannot move more than two steps
				return false;
			}
		}
		
	}
	
	
	public void move(String oldPos, String newPos, char promopiece) {
		Piece piece_oldPos = Chess.board.get(oldPos);
		//char newPosalpha = newPos.charAt(0);
		int newPosnum = newPos.charAt(1) - '0';
		
		//move piece to newPos
		Chess.board.put(newPos, piece_oldPos);
		
		//make oldPos an empty box
		if(Chess.isBlackBox(oldPos.charAt(0), oldPos.charAt(1)-'0')) {
			Chess.board.put(oldPos, new EmptySquare("##"));
		}
		else {
			Chess.board.put(oldPos, new EmptySquare("  "));
		}

		if(newPosnum == 1 || newPosnum == 8) {
			pawn_promotion(newPos, promopiece);
		}
	}
	

	//@Override
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
		
		if(Chess.board.get(filerank).getvalue().equals("##") || Chess.board.get(filerank).getvalue().equals("  ")) { //box is empty
			return true;
		}
		
		return false;
	}
	
	
	private void pawn_promotion(String newPos, char promopiece) {
		int newPosnum = newPos.charAt(1)-'0';
		
		Piece promopiecetype;
		
		switch(promopiece) {
		
		  case 'R' :
		    if(newPosnum == 8){
		      promopiecetype = new Rook("wR");
		    }
		    else{
		      promopiecetype = new Rook("bR");
		    }
		    break;
		    
		  case 'N' :
		    if(newPosnum == 8){
		      promopiecetype = new Knight("wN");
		    }
		    else{
		      promopiecetype = new Knight("bN");
		    }
		    break;
		    
		  case 'B' :
		    if(newPosnum == 8){
		      promopiecetype = new Bishop("wB");
		    }
		    else{
		      promopiecetype = new Bishop("bB");
		    }
		    break;
		    
		  default :
		    if(newPosnum == 8){
		      promopiecetype = new Queen("wQ");
		    }
		    else{
		      promopiecetype = new Queen("bQ");
		    }
		    break;
		    
		}
		
		/*promote pawn to desired piece, if none chosen, Queen by default*/
		Chess.board.put(newPos, promopiecetype);
		
	}
	

}
