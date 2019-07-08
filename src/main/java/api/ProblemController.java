package api;

import ilog.concert.IloException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;
import solver.Model;
import solver.Node;
import solver.Problem;
import solver.Resource;

import java.util.Map;
import java.util.concurrent.ForkJoinPool;

@RestController
public class ProblemController {
    @PostMapping(value = "/solve")
    public DeferredResult<Map<Node, Resource>> Solve(@RequestBody Problem input) throws IloException {
        DeferredResult<Map<Node, Resource>> output = new DeferredResult<>();

        ForkJoinPool.commonPool().submit(() -> {
            try {
                Model model = new Model(input);
                model.solve();
                output.setResult(model.getSolution());
            } catch (IloException e) {
                e.printStackTrace();
            }
        });

        return output;
    }
}
