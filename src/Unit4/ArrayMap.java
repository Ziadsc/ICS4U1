package Unit4;


class ArrayMap {
    static final int n = 10;
    static int[][] map = new int[n][n];

    public static void main (String[] args){
    	setBorders();
        map[8][2] = 99;
        printBoard();
    }

    static void printBoard() {
        for(int row=0; row<n; row++) {
            for(int col=0; col<n; col++){
                System.out.printf("%3d", map[row][col]);
            }
            System.out.println();
        }
        for (int i = 0; i < n*3 +2; i++) System.out.print("=");System.out.println();

    }

    static void setBorders() {
        for (int i = 0; i < n; i++) {
            map[i][0] = 10;
            map[0][i] = 10;
            map[n-1][(n-1)-i] = 10;
            map[(n-1)-i][n-1] = 10;
        }
    }

}