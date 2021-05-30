package Linked_List;

public class lec002 {
    public static class ListNode{
        ListNode next;
        int val;
        ListNode(int val){
            this.val = val;
        }
    } 
    public static ListNode reverseList(ListNode head) {
        if(head==null || head.next==null){
            return head;
        }

        ListNode curr = head;
        ListNode prev = null;
        while(curr!=null){
            ListNode forw = curr.next;
            
            curr.next = prev;
            prev = curr;
            curr= forw;
            
        }
        return prev;
    }
    public static ListNode midNode(ListNode head){
        if(head==null ||head.next==null) return head;
        ListNode slow = head , fast = head;
        while(fast.next!=null && fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
}

    public static ListNode mergeTwoLists(ListNode l1 , ListNode l2){
        if(l1 == null || l2 == null){
            return l1!=null? l1: l2;
        }

        ListNode dummy = new ListNode(-1);
        ListNode prev = dummy , c1 =  l1 , c2 = l2;

        while(c1!=null&&c2!=null){
            if(c1.val<=c2.val){
                prev.next = c1;
                c1 = c1.next;
            } else {
                prev.next = c2;
                c2 = c2.next;
            }
            prev = prev.next;
        }

        prev.next = c1 != null ? c1:c2;
        return dummy.next;
    }
    public static ListNode mergeSort(ListNode head) {
        if(head==null || head.next==null){
            return head;
        }
        ListNode mid = midNode(head);
        ListNode nhead= mid.next;
        mid.next = null;
        ListNode leftsortedlist = mergeSort(head);
        ListNode rightsortedlist = mergeSort(nhead);

        return mergeTwoLists(leftsortedlist  , rightsortedlist);
    }
    

    //removeNth From the end
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        if(head==null) return head;

        ListNode slow = head;
        ListNode fast = head;

        while(n-->0){
            fast = fast.next;
        }
        if(fast==null){
            ListNode rnode = slow;
            head = slow.next;
            rnode.next = null;
            return head;
           
        }
        while(fast.next!=null){
            slow = slow.next;
            fast = fast.next;
        }

        ListNode rnode = slow.next;
        slow.next = rnode.next;
        rnode.next = null;

        return head;
    }

    public static ListNode segregateEvenOdd(ListNode head) {
        if(head == null || head.next==null){
            return head;
        }

        ListNode even = new ListNode(-1);
        ListNode odd = new ListNode(-1);
        ListNode ep = even , op = odd , curr = head;

        while(curr!=null){
            if(curr.val%2!=0){
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
        if(head==null || head.next==null){
            return head;
        }

        ListNode one = new ListNode(-1);
        ListNode zero = new ListNode(-1);

        ListNode op = one , zp = zero , curr = head;

        while(curr != null){
            if(curr.val!=0){
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
        if(head==null || head.next==null){
            return head;
        }

        ListNode one = new ListNode(-1);
        ListNode zero = new ListNode(-1);
        ListNode two = new ListNode(-1);
        ListNode op = one , zp = zero , tp = two  , curr = head;

        while(curr !=null){
            if(curr.val == 1){
                op.next = curr;
                op = op.next;
            } else if (curr.val == 0){
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
        if(head==null || head.next == null){
            return head;
        }

        ListNode small  = new ListNode(-1); //dummynodes
        ListNode large  = new ListNode(-1);
        ListNode curr = head, sp = small , lp = large;
        
        ListNode pivotNode = head;
        while(pivotNode.next!=null){
            pivotNode = pivotNode.next; //last index  pr jane ke liye 
        }
        
        while(curr!=null){
             if(curr.val<=pivotNode.val){
                 sp.next = curr;
                 sp = sp.next;
             } else {
                 lp.next = curr;
                 lp = lp.next;
             }
             curr = curr.next;
        }

        sp.next = large.next;
        lp.next = null; //cycle ban jayegi agar ye lp ke next ko ull nhi krenge toh
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
            curr = curr.next //move
        }

      sp.next = pivotNode;
      pivotNode.next = large.next;
      lp.next = null;

      head=small.next;
      return head;
    
        
    }
}
