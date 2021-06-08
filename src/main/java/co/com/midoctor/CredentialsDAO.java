package co.com.midoctor;

import co.com.midoctor.persistence.CredentialsDTO;
import co.com.midoctor.persistence.FilesProcessor;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Gabriel Blanco
 * @version 1.0
 */
public class CredentialsDAO {
    private final ArrayList<CredentialsDTO> credentials = new ArrayList<>();

    public CredentialsDAO() {
        createFromObj();
    }
    public void create(String name, String username, String password) {
        CredentialsDTO newCred = new CredentialsDTO(name, username, password);
        credentials.add(newCred);
        try {
            new FilesProcessor().writeCredentials(name, username, password);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createFromObj() {
        try {
            var objs = new FilesProcessor().readCredentials("loginCreds");
            CredentialsDTO login = new CredentialsDTO(objs.get(0), objs.get(1), objs.get(2));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public CredentialsDTO read(String name) {
        for(CredentialsDTO credential : credentials) {
            if (credential.toString().equals(name)) {
                return credential;
            }
        }
        return null;
    }

    public void update(String name, String username, String password) {
        for(CredentialsDTO credential : credentials) {
            if (credential.toString().equals(name)) {
                credential.setUsername(username);
                credential.setPassword(password);
                try {
                    new FilesProcessor().writeCredentials(name, username, password);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void delete(String name) {
        credentials.removeIf(credential -> credential.toString().equals(name));
    }

    public ArrayList<CredentialsDTO> getArray() {return credentials;}
}
