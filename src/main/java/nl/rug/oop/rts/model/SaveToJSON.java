package nl.rug.oop.rts.model;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * SaveToJSON class.
 */
public class SaveToJSON {
    private Graph graph;
    private String fileName;

    private String ending = "\",\n";

    /**
     * SaveToJSON constructor.
     * @param graph The graph we want to save.
     * @param fileName The name of the file we save in.
     */
    public SaveToJSON(Graph graph, String fileName) {
        this.graph = graph;
        this.fileName = fileName;
    }

    /**
     * Method to write to JSON.
     * @param graph The graph we want to save.
     * @param fileName The name of the file we save in.
     */
    public void writeToFile(Graph graph, String fileName) {
        try {

            File newFile;

            if (fileName.endsWith(".json")) {
                newFile = new File(fileName);
            } else {
                newFile = new File(fileName + ".json");
            }

            if (newFile.createNewFile()) {
                System.out.println("Created a new file: " + newFile.getName());
            } else {
                System.out.println("Already exists");
            }

            FileWriter fileWriter = new FileWriter(newFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            writeGraph(graph, bufferedWriter);
            bufferedWriter.close();

        } catch (IOException e) {
            System.err.println("IO exception");
        }
    }

    /**
     * Method to save the graph.
     * @param graph The graph we want to save.
     * @param bufferedWriter File writer.
     */
    public void writeGraph(Graph graph, BufferedWriter bufferedWriter) {
        try {
            bufferedWriter.write("{\n");
            bufferedWriter.write(" \"Nodes\": [\n");

            for (Node node : graph.getNodes()) {
                writeNode(node, bufferedWriter);
            }

            bufferedWriter.write(" ],\n");

            bufferedWriter.write(" \"Edges\": [\n");

            for (Edge edge : graph.getEdges()) {
                writeEdge(edge, bufferedWriter);
            }

            bufferedWriter.write(" ]\n");

            bufferedWriter.write("}\n");
        } catch (IOException e) {
            System.err.println("IO exception in graph");
        }
    }

    /**
     * Method to save a node.
     * @param node The node we want to save.
     * @param bufferedWriter File writer.
     */
    public void writeNode(Node node, BufferedWriter bufferedWriter) {
        try {
            bufferedWriter.write(" {\n");
            bufferedWriter.write("  \"Id\": " + node.getId() + ",\n");
            bufferedWriter.write("  \"Name\": \"" + node.getName() + ending);
            bufferedWriter.write("  \"Armies\": [\n");

            for (Army army : node.getArmies()) {
                writeArmyNode(army, node, bufferedWriter);
            }

            bufferedWriter.write("  ],\n");
            bufferedWriter.write("  \"Events\": [\n");

            for (Event event : node.getEvents()) {
                if (event == node.getEvents().get(node.getEvents().size() - 1)) {
                    bufferedWriter.write("   \"" + event.getName() + "\"\n");
                } else {
                    bufferedWriter.write("   \"" + event.getName() + ending);
                }
            }

            bufferedWriter.write("  ]\n");

            int size = graph.getNodes().size();

            if (node.getId() == graph.getNodes().get(size - 1).getId()) {
                bufferedWriter.write(" }\n");
            } else {
                bufferedWriter.write(" },\n");
            }
        } catch (IOException e) {
            System.err.println("IO exception in node");
        }
    }

    /**
     * Method to save an edge.
     * @param edge The edge we want to save
     * @param bufferedWriter File writer.
     */
    public void writeEdge(Edge edge, BufferedWriter bufferedWriter) {
        try {
            bufferedWriter.write(" {\n");
            bufferedWriter.write("  \"Id\": " + edge.getId() + ",\n");
            bufferedWriter.write("  \"Name\": \"" + edge.getName() + ending);
            bufferedWriter.write("  \"Node1\": \"" + edge.getNode1().getName() + ending);
            bufferedWriter.write("  \"Node2\": \"" + edge.getNode2().getName() + ending);
            bufferedWriter.write("  \"Armies\": [\n");

            for (Army army : edge.getArmies()) {
                writeArmyEdge(army, edge, bufferedWriter);
            }

            bufferedWriter.write("  ],\n");
            bufferedWriter.write("  \"Events\": [\n");

            for (Event event : edge.getEvents()) {
                bufferedWriter.write("   \"" + event.getName() + "\"\n");
                if (event == edge.getEvents().get(edge.getEvents().size() - 1)) {
                    bufferedWriter.write("   \"" + event.getName() + "\"\n");
                } else {
                    bufferedWriter.write("   \"" + event.getName() + ending);
                }
            }

            bufferedWriter.write("  ]\n");

            int size = graph.getEdges().size();

            if (edge.getId() == graph.getEdges().get(size - 1).getId()) {
                bufferedWriter.write(" }\n");
            } else {
                bufferedWriter.write(" },\n");
            }
        } catch (IOException e) {
            System.err.println("IO exception in edge");
        }
    }

    /**
     * Method to save an army from a node.
     * @param army The army we want to save.
     * @param node The node in which it is located.
     * @param bufferedWriter File writer.
     */
    public void writeArmyNode(Army army, Node node, BufferedWriter bufferedWriter) {
        try {
            bufferedWriter.write("  {\n");
            bufferedWriter.write("   \"Faction\": \"" + army.getFaction() + ending);
            bufferedWriter.write("   \"Team\": " + army.getTeam() + ",\n");
            bufferedWriter.write("   \"Units\": [\n");

            for (int i = 0; i < army.getUnits().size(); i++) {
                writeUnit(army.getUnits().get(i), army, bufferedWriter);
                if (i == army.getUnits().size() - 1) {
                    bufferedWriter.write("   }\n");
                } else {
                    bufferedWriter.write("   },\n");
                }
            }

            bufferedWriter.write("   ]\n");

            int size = node.getArmies().size();

            if (node.getArmies().indexOf(army) == size - 1) {
                bufferedWriter.write("  }\n");
            } else {
                bufferedWriter.write("  },\n");
            }
        } catch (IOException e) {
            System.err.println("IO exception in army node");
        }
    }

    /**
     * Method to save an army from an edge.
     * @param army The army we want to save.
     * @param edge The edge it is located on.
     * @param bufferedWriter File writer.
     */
    public void writeArmyEdge(Army army, Edge edge, BufferedWriter bufferedWriter) {
        try {
            bufferedWriter.write("  {\n");
            bufferedWriter.write("   \"Faction\": \"" + army.getFaction() + ending);
            bufferedWriter.write("   \"Team\": " + army.getTeam() + ",\n");
            bufferedWriter.write("   \"Units\": [\n");

            for (int i = 0; i < army.getUnits().size(); i++) {
                writeUnit(army.getUnits().get(i), army, bufferedWriter);
                if (i == army.getUnits().size() - 1) {
                    bufferedWriter.write("   }\n");
                } else {
                    bufferedWriter.write("   },\n");
                }
            }

            bufferedWriter.write("   ]\n");

            int size = edge.getArmies().size();

            if (edge.getArmies().indexOf(army) == size - 1) {
                bufferedWriter.write("  }\n");
            } else {
                bufferedWriter.write("  },\n");
            }
        } catch (IOException e) {
            System.err.println("IO exception in army edge");
        }
    }

    /**
     * Method to save a unit.
     * @param unit The unit we want to save.
     * @param army The army in which it is.
     * @param bufferedWriter File writer.
     */
    public void writeUnit(Unit unit, Army army, BufferedWriter bufferedWriter) {
        try {
            bufferedWriter.write("   {\n");
            bufferedWriter.write("    \"Name\": \"" + unit.getName() + ending);
            bufferedWriter.write("    \"Strength\": " + unit.getDamage() + ",\n");
            bufferedWriter.write("    \"Health\": " + unit.getHealth() + "\n");
        } catch (IOException e) {
            System.err.println("IO exception in unit");
        }
    }
}
