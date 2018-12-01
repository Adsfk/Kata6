package kata6;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import java.awt.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class HistogramDisplay extends ApplicationFrame {

    private final Histogram<Integer> histo;

    public HistogramDisplay(Histogram histo) {
        super("Asignatura");
        this.histo = histo;
        setContentPane(createPanel());
        pack();
    }

    public void execute(){
        setVisible(true);
    }

    private ChartPanel createPanel(){
        ChartPanel chart = new ChartPanel(createChart(createDataset()));
        chart.setPreferredSize(new Dimension(500,400));
        return chart;
    }

    private JFreeChart createChart(DefaultCategoryDataset dataSet){
        JFreeChart chart = ChartFactory.createBarChart("Histograma JFreeChart","Dominios email","Nº de emails",
                dataSet,PlotOrientation.VERTICAL, false, false, rootPaneCheckingEnabled);
        return chart;
    }

    private DefaultCategoryDataset createDataset(){
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (Integer key : histo.keySet())
            dataset.addValue(histo.get(key), "", key);
        return dataset;
    }
}