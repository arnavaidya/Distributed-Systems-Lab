import java.io.*;
import java.net.*;
import java.util.*;

class Process {
    public int id;
    public boolean active;
    public int idx;
    public Process(int id, boolean active) {
        this.id = id;
        this.active = active;
        this.idx = 0;
    }
}

public class Ring {
    static List<Process> processes = new ArrayList<>();
    static List<Integer> processRing = new ArrayList<>();

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        for(int i = 1; i<=n; i++)
        {
            processes.add(new Process(i, true));
        }

        for(int i = 1; i<=n; i++)
        {
            int curr = in.nextInt();
            processes.get(curr-1).idx = i-1;
            processRing.add(curr);

        }

        System.out.println("Enter Number of Processes to terminate : ");
        int x = in.nextInt();

        while(x > 0)
        {
            System.out.println("Enter Process to terminate : ");
            int prc = in.nextInt();

            processes.get(prc-1).active = false;
            x--;
        }
        System.out.println("Enter Process to Start Election : ");
        int start = in.nextInt();

        int si = (processes.get(start - 1).idx + 1) % n;
        int cnt = 1;
        int ans = processes.get(start - 1).id;
        System.out.println("Election Started by : " + processes.get(start - 1).id + "\n");
        while(cnt < n)
        {
            if(processes.get(processRing.get(si) - 1).active)
            {
                System.out.println("Election Message Sent to : " + processes.get(processRing.get(si) - 1).id + "\n");
                if(processes.get(processRing.get(si) - 1).id > ans)
                    ans = processes.get(processRing.get(si) - 1).id;
            }
            si = (si + 1) % n;
            cnt++;
        }
        System.out.println("Final Leader : " + ans + "\n");
    }
}
