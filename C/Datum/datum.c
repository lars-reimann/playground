#include <stdio.h>
#include <math.h>

typedef struct date {
    int day;
    int month;
    int year;
} DATE ;

typedef struct time {
    int hours;
    int minutes;
    int seconds;
} TIME;

typedef struct datetime {
    DATE date;
    TIME time;
} DATETIME;

DATETIME start;

int isLeapYear(DATETIME *dt) {
    return (dt->date.year % 400 == 0) ||
           (dt->date.year % 4 == 0 && dt->date.year %100 != 0);
}

int isShortMonth(DATETIME *dt) {
    return dt->date.month == 4 || dt->date.month == 6 ||
           dt->date.month == 9 || dt->date.month == 11;
}

int lenOfMonth(DATETIME *dt) {
    if (dt->date.month == 2) {
        if (!isLeapYear(dt)) {
            return 28;
        }
        return 29;
    } else if (isShortMonth(dt)) {
        return 30;
    }
    return 31;
}

void nextday(DATETIME *dt) {
    if (dt->date.day == lenOfMonth(dt)) {
        dt->date.day = 1;
        if (dt->date.month == 12) {
            dt->date.month = 1;
            dt->date.year++;
        } else {
            dt->date.month++;
        }
    } else {
        dt->date.day++;
    }
}

void tick(DATETIME *dt) {
    if (dt->time.seconds == 59) {
        dt->time.seconds = 0;
        if (dt->time.minutes == 59) {
            dt->time.minutes = 0;
            if (dt->time.hours == 23) {
                dt->time.hours = 0;
                nextday(dt);
            } else {
                dt->time.hours++;
            }
        } else {
            dt->time.minutes++;
        }
    } else {
        dt->time.seconds++;
    }
}

int equal(DATETIME *dt1, DATETIME *dt2) {
    return dt1->date.day == dt2->date.day &&
           dt1->date.month == dt2->date.month &&
           dt1->date.year == dt2->date.year;
}

int daysBetweenDates(DATETIME *dt1, DATETIME *dt2) {
    int count = 0;
    while (!equal(dt1, dt2)) {
        nextday(dt1);
        count++;
    }
    return count;
}

int dayOfWeek(DATETIME *dt) {
    return (4 + daysBetweenDates(&start, dt)) % 7;
}

void printDatetime(DATETIME *dt) {
    printf("Datum: %02i.%02i.%02i\n", dt->date.day, dt->date.month,
                 dt->date.year);
    printf("Zeit: %02i:%02i:%02i\n", dt->time.hours, dt->time.minutes,
                 dt->time.seconds);
    switch (dayOfWeek(dt)) {
        case 0: {
            printf("Wochentag: Montag\n");
            break;
        } case 1: {
            printf("Wochentag: Dienstag\n");
            break;
        } case 2: {
            printf("Wochentag: Mittwoch\n");
            break;
        } case 3: {
            printf("Wochentag: Donnerstag\n");
            break;
        } case 4: {
            printf("Wochentag: Freitag\n");
            break;
        } case 5: {
            printf("Wochentag: Samstag\n");
            break;
        } case 6: {
            printf("Wochentag: Sonntag\n");
            break;
        }
    }
}

void init() {
    start.date.day = 15;
    start.date.month = 10;
    start.date.year = 1582;
    start.time.hours = 0;
    start.time.minutes = 0;
    start.time.seconds = 0;
}

int main(void) {

    DATE date;
    printf("Tag: ");
    scanf("%i", &date.day);
    printf("Monat: ");
    scanf("%i", &date.month);
    printf("Jahr: ");
    scanf("%i", &date.year);

    TIME time;
    printf("Stunden: ");
    scanf("%i", &time.hours);
    printf("Minuten: ");
    scanf("%i", &time.minutes);
    printf("Sekunden: ");
    scanf("%i", &time.seconds);

    DATETIME dt;
    dt.date = date;
    dt.time = time;

    init();
    printDatetime(&dt);

    tick(&dt);

    init();
    printf("---Tick---\n");
    printDatetime(&dt);

    return 0;
}
