package rs.ac.bg.fon.ps.view.form.model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import rs.ac.bg.fon.ps.domain.Performer;
import rs.ac.bg.fon.ps.domain.Singer;

public class PerformerSingerTableModel extends AbstractTableModel {

    private List<Performer> singers = new ArrayList<>();
    private final String[] columns = {"ID", "Country", "First name", "Last name", "Stage name", "Added by"};

    public PerformerSingerTableModel(List<Performer> singers) {
        this.singers = singers;
    }

    public PerformerSingerTableModel() {
        
    }
    
    @Override
    public int getRowCount() {
        return singers.size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Singer s = (Singer) singers.get(rowIndex);
        switch(columnIndex){
            case 0:
                return s.getPerformerID();
            case 1:
                return s.getCountry().getName();
            case 2:
                return s.getFirstName();
            case 3:
                return s.getLastName();
            case 4:
                return s.getArtistName();
            case 5:
                return s.getUser().getUsername();
            default:
                return "N/A";
        }
    }

    @Override
    public String getColumnName(int column) {
        return columns[column];
    }
    
    public void removeSinger(int rowIndex){
        singers.remove(rowIndex);
        fireTableDataChanged();
    }
    
    public Performer getPerformer(int rowIndex){
        return singers.get(rowIndex);
    }
    
}
