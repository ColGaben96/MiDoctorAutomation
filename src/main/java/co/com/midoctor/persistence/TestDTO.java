package co.com.midoctor.persistence;

/**
 * @author Gabriel Blanco
 * @version 1.0
 */
public class TestDTO {
    private final int id;
    private final String name;
    private String description;
    private Object value;

    public TestDTO(int id, String name, String description, Object value) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.value = value;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return name;
    }
}
