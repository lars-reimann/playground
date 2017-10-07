#include <stdio.h>
#include <arpa/inet.h>
#include <netinet/in.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <unistd.h>

int main() {
    int fd, err;
    struct sockaddr_in addr;

    // Es wird ein neuer Socket mit AddressFamily InterNET erzeugt. Auf unterster
    // Ebene wird also IP verwendet. Darauf aufsetzend wird UDP verwendet, was
    // durch den zweiten Parameter SOCKet DataGRAM angezeigt wird. Es wird ein
    // File Descriptor erzeugt und zurückgegeben.
    fd = socket(AF_INET, SOCK_DGRAM, 0);
    if (fd < 0) {
        return 1; // Fehler beim Erzeugen des Sockets
    }

    // Socket INternet FAMILY wird die AddressFamily InterNET zugewiesen.
    addr.sin_family = AF_INET;

    // Socket INternet PORT wird gesetzt. 4711 wird von Host byte order in Network
    // byte order umgewandelt, wobei 4711 als Short behandelt wird.
    addr.sin_port = htons(4711);
    addr.sin_addr.s_addr = htonl(INADDR_ANY);

    // Der Socket wird an die angegeben Adresse gebunden.
    err = bind(fd, (struct sockaddr *) &addr, sizeof(struct sockaddr_in));
    if (err < 0) {
        return 2; // Fehler beim Binden
    }

    char msg[64];
    int len;
    unsigned int flen;
    struct sockaddr_in from;

    len = recvfrom(fd, msg, sizeof(msg), 0, (struct sockaddr *) &from, &flen);
    if (len < 0) {
        return 3; // Fehler beim Empfangen
    }

    printf("Received %d bytes from host %s port %d: %s\n", len, inet_ntoa(from.sin_addr), ntohs(from.sin_port), msg);

    close(fd);
    return 0;
}
