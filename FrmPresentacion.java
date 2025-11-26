
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;


public class FrmPresentacion extends javax.swing.JFrame {
    private Analizador analizador;
    
    public FrmPresentacion() {
        this.analizador = new Analizador();
        initComponents();
    }
    
    private String validarFormatoEstricto(String codigo) {
        if (codigo.contains("//") || codigo.contains("/*")) {
            return "El código no está depurado: Contiene comentarios.";
        }

        if (!codigo.contains("class") || !codigo.contains("{") || !codigo.contains("}")) {
            return "El código no tiene la estructura básica de una clase Java "
                    + "(falta class o está mal escrita su apertura y/o cierre).";
        }

        String[] lineas = codigo.split("\\n");

        for (int i = 0; i < lineas.length; i++) {
            String linea = lineas[i];
            
            if (linea.length() == 0 && i < lineas.length - 1) {
                 return "El código no está depurado: Contiene líneas vacías (saltos de línea extra).";
            }

            if (linea.startsWith(" ") || linea.startsWith("\t")) {
                return "El código no está depurado: Tiene espacios o tabulaciones al inicio (Línea " + (i+1) + ").";
            }
            
            // if (linea.contains("  ")) {             }
        }
        
        return null;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtCodigoFuente = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblTablaSimbolos = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtListaIDs = new javax.swing.JTextArea();
        btnAnalizarCodigo = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Analizador Semantico - Kevin Haziel Elias Zavala");
        setBackground(new java.awt.Color(0, 51, 51));

        txtCodigoFuente.setColumns(20);
        txtCodigoFuente.setRows(5);
        jScrollPane1.setViewportView(txtCodigoFuente);

        jLabel1.setText("Insertar código Fuente");

        tblTablaSimbolos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Lexema", "Token"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblTablaSimbolos);
        if (tblTablaSimbolos.getColumnModel().getColumnCount() > 0) {
            tblTablaSimbolos.getColumnModel().getColumn(0).setResizable(false);
            tblTablaSimbolos.getColumnModel().getColumn(1).setResizable(false);
            tblTablaSimbolos.getColumnModel().getColumn(2).setResizable(false);
        }

        txtListaIDs.setColumns(20);
        txtListaIDs.setRows(5);
        jScrollPane3.setViewportView(txtListaIDs);

        btnAnalizarCodigo.setText("Analizar código");
        btnAnalizarCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnalizarCodigoActionPerformed(evt);
            }
        });

        jLabel2.setText("Lista de ID's");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 294, Short.MAX_VALUE)
                                .addComponent(jScrollPane3)
                                .addComponent(btnAnalizarCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(btnAnalizarCodigo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addGap(2, 2, 2)
                        .addComponent(jScrollPane3))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 524, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAnalizarCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnalizarCodigoActionPerformed
        String codigo = txtCodigoFuente.getText();
        
        if (codigo.trim().isEmpty()){
            JOptionPane.showMessageDialog(this, "Por favor escriba código Java.");
            return;
        }
        
        String errorValidacion = validarFormatoEstricto(codigo);
        
        if (errorValidacion != null) {
            JOptionPane.showMessageDialog(this, "RECHAZADO:\n" + errorValidacion, "Código no depurado correctamente", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        List<FilaSimbolo> resultados = analizador.analizar(codigo);
        String[] columnas = {"ID", "Lexema", "Token"}; 
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0);
        
        modelo.setRowCount(0);
        txtListaIDs.setText("");
        StringBuilder listaIDs = new StringBuilder();
        
        for(FilaSimbolo fila : resultados){
            modelo.addRow(fila.convertirAFila());
            
            if(fila.esDeclaracion()){
                listaIDs.append("ID").append(fila.getId())
                        .append(" -> ")
                        .append(fila.getTipoDato()).append(" ")
                        .append(fila.getLexema()).append("\n");
            }
        }
        
        tblTablaSimbolos.setModel(modelo);
        txtListaIDs.setText(listaIDs.toString());
    }//GEN-LAST:event_btnAnalizarCodigoActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmPresentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPresentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPresentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPresentacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPresentacion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnalizarCodigo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tblTablaSimbolos;
    private javax.swing.JTextArea txtCodigoFuente;
    private javax.swing.JTextArea txtListaIDs;
    // End of variables declaration//GEN-END:variables
}
