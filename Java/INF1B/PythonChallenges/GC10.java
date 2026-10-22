void main() {
    double n = 0;
    double n1 = 1;
    for (int i = 1; i < 100; i++)
    {
        double n2 = n1 + n;
        System.out.print(n2);
        System.out.print("\n");
        n = n1;
        n1 = n2;

    }
}
