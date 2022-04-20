#include <fcntl.h>
#include <pthread.h>
#include <stdio.h>
#include <sys/file.h>
#include <unistd.h>

int main() {
    FILE *fp;
    fp = fopen("msg.txt", "a+");
    flock(fileno(fp), LOCK_EX);
    char buff[1000];
    printf("Hello user you have succefully acquired the lock start typing once you are done please press the enter key");
    scanf("%[^\n]s", buff);
    printf("%s", buff);
    fprintf(fp, "%s\n", buff);
    fclose(fp);
    return 0;
}