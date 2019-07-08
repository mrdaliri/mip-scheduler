package api;

import solver.Node;
import solver.Problem;
import solver.Resource;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "requests")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Transient
    private Problem problem;

    @Convert(converter = SolutionConverter.class)
    @Lob
    private Map<Node, Resource> solution;

    @Temporal(TemporalType.TIMESTAMP)
    private Date submissionDateTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date resolutionDateTime;

    private Boolean isResolved = false;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    public Map<Node, Resource> getSolution() {
        return solution;
    }

    public void setSolution(Map<Node, Resource> solution) {
        this.solution = solution;
    }

    public Date getSubmissionDateTime() {
        return submissionDateTime;
    }

    public void setSubmissionDateTime(Date submissionDateTime) {
        this.submissionDateTime = submissionDateTime;
    }

    public Date getResolutionDateTime() {
        return resolutionDateTime;
    }

    public void setResolutionDateTime(Date resolutionDateTime) {
        this.resolutionDateTime = resolutionDateTime;
    }

    public Boolean getResolved() {
        return isResolved;
    }

    public void setResolved(Boolean resolved) {
        isResolved = resolved;
    }
}
