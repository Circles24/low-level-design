import java.util.Arrays;
import java.util.Collections;
import java.util.List;

interface SortStrategy<T extends Comparable<T>> {
    void sort(List<T> numbers);
}

class InbuiltSortStrategy<T extends Comparable<T>> implements SortStrategy<T> {

    @Override
    public void sort(List<T> numbers) {
        System.out.println("Inbuilt sort");
        numbers.sort(Comparable::compareTo);
    }
}

class BubbleSort<T extends Comparable<T>> implements SortStrategy<T> {

    @Override
    public void sort(List<T> numbers) {
        System.out.println("Bubble sort");

        int n = numbers.size();

        for (int i=0; i<n; i++) {
            for (int j=(n - 1); j>i; j--) {
                if (numbers.get(j).compareTo(numbers.get(j - 1)) < 0) {
                    Collections.swap(numbers, j, j - 1);
                }
            }
        }
    }
}

interface SearchStrategy<T> {
    int search(List<T> numbers, T query);
}

class LinearSearch<T> implements SearchStrategy<T> {
    public int search(List<T> numbers, T query) {
        System.out.println("Linear search");

        int n = numbers.size();
        for (int i=0; i<n; i++) {
            if (numbers.get(i) == query) {
                return i;
            }
        }

        return -1;
    }
}

class BinarySearch<T extends Comparable<T>> implements SearchStrategy<T> {
    public int search(List<T> numbers, T query) {
        System.out.println("Binary search");

        int n = numbers.size();

        int l = 0;
        int h = (n - 1);

        while (l < h) {
            int mid = (l + h) / 2;

            if (numbers.get(mid) == query) {
                return mid;
            } else if (numbers.get(mid).compareTo(query) > 0) {
                h = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        if (numbers.get(l) == query) {
            return l;
        }

        return -1;
    }
}

class ServiceLayer<T extends Comparable<T>> {
    SortStrategy<T> inbuiltSortStrategy = new InbuiltSortStrategy<>();
    SortStrategy<T> bubbleSort = new BubbleSort<>();

    SearchStrategy<T> linearSearch = new LinearSearch<>();
    SearchStrategy<T> binarySearch = new BinarySearch<>();

    public void sort(List<T> numbers) {
        if (numbers.size() > 15) {
            inbuiltSortStrategy.sort(numbers);
        } else {
            bubbleSort.sort(numbers);
        }
    }

    public int search(List<T> numbers, T query) {
        return search(numbers, query, false);
    }


    public int search(List<T> numbers, T query, boolean sorted) {
        if (sorted && numbers.size() > 15) {
           return binarySearch.search(numbers, query);
        }

        return linearSearch.search(numbers, query);
    }
}


public class StrategyDemo {
    public static void main(String args[]) {
        ServiceLayer<Integer> service = new ServiceLayer<>();

        List<Integer> arr1 = Arrays.asList(10, 8, 4, 2, 5, 2, 3, 5, 8, 6, 3);
        System.out.println("arr 1 : " + arr1);
        System.out.println("search index for 10 " + service.search(arr1, 10));
        System.out.println("search index for 4 " + service.search(arr1, 4));
        System.out.println("search index for 9 " + service.search(arr1, 9));

        service.sort(arr1);
        System.out.println("arr 1 : " + arr1);

        System.out.println("search index for 10 " + service.search(arr1, 10, true));
        System.out.println("search index for 4 " + service.search(arr1, 4, true));
        System.out.println("search index for 9 " + service.search(arr1, 9, true));


        List<Integer> arr2 = Arrays.asList(10, 8, 4, 2, 5, 2, 3, 5, 8, 6, 3, 10, 8, 4, 2, 5, 2, 3, 5, 8, 6, 3, 2, 5, 2, 3, 5, 8, 6, 3, 10, 8, 4, 2, 5, 2, 3, 5);
        System.out.println("arr 2 : " + arr2);
        System.out.println("search index for 10 " + service.search(arr2, 10));
        System.out.println("search index for 4 " + service.search(arr2, 4));
        System.out.println("search index for 9 " + service.search(arr2, 9));

        service.sort(arr2);
        System.out.println("arr 2 : " + arr2);

        System.out.println("search index for 10 " + service.search(arr2, 10, true));
        System.out.println("search index for 4 " + service.search(arr2, 4, true));
        System.out.println("search index for 9 " + service.search(arr2, 9, true));
    }
}
