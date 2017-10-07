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
        return 1; // Fehler beim Erzeugen des Sockets
    }

    char msg[64] = "Hello, world!";
    int len;
    struct sockaddr_in to;

    to.sin_family = AF_INET;
    to.sin_port = htons(4711);
    to.sin_addr.s_addr = htonl(INADDR_ANY);

    len = sendto(fd, msg, sizeof(msg), 0, (sockaddr *) &to, sizeof(sockaddr_in));
    if (len < 0) {
        close(fd);
        return 2;
    }

    close(fd);
    return 0;
}
