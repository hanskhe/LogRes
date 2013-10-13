package aStar;

import java.awt.Point;

public class Car {
	private boolean horisontal;
	private int iD;
	private int size;
	
	public Car(int iD, int size, boolean horisontal){
		this.iD = iD;
		this.size = size;
		this.horisontal = horisontal;
		
	}
	
	public boolean iSHorisontal(){
		return horisontal;
	}
	public int getID(){
		return iD;
	}
	public int getSize(){
		return size;
	}
	
	//Funksjoner for flytting. Forklart i første funksjon.
	public Board up(Board board, Point pos){
		//Henter ut bilobjektet fra den posisjonen det har
		Car car = board.board.get(pos.y+size-1).get(pos.x);
		//Sletter bilen fra opprinnelig pos
		board.board.get(pos.y+size-1).set(pos.x,null);
		//Legger inn bilen på rett sted
		board.board.get(pos.y-1).set(pos.x,car);
		//Sender tilbake oppdatert brett.
		return board;
	}
	public Board down(Board board, Point pos){
		Car car = board.board.get(pos.y).get(pos.x);
		board.board.get(pos.y).set(pos.x,null);
		board.board.get(pos.y+size).set(pos.x,car);
		return board;
	}
	public Board left(Board board, Point pos){
		Car car = board.board.get(pos.y).get(pos.x);
		board.board.get(pos.y).set(pos.x+size-1,null);
		board.board.get(pos.y).set(pos.x-1,car);
		return board;
	}
	public Board right(Board board, Point pos){
		Car car = board.board.get(pos.y).get(pos.x);
		board.board.get(pos.y).set(pos.x,null);
		board.board.get(pos.y).set(pos.x+size,car);
		return board;
	}
	
	//Car må beregne hvor langt den må flytte seg for å gi plass
	public int distanceToMove(int x, Board board){
		int y = 2;
		if(board.board.get(1).get(x)==this){
			y=1;
			if (board.board.get(0).get(x)==this){
				y=0;
			}
		}
		//Hvis bilen har størrelse 2 og kan flyttes unna med et enkelt trekk
		if (y==1 && this.size==2){
			return 1;
		}
		return (3-y);
	}
	
}
