/*
 * SaveDialogWindow.java
 *
 * Created on 15 ����� 2007, 9:26 ��
 */

package p2p.simulator.gui;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author  george
 */
public class SaveDialogWindow extends javax.swing.JFrame {
    
    /** Creates new form SaveDialogWindow */
    public SaveDialogWindow(String filter) {
        
            this.filter = filter;
            jFileChooser = new javax.swing.JFileChooser();
            pngFilter = new FileNameExtensionFilter("Images(*.png)", "png");
            txtFilter = new FileNameExtensionFilter("Files(*.txt)", "txt");
            
            if (filter.matches("png"))
                jFileChooser.addChoosableFileFilter(pngFilter);
            else if (filter.matches("txt"))
                jFileChooser.addChoosableFileFilter(txtFilter);
            //... Open a file dialog.
            returnVal = jFileChooser.showSaveDialog(SaveDialogWindow.this);
            actionPerformedFileChooser1();
    }
    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBounds(new java.awt.Rectangle(100, 0, 0, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 506, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 326, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void actionPerformedFileChooser1() {
            
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                //... The user selected a file, get it, use it.
                file = jFileChooser.getSelectedFile();
            }
            else
                file = null;
    }
    
    public String getFilename() {
        
        if (file == null)
            return null;
        
        if (file.getPath().endsWith("."+filter)) 
            return file.getPath();
        else
            return file.getPath()+"."+filter;
    }
        
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    private FileNameExtensionFilter pngFilter;
    private FileNameExtensionFilter txtFilter;
    private JFileChooser jFileChooser;
    private int returnVal;
    private File file;
    private String filter;
}
