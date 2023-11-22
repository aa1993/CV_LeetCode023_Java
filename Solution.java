/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        //return mergeKListsHelp(lists,0,lists.length);
        
        int l = lists.length;
        if(l==0)
            return null;
        while(l>1)
        {
            
            for(int i = 0 ; i<l-1; i=i+2)
            {
                lists[i/2]=this.sortLists(lists[i], lists[i+1]);
            }
            if(l%2==1)
            {
                lists[l/2]=lists[l-1];
                l=l/2+1;
            }
            else
                l=l/2;
        }
        return lists[0];
    }
    
    public ListNode mergeKListsHelp(ListNode[] lists,int from ,int to) {
        if(to -from == 0)
            return null;
        if(to -from == 1)
            return lists[0];
        if(to -from == 2)
        {
            return this.sortLists(lists[from],lists[from + 1]);
        }
        else if(to-from>2)
        {
            ListNode l1 = mergeKListsHelp(lists, from, (from+to)/2);
            ListNode l2 = mergeKListsHelp(lists, (from+to)/2, to);
            ListNode[] res = {l1,l2};
            return mergeKListsHelp(res,0, 2);
        }
        return new ListNode(0);
    }
    
    public ListNode sortLists(ListNode l1, ListNode l2)
    {
        ListNode res = new ListNode(0);
            ListNode cur = res;
            while(l1 != null || l2 != null)
            {
                if(l1 != null && l2 != null)
                {
                    if(l1.val <= l2.val)
                    {
                        cur.next = new ListNode(l1.val);
                        cur = cur.next;
                        l1 = l1.next;
                    }
                    else
                    {
                        cur.next = new ListNode(l2.val);
                        cur = cur.next;
                        l2 = l2.next;
                    }
                }
                else if(l1 != null)
                {
                    cur.next = new ListNode(l1.val);
                    cur = cur.next;
                    l1 = l1.next;
                }
                else
                {
                    cur.next = new ListNode(l2.val);
                    cur = cur.next;
                    l2 = l2.next;
                }
            }
            return res.next;
    }
}