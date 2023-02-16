package app.src.entities;

import java.util.UUID;

import persistence.SqliteGatewayImplementation;

public class StudentRegistration extends Identifiable {
    public String name;
    public String year_of_registration;
    public String semester;

    /* TODO Inject this */
    private transient PersistenceGateway grades_db;

    public StudentRegistration(
        String name,
        String year_of_registration,
        String semester
    ) {
        this.id = UUID.randomUUID().toString().replaceAll("-", "");
        this.name = name;
        this.year_of_registration = year_of_registration;
        this.semester = semester;
        this.grades_db = new SqliteGatewayImplementation("Grades_" + this.id);
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getYear_of_registration() {
        return this.year_of_registration;
    }
    public void setYear_of_registration(String year_of_registration) {
        this.year_of_registration = year_of_registration;
    }
    public String getSemester() {
        return this.semester;
    }
    public void setSemester(String semester) {
        this.semester = semester;
    }

    public PersistenceGateway get_grades_db() {
        if(this.grades_db == null)
            this.grades_db = new SqliteGatewayImplementation("Grades_" + this.id);
        return this.grades_db;
    }

    public int compare_ids(StudentRegistration other) {
        return this.id.compareTo(other.id);
    }

    public int compare_names(StudentRegistration other) {
        return this.name.compareTo(other.name);
    }

    public int compare_years_of_registration(StudentRegistration other) {
        return this.year_of_registration.compareTo(other.year_of_registration);
    }

    public int compare_semesters(StudentRegistration other) {
        return Integer.compare(Integer.parseInt(this.semester), Integer.parseInt(other.semester));
    }

    @Override
    public boolean is(Identifiable o) {
        StudentRegistration other=  (StudentRegistration)o;
        return this.name.equals(other.name)
            && this.year_of_registration.equals(other.year_of_registration)
            && this.semester.equals(other.semester);
    }

    @Override
    public String toString() {
        return "";
    }
}
