package benchmark.output.model;

import com.opencsv.bean.CsvBindByPosition;

public class ResultRecord {

    @CsvBindByPosition(position = 0)
    private String problemName;

    @CsvBindByPosition(position = 1)
    private String algorithmName;

    @CsvBindByPosition(position = 2)
    private String bestResultValue;

    @CsvBindByPosition(position = 3)
    private String pheromoneRatio;

    public String getProblemName() {
        return problemName;
    }

    public void setProblemName(String problemName) {
        this.problemName = problemName;
    }

    public String getAlgorithmName() {
        return algorithmName;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public String getBestResultValue() {
        return bestResultValue;
    }

    public void setBestResultValue(String bestResultValue) {
        this.bestResultValue = bestResultValue;
    }

    public String getPheromoneRatio() {
        return pheromoneRatio;
    }

    public void setPheromoneRatio(String pheromoneRatio) {
        this.pheromoneRatio = pheromoneRatio;
    }
}
