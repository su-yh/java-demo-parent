package com.suyh5901;

import org.springframework.util.ReflectionUtils;
import org.springframework.util.StopWatch;

import java.lang.reflect.Field;
import java.text.NumberFormat;

/**
 * @author suyh
 * @since 2024-04-13
 */
public class SuyhStopWatch extends StopWatch {
    public SuyhStopWatch() {

    }

    public SuyhStopWatch(String id) {
        super(id);
    }

    @Override
    public String prettyPrint() {
        boolean keepTaskList = true;
        Field keepTaskListField = ReflectionUtils.findField(StopWatch.class, "keepTaskList");
        ReflectionUtils.makeAccessible(keepTaskListField);
        try {
            keepTaskList = keepTaskListField.getBoolean(this);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        StringBuilder sb = new StringBuilder(shortSummary());
        sb.append('\n');
        if (!keepTaskList) {
            sb.append("No task info kept");
        }
        else {
            sb.append("---------------------------------------------\n");
            sb.append("ms         %     Task name\n");
            sb.append("---------------------------------------------\n");
            NumberFormat nf = NumberFormat.getNumberInstance();
            nf.setMinimumIntegerDigits(9);
            nf.setGroupingUsed(false);
            NumberFormat pf = NumberFormat.getPercentInstance();
            pf.setMinimumIntegerDigits(3);
            pf.setGroupingUsed(false);
            for (TaskInfo task : getTaskInfo()) {
                sb.append(nf.format(task.getTimeMillis())).append("  ");
                sb.append(pf.format((double) task.getTimeNanos() / getTotalTimeNanos())).append("  ");
                sb.append(task.getTaskName()).append('\n');
            }
        }
        return sb.toString();
    }
}
