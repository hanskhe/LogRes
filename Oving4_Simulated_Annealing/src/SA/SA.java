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
    private float currentBoardObjectiveValue;

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
        this.node = new Board(5,5); //Generate startboard
        this.temp = 1; //Choose a propper starting value
        this.optimalNumberOfEggs = boardX*numberOfEggs;   //This will only work for quadratick boards.

    }

    public static void main(String[] args){
        SA sa = new SA(5,5,2);

        sa.currentBoardObjectiveValue = Board.objectiveFunction(sa.node, sa.numberOfEggs);
        while (Board.objectiveFunction(sa.node, sa.numberOfEggs)<sa.targetValue && sa.temp > 0){
            Board neighbour = sa.node.generateNeighbors();
            float neighbourValue = Board.objectiveFunction(neighbour, sa.numberOfEggs);
            float q = ((sa.currentBoardObjectiveValue-neighbourValue)/neighbourValue);
            double currentP = Math.min(1,(Math.exp((-q/sa.temp))));
            double randX = Math.random(); //Does not allow the number 1 to be chosen, but a number almost infinitly close will be possible.
            if (randX > currentP){
                sa.node = neighbour;
            }
            sa.temp = recalculateTemp(sa.temp, sa.td);






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
    }
}
