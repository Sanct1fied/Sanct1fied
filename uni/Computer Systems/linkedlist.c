#include<stdio.h>
#include<stdlib.h>

typedef struct elem {
 int val;
 struct elem *nxt;
} elem;

void insertq(elem *qhead, int value){
    elem *new = malloc(sizeof(elem));

    if (new != NULL) {
        new->val = value;
        new->nxt = NULL;
        elem *cur = qhead;
        while (cur->nxt != NULL) {
            cur = cur->nxt;
        }
        cur->nxt = new;
    }
}

void removeq(elem *qhead){//find the last element and remove it.
    if (qhead != NULL && qhead->nxt != NULL) {
        elem *cur = qhead; //start at the head
        while (cur->nxt->nxt != NULL) { //until the final element is found
            cur = cur->nxt; //iterate through the list
        }
        free(cur->nxt); //remove the last element
        cur->nxt = NULL;
    }
}

void sizeq(elem *qhead) {
    int count = 0;
    elem *cur = qhead;
    while (cur != NULL) {
        count++; //record the size
        cur = cur->nxt;
    }
    printf("Size of the linked list: %d\n", count);
}

void searchq(elem *qhead, int value) {
    elem *cur = qhead;
    while (cur != NULL) {
        if (cur->val == value) { //until value is found.
            printf("Value %d found in the linked list.\n", value);
            return;
        }
        cur = cur->nxt; //iterate through the list
    }
    printf("Value %d not found in the linked list.\n", value);
}

elem *head;

int main() {
    elem *e1 = malloc(sizeof(elem));
    if (e1 == NULL) {
        printf("Memory allocation failed\n");
        return 1;
    }
    e1->val = 1;
    e1->nxt = NULL;
    head = e1;

    insertq(head, 2);
    insertq(head, 3);
    insertq(head, 4);
    removeq(head);
    sizeq(head);
    searchq(head, 2);
    printf("Linked list elements: ");
    elem *current = head;
    while (current != NULL) {
        printf("%d ", current->val);
        current = current->nxt;
    }
    printf("\n");

    return 0;
}
