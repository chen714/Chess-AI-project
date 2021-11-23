import javax.swing.*;
import java.util.*;

public class AIAgent{
  Random rand;

  public AIAgent(){
    rand = new Random();
  }

/*
  The method randomMove takes as input a stack of potential moves that the AI agent
  can make. The agent uses a random number generator to randomly select a move from
  the inputted Stack and returns this to the calling agent.
*/

  public Move randomMove(Stack possibilities){
    int moveID = rand.nextInt(possibilities.size());
    System.out.println("Agent randomly selected move : "+moveID);
    for(int i=1;i < (possibilities.size()-(moveID));i++){
      possibilities.pop();
    }
    Move selectedMove = (Move)possibilities.pop();
    return selectedMove;
  }

  public Move nextBestMove(Stack wPossibleMoves, Stack bPeices){
    int score = 0;
    Move currHighestMove = null;
    Stack moveScore = new Stack();
    Stack copyBlack;
    Stack copyWPossibleMoves = (Stack)wPossibleMoves.clone();
    Stack copyWPossibleMovesForGameStart = (Stack)wPossibleMoves.clone();
    while(!wPossibleMoves.empty()){
      copyBlack = (Stack)bPeices.clone();
      Move temp = (Move)wPossibleMoves.pop();
      Square landingSq = temp.getLanding();
      while(!copyBlack.isEmpty()){
        Square blackPeice = (Square)copyBlack.pop();
        if((landingSq.getXC() == blackPeice.getXC())&&(landingSq.getYC() == blackPeice.getYC())){
          if(blackPeice.pieceName.contains("Pawn")){
            Move tmpScoreMove = new Move(temp.getStart(),temp.getLanding(),1);
            moveScore.push(tmpScoreMove);
          }else if(blackPeice.pieceName.contains("Knight")){
            Move tmpScoreMove = new Move(temp.getStart(),temp.getLanding(),3);
            moveScore.push(tmpScoreMove);
          }else if(blackPeice.pieceName.contains("Bishop")){
            Move tmpScoreMove = new Move(temp.getStart(),temp.getLanding(),3);
            moveScore.push(tmpScoreMove);
          }else if(blackPeice.pieceName.contains("Rook")){
            Move tmpScoreMove = new Move(temp.getStart(),temp.getLanding(),5);
            moveScore.push(tmpScoreMove);
          }else if(blackPeice.pieceName.contains("Queen")){
            Move tmpScoreMove = new Move(temp.getStart(),temp.getLanding(),9);
            moveScore.push(tmpScoreMove);
          }else if(blackPeice.pieceName.contains("King")){
            Move tmpScoreMove = new Move(temp.getStart(),temp.getLanding(),1000);
            moveScore.push(tmpScoreMove);
          }
        }
      }
    }




    if(moveScore.isEmpty()) {
      while (!copyWPossibleMovesForGameStart.empty()) {
        Move temp = (Move) copyWPossibleMovesForGameStart.pop();
        if ((temp.getStart().getYC() < temp.getLanding().getYC())
                && (temp.getLanding().getXC() == 3) && (temp.getLanding().getYC() == 3)
                || (temp.getLanding().getXC() == 4) && (temp.getLanding().getYC() == 3)
                || (temp.getLanding().getXC() == 3) && (temp.getLanding().getYC() == 4)
                || (temp.getLanding().getXC() == 4) && (temp.getLanding().getYC() == 4)) {
          Move tmpScoreMove = new Move(temp.getStart(), temp.getLanding(), 1);
          moveScore.push(tmpScoreMove);

        }
      }
    }


    if(moveScore.isEmpty()){
      currHighestMove = randomMove(copyWPossibleMoves);
    }else {
      while (!moveScore.empty()) {
        Move temp = (Move) moveScore.pop();
        if (temp.getScore() > score) {
          score = temp.getScore();
          currHighestMove = temp;
        }
      }
    }

    return currHighestMove;
  }

  public Move twoLevelsDeep(Stack wPossibleMoves, Stack bPossibleMoves, Stack bPeices, Stack whitePieces){
    int score = 0;
    Move currHighestMove = null;
    Stack moveScore = new Stack();
    Stack copyBlack;

    Stack copyWPossibleMoves = (Stack)wPossibleMoves.clone();
    Stack copyWPossibleMovesForGameStart = (Stack)wPossibleMoves.clone();
    while(!wPossibleMoves.empty()){
      copyBlack = (Stack)bPossibleMoves.clone();
      Move temp = (Move)wPossibleMoves.pop();
      Square startingSq = temp.start;
      Square landingSq = temp.getLanding();
      while(!copyBlack.isEmpty()){
        Move blackPeice = (Move)copyBlack.pop();
        Square start = blackPeice.start;
        Square landing = blackPeice.getLanding();

        if((startingSq.pieceName.contains("King")) && (startingSq.getXC() == landing.getXC())&&(startingSq.getYC() == landing.getYC())){
          Move tmpScoreMove = new Move(temp.getStart(),temp.getLanding(),10);
          moveScore.push(tmpScoreMove);
        }else if((landingSq.getXC() == start.getXC())&&(landingSq.getYC() == start.getYC())){
          if(start.pieceName.contains("Pawn")){
            Move tmpScoreMove = new Move(temp.getStart(),temp.getLanding(),1);
            moveScore.push(tmpScoreMove);
          }else if(start.pieceName.contains("Knight")){
            Move tmpScoreMove = new Move(temp.getStart(),temp.getLanding(),3);
            moveScore.push(tmpScoreMove);
          }else if(start.pieceName.contains("Bishop")){
            Move tmpScoreMove = new Move(temp.getStart(),temp.getLanding(),3);
            moveScore.push(tmpScoreMove);
          }else if(start.pieceName.contains("Rook")){
            Move tmpScoreMove = new Move(temp.getStart(),temp.getLanding(),5);
            moveScore.push(tmpScoreMove);
          }else if(start.pieceName.contains("Queen")){
            Move tmpScoreMove = new Move(temp.getStart(),temp.getLanding(),9);
            moveScore.push(tmpScoreMove);
          }else if(start.pieceName.contains("King")){
            Move tmpScoreMove = new Move(temp.getStart(),temp.getLanding(),1000);
            moveScore.push(tmpScoreMove);
          }
        }
      }
    }

    if(moveScore.isEmpty()) {
      while (!copyWPossibleMovesForGameStart.empty()) {
        Move temp = (Move) copyWPossibleMovesForGameStart.pop();
        if ((temp.getStart().getYC() < temp.getLanding().getYC())
                && (temp.getLanding().getXC() == 3) && (temp.getLanding().getYC() == 3)
                || (temp.getLanding().getXC() == 4) && (temp.getLanding().getYC() == 3)
                || (temp.getLanding().getXC() == 3) && (temp.getLanding().getYC() == 4)
                || (temp.getLanding().getXC() == 4) && (temp.getLanding().getYC() == 4)) {
          Move tmpScoreMove = new Move(temp.getStart(), temp.getLanding(), 1);
          moveScore.push(tmpScoreMove);

        }
      }
    }


    if(moveScore.isEmpty()){
      currHighestMove = randomMove(copyWPossibleMoves);
    }else {
      while (!moveScore.empty()) {
        Move temp = (Move) moveScore.pop();
        if (temp.getScore() > score) {
          score = temp.getScore();
          currHighestMove = temp;
        }
      }
    }

    return currHighestMove;
  }

  private int determineScore(String pieceName){
    int score = 0;
    if(pieceName.contains("Pawn")){
      score = 1;
    }else if(pieceName.contains("Knight")){
      score = 3;
    }else if(pieceName.contains("Bishop")){
      score = 3;
    }else if(pieceName.contains("Rook")){
      score = 5;
    }else if(pieceName.contains("Queen")){
      score = 9;
    }else if(pieceName.contains("King")){
      score = 1000;
    }
    return score;
  }

}




//  int score = 0;
//  Move currHighestMove = null;
//  Stack moveScore = new Stack();
//  Stack copyBlack;
//  Stack copyWPossibleMoves = (Stack)wPossibleMoves.clone();
//  Stack copyWPossibleMovesForGameStart = (Stack)wPossibleMoves.clone();
//


//    while(!wPossibleMoves.empty()){
//            copyBlack = (Stack)blackPeices.clone();
//            Move temp = (Move)wPossibleMoves.pop();
//            Square landingSq = temp.getLanding(); //white move landing sq
//            while(!copyBlack.isEmpty()){
//            Stack copyBPossibleMovement = (Stack)bPossibleMoves.clone();
//            Square blackPeice = (Square)copyBlack.pop();
//            if((landingSq.getXC() == blackPeice.getXC())&&(landingSq.getYC() == blackPeice.getYC())){
//            if(blackPeice.pieceName.contains("Pawn")){ // white taking blacks pawn
//            while(!copyBPossibleMovement.empty()){
//            Move tmpMove = (Move)copyBPossibleMovement.pop(); // possible black move
//            Square start = tmpMove.start; //black move starting sq
//            Square landing = tmpMove.landing; //black move landing sq
//            if((landing.getYC() == landingSq.getYC()) && (landing.getXC() == landingSq.getXC())) { // if theres a black move that has a landing square matching our landing square for the pawn
//            if ((start.getYC() == landing.getYC()) && (landing.pieceName.contains("Pawn"))) {  //if the black attacking piece is a black pawn and is not in attacking position
//            Move tmpScoreMove = new Move(temp.getStart(), temp.getLanding(), 1); // pawn cant attack unless in attacking position so we score is +1 so we ignore that black move
//            moveScore.push(tmpScoreMove);
//            } else {
//            int blackScore = determineScore(landingSq.pieceName); //what score is the piece that black is taking from white
//            Move tmpScoreMove = new Move(temp.getStart(), temp.getLanding(), 1 - blackScore);
//            moveScore.push(tmpScoreMove);
//            }
//            }
//            }
//            }else if(blackPeice.pieceName.contains("Knight")){ //white taking black knight
//            while(!copyBPossibleMovement.empty()){
//            Move tmpMove = (Move)copyBPossibleMovement.pop(); // possible black move
//            Square start = tmpMove.start; //black move starting sq
//            Square landing = tmpMove.landing; //black move landing sq
//            if((landing.getYC() == landingSq.getYC()) && (landing.getXC() == landingSq.getXC())) { // if theres a black move that has a landing square matching our landing square for the pawn
//            if ((start.getYC() == landing.getYC()) && (landing.pieceName.contains("Pawn"))) {  //if the black attacking piece is a black pawn and is not in attacking position
//            Move tmpScoreMove = new Move(temp.getStart(), temp.getLanding(), 3); // pawn cant attack unless in attacking position so we score is +3 so we ignore that black move
//            moveScore.push(tmpScoreMove);
//            } else {
//            int blackScore = determineScore(landingSq.pieceName); //what score is the piece that black is taking from white
//            Move tmpScoreMove = new Move(temp.getStart(), temp.getLanding(), 3 - blackScore);
//            moveScore.push(tmpScoreMove);
//            }
//            }
//            }
//            }else if(blackPeice.pieceName.contains("Bishop")){
//            while(!copyBPossibleMovement.empty()){
//            Move tmpMove = (Move)copyBPossibleMovement.pop(); // possible black move
//            Square start = tmpMove.start; //black move starting sq
//            Square landing = tmpMove.landing; //black move landing sq
//            if((landing.getYC() == landingSq.getYC()) && (landing.getXC() == landingSq.getXC())) { // if theres a black move that has a landing square matching our landing square for the pawn
//            if ((start.getYC() == landing.getYC()) && (landing.pieceName.contains("Pawn"))) {  //if the black attacking piece is a black pawn and is not in attacking position
//            Move tmpScoreMove = new Move(temp.getStart(), temp.getLanding(), 3); // pawn cant attack unless in attacking position so we score is +3 so we ignore that black move
//            moveScore.push(tmpScoreMove);
//            } else {
//            int blackScore = determineScore(landingSq.pieceName); //what score is the piece that black is taking from white
//            Move tmpScoreMove = new Move(temp.getStart(), temp.getLanding(), 3 - blackScore);
//            moveScore.push(tmpScoreMove);
//            }
//            }
//            }
//            }else if(blackPeice.pieceName.contains("Rook")){
//            while(!copyBPossibleMovement.empty()){
//            Move tmpMove = (Move)copyBPossibleMovement.pop(); // possible black move
//            Square start = tmpMove.start; //black move starting sq
//            Square landing = tmpMove.landing; //black move landing sq
//            if((landing.getYC() == landingSq.getYC()) && (landing.getXC() == landingSq.getXC())) { // if theres a black move that has a landing square matching our landing square for the pawn
//            if ((start.getYC() == landing.getYC()) && (landing.pieceName.contains("Pawn"))) {  //if the black attacking piece is a black pawn and is not in attacking position
//            Move tmpScoreMove = new Move(temp.getStart(), temp.getLanding(), 1); // pawn cant attack unless in attacking position so we score is +1 so we ignore that black move
//            moveScore.push(tmpScoreMove);
//            } else {
//            int blackScore = determineScore(landingSq.pieceName); //what score is the piece that black is taking from white
//            Move tmpScoreMove = new Move(temp.getStart(), temp.getLanding(), 5 - blackScore);
//            moveScore.push(tmpScoreMove);
//            }
//            }
//            }
//            }else if(blackPeice.pieceName.contains("Queen")){
//            while(!copyBPossibleMovement.empty()){
//            Move tmpMove = (Move)copyBPossibleMovement.pop(); // possible black move
//            Square start = tmpMove.start; //black move starting sq
//            Square landing = tmpMove.landing; //black move landing sq
//            if((landing.getYC() == landingSq.getYC()) && (landing.getXC() == landingSq.getXC())) { // if theres a black move that has a landing square matching our landing square for the pawn
//            if ((start.getYC() == landing.getYC()) && (landing.pieceName.contains("Pawn"))) {  //if the black attacking piece is a black pawn and is not in attacking position
//            Move tmpScoreMove = new Move(temp.getStart(), temp.getLanding(), 1); // pawn cant attack unless in attacking position so we score is +1 so we ignore that black move
//            moveScore.push(tmpScoreMove);
//            } else {
//            int blackScore = determineScore(landingSq.pieceName); //what score is the piece that black is taking from white
//            Move tmpScoreMove = new Move(temp.getStart(), temp.getLanding(), 9 - blackScore);
//            moveScore.push(tmpScoreMove);
//            }
//            }
//            }
//            }else if(blackPeice.pieceName.contains("King")){
//            Move tmpScoreMove = new Move(temp.getStart(),temp.getLanding(),1000);
//            moveScore.push(tmpScoreMove);
//            }
//            }
//            }
//            }
//
////    if(moveScore.isEmpty()) {
////      while (!copyWPossibleMovesForGameStart.empty()) {
////        Move temp = (Move) copyWPossibleMovesForGameStart.pop();
////        if ((temp.getStart().getYC() < temp.getLanding().getYC())
////                && (temp.getLanding().getXC() == 3) && (temp.getLanding().getYC() == 3)
////                || (temp.getLanding().getXC() == 4) && (temp.getLanding().getYC() == 3)
////                || (temp.getLanding().getXC() == 3) && (temp.getLanding().getYC() == 4)
////                || (temp.getLanding().getXC() == 4) && (temp.getLanding().getYC() == 4)) {
////          Move tmpScoreMove = new Move(temp.getStart(), temp.getLanding(), 1);
////          moveScore.push(tmpScoreMove);
////
////        }
////      }
////    }
//
//
//            if(moveScore.isEmpty()){
//            JOptionPane.showMessageDialog(null, "random");
//            currHighestMove = randomMove(copyWPossibleMoves);
//            }else {
//            while (!moveScore.empty()) {
//            Move temp = (Move) moveScore.pop();
//            if (temp.getScore() > score) {
//            score = temp.getScore();
//            currHighestMove = temp;
//            }
//            }
//            }
//
//            return currHighestMove;