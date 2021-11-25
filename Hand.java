/**
 * The Hand class is a subclass of the CardList class, and is used to model a hand of cards. It
 * has a private instance variable for storing the player who plays this hand. It also has methods
 * for getting the player of this hand, checking if it is a valid hand, getting the type of this hand,
 * getting the top card of this hand, and checking if it beats a specified hand.
 * 
 * @author Sabyasachi Purkayastha (UID: 3035729708)
 */
public abstract class Hand extends CardList
{
	
	

	private CardGamePlayer player;
	/**
	 * A constructor for building a hand with the specified player and list of cards.
	 * 
	 * @param CardGamePlayer player
	 * 		Builds a hand with the specified player.
	 * @param CardList cards
	 * 		The list of cards used.
	 */
	public Hand(CardGamePlayer player, CardList cards)
	{
		
		this.player = player;
		
		
		for(int i = 0; i < cards.size(); i++)
		{
			
			this.addCard(cards.getCard(i));
		}
	}
	/**
	 * A method for retrieving the player of this hand.
	 * 
	 * @return CardGamePlayer player
	 * 		Returns the player of this hand.
	 */
	public CardGamePlayer getPlayer()
	{
		return this.player;
	}
	/**
	 * A method for retrieving the top card of this hand.
	 * 
	 * @return null
	 * 		Returns the top card of this hand. Returns null as it gets overwritten in the individual subclasses of the Hand
	 * 		class for which the Hand class is a superclass.
	 */
	public Card getTopCard()
	{
		return null;
	}
	/**
	 * A method for checking if this hand beats a specified hand.
	 * 
	 * @param hand
	 * 		Computes and checks whether this hand beats a specified hand or not.
	 * @return boolean
	 * 		Returns a boolean value depending on whether this hand beats a specified hand or not. 
	 * 		Returns true if it does, and false if it doesn't. 
	 */
	public boolean beats(Hand hand)
	{
		
		String[] legalCombos = {"Single", "Pair", "Triple", "Straight", "Flush", "FullHouse", "Quad", "StraightFlush"};
		// The following if statements are checking if the sizes are the same and returning the cards according to that.

		if(((this.size() == 1) && (hand.size() == 1)) && (this.isValid() && hand.isValid()))
		{
			if(this.getType() == legalCombos[0] && hand.getType() == legalCombos[0])
			{
				
				if(this.getTopCard().compareTo(hand.getTopCard()) == 1)
				{
					
					return true;
				}
			}
		}
	
		if(((this.size() == 2) && (hand.size() == 2)) && (this.isValid() && hand.isValid()))
		{
			if(this.getType() == legalCombos[1] && hand.getType() == legalCombos[1])
			{
		
				if(this.getTopCard().compareTo(hand.getTopCard()) == 1)
				{
				
					return true;
				}
			}
		}

		if(((this.size() == 3) && (hand.size() == 3)) && (this.isValid() && hand.isValid()))
		{
			if(this.getType() == legalCombos[2] && hand.getType() == legalCombos[2])
			{
			
				if(this.getTopCard().compareTo(hand.getTopCard()) == 1)
				{
					
					return true;
				}
			}
		}
	    // for straight
		if(((this.size() == 5) && (hand.size() == 5)) && (this.isValid() && hand.isValid()))
		{

			if (hand.getType() == "Straight"){
				if (hand.getType() != "Straight"){
					return true;
				}
			}



			else if(this.getType() == legalCombos[3] && hand.getType() == legalCombos[3])
			{
				
				if(this.getTopCard().compareTo(hand.getTopCard()) == 1)
				{
					
					return true;
				}
			}
		}
		// for flush
		
		if(((this.size() == 5) && (hand.size() == 5)) && (this.isValid() && hand.isValid()))
		{

			if (hand.getType() == "Flush"){
				if (hand.getType() == "StraightFlush" || hand.getType() == "Quad" || hand.getType() == "FullHouse"){
					return false;
				}
				else if (hand.getType() == "Straight"){
					return true;
				}
			}
			
			
			else if(this.getType() == legalCombos[4] && hand.getType() == legalCombos[4])
			{
				
				if(this.getTopCard().compareTo(hand.getTopCard()) == 1)
				{
					
					return true;
				}
			}
		}
		// for fullhouse

		if(((this.size() == 5) && (hand.size() == 5)) && (this.isValid() && hand.isValid()))
		{
			
			if (hand.getType() == "FullHouse"){
				if (hand.getType() == "StraightFlush" || hand.getType() == "Quad"){
					return false;
				}
				else if (hand.getType() == "Straight" || hand.getType() == "Flush"){
					return true;
				}
			}
			
			
			
			else if(this.getType() == legalCombos[5] && hand.getType() == legalCombos[5])
			{
				
				if(this.getTopCard().compareTo(hand.getTopCard()) == 1)
				{
					
					return true;
				}
			}
		}
		// for quad

		if(((this.size() == 5) && (hand.size() == 5)) && (this.isValid() && hand.isValid()))
		{

			if (hand.getType() == "Quad"){
				if (hand.getType() == "StraightFlush"){
					return false;
				}
				else if (hand.getType() == "Straight" || hand.getType() == "Flush" || hand.getType() == "FullHouse"){
					return true;
				}
			}
			
			else if(this.getType() == legalCombos[6] && hand.getType() == legalCombos[6])
			{
				
				if(this.getTopCard().compareTo(hand.getTopCard()) == 1)
				{
					
					return true;
				}
			}
		}

		// for StraightFlush
		if(((this.size() == 5) && (hand.size() == 5)) && (this.isValid() && hand.isValid()))
		{
			if (hand.getType() != "StraightFlush"){
				return true;
			}
			
			
			else if(this.getType() == legalCombos[7] && hand.getType() == legalCombos[7])
			{
				
				if(this.getTopCard().compareTo(hand.getTopCard()) == 1)
				{
					
					return true;
				}
			}
		}
		
		return false;
		
	}

	/**
	 * The abstract methods: isValid() and getType().
	 */
	/**
	 * A method for checking if this is a valid hand.
	 * 
	 * @return boolean
	 * 		Returns a boolean value of either true or false depending on whether the given method is a valid hand or not.
	 */
	 public abstract boolean isValid();
	 /**
	 * A method for returning a string specifying the type of this hand.
	 * 
	 * @return String
	 * 		Returns a String specifying the type of hand.
	 */
	public abstract String getType();
}