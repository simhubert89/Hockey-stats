package pkg;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Statistics stats = new Statistics(10);
		
		stats.add(new Player("Connor", "McDavid", 97, 44, 79, 28 , 80));
		stats.add(new Player("Austin", "Mathews", 34, 60, 46, 20, 73));
		stats.add(new Player("Steven", "Stamkos", 91, 42, 64, 24 , 81));
		stats.add(new Player("Jonathan", "Huberdeau", 11, 30, 85, 35, 80));
		stats.add(new Player("Johnny", "Gaudreau", 13, 40, 75, 64 , 82));
		stats.add(new Player("Matthew", "Tkachuk", 19, 42, 62, 57 , 82));
		stats.add(new Player("Roman", "Josi", 59, 23, 73, 13 , 80));
		stats.add(new Player("Cale", "Makar", 8, 28, 58, 48 , 77));
		
		System.out.println("--------------------------------------");
		System.out.println("Initial display :" + stats);
		System.out.println("--------------------------------------");
		
		stats.sortPlusMinus();
		System.out.println("\nSort players by +/- : " + stats);
		System.out.println("--------------------------------------");
		
		stats.sortGamesPlayed();
		System.out.println("\nSort players by games played : " + stats);
		System.out.println("--------------------------------------");
		
		stats.sortPPM();
		System.out.println("\nSort players by point per game average : " + stats);
		
	}

}
