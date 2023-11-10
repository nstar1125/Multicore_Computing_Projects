#include <omp.h>
#include <stdio.h>

int isPrime(int x){
    if(x<=1)
        return 0;
    for(int i=2; i<x; i++){
        if(x%i==0)
            return 0;
    }
    return 1;
}
int main (int argc, char* argv[])
{
    int type = atoi(argv[1]);
    int t_num = atoi(argv[2]);
    int num_end = 200000;
    int counter = 0;
    int i;
    double start_time, end_time;
    
    omp_set_num_threads(t_num);
    
    start_time = omp_get_wtime();
    switch(type){
        case 1:
#pragma omp parallel for schedule(static) num_threads(t_num) \
reduction(+: counter)
            for (i = 0; i <= num_end; i++) {
                if (isPrime(i))
                    counter++;
            }
            break;
        case 2:
#pragma omp parallel for schedule(dynamic) num_threads(t_num) \
reduction(+: counter)
            for (i = 0; i <= num_end; i++) {
                if (isPrime(i))
                    counter++;
            }
            break;
        case 3:
#pragma omp parallel for schedule(static, 10) num_threads(t_num) \
reduction(+: counter)
            for (i = 0; i <= num_end; i++) {
                if (isPrime(i))
                    counter++;
            }
            break;
        case 4:
#pragma omp parallel for schedule(dynamic, 10) num_threads(t_num) \
reduction(+: counter)
            for (i = 0; i <= num_end; i++) {
                if (isPrime(i))
                    counter++;
            }
            break;
    }
    end_time = omp_get_wtime();
    printf("%d\n", counter);
    double timeDiff = end_time - start_time;
        printf("Execution Time : %lfms\n", timeDiff);
}
