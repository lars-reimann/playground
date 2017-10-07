#include <stdio.h>
#include <arpa/inet.h>
#include <netinet/in.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <unistd.h>

int main() {
    int fd, err;
    struct sockaddr_in addr;

    fd = socket(AF_INET, SOCK_DGRAM, 0);
    if (fd < 0) {
        return 1; // Fehler beim Erstellen des Sockets
    }

    addr.sin_family = AF_INET;
    addr.sin_port = htons(1234);
    addr.sin_addr.s_addr = htonl(INADDR_ANY);

    err = bind(fd, (struct sockaddr *) &addr, sizeof(sockaddr_in));
    if (err < 0) {
        close(fd);
        return 2; // Fehler beim Binden
    }

    char msg[64];
    int len;
    unsigned int flen;
    struct sockaddr_in from;

    while (1) {
        len = recvfrom(fd, msg, sizeof(msg), 0, (struct sockaddr *) &from, &flen);
        if (len < 0) {
            close(fd);
            return 3; // Fehler beim Empfangen
        }
        printf("Received %d bytes from host %s port %d: %s\n", len, inet_ntoa(from.sin_addr), ntohs(from.sin_port), msg);
        sendto(fd, msg, sizeof(msg), 0, (struct sockaddr *) &from, sizeof(sockaddr_in));
    }

    close(fd);
    return 0;
}
