
public class Card implements Comparable {

	private String rank;
	private String suit;
	private int value;
	
	public Card(String r, String s, int v) {
		rank=r;
		suit=s;
		value=v;
	}//closing constructor.
	
	public String getRank() {return rank;}
	
	public String getSuit() {return suit;}
	
	public int getValue() {return value;}
	
	public boolean match(Card c){ return rank.equals(c.rank); }

	public void setValue(int n){ value=n; }

	
	public int compareTo(Object o) {
		Card c=(Card)o;
		if(value<c.getValue())
			return -1;
		if(value>c.getValue())
			return 1;
		return 0;
		
	}//closing compareTo.
	
	public boolean equals(Object o) {
		Card c=(Card)o;
		
		if(value==c.getValue())
			return true;
		return false;
		
	}//closing equals.
	
	public String toString() { return rank + " of " + suit;}

	
}
