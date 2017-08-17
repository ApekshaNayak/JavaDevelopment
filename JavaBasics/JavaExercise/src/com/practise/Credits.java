/**
 * 
 */
package com.practise;

/**
 * @author Apeksha
 *
 */
public class Credits {

	String script = "Enjoy.It's good.Not great.But seeing that you are a business man, "
			+ "Mr. Santiago.Captain.Captain.My associates and I are willing to negotiate, a reasonable price."
			+ " Let's say, 100,000.One million for the entire take, Not a penny less."
			+ " A steep price for a small catch.A pod of 20,000 sharks are  migrating in this direction. "
			+ "When we're through with it, One million will look like a bargain."
			+ "If you're looking to negotiate, Mr. almer, look for an insurance salesman. "
			+ "I do believe we have a deal. All hands on deck. All hands on deck. "
			+ "Tie the sails! Get the fins to the gally! Captain there is a north wind hitting us fast! "
			+ "You need to get up here. I think this is for you captain. The deposit. "
			+ "You'll receive the rest when we dock. You know the most important thing I learned out here?"
			+ " We shouldn't be afraid of the sharks. They are the ones that should be afraid of us. Salud."
			+ " What the hell is that? They're called waves. They do that from time to time. "
			+ "The storm is too strong. We are going to have to go around it. Make it happen."
			+ " I'm going down below. Captain! Change of plans Captain. "
			+ "I'm going to take the money, your  cargo, and what's left of the ship. "
			+ "Aah! I don't think so. Crazy waves out there today. Something unnatural about it. "
			+ "There's something unnatural about everything. Lots of scenery today."
			+ " I'm gonna focus on the waves. You better hurry mate. The clouds are coming in."
			+ " Whoo! Reminds me of the nationals in 04. Remember? You totally ripped that bugger. "
			+ "I thought you were going to be rag dolled for sure. You know we really don't talk like that, right? "
			+ "You doing a ride-out or not? Sure. Thanks. It's totally cooking out here mate. You're not kidding."
			+ " It's a Mexican hurricane. Bad for them good for us. Isn't that a drink? "
			+ "Hit. Hit! Whoo! Good Morning California this is Joni Waves. That's Joni with with an \"I\". "
			+ "You may have noticed this morning the unusual weather here in Southern California. "
			+ "The clouds  Hey get outta the water! SHARKS!";

	String filmName = "Sharknado";
	int year = 2013;
	String director = "Anthony Ferrante";
	String role1 = "Fin";
	String actor1 = "Ian Ziering";
	String role2 = "April";
	String actor2 = "Tara Reid";
	String role3 = "George";
	String actor3 = "John Heard";
	String role4 = "Nova";
	String actor4 = "Cassie Scerbo";
	
	private void display() {
		System.out.println(filmName+"("+year+")"+"\nMovie credits: \n"
				+ "A "+director+" film.\n\n"
				+role1+"\t"+actor1+"\n"
				+role2+"\t"+actor2+"\n"
				+role3+"\t"+actor3+"\n"
				+role4+"\t"+actor4+"\n");
	}
	private int position() {
		String findString = "You better hurry mate.";
		int position = script.indexOf(findString);
		return position;
	}
	
	public static void main(String[] args) {
		Credits credit = new Credits();
		System.out.println("The string positin is: "+credit.position());
		credit.display();
	}
	
	
	}
