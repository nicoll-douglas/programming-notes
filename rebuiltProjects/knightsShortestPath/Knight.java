package rebuiltProjects.knightsShortestPath;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Knight {
  private int[] position;
  
  private final static int[][] STEPS = {
    {1, 2},
    {2, 1},
    {2, -1},
    {1, -2},
    {-1, -2},
    {-2, -1},
    {-2, 1},
    {-1, 2}
  };

  public Knight(int[] position) {
    this.position = position;
  }

  public int[] position() {
    return this.position;
  }

  public static ArrayList<int[]> getPossibleMoves(int[] initialPosition) {
    int x = initialPosition[0];
    int y = initialPosition[1];
    ArrayList<int[]> moves = new ArrayList<>();

    for (int[] step : STEPS) {
      int dx = step[0];
      int dy = step[1];
      int[] move = {x + dx, y + dy};
      
      if (move[0] <= 7 && move[1] <= 7 && move[0] >= 0 && move[1] >= 0) {
        moves.add(move);
      }
    }

    return moves;
  }

  public ArrayList<int[]> getPossibleMoves() {
    return getPossibleMoves(this.position);
  }

  public int shortestNumberOfMoves(int[] targetPosition) {
    HashMap<String, Integer> movesCounter = new HashMap<>();
    movesCounter.put(Arrays.toString(this.position), 0);

    ArrayList<int[]> queue = new ArrayList<>();
    queue.add(this.position);

    while (!(queue.get(0)[0] == targetPosition[0] && queue.get(0)[1] == targetPosition[1])) {
      int[] currentPosition = queue.remove(0);
      ArrayList<int[]> possibleMoves = getPossibleMoves(currentPosition);
      
      possibleMoves.forEach((move) -> {
        String moveKey = Arrays.toString(move);
        if (movesCounter.containsKey(moveKey)) return;

        queue.add(move);
        
        String currentPosMoveKey = Arrays.toString(currentPosition);
        int newMoveCount = movesCounter.get(currentPosMoveKey) + 1;
        movesCounter.put(moveKey, newMoveCount);
      });
    }

    String targetPosKey = Arrays.toString(targetPosition);
    return movesCounter.get(targetPosKey);
  }

}
