package util;

import evolutionaryAlgorithmComponents.Individual;

/**
 * @author kostas
 * Implementation of a minHeap
 */
public class MinHeap {

	public MinHeap() {
	}
	public static void heapsort(Object[] a, int numberOfElementsToSort){
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
	public static void heapsort(Object[] a){
		int count = a.length;
		heapify(a);
		int end = count - 1;
		while (end > 0){
			swap(a, end, 0);
			end --;
			siftDown(a, 0, end);
		}
	}
	private static void heapify(Object[] a){
		int count = a.length;
		int start = iParent(count-1);
		
		while (start >= 0){
			siftDown(a, start, count-1);
			start --;
		}
	}
	private static void siftDown(Object[] a, int start, int end){
		int root = start;
		while (iLeftChild(root) <= end){
			int child = iLeftChild(root);
			int swap = root;
			//if (a[swap] < a[child])
			if (((Individual)a[swap]).compareTo((Individual)a[child]) < 0)
				swap = child;
			//if (child+1 <= end && a[swap] < a[child+1])
			if (child+1 <= end && ((Individual)a[swap]).compareTo((Individual)a[child+1]) < 0)
				swap = child + 1;
			if (swap == root)
				break;
			else {
				swap(a, root, swap);
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
	private static void swap(Object[] a, int i, int j){
		Object temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
}
