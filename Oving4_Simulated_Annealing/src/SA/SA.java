package SA;


import java.util.ArrayList;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: HK
 * Date: 20.10.13
 * Time: 16:48
 * To change this template use File | Settings | File Templates.
 */
public class SA {
    private Board node;
    private float temp;
    private float td;
    private float prop;
    private float targetValue;
    private double currentBoardObjectiveValue;

    private int boardX;
    private int boardY;
    private int numberOfEggs;

    private int optimalNumberOfEggs;



    private static float recalculateTemp(float t, float dt){
        return t-dt;
    }

    public SA(int boardX, int boardY, int numberOfEggs){
        this.boardX = boardX;
        this.boardY = boardY;
        this.numberOfEggs = numberOfEggs;
        this.node = new Board(boardX,boardY,numberOfEggs); //Generate startboard
        this.temp = 1; //Choose a propper starting value
		this.targetValue = 1;
		this.td = (float)0.0000001;
		Board.k = numberOfEggs;
		Board.optimalNumberofEggs = boardX*numberOfEggs;

    }

    public static void main(String[] args){
		//debugvar
		int explored = 0;
		int correctChoice = 0;
		//Initiate the problem
        SA sa = new SA(8,8,1);
		//Find score of the current board
        sa.currentBoardObjectiveValue = Board.objectiveFunction(sa.node, sa.numberOfEggs);
		System.out.println(sa.currentBoardObjectiveValue);
		//Debug variable
		double x = Board.objectiveFunction(sa.node, sa.numberOfEggs);

		System.out.println("Temp: " + sa.temp + " && Value: " + x);
		//As long as we are below the target value, and the temp has not reached 0:
        while (x<sa.targetValue && sa.temp > 0){
			//Generate one new neighbor
            Board neighbour = sa.node.generateNeighbors();
			//Find the score for the neighbor
            double neighbourValue = Board.objectiveFunction(neighbour, sa.numberOfEggs);
            //System.out.println("NeighborValue from Main: " + neighbourValue);
			//Calc the diff in score
			double q = ((sa.currentBoardObjectiveValue-neighbourValue));
			System.out.println("q: " + q);
			//Calc the function for p
            double currentP = Math.min(1.0,(Math.exp((q/(double)sa.temp))));
			//Generate random number
            double randX = Math.random(); //Does not allow the number 1 to be chosen, but a number almost infinitly close will be possible.
			//If the given solution is better than the one we had, chose it
			if (q<0){
				sa.node = neighbour;
				sa.currentBoardObjectiveValue = neighbourValue;
				x = neighbourValue;
				correctChoice++;
			}
			//If it is worse, give it a chance to be chosen
			else if (randX > currentP){
                sa.node = neighbour;
				sa.currentBoardObjectiveValue = neighbourValue;
				x = neighbourValue;
				explored++;
			}
			//Recalc temp
            sa.temp = recalculateTemp(sa.temp, sa.td);

			System.out.println("Temp: " + sa.temp + " && CurrentScore: " + x);
			//System.out.println(sa.node.getBoard());



			//First solution:


            //ArrayList<Board> neighbors = sa.board.generateNeighbors();
            //ArrayList<Float> neighborsValues = new ArrayList<Float>();
            //float pMax = 0;
            //int iMax = 0;
            // for (int i = 0; i<neighbors.size(); i++){
            //   float p = objectiveFunction(neighbors.get(i));
            //   neighborsValues.add(p);
            //   if (p > pMax){
            //       pMax = p;
            //       iMax = i;
            //    }
//            float q = (objectiveFunction(neighbors.get(iMax))-objectiveFunction(sa.board))/objectiveFunction(sa.board);
//            double currentP = Math.min(1,(Math.exp((-q/sa.temp))));
//            double randX = Math.random(); //Does not allow the number 1 to be chosen, but a number almost infinitly close will be possible.
//            if (randX>currentP){
//                sa.board = neighbors.get(iMax);
//            }
//            else{
//                Random rnd = new Random();
//                sa.board = neighbors.get(rnd.nextInt(neighbors.size()));
//            }
//            sa.temp = recalculateTemp(sa.temp, sa.td);
//
//            }
        }
		System.out.println("SA-Algo finished");
		System.out.println(sa.node.getBoard());
		System.out.println(Board.optimalNumberofEggs);
		System.out.println("Explored #: " + explored);
		System.out.println("Correct #: " + correctChoice);
    }
}