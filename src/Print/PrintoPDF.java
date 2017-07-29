/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Print;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.ArrayList;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

/**
 *
 * @author Fatlum Arifi
 */
public class PrintoPDF implements Printable{
    
    private ArrayList<JComponent> items;//arrayList te gjitha komponeteve ne swing

    public PrintoPDF()
    {
        items = new ArrayList<JComponent>();
    }
    @Override
    public int print(Graphics g, PageFormat pf, int page) throws PrinterException
    {
        if(page > 0) return Printable.NO_SUCH_PAGE;
        Graphics2D g2 = (Graphics2D)g;
        g2.translate(pf.getImageableX(), pf.getImageableY());


        for(JComponent item : items)
        {
            item.print(g);
            g2.translate(0, item.getHeight());
        }
        return Printable.PAGE_EXISTS;
    }

    public void printIt()
    {
        PrintRequestAttributeSet attributes = new HashPrintRequestAttributeSet();
        PrinterJob job = PrinterJob.getPrinterJob();
        try
        {
            job.setPrintable(this);
            if(job.printDialog(attributes))
                job.print(attributes);
        }
        catch (PrinterException e)
        {
            System.err.format("Cannot print %s%n", e.getMessage());
            JOptionPane.showMessageDialog(null, "Nuk u printua!");
        }
    }

    public void addComponent(JComponent component) { items.add(component); }
}
