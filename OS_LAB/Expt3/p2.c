// Sequential fork -Write a program p2.c that takes a number n as command line argument and creates n child processes sequentially, i.e., the first parent process (p2) creates all children in a loop without any delays. Let the child processes sleep for a small random duration (use the urand_r() call, and print the creation and exit order of the child processes. Note that the random numbers used for sleep should be different across the child processes.

#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <threads.h>
#include <time.h>
#include <unistd.h>

int main(int argc, char* argv[]) {
    // int N = atoi(argv[1]);
    int N = 4;
    for (int i = 0; i < N; i++) {
        if (fork() == 0) {
            pid_t pid = getpid();
            printf("CREATED: Child Process %d of Parent Process %d", pid, getppid());
            unsigned int seed = time(NULL);
            sleep(rand_r(&seed));
            int status;
            waitpid(pid, &status, 0);
            if (WIFEXITED(status)) {
                int exit_status = WEXITSTATUS(status);
                printf("EXIT: Exit status of the child was %d\n",
                       exit_status);
            }
        }
    }
    return 0;
}