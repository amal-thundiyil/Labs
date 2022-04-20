#include <error.h>
#include <fcntl.h>
#include <semaphore.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/shm.h>
#include <sys/wait.h>
#include <unistd.h>

int main() {
    sem_t mutex;
    key_t key = 1234;
    int shm_id = shmget(
        key,
        41 * sizeof(int),
        IPC_CREAT | 0666);
    int *sh = (int *)shmat(shm_id, NULL, 0);
    sem_t *sem = sem_open("my_mutex", O_CREAT | O_EXCL, 0777, 0);
    int data = -1;
    sem_wait(sem);
    sem_wait(sem);
    int i, j;
    for (i = 0; i < 40 - 1; i++)
        for (j = 0; j < 40 - i - 1; j++)
            if (sh[j] > sh[j + 1]) {
                int temp = sh[j];
                sh[j] = sh[j + 1];
                sh[j + 1] = temp;
            }
    int ans = 0;
    for (int i = 0; i < 40; i++) {
        ans += sh[i];
    }
    printf("Sum: %d\n", ans);
    for (int i = 0; i < 40; i++) {
        printf("%d ", sh[i]);
    }
    printf("\n");
    sem_unlink("my_mutex");
    return 0;
}