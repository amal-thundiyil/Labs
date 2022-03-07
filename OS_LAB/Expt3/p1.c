#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <unistd.h>
int main(int cnt, char *arr[]) {
    char *a = arr[1];
    int lim = atoi(a);
    for (int i = 0; i < lim; i++) {
        if (fork() == 0) {
            printf("CREATED: Child Process %d of Parent Process %d\n", getpid(), getppid());
            unsigned int seed = rand();
            int r = rand_r(&seed) % 12;
            printf("Child process is sleeping for %d \n", r);
            sleep(r);
            printf("EXIT: child process %d of parent process %d is exiting\n", getpid(), getppid());
            exit(0);
        }
    }
    for (int i = 0; i < lim; i++)
        wait(NULL);

    return 0;
}