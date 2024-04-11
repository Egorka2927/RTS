package nl.rug.oop.rts.controller;

import nl.rug.oop.rts.model.Edge;
import nl.rug.oop.rts.model.Node;

import java.util.List;

/**
 * Graph listener interface.
 */

public interface GraphListener {
    void graphUpdated(List<Node> nodes, List<Edge> edges, Node selectedNode, Edge selectedEdge);
}
