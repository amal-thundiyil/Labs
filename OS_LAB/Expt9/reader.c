#include <fcntl.h>
#include <pthread.h>
#include <stdio.h>
#include <sys/file.h>
#include <unistd.h>

int main() {
    FILE *fp;
    fp = fopen("msg.txt", "r");
    flock(fileno(fp), LOCK_SH);
    do {
        char c = fgetc(fp);
        if (feof(fp)) {
            break;
        }
        sleep(1);
        printf("%c", c);
        fflush(stdout);
    } while (1);
    flock(fileno(fp), LOCK_UN);
    printf("\n");
    return 0;
}