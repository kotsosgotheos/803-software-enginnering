package client.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import app.src.boundaries.StudentBrowserBoundary;
import app.src.entities.Course;
import app.src.entities.RegistrationToken;
import app.src.entities.PersistenceGateway;
import app.src.list_of_students.NameStudentSorter;
import app.src.list_of_students.StudentBrowser;

import persistence.SqliteGatewayImplementation;

@Controller
public class StudentBrowserController {
    @GetMapping("/courses/{courseid}/students")
    public String student_browser(
        @PathVariable("courseid") String courseid,
        @RequestParam("userid") String userid,
        Model model
    ) {
        PersistenceGateway db = new SqliteGatewayImplementation("Accounts");
        RegistrationToken token = (RegistrationToken)db.get_by_id(userid);
        StudentBrowserBoundary browser = new StudentBrowser(
            (Course)token.get_courses_db().get_by_id(courseid),
            new NameStudentSorter()
        );
        model.addAttribute("student_list", browser.list_students());
        model.addAttribute("userid", userid);
        model.addAttribute("courseid", courseid);
        return "routes/student_browser";
    }
}
