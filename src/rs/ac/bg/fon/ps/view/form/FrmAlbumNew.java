/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.form;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import rs.ac.bg.fon.ps.domain.Genre;
import rs.ac.bg.fon.ps.view.cordinator.MainCordinator;

/**
 *
 * @author User
 */
public class FrmAlbumNew extends javax.swing.JDialog {

    /**
     * Creates new form FrmAlbumNew
     */
    public FrmAlbumNew(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSongs = new javax.swing.JTable();
        btnDeleteSelectedSong = new javax.swing.JButton();
        btnEditSong = new javax.swing.JButton();
        btnSaveAlbum = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        lblAlbumName = new javax.swing.JLabel();
        txtAlbumName = new javax.swing.JTextField();
        lblAlbumNameError = new javax.swing.JLabel();
        lblNumberOfSongs = new javax.swing.JLabel();
        txtNumberOfSongs = new javax.swing.JTextField();
        lblAlbumDuration = new javax.swing.JLabel();
        txtAlbumDuration = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        lblSongName = new javax.swing.JLabel();
        txtSongName = new javax.swing.JTextField();
        lblDuration1 = new javax.swing.JLabel();
        txtSongDuration = new javax.swing.JTextField();
        lblGenre = new javax.swing.JLabel();
        cbGenre = new javax.swing.JComboBox<>();
        lblSongNameError = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblSingers = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblBands = new javax.swing.JTable();
        btnAddSinger = new javax.swing.JButton();
        btnRemoveSelectedSinger = new javax.swing.JButton();
        btnAddBand = new javax.swing.JButton();
        btnRemoveSelectedBand = new javax.swing.JButton();
        btnAddNewSong = new javax.swing.JButton();
        lblSongDurationError = new javax.swing.JLabel();
        btnSaveSong = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnDeleteAlbum = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Songs"));

        tblSongs.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tblSongs);

        btnDeleteSelectedSong.setText("Delete song");

        btnEditSong.setText("Edit song");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnEditSong)
                        .addGap(18, 18, 18)
                        .addComponent(btnDeleteSelectedSong)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDeleteSelectedSong)
                    .addComponent(btnEditSong))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        btnSaveAlbum.setText("Save album");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Album"));

        lblAlbumName.setText("Album's name");

        lblAlbumNameError.setForeground(new java.awt.Color(255, 0, 0));

        lblNumberOfSongs.setText("Number of songs");

        txtNumberOfSongs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumberOfSongsActionPerformed(evt);
            }
        });

        lblAlbumDuration.setText("Duration");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNumberOfSongs)
                    .addComponent(lblAlbumDuration)
                    .addComponent(lblAlbumName))
                .addGap(37, 37, 37)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblAlbumNameError)
                    .addComponent(txtAlbumName, javax.swing.GroupLayout.DEFAULT_SIZE, 203, Short.MAX_VALUE)
                    .addComponent(txtNumberOfSongs, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtAlbumDuration))
                .addContainerGap(76, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAlbumName)
                    .addComponent(txtAlbumName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(lblAlbumNameError)
                .addGap(19, 19, 19)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblNumberOfSongs)
                    .addComponent(txtNumberOfSongs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAlbumDuration)
                    .addComponent(txtAlbumDuration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("New song"));

        lblSongName.setText("Song's name");

        lblDuration1.setText("Duration");

        lblGenre.setText("Genre");

        cbGenre.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        lblSongNameError.setForeground(new java.awt.Color(255, 0, 0));

        tblSingers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tblSingers);

        tblBands.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(tblBands);

        btnAddSinger.setText("Add singer");

        btnRemoveSelectedSinger.setText("Remove singer");

        btnAddBand.setText("Add band");

        btnRemoveSelectedBand.setText("Remove band");

        btnAddNewSong.setText("Add song");

        lblSongDurationError.setForeground(new java.awt.Color(255, 0, 0));

        btnSaveSong.setText("Save");

        btnCancel.setText("Cancel");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblDuration1)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblSongName)
                                    .addComponent(lblGenre))
                                .addGap(38, 38, 38)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbGenre, 0, 182, Short.MAX_VALUE)
                                    .addComponent(txtSongDuration)
                                    .addComponent(txtSongName)
                                    .addComponent(lblSongDurationError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblSongNameError, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 80, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(btnAddSinger)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnRemoveSelectedSinger)))
                        .addGap(34, 34, 34)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(btnAddBand)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnRemoveSelectedBand))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnSaveSong)
                        .addGap(14, 14, 14)
                        .addComponent(btnCancel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnAddNewSong)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblSongName)
                            .addComponent(txtSongName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSongNameError, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblDuration1)
                            .addComponent(txtSongDuration, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblSongDurationError, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblGenre)
                            .addComponent(cbGenre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAddSinger)
                        .addComponent(btnRemoveSelectedSinger))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAddBand)
                        .addComponent(btnRemoveSelectedBand)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAddNewSong)
                    .addComponent(btnSaveSong)
                    .addComponent(btnCancel))
                .addContainerGap())
        );

        btnDeleteAlbum.setText("Delete album");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnDeleteAlbum)
                        .addGap(18, 18, 18)
                        .addComponent(btnSaveAlbum))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSaveAlbum)
                    .addComponent(btnDeleteAlbum))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNumberOfSongsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumberOfSongsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumberOfSongsActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddBand;
    private javax.swing.JButton btnAddNewSong;
    private javax.swing.JButton btnAddSinger;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnDeleteAlbum;
    private javax.swing.JButton btnDeleteSelectedSong;
    private javax.swing.JButton btnEditSong;
    private javax.swing.JButton btnRemoveSelectedBand;
    private javax.swing.JButton btnRemoveSelectedSinger;
    private javax.swing.JButton btnSaveAlbum;
    private javax.swing.JButton btnSaveSong;
    private javax.swing.JComboBox<Object> cbGenre;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblAlbumDuration;
    private javax.swing.JLabel lblAlbumName;
    private javax.swing.JLabel lblAlbumNameError;
    private javax.swing.JLabel lblDuration1;
    private javax.swing.JLabel lblGenre;
    private javax.swing.JLabel lblNumberOfSongs;
    private javax.swing.JLabel lblSongDurationError;
    private javax.swing.JLabel lblSongName;
    private javax.swing.JLabel lblSongNameError;
    private javax.swing.JTable tblBands;
    private javax.swing.JTable tblSingers;
    private javax.swing.JTable tblSongs;
    private javax.swing.JTextField txtAlbumDuration;
    private javax.swing.JTextField txtAlbumName;
    private javax.swing.JTextField txtNumberOfSongs;
    private javax.swing.JTextField txtSongDuration;
    private javax.swing.JTextField txtSongName;
    // End of variables declaration//GEN-END:variables

    public JComboBox<Object> getCbGenre() {
        return cbGenre;
    }

    public JLabel getLblAlbumNameError() {
        return lblAlbumNameError;
    }

    public JLabel getLblSongNameError() {
        return lblSongNameError;
    }

    public JLabel getLblSongDurationError() {
        return lblSongDurationError;
    }

    public JTable getTblBands() {
        return tblBands;
    }

    public JTable getTblSingers() {
        return tblSingers;
    }

    public JTable getTblSongs() {
        return tblSongs;
    }

    public JButton getBtnAddNewSong() {
        return btnAddNewSong;
    }

    public JButton getBtnCancel() {
        return btnCancel;
    }

    public JButton getBtnEditSong() {
        return btnEditSong;
    }

    public JButton getBtnSaveAlbum() {
        return btnSaveAlbum;
    }

    public JButton getBtnSaveSong() {
        return btnSaveSong;
    }

    public JButton getBtnDeleteSelectedSong() {
        return btnDeleteSelectedSong;
    }

    public JTextField getTxtAlbumDuration() {
        return txtAlbumDuration;
    }

    public JTextField getTxtAlbumName() {
        return txtAlbumName;
    }

    public JTextField getTxtNumberOfSongs() {
        return txtNumberOfSongs;
    }

    public JTextField getTxtSongDuration() {
        return txtSongDuration;
    }

    public JTextField getTxtSongName() {
        return txtSongName;
    }

    public JButton getBtnDeleteAlbum() {
        return btnDeleteAlbum;
    }
    
    public void addBtnAddSingerActionListener(ActionListener actionListener){
        btnAddSinger.addActionListener(actionListener);
    }
    
    public void addBtnRemoveSelectedSingerActionListener(ActionListener actionListener){
        btnRemoveSelectedSinger.addActionListener(actionListener);
    }
    
    public void addBtnAddBandActionListener(ActionListener actionListener){
        btnAddBand.addActionListener(actionListener);
    }
    
    public void addBtnRemoveSelectedBandActionListener(ActionListener actionListener){
        btnRemoveSelectedBand.addActionListener(actionListener);
    }
    
    public void addBtnAddNewSongActionListener(ActionListener actionListener){
        btnAddNewSong.addActionListener(actionListener);
    }
    
    public void addBtnDeleteSelectedSongActionListener(ActionListener actionListener){
        btnDeleteSelectedSong.addActionListener(actionListener);
    }
    
    public void addBtnSaveAlbumActionListener(ActionListener actionListener) {
        btnSaveAlbum.addActionListener(actionListener);
    }
    
    public void addBtnEditSongActionListener(ActionListener actionListener){
        btnEditSong.addActionListener(actionListener);
    }
    
    public void addBtnSaveSongActionListener(ActionListener actionListener){
        btnSaveSong.addActionListener(actionListener);
    }
    
    public void addBtnCancelActionListener(ActionListener actionListener){
        btnCancel.addActionListener(actionListener);
    }
    
    public void addBtnDeleteAlbumActionListener(ActionListener actionListener){
        btnDeleteAlbum.addActionListener(actionListener);
    }
}
