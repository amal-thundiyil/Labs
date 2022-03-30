#include <errno.h>
#include <fcntl.h>
#include <pthread.h>
#include <semaphore.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <time.h>
#include <unistd.h>

#define SEM_MUTEX_NAME "/sem-mutex"
#define MAX_BUFFERS 40
#define COUNT -1

char buf[MAX_BUFFERS][100];
int buffer_index;
int buffer_print_index;

sem_t *mutex_sem, *buffer_count_sem, *mutex_sem;

int main(void) {
    if ((mutex_sem = sem_open(SEM_MUTEX_NAME, O_CREAT, 0660, 1)) == SEM_FAILED) {
        perror("sem_open");
        exit(1);
    }

    int semval;
    while (1) {
        if (sem_getvalue(mutex_sem, &semval) == -1)
            perror("sem_getvalue");
        if (!semval) break;
        sleep(1);
    }

    if (sem_unlink("COUNT") == -1) {
        perror("sem_unlink");
        exit(1);
    }

    exit(0);
}