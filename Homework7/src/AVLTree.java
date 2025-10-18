/**
 * An AVL tree implementation to store and manage Stock objects.
 */
public class AVLTree {
    /**
     * Inner class representing a node in the AVL tree.
     */
    private class Node {
        Stock stock;
        Node left, right;
        int height;

        /**
         * Constructs a node with the given Stock object.
         *
         * @param stock The Stock object to be stored in the node.
         */
        Node(Stock stock) {
            this.stock = stock;
            this.height = 1;
        }
    }

    private Node root;

    /**
     * Inserts a Stock object into the AVL tree.
     *
     * @param stock The Stock object to insert.
     */
    public void insert(Stock stock) {
        root = insert(root, stock);
    }

    private Node insert(Node node, Stock stock) {
        if (node == null) {
            return new Node(stock);
        }

        int compareResult = stock.getSymbol().compareTo(node.stock.getSymbol());

        if (compareResult < 0) {
            node.left = insert(node.left, stock);
        } else if (compareResult > 0) {
            node.right = insert(node.right, stock);
        } else {
            // Symbol already exists, update the stock attributes
            node.stock = stock;
            return node;
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        // Left Left Case
        if (balance > 1 && stock.getSymbol().compareTo(node.left.stock.getSymbol()) < 0) {
            return rightRotate(node);
        }

        // Right Right Case
        if (balance < -1 && stock.getSymbol().compareTo(node.right.stock.getSymbol()) > 0) {
            return leftRotate(node);
        }

        // Left Right Case
        if (balance > 1 && stock.getSymbol().compareTo(node.left.stock.getSymbol()) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && stock.getSymbol().compareTo(node.right.stock.getSymbol()) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    /**
     * Deletes a node with the specified symbol from the AVL tree.
     *
     * @param symbol The symbol of the Stock object to delete.
     */
    public void delete(String symbol) {
        root = delete(root, symbol);
    }

    private Node delete(Node node, String symbol) {
        if (node == null) {
            return null;
        }

        int compareResult = symbol.compareTo(node.stock.getSymbol());

        if (compareResult < 0) {
            node.left = delete(node.left, symbol);
        } else if (compareResult > 0) {
            node.right = delete(node.right, symbol);
        } else {
            // Found the node to be deleted

            // Node with only one child or no child
            if (node.left == null || node.right == null) {
                Node temp = node.left != null ? node.left : node.right;

                if (temp == null) {
                    temp = node;
                    node = null;
                } else {
                    node = temp;
                }
            } else {
                // Node with two children: Get the inorder successor (smallest in the right subtree)
                Node temp = minValueNode(node.right);

                // Copy the inorder successor's data to this node
                node.stock = temp.stock;

                // Delete the inorder successor
                node.right = delete(node.right, temp.stock.getSymbol());
            }
        }

        if (node == null) {
            return null;
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        // Left Left Case
        if (balance > 1 && getBalance(node.left) >= 0) {
            return rightRotate(node);
        }

        // Left Right Case
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Right Case
        if (balance < -1 && getBalance(node.right) <= 0) {
            return leftRotate(node);
        }

        // Right Left Case
        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    /**
     * Finds the node with the minimum value in the subtree rooted at the given node.
     *
     * @param node The root node of the subtree.
     * @return The node with the minimum value.
     */
    private Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    /**
     * Searches for a Stock object with the specified symbol in the AVL tree.
     *
     * @param symbol The symbol of the Stock object to search for.
     * @return The Stock object if found, or null if not found.
     */
    public Stock search(String symbol) {
        Node result = search(root, symbol);
        return (result != null) ? result.stock : null;
    }

    private Node search(Node node, String symbol) {
        if (node == null || node.stock.getSymbol().equals(symbol)) {
            return node;
        }

        int compareResult = symbol.compareTo(node.stock.getSymbol());

        if (compareResult < 0) {
            return search(node.left, symbol);
        } else {
            return search(node.right, symbol);
        }
    }

    /**
     * Calculates the height of the given node in the AVL tree.
     *
     * @param node The node for which to calculate the height.
     * @return The height of the node.
     */
    private int height(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    /**
     * Calculates the balance factor of the given node in the AVL tree.
     *
     * @param node The node for which to calculate the balance factor.
     * @return The balance factor of the node.
     */
    private int getBalance(Node node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    /**
     * Performs a right rotation on the given node in the AVL tree.
     *
     * @param y The node to perform the rotation on.
     * @return The new root node after rotation.
     */
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T = x.right;

        // Perform rotation
        x.right = y;
        y.left = T;

        // Update heights
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        // Return new root
        return x;
    }

    /**
     * Performs a left rotation on the given node in the AVL tree.
     *
     * @param x The node to perform the rotation on.
     * @return The new root node after rotation.
     */
    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T = y.left;

        // Perform rotation
        y.left = x;
        x.right = T;

        // Update heights
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        // Return new root
        return y;
    }

    /**
     * Performs an in-order traversal of the AVL tree and prints the Stock objects.
     */
    public void inOrderTraversal() {
        inOrderTraversal(root);
    }

    private void inOrderTraversal(Node node) {
        if (node != null) {
            inOrderTraversal(node.left);
            System.out.println(node.stock);
            inOrderTraversal(node.right);
        }
    }
}
