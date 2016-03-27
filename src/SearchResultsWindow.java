import java.awt.*;       // Using AWT containers and components
import java.awt.event.*; // Using AWT events and listener interfaces
import javax.swing.*;    // Using Swing components and containers

/**
 * GUI for the Printer Search Program return reuslts, should be initialized from MenuUI by clicking the Filter Results button.
 *
 * @author  CS Club
 * @version 1.0
 */
public class SearchResultsWindow extends JFrame {

    private String searchName, searchRom, searchFinish;
    private Double searchTension, searchCompression, searchImpact, searchPartComplexity, searchLeadTime, searchTolerance;
    private boolean searchEOC;


    /**
     * Default constructor initializing content inside.
     */
    public SearchResultsWindow() {
        Container container = getContentPane();
        container.setLayout(new FlowLayout());
    }

    /**
     * Constructor to initialize frame window for entered paramater results.
     * @param name
     * @param tension
     * @param compression
     * @param impact
     * @param partComplexity
     * @param leadTime
     * @param eoc
     * @param rom
     * @param tolerance
     * @param finish
     */
    public SearchResultsWindow(String name, Double tension, Double compression, Double impact, Double partComplexity,
                               Double leadTime, boolean eoc, String rom, Double tolerance, String finish) {

        searchName = name;
        searchRom = rom;
        searchFinish = finish;
        searchTension = tension;
        searchCompression = compression;
        searchImpact = impact;
        searchPartComplexity = partComplexity;
        searchLeadTime = leadTime;
        searchTolerance = tolerance;
        searchEOC = eoc;


        // Retrieve the content-pane of the top-level container JFrame
        // All operations done on the content-pane
        Container container = getContentPane();

        JPanel finalResults = new JPanel();
        finalResults.setLayout(new FlowLayout());

        container.setLayout(new BorderLayout(0,0));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Exit program if close-window button clicked
        setTitle("Results"); // Sets title
        setSize(800, 450);         // Sets initial size
        setVisible(true);          // Shows
        setLocationRelativeTo(null); // Displays frame in the middle of the screen
    }

}
