package Linked_List;

public class CycleNode2 {
    public class ListNode{
        int val = 0;
        ListNode next = null;
        ListNode(int val){
            this.val = val;
    
        }
    }

    
    public static ListNode CycleNode1(ListNode head){
        if(head == null || head.next==null){
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next!= null && fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            if(fast==slow){
                break;
            }
            if(slow!=fast) return null;

            slow = head;
            while(slow != fast){
                slow = slow.next;
                fast = fast.next;
            }
            return slow;

        }

    }
    public static ListNode CycleNode2(ListNode head){
        if(head == null || head.next == null){
            return null;
        }
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next!=null && fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
            if(fast==slow){
                break;
            }
        }

        if(fast != slow){
            return null;
        }
        
        ListNode meetingNode = fast;
         int a =1 , b = 0 , c = 0 , bc = 0 , nDash = 0 , n = 0; //bc is b+c
         int count = 0;
         boolean isLoopRun = false;
         while(slow!=fast){
             slow = slow.next;
             fast = fast.next;
             if( nDash == 0 && fast == meetingNode){
                 bc = count;
             } 
             if(fast==meetingNode){
                 nDash++;
             }
             a++;
             count++;
             isLoopRun = true;
         }

         if(!isLoopRun){
             fast = fast.next;
             bc  = 1;
             while(fast!=slow){
                 fast = fast.next;
                 bc++;
             }
             a = 0;
             b = bc ;
             c = 0;
             n = 1;
             nDash = 0;
         } else {
             n = nDash + 1;
             c = a - bc * nDash;
             b = bc - c;
         }
         System.out.println("Length Of Tail is:" + a);
         System.out.println("Length Of b is:" + b);
         System.out.println("Length Of c is:" + c);
         System.out.println("No Of rotation by fast pointer before meeting poiny:" + n);
         System.out.println("No Of rotation by fast pointer after meeting poiny:" + nDash);
 
         return slow;
    }
}
