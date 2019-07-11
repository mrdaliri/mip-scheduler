package api;

import solver.Node;
import solver.Resource;

/***
 *  Simple class to represent resource allocation
 *  It is used in controller when we want to return a list of allocations instead of key/pair values to client;
 *  as it is not a good principle to use JSON keys for presenting data values.
 *
 *  The reason that I put this class in the api package (and not in the solver package)
 *  is that I think representing using list is an API requirement;
 *  also, I believe a map (key/pairs) is more suitable for internal uses considering its O(1) access time.
 */

public class ResourceAllocation {
    private Node node;
    private Resource resource;

    public ResourceAllocation() {

    }

    public ResourceAllocation(Node node, Resource resource) {
        this.node = node;
        this.resource = resource;
    }

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }
}
