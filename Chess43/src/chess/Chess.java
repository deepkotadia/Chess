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

public class Chess {

	public static HashMap<String, String> board = new HashMap<String, String>(70);
	
	public static void initboard() {
		for(char alpha = 'a'; alpha <= 'h'; alpha++) {
			for(int num = 1; num <= 8; num++) {
				String filerank = Character.toString(alpha) + Integer.toString(num);
				
				if((num == 1 || num == 3 || num == 5 || num == 7) && (alpha == 'a' || alpha == 'c' || alpha == 'e' || alpha == 'g')) {
					board.put(filerank, "##");
				}
				else if((num == 2 || num == 4 || num == 6 || num == 8) && (alpha == 'b' || alpha == 'd' || alpha == 'f' || alpha == 'h')) {
					board.put(filerank, "##");
				}
				else {
					board.put(filerank, "  ");
				}
			}
		}
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
		// TODO Auto-generated method stub
		
		initboard();
		printboard();

	}

}
