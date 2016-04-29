package core;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumnModel;

/**
 * Displays a table preview of the data and opens a print dialog.
 * 
 * @author  Marcinina ALvaran, Alireza Bahremand
 * @version 1.0
 */
public class PrintDocument extends JFrame
{
    private static final long serialVersionUID = 8431149564338550049L;
    
    JTable printerTable;
    JPanel tablePanel = new JPanel();
    
    ArrayList<Printer> printerList;
    boolean filtered;
    
    static final int TOTAL_PARAMETERS = 8;
    static final String[] header =
            {"PRINTER", "VENDOR", "TENSION (ksi)", "COMPR. (ksi)",
            "IMPACT (lb-ft)", "MATERIALS", "TOL. (in)", "FINISH (\u00B5in)"};
    
    // Declared as a class variable due to use in both designFrame and formatTable
    static final int[] columnWidths = {120,  // Printer name
                                        90,  // Vendor
                                        85,  // Tension
                                        82,  // Compression
                                        85,  // Impact
                                        80,  // Materials
                                        65,  // Tolerance
                                        75}; // Finish
    
    /**
     * Creates a preview window with an empty table.
     */
    public PrintDocument()
    {
        printerTable = new JTable();
        designFrame();
        setContentPane(printerTable);
        pack();
        setVisible(true);
    }
    
    /**
     * Creates a preview window with table containing the specified printer data.
     * 
     * @param printerList the ArrayList<Printer> with the data to add to the table
     * @param filtered    the boolean that describes if the list is search filtered
     */
    public PrintDocument(ArrayList<Printer> printerList, boolean filtered)
    {
        this.printerList = printerList;
        this.filtered = filtered;
        designFrame();
        fillTable();
        setContentPane(new JScrollPane(printerTable));
        pack();
        setVisible(true);
    }
    
    private void designFrame()
    {
        int frameHeight = (int) (MenuWindow.FRAME_HEIGHT*0.8);
        int frameWidth = 0;

        setTitle("Table Preview");
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        for (int column = 0; column < TOTAL_PARAMETERS; column++)
                frameWidth += columnWidths[column];
        
        // Added 25 to take into account space occupied by border
        setMinimumSize(new Dimension(frameWidth+25, frameHeight));
        setResizable(false);
    }
    
    /**
     * Adds printer list data to a JTable.
     */
    private void fillTable()
    {
        Printer currentPrinter;
        int listSize = printerList.size();
        
        String[][] data = new String[listSize][TOTAL_PARAMETERS];
        
        // Fill table with printer data
        for(int row = 0; row < listSize; row++)
        {
            if (!filtered)
                currentPrinter = printerList.get(row);
            else
                currentPrinter = printerList.get((listSize-1)-row);
            
            // Fill cell data
            for (int column = 0; column < TOTAL_PARAMETERS; column++)
                fillCells(currentPrinter, data, row, column);
        }
        
            // Set up table panel
            printerTable = new JTable(data, header);
            formatTable();
            printerTable.setVisible(true);
            tablePanel.add(new JScrollPane(printerTable));
            tablePanel.setOpaque(true);
            tablePanel.setVisible(true);
    }
    
    /**
     * Add printer data to a two-dimension String array.
     * 
     * @param currentPrinter  the Printer whose data will be added to the table
     * @param data            the String array to store the data
     * @param row             the row index in the String array
     * @param column          the column index in the String array
     */
    private void fillCells(Printer currentPrinter, String[][] data,
            int row, int column)
    {
        String open, close;

        open = "<html><font color=\"rgb(0,200,0)\"><b>";
        close = "</b></font></html>";
        
        /* Matches array Index Reference:
         * 
         *  0 = Tension
         *  1 = Compression
         *  2 = Impact
         *  3 = vendor
         *  4 = Range of Mats.
         *  5 = Tolerance
         *  6 = Finish
         */
        switch (column)
        {
        case 0: data[row][column] = currentPrinter.getPrinterName();
            break;
        case 1:
            if (filtered && currentPrinter.getMatches()[3] > 0)
                data[row][column] = open + currentPrinter.getVendor() + close;
            else
                data[row][column] = currentPrinter.getVendor();
            break;
        case 2: 
            if (filtered && currentPrinter.getMatches()[0] > 0)
                data[row][column] = open + currentPrinter.getTension() + close;
            else
                data[row][column] = currentPrinter.getTension() + "";
            break;
        case 3:
            if (filtered && currentPrinter.getMatches()[1] > 0)
                data[row][column] = open + currentPrinter.getCompression() + close;
            else
                data[row][column] = currentPrinter.getCompression() + "";
            break;
        case 4:
            if (filtered && currentPrinter.getMatches()[2] > 0)
                data[row][column] = open + currentPrinter.getImpact() + close;
            else
                data[row][column] = currentPrinter.getImpact() + "";
            break;
        case 5: 
            if (filtered && currentPrinter.getMatches()[4] > 0)
                data[row][column] = open + currentPrinter.materialsString() + close;
            else
                data[row][column] = currentPrinter.materialsString() + "";
            break;
        case 6:
            if (filtered && currentPrinter.getMatches()[5] > 0)
                data[row][column] = open + currentPrinter.getTolerance() + close;
            else
                data[row][column] = currentPrinter.getTolerance() + "";
            break;
        case 7:
            if (filtered && currentPrinter.getMatches()[6] > 0)
                data[row][column] = open + currentPrinter.getFinish() + close;
            else
                data[row][column] = currentPrinter.getFinish() + "";
            break;
        default: System.out.println("Invalid column number.");
        }
    }
    
    /**
     * Adjust columns widths and text centering of table.
     */
    private void formatTable()
    {
        DefaultTableCellRenderer centerCell = new DefaultTableCellRenderer();
        TableColumnModel columnModel;
        
        // Set cell dimensions
        printerTable.setRowHeight(40);
        columnModel = printerTable.getColumnModel();
        for(int column = 0; column < TOTAL_PARAMETERS; column++)
            columnModel.getColumn(column).setMaxWidth(columnWidths[column]);
        
        // Center text in cells
        centerCell.setHorizontalAlignment(SwingConstants.CENTER);
        printerTable.setDefaultRenderer(Object.class, centerCell);
    }
    
    /**
     * Displays a print dialog for the printer table.
     */
    public void print()
    {
        MessageFormat header, footer;
        
        // Format print page
        header = new MessageFormat("Printer List");
        footer = new MessageFormat("{0}");
        
        // Print the table of printers
        try {
            printerTable.print(JTable.PrintMode.NORMAL, header, footer);
        } catch (PrinterException ex) {     // In case job doesn't work, error dialog box.
            /* The job did not successfully complete */
            String message = "\"There was an error!\"\n"
                    + "Please try restarting the application!\n";
            JOptionPane.showMessageDialog(new JFrame(), message, "Dialog",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Returns a table for printer data
     * 
     * @return a JTable for printer data
     */
    public JTable getPrinterTable(){
        return printerTable;
    }
}
