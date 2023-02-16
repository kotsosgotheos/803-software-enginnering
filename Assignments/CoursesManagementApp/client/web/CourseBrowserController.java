package client.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import app.src.boundaries.CourseBrowserBoundary;
import app.src.entities.PersistenceGateway;
import app.src.entities.RegistrationToken;

import app.src.list_of_courses.CourseBrowser;
import app.src.list_of_courses.NameCourseSorter;

import persistence.SqliteGatewayImplementation;

@Controller
public class CourseBrowserController {
    @GetMapping("/courses")
    public String course_browser(
        @RequestParam("userid") String userid,
        Model model
    ) {
        PersistenceGateway accounts_db = new SqliteGatewayImplementation("Accounts");
        RegistrationToken token = (RegistrationToken)accounts_db.get_by_id(userid);
        CourseBrowserBoundary browser = new CourseBrowser(token.get_courses_db(), new NameCourseSorter());
        model.addAttribute("course_list", browser.list_courses());
        model.addAttribute("userid", userid);
        return "routes/course_browser";
    }
}
