#include <pthread.h>
#include <semaphore.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <time.h>

int main() {
    int n;
    printf("Enter no. of elems: \n");
    scanf("%d", &n);
    printf("\n");
    key_t key = 1234;
    int sh_id = shmget(key, 40 * sizeof(int), IPC_CREAT | 0777);
    int *sh = (int *)shmat(sh_id, NULL, 0);
    sem_t *p = sem_open("r", 0);
    for (int i = 0; i < n; i++)
        scanf("%d", &sh[i]);
    sh[n] = -1;
    sem_post(p);
}