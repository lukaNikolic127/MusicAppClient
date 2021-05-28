package rs.ac.bg.fon.ps.communication;

import java.io.IOException;
import java.util.List;
import java.net.Socket;
import rs.ac.bg.fon.ps.domain.Album;
import rs.ac.bg.fon.ps.domain.Band;
import rs.ac.bg.fon.ps.domain.Country;
import rs.ac.bg.fon.ps.domain.Genre;
import rs.ac.bg.fon.ps.domain.Performer;
import rs.ac.bg.fon.ps.domain.Singer;
import rs.ac.bg.fon.ps.domain.User;

public class Communication {

    private Socket socket;
    private Sender sender;
    private Receiver receiver;
    private User user; //current loggedin user

    private static Communication instance;

    private Communication() throws IOException {
        try {
            socket = new Socket("127.0.0.1", 9000);
            sender = new Sender(socket);
            receiver = new Receiver(socket);
            System.out.println("Connected");
        } catch (IOException ex) {
            throw new IOException("Connection to server failed!");
        }
    }

    public void logout() throws IOException {
        socket.close();
        System.out.println("Disconnected");
    }
    
    public static Communication getInstance() throws IOException {
        if (instance == null) {
            instance = new Communication();
        }
        return instance;
    }

    public User getUser() {
        return user;
    }

    public User login(User user) throws IOException, Exception {
        Request request = new Request(Operation.LOG_IN, null, user);
        try {
            sender.send(request);
        } catch (IOException ex) {
            throw new IOException("Connection to server failed!");
        }
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            this.user = (User) response.getResult();
            return this.user;
        } else {
            throw response.getException();
        }
    }

    public List<Country> getAllCountries() throws IOException, Exception {
        Request request = new Request(Operation.RETURN_ALL_COUNTRIES, null, null);
        try {
            sender.send(request);
        } catch (IOException ex) {
            throw new IOException("Connection to server failed!");
        }
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (List<Country>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public void removePerformer(Performer performer) throws IOException, Exception {
        Request request = new Request(Operation.DELETE_PERFORMER, performer, null);
        try {
            sender.send(request);
        } catch (IOException ex) {
            throw new IOException("Connection to server failed!");
        }
        Response response = (Response) receiver.receive();
        if (response.getException() != null) {
            throw response.getException();
        }
    }

    public void savePerformer(Performer performer) throws IOException, Exception {
        Request request = new Request(Operation.ADD_PERFORMER, performer, user);
        try {
            sender.send(request);
        } catch (IOException ex) {
            throw new IOException("Connection to server failed!");
        }
        Response response = (Response) receiver.receive();
        if (response.getException() != null) {
            throw response.getException();
        }
    }

    public List<Genre> getAllGenres() throws IOException, Exception {
        Request request = new Request(Operation.RETURN_ALL_GENRES, null, null);
        try {
            sender.send(request);
        } catch (IOException ex) {
            throw new IOException("Connection to server failed!");
        }
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (List<Genre>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public void saveAlbum(Album album) throws IOException, Exception {
        Request request = new Request(Operation.ADD_ALBUM, album, user);
        try {
            sender.send(request);
        } catch (IOException ex) {
            throw new IOException("Connection to server failed!");
        }
        Response response = (Response) receiver.receive();
        if (response.getException() != null) {
            throw response.getException();
        }
    }

    public List<Album> getAllAlbums() throws IOException, Exception {
        Request request = new Request(Operation.RETURN_ALL_ALBUMS, null, null);
        try {
            sender.send(request);
        } catch (IOException ex) {
            throw new IOException("Connection to server failed!");
        }
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (List<Album>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public List<User> getAllUsers() throws IOException, Exception {
        Request request = new Request(Operation.RETURN_ALL_USERS, null, null);
        try {
            sender.send(request);
        } catch (IOException ex) {
            throw new IOException("Connection to server failed!");
        }
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (List<User>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public List<Performer> searchPerformers(Performer pattern) throws IOException, Exception {
        Request request = new Request(Operation.SEARCH_PERFORMERS, pattern, null);
        try {
            sender.send(request);
        } catch (IOException ex) {
            throw new IOException("Connection to server failed!");
        }
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (List<Performer>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public List<Performer> getAllPerformers(Performer performer) throws IOException, Exception {
        Request request = new Request(Operation.RETURN_ALL_PERFORMERS, performer, user);
        try {
            sender.send(request);
        } catch (IOException ex) {
            throw new IOException("Connection to server failed!");
        }
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (List<Performer>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public List<Album> searchAlbums(Album pattern) throws IOException, Exception {
        Request request = new Request(Operation.SEARCH_ALBUMS, pattern, null);
        try {
            sender.send(request);
        } catch (IOException ex) {
            throw new IOException("Connection to server failed!");
        }
        Response response = (Response) receiver.receive();
        if (response.getException() == null) {
            return (List<Album>) response.getResult();
        } else {
            throw response.getException();
        }
    }

    public void removeAlbum(Album album) throws IOException, Exception {
        Request request = new Request(Operation.DELETE_ALBUM, album, null);
        try {
            sender.send(request);
        } catch (IOException ex) {
            throw new IOException("Connection to server failed!");
        }
        Response response = (Response) receiver.receive();
        if (response.getException() != null) {
            throw response.getException();
        }
    }

    public void updateAlbum(Album album) throws IOException, Exception {
        Request request = new Request(Operation.UPDATE_ALBUM, album, user);
        try {
            sender.send(request);
        } catch (IOException ex) {
            throw new IOException("Connection to server failed!");
        }
        Response response = (Response) receiver.receive();
        if (response.getException() != null) {
            throw response.getException();
        }
    }

    public Performer getPerformer(Performer performer) throws IOException, Exception {
        Request request = new Request(Operation.RETURN_PERFORMER, performer, user);
        try {
            sender.send(request);
        } catch (IOException ex) {
            throw new IOException("Connection to server failed!");
        }
        Response response = (Response) receiver.receive();
        if (response.getException() != null) {
            throw response.getException();
        } else {
            return (Performer) response.getResult();
        }
    }

    public Album getAlbum(Album album) throws IOException, Exception {
        Request request = new Request(Operation.RETURN_ALBUM, album, user);
        try {
            sender.send(request);
        } catch (IOException ex) {
            throw new IOException("Connection to server failed!");
        }
        Response response = (Response) receiver.receive();
        if (response.getException() != null) {
            throw response.getException();
        } else {
            return (Album) response.getResult();
        }
    }

    

}
