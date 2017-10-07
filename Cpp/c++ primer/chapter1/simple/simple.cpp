#include <iostream>

void log(int value, int count) {
    std::cout << value << " occurs " << count << " times." << std::endl;
}

int main() {
    int value, a;
    if (std::cin >> value) {
        int count = 1;
        while (std::cin >> a) {
            if (a == value) {
                count++;
            } else {
                log(value, count);
                value = a;
                count = 1;
            }
        }
        log(value, count);
    }
}