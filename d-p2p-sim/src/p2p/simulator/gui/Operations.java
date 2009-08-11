/*
 * Operations.java
 *
 * Created on May 24, 2008, 1:34 PM
 */
package p2p.simulator.gui;

import java.awt.Rectangle;
import p2p.simulator.Simulator;
import p2p.simulator.network.NetworkFilter;

/**
 *
 * @author  papalukg
 */
public class Operations extends javax.swing.JPanel {

    private Simulator S;
    private StatusBar statBar;

    /** Creates new form Operations */
    public Operations() {
        initComponents();
    }

    public Operations(Simulator S, StatusBar statBar) {
        this.S = S;
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

        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        clusterPeerField = new javax.swing.JTextField();
        keyField = new javax.swing.JTextField();
        lookupButton = new javax.swing.JButton();
        insertButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        msgArea = new javax.swing.JTextArea();
        jSeparator2 = new javax.swing.JSeparator();
        clearButton = new javax.swing.JButton();

        jLabel3.setText("jLabel3");

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 12));
        jLabel1.setText("Cluster Peer");

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 12));
        jLabel2.setText("Key");

        clusterPeerField.setColumns(8);
        clusterPeerField.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        clusterPeerField.setPreferredSize(new java.awt.Dimension(114, 20));

        keyField.setColumns(8);
        keyField.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        keyField.setPreferredSize(new java.awt.Dimension(114, 20));
        keyField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                keyFieldActionPerformed(evt);
            }
        });

        lookupButton.setFont(new java.awt.Font("Dialog", 0, 12));
        lookupButton.setText("lookup");
        lookupButton.setMargin(new java.awt.Insets(2, 5, 2, 5));
        lookupButton.setPreferredSize(new java.awt.Dimension(52, 24));
        lookupButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lookupButtonActionPerformed(evt);
            }
        });

        insertButton.setFont(new java.awt.Font("Dialog", 0, 12));
        insertButton.setText("insert");
        insertButton.setMargin(new java.awt.Insets(2, 5, 2, 5));
        insertButton.setPreferredSize(new java.awt.Dimension(52, 24));
        insertButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                insertButtonActionPerformed(evt);
            }
        });

        deleteButton.setFont(new java.awt.Font("Dialog", 0, 12));
        deleteButton.setText("delete");
        deleteButton.setMargin(new java.awt.Insets(2, 5, 2, 5));
        deleteButton.setPreferredSize(new java.awt.Dimension(52, 24));
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        jLabel4.setText("Lookup & Update Operations");

        jSeparator1.setForeground(new java.awt.Color(153, 166, 191));

        jLabel5.setText("Messages");

        msgArea.setColumns(20);
        msgArea.setRows(5);
        jScrollPane1.setViewportView(msgArea);

        jSeparator2.setForeground(new java.awt.Color(153, 166, 191));

        clearButton.setFont(new java.awt.Font("Dialog", 0, 12));
        clearButton.setText("clear");
        clearButton.setMargin(new java.awt.Insets(2, 5, 2, 5));
        clearButton.setPreferredSize(new java.awt.Dimension(52, 24));
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 472, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2))
                                .addGap(54, 54, 54)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(keyField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(clusterPeerField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel4)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lookupButton, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(insertButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel5)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 484, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(444, Short.MAX_VALUE)
                        .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(clusterPeerField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(keyField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(insertButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lookupButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(clearButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lookupButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lookupButtonActionPerformed
        // TODO add your handling code here:
        int peerId, key;

        try {
            peerId = Integer.parseInt(clusterPeerField.getText());
            key = Integer.parseInt(keyField.getText());
        } catch (NumberFormatException e) {
            return;
        }

        S.enableGuiLogger(true);
        statBar.updateStatusLabel("Performing Lookup Operation...");
        S.lookupKey(peerId, key);
        statBar.updateStatusLabel("Done");
        S.enableGuiLogger(false);
}//GEN-LAST:event_lookupButtonActionPerformed

    private void insertButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_insertButtonActionPerformed
        // TODO add your handling code here:
        int peerId, key;

        try {
            peerId = Integer.parseInt(clusterPeerField.getText());
            key = Integer.parseInt(keyField.getText());
        } catch (NumberFormatException e) {
            return;
        }
        S.enableGuiLogger(true);
        statBar.updateStatusLabel("Performing Insert Operation...");
        S.insertKey(peerId, key);
        statBar.updateStatusLabel("Done");
        S.enableGuiLogger(false);
    }//GEN-LAST:event_insertButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        // TODO add your handling code here:
        int peerId, key;

        try {
            peerId = Integer.parseInt(clusterPeerField.getText());
            key = Integer.parseInt(keyField.getText());
        } catch (NumberFormatException e) {
            return;
        }

        S.enableGuiLogger(true);
        statBar.updateStatusLabel("Performing Delete Operation...");
        S.deleteKey(peerId, key);
        statBar.updateStatusLabel("Done");
        S.enableGuiLogger(false);
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void clearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButtonActionPerformed
        // TODO add your handling code here:
        NetworkFilter.setLogMsg("");
}//GEN-LAST:event_clearButtonActionPerformed

private void keyFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keyFieldActionPerformed
// TODO add your handling code here:
}//GEN-LAST:event_keyFieldActionPerformed
    
    public void updateMsgArea(String str) {
        
        msgArea.setText(str);
        Rectangle msgAreaRect = getBounds();
        msgAreaRect.x = 0;
        msgAreaRect.y = 0;
        paintImmediately(msgAreaRect);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearButton;
    private javax.swing.JTextField clusterPeerField;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton insertButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField keyField;
    private javax.swing.JButton lookupButton;
    private javax.swing.JTextArea msgArea;
    // End of variables declaration//GEN-END:variables
    
}
