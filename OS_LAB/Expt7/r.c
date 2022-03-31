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

void bubble_sort(int arr[], int n) {
    int i, j;
    for (i = 0; i < n - 1; i++) {
        for (j = 0; j < n - i - 1; j++) {
            if (arr[j] > arr[j + 1]) {
                int temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
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
    bubble_sort(sh, c);
    printf("Final sorted array: ");
    for (int i = 0; i < c; i++)
        printf("%d ", sh[i]);
    printf("\n");
    int ans = 0;
    for (int i = 0; i < 40; i++) {
        ans += sh[i];
    }
    printf("Sum of the array is: %d", ans);
    sem_close(r);
}