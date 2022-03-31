#include <pthread.h>
#include <semaphore.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <time.h>

int main() {
    key_t key = 1234;
    int segment_id = shmget(key, 40 * sizeof(int), IPC_CREAT | 0777);
    int *sh = (int *)shmat(segment_id, NULL, 0);
    sem_t *p = sem_open("r", 0);
    sem_wait(p);
    int n, k = 0;
    printf("Enter no. of elems: \n");
    scanf("%d", &n);
    printf("\n");
    while (1) {
        if (sh[k] == -1) {
            break;
        } else
            k++;
    }
    for (int i = 0; i < n; i++)
        scanf("%d", &sh[k + i]);
    sh[k + n] = -1;
    sem_post(p);
}