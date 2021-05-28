package rs.ac.bg.fon.ps.view.form.model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import rs.ac.bg.fon.ps.domain.Band;
import rs.ac.bg.fon.ps.domain.Performer;

public class BandTableModel extends AbstractTableModel {

    private final List<Performer> bands;
    private final String[] columns = {"Name", "Selected"};
    private List<Performer> selected;

    public BandTableModel(List<Performer> bands, List<Performer> selected) {
        this.bands = bands;
        if (selected != null) {
            this.selected = selected;
        } else {
            this.selected = new ArrayList<>();
        }
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
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex==1){
            return Boolean.class;
        }
        return super.getColumnClass(columnIndex);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Band band = (Band) bands.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return band.getBandName();
            case 1:
                return selected.contains(band);
            default:
                return "N/A";
        }
    }

    public void selectBand(int selectedRow) throws Exception {
        if (!selected.contains(bands.get(selectedRow))) {
            selected.add(bands.get(selectedRow));
        } else {
            throw new Exception("Band is already selected!");
        }
        fireTableDataChanged();
    }

    public void deselectBand(int selectedRow) throws Exception {
        if (selected.contains(bands.get(selectedRow))) {
            selected.remove(bands.get(selectedRow));
        } else {
            throw new Exception("Band is not selected!");
        }
        fireTableDataChanged();
    }

    public List<Performer> getSelected() {
        return selected;
    }

    public void setSelected(List<Performer> selected) {
        if (selected.size() > 0) {
            this.selected = selected;
            fireTableDataChanged();
        }
    }

}
