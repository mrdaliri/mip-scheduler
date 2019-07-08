package api;

import ilog.concert.IloException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import solver.*;

import java.util.Map;
import java.util.concurrent.Future;

@Service
public class SolverService {
    @Autowired
    RequestRepository repository;

    @Async
    public Future<Map<Node, Resource>> solve(Request request) {
        Map<Node, Resource> solution = null;
        try {
            Model model = new Model(request.getProblem());
            model.solve();
            solution = model.getSolution();
        } catch (IloException e) {
            e.printStackTrace();
        }

        return new AsyncResult<Map<Node, Resource>>(solution);
    }
}
