#include <error.h>
#include <fcntl.h>
#include <semaphore.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/shm.h>
#include <sys/wait.h>
#include <unistd.h>
#define SHMSZ 27

void insertion_sort(int arr[], int n) {
    int i, j;
    for (int i = 0; i < n; i++) {
        int key = arr[i];
        int j = i - 1;
        while (arr[j] >= key && j >= 0) {
            arr[j + 1] = arr[j];
            j = j - 1;
        }
        arr[j + 1] = key;
    }
}
int main() {
    key_t key = 1234;
    sem_unlink("r");
    sem_t *r = sem_open("r", O_CREAT | O_EXCL, 0660, 0);
    if (r == SEM_FAILED) {
        perror("ERROR !! \n");
        exit(EXIT_FAILURE);
    }
    int sh_id = shmget(key, 40 * sizeof(int), IPC_CREAT | 0777);
    int *sh = (int *)shmat(sh_id, NULL, 0);
    sem_wait(r);
    sem_post(r);
    sleep(2);
    sem_wait(r);
    int c = 0;
    while (1) {
        if (sh[c] == -1) {
            break;
        } else
            c++;
    }
    insertion_sort(sh, c);
    printf("Final sorted data: \n");
    for (int i = 0; i < c; i++)
        printf("%d ", sh[i]);
    printf("\n");
    int ans = 0;
    for (int i = 0; i < 40; i++) {
        ans += sh[i];
    }
    printf("Sum: %d", ans);
    sem_close(r);
}