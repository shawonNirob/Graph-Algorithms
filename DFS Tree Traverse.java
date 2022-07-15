//Preorder
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList();
        if(root==null) return list;
        Stack<TreeNode> stack = new Stack();
        stack.add(root);
        
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            list.add(node.val);
            
            if(node.right != null) stack.add(node.right);
            if(node.left != null) stack.add(node.left);
        }
        return list;
    }
}


//Postorder
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList();
        if(root==null) return list;
        Stack<TreeNode> stack = new Stack();
        stack.add(root);
        
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            list.add(node.val);
            if(node.right != null) stack.add(node.right);
            if(node.left != null) stack.add(node.left);
            
        }
        Collections.reverse(list);
        return list;
    }
}
//
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> list = new LinkedList();
        if(root==null) return list;
        Stack<TreeNode> stack = new Stack();
        stack.add(root);
        
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            list.addFirst(node.val);
            if(node.left != null) stack.add(node.left);
            if(node.right != null) stack.add(node.right);
            
        }
        return list;
    }
}


//Inorder
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root==null) return list;
        Stack<TreeNode> stack = new Stack();
        TreeNode curr = root;
        
        while(curr != null || !stack.isEmpty()){
            while(curr != null){
                stack.push(curr);
                curr=curr.left;
            }
            curr= stack.pop();
            list.add(curr.val);
            curr = curr.right;
        }
        return list;
    }
}
//Morris Inorder Traverse 
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root==null) return list;
        
        TreeNode curr = root;
        TreeNode pre;
        
        while(curr != null){
            if(curr.left == null){
                list.add(curr.val);
                curr = curr.right;
            }else{
                pre = curr.left;
                while(pre.right != null){
                    pre = pre.right;
                }
                pre.right = curr;
                
                TreeNode temp = curr.left;
                curr.left = null;
                curr = temp;
            }
        }
        return list;
    }
}


