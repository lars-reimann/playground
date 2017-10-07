#include <stdio.h>
#include <arpa/inet.h>
#include <netinet/in.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <unistd.h>

int main() {
    int fd, err;

    fd = socket(AF_INET, SOCK_DGRAM, 0);
    if (fd < 0) {
        return 1; // Fehler beim Erstellen des Sockets
    }

    char msg[64];
    int len;
    unsigned int tolen;
    struct sockaddr_in to;

    to.sin_family = AF_INET;
    to.sin_port = htons(2007);
    inet_aton("192.168.2.102", &to.sin_addr);

    while (1) {
        printf("msg> ");
        scanf("%s", msg);
        len = sendto(fd, msg, sizeof(msg), 0, (struct sockaddr *) &to , sizeof(struct sockaddr_in));
        if (len < 0) {
            close(fd);
            return 2; // Fehler beim Senden
        }
        len = recvfrom(fd, msg, sizeof(msg), 0, (struct sockaddr *) &to, &tolen);
        if (len < 0) {
            close(fd);
            return 3; // Fehler beim Empfangen
        }
        printf("%s\n", msg);
    }

    close(fd);
    return 0;
}
