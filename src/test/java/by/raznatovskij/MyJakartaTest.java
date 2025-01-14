package by.raznatovskij;


import org.junit.jupiter.api.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MyJakartaTest {
    private static final String TEST_JSON_PATH = "test-data.json";
    private MyJakarta myJakarta;

    @BeforeEach
    public void setUp() {
        myJakarta = new MyJakarta();
    }

    @AfterEach
    public void tearDown() {

        File file = new File(TEST_JSON_PATH);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void testWriteToJson() throws IOException {

        Technology tech1 = new Technology("Java", "Programming Language");
        Technology tech2 = new Technology("Jakarta EE", "Enterprise Edition");

        myJakarta.updateTechnology(tech1);
        myJakarta.updateTechnology(tech2);


        myJakarta.writeToJson(TEST_JSON_PATH);


        File file = new File(TEST_JSON_PATH);
        assertTrue(file.exists());


        assertTrue(file.length() > 0);
    }

    @Test
    public void testReadFromJson() throws IOException {

        String jsonContent = """
        {
            "version": "1.0",
            "description": "Test Description",
            "technologies": [
                {"name": "Java", "description": "Programming Language"},
                {"name": "Jakarta EE", "description": "Enterprise Edition"}
            ]
        }
    """;
        Files.writeString(Path.of(TEST_JSON_PATH), jsonContent);


        myJakarta.readFromJson(TEST_JSON_PATH);


        List<Technology> technologies = myJakarta.getTechnologies();
        assertEquals(2, technologies.size());
        assertEquals("Java", technologies.get(0).getName());
        assertEquals("Programming Language", technologies.get(0).getDescription());
    }

    @Test
    public void testUpdateTechnology() throws IOException {

        Technology tech = new Technology("Java", "Programming Language");
        myJakarta.updateTechnology(tech);


        myJakarta.writeToJson(TEST_JSON_PATH);


        tech.setDescription("Updated Description");
        myJakarta.updateTechnology(tech);


        List<Technology> technologies = myJakarta.getTechnologies();
        assertEquals(1, technologies.size());
        assertEquals("Updated Description", technologies.get(0).getDescription());
    }
}

