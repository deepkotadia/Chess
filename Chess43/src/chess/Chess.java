/**
 * 
 */
package chess;

/**
 * @author Deep Kotadia
 * @author Chinmoyi Bhushan
 *
 */
import java.util.*;
import pieces.*;

public class Chess {

	public static HashMap<String, String> board = new HashMap<String, String>(70);
	
	public static void initboard() {
		
		for(char alpha = 'a'; alpha <= 'h'; alpha++) {
			for(int num = 1; num <= 8; num++) {
				
				String filerank = Character.toString(alpha) + Integer.toString(num);
				
				if(isBlackBox(alpha, num)) {
					board.put(filerank, "##");
				}
				else {
					board.put(filerank, "  ");
				}
				
				//fill black side
				if(filerank.equals("a8") || filerank.equals("h8")) {
					board.put(filerank, "bR");
				}
				else if(filerank.equals("b8") || filerank.equals("g8")) {
					board.put(filerank, "bN");
				}
				else if(filerank.equals("c8") || filerank.equals("f8")) {
					board.put(filerank, "bB");
				}
				else if(filerank.equals("d8")) {
					board.put(filerank, "bQ");
				}
				else if(filerank.equals("e8")) {
					board.put(filerank, "bK");
				}
				else if(num == 7) {
					board.put(filerank, "bp");
				}
				
				//fill white side
				if(filerank.equals("a1") || filerank.equals("h1")) {
					board.put(filerank, "wR");
				}
				else if(filerank.equals("b1") || filerank.equals("g1")) {
					board.put(filerank, "wN");
				}
				else if(filerank.equals("c1") || filerank.equals("f1")) {
					board.put(filerank, "wB");
				}
				else if(filerank.equals("d1")) {
					board.put(filerank, "wQ");
				}
				else if(filerank.equals("e1")) {
					board.put(filerank, "wK");
				}
				else if(num == 2) {
					board.put(filerank, "wp");
				}
				
			}
		}
	}
	
	
	public static boolean isBlackBox(char alpha, int num) {
		
		if((num == 1 || num == 3 || num == 5 || num == 7) && (alpha == 'a' || alpha == 'c' || alpha == 'e' || alpha == 'g')) {
			return true;
		}
		else if((num == 2 || num == 4 || num == 6 || num == 8) && (alpha == 'b' || alpha == 'd' || alpha == 'f' || alpha == 'h')) {
			return true;
		}
		return false;
	}
	
	
	public static void printboard() {
		for(int num = 8; num >= 1; num--) {
			for(char alpha = 'a'; alpha <= 'h'; alpha++) {
				String filerank = Character.toString(alpha) + Integer.toString(num);
				String piece_at_index = board.get(filerank);
				System.out.print(piece_at_index + " ");
			}
			System.out.println(num);
		}
		System.out.println(" a" + "  b" + "  c" + "  d" + "  e" + "  f" + "  g" + "  h");
		System.out.println();

	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		initboard();
		printboard();
		String wholestr, oldPos, newPos, piece_oldPos; //piece_newPos;
		char piece;
		String[] inputstr_as_arr = new String[2];
		System.out.println("test print");
		
		boolean is_white_move = true; //true when it's white's move; false when it's black's move
		
		while(state_of_game() != -1) { //to check if game is still on
			
			if(is_white_move == true) {
				System.out.print("White's move: ");
			}
			else {
				System.out.print("Black's move: ");
			}
			
			System.out.println();
			
				
			
			Scanner sc = new Scanner(System.in);
			wholestr = sc.nextLine(); 
			
			while(wholestr.length() != 5 || wholestr.charAt(2) != ' ') {
				System.out.println("Invalid input; enter in form filerank filerank");
				wholestr = sc.nextLine();
			}
			
			inputstr_as_arr = wholestr.split(" ");
			oldPos = inputstr_as_arr[0];
			newPos = inputstr_as_arr[1];
			piece_oldPos = board.get(oldPos);
			//piece_newPos = board.get(newPos);
			
			if((is_white_move == true && piece_oldPos.charAt(0) == 'w') || (is_white_move == false && piece_oldPos.charAt(0) == 'b')) {
				piece = piece_oldPos.charAt(1);
				
				switch(piece) {
					case 'p' : 
						Pawn.move(oldPos, newPos);
						break;
					case 'R' :
						Rook.move(oldPos, newPos);
						break;
					case 'N' :
						Knight.move(oldPos, newPos);
						break;
					case 'B' :
						Bishop.move(oldPos, newPos);
						break;
					case 'Q' :
						Queen.move(oldPos, newPos);
						break;
					case 'K' :
						King.move(oldPos, newPos);
						break;
				}
				
			}
			else {
				System.out.println("Invalid input; move correct color piece");
				wholestr = sc.nextLine(); 
				
				while(wholestr.length() != 5 || wholestr.charAt(2) != ' ') {
					System.out.println("Invalid input; move correct color piece");
					wholestr = sc.nextLine();
				}
			}
			
			sc.close();
			
		}

	}
	
	/*function to check state of the game after every legal move
	 * returns 0 if game is still on
	 * returns 1 if check
	 * returns -1 if checkmate/draw, game over
	 * */
	public static int state_of_game() {
		return 0;
	}

}
