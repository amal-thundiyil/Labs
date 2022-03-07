#include <stdio.h>

typedef struct node {
    int data;
    int index;
} Node;

void display(Node nodes[], Node sorted[], int N) {
    for (int i = 0; i < N; i++) {
        printf("%d ", sorted[i].data);
    }
    printf("\n");
    for (int i = 0; i < N; i++) {
        printf("%d ", nodes[i].data);
    }
    printf("\n");
}

void merge(Node nodes[], Node sorted[], int start, int end) {
    int mid = (start + end) / 2;
    int i = start;
    int j = mid + 1;
    int k = start;
    while (i <= mid && j <= end) {
        if (nodes[i].data < nodes[j].data) {
            sorted[k].data = nodes[i].data;
            sorted[k].index = nodes[i].index;
            k++;
            i++;
        } else {
            sorted[k].data = nodes[j].data;
            sorted[k].index = nodes[j].index;
            k++;
            j++;
        }
    }
    while (i <= mid) {
        sorted[k].data = nodes[i].data;
        sorted[k].index = nodes[i].index;
        k++;
        i++;
    }
    while (j <= end) {
        sorted[k].data = nodes[j].data;
        sorted[k].index = nodes[j].index;
        k++;
        j++;
    }
    for (i = start; i <= end; i++) {
        nodes[i].data = sorted[i].data;
        nodes[i].index = sorted[i].index;
    }
}

void merge_sort(Node nodes[], Node sorted[], int low, int high) {
    if (low >= high) return;
    int mid = (low + high) / 2;
    merge_sort(nodes, sorted, low, mid);
    merge_sort(nodes, sorted, mid + 1, high);
    merge(nodes, sorted, low, high);
}

void solve() {
    int N;
    scanf("%d", &N);
    Node nodes[N], sorted[N];
    for (int i = 0; i < N; i++) {
        scanf("%d", &nodes[i].data);
        nodes[i].index = i;
    }
    merge_sort(nodes, sorted, 0, N - 1);

    int min_ptr = 0, min_sum = 0;
    while (min_ptr < N - 1) {
        while (sorted[min_ptr].data >= 0) {
            sorted[min_ptr].data -= (sorted[min_ptr].index + 1);
            min_sum += (sorted[min_ptr].index + 1);
        }
        min_ptr += 1;
        while (min_ptr < N - 1) {
            sorted[min_ptr].data -= min_sum;
            sorted[min_ptr].index -= 1;
            if (sorted[min_ptr].data > 0) break;
            min_ptr += 1;
        }
    }
    sorted[N - 1].data -= min_sum;
    if (sorted[N - 1].data >= 0)
        printf("Ladia\n");
    else
        printf("Kushagra\n");
}

int main() {
    int T;
    scanf("%d", &T);
    while (T--) solve();
    return 0;
}