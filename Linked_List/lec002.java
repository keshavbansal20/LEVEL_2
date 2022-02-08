package Linked_List;

public class lec002 {
    public static class ListNode {
        ListNode next;
        int val;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode curr = head;
        ListNode prev = null;
        while (curr != null) {
            ListNode forw = curr.next;

            curr.next = prev;
            prev = curr;
            curr = forw;

        }
        return prev;
    }

    public static ListNode midNode(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 != null ? l1 : l2;
        }

        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy, c1 = l1, c2 = l2;

        while (c1 != null && c2 != null) {
            if (c1.val <= c2.val) {
                prev.next = c1;
                c1 = c1.next;
            } else {
                prev.next = c2;
                c2 = c2.next;
            }
            prev = prev.next;
        }

        prev.next = c1 != null ? c1 : c2;
        return dummy.next;
    }

    public static ListNode mergeSort(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode mid = midNode(head);
        ListNode nhead = mid.next;
        mid.next = null;
        ListNode leftsortedlist = mergeSort(head);
        ListNode rightsortedlist = mergeSort(nhead);

        return mergeTwoLists(leftsortedlist, rightsortedlist);
    }

    // removeNth From the end
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null)
            return head;

        ListNode slow = head;
        ListNode fast = head;   

        while (n-- > 0) {
            fast = fast.next;
        }
        if (fast == null) {
            ListNode rnode = slow;
            head = slow.next;
            rnode.next = null;
            return head;

        }   
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }

        ListNode rnode = slow.next;
        slow.next = rnode.next;
        rnode.next = null;

        return head;
    }

    public static ListNode segregateEvenOdd(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode even = new ListNode(-1);
        ListNode odd = new ListNode(-1);
        ListNode ep = even, op = odd, curr = head;

        while (curr != null) {
            if (curr.val % 2 != 0) {
                op.next = curr;
                op = op.next;
            } else {
                ep.next = curr;
                ep = ep.next;
            }
            curr = curr.next;
        }

        ep.next = odd.next;
        op.next = null;
        head = even.next;

        return head;
    }

    public static ListNode segregate01(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode one = new ListNode(-1);
        ListNode zero = new ListNode(-1);

        ListNode op = one, zp = zero, curr = head;

        while (curr != null) {
            if (curr.val != 0) {
                op.next = curr;
                op = op.next;
            } else {
                zp.next = curr;
                zp = zp.next;
            }
            curr = curr.next;
        }
        zp.next = one.next;
        op.next = null;

        head = zero.next;
        zero.next = one.next = null;

        return head;

    }

    public static ListNode segregate012(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode one = new ListNode(-1);
        ListNode zero = new ListNode(-1);
        ListNode two = new ListNode(-1);
        ListNode op = one, zp = zero, tp = two, curr = head;

        while (curr != null) {
            if (curr.val == 1) {
                op.next = curr;
                op = op.next;
            } else if (curr.val == 0) {
                zp.next = curr;
                zp = zp.next;
            } else {
                tp.next = curr;
                tp = tp.next;
            }
            curr = curr.next;
        }

        op.next = two.next;
        zp.next = one.next;
        tp.next = null;

        head = zero.next;

        zero.next = one.next = two.next = null;
        return head;
    }

    public static ListNode segregateOnLastIndex(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode small = new ListNode(-1); // dummynodes
        ListNode large = new ListNode(-1);
        ListNode curr = head, sp = small, lp = large;

        ListNode pivotNode = head;
        while (pivotNode.next != null) {
            pivotNode = pivotNode.next; // last index pr jane ke liye
        }

        while (curr != null) {
            if (curr.val <= pivotNode.val) {
                sp.next = curr;
                sp = sp.next;
            } else {
                lp.next = curr;
                lp = lp.next;
            }
            curr = curr.next;
        }

        sp.next = large.next;
        lp.next = null; // cycle ban jayegi agar ye lp ke next ko ull nhi krenge toh
        return sp;

    }

    public static ListNode segregate(ListNode head, int pivotIdx) {
        if(head==null || head.next==null){
            return head;
        }

        ListNode small = new ListNode(-1);
        ListNode large = new ListNode(-1);

        ListNode curr = head , sp = small , lp = large;

        ListNode pivotNode = head;
    
        while(pivotIdx-->0){
            pivotNode = pivotNode.next;
        }
        
        while(curr!=null){
            if(pivotNode == curr){

            } else if (curr.val<=pivotNode.val){
                sp.next = curr;  //link the nodes of small 
                sp = sp.next;
            } else {
                lp.next = curr; //link
                lp = lp.next; //move
            }
            curr = curr.next; //move
        }

      sp.next = pivotNode;
      pivotNode.next = large.next;
      lp.next = null;

      head=small.next;
      return head;
    
        
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        l1 = reverseList(l1);
        l2 = reverseList(l2);

        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy, c1 = l1, c2 = l2;
        int carry = 0;
        while (c1 != null || c2 != null || carry != 0) {
            int sum = carry + (c1 != null ? c1.val : 0) + (c2 != null ? c2.val : 0);
            carry = sum / 10;
            sum = sum % 10;
            prev.next = new ListNode(sum);
            prev = prev.next;

            if (c1 != null) {
                c1 = c1.next;
            } else {
                c2 = c2.next;
            }
        }

        ListNode head = reverseList(dummy.next);

        l1 = reverseList(l1);
        l2 = reverseList(l2);

        return head;

    }

    public static ListNode substract(ListNode l1, ListNode l2) {
        if(l2==null) return l1;
        if(l1==null){
            l2.val = -l2.val;
            return l2;
        }
            l1 = reverseList(l1);
            l2 = reverseList(l2);
            ListNode dummy = new ListNode(-1);
            ListNode c1 = l1, c2 = l2, prev = dummy;
            int borrow = 0;
            while (c1 != null | c2 != null) {
                int diff = borrow + (c1 != null ? c1.val : 0) - (c2 != null ? c2.val : 0);
                if (diff < 0) {
                    diff += 10;
                    borrow = -1;
                } else {
                    borrow = 0;
                }

                prev.next = new ListNode(diff);
                prev = prev.next;

                if(c1!=null)
                    c1= c1.next;
                if(c2!=null)
                    c2 = c2.next;
            }

            ListNode head = dummy.next;
            head = reverseList(head);
            while(head!=null && head.val==0){ 
                head = head.next;
            }
            l1 = reverseList(l1);
            l2 = reverseList(l2);
            return head;
    }

    public static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head, fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    //cycle ka starting point return kr rhe hai 
    public static ListNode CycleNode(ListNode head){
        
        if(head==null || head.next==ull){
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;
        while(fast!=null && fast.next!=null){
            fast = fast.next.next;
            slow = slow.next;
            if(fast==slow) break;        
        }
        if(fast!=slow){
            return null;
        }
        slow = head;
        while(slow!=fast){
            slow = slow.next;
            fast = fast.next;
        }
        return slow;

    }

    public static int length(ListNode head) {
        ListNode curr = head;
        int len = 0;
        while (curr != null) {
            len++;
            curr = curr.next;
        }

        return len;
    }
    ListNode th = null , tt = null;
    public void addFirst(ListNode node){
      
        if(th==null){
            th = tt = node;
        }else {
            node.next = th;
            th = node;
        }
    }
        
    public ListNode reverseKGroup(ListNode head, int K) {
       if(head.next==null || K == 1 ){
            return head;
        }
        int len = length(head);
        ListNode curr = head , ph = null , pt = null;
        while(curr!=null && len>=K){
            int itr = K;
            while(itr-->0){
                ListNode forw = curr.next;
                curr.next = null;
                addFirst(curr);
                curr = forw;
            }
            if(ph == null){
                ph= th;
                pt = tt;
            } else {
                pt.next = th;
                pt = tt;
            }
             th = tt = null;
             len-=K;
        }
        pt.next = curr;
        return ph; 
    }

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if(head.next==null || m==n ){
            return head;
        }    
        int i = 1;
        ListNode curr = head , prev = null;
        while(curr!=null){
            while(i>=m && i<=n){
                ListNode forw = curr.next;
                curr.next= null;
                addFirst(curr);
                curr = forw;
                i++;
            }
            if(i>n){
                if(prev==null){
                    prev.next = th;
                    tt.next = curr;
                    return head;
                }else {
                    head = th;
                    tt.next = curr;
                    return head;
                }
            }
            prev = curr;
            curr = curr.next;
            i++;

        }

        return null;
    }   

    public ListNode removeDuplicates(ListNode head){
        if(head==null || head.next==null){
            return head;
        }
        ListNode curr = head.next , prev = head;
        while(curr!=null){
            while(curr!=null && curr.val==prev.val){
                ListNode forw = curr.next;
                curr.next = null;
                curr = forw;
            }
            prev.next = curr;
            prev = prev.next;
            if(curr!=null){
                curr = curr.next;
            }
        }
        return head;
    }

    //remove all duplicates in the list
    public ListNode deleteDuplicates(ListNode head) {
        if(head = null || head.next ==null){
            return head;
        }
        ListNode dummy = new ListNode(-1);
        ListNode curr = head.next , prev = dummy;
        prev.next = head;
        while(curr!=null){
            boolean isLoopRun = false;
            while(curr!=null && prev.next.val==curr.val){
                ListNode forw  = curr.next;
                curr.next = null;
                curr = forw;
                isLoopRun = true;
            }
             
            if(isLoopRun){
                prev.next= curr;
            }else {
                prev = prev.next;
                prev.next = curr;
            }
            if(curr!=null){
                curr = curr.next;
            }
        }
        return dummy.next;
    }
}
