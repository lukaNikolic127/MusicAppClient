package rs.ac.bg.fon.ps.view.form.model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import rs.ac.bg.fon.ps.domain.Band;
import rs.ac.bg.fon.ps.domain.Performer;

public class PerformerBandTableModel extends AbstractTableModel {

    private List<Performer> bands = new ArrayList<>();
    private final String[] columns = {"ID", "Country", "Name", "Added by"};

    public PerformerBandTableModel(List<Performer> bands) {
        this.bands = bands;
    }

    public PerformerBandTableModel() {
    }
    
    @Override
    public int getRowCount() {
        return bands.size();
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
        Band band = (Band) bands.get(rowIndex);
        switch(columnIndex){
            case 0:
                return band.getPerformerID();
            case 1:
                return band.getCountry().getName();
            case 2:
                return band.getBandName();
            case 3:
                return band.getUser().getUsername();
            default:
                return "N/A";
        }
    }

    public Performer getPerformer(int rowIndex) {
        return bands.get(rowIndex);
    }
    
}
