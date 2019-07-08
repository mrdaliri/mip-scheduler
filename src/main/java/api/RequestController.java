package api;

import ilog.concert.IloException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import solver.Model;
import solver.Node;
import solver.Problem;
import solver.Resource;

import java.util.Map;

@RestController
public class RequestController {
    @Autowired
    RequestRepository repository;

    @PostMapping("/solve")
    public Request createRequst(@RequestBody Problem input) throws IloException {
        Request request = new Request();
        request.setProblem(input);
        Model model = new Model(input);
        model.solve();
        request.setSolution(model.getSolution());
        repository.save(request);
        return request;
//        return model.getSolution().toString();
    }

    @GetMapping("/solution/{id}")
    public Map<Node, Resource> getSolution(@PathVariable Integer id) {
        Request request = repository.getOne(id);
        return request.getSolution();
    }
}
