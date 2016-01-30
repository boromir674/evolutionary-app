package util;

public class Heap {

	public Heap() {
	}
	public void heapsort(double[] a, int count){
		heapify(a, count);
		int end = count - 1;
		while (end > 0){
			swap(a[end], a[0]);
			end --;
			siftDown(a, 0, end);
		}
	}
	public void heapify(double[] a, int count){
		int start = iParent(count-1);
		
		while (start >= 0){
			siftDown(a, start, count-1);
			start --;
		}
	}
	@SuppressWarnings("static-method")
	public void siftDown(double[] a, int start, int end){
		int root = start;
		int child;
		while (iLeftChild(root) <= end){
			child = iLeftChild(root);
			int swap = root;
			if (a[swap] < a[child])
				swap = child;
			if (child+1 <= end && a[swap] < a[child+1])
				swap = child + 1;
			if (swap == root)
				break;
			else {
				swap(a[root], a[swap]);
				root = swap;
			}
		}
	}
	private static int iParent(int i){
		return (int) Math.floor((i-1)/2);
	}
	private static int iLeftChild(int i){
		return 2*i + 1;
	}
	@SuppressWarnings("unused")
	private static int iRightChild(int i){
		return 2*i + 2;
	}
	private static void swap(double a, double b){
		double temp = a;
		a = b;
		b = temp;
	}
}
