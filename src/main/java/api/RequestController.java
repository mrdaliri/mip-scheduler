package api;

import ilog.concert.IloException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import solver.Model;
import solver.Node;
import solver.Problem;
import solver.Resource;

import java.util.Map;

@RestController
public class RequestController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    RequestRepository repository;

    @Autowired
    SolverService solver;

    @PostMapping("/solve")
    public Request createRequest(@RequestBody Problem input) throws IloException {
        Request request = new Request();
        request.setProblem(input);
        repository.save(request);

        solver.solve(request);

        return request;
    }

    @GetMapping("/solution/{id}")
    public Map<Node, Resource> getSolution(@PathVariable Integer id) {
        Request request = repository.getOne(id);
        return request.getSolution();
    }

}
