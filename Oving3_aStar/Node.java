package aStar;

import java.util.ArrayList;

import javax.annotation.Generated;

public class Node implements Comparable<Node> {
	private int nodeCost;
	private int estimateCost;
	private int totalCost;
	private boolean isGoal;
	private int status;
	private Node parrent;
	private ArrayList<Node> children;
	private Board board;

	public Node(int nodeCost, Node parrent, Board board){
		this.nodeCost = nodeCost;
		this.parrent = parrent;
		this.board = board;
		this.estimateCost = calcEstimate();
		this.totalCost = this.nodeCost + this.estimateCost;
		if (estimateCost == 0){			
			this.isGoal = true;
		}
		children = new ArrayList<Node>();
		status = board.board.hashCode();
	}

	private int calcEstimate() {
		//Heuristisk estimat. Her anslås kostnaden for å bevege bilen.
		//Det summeres hvor mange trekk som gjennstår for bil 0, og hvor mye flytting som må til for å fjerne bilene forran bil0
		ArrayList<Car> row = this.board.board.get(2);
		int index = 0;
		int score = 0;
		for (int i = 0; i<row.size();i++){
			if(row.get(i) != null && row.get(i).getID()==0){
				index = i+1;
				break;
			}
		}
		score = row.size()-1-index;
		
		for (int i = index+1; i<row.size();i++){
			if(row.get(i)!=null){
				score += row.get(i).distanceToMove(i, board); 
			}
		}
		System.out.println("This node was initiialized with score " + score);
		return score;
	}

	public Board getBoard(){
		return board;
	}
	public int getNodeCost() {
		return nodeCost;
	}

	public void setNodeCost(int nodeCost) {
		this.nodeCost = nodeCost;
		this.totalCost = this.nodeCost + this.estimateCost;
	}

	public int getEstimateCost() {
		return estimateCost;
	}

	public void setEstimateCost(int h) {
		this.estimateCost = estimateCost;
		this.totalCost = this.nodeCost + this.estimateCost;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Node getParrent() {
		return parrent;
	}

	public void setParrent(Node parrent) {
		this.parrent = parrent;
	}

	public ArrayList<Node> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<Node> children) {
		this.children = children;
	}
	
	public boolean getIsGoal(){
		return this.isGoal;
	}
	public int getTotalCost(){
		return this.totalCost;
	}
	
	public ArrayList<Node> PopulateChildren(){
		ArrayList<Board> boardOfChildren = new ArrayList<Board>(board.allBoards());
			for (Board child : boardOfChildren){
				children.add(new Node(this.nodeCost+1, this, child));
			}
			return children;
	}

	@Override
	public int compareTo(Node other) {
		return this.getTotalCost()-other.getTotalCost();
	}
	
	
}
