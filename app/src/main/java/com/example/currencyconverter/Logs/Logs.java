package com.example.currencyconverter.Logs;

import java.util.ArrayList;
import java.util.List;

public class Logs {

    private List<Log> logs = new ArrayList<>();

    public List<Log> getLogs() {
        return logs;
    }

    public void setLogs(List<Log> log) {
        this.logs = log;
    }

    public void addLog(Log log) {
        if (logs.size() == 11) {
            for (int i = 0; i < logs.size() - 1; i++) {
                logs.set(i, logs.get(i + 1));
            }
            logs.remove(10);
        }
        logs.add(log);
    }


}
