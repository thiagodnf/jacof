package benchmark.visualization.chart;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.ApplicationFrame;

public class LineChart extends ApplicationFrame{

    private XYSeriesCollection dataset;
    private XYSeries series;
    private double iteration;

    public LineChart(String applicationTitle) {
        super(applicationTitle);
        this.dataset = new XYSeriesCollection();
        this.series = new XYSeries("PheromoneRatio");
        this.iteration = 0;
        dataset.addSeries(series);
    }

    public void update(double pheremoneRatio) {
        series.add(iteration, pheremoneRatio);
        iteration++;
    }

    public void display() {
        JFreeChart lineChart = ChartFactory.createXYLineChart(
                "PheromoneRatio by Iterations",
                "Iteration",
                "PheronomeRatio",
                dataset);
        ChartPanel chartPanel = new ChartPanel( lineChart );
        chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
        setContentPane( chartPanel );
        pack();
        setVisible(true);
    }
}
