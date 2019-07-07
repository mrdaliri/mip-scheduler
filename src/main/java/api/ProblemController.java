package api;

import ilog.concert.IloException;
import org.springframework.web.bind.annotation.*;
import solver.Model;
import solver.Problem;

@RestController
public class ProblemController {
    @RequestMapping(value = "/solve", method = RequestMethod.POST)
    public String Solve(@RequestBody Problem input) throws IloException {
        Model model = new Model(input);
        model.solve();
        return model.getSolution().toString();
    }
}
