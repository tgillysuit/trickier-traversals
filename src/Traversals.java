import java.util.*;

public class Traversals {

  /**
   * Returns the sum of the values of all leaf nodes in the given tree of integers.
   * A leaf node is defined as a node with no children.
   * If node is null, this method returns 0.
   *
   * @param node the node of the tree
   * @return the sum of leaf node values, or 0 if the tree is null
   */
  public static int sumLeafNodes(TreeNode<Integer> node) {
    if (node == null) return 0; // We will return 0 if the node is null
    
    if (node.left == null && node.right == null) return node.value; // We will return the value of the "root" or the only leaf node
    
    return sumLeafNodes(node.left) + sumLeafNodes(node.right); // We will add up and return the left and right leaf nodes
  }

  /**
   * Counts the number of internal nodes (non-leaf nodes) in the given tree of integers.
   * An internal node has at least one child.
   * If node is null, this method returns 0.
   *
   * @param node the node of the tree
   * @return the count of internal nodes, or 0 if the tree is null
   */
  public static int countInternalNodes(TreeNode<Integer> node) {
    if (node == null) return 0; // Return 0 if the node is null
    
    if (node.left == null && node.right == null) return 0; // return 0 because these are leafs and we're trying to find the internal nodes in between the root and the leaf nodes.
    
    return countInternalNodes(node.left) + countInternalNodes(node.right) + 1; // We add the left and right nodes + the root and return the total.
  }

  /**
   * Creates a string by concatenating the string representation of each node's value
   * in a post-order traversal of the tree. For example, if the post-order visitation
   * encounters values "a", "b", and "c" in that order, the result is "abc".
   * If node is null, returns an empty string.
   *
   * @param node the node of the tree
   * @param <T>  the type of values stored in the tree
   * @return a post-order traversal string, or an empty string if the tree is null
   */
  public static <T> String buildPostOrderString(TreeNode<T> node) {
    if (node == null) return ""; // return empty string if the node is null

    String left = buildPostOrderString(node.left); // we set the value of the left subtree to a string
    String right = buildPostOrderString(node.right); // we set the value of the right subtree to a string

    return left + right + node.value.toString(); // then return the nodes in post-order with converting the root toString()
  }

  /**
   * Collects the values of all nodes in the tree level by level, from top to bottom.
   * If node is null, returns an empty list.
   *
   * @param node the node of the tree
   * @param <T>  the type of values stored in the tree
   * @return a list of node values in a top-to-bottom order, or an empty list if the tree is null
   */
  public static <T> List<T> collectLevelOrderValues(TreeNode<T> node) {
    List<T> result = new ArrayList<>(); // we create a new empty list 
    if (node == null) return result; // we return that empty list if the node is null

    Queue<TreeNode<T>> queue = new LinkedList<>(); // creating a queue to help with level order traversal
    queue.add(node); // add the node to the queue

    while(!queue.isEmpty()) { // traverse through the nodes
      TreeNode<T> current = queue.poll(); // pop the current node from the Queue
      result.add(current.value); // add the current value to the ArrayList

      if (current.left != null) queue.add(current.left); // if the left subtree is not null, add the current left node to the Queue 
      if (current.right != null) queue.add(current.right); // if the right subtree is not null, add the current right node to the Queue 
    }

    return result; // return the ArrayList
  }

  /**
   * Counts the distinct values in the given tree.
   * If node is null, returns 0.
   *
   * @param node the node of the tree
   * @return the number of unique values in the tree, or 0 if the tree is null
   */
  public static int countDistinctValues(TreeNode<Integer> node) {
    if (node == null) return 0; // if node is null return 0
    
    Set<Integer> unique = new HashSet<>(); // creating a Set to store key-value pairs (nodes, uniques)
    helpCountDistinctValues(node, unique); // calling the helper method
    
    return unique.size(); // returning the size of Set
  }

  // Helper Method for countDistinctValues
  private static void helpCountDistinctValues(TreeNode<Integer> node, Set<Integer> unique) {
    if (node == null) return; // return nothing because this is a void method
    
    unique.add(node.value); // add the value of the node to the Set
    helpCountDistinctValues(node.left, unique); // traverse to the left and add the unique nodes and their uniques
    helpCountDistinctValues(node.right, unique); // traverse to the right and add the unique nodes and their uniques
  }

  /**
   * Determines whether there is at least one root-to-leaf path in the tree
   * where each successive node's value is strictly greater than the previous node's value.
   * If node is null, returns false.
   *
   * @param node the node of the tree
   * @return true if there exists a strictly increasing root-to-leaf path, false otherwise
   */
  public static boolean hasStrictlyIncreasingPath(TreeNode<Integer> node) {
    if (node == null) return false; // return false if null

    Queue<TreeNode<Integer>> queue = new LinkedList<>(); // create a Queue to store current node
    Queue<Integer> values = new LinkedList<>(); // create a Queue to store previous nodes

    queue.add(node); // adding the node to the Queue
    values.add(Integer.MIN_VALUE); // adding the Minimum value to the values Queue

    // While the Queue is not empty
    while (!queue.isEmpty()) {
      TreeNode<Integer> current = queue.poll(); // create a pointer set it the current node after being removed from the Queue
      int prevValue = values.poll(); // create a pointer for the previous value after being removed from the values Queue

      // if the current.value is greater than the previous value, we will check to see if the current left and right subtrees are null to return true.
      if (current.value > prevValue) {
        if (current.left == null && current.right == null) {
          return true;
        }
      }
      // if the current right subtree is not null, add the current right node to queue, and add the current.value to the values queue
      if (current.right != null) {
        queue.add(current.right);
        values.add(current.value);
      }
      // if the current left subtree is not null, add the current left node to queue, and add the current.value to the values queue
      if (current.left != null) {
        queue.add(current.left);
        values.add(current.value);
      }
    }
    // otherwise return false overall
    return false;
  }
  // OPTIONAL CHALLENGE
  /**
   * Checks if two trees have the same shape. Two trees have the same shape
   * if they have exactly the same arrangement of nodes, irrespective of the node values.
   * If both trees are null, returns true. If one is null and the other is not, returns false.
   *
   * @param nodeA the node of the first tree
   * @param nodeB the node of the second tree
   * @param <T>   the type of values stored in the trees
   * @return true if the trees have the same shape, false otherwise
   */
  public static <T> boolean haveSameShape(TreeNode<T> nodeA, TreeNode<T> nodeB) {
    return false;
  }


  // OPTIONAL CHALLENGE
  // Very challenging!
  // Hints:
  // List.copyOf may be helpful
  // Consider adding a helper method
  // Consider keeping the current path in a separate variable
  // Consider removing the current node from the current path after the node's subtrees have been traversed.
  /**
   * Finds all paths from the root to every leaf in the given tree.
   * Each path is represented as a list of node values from root to leaf.
   * The paths should be added pre-order.
   * If node is null, returns an empty list.
   * 
   * Example:
   *
   *         1
   *        / \
   *       2   3
   *      / \    \
   *     4   5    6
   * 
   * Expected output:
   *   [[1, 2, 4], [1, 2, 5], [1, 3, 6]]
   * 
   * @param node the root node of the tree
   * @return a list of lists, where each inner list represents a root-to-leaf path in pre-order
   */
  public static <T> List<List<T>> findAllRootToLeafPaths(TreeNode<T> node) {
    return null;
  }
}
