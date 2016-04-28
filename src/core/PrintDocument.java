package core;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.print.PrinterException;
import java.text.MessageFormat;

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
    
    static final int TOTAL_PARAMETERS = 8;
    static final String[] header =
            {"PRINTER", "VENDOR", "TENSION (ksi)", "COMPRESSION (ksi)",
            "IMPACT (lb-ft)", "MATERIALS", "TOLERANCE (in)", "FINISH (\u00B5in)"};
    
    // Declared as a class variable due to use in both designFrame and formatTable
    static final int[] columnWidths = {120,  // Printer name
                                        90,  // Vendor
                                        85,  // Tension
                                       120,  // Compression
                                        85,  // Impact
                                        80,  // Materials
                                       100,  // Tolerance
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
     * @param printerList the PrinterList with the data to add to the table
     */
    public PrintDocument(PrinterList printerList)
    {
        designFrame();
        fillTable(printerList);
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
        
        // Added 30 to take into account space occupied by border
        setMinimumSize(new Dimension(frameWidth+25, frameHeight));
        setResizable(false);
    }
    
    private void fillTable(PrinterList printerList)
    {
        Printer currentPrinter;
        int listSize = printerList.getNumberOfPrinters();
        
        String[][] data = new String[listSize][TOTAL_PARAMETERS];
        
        // Fill table with printer data
        for(int row = 0; row < listSize; row++)
        {
            currentPrinter = printerList.getPrinter(row);
            
            for (int column = 0; column < TOTAL_PARAMETERS; column++)
            {
                switch (column)
                {
                case 0: data[row][column] = currentPrinter.getPrinterName();
                    break;
                case 1: data[row][column] = currentPrinter.getVendor();
                    break;
                case 2: data[row][column] = currentPrinter.getTension() + "";
                    break;
                case 3: data[row][column] = currentPrinter.getCompression() + "";
                    break;
                case 4: data[row][column] = currentPrinter.getImpact() + "";
                    break;
                case 5: data[row][column] = currentPrinter.materialsString();
                    break;
                case 6: data[row][column] = currentPrinter.getTolerance() + "";
                    break;
                case 7: data[row][column] = currentPrinter.getFinish() + "";
                    break;
                default: System.out.println("Invalid column number.");
                }
            }
            
            // Set up table panel
            printerTable = new JTable(data, header);
            formatTable();
            printerTable.setVisible(true);
            tablePanel.add(new JScrollPane(printerTable));
            tablePanel.setOpaque(true);
            tablePanel.setVisible(true);
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
