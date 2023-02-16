package app.src.entities;

import java.util.UUID;

import persistence.SqliteGatewayImplementation;

public class Course extends Identifiable {
    public String name;
    public String syllabus;
    public String instructor;
    public String year;
    public String semester;

    private transient PersistenceGateway students_db;

    public Course(
        String name,
        String syllabus,
        String instructor,
        String year,
        String semester
    ) {
        this.id = UUID.randomUUID().toString().replaceAll("-", "");
        this.name = name;
        this.syllabus = syllabus;
        this.instructor = instructor;
        this.year = year;
        this.semester = semester;

        /* TODO Figure out way to inject this */
        this.students_db = new SqliteGatewayImplementation("Course_" + this.id + "_enrolled_students");
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSyllabus() {
        return this.syllabus;
    }
    public void setSyllabus(String syllabus) {
        this.syllabus = syllabus;
    }
    public String getInstructor() {
        return this.instructor;
    }
    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }
    public String getYear() {
        return this.year;
    }
    public void setYear(String year) {
        this.year = year;
    }
    public String getSemester() {
        return this.semester;
    }
    public void setSemester(String semester) {
        this.semester = semester;
    }

    public PersistenceGateway get_students_db() {
        if(this.students_db == null)
            this.students_db = new SqliteGatewayImplementation("Course_" + this.id + "_enrolled_students");
        return this.students_db;
    }

    public int compare_ids(Course other) {
        return this.id.compareTo(other.id);
    }

    public int compare_names(Course other) {
        return this.name.compareTo(other.name);
    }

    public int compare_syllabi(Course other) {
        return this.syllabus.compareTo(other.syllabus);
    }

    public int compare_instructors(Course other) {
        return this.instructor.compareTo(other.instructor);
    }

    public int compare_years(Course other) {
        return this.year.compareTo(other.year);
    }

    public int compare_semesters(Course other) {
        return this.semester.compareTo(other.semester);
    }

    @Override
    public boolean is(Identifiable o) {
        Course other = (Course)o;
        return this.name.equals(other.name)
            && this.syllabus.equals(other.syllabus)
            && this.instructor.equals(other.instructor)
            && this.year.equals(other.year)
            && this.semester.equals(other.semester);
    }

    @Override
    public String toString() {
        return "";
    }
}
