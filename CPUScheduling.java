import java.util.*;

class Process {
    String pid;
    int at, bt, ct, tat, wt, remainingBt;
    boolean completed = false;

    Process(String pid, int at, int bt) {
        this.pid = pid;
        this.at = at;
        this.bt = bt;
        this.remainingBt = bt;
    }
}

public class CPUScheduling {

    // Print table results
    static void printResults(List<Process> processes) {
        int n = processes.size();
        double totalTAT = 0, totalWT = 0;

        System.out.println("Process\tAT\tBT\tCT\tTAT\tWT");
        for (Process p : processes) {
            p.tat = p.ct - p.at;
            p.wt = p.tat - p.bt;
            totalTAT += p.tat;
            totalWT += p.wt;
            System.out.println(p.pid + "\t" + p.at + "\t" + p.bt + "\t" +
                               p.ct + "\t" + p.tat + "\t" + p.wt);
        }
        System.out.printf("Average TAT = %.2f\n", totalTAT / n);
        System.out.printf("Average WT = %.2f\n\n", totalWT / n);
    }

    // Print Gantt chart
    static void printGanttChart(List<String> gantt) {
        System.out.print("Gantt Chart: ");
        for (String s : gantt) {
            System.out.print(s + " | ");
        }
        System.out.println("\n");
    }

    // FCFS Scheduling
    static void fcfs(List<Process> processes) {
        System.out.println("=== FCFS Scheduling ===");
        List<Process> copy = deepCopy(processes);
        copy.sort(Comparator.comparingInt(p -> p.at));

        int time = 0;
        List<String> gantt = new ArrayList<>();
        for (Process p : copy) {
            if (time < p.at) time = p.at;
            time += p.bt;
            p.ct = time;
            gantt.add(p.pid);
        }

        printGanttChart(gantt);
        printResults(copy);
    }

    // SJF Non-preemptive
    static void sjf(List<Process> processes) {
        System.out.println("=== SJF (Non-preemptive) Scheduling ===");
        List<Process> copy = deepCopy(processes);

        int time = 0, completed = 0;
        List<String> gantt = new ArrayList<>();

        while (completed < copy.size()) {
            Process shortest = null;
            for (Process p : copy) {
                if (!p.completed && p.at <= time) {
                    if (shortest == null || p.bt < shortest.bt) {
                        shortest = p;
                    }
                }
            }
            if (shortest == null) {
                time++;
            } else {
                time += shortest.bt;
                shortest.ct = time;
                shortest.completed = true;
                gantt.add(shortest.pid);
                completed++;
            }
        }

        printGanttChart(gantt);
        printResults(copy);
    }

    // SRTF Preemptive
    static void srtf(List<Process> processes) {
        System.out.println("=== SRTF (Preemptive) Scheduling ===");
        List<Process> copy = deepCopy(processes);

        int time = 0, completed = 0;
        List<String> gantt = new ArrayList<>();

        while (completed < copy.size()) {
            Process shortest = null;
            for (Process p : copy) {
                if (!p.completed && p.at <= time) {
                    if (shortest == null || p.remainingBt < shortest.remainingBt) {
                        shortest = p;
                    }
                }
            }
            if (shortest == null) {
                time++;
            } else {
                gantt.add(shortest.pid);
                shortest.remainingBt--;
                time++;
                if (shortest.remainingBt == 0) {
                    shortest.completed = true;
                    shortest.ct = time;
                    completed++;
                }
            }
        }

        printGanttChart(gantt);
        printResults(copy);
    }

    // Utility: deep copy process list
    static List<Process> deepCopy(List<Process> processes) {
        List<Process> newList = new ArrayList<>();
        for (Process p : processes) {
            newList.add(new Process(p.pid, p.at, p.bt));
        }
        return newList;
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
 
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        List<Process> processes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            System.out.print("Enter Process ID, Arrival Time, Burst Time: ");
            String pid = sc.next();
            int at = sc.nextInt();
            int bt = sc.nextInt();
            processes.add(new Process(pid, at, bt));
        }

        fcfs(processes);
        sjf(processes);
        srtf(processes);
        }
    }
}
