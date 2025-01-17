import edu.princeton.cs.algs4.Queue;

public class MergeSort {
    /**
     * Removes and returns the smallest item that is in q1 or q2.
     *
     * The method assumes that both q1 and q2 are in sorted order, with the smallest item first. At
     * most one of q1 or q2 can be empty (but both cannot be empty).
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      The smallest item that is in q1 or q2.
     */
    private static <Item extends Comparable> Item getMin(
            Queue<Item> q1, Queue<Item> q2) {
        if (q1.isEmpty()) {
            return q2.dequeue();
        } else if (q2.isEmpty()) {
            return q1.dequeue();
        } else {
            // Peek at the minimum item in each queue (which will be at the front, since the
            // queues are sorted) to determine which is smaller.
            Comparable q1Min = q1.peek();
            Comparable q2Min = q2.peek();
            if (q1Min.compareTo(q2Min) <= 0) {
                // Make sure to call dequeue, so that the minimum item gets removed.
                return q1.dequeue();
            } else {
                return q2.dequeue();
            }
        }
    }

    /** Returns a queue of queues that each contain one item from items. */
    private static <Item extends Comparable> Queue<Queue<Item>>
            makeSingleItemQueues(Queue<Item> items) {
        // Your code here!
        Queue<Queue<Item>> ret = new Queue<>();
        for (Item x : items){
            Queue<Item> q = new Queue<>();
            q.enqueue(x);
            ret.enqueue(q);
        }
        return ret;
    }

    /**
     * Returns a new queue that contains the items in q1 and q2 in sorted order.
     *
     * This method should take time linear in the total number of items in q1 and q2.  After
     * running this method, q1 and q2 will be empty, and all of their items will be in the
     * returned queue.
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      A Queue containing all of the q1 and q2 in sorted order, from least to
     *              greatest.
     *
     */
    private static <Item extends Comparable> Queue<Item> mergeSortedQueues(
            Queue<Item> q1, Queue<Item> q2) {
        // Your code here!
        Queue<Item> mergedQueue = new Queue<>();
        while (!(q1.isEmpty() && q2.isEmpty())) {
            mergedQueue.enqueue(getMin(q1, q2));
        }
        return mergedQueue;
    }


    /** Returns a Queue that contains the given items sorted from least to greatest. */
    public static <Item extends Comparable> Queue<Item> mergeSort(
            Queue<Item> items) {
        // Your code here!
        if (items.size() <= 1) {
            return items;
        }
        Queue<Queue<Item>> qq = makeSingleItemQueues(items);
        while (qq.size() != 1) {
            Queue<Queue<Item>> qq2 = new Queue<>();
            while (!qq.isEmpty()) {
                Queue<Item> q1 = qq.dequeue();
                Queue<Item> q2 = qq.isEmpty() ? new Queue<>() : qq.dequeue();
                qq2.enqueue(mergeSortedQueues(q1, q2));
            }
            qq = qq2;
        }
        return qq.dequeue();
    }

     public static void main(String[] args) {
        Queue<Integer> q = new Queue<>();
        q.enqueue(3);
        q.enqueue(3);
        q.enqueue(1);
        q.enqueue(10);
        Queue<Integer> q2 = MergeSort.mergeSort(q);
        System.out.println(q);
        System.out.println(q2);
     }
}
