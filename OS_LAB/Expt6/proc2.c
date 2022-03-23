#include <error.h>
#include <fcntl.h>
#include <semaphore.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/shm.h>
#include <sys/wait.h>

int main() {
    int choice = 1;
    char input[20];
    key_t key = ftok("shmfile", 65);
    int segment_id = shmget(key, 4096, 0666 | IPC_CREAT);
    char* shared_memory = (char*)shmat(segment_id, NULL, 0);
    while (choice == 1) {
        printf("Shared memory data: %s\n", shared_memory);
        printf("Enter the district: \n");
        scanf("%s", input);
        sprintf(shared_memory, input);
        printf("Press 1 to continue: \n");
        scanf("%d", &choice);
    }
    shmdt(shared_memory);
    return 0;
}
