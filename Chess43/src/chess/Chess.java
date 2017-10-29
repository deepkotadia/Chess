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

	public static HashMap<String, Piece> board = new HashMap<String, Piece>(70);
	
	public static void initboard() {
		
		for(char alpha = 'a'; alpha <= 'h'; alpha++) {
			for(int num = 1; num <= 8; num++) {
				
				String filerank = Character.toString(alpha) + Integer.toString(num);
				
				if(isBlackBox(alpha, num)) {
					board.put(filerank, new EmptySquare("##"));
				}
				else {
					board.put(filerank, new EmptySquare("  "));
				}
				
				//fill black side
				if(filerank.equals("a8") || filerank.equals("h8")) {
					board.put(filerank, new Rook("bR"));
				}
				else if(filerank.equals("b8") || filerank.equals("g8")) {
					board.put(filerank, new Knight("bN"));
				}
				else if(filerank.equals("c8") || filerank.equals("f8")) {
					board.put(filerank, new Bishop("bB"));
				}
				else if(filerank.equals("d8")) {
					board.put(filerank, new Queen("bQ"));
				}
				else if(filerank.equals("e8")) {
					board.put(filerank, new King("bK"));
				}
				else if(num == 7) {
					board.put(filerank, new Pawn("bp"));
				}
				
				//fill white side
				if(filerank.equals("a1") || filerank.equals("h1")) {
					board.put(filerank, new Rook("wR"));
				}
				else if(filerank.equals("b1") || filerank.equals("g1")) {
					board.put(filerank, new Knight("wN"));
				}
				else if(filerank.equals("c1") || filerank.equals("f1")) {
					board.put(filerank, new Bishop("wB"));
				}
				else if(filerank.equals("d1")) {
					board.put(filerank, new Queen("wQ"));
				}
				else if(filerank.equals("e1")) {
					board.put(filerank, new King("wK"));
				}
				else if(num == 2) {
					board.put(filerank, new Pawn("wp"));
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
				String piece_at_index = board.get(filerank).getvalue();
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
		String wholestr, oldPos, newPos, promotion;
		Piece piece_oldPos;
		char promopiece;
		String[] inputstr_as_arr = new String[3];
		
		boolean is_white_move = true; //true when it's white's move, false when it's black's move
		boolean is_move_valid = false; //return value from move method
		
		Scanner sc = new Scanner(System.in);
		
		while(state_of_game() == 0 || state_of_game() == 1) { //to check if game is still on
			
			
			if(is_white_move == true) {
				System.out.print("White's move: ");
			}
			else {
				System.out.print("Black's move: ");
			}
			wholestr = sc.nextLine(); 
			
			while(wholestr.length() > 7 || wholestr.charAt(2) != ' ' || (wholestr.length() == 7 && wholestr.charAt(5) != ' ')) {
				System.out.println("Invalid input; enter in form filerank filerank");
				System.out.println();
				if(is_white_move == true) {
					System.out.print("White's move: ");
				}
				else {
					System.out.print("Black's move: ");
				}
				wholestr = sc.nextLine();
			}
			
			System.out.println();
			
			inputstr_as_arr = wholestr.split(" ");
			
			/*This is for pawn promotion, if applicable*/
			if(inputstr_as_arr.length > 2) {
				promotion = inputstr_as_arr[2];
				promopiece = promotion.charAt(0);
			}
			else {
				promopiece = '0'; //set promotion string to 0 (no promotion)
			}
			
			oldPos = inputstr_as_arr[0];
			newPos = inputstr_as_arr[1];
			piece_oldPos = board.get(oldPos);
			
			if((is_white_move == true && piece_oldPos.getvalue().charAt(0) == 'w') || (is_white_move == false && piece_oldPos.getvalue().charAt(0) == 'b')) {
				
				/*Test if requested move is valid for the corresponding piece (polymorphism used here)*/
				is_move_valid = piece_oldPos.isMoveValid(oldPos, newPos);
			}
			else {
				
				System.out.println("Invalid input; move correct color piece");
				System.out.println();
				continue;
				
			}
			
			if(is_move_valid == true) {
				
				/*move is valid, so now move the piece (update hashmap)*/
				piece_oldPos.move(oldPos, newPos, promopiece);
				printboard();
				
				/*other person's turn, so switch*/
				if(is_white_move == true) {
					is_white_move = false; //now black's turn
				}
				else {
					is_white_move = true; //now white's turn
				}
			}
			else { //not a valid move, prompt to enter again
				System.out.println("Illegal move, try again");
				System.out.println();
				continue;
			}
						
		}
		
		sc.close();

	}
	
	/*function to check state of the game after every legal move
	 * returns 0 if game is still on
	 * returns 1 if check
	 * returns -1 if checkmate/draw/resign, game over
	 * */
	public static int state_of_game() {
		return 0;
	}

}
