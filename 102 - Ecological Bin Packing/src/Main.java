import java.util.Scanner;

public class Main {

    //bottles[i][j] is the number of type j bottles in bin i
    private static int[][] bottles = new int[3][3];
    private static char[] bottleType = new char[]{'B', 'G', 'C'};
    private static int[][] permutations = {
            {0, 1, 2},  //bgc
            {0, 2, 1},  //bcg
            {1, 0, 2},  //gbc
            {1, 2, 0},  //gcb
            {2, 0, 1},  //cbg
            {2, 1, 0}   //cgb
    };

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {

            int minMoves = Integer.MAX_VALUE;
            String minCombination = "";

            //fill up grid [bin#1:[b, g, c], bin#2[b, g, c], bin#3[b, g, c]]
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    bottles[i][j] = scanner.nextInt();
                }
            }

            //grab each bin order
            for (int[] binOrder : permutations) {
                int moveCount = 0;
                //grab each bin. the value of the bin corresponds to the type of bottle that goes inside it
                for (int bin : binOrder) {
                    //go through all of the types (0 = b, 1 = g, 2 = c)
                    for (int type = 0; type < 3; type ++) {
                        //count how many bottles start in the bin (binOrder[bin]) that do not share binOrder[bin]'s value (aka type)
                        if (binOrder[bin] != type) {
                            moveCount += bottles[bin][type];
                        }
                    }
                }

                String combination = "" + bottleType[binOrder[0]] + bottleType[binOrder[1]] + bottleType[binOrder[2]];
                //if we have fewer moves or the same moves with a "smaller" bucket order, set it to be our minimum value
                if (moveCount < minMoves || (moveCount == minMoves && combination.compareTo(minCombination) < 0)) {
                    minCombination = combination;
                    minMoves = moveCount;
                }
            }

            System.out.println(minCombination + " " + minMoves);
        }
    }
}
