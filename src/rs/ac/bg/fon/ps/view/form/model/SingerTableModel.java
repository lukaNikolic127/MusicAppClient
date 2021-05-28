package rs.ac.bg.fon.ps.view.form.model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import rs.ac.bg.fon.ps.domain.Performer;
import rs.ac.bg.fon.ps.domain.Singer;

public class SingerTableModel extends AbstractTableModel {

    private final List<Performer> singers;
    private final String[] columns = {"First name", "Last name", "Stage name", "Selected"};
    private List<Performer> selected;

    public SingerTableModel(List<Performer> singers, List<Performer> selected) {
        this.singers = singers;
        if(selected != null)
            this.selected = selected;
        else
            this.selected = new ArrayList<>();
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
    public String getColumnName(int column) {
        return columns[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex==3){
            return Boolean.class;
        }
        return super.getColumnClass(columnIndex);
    }
   
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Singer singer = (Singer) singers.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return singer.getFirstName();
            case 1:
                return singer.getLastName();
            case 2:
                return singer.getArtistName();
            case 3:
                return selected.contains(singer);
            default:
                return "N/A";
        }
    }

    public void selectPerformer(int selectedRow) throws Exception {
        if (!selected.contains(singers.get(selectedRow))) {
            selected.add(singers.get(selectedRow));
        } else {
            throw new Exception("Singer is already selected!");
        }
        fireTableDataChanged();
    }

    public void deselectPerformer(int selectedRow) throws Exception {
        if (selected.contains(singers.get(selectedRow))) {
            selected.remove(singers.get(selectedRow));
        } else {
            throw new Exception("Singer is not selected!");
        }
        fireTableDataChanged();
    }

    public List<Performer> getSelected() {
        return selected;
    }

    public List<Performer> getSingers() {
        return singers;
    }
    
    public void setSelected(List<Performer> selected) {
        if (selected.size() > 0) {
            this.selected = selected;
            fireTableDataChanged();
        }
    }
    
    public Performer getFirstSelected(){
        return selected.get(0);
    }
    
    public Performer getFirst(){
        return singers.get(0);
    }
    
    public boolean check(){
        for (Performer singer : singers) {
            if(selected.contains(singer))
                return true;
        }
        return false;
    }
}
