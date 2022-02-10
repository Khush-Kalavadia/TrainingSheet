package com.java.data.structures;

import java.util.*;

public class Graph
{
    private class Node
    {
        private int label;

        public Node(int label)
        {
            this.label = label;
        }

        @Override
        public String toString()
        {
            return label + "";
        }
    }

    private Map<Integer, Node> nodes = new HashMap<>();

    private Map<Node, List<Node>> adjacencyList = new HashMap<>();

    public void addNode(int label)
    {
        Node node = new Node(label);

        nodes.putIfAbsent(label, node);

        adjacencyList.putIfAbsent(node, new ArrayList<>());
    }

    public void addEdge(int from, int to)
    {
        try
        {
            Node nodeFrom = nodes.get(from);

            Node nodeTo = nodes.get(to);

            if (nodeFrom == null || nodeTo == null)
            {
                System.out.println("Node not present.");
            }

            adjacencyList.get(nodeFrom).add(nodeTo);

//            adjacencyList.get(nodeTo).add(nodeFrom);        //to implement non directed graph
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }


    public void print()
    {
        try
        {
            for (Node n : adjacencyList.keySet())
            {
                System.out.print(n.label + " is connected to ");

                System.out.println(adjacencyList.get(n));
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void removeNode(int label)
    {
        try
        {
            Node node = nodes.get(label);

            if (node == null)
            {
                System.out.println("No node found");

                return;
            }

//            List<Node> list = adjacencyList.get(node);

            for (Node n : adjacencyList.keySet())
            {
                List<Node> list = adjacencyList.get(n);

                list.remove(node);
            }

            adjacencyList.remove(node);

            nodes.remove(node);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void removeEdge(int from, int to)
    {
        try
        {
            Node toNode = nodes.get(to);

            Node fromNode = nodes.get(from);

            if (fromNode == null || toNode == null)
            {
                System.out.println("Node not present");

                return;
            }

            List<Node> list = adjacencyList.get(fromNode);

            if (!list.remove(toNode))
            {
                System.out.println("Edge not present");
            }

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        try
        {
            Graph graph = new Graph();

            graph.addNode(1);

            graph.addNode(2);

            graph.addNode(3);

            graph.addNode(4);

            graph.addEdge(1, 3);

            graph.addEdge(1, 4);

            graph.addEdge(2, 3);

            graph.addEdge(4, 3);

            graph.print();

            System.out.println("\nRemove node 2");

            graph.removeNode(2);

            graph.print();

            System.out.println("\nRemove edge 1,4");

            graph.removeEdge(1, 4);

            graph.print();

            System.out.println("\nRemove edge 3,4");

            graph.removeEdge(3, 4);

//            graph.print();

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

}
