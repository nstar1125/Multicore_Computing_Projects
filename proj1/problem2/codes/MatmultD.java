package proj1_2;

import java.util.*;

public class MatmultD
{
  private static Scanner sc = new Scanner(System.in);
  public static void main(String [] args) throws InterruptedException
  {
    int thread_no=0;
    if (args.length==1) thread_no = Integer.valueOf(args[0]);
    else thread_no = 4;
        
    threadMatrix.answer = 0;
    
    int a[][]=readMatrix();
    int b[][]=readMatrix();
    threadMatrix.c = new int[a.length][b[0].length];

    long startTime = System.currentTimeMillis();
    
    threadMatrix[] thread = new threadMatrix[thread_no];
    for (int t = 0; t < thread_no; t++) {
        thread[t] = new threadMatrix(t, thread_no, a, b);
    }
    for (int t = 0; t < thread_no; t++) {
        thread[t].start();
    }
    for (int t = 0; t < thread_no; t++) {
        thread[t].join();
    }
    
    long endTime = System.currentTimeMillis();

    //System.out.println("Matrix A: ");
    //printMatrix(a);
    //System.out.println("Matrix B: ");
    //printMatrix(b);
    //System.out.println("Matrix A * B: ");
    //printMatrix(threadMatrix.c);

    System.out.println("Result: ");
    for (int t = 0; t < thread_no; t++) {
        System.out.println("Thread-" + t + "'s Execution Time : " + thread[t].timeDiff + "ms");
    }
    System.out.printf("[thread_no]:%2d , [Time]:%4d ms\n", thread_no, endTime-startTime);
    System.out.println();

    for (int t = 0; t < thread_no; t++) {
        System.out.println("Thread-" + t + "'s Sum : " + thread[t].temp );
    }

    System.out.println("Matrix Sum = " + threadMatrix.answer + "\n");
    
  }

   public static int[][] readMatrix() {
       int rows = sc.nextInt();
       int cols = sc.nextInt();
       int[][] result = new int[rows][cols];
       for (int i = 0; i < rows; i++) {
           for (int j = 0; j < cols; j++) {
              result[i][j] = sc.nextInt();
           }
       }
       return result;
   }

  public static void printMatrix(int[][] mat) {
  System.out.println("Matrix["+mat.length+"]["+mat[0].length+"]");
    int rows = mat.length;
    int columns = mat[0].length;
    int sum = 0;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {
        System.out.printf("%4d " , mat[i][j]);
        sum+=mat[i][j];
      }
      System.out.println();
    }
    System.out.println();
    System.out.println("Matrix Sum = " + sum + "\n");
  }

  static class threadMatrix extends Thread {
      int start;
      int stride;
      int[][] a;
      int[][] b;
      static int[][] c;
      
      static int answer;

      int temp = 0;

      int n;
      int m;
      int p;

      long startTime;
      long endTime;
      long timeDiff;

      public threadMatrix(int start, int stride, int[][] a, int[][] b) {
          this.start = start;
          this.stride = stride;
          this.a = a;
          this.b = b;
      }

      public void run() {
          startTime = System.currentTimeMillis();

          n = a[0].length;
          m = a.length;
          p = b[0].length;

          for (int i = start; i < m; i = i + stride) {
              for (int j = 0; j < p; j++) {
                  for (int k = 0; k < n; k++) {
                	  int element = a[i][k] * b[k][j];
                      temp += element;
                      c[i][j]+=element;
                  }
              }
          }
          answer = answer + temp;

          endTime = System.currentTimeMillis();
          timeDiff = endTime - startTime;
      }
  }
}
