package com.java.datastructures;

public class BinarySearchTree
{
    private class Node
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

    void preOrderPrint(Node root)
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

    void inOrderPrint(Node root)
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

    void postOrderPrint(Node root)
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

    int treeHeight(Node node)
    {
        try
        {
            if (node == null)
            {
                return 0;
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

    int minElement(Node node)
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

            System.out.println("\n\nInorder traversal (left, root, right) [ascending order]");

            tree.inOrderPrint();

            System.out.println("\nFind 8: " + tree.find(8));

            System.out.println("Find 5: " + tree.find(5));

            System.out.println("\nPreorder traversal (root, left, right)");

            tree.preOrderPrint();

            System.out.println("\n\nPostorder traversal (left, right, root)");

            tree.postOrderPrint();

            System.out.println("\n\nHeight of the tree: " + tree.treeHeight());

            System.out.println("Minimum value in the tree: " + tree.minElement());

        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

}
