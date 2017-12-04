package benchmark.output;

import benchmark.output.model.ResultRecord;
import benchmark.problem.AcoTSP;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import org.moeaframework.core.Algorithm;
import org.moeaframework.core.EvolutionaryAlgorithm;
import thiagodnf.jacof.util.ExecutionStats;
import tsplib.TSPInstance;
import tsplib.Tour;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import static tsplib.Tour.toTour;

public class CSV implements Output {

    private String fileName;

    public CSV(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void use(Algorithm algorithm, TSPInstance instance) {

        if (algorithm instanceof EvolutionaryAlgorithm) {
            EvolutionaryAlgorithm ea = (EvolutionaryAlgorithm) algorithm;
            Tour tour = toTour(algorithm.getResult().get(0));

            ResultRecord resultRecord = new ResultRecord();
            resultRecord.setProblemName(instance.getName());
            resultRecord.setAlgorithmName(ea.getClass().getSimpleName());
            resultRecord.setBestResultValue(String.valueOf(tour.distance(instance)));

            try {
                writeRecordToCSV(resultRecord);
            } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void use(ExecutionStats executionStats) {
        if(executionStats.getProblem() instanceof AcoTSP) {
            AcoTSP acoTSP = (AcoTSP) executionStats.getProblem();
            ResultRecord resultRecord = new ResultRecord();
            resultRecord.setProblemName(acoTSP.getTspInstance().getName());
            resultRecord.setAlgorithmName(executionStats.aco.getClass().getSimpleName());
            resultRecord.setBestResultValue(String.valueOf(acoTSP.evaluate(executionStats.bestSolution)));
            resultRecord.setPheromoneRatio(String.valueOf(executionStats.aco.getDiversity().getPheromoneRatio()));

            try {
                writeRecordToCSV(resultRecord);
            } catch (IOException | CsvDataTypeMismatchException | CsvRequiredFieldEmptyException e) {
                e.printStackTrace();
            }

        }
    }

    private void writeRecordToCSV(ResultRecord resultRecord) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        Writer writer = new FileWriter(fileName, true);
        StatefulBeanToCsv beanToCsv = new StatefulBeanToCsvBuilder(writer)
                .withSeparator(';')
                .build();
        beanToCsv.write(resultRecord);
        writer.close();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
