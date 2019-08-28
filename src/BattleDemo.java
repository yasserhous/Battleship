/**
 * AUTHOR: MOHAMED-YASSER HOUSSEIN
 * PURPOSE: DEVELOPED AS AN ASSIGNMENT FOR A JAVA COURSE
 * DESCRIPTION: A SIMPLE BATTLESHIP GAME. THE USER IS ASKED TO FIRST PLACE A SET OF SHIPS AND GRENADES AND THEN ASKED TO SINK
 * A BOT'S SET OF SHIPS
 */
import java.util.Scanner;
import java.util.Random;
public class BattleDemo {
	public static void main(String args[]) {
	Scanner keyIn = new Scanner(System.in);
	Random generator = new Random();
	Token[][] gameTokens= new Token[8][8];
	int horizontalCoord,verticalCoord;
	String coordinate;
	/**
	 * At this point we create new game tokens for a human user 
	 * tokens can be either ship of grenades
	 */
	System.out.println("\n\n************************************************************************************************");
	System.out.println("Welcome to Battleship. Here are the rules\n");
	System.out.println("The map is a 8x8 grid. on the vertical , you have coordinates from A to H\n");
	System.out.println("And on the horizontal you have numbers from 1 to 8\n");
	System.out.println("For example, B3 will place your ship on the second row /third column\n");
	System.out.println("At first , you are asked to place 6 ships and 4 grenades. Then the bot will in turn\n");
	System.out.println("place his own ships and grenades. Your goal is to sink the ships of the bot before he sinks yours\n");
	System.out.println("If you hit a grenade, you lose a turn, if the bot hits a grenade, he loses a turn, GoodLuck!");
	System.out.println("************************************************************************************************\n\n\n");
	 for(int i=0;i<6;i++) {
		 System.out.println("Enter Coordinate of Ship "+(i+1)+": ");
		 coordinate = keyIn.next();
		 horizontalCoord = getHorizontalCoord(coordinate);
		 verticalCoord = getVerticalCoord(coordinate);
		 /**
		  * At this point we validate the coordinates inputed by user by
		  * using methods validateInt() and validateOverLap()
		  */
		 while(validateInt(horizontalCoord,verticalCoord)) {
			 System.out.println("Coordinate outside grid ,please enter new coordinates");
			 coordinate = keyIn.next();
			 horizontalCoord = getHorizontalCoord(coordinate);
			 verticalCoord = getVerticalCoord(coordinate);
		 }
		 
		 while(validateOverlap(horizontalCoord,verticalCoord,gameTokens)) {
			 System.out.println("Location already taken. please choose another location");
			 coordinate = keyIn.next();
			 horizontalCoord = getHorizontalCoord(coordinate);
			 verticalCoord = getVerticalCoord(coordinate);
	     }
		 gameTokens[verticalCoord-1][horizontalCoord-1]= new Token("user",'s',false);
     }
	 //filling user grenades
	 for(int i=0;i<4;i++){
		 System.out.println("Enter Coordinate of Grenade "+(i+1)+": ");
		 coordinate = keyIn.next();
		 horizontalCoord = getHorizontalCoord(coordinate);
		 verticalCoord = getVerticalCoord(coordinate);
		 //validating if coordinate within gridSize
		 while(validateInt(horizontalCoord,verticalCoord)) {
			 System.out.println("Coordinate outside grid ,please enter new coordinates");
			 coordinate = keyIn.next();
			 horizontalCoord = getHorizontalCoord(coordinate);
			 verticalCoord = getVerticalCoord(coordinate);
		 }
		 //validating if there is an overlap of two objects
		 while(validateOverlap(horizontalCoord,verticalCoord,gameTokens)) {
			 System.out.println("Location already taken. please choose another location");
			 coordinate = keyIn.next();
			 horizontalCoord = getHorizontalCoord(coordinate);
			 verticalCoord = getVerticalCoord(coordinate);
	     }
		 gameTokens[verticalCoord-1][horizontalCoord-1]= new Token("user",'g',false);
	 }
	 /**
	  * At this point , you can use a random generator to create bot-Tokens in order
	  * To create an opponent for the user.We can use method validateOverLap to make 
	  * sure no token is overlaping
	  */
	 for(int i=0;i<6;i++) {
		 horizontalCoord=(generator.nextInt(8) + 1);
		 verticalCoord=(generator.nextInt(8) + 1);
		//validating if coordinate within gridSize
		 while(validateOverlap(horizontalCoord,verticalCoord,gameTokens)) {
			 horizontalCoord=(generator.nextInt(8) + 1);
			 verticalCoord=(generator.nextInt(8) + 1);
		 }
	  gameTokens[verticalCoord-1][horizontalCoord-1]= new Token("pc",'S',false);
      }
	//PC FILLING GRENADES
	 for(int i=0;i<6;i++) {
		 horizontalCoord=(generator.nextInt(8) + 1);
		 verticalCoord=(generator.nextInt(8) + 1);
		//validating if coordinate within gridSize
		 while(validateOverlap(horizontalCoord,verticalCoord,gameTokens)) {
			 horizontalCoord=(generator.nextInt(8) + 1);
			 verticalCoord=(generator.nextInt(8) + 1);
		 }
	gameTokens[verticalCoord-1][horizontalCoord-1]= new Token("pc",'G',false);
	}
	//filling the rest of the grid with empty tokens
	 for(int i=0;i<gameTokens.length;i++) {
			for(int j=0;j<gameTokens.length;j++) 
				 if(gameTokens[i][j]==null) 
					 gameTokens[i][j]=new Token("empty",'_',false);
	 }
	  System.out.println("Ok! The pc has now set his own battle tokens too");
	//Displaying empty gameboard with everything hidden
	  gridDisplay(gameTokens);
	 /**
	  * At this point the game begins and each player has the right to hit one time
	  * if player hits a ship: The ship is sunk
	  * if player hits a grenade: The grenade explodes and player loses a turn
	  * if player hits an empty spot:nothing happens
	  * game ends when one player is able to sink the other player's entire fleet 
	  */
	  int userSunk=0,pcSunk=0;
	  boolean isWin =(userSunk==6||pcSunk==6);
	  int userPlayAgain=0,pcPlayAgain=0;
	  //USER TURN
	  while(!isWin) {
      for (int k = 0; k <= userPlayAgain; k++) {
	  System.out.print("Choose the position for your next rocket: ");
	  coordinate=keyIn.next();
	  horizontalCoord = getHorizontalCoord(coordinate);
      verticalCoord = getVerticalCoord(coordinate);
      //validating if coordinate inside inside grid
      while(validateInt(horizontalCoord,verticalCoord)) {
			 System.out.println("Coordinate outside grid ,please enter new coordinates");
			 coordinate = keyIn.next();
			 horizontalCoord = getHorizontalCoord(coordinate);
			 verticalCoord = getVerticalCoord(coordinate);
		 }
      if(gameTokens[verticalCoord-1][horizontalCoord-1].getCalledUpon()==true)
    	  System.out.println("Positioned already called");
      else {
    	  gameTokens[verticalCoord-1][horizontalCoord-1].setCalledUpon(true);
          switch(gameTokens[verticalCoord-1][horizontalCoord-1].getType()) {
    	  case ('S'):
    		  System.out.println("Ship hit");
    	      pcSunk++;
    	  break;
    	  case('s'):
    		  System.out.println("Ship hit");
    	      userSunk++;
    	  break;
    	  case('G'):
    		  System.out.println("Boom!grenade.");
    	      pcPlayAgain++;
    	  break;
    	  case('g'):
    	  System.out.println("Boom!grenade.");
    	  pcPlayAgain++;
    	  break;
    	  case('_'):
    		  System.out.println("Nothing");
    		  gameTokens[verticalCoord-1][horizontalCoord-1].setType('*');
    	  break;
          }
      }
      }
      userPlayAgain=0;//After user has played all his turns he reinitialize variable to 0
      
     //display result     
     gridDisplay(gameTokens);
     isWin =(userSunk==6||pcSunk==6);//resetting values after user turn
     //PC TURN
     if(isWin)//skipping pc turn if user already won
    	 continue;
     for (int m = 0; m <= pcPlayAgain; m++) {
     horizontalCoord=(generator.nextInt(8) + 1);
	 verticalCoord=(generator.nextInt(8) + 1);
	 System.out.println("Computer's turn now!");
	  if(gameTokens[verticalCoord-1][horizontalCoord-1].getCalledUpon()==true)
    	  System.out.println("Positioned already called");
      else {
    	  gameTokens[verticalCoord-1][horizontalCoord-1].setCalledUpon(true);
          switch(gameTokens[verticalCoord-1][horizontalCoord-1].getType()) {
    	  case ('S'):
    		  System.out.println("Ship hit");
    	      pcSunk++;
    	  break;
    	  case('s'):
    		  System.out.println("Ship hit");
    	      userSunk++;
    	  break;
    	  case('G'):
    		  System.out.println("Boom!grenade.");
    	      userPlayAgain++;
    	  break;
    	  case('g'):
    	  System.out.println("Boom!grenade.");
    	  userPlayAgain++;
    	  break;
    	  case('_'):
    		  System.out.println("Nothing");
    		  gameTokens[verticalCoord-1][horizontalCoord-1].setType('*');
    	  break;
          }
      }
     }
	  pcPlayAgain=0;//after pc has played all his turns we reinitialize variable to 0
     //display result     
     gridDisplay(gameTokens);
     isWin =(userSunk==6||pcSunk==6);//resetting values after pc turn
	  }
      System.out.println(pcSunk == 6 ? "You won" : "You lost");
      System.out.println("Take a look at where all the tokens are!\n\n");
      for(int i=0;i<gameTokens.length;i++) {
			for(int j=0;j<gameTokens.length;j++) {
				System.out.print(gameTokens[i][j].getType()+"   ");
			if((j%(gameTokens.length-1))==0&&j!=0)
				System.out.println();
			}
      }
      keyIn.close();
	}
    //static methods
	
	/**
	 * method that takes a string such as"A1"and retreives the letter and 
	 * returns the integer corresponding to that letter
	 * @param tempString
	 * @return
	 */
	public static int getHorizontalCoord(String tempString) {
		String temp =tempString.toUpperCase();
		char horizontalCoord = temp.charAt(0);
		int latitude=1;
		switch (horizontalCoord) {
		case ('A'):
			latitude = 1;
			break;
		case 'B':
			latitude = 2;
			break;
		case 'C':
			latitude = 3;
			break;
		case 'D':
			latitude = 4;
			break;
		case 'E':
			latitude = 5;
			break;
		case 'F':
			latitude = 6;
			break;
		case 'G':
			latitude = 7;
			break;
		case 'H':
			latitude = 8;
			break;
		default:
		    latitude =9;

		}
		return (latitude);
	}
	/**
	 * method that takes a string such as"A1" and returns(as an integer)the value 1.
	 * @param tempString
	 * @return
	 */
	public static int getVerticalCoord(String tempString) {
		String integerPartOfString = tempString.substring(1);
		int longitude = Integer.parseInt(integerPartOfString);
		return longitude;
	}
	/**
	 * method that takes two coordinates and validate if they are within(1,8)
	 * @param x
	 * @param y
	 * @return
	 */
	public static boolean validateInt(int x,int y) {
		return(x<1||x>8)||(y<1||y>8);
	}
	/**
	 * method that takes two coordinates and a two dimensional array and verifies 
	 * if the object at those coordinate is overlaping another object
	 * @param x
	 * @param y
	 * @param aToken
	 * @return
	 */
	public static boolean validateOverlap(int x,int y,Token[][] aToken) {
		return(aToken[y-1][x-1]!=null);
	}
	/**
	 * This method takes a multidimensional array of tokens and display a grid while considering
	 * hiding the type of tokens never called upon
	 * @param aToken
	 */
	public static void gridDisplay(Token[][] aToken) {
		for(int i=0;i<aToken.length;i++) {
			for(int j=0;j<aToken.length;j++) {
				 if(aToken[i][j].getCalledUpon()==false)
					 System.out.print(aToken[i][j].stillHidden()+ "   ");
				 else
					 System.out.print(aToken[i][j].getType()+"   ");
					 
			if((j%(aToken.length-1))==0&&j!=0)
				System.out.println();
			}
	 }
		
	}
	
	

}
