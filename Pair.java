/**
 * These classes are a subclass of the Hand class, and are used to model a hand of pair in a Big Two 
 * card game, respectively. They override methods of the Hand class as appropriate. In
 * particular, the getType() method returns the name of the class as a String object in these
 * classes modeling legal hands in a Big Two card game. 
 * 
 * @author Sabyasachi Purkayastha (UID: 3035729708)
 */
public class Pair extends Hand
{
	
	/**
	 * A constructor for building a Pair hand with the specified player and list of cards, defining the
	 * implicit super constructor Hand().
	 * 
	 * @param CardGamePlayer player
	 * 		Builds a hand with the specified player.
	 * @param CardList cards
	 * 		The list of cards used.
	 */
	public Pair(CardGamePlayer player, CardList cards)
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
		
		this.sort();
		
		return this.getCard(1);
	}
	/**
	 * A method for checking if this is a valid hand.
	 * 
	 * @return boolean
	 * 		Returns a boolean value of either true or false depending on whether the given method is a valid hand or not.
	 */
	public boolean isValid()
	{
		
		if(this.size() == 2)
		{
			
			int rankC1 = this.getCard(0).getRank();
			int rankC2 = this.getCard(1).getRank();
			
			if(rankC1 == rankC2)
			{
				
				return true;
			}
			else
			{
			
				return false;
			}
		}

		return false;
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
		 * Returns String specifying the type of hand: Pair.
		 */
		return "Pair";
	}
}