import java.util.ArrayList;

public class Tile implements Comparable<Tile> {
	double x;
	double y;
	boolean isOccupied = false;
	GameObject gameObject;
	int priority = 0;
	int cost = 1;
	
	ArrayList <Tile> neighbors;
	Tile(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public GameObject getGameObject() {
		return gameObject;
	}
	
	public void setGameObject(GameObject gameObject) {
		this.gameObject = gameObject;
	}
	
	public boolean getIsOccupied() {
		return isOccupied;
	}
	
	public void setIsOccupied(boolean isOccupied) {
		this.isOccupied = isOccupied;
		if (isOccupied) {
			cost = 10000;
		}
		else {
			cost = 1;
		}
	}
	
	public ArrayList<Tile> getNeighbors() {
		return neighbors;
	}
	
	public void setNeighbors (ArrayList <Tile> neighbors) {
		this.neighbors = neighbors;
	}
	
	public int compareTo(Tile tile) {
		if (priority > tile.priority) {
			return 1;
		}
		else {
			return -1;
		}
		
	}
}
