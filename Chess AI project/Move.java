class Move{
  Square start;
  Square landing;
  int score;

  public Move(Square x, Square y){
    start = x;
    landing = y;
  }

  public Move(Square x, Square y, int Score){
    start = x;
    landing = y;
    score = Score;
  }

  public Move(){
    
  }

  public Square getStart(){
    return start;
  }

  public int getScore(){
    return score;
  }

  public Square getLanding(){
    return landing;
  }
}
