#include <fcntl.h>
#include <semaphore.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <sys/ipc.h>
#include <sys/shm.h>
#include <sys/wait.h>
#include <time.h>
#include <unistd.h>

int main() {
    key_t key = 1234;
    int shm_id = shmget(
        key,
        41 * sizeof(int),
        IPC_CREAT | 0666);
    int *sh = (int *)shmat(shm_id, NULL, 0);
    sem_t *sem = sem_open("my_mutex", O_RDWR);
    for (int i = 0; i < 20; i++) {
        int idx = sh[40];
        sh[40]++;
        printf("Element %d: ", idx);
        scanf("%d", &sh[idx]);
    }
    printf("%d\n", sh[40]);
    sem_post(sem);
}