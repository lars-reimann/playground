#include <stdio.h>
#include <stdlib.h>

typedef struct Node Node;

struct Node {
    Node *left;
    Node *right;
    int entry;
};

void insert(Node *root, Node *next) {
    if (root->entry > next->entry) {
        if (root->left == NULL) {
            root->left = next;
        }
        insert(root->left, next);
    } else if (root->entry < next->entry){
        if (root->right == NULL) {
            root->right = next;
        }
        insert(root->right, next);
    }
}

void freeNodes(Node *root) {
    if (root->left != NULL) {
        freeNodes(root->left);
    }
    if (root->right != NULL) {
        freeNodes(root->right);
    }
    free(root);
}

void depthFirstSearch(Node *root) {
    if (root->left != NULL) {
        depthFirstSearch(root->left);
    }
    if (root->right != NULL) {
        depthFirstSearch(root->right);
    }
    printf("%i\n", root->entry);
}

int main(void) {

    // Eingabe
    int n;
    printf("Wie viele Zahlen sollen eingefügt werden? --- ");
    scanf("%i", &n);

    if (n > 1) {
        Node *root = (Node *) malloc(sizeof(Node));
        printf("Naechste Zahl eingeben: ");
        scanf("%i", &root->entry);
        root->left = NULL;
        root->right = NULL;

        int i;
        for (i = 1; i < n; i++) {
            Node *next = (Node *) malloc(sizeof(Node));
            printf("Naechste Zahl eingeben: ");
            scanf("%i", &next->entry);
            next->left = NULL;
            next->right = NULL;

            insert(root, next);
        }
        depthFirstSearch(root);
        freeNodes(root);
    }
    return 0;
}
