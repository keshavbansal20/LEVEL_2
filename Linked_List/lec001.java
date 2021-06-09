package Linked_List;

import java.util.*;

public class lec001 {
        public static class ListNode{
            ListNode next;
            int val;
           
            ListNode( int val){
                this.val = val;
            }

            
        }
        public static ListNode midNode2(ListNode head){
            if(head==null || head.next == null){
                return head;
            }

            ListNode slow = head , fast = head;
            while(fast!=null && fast.next!=null){
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        }

        public static ListNode midNode(ListNode head){
                if(head==null ||head.next==null) return head;
                ListNode slow = head , fast = head;
                while(fast!=null && fast.next.next!=null){
                    slow = slow.next;
                    fast = fast.next.next;
                }
                return slow;
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
        public static boolean isPalindrome(ListNode head){
            if(head==null || head.next==null){
                return true;
            }
            ListNode mid = midNode(head);
            ListNode nhead = mid.next;
            mid.next = null;
            nhead = reverseList(nhead);
            ListNode c1 = head , c2 = nhead;
            boolean res = true;
            while(c2!=null){  
                if(c1.val!=c2.val){
                    res = false;
                    break;
                }
                c1 = c1.next;
                c2 = c2.next;
            }
            
            nhead = reverseList(nhead);
            mid.next = nhead;
            return res;
        }
        
        //isme do pointer lekr kra h c1 and c2 , c2 ki jgh dummy node p2 ko bhi use kr skte hai
        public static void unfold(ListNode head){
            if(head==null||head.next==null){
                return;
            }

            ListNode l1= new ListNode(-1);
            ListNode l2 = new ListNode(-1);

            ListNode p1 = l1 , p2 = l2 , c1 = head , c2 = head.next;
            while(c1 !=null && c2 !=null){
                p1.next = c1;
                p2.next = c2;
                
                p1 = p1.next;
                p2 = p2.next;

                if(c2!=null){
                    c1 =  c2.next;
                }
                if(c1!=null){
                    c2 = c1.next;
                }
            }

            p1.next = null;
            p2 = reverseList(l2.next);
            p1.next = p2;
        }

        public static void fold(ListNode head) {

            ListNode mid = midNode(head);
            ListNode nhead = mid.next;
            mid.next = null;
            nhead = reverseList(nhead);
            ListNode c1 = head , c2 = nhead;
            while(c2!=null){
                ListNode f1 = c1.next  ,f2 = c2.next; //backup
                 c1.next = c2; //link
                 c2.next = f1;
                 

                 //move
                c1 = f1;
                c2 = f2;
            }
        }

        //isme sirf forwARD OINTER KA USE KRA H FOR THE CONNECTIONS
        public static void unfold_2(ListNode head){
            if(head==null||head.next==null){
                return;
            }

            ListNode l1= new ListNode(-1);
            ListNode l2 = new ListNode(-1);
            ListNode p1 = l1 , p2 = l2;

            p1.next = head;
            p2.next = head.next;
            
            p1 = p1.next;
            p2 = p2.next;

            while(p2 !=null && p2.next !=null){
                ListNode f = p2.next;
                
                p1.next = f;
                p2.next = f.next;

                p1 = p1.next;
                p2 = p2.next;
            }

            p1.next = null;
            p2 = reverseList(l2.next); // reverse again to retrun original linkedlist
            p1.next = p2;
        }

        //not to make new linkeslist
        public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
            if(l1==null || l2==null){
                return l1!=null?l1:l2;
            }
            ListNode p = new ListNode(-1);
            ListNode c1= l1;
            ListNode c2 = l2;
            ListNode nhead ;
            
            if(c1.val<=c2.val){
                nhead = c1;
            }else{
                nhead = c2;
            }
            
            while(c1!=null&&c2!=null){
                if(c1.val<=c2.val){
                    p.next= c1;
                    c1=c1.next;
                    p = p.next;
                }else{
                    p.next = c2;
                    c2= c2.next;
                    p = p.next;
                }
            }
            
             p.next = c1!=null?c1:c2;
            
            
            return nhead;
        }

        //O(NK) where k=(lambda)(K*2); wherre K=Nlambda 
        public static ListNode mergeKLists(ListNode[] lists) {
               ListNode l1 = lists[0];
               
                 ListNode res =l1;
            for(int i=1; i<(lists.length);i++){
                 res = mergeTwoLists(lists[i] , res);
               
               
            }
            return res;
        }

        //2nd option
        public static ListNode mergeTwoLists_2(ListNode l1 , ListNode l2){
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
    
    public static ListNode mergeKList(ListNode[] lists , int si , int ei){
        if(si==ei){
            return lists[si];
        }
        int mid = (si+ei)/2;
        ListNode leftMergedList = mergeKList(lists , si , mid);
        ListNode rightMergedList = mergeKList(lists , mid+1 , ei);
        
        return mergeTwoLists(leftMergedList , rightMergedList);
    }
    public ListNode mergeKLists_2(ListNode[] lists) {
            if(lists.length==0){
                return null;
            }
         ListNode res = mergeKList(lists , 0 , lists.length-1);
         return res;
    }

        public static void main(String[] args){

        }    
}
