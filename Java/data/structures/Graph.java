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

    public void traverseDepthFirstRec(int root)
    {
        if (nodes.containsKey(root))
        {
            traverseDepthFirstRec(nodes.get(root), new HashSet<>());
        }
        else
        {
            System.out.println("Node does exist");
        }
    }

    private void traverseDepthFirstRec(Node root, Set<Node> visited)
    {
        try
        {
            System.out.println(root);

            visited.add(root);

            for (Node connectedNode : adjacencyList.get(root))
            {
                if (!visited.contains(connectedNode))
                {
                    traverseDepthFirstRec(connectedNode, visited);
                }
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

//    public void traverseDepthFirstIte(int root)
//    {
//        Stack<Node> stack = new Stack<>();
//
//        Set<Node> visited = new HashSet<>();
//
//        stack.push(nodes.get(root));
//
//        while (!stack.isEmpty())
//        {
//            Node current = stack.pop();
//
//            if (visited.contains(current))
//            {
//                continue;
//            }
//
//            System.out.println(current);
//
//            visited.add(current);
//
//            for (Node neighbour : adjacencyList.get(current))
//            {
//                if (!visited.contains(neighbour))
//                    stack.push(neighbour);
//            }
//        }
//    }

    public void traverseBreadthFirst(int root)
    {
        try
        {
            if (!nodes.containsKey(root))
            {
                System.out.println("Root Node not present");
            }
            else
            {
                Node node = nodes.get(root);

                Set<Node> visited = new HashSet<>();

                Queue<Node> queue = new ArrayDeque<>();

                queue.add(node);

                while (!queue.isEmpty())
                {
                    Node current = queue.remove();

                    if (visited.contains(current))
                    {
                        continue;
                    }

                    System.out.println(current);

                    visited.add(current);

                    for (Node neighbour : adjacencyList.get(current))
                    {
                        if (!visited.contains(neighbour))
                        {
                            queue.add(neighbour);
                        }
                    }
                }
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

            graph.addEdge(4, 2);

            graph.print();

            System.out.println("Traverse Breadth First from root 1");

            graph.traverseBreadthFirst(1);

            System.out.println("Traverse Depth First from root 1");

            graph.traverseDepthFirstRec(1);

            System.out.println("Traverse Depth First from root 2 using iterative approach");

            graph.traverseDepthFirstRec(2);

            System.out.println("Traverse Depth First from root 5");

            graph.traverseDepthFirstRec(5);

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
