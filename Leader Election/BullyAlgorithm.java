import java.util.*;

class Process {
    int id;
    boolean active = false;
    public Process(int id) {
        this.id = id;
        this.active = true;
    }
}

public class BullyAlgorithm {
    static List<Process> processes = new ArrayList<>();
    static int coordinator = -1;

    public static void main(String[] args) {
        int n = 5;

        for(int i = 1; i<=n; i++) {
            processes.add(new Process(i));
        }

        coordinator = n;
        System.out.println("Current coordinator : " + n);
        processes.get(coordinator - 1).active = false; // failed

        startElection(2);
    }

    static void startElection(int initiatorId) {
        if(!processes.get(initiatorId - 1).active) return;
        System.out.println(initiatorId + " Starts an election.\n");
        boolean higher = false;

        for(Process p: processes) {
            if(p.id > initiatorId && p.active) {
                higher = true;
                System.out.println(p.id + " is active.\n");
                startElection(p.id);
            }
        }

        if(!higher) {
            coordinator = initiatorId;
            System.out.println(initiatorId + " is now coordinator.\n");
            for(Process p : processes) {
                if(p.id < initiatorId && p.active)  {
                    System.out.println(initiatorId + " informs " + p.id + "\n");
                }
            }
        }
    }
}
