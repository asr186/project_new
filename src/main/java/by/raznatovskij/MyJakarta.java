package by.raznatovskij;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MyJakarta {
    private static final String DEFAULT_JSON_PATH = "data.json"; // Путь к JSON-файлу

    private String version;
    private String description;
    private List<Technology> technologies;


    public MyJakarta() {
        this.technologies = new ArrayList<>();
    }

    public MyJakarta(String version, String description) {
        this.version = version;
        this.description = description;
        this.technologies = new ArrayList<>();
    }


    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Technology> getTechnologies() {
        return technologies;
    }

    public void setTechnologies(List<Technology> technologies) {
        this.technologies = technologies;
    }


    public void writeToJson(String path) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(path), this);
            System.out.println("Данные успешно записаны в файл: " + path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void readFromJson(String path) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            MyJakarta temp = mapper.readValue(new File(path), MyJakarta.class);
            this.version = temp.version;
            this.description = temp.description;
            this.technologies = temp.technologies;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void updateTechnology(Technology technology) {
        boolean updated = false;
        for (Technology tech : technologies) {
            if (tech.getName().equals(technology.getName())) {
                tech.setDescription(technology.getDescription());
                updated = true;
                break;
            }
        }
        if (!updated) {
            technologies.add(technology); // Если технология не найдена, добавляем новую
        }
        writeToJson(DEFAULT_JSON_PATH); // Сохраняем изменения
        System.out.println("Технология обновлена/добавлена: " + technology);
    }

    @Override
    public String toString() {
        return "MyJakarta{" +
                "version='" + version + '\'' +
                ", description='" + description + '\'' +
                ", technologies=" + technologies +
                '}';
    }

}
