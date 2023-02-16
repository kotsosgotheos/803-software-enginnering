package app.src.entities;

import java.util.UUID;

public class Grades extends Identifiable {
    public String project;
    public String exam;
    public String semester;

    public Grades(String project, String exam, String semester) {
        this.id = UUID.randomUUID().toString().replaceAll("-", "");
        this.project = project;
        this.exam = exam;
        this.semester = semester;
    }

    public String getProject() {
        return this.project;
    }
    public void setProject(String project) {
        this.project = project;
    }
    public String getExam() {
        return this.exam;
    }
    public void setExam(String exam) {
        this.exam = exam;
    }
    public String getSemester() {
        return this.semester;
    }
    public void setSemester(String semester) {
        this.semester = semester;
    }

    @Override
    public boolean is(Identifiable o) {
        Grades other = (Grades)o;
        return this.project.equals(other.project)
            && this.exam.equals(other.exam)
            && this.semester.equals(other.semester);
    }

    @Override
    public String toString() {
        return "";
    }
}
