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
        return root!=null? size(root.left) + size(root.right) + 1 : 0;
    }
    public int height(TreeNode root){
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
        if(root.left==null&& root.right==null){
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
        rootToAllLeafPath( root , smallAns , ans);
        return ans;
    }

    public static void singleChildNodes(TreeNode node , ArrayList<Integer> ans){
        if(node==null || node.left && node.right) return;
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

    public List<Integer>  distanceK(TreeNode root , TreeNode target ,int k ){
        ArrayList<TreeNode> path = new ArrayList<>();
        NodeToRootPath(root ,target.val , path);
        for(int i= 0 ;i < path.size();i++){
            if(k-i>0){
                kdown(path.get(i) , i==0?null || path.get(i-1) , k-i , ans):
            }
        }
        return ans;
    }
    public void kdown(TreeNode node ,TreeNode blockNode , int k , ArrayList<Integer> ans){
        if(node==null ||blockNode==root || k<0 ) return;
        if(k==0){
            ans.add(root.val);
            return;
        }
        kdown(root.left, blockNode, k-1, ans);
        kdown(node.right, blockNode, k-1, ans);
    }
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

    public static void kdown(TreeNode root , int time , TreeNode blockNOde , ArrayList<ArrayList<Integer>> ans){
        if(root==null || root == blockNode){
            return;
        }
        
    }
    public int burningTree(TreeNode root , TreeNode target , ArrayList<Integer> ans){
        if(root==null){
            return -1;
        }
        if (root == target){
            kdown(root , null , 0  , ans );
            return 1;
        }
        int ld = distance_01(root.left , target , k , ans);
        if(ld!=-1){
            kDown(root, root.left, ld, ans);
            return ld+1;
        }
        int rd = distance_01(root.right, target, k, ans);
        if(rd!=-1){
            kDown(root, root.right, rd, ans);
            return rd+1;
        }
        return -1;
    }
}