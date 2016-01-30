package util;

/**
 * @author kostas
 * Implementation of a minHeap
 */
public class Heap {

	public Heap() {
	}
	public void heapsort(double[] a, int numberOfElementsToSort){
		int count = a.length;
		heapify(a);
		int end = count - 1;
		int low = end - numberOfElementsToSort;
		while (end > low){
			swap(a, end, 0);
			end --;
			siftDown(a, 0, end);
		}
	}
	public void heapsort(double[] a){
		int count = a.length;
		heapify(a);
		int end = count - 1;
		while (end > 0){
			swap(a, end, 0);
			end --;
			siftDown(a, 0, end);
		}
	}
	public void heapify(double[] a){
		int count = a.length;
		int start = iParent(count-1);
		
		while (start >= 0){
			siftDown(a, start, count-1);
			start --;
		}
	}
	@SuppressWarnings("static-method")
	public void siftDown(double[] a, int start, int end){
		int root = start;
		while (iLeftChild(root) <= end){
			int child = iLeftChild(root);
			int swap = root;
			if (a[swap] < a[child])
				swap = child;
			if (child+1 <= end && a[swap] < a[child+1])
				swap = child + 1;
			if (swap == root)
				break;
			else {
				swap(a, root, swap);
				root = swap;
			}
		}
	}
	public int iParent(int i){
		return (int) Math.floor((i-1)/2);
	}
	public int iLeftChild(int i){
		return 2*i + 1;
	}
	@SuppressWarnings("unused")
	private static int iRightChild(int i){
		return 2*i + 2;
	}
	public void swap(double[] a, int i, int j){
		double temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
}
