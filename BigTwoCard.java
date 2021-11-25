/**
 * The BigTwoCard class is a subclass of the Card class, and is used to model a card used in a
 * Big Two card game. It overrides the compareTo() method it inherits from the Card class
 * to reflect the ordering of cards used in a Big Two card game.
 * 
 * @author Sabyasachi Purkayastha (UID: 3035729708)
 */
public class BigTwoCard extends Card
{
	
	
	/**
	 * A constructor for building a card with the specified suit and rank. 
	 * 
	 * @param integer suit
	 * 		Suit is an integer between 0 and 3.
	 * 		0 = Diamond, 1 = Club, 2 = Heart, 3 = Spade.
	 * @param integer rank
	 * 		Rank is an integer between 0 and 12.
	 * 		0 = 'A', 1 = '2', 2 = '3', ..., 8 = '9', 9 = '0', 10 = 'J', 11 = 'Q', 12 = 'K'.
	 */
	public BigTwoCard(int suit, int rank)
	{
		super(suit, rank);
	}
	/**
	 * A method for comparing the order of this card with the specified card. Returns a negative
	 * integer, zero, or a positive integer as this card is less than, equal to, or greater than the 
	 * specified card.
	 *  
	 * @param Card card
	 *		Stores the card to be compared.
	 * @return integer
	 * 		Returns a negative integer, zero, or a positive integer as this card is
	 *      less than, equal to, or greater than the specified card
	 */
	public int compareTo(Card card)
	{
		
		int thisRank = this.rank;
	
		int specifiedRank = card.rank;
	
		if(thisRank == 0)
		{
			thisRank = 13;
		}
		if(thisRank == 1)
		{
			thisRank = 14;
		}
	
		if(specifiedRank == 0)
		{
			specifiedRank = 13;
		}
		if(specifiedRank == 1)
		{
			specifiedRank = 14;
		}
	
		if(thisRank > specifiedRank)
		{
			return 1;
		}
		else if(thisRank < specifiedRank)
		{
			return -1;
		}
		else if(this.suit > card.suit)
		{
			return 1;
		}
		else if(this.suit < card.suit)
		{
			return -1;
		}
		else
		{
			return 0;
		}
	}
}