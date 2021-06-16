//689765 
//Level 3

import java.util.InputMismatchException;
import java.util.Scanner;

public class Nim {
	
	static Scanner in = new Scanner(System.in);
	static int[] bag = { 0, 10, 10, 10 }; // wasted 0 for easy comprehension
	static int[] usrResponse = new int[2]; 
	static byte player = 1;  //byte to save memory
	static final String INVALID_INPUT = "Invalid input, try again";
	static final String BAG_ERROR_RESPONSE = "Nim cannot not use that bag, try again";
	static final String TOKEN_ERROR ="Please check token and try again";	
	static int winner = -1;
	
	public static void main(String[] args) {

		//TODO: Optimize code, break Part 
		while (!isThereWinner()) { 
			getUsrInput();
			updateBag();
			bagStatus();

			if (!isThereWinner()) {
				switchPlayer();
			} else {
				printWinner();
				gameOver();
				break;
			}
		}

	}

	
	private static void getUsrInput() {
		/**This method gets a bag number bagChosen and a token tokenChosen from the player respectively.
		 *It validates the bag input by checking if it has double digits, falls between 
		 *the required range, and checks if the bagChosen was already empty.
		 *The token is validated by checking range and making sure it isn't more than the tokens in the 
		 *current bag **/
		try {
			System.out.print("Player " + getPlayer() + " - choose bag: ");
			 String bagChosen = in.next(); // computer sees input as string

			// CHECKS FOR DOUBLE DIGITS,RANGE,BAG ALREADY EMPTY 
			if (bagChosen.length() > 1 || bagChosen.charAt(0) < '1' || bagChosen.charAt(0) > '3' || bag[bagChosen.charAt(0) - '0'] == 0) {
				throw new InputMismatchException(BAG_ERROR_RESPONSE);
			}

			// GETS AND VALIDATES TOKEN
			boolean isTokenError = true;
			while (isTokenError) {
				System.out.print("Now choose no. of tokens: ");
				if (!in.hasNextInt()) {
					System.out.println(TOKEN_ERROR);
					in.next();
				} else {
					int tokenChosen = in.nextInt();
					// HANDLES TOKEN RANGE AND USER PICKING MORE TOKENS IN BAG
					if (tokenChosen < 1 || tokenChosen > 10 || bag[bagChosen.charAt(0) - '0'] < tokenChosen || bag[bagChosen.charAt(0) - '0'] < 0) {
						System.out.println(TOKEN_ERROR);
					} else{
						usrResponse[0] = Integer.parseInt(bagChosen);
						usrResponse[1] = tokenChosen;
						isTokenError = false;
					}
				} // else
			} // While

		} catch (InputMismatchException e) { 
			if(e.getMessage().equals(BAG_ERROR_RESPONSE)){
				System.out.println(BAG_ERROR_RESPONSE);
				getUsrInput();
			}else{
			System.out.println(INVALID_INPUT);
			getUsrInput();
			}
		}

	}

	public static boolean isThereWinner() {
		/**This method determines the winner of the game
		 * by checking if all the bags are empty and 
		 * @return true if a winner was found and false if otherwise**/
		boolean isWinner;
		if (bag[1] == 0 && bag[2] == 0 && bag[3] == 0) {
			winner = getOtherPlayer();
			isWinner = true;
		} else {
			isWinner = false;
		}
		return isWinner;
	}

		public static void printWinner(){
			/**Displays the winner of the game, if any **/
		if (winner == 1) {
			System.out.println("Game Over - Player " + winner + " wins");
		} else if (winner == 2) {
			System.out.println("Game Over - Player " + winner + " wins");
		}
	}

		public static void updateBag() {
			/**updates the bag based on the users input **/
			//usrBagValue is the number of tokens in the current bag
			//usrToken is the number of tokens selected by user 
			int usrBagValue = bag[usrResponse[0]];
			int usrTokens = usrResponse[1];

				usrBagValue -= usrTokens;

			bag[usrResponse[0]] = usrBagValue;
		}

		public static void bagStatus() {
			/**Prints out the current status of the bags in the format
			 * Bag Status: XX, YY, ZZ **/
			System.out.print("Bag Status: ");
			int bagLocation = 1;
			while (bagLocation != bag.length) {
				if (bagLocation != bag.length - 1) // if ! third time through
					System.out.print(bag[bagLocation] + ", ");
				else
					System.out.print(bag[bagLocation]);

				bagLocation++;
			}
			System.out.println();
		}

	public static void switchPlayer() {
		/**Switches the player**/
		if (player == 1)
			player = 2;
		else
			player = 1;
	}

	public static int getPlayer() {
		/**@returns the current player**/
		return player;
	}

	public static int getOtherPlayer() {
		/**@return The player who's not in turn **/
		if (getPlayer() == 1)
			return 2;
		else
			return 1;

	}
	private static void gameOver() {
		/**Resets Variables for next game**/
		player =1;
		bag[1] = 10;
		bag[2] = 10;
		bag[3] = 10;
		winner = -1;
		usrResponse[0] = 0;
		usrResponse[0] = 0;
		in.close();
	}
}