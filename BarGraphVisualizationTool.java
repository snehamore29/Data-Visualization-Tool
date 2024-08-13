/*
PROBLRM STATEMENT:-   Create a data visualization tool: Data visualization tools can help users understand complex data sets 
by presenting them in a visual format. You can create a tool that uses data structures like trees and 
graphs to visualize data, or implement algorithms like clustering and dimensionality reduction to 
analyze and present data.
*/
//GROUP NO. 13




import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class DSF {
    String category;
    double value;

    DSF(String category, double value) {
        this.category = category;
        this.value = value;
    }
}

class BarGraph extends JPanel {

    private List<DSF> DSFs;
    private Color[] barColors; // Array to store different colors for bars

    BarGraph(List<DSF> DSFs) {
        this.DSFs = DSFs;
        this.barColors = new Color[]{Color.RED, Color.GREEN, Color.BLUE, Color.MAGENTA};
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int padding = 40;
        int width = getWidth();
        int height = getHeight();
        int barWidth = (width - 2 * padding) / DSFs.size();
        int barSpacing = 6; // Adjust based on your preference

        // Draw X  axis 
        g2d.setColor(Color.BLACK);
        g2d.drawString("Years", width / 2 - 30, height - 5);

        Graphics2D g2dRotated = (Graphics2D) g2d.create();   //Y axis
        g2dRotated.rotate(-Math.PI / 2); // Rotate by -90 degrees
        g2dRotated.drawString("No. of certifications done", -(height / 2) + 30, padding - 15);
        g2dRotated.dispose();



        for (int i = 0; i < DSFs.size(); i++) {
            int x = padding + i * (barWidth + barSpacing);
            int barHeight = (int) (DSFs.get(i).value / 100.0 * (height - 2 * padding));
            int y = height - padding - barHeight;

            g2d.setColor(barColors[i]); // Use different color for each bar
            g2d.fillRect(x, y, barWidth, barHeight);

            // Draw category labels
            g2d.setColor(Color.BLACK);
            g2d.drawString(DSFs.get(i).category, x + barWidth / 2 - 15, height - padding + 15);
        }
    }
}

class PieChart extends JPanel {

    private List<DSF> DSFs;
    private Color[] sectorColors;

    PieChart(List<DSF> DSFs) {
        this.DSFs = DSFs;
        this.sectorColors = new Color[]{Color.RED, Color.GREEN, Color.BLUE, Color.MAGENTA};
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        int padding = 20;
        int width = getWidth();
        int height = getHeight();
        int diameter = Math.min(width, height) - 2 * padding;
        int startX = (width - diameter) / 2;
        int startY = (height - diameter) / 2;

        double total = DSFs.stream().mapToDouble(dp -> dp.value).sum();
        double currentAngle = 0;
        for (int i = 0; i < DSFs.size(); i++) {
            double arcAngle = 360 * (DSFs.get(i).value / total);
            g2d.setColor(sectorColors[i]); // Use different color for each sector
            g2d.fillArc(startX, startY, diameter, diameter, (int) currentAngle, (int) arcAngle);
            currentAngle += arcAngle;
        }
    }
}

public class BarGraphVisualizationTool {   //main class
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("*******TEAM 13th DATA VISUALIZATION TOOL*******");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 400);

            
            List<DSF> DSFs = new ArrayList<>();
            DSFs.add(new DSF("Firat Year", 30));
            DSFs.add(new DSF("Second Year", 50));
            DSFs.add(new DSF("Third Year", 45));
            DSFs.add(new DSF("Final Year", 90));

            
            BarGraph barGraph = new BarGraph(DSFs);       // Create an object of BarGraph

            
            PieChart pieChart = new PieChart(DSFs);     // Create an object of PieChart

            // Create a container panel for both visualizations
            JPanel containerPanel = new JPanel();
            containerPanel.setLayout(new GridLayout(1, 2)); // 1 is row and 2 is columns
            containerPanel.add(barGraph);
            containerPanel.add(pieChart);

            frame.add(containerPanel);

            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
