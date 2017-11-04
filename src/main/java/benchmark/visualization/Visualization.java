package benchmark.visualization;

import org.moeaframework.core.Algorithm;
import org.moeaframework.core.EvolutionaryAlgorithm;
import org.moeaframework.core.Population;
import org.moeaframework.core.Solution;
import thiagodnf.jacof.aco.ant.Ant;
import tsplib.TSPInstance;
import tsplib.TSPPanel;
import tsplib.Tour;


import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.BasicStroke;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import static java.awt.Color.lightGray;
import static tsplib.Tour.toTour;

/**
 * Created by wojci on 28.10.2017.
 */
public class Visualization {

    private boolean enable;

    private JFrame frame;

    private TSPPanel panel;

    private JTextArea progressText;

    private StringBuilder progress;

    private TSPInstance tspInstance;

    public Visualization(boolean enable) {
        this.enable = enable;
    }

    public void prepareVisualization(TSPInstance tspInstance) {
        if (enable) {
            this.tspInstance = tspInstance;
            panel = new TSPPanel(tspInstance);
            panel.setAutoRepaint(false);

            // create other components on the display
            progress = new StringBuilder();
            progressText = new JTextArea();

            JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
            splitPane.setTopComponent(panel);
            splitPane.setBottomComponent(new JScrollPane(progressText));
            splitPane.setDividerLocation(300);
            splitPane.setResizeWeight(1.0);

            // display the panel on a window
            frame = new JFrame(tspInstance.getName());
            frame.getContentPane().setLayout(new BorderLayout());
            frame.getContentPane().add(splitPane, BorderLayout.CENTER);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(500, 400);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }

    }

    public void updateVisualization(int iteration, Ant globalBest, Ant[] ants) {
        if (enable && frame.isVisible()) {
            List<Tour> tours = new ArrayList<>();
            for (Ant ant : ants) {
                tours.add(toTour(ant));
            }
            updateVisualization(iteration, toTour(globalBest), tours);
        }
    }


    public void updateVisualization(int iteration, Solution globalBest, Population population) {

        if (enable && frame.isVisible()) {
            List<Tour> tours = new ArrayList<>();
            for (Solution solution : population) {
                tours.add(toTour(solution));
            }
            updateVisualization(iteration, toTour(globalBest), tours);
        }

    }

    private void updateVisualization(int iteration, Tour best, List<Tour> tours) {
        // clear existing tours in display
        panel.clearTours();

        // display population with light gray lines
        tours.forEach(tour -> panel.displayTour(tour, lightGray));

        // display current optimal solutions with red line
        panel.displayTour(best, Color.RED, new BasicStroke(2.0f));
        progress.insert(0, "Iteration " + iteration + ": " +
                best.distance(tspInstance) + "\n");
        progressText.setText(progress.toString());

        // repaint the TSP display
        panel.repaint();
    }


    public void updateVisualization(int iteration, Algorithm algorithm) {
        if (enable && algorithm instanceof EvolutionaryAlgorithm) {
            EvolutionaryAlgorithm ea = (EvolutionaryAlgorithm) algorithm;
            this.updateVisualization(iteration, algorithm.getResult().get(0), ea.getPopulation());
        }
    }
}
