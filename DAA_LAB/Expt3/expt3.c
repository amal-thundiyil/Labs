#include <limits.h>
#include <stdio.h>

int inversions = 0, min = INT_MAX, max = INT_MIN;

void display(int sorted[], int lo, int hi) {
    for (int i = lo; i <= hi; i++) {
        printf("%d ", sorted[i]);
    }
    printf("\n");
}

void display_inv(int arr[], int inv, int lo, int hi) {
    for (int i = lo; i <= hi; i++) {
        printf("Inversion: (%d, %d)\n", inv, arr[i]);
    }
}

void merge(int arr[], int sorted[], int start, int end) {
    int mid = (start + end) / 2;
    int i = start;
    int j = mid + 1;
    int k = start;
    while (i <= mid && j <= end) {
        if (arr[i] < arr[j]) {
            sorted[k] = arr[i];
            k++;
            i++;
        } else {
            sorted[k] = arr[j];
            display_inv(arr, arr[j], i, mid);
            k++;
            j++;
            inversions += (mid - i + 1);
        }
    }
    while (i <= mid) {
        sorted[k] = arr[i];
        k++;
        i++;
    }
    while (j <= end) {
        sorted[k] = arr[j];
        k++;
        j++;
    }
    for (i = start; i <= end; i++) {
        arr[i] = sorted[i];
    }
}

void find_min_max(int nums[], int low, int high) {
    if (low == high) {
        if (max < nums[low]) {
            max = nums[low];
        }
        if (min > nums[high]) {
            min = nums[high];
        }
        return;
    } else {
        int mid = (low + high) / 2;
        find_min_max(nums, low, mid);
        printf("Min: %d, Max: %d\n", min, max);
        find_min_max(nums, mid + 1, high);
        printf("Min: %d, Max: %d\n", min, max);
    }
}

void merge_sort(int arr[], int sorted[], int low, int high) {
    if (low >= high) return;
    int mid = (low + high) / 2;
    merge_sort(arr, sorted, low, mid);
    merge_sort(arr, sorted, mid + 1, high);
    printf("Left: ");
    display(arr, low, mid);
    printf("Right: ");
    display(arr, mid + 1, high);
    merge(arr, sorted, low, high);
    printf("Merge: ");
    display(arr, low, high);
}

void solve() {
    int N;
    scanf("%d", &N);
    int arr[N], sorted[N];
    for (int i = 0; i < N; i++) {
        scanf("%d", &arr[i]);
    }
    merge_sort(arr, sorted, 0, N - 1);
    printf("\nInversions: %d\n", inversions);

    printf("\nFinding minimum and maximum: \n");
    find_min_max(arr, 0, N - 1);
    printf("\nMinimum: %d, Maximum: %d\n", min, max);
}

int main() {
    int T = 1;
    inversions = 0;
    while (T--) solve();
    return 0;
}