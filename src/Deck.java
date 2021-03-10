import java.util.ArrayList;

public class Deck {

private ArrayList<Card> deck;
	
	//HELPER METHOD Deck() Constructor
	public void createCards(String s) {
		
		Card card;
		String number="1";
		
		for(int i=1; i<14;i++) {
			
			number=number+i;
			if(i==1) {
				card=new Card("Ace",s,i);
				deck.add(card);
			}
			else if(i==11) {
				card=new Card("Jack",s,i);
				deck.add(card);
			}
			else if(i==12) {
				card=new Card("Queen",s,i);
				deck.add(card);
			}
			else if(i==13) {
				card=new Card("King",s,i);
				deck.add(card);
			}
			else {		
				card=new Card(number,s,i);
				deck.add(card);
			}
			
			number ="";
			
		}
		
	}//closing Helper method.
	public Deck() { 
		
		deck=new ArrayList<Card>();
		
		createCards("Diamonds");
		createCards("Hearts");
		createCards("Spades");
		createCards("Clubs");
		
		shuffle();
	}//Closing constructor with no parameters.
	
	
	public Deck(String[] rank ,String[] suits, int[] pointValue ) {
		
		deck=new ArrayList<Card>();
		
		int lenRank=rank.length;
		int lenSuits=suits.length;
		Card c;
		
		for(int k=0;k<lenSuits;k++) 
				for(int i=0;i<lenRank;i++) {
					c=new Card(rank[i],suits[k],pointValue[i]);
					deck.add(c);
				}
		
		shuffle();
		
	}//Closing constructor with parameters.
	
	
	public void shuffle() {
		
		for(int i=0;i<size();i++) {
			
			int rand1=(int)(Math.random()*(size()));
			int rand2= (int)(Math.random()*(size()));
			
			while(rand2==rand1)
				 rand2= (int)(Math.random()*(size()));
			
			Card holder=deck.get(rand1);
			deck.set(rand1, deck.get(rand2));
			deck.set(rand2, holder);
			
		}
		
	}//closing shuffle().
	
	
	public int size() { return deck.size();}//closing size().
	
	
	public boolean isEmpty() {
		
		if(size()==0)
			return true;
		return false;
		
	}//closing isEmpty().
	
	
	public String toString() {
		
		String r="";
		for(int i=0;i<size();i++)
			r=r+"\n"+deck.get(i);
		
		return r;
	}//closing toString
	
	
	public void refillDeck() {
		
		Deck a =  new Deck();
		this.deck=a.deck;
		
	}//closing refillDeck().
	
	
	public Card deal() {
		
		if(isEmpty()==true)
			return null;
		
		int rand =(int)(Math.random()*size());
		//System.out.println(rand);//--------------------------->Used for checking.
		Card c=deck.remove(rand); 
		
		//System.out.println(deck);//-------------------------->Used for checking.
		return c;
		
	}//closing deal().
	
	
	public Card[] deal(int n) {
		
		if(n>=size())
			throw new IllegalArgumentException("Not Enough Cards on Deck.");
		
		Card[] cardsRemoved = new Card[n];
		int rand=(int)(Math.random()*size());
		//System.out.println(rand);//--------------------------->Used for checking.
		Card c;
		for(int i=0;i<n;i++) {
			
			c=deck.remove(rand);
			cardsRemoved[i]=c;
			rand=(int)(Math.random()*size());
			//System.out.println(rand);//--------------------------->Used for checking.
			
		}
		
		//for (Card w:cardsRemoved)//----------------------------->Used for checking.
			//System.out.println(w);//----------------------------->Used for checking.
		//System.out.println(deck);//----------------------------->Used for checking.
		return cardsRemoved;
		
	}//closing deal(int n).

	
}//closing class.
