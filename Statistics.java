package pkg;

public class Statistics {
	private Player[] players;
	private int nbPlayers;
	
	public Statistics(int size) {
		players = new Player[size];
		nbPlayers = 0;
	}
	
	public boolean add(Player player) {
		if(nbPlayers == players.length)
			increaseSize();
		
		if(find(player.getNumber()) == null) {
			players[nbPlayers++] = player;
			return true;
		}
		return false;
	}
	
	
	public Player delete(int number) {
		for(int i=0; i < nbPlayers; i++)
			if(players[i].getNumber() == number) {
				for(int j=i; j<nbPlayers - 1; j++)
					players[j] = players[j + 1];
				players[nbPlayers-1] = null;
				nbPlayers--;
				return players[i];
			}
		return null;
	}
	
	
	private void increaseSize() {
		Player[] temp = new Player[players.length*2];
		for(int i=0; i < players.length; i++)
			temp[i] = players[i];
		players=temp;
	}
	
	
	public Player find(int number) {
		for (int i=0; i < nbPlayers; i++)
			if( players[i].getNumber() == number)
				return players[i];
		return null;
	}
	
	public Player binarySearch(int number) {
		sortNumbersByInsert();
		return binarySearch(number, 0, nbPlayers - 1);
	}
	
	private Player binarySearch(int number, int lower, int sup) {
		int center = (lower+sup) /2;
		
		System.out.println("Center:" + center + " lower:" + lower + " sup:" + sup);
		
		if(players[center].getNumber() == number)
			return players[center];
		else if (sup - lower == 1)
			return null;
		else if (players[center].getNumber() > number)
			return binarySearch(number, center+1, sup);
		else
			return binarySearch(number, lower, center-1);
	}
	
	@Override
	public String toString() {
		String s= "";
		for(int i=0; i < nbPlayers; i++)
			s += "\n" + players[i];
		return s;
	}
	
	public void sortGoalsByBubble() {
		Player temp;
		
		for(int i= nbPlayers -1; i > 0; i--)
			for(int j=0; j < i; j++)
				if(players[j].getNumber() < players[j+1].getNumber()) // swap
				{
					temp = players[j];
					players[j] = players[j+1];
					players[j+1] = temp;
				}
	}
	
	public void sortPassesBySelection() {
		Player temp;
		int max;
		
		for(int i=0; i < nbPlayers-1; i++) {
			max = i;
			
			for(int j = i+1; j < nbPlayers; j++)
				if(players[j].getPasses() > players[max].getPasses())
					max = j;
			temp = players[i]; 
			players[i] = players[max];
			players[max] = temp;
		}
	}
	
	public void sortPointsByInsert() {
		Player player;
		int j;
		
		for(int i=1; i < nbPlayers; i++)
		{
			player = players[i];
			j = i;
			
			while (j>0 && players[j-1].getPoints() <= player.getPoints())
			{
				players[j] = players[j-1];
				j--;
			}
			players[j] = player;
			
			
		}
	}
	
	public void sortPlusMinus() {
		Player[] workspace = new Player[nbPlayers];
		sortRecursivePlusMinus(workspace, 0, nbPlayers - 1);
	}
	
	private void sortRecursivePlusMinus(Player[] workspace, int lower, int sup) {
		if(lower == sup)
			return;
		else {
			int center = (lower+sup) /2;
			sortRecursivePlusMinus(workspace,lower,center);
			sortRecursivePlusMinus(workspace, center + 1, sup);
			merge(workspace, lower, center + 1, sup);
		}
	}
	
	private void merge(Player[] workspace, int ptrLowerTab, int ptrSupTab, int sup) {
		int j=0;
		int lower = ptrLowerTab;
		int center = ptrSupTab -1 ;
		int n = sup - lower + 1;
		
		while (ptrLowerTab <= center && ptrSupTab <= sup) {
			if( players[ptrLowerTab].getPlusMinus() > players[ptrSupTab].getPlusMinus())
				workspace[j++] = players[ptrLowerTab++];
			else
				workspace[j++] = players[ptrSupTab++];
		}
		
		while (ptrLowerTab <= center)
			workspace[j++] = players[ptrLowerTab++];
		
		while (ptrSupTab <= sup)
			workspace [j++] = players[ptrSupTab++];
		for(j=0; j < n; j++)
			players[lower + j] = workspace[j];
		
	}
	
	public void sortGamesPlayed()
	{
		int i, j;
		Player temp;
		
		int h = 1;
		
		while(h <= nbPlayers / 3)
			h = h * 3 + 1;
		
		while(h > 0)
		{
			for(i =h; i < nbPlayers; i++)
			{
				temp = players[i];
				j = i;
				
				while( j > h -1 && players[j - h].getGamesPlayed() <= temp.getGamesPlayed())
					
				{
					players[j] = players[j - h];
					j -= h;
				}
				players[j] = temp;
			}
			h = (h-1) / 3;
		}
	}
	
	public void sortPPM() {
		sortRecursivePPM(0, nbPlayers -1);
	}
	
	private void sortRecursivePPM(int lower, int sup) {
		int n = sup - lower + 1;
		
		if(n <= 3)
			sortManual(lower, sup);
		else {
			double centerline = medianeDeTrois(lower, sup);
			int partition = partition(lower, sup, centerline);
			sortRecursivePPM(lower, partition -1);
			sortRecursivePPM(partition +1, sup);
		}
	}
	
	private int partition(int lower, int sup, double pivot) {
		int ptrLower = lower;
		int ptrSup = sup -1 ;
		
		while(true ) {
			while (players[++ptrLower].getPPM() > pivot);
			while (players[--ptrSup].getPPM() < pivot);
			
			if(ptrLower >= ptrSup)
				break;
			else
				swap(ptrLower, ptrSup);
		}
		swap(ptrLower, sup-1);
		return ptrLower;
		
	}
	
	private void sortManual(int lower, int sup) {
		int n= sup - lower  + 1;
		
		if(n <= 1)return;
		
		if(n == 2)
		{
			if(players[lower].getPPM() < players[sup].getPPM())
				swap(lower, sup);
			return;
		}
		else {
			if(players[lower].getPPM() < players[sup -1].getPPM())
				swap(lower, sup -1);
			if(players[lower].getPPM() < players[sup].getPPM())
				swap(lower, sup);
			if(players[sup -1].getPPM() < players[sup].getPPM())
				swap(sup -1, sup );
		}
	}
	
	private void swap(int a, int b)
	{
		Player temp = players[a];
		players[a] = players[b];
		players[b] = temp;	
		}
	
	private double medianeDeTrois(int inf, int sup) {
		int center = (inf + sup) / 2;
		
		if(players[inf].getPPM() < players[center].getPPM())
			swap(inf, center);
		if(players[inf].getPPM() < players[sup].getPPM())
			swap(inf, sup);
		if(players[center].getPPM() < players[sup].getPPM())
			swap(center, sup);
		swap (center, sup -1);
		return players[sup -1].getPPM();
		
	}
	
	public void sortNumbersByInsert() {
		Player player;
		int j;
		
		for(int i =1; i < nbPlayers; i++) {
			player = players[i];
			j = i;
			
			while ( j > 0 && players[ j -1].getNumber() <= player.getNumber())
			{
				players[j] = players[j -1 ];
				j--;
			}
			
			players[j] = player;
		}
	}
	
	// THANKS FOR WATCHING <3
	
 }
