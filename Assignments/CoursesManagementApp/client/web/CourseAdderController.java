package client.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import app.src.entities.Course;
import app.src.entities.PersistenceGateway;
import app.src.entities.RegistrationToken;
import app.src.boundaries.CourseAdderBoundary;
import app.src.list_of_courses.CourseAdder;

import persistence.SqliteGatewayImplementation;

@Controller
public class CourseAdderController {
    @PostMapping("/add_new_course")
    public String add_new_course(@RequestParam("userid") String userid, Course new_course) {
        PersistenceGateway accounts_db = new SqliteGatewayImplementation("Accounts");
        RegistrationToken token = (RegistrationToken)accounts_db.get_by_id(userid);

        CourseAdderBoundary adder = new CourseAdder(token.get_courses_db());
        adder.add_new_course(
            new_course.name,
            new_course.syllabus,
            new_course.instructor,
            new_course.year,
            new_course.semester
        );
        return "redirect:/courses?userid=" + token.id;
    }

    @GetMapping("/courses/add")
    public String course_adder(@RequestParam("userid") String userid, Model model) {
        Course new_course = new Course("", "", "", "", "");
        model.addAttribute("new_course", new_course);
        model.addAttribute("userid", userid);
        return "routes/course_adder";
    }
}
