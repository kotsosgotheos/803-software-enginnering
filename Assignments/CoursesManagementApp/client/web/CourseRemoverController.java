package client.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import app.src.entities.Identifiable;
import app.src.entities.PersistenceGateway;
import app.src.entities.RegistrationToken;
import app.src.boundaries.CourseRemoverBoundary;
import app.src.list_of_courses.CourseRemover;

import persistence.SqliteGatewayImplementation;

@Controller
public class CourseRemoverController {
    @GetMapping("/courses/remove")
    public String course_remover(@RequestParam("id") String id, @RequestParam("userid") String userid) {
        PersistenceGateway accounts_db = new SqliteGatewayImplementation("Accounts");
        RegistrationToken token = (RegistrationToken)accounts_db.get_by_id(userid);

        CourseRemoverBoundary remover = new CourseRemover(token.get_courses_db());
        remover.remove_course(id);

        return "redirect:/courses?userid=" + token.id;
    }
}
