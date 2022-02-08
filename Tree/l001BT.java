import java.util.*;

public class l001BT{
    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val){
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }
    
    public int size(TreeNode root){
        return root!=null? size(root.left) + size(root.right) + 1 : 0; //left se size and right call +1 in terms of nodes
    }
    public int height(TreeNode root){ //in terms of edges 
        return root==null? -1: Math.max(height(root.left) , height(root.right))+1;
    }
    public static int Maximum(TreeNode root){
        return root==null?-(int) 1e9:Math.max(Math.max(Maximum(root.left) , Maximum(root.right)) , root.val);
    }
    public static boolean find(TreeNode root , int data){
        if(root==null) return false;
        if(root.val==data) return true;
        return find(root.left , data) || find(root.right , data);
    }
    public static ArrayList<TreeNode> NodeToRootPath(TreeNode root , int data){
        if(root==null) return new ArrayList<>();
        if(root.val==data){
            ArrayList<TreeNode> base = new ArrayList<>();
            base.add(root);
            return base;
        }
        ArrayList<TreeNode> left = NodeToRootPath(root.left, data);
        if(left.size()>0){
            left.add(root);
            return left;

        }
        ArrayList<TreeNode> right = NodeToRootPath(root.right , data);
            if(right.size()>0){
                right.add(root);
                return right;
            }
        return new ArrayList<>();
    }

    public static boolean nodeToRootPath(TreeNode root , int data , ArrayList<TreeNode> ans){
        if(root==null) return false;
        if(root.val==data){
            ans.add(root);
            return true;
        }
        boolean res = nodeToRootPath(root.left, data, ans) || nodeToRootPath(root.right, data, ans);
        if(res){
            ans.add(root);
        }
    
        return res;
    }

    public static void nodeToAllLeafPath(TreeNode root , ArrayList<Integer> smallAns , ArrayList<ArrayList<Integer>> ans){
        if(root==null) return;
        if(root.left==null&& root.right==null){  //check leaf node
            ArrayList<Integer> base = new ArrayList<>(smallAns);
            base.add(root.val);
            ans.add(base);
            return;
        }
        smallAns.add(root.val);
        nodeToAllLeafPath(root.left, smallAns, ans);
        nodeToAllLeafPath(root.right, smallAns, ans);
        smallAns.remove(smallAns.size()-1);
    }
    public static ArrayList<ArrayList<Integer>> rootToAllLeafPath(TreeNode root){
        ArrayList<Integer> smallAns = new ArrayList<>();
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        nodeToAllLeafPath( root , smallAns , ans);
        return ans;
    }

    public static void singleChildNodes(TreeNode node , ArrayList<Integer> ans){
        if(node==null || node.left==null && node.right==null) return;
        if(node.left==null || node.right==null){
            ans.add(node.val);
        }
        singleChildNodes(node.left, ans);
        singleChildNodes(node.right, ans);
    }

    public static int countSingleChildNodes(TreeNode node){
        if(node==null || (node.left==null && node.right==null)){
            return 0;
        }

        int left = countSingleChildNodes(node.left);
        int right = countSingleChildNodes(node.right);
        
        int ans = left+right;
        if(node.left==null || node.right==null){
            ans++;
        }
        return ans;
    
    }
    public void kDown(TreeNode root , TreeNode blockNode , int k ,List<Integer> ans){
        if(root==null || blockNode ==root || k<0){
            return;
        }
        if(k==0) {
            ans.add(root.val);
            return ;
        }
        
        kDown(root.left, blockNode, k-1, ans);
        kDown(root.right, blockNode, k-1, ans);
    }

    
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        ArrayList<TreeNode> path = new ArrayList<>();
        nodeToRootPath(root, target.val, path);

        List<Integer> ans = new ArrayList<>();
        TreeNode blockNode = null;
        for (int i = 0; i < path.size(); i++) {
            if (K - i < 0)
                break;
            kDown(path.get(i), blockNode, K - i, ans);
            blockNode = path.get(i);
        }

        return ans;
    }
    //1 way of kdown    
    public void kdown(TreeNode node ,TreeNode blockNode , int k , ArrayList<Integer> ans){
        if(node==null ||blockNode==node || k<0 ) return;
        if(k==0){
            ans.add(node.val);
            return;
        }
        kdown(node.left, blockNode, k-1, ans);
        kdown(node.right, blockNode, k-1, ans);
    }
    //2  way of kdown 

    public int distance_01(TreeNode root , TreeNode target , int k , ArrayList<Integer> ans){
        if(root==null){
            return -1;
        }
        if (root == target){
            kdown(root , null ,k , ans );
            return 1;
        }
        int ld = distance_01(root.left , target , k , ans);
        if(ld!=-1){
            kDown(root, root.left, k-ld, ans);
            return ld+1;
        }
        int rd = distance_01(root.right, target, k, ans);
        if(rd!=-1){
            kDown(root, root.right, k-rd, ans);
            return rd+1;
        }
        return -1;
    }

    //buring tree
    public static void kdown(TreeNode root , int time , TreeNode blockNode , ArrayList<ArrayList<Integer>> ans){
        if(root==null || root == blockNode){
            return;
        }
        if(time==ans.size())
            ans.add(new ArrayList<>());
        ans.get(time).add(root.val);
        
            kdown(root.left, time+1, blockNode, ans);
            kdown(root.right, time+1, blockNode, ans);
        
    }

    public int burningTree(TreeNode root , TreeNode target , ArrayList<Integer> ans){
        if(root==null){
            return -1;
        }
        if (root == target){
            kdown(root , null , 0  , ans );
            return 1;
        }
        int ld = burningTree(root.left , target  , ans);
        if(ld!=-1){
            kDown(root, root.left, ld, ans);
            return ld+1;
        }
        int rd = burningTree(root.right, target, ans);
        if(rd!=-1){
            kDown(root, root.right, rd, ans);
            return rd+1;
        }
        return -1;
    }

    public static void kdown(TreeNode root, int time, TreeNode blockNode, ArrayList<ArrayList<Integer>> ans,HashSet<Integer> water) {
        if (root == null || root == blockNode || water.contains(root.val))
            return;

             if (time == ans.size())
                ans.add(new ArrayList<>());
            ans.get(time).add(root.val);

            kdown(root.left, time + 1, blockNode, ans);
            kdown(root.right, time + 1, blockNode, ans);
        }
        
        // -1 : did we gett the target node, -2 : fire will not reach that node, t > 0 :
        // fire will reach with time t.
        public static int burningTreeWithWater(TreeNode root, int target, ArrayList<ArrayList<Integer>> ans,
            HashSet<Integer> water) {
        if (root == null)
            return -1;
        if (root.val == target) {
            if (!water.contains(root.val)) {
                kdown(root, 0, null, ans);
                return 1;
            } else
                return -2;
        }

        int ld = burningTreeWithWater(root.left, target, ans, water);
        if (ld > 0) {
            if (!water.contains(root.val)) {
                kdown(root, ld, root.left, ans);
                return ld + 1;
            }
            return -2;
        }
        if (ld == -2)
            return -2;

        int rd = burningTreeWithWater(root.right, target, ans, water);
        if (rd > 0) {
            if (!water.contains(root.val)) {
                kdown(root, rd, root.right, ans);
                return rd + 1;
            }
            return -2;
        }
        if (rd == -2)
            return -2;

        return -1;
        }

    public static void burningTree(TreeNode root , int target) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        burningTree(root, target , ans);
    }



      // LCA===================================================================
    //2nd Method of LCA
    TreeNode LCA = null;

    public boolean lowestCommonAncestor_(TreeNode node, TreeNode p, TreeNode q) {
        if (node == null)
            return false;

        boolean self = false;
        if (node == p || node == q)
            self = true;

        boolean left = lowestCommonAncestor_(node.left, p, q);
        boolean right = lowestCommonAncestor_(node.right, p, q);

        if ((left && right) || (left && self) || (right && self))
            LCA = node;

        return left || right || self;
    }

    public TreeNode lowestCommonAncestor(TreeNode node, TreeNode p, TreeNode q) {
        lowestCommonAncestor_(node, p, q);
        return LCA;
    }

    public static class pair{
        TreeNode node;
        int idx;
        pair(TreeNode node , int idx){
            this.node = node;
            this.idx = idx;
        }
    }

    public static int maxwidth(TreeNode node){
        LinkedList<pair> que = new LinkedList<>();
        que.addFirst(new pair(node , 0));
        int max = 0;
        while(que.size()>0){
            int s = que.size();
            int lm = que.getFirst().idx;
            int rm = que.getFirst().idx;
            while(s-->0){
                pair rp = que.removeFirst();
                rm = rp.idx;
                if(rp.node.left!=null) que.addFirst(new pair(rp.node.left , 2*rm+1 ));
                if(rp.node.right!=null) que.addFirst(new pair(rp.node.right , 2*rm+2 ));

            }
            max = rm-lm+1;
        }
        return max;
    }
}