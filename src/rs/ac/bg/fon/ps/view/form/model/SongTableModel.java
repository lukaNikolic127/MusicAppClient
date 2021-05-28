package rs.ac.bg.fon.ps.view.form.model;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import rs.ac.bg.fon.ps.communication.PerformerType;
import rs.ac.bg.fon.ps.domain.Band;
import rs.ac.bg.fon.ps.domain.Performer;
import rs.ac.bg.fon.ps.domain.PerformerSong;
import rs.ac.bg.fon.ps.domain.Singer;
import rs.ac.bg.fon.ps.domain.Song;
import rs.ac.bg.fon.ps.view.constants.AlbumSongConstants;

public class SongTableModel extends AbstractTableModel {

    private final List<Song> songs;
    private final String[] columns = {"Name", "Performers", "Duration", "Genre", "Added / Edited by"};

    public SongTableModel() {
        this.songs = new ArrayList<>();
    }

    public SongTableModel(List<Song> songs) {
        this.songs = songs;
    }

    @Override
    public int getRowCount() {
        return songs.size();
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

        Song s = songs.get(rowIndex);

        List<Performer> performersOfSong = new ArrayList<>();
        for (PerformerSong performerSong : s.getPerformerSongs()) {
            performersOfSong.add(performerSong.getPerformer());
        }
        switch (columnIndex) {
            case 0:
                return s.getName();
            case 1:
                String artists = "";
                for (Performer performerOfSong : performersOfSong) {
                    if (performerOfSong instanceof Singer) {
                        Singer singer = (Singer) performerOfSong;
                        artists += singer.getArtistName();
                    } else if (performerOfSong instanceof Band) {
                        Band band = (Band) performerOfSong;
                        artists += band.getBandName();
                    }
                    if (performersOfSong.indexOf(performerOfSong) == (performersOfSong.size() - 1)) {
                        artists += ".";
                    } else {
                        artists += ", ";
                    }
                }
                return artists;
            case 2:
                return String.valueOf(s.getDuration());
            case 3:
                return s.getGenre();
            case 4:
                return s.getUser().getUsername();
            default:
                return "N/A";
        }

    }

    public void addSong(Song songNew) throws Exception {
        if (songs.size() < AlbumSongConstants.MAX_NUM_OF_SONGS) {
            for (Song song : songs) {
                if (song.getName().equals(songNew.getName())) {
                    throw new Exception("Song '" + song.getName() + "' already exists!");
                }
            }
            songs.add(songNew);
            fireTableDataChanged();
        } else {
            throw new Exception("Album cannot have more than " + AlbumSongConstants.MAX_NUM_OF_SONGS + " songs!");
        }
    }

    public void removeSong(int selectedRow) {
        Song songToRemove = songs.get(selectedRow);
        songs.remove(songToRemove);

        fireTableDataChanged();
    }

    public int getNumberOfSongs() {
        return songs.size();
    }

    public double getAlbumDuration() {
        double duration = 0;
        for (Song song : songs) {
            duration += song.getDuration();
        }
        return duration;
    }

    public Song getSong(int rowIndex) {
        return songs.get(rowIndex);
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void saveSong(Song songEdit) throws Exception {
        for (Song song : songs) {
            if (song.getName().equals(songEdit.getName()) && song.getSongID() != songEdit.getSongID()) {
                throw new Exception("Song '" + song.getName() + "' already exists!");
            }
        }
        for (Song song : songs) {
            if (song.getSongID() == songEdit.getSongID()) {
                song = songEdit;
            }
        }
        fireTableDataChanged();
    }

}
