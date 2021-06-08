package co.com.midoctor.persistence;

/**
 * @author Gabriel Blanco
 * @version 1.0
 */
public class CredentialsDTO {
    private final String name;
    private String username;
    private String password;

    public CredentialsDTO(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return name;
    }
}
