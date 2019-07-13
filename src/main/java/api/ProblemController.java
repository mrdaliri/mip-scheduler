package api;

import ilog.concert.IloException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.server.ResponseStatusException;
import solver.Model;
import solver.Node;
import solver.Problem;
import solver.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;

@RestController
@CrossOrigin(origins = "*")
public class ProblemController {
    @PostMapping(value = "/solve")
    public DeferredResult<List<ResourceAllocation>> Solve(@RequestBody Problem input) throws IloException {
        DeferredResult<List<ResourceAllocation>> output = new DeferredResult<>();

        ForkJoinPool.commonPool().submit(() -> {
            try {
                Model model = new Model(input);
                long startTime = System.currentTimeMillis();
                model.solve();
                if (model.isFeasible()) {
                    Map<Node, Resource> modelSolution = model.getSolution();
                    List<ResourceAllocation> allocationList = new ArrayList<>();
                    for (Map.Entry<Node, Resource> entry : modelSolution.entrySet()) {
                        allocationList.add(new ResourceAllocation(entry.getKey(), entry.getValue()));
                    }
                    output.setResult(allocationList);
                } else {
                    output.setErrorResult(new ResponseStatusException(HttpStatus.NOT_FOUND, "Failed to found a solution."));
                }
            } catch (IloException e) {
                e.printStackTrace();
            }
        });

        return output;
    }
}
