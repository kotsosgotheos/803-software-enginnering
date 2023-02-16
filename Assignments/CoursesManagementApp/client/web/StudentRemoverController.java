package client.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import app.src.entities.Course;
import app.src.entities.PersistenceGateway;
import app.src.entities.RegistrationToken;
import app.src.boundaries.StudentRemoverBoundary;
import app.src.list_of_students.StudentRemover;

import persistence.SqliteGatewayImplementation;

@Controller
public class StudentRemoverController {
    @GetMapping("/courses/{courseid}/students/remove")
    public String student_remover(
        @PathVariable("courseid") String courseid,
        @RequestParam("studentid") String studentid,
        @RequestParam("userid") String userid
    ) {
        PersistenceGateway accounts_db = new SqliteGatewayImplementation("Accounts");
        RegistrationToken token = (RegistrationToken)accounts_db.get_by_id(userid);
        Course course = (Course)token.get_courses_db().get_by_id(courseid);

        StudentRemoverBoundary remover = new StudentRemover(course);
        remover.remove_student(studentid);

        return "redirect:/courses/" + courseid + "/students?userid=" + userid;
    }
}
