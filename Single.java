/**
 * These classes are a subclass of the Hand class, and are used to model a hand of single in a Big Two 
 * card game, respectively. They override methods of the Hand class as appropriate. In
 * particular, the getType() method returns the name of the class as a String object in these
 * classes modeling legal hands in a Big Two card game. 
 * 
 * @author Sabyasachi Purkayastha (UID: 3035729708)
 */
public class Single extends Hand
{
	
	/**
	 * A constructor for building a Single hand with the specified player and list of cards, defining the
	 * implicit super constructor Hand().
	 * 
	 * @param CardGamePlayer player Builds a hand with the specified player.
	 * @param CardList cards The list of cards used.
	 */
	public Single(CardGamePlayer player, CardList cards)
	{
		
		super(player, cards);
	}
	/**
	 * A method for retrieving the top card of this hand.
	 * 
	 * @return Card
	 * 		Returns the top card of this hand.
	 */
	public Card getTopCard()
	{
		
		return this.getCard(0);
	}
	/**
	 * A method for checking if this is a valid hand.
	 * 
	 * @return boolean
	 * 		Returns a boolean value of either true or false depending on whether the given method is a valid hand or not.
	 */
	public boolean isValid()
	{
		if(this.size() == 1)
		{
			/**
			 * returns true if valid Single hand.
			 */
			return true;
		}
		else
		{
			/**
			 * returns false otherwise.
			 */
			return false;
		}
	}
	/**
	 * A method for returning a string specifying the type of this hand.
	 * 
	 * @return String
	 * 		Returns a String specifying the type of hand.
	 */
	public String getType()
	{
		/**
		 * Returns String specifying the type of hand: Single.
		 */
		return "Single";
	}
}