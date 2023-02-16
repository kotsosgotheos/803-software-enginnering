package client.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import app.src.entities.Course;
import app.src.entities.PersistenceGateway;
import app.src.entities.RegistrationToken;
import app.src.entities.StudentRegistration;

/* TODO Fix boundary interface */
// import app.src.boundaries.StudentUpdaterBoundary;
import app.src.list_of_students.StudentUpdater;

import persistence.SqliteGatewayImplementation;

@Controller
public class StudentUpdaterController {
    @PostMapping("/update_student_object")
    public String update_student_object(
        String userid,
        String courseid,
        String studentid,
        StudentRegistration new_student
    ) {
        PersistenceGateway accounts_db = new SqliteGatewayImplementation("Accounts");
        RegistrationToken token = (RegistrationToken)accounts_db.get_by_id(userid);
        Course course = (Course)token.get_courses_db().get_by_id(courseid);

        StudentUpdater updater = new StudentUpdater(course, studentid);
        updater
            .edit_name(new_student.name)
            .edit_year_of_registration(new_student.year_of_registration)
            .edit_semester(new_student.semester)
            .update();
        
        return "redirect:/courses/" + courseid + "/students?userid=" + userid;
    }

    @GetMapping("/courses/{cid}/students/update")
    public String student_updater(
        @PathVariable("cid") String cid,
        @RequestParam("userid") String userid,
        @RequestParam("studentid") String studentid,
        Model model
    ) {
        PersistenceGateway accounts_db = new SqliteGatewayImplementation("Accounts");
        RegistrationToken token = (RegistrationToken)accounts_db.get_by_id(userid);
        Course course = (Course)token.get_courses_db().get_by_id(cid);
        StudentRegistration new_student = (StudentRegistration)course.get_students_db().get_by_id(studentid);
        model.addAttribute("new_student", new_student);
        model.addAttribute("courseid", cid);
        model.addAttribute("userid", userid);
        return "routes/student_updater";
    }
}
