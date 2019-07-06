package api;

import org.springframework.web.bind.annotation.*;
import solver.Problem;

@RestController
public class WebController {

    @RequestMapping("/sample")
    public SampleResponse Sample(@RequestParam(value = "name", defaultValue = "Robot") String name) {
        SampleResponse response = new SampleResponse();
        response.setId(1);
        response.setMessage("Your name is " + name);
        return response;
    }

    @RequestMapping(value = "/solve", method = RequestMethod.POST)
    public Problem Test(@RequestBody Problem inputPayload) {
        return inputPayload;
    }
}
