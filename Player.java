package pkg;

public class Player {
	private String firstName;
	private String lastName;
	private int number;
	private int goals;
	private int passes;
	private int plusMinus;
	private int gamesPlayed;
	
	public Player(String firstName, String lastName, int number, int goals, int passes, int plusMinus, int gamesPlayed)
	{
		this.firstName = firstName;
		this.lastName = lastName;
		this.number = number;
		this.goals = goals;
		this.passes = passes;
		this.plusMinus = plusMinus;
		this.gamesPlayed = gamesPlayed;
	}
	
	public int getNumber() { return number ;}
	
	public int getGoals() { return goals ; }
	
	public int getPasses() { return passes ; }
	
	public int getPoints() {	return goals + passes;}
	
	public int getPlusMinus() {	return plusMinus;}
	
	public int getGamesPlayed() {return gamesPlayed;}
	
	public double getPPM() {return (double) getPoints() / gamesPlayed; }
	
	@Override
	public String toString() {
		return "#" + number + " " + firstName + " " + lastName + ":\t" + goals + " goals\t" + passes + " passes\t" + getPoints() + " points\t" + 
				getPlusMinus() + " (+/-)\t" + getGamesPlayed() + " parties \t" + getPPM() + " PPM";
	}
}
