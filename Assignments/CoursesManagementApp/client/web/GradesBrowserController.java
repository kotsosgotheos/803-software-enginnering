package client.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

import app.src.entities.Course;
import app.src.entities.Grades;
import app.src.entities.Identifiable;
import app.src.entities.PersistenceGateway;
import app.src.entities.RegistrationToken;
import app.src.entities.StudentRegistration;

import persistence.SqliteGatewayImplementation;

@Controller
public class GradesBrowserController {
    @GetMapping("/courses/{courseid}/students/{studentid}/grades")
    public String grades_browser(
        @PathVariable("courseid") String courseid,
        @PathVariable("studentid") String studentid,
        @RequestParam("userid") String userid,
        Model model
    ) {
        PersistenceGateway accounts_db = new SqliteGatewayImplementation("Accounts");
        RegistrationToken token = (RegistrationToken)accounts_db.get_by_id(userid);
        Course course = (Course)token.get_courses_db().get_by_id(courseid);
        StudentRegistration student = (StudentRegistration)course.get_students_db().get_by_id(studentid);
        ArrayList<Identifiable> grades_list = student.get_grades_db().get_all_items();

        model.addAttribute("grades_list", grades_list);
        model.addAttribute("userid", userid);
        model.addAttribute("courseid", courseid);
        model.addAttribute("studentid", studentid);

        return "routes/grades_browser";
    }
}
