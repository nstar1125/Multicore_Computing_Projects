#include <omp.h>
#include <stdio.h>

long num_steps = 10000000; 
double step;

int main (int argc, char* argv[])
{
    int type = atoi(argv[1]);
    int size = atoi(argv[2]);
    int t_num = atoi(argv[3]);
    
	long i; double x, pi, sum = 0.0;
	double start_time, end_time;

	start_time = omp_get_wtime();
	step = 1.0/(double) num_steps;
    
    switch(type){
        case 1:
#pragma omp parallel for schedule(static, size) num_threads(t_num) \
reduction(+: sum)
            for (i=0;i< num_steps; i++){
                x = (i+0.5)*step;
                sum = sum + 4.0/(1.0+x*x);
            }
            break;
        case 2:
#pragma omp parallel for schedule(dynamic, size) num_threads(t_num) \
reduction(+: sum)
            for (i=0;i< num_steps; i++){
                x = (i+0.5)*step;
                sum = sum + 4.0/(1.0+x*x);
            }
            break;
        case 3:
#pragma omp parallel for schedule(guided, size) num_threads(t_num) \
reduction(+: sum)
            for (i=0;i< num_steps; i++){
                x = (i+0.5)*step;
                sum = sum + 4.0/(1.0+x*x);
            }
            break;
    }

	pi = step * sum;
	end_time = omp_get_wtime();
	double timeDiff = end_time - start_time;
        printf("Execution Time : %lfms\n", timeDiff);

	printf("pi=%.24lf\n",pi);
}
