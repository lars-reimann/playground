#include <stdio.h>
#include <unistd.h>

void questionName() {
    char name[100];
    printf("Guten Tag, wie heissen Sie? --- ");
    scanf("%s", name);
    printf("Hallo %s! Ich bin der automatische Geschenkgenerator von Lars.\n\n",
           name);
}

void questionEvent() {
    char event[100];
    char genus = ' ';
    printf("Fuer welches Ereignis haben Sie dieses Geschenk bekommen? --- ");
    scanf("%s", event);
    do {
        getchar();
        printf("Heisst es der %s, die %s oder das %s?\n", event, event, event);
        printf("Geben Sie entsprechend m, f oder n ein. --- ");
        scanf("%c", &genus);
        if (genus != 'm' && genus != 'f' && genus != 'n') {
            printf("Ihre Eingabe entsprach weder m noch f noch n.\n\n");
            sleep(2);
        }
    } while (genus != 'm' && genus != 'f' && genus != 'n');
    if (genus == 'm' || genus == 'n') {
        printf("\nAlles Gute zum %s!\n\n", event);
    } else if (genus == 'f') {
        printf("\nAlles Gute zur %s!\n\n", event);
    }
}

void generatePresent() {
    sleep(2);

    printf("Ihr Geschenk wird erstellt.\n");
    int i, j;
    for (i = 0; i < 5; i++) {
        sleep(1);
        for (j = 0; j < 20; j++) {
            printf("."); 
        }
        printf("  %i%%\n", (i + 1) * 20);
    }
    printf("\nSie erhalten einen eintägigen Computerkurs bei ihrem \n");
    printf("Lieblingstutor Lars Reimann.\n\n");
    
    sleep(5);
    printf("Behandelt werden unter anderem elementare Bedienung von\n");
    printf("Computern - wie schaltet man ihn an und aus, wann muss man\n");
    printf("doppelt klicken, wann einzeln, usw. -, wie legt man ein Adressbuch\n");
    printf("an, uvm.\n\n");
    
    sleep(7);    
    printf("Dies alles erhalten Sie zum Supersonderangebot von nur\n");
    printf("19,99 Euro pro Stunde.\n\n");
    
    sleep(4);
    printf("War nur Spaß, das ganze ist natürlich kostenlos...\n\n");
}

void showOff() {
    printf("Danach koennen Sie unter anderem nicht Programme erstellen wie\n");
    printf("dieses, oder wie dieses:\n\n");
    
    sleep(4);
    
    int i, j;
    
    printf("    ");
    for (i = 0; i < 10; i++) {
        printf(" %.2i ", i);
    }
    
    usleep(250000);
    printf("\n--+");
    for (i = 0; i < 10; i++) {
        printf("----");
    }
    printf("\n");
    
    for (i = 1; i <= 10; i++) {
        usleep(250000);
        printf("%.2i| ", i);
        for (j = 1; j <= 10; j++) {
            printf("%.3i ", i * j);
        }
        printf("\n");
    }
    
    sleep(3);
    printf("\nViel Freude mit Ihrem Geschenk!\n\n");
}

int main() {
    questionName();
    
    sleep(2);
    questionEvent();
    
    sleep(2);
    generatePresent();
    
    sleep(5);    
    showOff();
    return 0;
}
