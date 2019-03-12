import java.util.Scanner;

public class Pyramid {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the height of a single triangle (must be at least 2): ");
        String userInputHeight = scanner.next();

        System.out.print("Enter number of levels: ");
        String userInputLevels = scanner.next();

        System.out.println();

        int triangleHeight = 0;
        int numLevels = 0;

        // Check that both inputs are numbers
        try {
            triangleHeight = Integer.parseInt(userInputHeight);
            numLevels = Integer.parseInt(userInputLevels);
        } catch (NumberFormatException e) {
            System.out.println("You may only enter numbers");
            System.exit(1);
        }

        // Check numbers are valid to build a triangle
        if (triangleHeight < 2 || numLevels < 1) {
            System.out.println("Invalid numbers.");
            System.exit(1);
        } else {
            pyramid(triangleHeight, numLevels, numLevels);
        }
    }

    /**
     * Recursivly builds a level of triangles starting from the top of the pyramid
     *
     * @param triangleHeight the height of a single triangle that forms part of a row
     * @param numLevels the number of rows the pyramid will consist of
     * @param currentLevel the current row to be built
     */
    private static void pyramid(int triangleHeight, int numLevels, int currentLevel) {

        if(currentLevel <= 0) {
            return;
        }

        int numTriangles = numLevels - currentLevel;
        int rowShift = triangleHeight * (currentLevel - 1);

        // The first triangle of any row above level 1 must
        // be shifted before the algorithm can resume
        for (int row = 0; row < triangleHeight; row++) {

            triangle(triangleHeight, rowShift, row, true);

            for(int i = 0; i < numTriangles; i++) {
                triangle(triangleHeight, rowShift, row, false);
            }

            System.out.println();
        }

        pyramid(triangleHeight, numLevels, currentLevel - 1);
    }

    /**
     * Builds a single triangle of a given height and width
     *
     * @param triangleHeight the height of the triangle, height is width * 2
     * @param rowShift the spaceing to shift the level to the right
     * @param row the current row of the triangle level being built
     * @param firstTriangle a flag to indicate the first triangle being built, used for shifting
     */
    private static void triangle(int triangleHeight, int rowShift, int row, boolean firstTriangle) {

        int triangleWidth = (triangleHeight * 2) + rowShift;

        for (int col = 0; col < triangleWidth; col++) {

            int leftTriangleEdge = rowShift + triangleHeight - row - 1;
            int rightTriangleEdge = rowShift + triangleHeight + row - 1;

            if (col == leftTriangleEdge) {
                System.out.print("/");
            }

            if (col == rightTriangleEdge) {
                System.out.print("\\");
            }

            boolean bottomCanvas = (row == (triangleHeight - 1));
            boolean insideTriangle = (leftTriangleEdge <= col) && (col < rightTriangleEdge);
            boolean shiftSpacing = firstTriangle && (col < rowShift);
            boolean leftSpacing = (rowShift <= col) && (col < leftTriangleEdge);
            boolean rightSpacing = rightTriangleEdge < (col - 1);

            if (bottomCanvas && insideTriangle) {
                System.out.print("_");
            } else if (shiftSpacing || leftSpacing || insideTriangle || rightSpacing) {
                System.out.print(" ");
            }
        }
    }

}