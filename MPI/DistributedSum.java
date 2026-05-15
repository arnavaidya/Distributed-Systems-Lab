import mpi.*;

public class DistributedSum {
    public static void main(String[] args) {
        MPI.Init(args);
        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();

        int[] sendbuf = new int[size]; // root array
        int[] recvbuf = new int[1];  // each process gets 1 element
        int[] result = new int[1]; // prefix sum result

        if(rank == 0) {  // root
            System.out.println("Root Initialising array : ");
            for(int i = 0; i < size ; i++) {
                sendbuf[i] = (i+1) * 10;
            }
        }

        // Scatter : Distribute 1 element to each
        MPI.COMM_WORLD.Scatter(sendbuf, 0, 1, MPI.INT,
                               recvbuf, 0, 1, MPI.INT,
                               0);

        // Scan : Compute sum
        MPI.COMM_WORLD.Scan(recvbuf, 0, result, 0, 1, MPI.INT, MPI.SUM);

        System.out.println("Process " + rank + " received " + recvbuf[0] + " | intermdiate sum = " + result[0]);
        MPI.Finalize();
    }
}
