/**
 * These classes are a subclass of the Hand class, and are used to model a hand of straight in a Big Two 
 * card game, respectively. They override methods of the Hand class as appropriate. In
 * particular, the getType() method returns the name of the class as a String object in these
 * classes modeling legal hands in a Big Two card game. 
 * 
 * @author Sabyasachi Purkayastha (UID: 3035729708)
 */
public class Straight extends Hand
{
	
	/**
	 * A constructor for building a Straight hand with the specified player and list of cards, defining the
	 * implicit super constructor Hand().
	 * 
	 * @param CardGamePlayer Builds a hand with the specified player.
	 * @param CardList The list of cards used.
	 */
	public Straight(CardGamePlayer player, CardList cards)
	{
		
		super(player, cards);
	}
	/**
	 * A method for retrieving the top card of this hand.
	 * 
	 * @return Card the top card of this hand.
	 */
	public Card getTopCard()
	{
		
		int[] rankC = {this.getCard(0).getRank(), this.getCard(1).getRank(), this.getCard(2).getRank(), this.getCard(3).getRank(), this.getCard(4).getRank()};
		
		int i, j, rankTemp;

		for(i = 0; i < rankC.length; i++)
		{
			if(rankC[i] == 0)
			{
				rankC[i] = 13;
			}
			else if(rankC[i] == 1)
			{
				rankC[i] = 14;
			}
		}
	
		for(i = 0; i < rankC.length; i++)
		{
			for(j = 0; j < rankC.length - i - 1; j++)
			{
				if(rankC[j] > rankC[j + 1])
				{
					rankTemp = rankC[j];
					rankC[j] = rankC[j + 1];
					rankC[j + 1] = rankTemp;
				}
			}
		}
		
		 //Note: We have the highest ranked Card at position 4 of the rankC array.
		 // We transform the rank back into initial rank.
		 
		for(i = 0; i < rankC.length; i++)
		{
			if(rankC[i] >= 13)
			{
				rankC[i] = rankC[i] - 13;
			}
		}
	
		int topCardIndex = -1;
		for(i = 0; i < rankC.length; i++)
		{
			if(this.getCard(i).getRank() == rankC[4])
			{
				topCardIndex = i;
			}
		}
		return this.getCard(topCardIndex);
	}
	/**
	 * A method for checking if this is a valid hand.
	 * 
	 * @return boolean
	 * 		Returns a boolean value of either true or false depending on whether the given method is a valid hand or not.
	 */
	public boolean isValid()
	{
		
		if(this.size() != 5)
		{
			return false;
		}
	
		int[] rankC = {this.getCard(0).getRank(), this.getCard(1).getRank(), this.getCard(2).getRank(), this.getCard(3).getRank(), this.getCard(4).getRank()};
	
		int i, j, rankTemp;
	
		for(i = 0; i < rankC.length; i++)
		{
			if(rankC[i] == 0)
			{
				rankC[i] = 13;
			}
			else if(rankC[i] == 1)
			{
				rankC[i] = 14;
			}
		}
	
		for(i = 0; i < rankC.length; i++)
		{
			for(j = 0; j < rankC.length - i - 1; j++)
			{
				if(rankC[j] > rankC[j + 1])
				{
					rankTemp = rankC[j];
					rankC[j] = rankC[j + 1];
					rankC[j + 1] = rankTemp;
				}
			}
		}
		
		//Checks whether a straight hand or not.
		
		int checkStraight = 0;
		for(i = 0; i < rankC.length - 1; i++)
		{
			if(rankC[i] == (rankC[i+1] - 1))
			{
				checkStraight = checkStraight + 1;
			}
		}
		
		 //We return true if checkStraight counter equals 4.
		
		if(checkStraight == 4)
		{
			return true;
		}
	
		return false;
	}
	/**
	 * A method for returning a string specifying the type of this hand.
	 * 
	 * @return String Returns a String specifying the type of hand.
	 */
	public String getType()
	{
		/**
		 * Returns String specifying the type of hand: Straight.
		 */
		return "Straight";
	}
}