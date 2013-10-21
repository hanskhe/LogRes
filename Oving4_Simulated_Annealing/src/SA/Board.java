package SA;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: HK
 * Date: 20.10.13
 * Time: 16:37
 * To change this template use File | Settings | File Templates.
 */
public class Board {
    private ArrayList<ArrayList<Boolean>> board;




    public Board(int x, int y){
        board = new ArrayList<ArrayList<Boolean>>();
        for (int i = 0; i<x; i++){
            board.add(new ArrayList<Boolean>());
            for (int j = 0; j<y; i++){
                board.get(i).add(false);
            }
        }

    }

    public Board(ArrayList<ArrayList<Boolean>> board){
         this.board = (ArrayList<ArrayList<Boolean>>) board.clone();
    }


    public Board generateNeighbors(){
        //Chose between adding, removing or moving an egg
        Random random = new Random();
        int Choice = random.nextInt(3);
        switch (Choice){
            case 0 :
                return addEgg();
            case 1 :
                return removeEgg();
            case 2 :
                return moveEgg();
            default:
                return null;
        }
    }


    private Board addEgg(){

        while (true){
            int newX = (int) Math.random() * board.size();
            int newY = (int) Math.random() * board.get(newX).size();
            //Insert egg at random empty space
            if (!(board.get(newX).get(newY))){
                ArrayList<Boolean> temp = board.get(newX);
                temp.set(newY,true);
                board.set(newX,temp);
            }
        }



    }

    private Board removeEgg(){
        while (true){
            int newX = (int) Math.random() * board.size();
            int newY = (int) Math.random() * board.get(newX).size();
            //Insert egg at random empty space
            if ((board.get(newX).get(newY))){
                ArrayList<Boolean> temp = board.get(newX);
                temp.set(newY,false);
                board.set(newX,temp);
            }
        }

    }

    private Board moveEgg(){
        Board newBoard = new Board(board);
        while (true){
            int newX = (int) Math.random() * board.size();
            int newY = (int) Math.random() * board.get(newX).size();
            Random random = new Random();
            //Insert egg at random empty space
            if ((board.get(newX).get(newY))){
                int deltaX = random.nextInt(3)-1;
                int deltaY = random.nextInt(3)-1;
                if (checkLeagality(newX, newY, deltaX, deltaY)){

                    ArrayList<Boolean> temp1 = board.get(newX+deltaX);
                    temp1.set(newY+deltaY, true);

                    ArrayList<Boolean> temp2 = board.get(newX);
                    temp2.set(newY, false);

                    board.set(newX+deltaX, temp1);
                    board.set(newX, temp2);
                    break;
                }
            }
        }
        return newBoard;
    }

    private boolean checkLeagality(int x, int y, int dx, int dy){
        if (x+dx < 0 || x+dx > board.size()-1){
            return false;
        }
        else if (y+dy < 0 || y+dy > board.size()-1){
            return false;
        }
        else if ((board.get(x+dx).get(y+dy))){
           return false;
        }
        else{
            return true;
        }

    }



}

