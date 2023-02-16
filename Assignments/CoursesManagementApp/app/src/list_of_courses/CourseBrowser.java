package app.src.list_of_courses;

import java.util.ArrayList;

import app.src.entities.PersistenceGateway;
import app.src.boundaries.CourseBrowserBoundary;
import app.src.entities.Identifiable;

public class CourseBrowser implements CourseBrowserBoundary {
    private PersistenceGateway courses;
    private CourseSorter sorter;

    public CourseBrowser(PersistenceGateway courses, CourseSorter sorter) {
        this.courses = courses;
        this.sorter = sorter;
    }

    @Override
    public ArrayList<Identifiable> list_courses() {
        return sorter.sort(courses);
    }
}
