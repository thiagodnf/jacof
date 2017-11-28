package benchmark.stats;

import benchmark.visualization.chart.LineChart;
import thiagodnf.jacof.aco.ACO;

public class Diversity {

    private ACO aco;
    private LineChart lineChart;

    private double pheromoneRatio;

    public Diversity(ACO aco) {
        this.aco = aco;
        this.lineChart = new LineChart("Chart");
        lineChart.display();
    }

    public void update() {
        updatePheremoneRatio();
        updateAtractivnessDispersion();
        updateAttracivnessRatio();
    }

    private void updatePheremoneRatio() {
        long countEdgesWithPheromone = 0;
        long countAllEdges = 0;
        double[][] edges = aco.getGraph().getTau();
        double initalPheromoneValue = aco.getGraphInitialization().getT0();
        int x;
        int y;
        double[] currentRow;

        for(x = 0; x < edges.length; x++) {
            currentRow = edges[x];
            for(y = 0; y < currentRow.length; y++) {
                countAllEdges++;
                if(currentRow[y] > initalPheromoneValue) countEdgesWithPheromone++;
            }
        }

        pheromoneRatio = (double)countEdgesWithPheromone/countAllEdges;
        lineChart.update(pheromoneRatio);
    }


    private void updateAtractivnessDispersion() {
    }


    private void updateAttracivnessRatio() {
    }

    public double getPheromoneRatio() {
        return pheromoneRatio;
    }

    public void setPheromoneRatio(double pheromoneRatio) {
        this.pheromoneRatio = pheromoneRatio;
    }
}
