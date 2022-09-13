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
//Morris preOrder traverse
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root==null) return list;
        
        TreeNode curr = root;
        TreeNode pre;
        
        while(root != null){
            if(root.left == null){
                list.add(root.val);
                root = root.right;
            }else{
                pre = root.left;
                while(pre.right != null && pre.right != root){
                    pre = pre.right;
                }
                if(pre.right == root){
                    root = root.right;
                    pre.right = null;
                }else{
                    list.add(root.val);
                    pre.right = root;
                    root = root.left;
                }
            }
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
            if(node.left != null) stack.add(node.left);
            if(node.right != null) stack.add(node.right);
            
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
//1. Go left Most
//2. root = pop from stack the left most,  and set root =  root.right;
//repeat 1 and 2; 
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root==null) return list;
        Stack<TreeNode> stack = new Stack();
        
        while(root != null || !stack.isEmpty()){
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            list.add(root.val);
            root = root.right;
        }
        return list;
    }
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


