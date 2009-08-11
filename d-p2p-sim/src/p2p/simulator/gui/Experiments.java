/*
 * Experiments.java
 *
 * Created on May 24, 2008, 2:35 PM
 */
package p2p.simulator.gui;

import p2p.simulator.Simulator;
import p2p.simulator.dist.Distribution;

/**
 *
 * @author  papalukg
 */
public class Experiments extends javax.swing.JPanel {
    private Simulator S;
    private StatusBar statBar;
    private int distribution;
    private Plot lookupPlot;
    private Plot insertPlot;
    private Plot deletePlot;
    
    /** Creates new form Experiments */
    public Experiments() {
        initComponents();
    }
    
    public Experiments(Simulator S, StatusBar statBar) {
        this.S = S;
        this.distribution = 0;
        this.statBar = statBar;
        
        
        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        insertButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        queriesField = new javax.swing.JTextField();
        distributionField = new javax.swing.JComboBox();
        plotButton = new javax.swing.JButton();
        lookupButton = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        lookupRadioButton = new javax.swing.JRadioButton();
        insertRadioButton = new javax.swing.JRadioButton();
        deleteRadioButton = new javax.swing.JRadioButton();

        insertButton.setFont(new java.awt.Font("Dialog", 0, 12));
        insertButton.setText("insert");
        insertButton.setMargin(new java.awt.Insets(2, 5, 2, 5));
        insertButton.setPreferredSize(new java.awt.Dimension(55, 24));
        insertButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertButtonActionPerformed(evt);
            }
        });

        deleteButton.setFont(new java.awt.Font("Dialog", 0, 12));
        deleteButton.setText("delete");
        deleteButton.setMargin(new java.awt.Insets(2, 5, 2, 5));
        deleteButton.setPreferredSize(new java.awt.Dimension(55, 24));
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 12));
        jLabel1.setText("Number of Queries");

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 12));
        jLabel2.setText("Distribution of Keys");

        queriesField.setColumns(8);
        queriesField.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        distributionField.setBackground(new java.awt.Color(255, 255, 255));
        distributionField.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        distributionField.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Uniform", "PowLaw", "Beta" }));
        distributionField.setPreferredSize(new java.awt.Dimension(78, 20));
        distributionField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                distributionFieldActionPerformed(evt);
            }
        });

        plotButton.setFont(new java.awt.Font("Dialog", 0, 12));
        plotButton.setText("plot");
        plotButton.setEnabled(false);
        plotButton.setMargin(new java.awt.Insets(2, 5, 2, 5));
        plotButton.setPreferredSize(new java.awt.Dimension(55, 24));
        plotButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                plotButtonActionPerformed(evt);
            }
        });

        lookupButton.setFont(new java.awt.Font("Dialog", 0, 12));
        lookupButton.setText("lookup");
        lookupButton.setMargin(new java.awt.Insets(2, 5, 2, 5));
        lookupButton.setPreferredSize(new java.awt.Dimension(55, 24));
        lookupButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lookupButtonActionPerformed(evt);
            }
        });

        jLabel8.setText("Lookup & Update Evaluation");

        jSeparator1.setForeground(new java.awt.Color(153, 166, 191));

        lookupRadioButton.setText("Lookup");
        lookupRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lookupRadioButtonActionPerformed(evt);
            }
        });

        insertRadioButton.setText("Insert");
        insertRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertRadioButtonActionPerformed(evt);
            }
        });

        deleteRadioButton.setText("Delete");
        deleteRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteRadioButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(distributionField, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(queriesField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel8)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 385, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lookupButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(insertButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lookupRadioButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(insertRadioButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteRadioButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 132, Short.MAX_VALUE)
                        .addComponent(plotButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(queriesField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(distributionField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(insertButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lookupButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(plotButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(insertRadioButton)
                    .addComponent(deleteRadioButton)
                    .addComponent(lookupRadioButton))
                .addContainerGap(214, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lookupButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lookupButtonActionPerformed
        // TODO add your handling code here:
        int lookupQueries;
        String lookupDistribution;
        
        try {
            lookupQueries = Integer.parseInt(queriesField.getText());
            distribution = distributionField.getSelectedIndex();
            lookupDistribution = getDistribution(distribution);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return;
        }
        
        plotButton.setEnabled(false);
        statBar.updateStatusLabel("Performing Lookup Test...");
        //TODO:
        S.lookupTest(lookupQueries, Distribution.parse(lookupDistribution));
        statBar.updateStatusLabel("Done");
        plotButton.setEnabled(true);
}//GEN-LAST:event_lookupButtonActionPerformed

    private void plotButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_plotButtonActionPerformed
        // TODO add your handling code here:
        
            
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                if (lookupRadioButton.isSelected()) {
                    lookupPlot = new Plot(S.getLookupFt(), "Lookup Performance", "Path Length");
                    lookupPlot.setVisible(true);
                }
                else if (insertRadioButton.isSelected()) {
                    lookupPlot = new Plot(S.getInsertFt(), "Insertion Performance", "Path Length");
                    lookupPlot.setVisible(true);
                }
                else if (deleteRadioButton.isSelected()) {
                    lookupPlot = new Plot(S.getDeleteFt(), "Deletion Performance", "Path Length");
                    lookupPlot.setVisible(true);
                }
            }
        });
}//GEN-LAST:event_plotButtonActionPerformed

    private void insertButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertButtonActionPerformed
        // TODO add your handling code here:
        int insertQueries;
        String insertDistribution;
        
        try {
            insertQueries = Integer.parseInt(queriesField.getText());
            distribution = distributionField.getSelectedIndex();
            insertDistribution = getDistribution(distribution);
            //System.out.println("insertDistribution "+insertDistribution+" distribution "+distribution);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return;
        }
        
        plotButton.setEnabled(false);
        statBar.updateStatusLabel("Performing Insertion Test...");
        //TODO:
        S.insertionTest(insertQueries, Distribution.parse(insertDistribution));
        statBar.updateStatusLabel("Done");
        plotButton.setEnabled(true);
    }//GEN-LAST:event_insertButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        // TODO add your handling code here:
        int deleteQueries;
        String deleteDistribution;
        
        try {
            deleteQueries = Integer.parseInt(queriesField.getText());
            distribution = distributionField.getSelectedIndex();
            deleteDistribution = getDistribution(distribution);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return;
        }
        
        plotButton.setEnabled(false);
        statBar.updateStatusLabel("Performing Deletion Test...");
        //TODO:
        S.deletionTest(deleteQueries, Distribution.parse(deleteDistribution));
        statBar.updateStatusLabel("Done");
        plotButton.setEnabled(true);
    }//GEN-LAST:event_deleteButtonActionPerformed

private void insertRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertRadioButtonActionPerformed
// TODO add your handling code here:
    lookupRadioButton.setSelected(false);
    deleteRadioButton.setSelected(false);
}//GEN-LAST:event_insertRadioButtonActionPerformed

private void distributionFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_distributionFieldActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_distributionFieldActionPerformed

private void lookupRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lookupRadioButtonActionPerformed
// TODO add your handling code here:
    insertRadioButton.setSelected(false);
    deleteRadioButton.setSelected(false);
}//GEN-LAST:event_lookupRadioButtonActionPerformed

private void deleteRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteRadioButtonActionPerformed
// TODO add your handling code here:
    lookupRadioButton.setSelected(false);
    insertRadioButton.setSelected(false);
}//GEN-LAST:event_deleteRadioButtonActionPerformed
    
    private String getDistribution (int distribution) {
            
            String str = new String();
            
            if (distribution == 0)
                    str = new String("UNIF");
            else if (distribution == 1)
                    str = new String("POWL");
            else if (distribution == 2)
                    str = new String("BETA");
            
            return str;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton deleteButton;
    private javax.swing.JRadioButton deleteRadioButton;
    private javax.swing.JComboBox distributionField;
    private javax.swing.JButton insertButton;
    private javax.swing.JRadioButton insertRadioButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton lookupButton;
    private javax.swing.JRadioButton lookupRadioButton;
    private javax.swing.JButton plotButton;
    private javax.swing.JTextField queriesField;
    // End of variables declaration//GEN-END:variables
    
}
