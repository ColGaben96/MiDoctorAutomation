package co.com.midoctor;

import co.com.midoctor.persistence.TestDTO;

import java.util.ArrayList;

/**
 * @author Gabriel Blanco
 * @version 1.0
 */
public class TestDAO {
    private ArrayList<TestDTO> tests = new ArrayList<>();
    public void create(int id, String name, String description, Object value) {
        TestDTO newTest = new TestDTO(id, name, description, value);
        tests.add(newTest);
    }
    public boolean passed(int id, Object currentVal) {
        for (TestDTO test : tests) {
            if (test.getId() == id) {
                return test.getValue() == currentVal;
            }
        }
        return false;
    }
    public void update(int id, String description, Object value) {
        tests.get(id).setDescription(description);
        tests.get(id).setValue(value);
    }
    public void delete(int id) {
        tests.remove(id);
    }

    public ArrayList<TestDTO> getArray() {
        return tests;
    }
}
