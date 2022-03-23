// The program r.c initializes n number of semaphores. It first assign count equal -1, which is then used by process p and q. This count is protected by semaphore. It also allocates shared memory of size 40 ints. It waits for process p and q to enter all n1 and n2 elements through different terminals. This program r.c sorts shared data in ascending order. It waits to finish p and q. At end, The program r.c detaches and deletes n semaphores and print the sum of all elements of the list.
#include <error.h>
#include <fcntl.h>
#include <pthread.h>
#include <semaphore.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/shm.h>
#include <sys/wait.h>
#include <unistd.h>

sem_t mutex;

void* thread(void* arg) {
    sem_wait(&mutex);
    printf("\nEntered..\n");

    sleep(4);

    printf("\nJust Exiting...\n");
    sem_post(&mutex);
}

int main() {
    int choice = 1;
    int n = 4, count = -1;
    key_t key = ftok("shmfile", 65);
    int segment_id = shmget(key, 4096, (40 * sizeof(int)) | IPC_CREAT);
    char* shared_memory = (char*)shmat(segment_id, NULL, 0);
    sprintf(shared_memory, "%d", count);
    printf("Shared memory data: %s\n", shared_memory);

    // sem_init(&mutex, 0, 1);
    // pthread_t t1, t2;
    // pthread_create(&t1, NULL, thread, NULL);
    // sleep(2);
    // pthread_create(&t2, NULL, thread, NULL);
    // pthread_join(t1, NULL);
    // pthread_join(t2, NULL);

    shmdt(shared_memory);
    sem_destroy(&mutex);
    return 0;
}