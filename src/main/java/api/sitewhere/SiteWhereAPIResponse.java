package api.sitewhere;

import java.util.List;

public class SiteWhereAPIResponse<T> {
    private int numResults;
    private List<T> results;

    public SiteWhereAPIResponse() {
    }

    public int getNumResults() {
        return numResults;
    }

    public void setNumResults(int numResults) {
        this.numResults = numResults;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }
}
