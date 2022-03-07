#include <limits.h>

#include <algorithm>
#include <iostream>
using namespace std;

struct Process {
    int pid;
    int burst_time;
    int arrival_time;
};

void calc_turn_around_time(Process proc[], int n, int waiting_time[], int turnaround_time[]) {
    for (int i = 0; i < n; i++)
        turnaround_time[i] = proc[i].burst_time + waiting_time[i];
}

void calc_waiting_time(Process proc[], int n, int waiting_time[]) {
    int rt[n];
    for (int i = 0; i < n; i++)
        rt[i] = proc[i].burst_time;
    int complete = 0, t = 0, minm = INT_MAX;
    int shortest = 0, finish_time;
    bool check = false;
    while (complete != n) {
        for (int j = 0; j < n; j++) {
            if ((proc[j].arrival_time <= t) && (rt[j] < minm) && rt[j] > 0) {
                minm = rt[j];
                shortest = j;
                check = true;
            }
        }
        if (check == false) {
            t++;
            continue;
        }
        rt[shortest]--;
        minm = rt[shortest];
        if (minm == 0)
            minm = INT_MAX;
        if (rt[shortest] == 0) {
            complete++;
            check = false;
            finish_time = t + 1;
            waiting_time[shortest] = finish_time -
                                     proc[shortest].burst_time -
                                     proc[shortest].arrival_time;
            if (waiting_time[shortest] < 0)
                waiting_time[shortest] = 0;
        }
        t++;
    }
}
void calc_average_time(Process proc[], int n, int waiting_time[], int turnaround_time[]) {
    int total_waiting_time = 0, total_turnaround_time = 0;

    cout << "Processes "
         << " Arrival time "
         << "    Burst time "
         << "   Waiting time "
         << "   Turn around time\n";
    for (int i = 0; i < n; i++) {
        total_waiting_time = total_waiting_time + waiting_time[i];
        total_turnaround_time = total_turnaround_time + turnaround_time[i];
        cout << " " << proc[i].pid << "\t\t" << proc[i].arrival_time << "\t\t" << proc[i].burst_time << "\t\t " << waiting_time[i] << "\t\t " << turnaround_time[i] << endl;
    }
    cout << "\nAverage waiting time = " << (float)total_waiting_time / (float)n;
    cout << "\nAverage turn around time = " << (float)total_turnaround_time / (float)n;
    cout << "\n";
}
int main() {
    int n;
    cin >> n;
    Process proc[n];
    for (int i = 0; i < n; i++) {
        cin >> proc[i].pid >> proc[i].burst_time >> proc[i].arrival_time;
    }

    int waiting_time[n], turnaround_time[n];
    calc_waiting_time(proc, n, waiting_time);
    calc_turn_around_time(proc, n, waiting_time, turnaround_time);
    calc_average_time(proc, n, waiting_time, turnaround_time);
    return 0;
}