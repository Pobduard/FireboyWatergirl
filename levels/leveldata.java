package levels;

public class leveldata{
	private int lvlData[][];
	
	public leveldata(int[][] data){
		this.lvlData = data;
	}

	public int[][] getLvlData(){
		return this.lvlData;
	}
}
