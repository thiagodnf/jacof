package tsplib;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EdgeMultiWeightMatrix extends DistanceTable {

    private List<EdgeWeightMatrix> edgeWeightMatrices = new ArrayList<>();
    private int matrixDimension;
    private int numberOfMatrixes;

    public EdgeMultiWeightMatrix(int matrixDimension, int numberOfMatrixes) {
        this.matrixDimension = matrixDimension;
        this.numberOfMatrixes = numberOfMatrixes;
    }

    @Override
    public int[] listNodes() {
        int[] nodes = new int[matrixDimension];

        for (int i = 1; i <= matrixDimension; i++) {
            nodes[i - 1] = i;
        }

        return nodes;
    }

    @Override
    public int[] getNeighborsOf(int id) {
        int index = 0;
        int[] neighbors = new int[matrixDimension - 1];

        if ((id < 1) || (id > matrixDimension)) {
            throw new IllegalArgumentException("no node with identifier " + id);
        }

        for (int i = 1; i <= matrixDimension; i++) {
            if (i != id) {
                neighbors[index++] = i;
            }
        }

        return neighbors;
    }


    @Override
    public double getDistanceBetween(int id1, int id2) {

        return edgeWeightMatrices.stream()
                .map(edgeWeightMatrix -> edgeWeightMatrix.getDistanceBetween(id1, id2))
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    @Override
    public void load(BufferedReader reader) throws IOException {
        for (int i = 0; i < numberOfMatrixes; i++) {
            EdgeWeightMatrix edgeWeightMatrix = new EdgeWeightMatrix(matrixDimension, EdgeWeightFormat.FULL_MATRIX);
            edgeWeightMatrix.load(reader);
            edgeWeightMatrices.add(edgeWeightMatrix);
            reader.readLine();
        }
    }
}
