#include <thrust/device_vector.h>
#include <thrust/functional.h>
#include <thrust/transform_reduce.h>
#include <thrust/execution_policy.h>
#include <iostream>

struct calculate_sum : public thrust::unary_function<long, double>
{
    double step;

    calculate_sum(double step) : step(step) {}

    __device__
    double operator()(const long& i) const
    {
        double x = (i + 0.5) * step;
        return 4.0 / (1.0 + x * x);
    }
};

int main()
{
    long num_steps = 1000000000;
    double step = 1.0 / static_cast<double>(num_steps);

    thrust::device_vector<long> indices(num_steps);
    thrust::sequence(indices.begin(), indices.end());

    clock_t start_time = clock();

    double sum = thrust::transform_reduce(thrust::device, indices.begin(), indices.end(),
                                          calculate_sum(step), 0.0, thrust::plus<double>());

    double pi = step * sum;

    clock_t end_time = clock();
    
    clock_t diff_time = end_time - start_time;
    printf("execution time: %f sec. \n", (double)diff_time/CLOCKS_PER_SEC);

    printf("pi = %.10lf\n", pi);

    return 0;
}
