package client.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import app.src.entities.Course;
import app.src.entities.Identifiable;
import app.src.entities.PersistenceGateway;
import app.src.entities.RegistrationToken;
/* TODO Fix boundary interface */
// import app.src.boundaries.CourseUpdaterBoundary;
import app.src.list_of_courses.CourseUpdater;

import persistence.SqliteGatewayImplementation;

@Controller
public class CourseUpdaterController {
    @PostMapping("/update_course_object")
    public String update_course_object(String courseid, String userid, Course new_course) {
        PersistenceGateway accounts_db = new SqliteGatewayImplementation("Accounts");
        RegistrationToken token = (RegistrationToken)accounts_db.get_by_id(userid);

        CourseUpdater updater = new CourseUpdater(token.get_courses_db(), courseid);
        updater
            .edit_name(new_course.name)
            .edit_syllabus(new_course.syllabus)
            .edit_instructor(new_course.instructor)
            .edit_year(new_course.year)
            .edit_semester(new_course.semester)
            .update();

        return "redirect:/courses?userid=" + token.id;
    }

    @GetMapping("/courses/update")
    public String course_updater(@RequestParam("id") String id, @RequestParam("userid") String userid, Model model) {
        PersistenceGateway accounts_db = new SqliteGatewayImplementation("Accounts");
        RegistrationToken token = (RegistrationToken)accounts_db.get_by_id(userid);
        Course new_course = (Course)token.get_courses_db().get_by_id(id);
        model.addAttribute("new_course", new_course);
        model.addAttribute("token", token);
        return "routes/course_updater";
    }
}
