package client.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

import app.src.boundaries.StatisticsCalculatorBoundary;
import app.src.entities.Course;
import app.src.entities.DescriptiveStatisticsGateway;
import app.src.entities.PersistenceGateway;
import app.src.entities.RegistrationToken;
import app.src.entities.StatisticsStrategy;

import app.src.stats.*;

import persistence.SqliteGatewayImplementation;

import statistics.ApacheMathDescriptiveStatisticsGatewayImplementation;

@Controller
public class StatisticsCalculatorController {
    @GetMapping("/courses/{courseid}/students/statistics")
    public String statistics_calculator(
        @PathVariable("courseid") String courseid,
        @RequestParam("userid") String userid,
        Model model
    ) {
        PersistenceGateway accounts_db = new SqliteGatewayImplementation("Accounts");
        RegistrationToken token = (RegistrationToken)accounts_db.get_by_id(userid);
        Course course = (Course)token.get_courses_db().get_by_id(courseid);

        StatisticsCalculatorBoundary stats_calculator = new StatisticsCalculator(course);
        ArrayList<StatisticsStrategy> strategies = new ArrayList<StatisticsStrategy>();
        DescriptiveStatisticsGateway ds = new ApacheMathDescriptiveStatisticsGatewayImplementation();
        strategies.add(new KurtosisStatisticStrategy(ds));
        strategies.add(new MaxStatisticStrategy(ds));
        strategies.add(new MeanStatisticStrategy(ds));
        strategies.add(new MedianStatisticStrategy(ds));
        strategies.add(new MinStatisticStrategy(ds));
        strategies.add(new SkewnessStatisticStrategy(ds));
        strategies.add(new StandardDeviationStatisticStrategy(ds));
        strategies.add(new VarianceStatisticStrategy(ds));
        stats_calculator.set_strategies(strategies);

        model.addAttribute("stats_map", stats_calculator.calculate_statistics());
        model.addAttribute("userid", userid);

        return "routes/statistics_calculator";
    }
}
