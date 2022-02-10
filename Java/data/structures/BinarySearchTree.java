package com.java.data.structures;

public class BinarySearchTree
{
    private static class Node
    {
        private int value;

        private Node leftChild;

        private Node rightChild;

        public Node(int value)
        {
            this.value = value;

            this.leftChild = null;

            this.rightChild = null;
        }
    }

    private Node root;

    public BinarySearchTree()
    {
        this.root = null;
    }

    void insert(int value)
    {
        try
        {
            Node node = new Node(value);

            if (root == null)
            {
                root = node;
            }
            else
            {
                Node compare = root;

                Node insertNode = null;

                while (compare != null)
                {
                    if (value == compare.value)
                    {
                        System.out.format("Value %d already present", value);

                        return;
                    }
                    else if (value > compare.value)
                    {
                        insertNode = compare;

                        compare = compare.rightChild;
                    }
                    else
                    {
                        insertNode = compare;

                        compare = compare.leftChild;
                    }
                }

                if (value > insertNode.value)
                {
                    insertNode.rightChild = node;
                }
                else
                {
                    insertNode.leftChild = node;
                }
            }

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    boolean find(int value)
    {
        Node n = root;

        try
        {
            while (n != null)
            {
                if (n.value == value)
                {
                    return true;
                }
                else if (value > n.value)
                {
                    n = n.rightChild;
                }
                else
                {
                    n = n.leftChild;
                }
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return false;
    }

    void preOrderPrint()
    {
        preOrderPrint(root);
    }

    private void preOrderPrint(Node root)
    {
        try
        {
            //base condition
            if (root == null)
            {
                return;
            }

            System.out.print(root.value + " ");

            preOrderPrint(root.leftChild);

            preOrderPrint(root.rightChild);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }

    void inOrderPrint()
    {
        inOrderPrint(root);
    }

    private void inOrderPrint(Node root)
    {
        try
        {
            // base case
            if (root == null)
            {
                return;
            }

            inOrderPrint(root.leftChild);

            System.out.print(root.value + " ");

            inOrderPrint(root.rightChild);

        }

        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }

    void postOrderPrint()
    {
        postOrderPrint(root);
    }

    private void postOrderPrint(Node root)
    {
        try
        {
            // base case
            if (root == null)
            {
                return;
            }

            postOrderPrint(root.leftChild);

            postOrderPrint(root.rightChild);

            System.out.print(root.value + " ");

        }

        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }

    int treeHeight()
    {
        return treeHeight(root);
    }

    private int treeHeight(Node node)
    {
        try
        {
            if (node == null)
            {
                return -1;
            }

//            if (treeHeight(node.leftChild) > treeHeight(node.rightChild))
//            {
//                return 1 + treeHeight(node.leftChild);
//            }
//            else
//            {
//                return 1 + treeHeight(node.rightChild);
//            }

            return 1 + Math.max(treeHeight(node.leftChild), treeHeight(node.rightChild));   //using post order traversal
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return -1;
    }

    int minElement()
    {
        return minElement(root);
    }

    private int minElement(Node node)
    {
        try
        {
            if (node == null)
            {
                return Integer.MAX_VALUE;
            }

            int min = Math.min(minElement(node.leftChild), minElement(node.rightChild));

            return Math.min(min, node.value);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return Integer.MAX_VALUE;
    }

    boolean equals(BinarySearchTree binarySearchTree)
    {
        if (binarySearchTree == null)
        {
            return false;
        }

        return equals(root, binarySearchTree.root);
    }

    private boolean equals(Node root1, Node compareRoot)
    {
        try
        {
            if (compareRoot == null && root1 == null)
            {
                return true;
            }
            if (compareRoot != null && root1 != null)
            {
                return root1.value == compareRoot.value
                        && equals(root1.leftChild, compareRoot.leftChild)
                        && equals(root1.rightChild, compareRoot.rightChild);
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return false;           //when one root is null and other isn't null
    }

    boolean validate()
    {
        return validate(this.root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean validate(Node node, int min, int max)
    {
        try
        {
            if (node == null)
            {
                return true;
            }

            return (min < node.value && node.value < max)
                    && validate(node.leftChild, min, node.value)
                    && validate(node.rightChild, node.value, max);

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

        return false;
    }

    public void nodeKDistance(int distance)
    {
        nodeKDistance(root, distance, 0);
    }

    private void nodeKDistance(Node node, int distance, int currentDistance)
    {
        try
        {
            if (node == null)
            {
                return;
            }

            if (distance == currentDistance)
            {
                System.out.println(node.value);
            }

            nodeKDistance(node.leftChild, distance, currentDistance + 1);

            nodeKDistance(node.rightChild, distance, currentDistance + 1);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

    }

    void levelOrderTraversal()
    {
        try
        {
            for (int i = 0; i <= treeHeight(); i++)
            {
                nodeKDistance(i);
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
            BinarySearchTree tree = new BinarySearchTree();

            tree.insert(7);

            tree.insert(4);

            tree.insert(9);

            tree.insert(1);

            tree.insert(6);

            tree.insert(8);

            tree.insert(8);

            tree.insert(10);

            BinarySearchTree tree1 = new BinarySearchTree();

            tree1.insert(7);

            tree1.insert(4);

            tree1.insert(9);

            tree1.insert(1);

            tree1.insert(6);

            tree1.insert(8);

            tree1.insert(10);

            System.out.println("\n\nTrees are equal: " + tree.equals(tree1));

            System.out.println("\nInorder traversal (left, root, right) [ascending order]");

            tree.inOrderPrint();

            System.out.println("\nFind 8: " + tree.find(8));

            System.out.println("Find 5: " + tree.find(5));

            System.out.println("\nPreorder traversal (root, left, right)");

            tree.preOrderPrint();

            System.out.println("\n\nPostorder traversal (left, right, root)");

            tree.postOrderPrint();

            System.out.println("\n\nHeight of the tree: " + tree.treeHeight());

            System.out.println("Minimum value in the tree: " + tree.minElement());

            Node root = new Node(10);

            root.leftChild = new Node(8);

            root.rightChild = new Node(20);

            root.leftChild.leftChild = new Node(11);

            root.leftChild.rightChild = new Node(9);

//             Tree visualization
//                     10
//             8                20
//        11      9

            System.out.println("Tree validation: " + tree.validate(root, Integer.MIN_VALUE, Integer.MAX_VALUE));

            System.out.println("\nNodes at distance 2 from the tree.");

            tree.nodeKDistance(2);

            System.out.println("\nLevel order traversal:");

            tree.levelOrderTraversal();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

}
