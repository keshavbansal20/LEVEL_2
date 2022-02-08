import java.util.*;
public class l001BT_VIEW {
    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val){
            this.val = val;
            this.left =null;
            this.right = null;
        }
    }
    
       
    }
    public static void levelOrder(TreeNode root){
        LinkedList<TreeNode> que = new LinkedList<>();
        que.addLast(root);
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        int level = 0;
        while(que.size()>0){
            int size = que.size();
            ArrayList<Integer> smallAns = new ArrayList<>();
            System.out.println(level);
            while(size>0){
                TreeNode rnode = que.removeFirst();
                smallAns.add(rnode.val);
                if(rnode.left!=null) que.addLast(rnode.left);
                if(rnode.right!=null) que.addLast(rnode.right);

            }
            ans.add(smallAns);
            level++;
        }

        int count = 0;
        for(ArrayList<Integer> list:ans){
            System.out.println(count++ +" -> " +list);
        }
    }

    public static List<Integer> leftView(TreeNode root){
        if(root==null){
            return new ArrayList<>();
        }
        LinkedList<TreeNode> que = new LinkedList<>();
        que.addLast(root);
        List<Integer> ans = new ArrayList<>();
        
        while(que.size()>0){
            int size = que.size();
           
            ans.add(que.getFirst().val);
            while(size>0){
                TreeNode rnode = que.removeFirst();
                
                if(rnode.left!=null) que.addLast(rnode.left);
                if(rnode.right!=null) que.addLast(rnode.right);

            }
          
            
        }

        return ans;
    }

    public static List<Integer> rightSideView(TreeNode root){
        if(root==null){
            return new ArrayList<>();
        }
        LinkedList<TreeNode> que = new LinkedList<>();
        que.addLast(root);
        List<Integer> ans = new ArrayList<>();
        
        while(que.size()>0){
            int size = que.size();
           
            ans.add(que.getLast().val);
            while(size-->0){
                TreeNode rnode = que.removeFirst();
                
                if(rnode.left!=null) que.addLast(rnode.left);
                if(rnode.right!=null) que.addLast(rnode.right);

            }
            
        }
        
        return ans;
    }

    public static void widthOfShadow(TreeNode node , int v1 , int[] minMax){
        if(node==null){
            return;
        }
        minMax[0] = Math.min(minMax[0] , v1);
        minMax[1] = Math.max(minMax[1] , v1);
        widthOfShadow(node.left, v1-1, minMax);
        widthOfShadow(node.right, v1+1, minMax);
    }

    public static class vPair{
        TreeNode node = null;
        int v1 = 0;
        int level = 0;
        vPair(TreeNode node , int v1){
            this(node, v1 , 0);
        }
        vPair(TreeNode node , int v1 , int level){
            this.v1 = v1;
            this.node = node;
            this.level = level;
        }
    }


    //print root value in vertical order
    public static ArrayList<ArrayList<Integer>> verticalOrderTraversal(TreeNode root){
        LinkedList<vPair> que = new LinkedList<>();
        int[] minMax = new int[2];
        widthOfShadow(root, 0, minMax);
        int length = minMax[1]-minMax[0]+1;
        que.add(new vPair(root , Math.abs(minMax[0])));
        
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        
        for(int i = 0 ; i < length ; i++){
            ans.add(new ArrayList<>());
        }
        
        while(que.size()>0){
            int size = que.size();
            while(size-->0){
                vPair rp =  que.removeFirst();
                int v1 = rp.v1;
                TreeNode node = rp.node;
                ans.get(v1).add(node.val);
                if(node.left!=null) que.addLast(new vPair(node.left , v1-1));
                if(node.right!=null) que.addLast(new vPair(node.right , v1+1));
            }
        }
        return ans;
    }

    public static ArrayList<Integer> bottomView(TreeNode root){
        LinkedList<vPair> que = new LinkedList<>();
        int[] minMax = new int[2];
        widthOfShadow(root, 0, minMax);
        int length = minMax[1]-minMax[0]+1;
        que.add(new vPair(root , Math.abs(minMax[0])));
        
        ArrayList<Integer> ans = new ArrayList<>();
        
        for(int i = 0 ; i < length ; i++){
            ans.add(null);
        }
        
        while(que.size()>0){
            int size = que.size();
            while(size-->0){
                vPair rp =  que.removeFirst();
                int v1 = rp.v1;
                TreeNode node = rp.node;
                ans.set(v1,node.val);
                if(node.left!=null) que.addLast(new vPair(node.left , v1-1));
                if(node.right!=null) que.addLast(new vPair(node.right , v1+1));
            }
        }
        return ans;
    }
    
    public static ArrayList<Integer> topView(TreeNode root){
        LinkedList<vPair> que = new LinkedList<>();
        int[] minMax= new int[2];
        widthOfShadow(root, 0, minMax);
        int length = minMax[1]-minMax[0]+1;
        que.addLast(new vPair(root , Math.abs(minMax[0])));
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i = 0 ;i < length ; i++){
            ans.add(null);
        }

        while(que.size()>0){
            int size = que.size();
            while(size-->0){
                vPair rp = que.removeFirst();
                int v1 = rp.v1;
                TreeNode node = rp.node;
                if(ans.get(v1)==null)
                ans.set(v1 , node.val);

                if(node.left!=null)
                    que.addLast(new vPair(node.left , v1-1));
                if(node.right!=null)
                    que.addLast(new vPair(node.right , v1+1));
            }
        }

        return ans;
    }

    public static ArrayList<ArrayList<Integer>> diagonalOrder(TreeNode root) {
        LinkedList<TreeNode> que = new LinkedList<>();
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();

        que.addLast(root);
        while(que.size()>0){
            int size = que.size();
            ArrayList<Integer> smallAns = new ArrayList<>();
            while(size-- >0){  //diagonal 
                TreeNode node = que.removeFirst();
                while(node!=null){ // clusters of diagonal
                    smallAns.add(node.val);
                    if(node.left!=null)
                        que.addLast(node.left);
                    node = node.right;
                }
            }
            ans.add(smallAns);
        }

        return ans;
    }

    public static ArrayList<ArrayList<Integer>> antiDiagonalOrder(TreeNode root){
        LinkedList<TreeNode> que = new LinkedList<>();
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        que.addLast(root);
        while(que.size()!=0){
            int size = que.size();
            ArrayList<Integer> smallAns = new ArrayList<>();
            while(size-->0){
                TreeNode node = que.removeFirst();
                while(node!=null){
                    smallAns.add(node.val);
                    if(node.right!=null)
                        que.addLast(node.right);
                    node = node.left;
                }


            }
            ans.add(smallAns);
        }
        return ans;
    }

    public static ArrayList<Integer> verticalOrderSum(TreeNode root){
        LinkedList<vPair> que = new LinkedList<>();
        int[] minMax = new int[2];
        widthOfShadow(root, 0, minMax);
        int length = minMax[1]-minMax[0]+1;
        que.add(new vPair(root , Math.abs(minMax[0])));
        
        ArrayList<Integer> ans = new ArrayList<>();
        
        for(int i = 0 ; i < length ; i++){
            ans.add(0);
        }
        
        while(que.size()>0){
            int size = que.size();
            while(size-->0){
                vPair rp =  que.removeFirst();
                int v1 = rp.v1;
                TreeNode node = rp.node;
                ans.set(v1,node.val+ans.get(v1));
                if(node.left!=null) que.addLast(new vPair(node.left , v1-1));
                if(node.right!=null) que.addLast(new vPair(node.right , v1+1));
            }
        }
        return ans;
    }

    public static ArrayList<Integer> diagonalOrderSum(TreeNode root) {
        LinkedList<TreeNode> que = new LinkedList<>();
        ArrayList<Integer> ans = new ArrayList<>();

        que.addLast(root);
        while(que.size()>0){
            int size = que.size();
            int sum = 0;
            while(size-- >0){  //diagonal 
                TreeNode node = que.removeFirst();
                while(node!=null){ // clusters of diagonal
                    sum+=node.val;
                    if(node.left!=null)
                        que.addLast(node.left);
                    node = node.right;
                }
            }
            ans.add(sum);
        }

        return ans;
    }


    public static class ListNode{
        int data = 0;
        ListNode prev = null;
        ListNode next  = null;
        ListNode(int data){
            this.data = data;
        
        }
    }
    //linkedlist technique se
    public static void verticalOrderSum(TreeNode root , ListNode node){
        node.data+=root.val;
        if(root.left!=null){
            if(node.prev==null){
                ListNode nnode = new ListNode(0);
                nnode.next = node;
                node.prev = nnode;
                
            }
            verticalOrderSum(root.left , node.prev);
        }

         if(root.right !=null){
            if(node.next==null){
                ListNode nnode = new ListNode(0);
                nnode.prev = node;
                node.next = nnode;
            }
            verticalOrderSum(root.right , node.next);
        }
    }


    public static void diagonalOrderSum(TreeNode root , ListNode node){
        node.data+=root.val;
        if(root.left!=null){
            if(node.prev==null){
                ListNode nnode = new ListNode(0);
                nnode.next = node;
                node.prev = nnode;
            }
            diagonalOrderSum(root.left, node.prev);
        }
        if(root.right!=null){
            diagonalOrderSum(root.right, node);
        }

    }

    public static void diagonalSum(TreeNode node){
        ListNode curr = new ListNode(0);
        diagonalOrderSum(node , curr);
    }

    

    //987
    public static List<List<Integer>> verticalTraversal(TreeNode root){
        PriorityQueue<vPair> que = new PriorityQueue<>((a,b)->{
            if(a.v1!=b.v1)
                return a.v1 - b.v1;
            return a.node.val - b.node.val;
        });
        PriorityQueue<vPair> chilque = new PriorityQueue<>((a,b)->{
            if(a.v1!=b.v1)
                return a.v1 - b.v1;
            return a.node.val - b.node.val;
        });
        int[] minMax = new int[2];
        widthOfShadow(root, 0, minMax);
        int length = minMax[1]-minMax[0]+1;
        que.add(new vPair(root , Math.abs(minMax[0])));
        
        List<List<Integer>> ans = new ArrayList<>();
        
        for(int i = 0 ; i < length ; i++){
            ans.add(new ArrayList<>());
        }
        
        while(que.size()>0){
            int size = que.size();
            while(size-->0){
                vPair rp =  que.remove();
                int v1 = rp.v1;
                TreeNode node = rp.node;
                ans.get(v1).add(node.val);
                if(node.left!=null) chilque.add(new vPair(node.left , v1-1));
                if(node.right!=null) chilque.add(new vPair(node.right , v1+1));
            }
            PriorityQueue<vPair> temp = que;
            que = chilque;
            chilque = temp;
        }
        return ans;
    }

    //el pQ se hi verticaltravesala pr vpair class m level variable or daalna hai
    public static List<List<Integer>> verticalTraversal_02(TreeNode root){
        PriorityQueue<vPair> que = new PriorityQueue<>((a , b) -> {
            if(a.level!=b.level){
                return a.level - b.level;
            } else if( a.v1 != b.v1){
                return a.v1 - b.v1;
            }

            return a.node.val - b.node.val;
            
        });
        int[] minMax = new int[2];
        widthOfShadow(root, 0, minMax);
        int length = minMax[1]-minMax[0]+1;
        que.add(new vPair(root , Math.abs(minMax[0]),0));
        
        List<List<Integer>> ans = new ArrayList<>();
        
        for(int i = 0 ; i < length ; i++){
            ans.add(new ArrayList<>());
        }
        
        while(que.size()>0){
            int size = que.size();
            while(size-->0){
                vPair rp =  que.remove();
                int v1 = rp.v1;
                int level = rp.level;
                TreeNode node = rp.node;
                ans.get(v1).add(node.val);
                if(node.left!=null) que.add(new vPair(node.left , v1-1 , level+1));
                if(node.right!=null) que.add(new vPair(node.right , v1+1 , level+1));
            }
        }
        return ans;
    }

    
}
