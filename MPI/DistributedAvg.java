import mpi.*;

public class DistributedAvg {
    public static void main(String[] args) {
        MPI.Init(args);

        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();
        int N = 12; // Elements in array
        int chunkSize = N / size;

        int[] sendbuf = new int[N];
        int[] recvbuf = new int[chunkSize];

        double[] localAvg = new double[1];
        double[] gatheredAvg = new double[size];

        if(rank == 0) // root process -> init array
        {

            for(int i = 0; i < N; i++)
            {
                sendbuf[i] = (i+1) * 10;
            }
        }
        MPI.COMM_WORLD.Scatter(sendbuf, 0, chunkSize, MPI.INT,
                               recvbuf, 0, chunkSize, MPI.INT,
                               0);

        // local averages
        int sum = 0;
        for(int i = 0; i<chunkSize; i++)
        {
            sum += recvbuf[i];
        }
        localAvg[0] = (double) sum / chunkSize;
        System.out.println("Process " + rank + " local average = " + localAvg[0]);

        MPI.COMM_WORLD.Gather(localAvg, 0, 1, MPI.DOUBLE,
                              gatheredAvg, 0, 1, MPI.DOUBLE,
                              0);
        if(rank == 0) // final avg calc
        {
            double finalAvg = 0;
            for(int i = 0; i<size; i++)
            {
                finalAvg += gatheredAvg[i];
            }
            finalAvg /= size;
            System.out.println("Final Average = " + finalAvg);
        }

        MPI.Finalize();
    }
}
