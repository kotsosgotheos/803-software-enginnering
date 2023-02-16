package client.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import app.src.boundaries.StudentAdderBoundary;
import app.src.entities.Course;
import app.src.entities.PersistenceGateway;
import app.src.entities.RegistrationToken;
import app.src.entities.StudentRegistration;
import app.src.list_of_students.StudentAdder;

import persistence.SqliteGatewayImplementation;

@Controller
public class StudentAdderController {
    @PostMapping("/add_new_student")
    public String add_new_student(
        @RequestParam("courseid") String courseid,
        @RequestParam("userid") String userid,
        StudentRegistration new_student
    ) {
        PersistenceGateway accounts_db = new SqliteGatewayImplementation("Accounts");
        RegistrationToken token = (RegistrationToken)accounts_db.get_by_id(userid);

        StudentAdderBoundary adder = new StudentAdder((Course)token.get_courses_db().get_by_id(courseid));
        adder.add_new_student(
            new_student.name,
            new_student.year_of_registration,
            new_student.semester
        );
        return "redirect:/courses/" + courseid + "/students?userid=" + userid;
    }

    @GetMapping("/courses/{cid}/students/add")
    public String student_adder(@RequestParam("userid") String userid, @PathVariable("cid") String cid, Model model) {
        StudentRegistration new_student = new StudentRegistration("", "", "");
        model.addAttribute("new_student", new_student);
        model.addAttribute("userid", userid);
        model.addAttribute("courseid", cid);
        return "routes/student_adder";
    }
}
