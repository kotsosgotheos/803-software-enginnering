package client.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import app.src.boundaries.GradesAdderBoundary;
import app.src.entities.Course;
import app.src.entities.Grades;
import app.src.entities.PersistenceGateway;
import app.src.entities.RegistrationToken;
import app.src.entities.StudentRegistration;
import app.src.grades.GradesAdder;

import persistence.SqliteGatewayImplementation;

@Controller
public class GradesAdderController {
    @PostMapping("/add_new_grades_set")
    public String add_new_grades_set(
        @RequestParam("userid") String userid,
        @RequestParam("courseid") String courseid,
        @RequestParam("studentid") String studentid,
        Grades new_grades
    ) {
        PersistenceGateway accounts_db = new SqliteGatewayImplementation("Accounts");
        RegistrationToken token = (RegistrationToken)accounts_db.get_by_id(userid);
        Course course = (Course)token.get_courses_db().get_by_id(courseid);
        StudentRegistration student = (StudentRegistration)course.get_students_db().get_by_id(studentid);

        GradesAdderBoundary adder = new GradesAdder(student);
        adder.add_new_grades(
            new_grades.project,
            new_grades.exam,
            new_grades.semester
        );

        return "redirect:/courses/" + courseid + "/students/" + studentid + "/grades?userid=" + userid;
    }

    @GetMapping("/courses/{courseid}/students/{studentid}/addgrades")
    public String grades_adder(
        @RequestParam("userid") String userid,
        @PathVariable("courseid") String courseid,
        @PathVariable("studentid") String studentid,
        Model model
    ) {
        Grades new_grades = new Grades("", "", "");
        model.addAttribute("new_grades", new_grades);
        model.addAttribute("userid", userid);
        model.addAttribute("courseid", courseid);
        model.addAttribute("studentid", studentid);
        return "routes/grades_adder";
    }
}
