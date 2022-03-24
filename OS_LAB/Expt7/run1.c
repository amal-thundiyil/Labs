#include <mach/error.h>
#include <fcntl.h>
#include <semaphore.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/shm.h>
#include <sys/wait.h>

// extern int errno;

int main(void)

{
    sem_t *sem_id = sem_open("sema8", O_CREAT | O_EXCL, 0777, 1);

    if (sem_id == (void *)-1)

    {
        printf("sema failed");

        return 0;
    }

    sem_wait(sem_id);

    return 0;
}