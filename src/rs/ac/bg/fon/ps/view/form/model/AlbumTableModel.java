package rs.ac.bg.fon.ps.view.form.model;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import rs.ac.bg.fon.ps.domain.Album;

public class AlbumTableModel extends AbstractTableModel {

    private List<Album> albums;
    private final String[] columns = {"Name", "Duration", "Number of songs", "Added / Edited by"};

    public AlbumTableModel(List<Album> albums) {
        this.albums = albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
        fireTableDataChanged();
    }
    
    @Override
    public int getRowCount() {
        return albums.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Album album = albums.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return album.getName();
            case 1:
                return album.getDuration();
            case 2:
                return album.getNumberOfSongs();
            case 3:
                return album.getUser().getUsername();
            default:
                return "N/A";
        }
    }
    
    public Album getSelectedAlbum(int rowIndex){
        return albums.get(rowIndex);
    }

}
