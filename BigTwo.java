import java.util.ArrayList;

/**
 * The BigTwo class implements the CardGame interface and is used to model the BigTwo Card Game.
 * 
 *  @author Sabyasachi Purkayastha (UID: 3035729708)
 */


public class BigTwo implements CardGame {

    private int getNumOfPlayers;
	private Deck deck;
	private ArrayList<CardGamePlayer> playerList;
	private ArrayList<Hand> handsOnTable;
	private int currentPlayerIdx;
	private BigTwoGUI ui;
	private boolean wrongMove = true;
	private int lastpass;
    

/**
 * A constructor for creating a BigTwo Card Game.
 */
public BigTwo()
	{
	
		playerList = new ArrayList<CardGamePlayer>();

		for(int i = 0; i <= 3; i++)
		{

			CardGamePlayer newPlayer = new CardGamePlayer();
			playerList.add(newPlayer);
		}

		handsOnTable = new ArrayList<Hand>();

		this.ui = new BigTwoGUI(this);
	}

    /**
	 * A method for getting the number of players.
	 */
    public int getNumOfPlayers() {
		return this.getNumOfPlayers;
	 
    }
	/**
	 * A method for retrieving the deck of cards being used.
	 */

	public Deck getDeck()
	{
		return this.deck;
	}
	/**
	 * A method for retrieving the list of players
	 */
	
	public ArrayList<CardGamePlayer> getPlayerList()
	{
		return this.playerList;
	}

	/**
	 * A method for retrieving the hands being played on the table
	 */

	public ArrayList<Hand> getHandsOnTable()
	{
		return this.handsOnTable;
	}
    /**
	 * A method for retrieving the index of the current player.
	 */
	
	public int getCurrentPlayerIdx()
	{
		return this.currentPlayerIdx;
	}
	/** 
	 * A method for setting the index of the current Player
	 */

    public void setCurrentPlayerIdx(int x)
    {
        this.currentPlayerIdx=x;
    }
    

    /**
	 * a method for starting/restarting the game with a given shuffled deck of cards.
	 * @param deck deck of cards 
	 */
    public void start(Deck deck)
    {
        for(int i = 0; i <= 3; i++)
		{
          this.playerList.get(i).removeAllCards();
		}

        this.handsOnTable = new ArrayList<Hand>();

    int k = 0;
    int i =0; 
    while(i<52)
    {
        if (k == 4)
        {
            k = 0;
        } 
       

    this.playerList.get(k).addCard(deck.getCard(i));
    
    if (deck.getCard(i).getSuit() ==0  && deck.getCard(i).getRank()==2)
    {
        this.currentPlayerIdx = k;
        this.ui.setActivePlayer(k);
		lastpass = k;
    }
    i++; 
    k++;
	}

	for (int j = 0; j< 4; j++)
	{
		this.playerList.get(j).getCardsInHand().sort();
	}

   this.ui.repaint();
   this.ui.promptActivePlayer();

 }

 /**
  * A method for checking a move by the player
  * @param playerIdx The player who is making the move
  * @param cardIdx  The indexes of the player cards are stored in this array
  */
 public void checkMove(int playerIdx, int[] cardIdx)
 {
     // Checking if the current move is a valid pass.
	 if (cardIdx==null)
	 {		
		 if (lastpass!=this.currentPlayerIdx && handsOnTable.size()!=0)
		 {
			wrongMove = false;
			this.ui.printMsg("{Pass}");
		 }
	 }

	 else
	 {
		 Hand hand = null;
		 Hand previousHand;
		 
	    
		 CardList temp = new CardList();
		 int posOfCard;
		 int i = 0;
		 // Getting the played hand from the indexes.
		 while(i < cardIdx.length)
		 {
			 posOfCard= cardIdx[i];
			 temp.addCard(this.getPlayerList().get(playerIdx).getCardsInHand().getCard(posOfCard));
	 
		  i++;
		 }
	 
		 temp.sort(); 
		 // Finding the type of the hand.
		 hand = composeHand(this.getPlayerList().get(playerIdx), temp); 
		 // Checking if this is a valid hand.
		 if(hand!=null)
		 {
			 
			 if(this.handsOnTable.size()!=0 && lastpass!=playerIdx)
			 {
				 
				 // getting the previous hand for comparison.
				 previousHand = handsOnTable.get(handsOnTable.size()-1);
				 if(hand.size()==previousHand.size() && hand.beats(previousHand))
				 {
					 
					 this.handsOnTable.add(hand);  
					 // removing the cards played from the player.
					 for(int j = cardIdx.length-1; j>= 0; j--)
					 {
						 posOfCard = cardIdx[j];
						 this.getPlayerList().get(playerIdx).getCardsInHand().removeCard(cardIdx[j]);
					 }
			        // printing out the move.
					 if (hand != null) {
					 this.ui.printMsg("{" + hand.getType() + "}");
					 //hand.print(true, false);

					 this.printLastHand(hand);

					 } 
					 else {
						this.ui.printMsg("[Empty]");
					 }

					 // the id of the player who played the last valid move is set to last pass.
					 this.lastpass = this.currentPlayerIdx;
					 if (lastpass == 4){
						 lastpass = 0;
					 }
					 // the move played is valid.
					 this.wrongMove =false;
				 }
			 }    
			 else{

				if ( handsOnTable.size()==0){
					boolean check3d=false; // A variable to check if the 3 of diamonds is in the hand.
                   //  Checking if the three of diamonds is in the hand.
					for (int k =0; k< hand.size(); k++){
						if (hand.getCard(k).getRank()==2 && hand.getCard(k).getSuit()==0){
							check3d = true;
						}

					} 
					if(check3d) // if three of diamonds is in the hand and this is the first hand played add hand to hands on table.
					{
						this.handsOnTable.add(hand);
						// removing the cards played from the player.
				 	    for(int j = cardIdx.length-1; j>=0; j--)
				 		{
						 	posOfCard = cardIdx[j];
					 		this.getPlayerList().get(playerIdx).getCardsInHand().removeCard(cardIdx[j]);
					 	}

						// printing out the move.
					 	if (hand != null) {
							this.ui.printMsg("{" + hand.getType() + "}");
						 	this.printLastHand(hand);
					 	} 
				 		else 
				 		{
							this.ui.printMsg("[Empty]");
				 		}

						 // the id of the player who played the last valid move is set to last pass.
				 		this.lastpass = this.currentPlayerIdx;
				 		if (lastpass == 4)
						{
						 		lastpass = 0;
				 		}
				 		this.wrongMove = false;
					}
				}
				// if the player played the last move, add hands to hands on table.
				else if( lastpass == playerIdx)
				{
					this.handsOnTable.add(hand);
					// removing the cards played from the player.
					for(int j = cardIdx.length-1; j>=0; j--)
					{
						posOfCard = cardIdx[j];
						this.getPlayerList().get(playerIdx).getCardsInHand().removeCard(cardIdx[j]);
					}
   
					   // printing out the move.
					if (hand != null) {
						this.ui.printMsg("{" + hand.getType() + "}");
						this.printLastHand(hand);
					} 
					else 
					{
						this.ui.printMsg("[Empty]");
					}
   
					 // the id of the player who played the last valid move is set to last pass.
					this.lastpass = this.currentPlayerIdx;
					if (lastpass == 4){
						lastpass = 0;
					}
					this.wrongMove = false;
				}
				 

				 
			 }
		 }
	 }
	 // if the hand played is invalid, ask for input again
	 if (wrongMove==true)
	 {
		this.ui.printMsg("This is an illegal move!");
		 this.ui.promptActivePlayer();
		 
	 }

	 else
	 {
		 if( this.endOfGame() == false){
			 // if the game has not yet ended move on to the next player.

			    this.ui.printMsg("");
				// updating the currentPlayerIdx.
			 	if(this.getCurrentPlayerIdx()<3)
			  	{
				 	int x = this.getCurrentPlayerIdx();
				 	this.setCurrentPlayerIdx(++x);
				 	this.ui.setActivePlayer(this.getCurrentPlayerIdx());
			  	}

			 	else
			 	{   
				 	this.setCurrentPlayerIdx(0);
				 	this.ui.setActivePlayer(this.getCurrentPlayerIdx());
			 	}


			 	this.ui.setActivePlayer(this.getCurrentPlayerIdx());
			 	this.ui.repaint();
			 	this.wrongMove = true;
			 	this.ui.promptActivePlayer();


	 }

		 else
		 {
			 // if the game ends print out subsequent messages.
			 this.ui.printMsg("");
			 this.ui.printMsg("Game Ends");
			for (int i =0; i<4; i++)
			{
				if (this.getPlayerList().get(i).getNumOfCards()!=0)
				{
					this.ui.printMsg("Player "+ i +" has " + this.getPlayerList().get(i).getNumOfCards()+ " cards in hand.");

				}

				else
				{
					this.ui.printMsg("Player " + i + " wins the game.");
				}
			}
			 

		 }

	 }



 }


/**
 * The method for checking if the game ends.
 * return true/false depending whether the game ends or not.
 */


public boolean endOfGame()
{
    int i = 0;
    int size;
    while(i<4)
    {
       size = this.getPlayerList().get(i).getCardsInHand().size();
       if (size == 0)
       {
           return true;
       }

	   i++;
       
    }
    return false;
}

public static void main(String[] args){
    BigTwo bigTwoGame = new BigTwo();
    BigTwoDeck bigtwodeck = new BigTwoDeck();
    bigtwodeck.initialize();
    bigtwodeck.shuffle();
    //bigTwoGame.ui = new BigTwoGUI(bigTwoGame);
    bigTwoGame.start(bigtwodeck);

    
}
/**
 *  A method for making a move by a player with the specified index using the cards specified by the list of indices
 * @param playerIdx The player who is making the move
  * @param cardIdx  The indexes of the player cards are stored in this array
 */
    public void makeMove(int playerIdx, int[] cardIdx){
        this.checkMove(playerIdx, cardIdx);
    }


/**
 *  A method for returning a valid hand from the specified list of cards of the playe
 * @param player who is making the move
 * @param cards the cards that the player played
 * @return depending on the players move it returns what type of hand has been played. Null if the played hand isn't valid.
 */
    public static Hand composeHand(CardGamePlayer player, CardList cards)
	{
	
		Single single = new Single(player, cards);
	
		Pair pair = new Pair(player, cards);
	
		Triple triple = new Triple(player, cards);
	
		Straight straight = new Straight(player, cards);
	
		Flush flush = new Flush(player, cards);
	
		FullHouse fullHouse = new FullHouse(player, cards);
	
		Quad quad = new Quad(player, cards);
	
		StraightFlush straightFlush = new StraightFlush(player, cards);

		if(straightFlush.isValid())
		{
			return straightFlush;
		}

		else if(quad.isValid())
		{
			return quad;
		}
	
		else if(fullHouse.isValid())
		{
			return fullHouse;
		}
	
		else if(flush.isValid())
		{
			return flush;
		}
	
		else if(straight.isValid())
		{
			return straight;
		}

		else if(triple.isValid())
		{
			return triple;
		}

		else if(pair.isValid())
		{
			return pair;
		}

		else if(single.isValid())
		{
			return single;
		}
	
		else
		{
			return null;
		}

	}




	public void printLastHand(Hand hand)
	{
		String s = "";
		for (int i = 0; i < hand.size(); i++){
			s += hand.getCard(i) + " ";

		}
		this.ui.printMsg(s);
	}
}
	
