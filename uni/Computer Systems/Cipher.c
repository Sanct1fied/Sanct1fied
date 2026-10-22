#include <stdio.h>
#include <stdlib.h>
#include <string.h>

long long modpow(long long base, long long exp, long long mod){
    long long result = 1;
    base %= mod;
    while(exp > 0){
        if(exp & 1)
            result = (result * base) % mod;
        base = (base * base) % mod;
        exp >>= 1;
    }
    return result;
}

char* decrypt(const int* input, size_t len, int d, int n){
    char* output = (char*)malloc(len + 1);
    for(size_t i = 0; i < len; i++){
        output[i] = (char)modpow(input[i], d, n);
    }
    output[len] = '\0';
    return output;
}

int* encrypt(const char* input, int e, int n){
    size_t len = strlen(input);
    int* output = (int*)malloc(len * sizeof(int));
    for(size_t i = 0; i < len; i++){
        output[i] = (int)modpow((unsigned char)input[i], e, n);
    }
    return output;
}


int modinverse(int a, int m){
    long long aa = a % m;
    for(int x = 1; x < m; x++){
        if((aa * x) % m == 1)
            return x;
    }
    return -1;
}

int main(){
    printf("p?: ");
    int p = 53;
    //scanf("%d", &p);
    printf("q?: ");
    int q = 59;
    //scanf("%d", &q);
    int n = p * q;
    printf("n: %d\n", n);
    if (n <= 255) {
        // Use 8-bit encryption
        printf("n must be > 255 for 8-bit encryption.\n");
        return 1;
    }

    int eulertotient = (p - 1) * (q - 1);
    printf("Choose e between 1 and %d: ", eulertotient);

    int e = 1481;
    //scanf("%d", &e);
    if (e <= 1 || e >= eulertotient) {
        printf("e out of range.\n");
        return 1;
    }
    int d = modinverse(e, eulertotient);
    if (d == -1) {
        printf("Invalid e (not coprime with %d). Choose another e.\n", eulertotient);
        return 1;
    }
    printf("d: %d\n", d);

    printf("Encrypt or Decrypt? (e/d): ");
    char choice;
    scanf(" %c", &choice);
    if (choice == 'e') {
        char message[256];
        printf("Enter message: ");
        scanf("%s", message);
        size_t len = strlen(message);
        int* encrypted = encrypt(message, e, n);
        printf("Encrypted:");
        for(size_t i = 0; i < len; i++){
            printf(" %d", encrypted[i]);
        }
        printf("\n");
    } else if (choice == 'd') {
        size_t len;
        printf("Enter number of encrypted integers: ");
        scanf("%zu", &len);
        int* encrypted = (int*)malloc(len * sizeof(int));
        printf("Enter encrypted integers: ");
        for(size_t i = 0; i < len; i++){
            scanf("%d", &encrypted[i]);
        }
        char* decrypted = decrypt(encrypted, len, d, n);
        printf("Decrypted: %s\n", decrypted);
        free(encrypted);
        free(decrypted);
    } else {
        printf("Invalid choice.\n");
        return 1;
    }
    return 0;
}
