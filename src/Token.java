/**
 * The Token class is to be used in a Battleship game driver.
 * it allows simple manipulation to game tokens such as hiding the token, 
 * changing the type of token and many  more.
 * @author Mohamed-Yasser Houssein
 * Comp 249
 * Assignment #1
 * Due Date: September 22,2017
 */
import java.util.Random;   
 public class Token {
	
	Random generator = new Random();
	private boolean calledUpon;
	private String player;
	private char type;
	public static final char HIDDEN='_';
	/**
	 * CONSTRUCTORS
	 * Default Constructor:to use in empty grid spaces
	 * Three variable Constructor: Takes a String, a char, and a boolean
	 * Copy Constructor
	 * @param tempString 
	 * @param tempType
	 */
	
	public Token() {
		type = '_';//nothing there
		player = null;
		calledUpon=false;
	}
	public Token(String player,char type,boolean calledUpon) {
		this.player=player;
		this.type=type;
		this.calledUpon= calledUpon;
	}
	public Token(Token aToken) {
		player=aToken.player;
		type=aToken.type;
		calledUpon=aToken.calledUpon;
	}
	/**
	 * mutator setPlayer allows to change the type of player
	 * @param player
	 */
	public void setPlayer(String player){
		this.player=player;
	}
	/**
	 * mutator setType allows to change the type of token
	 * @param type
	 */
	public void setType(char type){
		this.type=type;
	}
	/**
	 * mutator setCalledUpon allows to change whether object has been called
	 * 
	 * @param calledUpon
	 */
	public void setCalledUpon(boolean calledUpon){
		this.calledUpon=calledUpon;
	}
	/**
	 * accessor getPlayer allows to get type of player
	 * @return
	 */
	public String getPlayer() {
		return player;
	}
	/**
	 * accessor getType allows to get type of token
	 * @return
	 */
	public char getType() {
		return type;
	}
	/**
	 * accessor getCalledUpon allows to validate if token had been called
	 * @return
	 */
	public boolean getCalledUpon(){
		return calledUpon;
	}
	/**
	 * equals method returns true if same type of token, same type of player and whether they
	 * have been both called upon
	 * @param aToken
	 * @return true
	 */
	public boolean equals(Token aToken) {
		return(player.equals(aToken.player)&&type==aToken.type&&calledUpon==aToken.calledUpon);
	}
	/**
	 * toString method returning the type of player and the type of token
	 */
	public String toString() {
	 return("Type of Player: "+player+"Type of token: "+type);
	}
	/**
	 * stillHidden method allows to hide the type of token when it
	 * has not been called upon yet
	 * @return
	 */
	public char stillHidden() {
		return HIDDEN;
	}
}

