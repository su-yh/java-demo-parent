package com.imooc.activiti.config;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.event.EventLogEntry;
import org.activiti.engine.logging.LogMDC;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.activiti.engine.test.Deployment;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

@Slf4j
public class ConfigEventLogTest {
    @Rule
    public ActivitiRule activitiRule = new ActivitiRule("activiti-event-log.cfg.xml");

    /**
     *
     * eventLogEntry.type: PROCESSINSTANCE_START, eventLog.data: {"timeStamp":1594303601256,"processDefinitionId":"my-process:1:3","createTime":1594303601256,"id":"4"}
     * eventLogEntry.type: ACTIVITY_STARTED, eventLog.data: {"timeStamp":1594303601265,"activityId":"start","processDefinitionId":"my-process:1:3","processInstanceId":"4","executionId":"5","behaviorClass":"org.activiti.engine.impl.bpmn.behavior.NoneStartEventActivityBehavior","activityName":"开始","activityType":"startEvent"}
     * eventLogEntry.type: ACTIVITY_COMPLETED, eventLog.data: {"timeStamp":1594303601268,"activityId":"start","processDefinitionId":"my-process:1:3","processInstanceId":"4","executionId":"5","behaviorClass":"org.activiti.engine.impl.bpmn.behavior.NoneStartEventActivityBehavior","activityName":"开始","activityType":"startEvent"}
     * eventLogEntry.type: SEQUENCEFLOW_TAKEN, eventLog.data: {"targetActivityId":"someTask","timeStamp":1594303601271,"sourceActivityName":"开始","targetActivityBehaviorClass":"org.activiti.engine.impl.bpmn.behavior.UserTaskActivityBehavior","sourceActivityType":"org.activiti.bpmn.model.StartEvent","targetActivityName":"Activiti is awesome!","id":"flow1","sourceActivityBehaviorClass":"org.activiti.engine.impl.bpmn.behavior.NoneStartEventActivityBehavior","targetActivityType":"org.activiti.bpmn.model.UserTask","sourceActivityId":"start"}
     * eventLogEntry.type: ACTIVITY_STARTED, eventLog.data: {"timeStamp":1594303601272,"activityId":"someTask","processDefinitionId":"my-process:1:3","processInstanceId":"4","executionId":"5","behaviorClass":"org.activiti.engine.impl.bpmn.behavior.UserTaskActivityBehavior","activityName":"Activiti is awesome!","activityType":"userTask"}
     * eventLogEntry.type: TASK_CREATED, eventLog.data: {"timeStamp":1594303601297,"taskDefinitionKey":"someTask","processDefinitionId":"my-process:1:3","processInstanceId":"4","executionId":"5","createTime":1594303601273,"name":"Activiti is awesome!","id":"8","priority":50}
     * eventLogEntry.type: TASK_COMPLETED, eventLog.data: {"duration":194,"timeStamp":1594303601467,"taskDefinitionKey":"someTask","processDefinitionId":"my-process:1:3","processInstanceId":"4","executionId":"5","createTime":1594303601273,"name":"Activiti is awesome!","id":"8","priority":50}
     * eventLogEntry.type: ACTIVITY_COMPLETED, eventLog.data: {"timeStamp":1594303601483,"activityId":"someTask","processDefinitionId":"my-process:1:3","processInstanceId":"4","executionId":"5","behaviorClass":"org.activiti.engine.impl.bpmn.behavior.UserTaskActivityBehavior","activityName":"Activiti is awesome!","activityType":"userTask"}
     * eventLogEntry.type: SEQUENCEFLOW_TAKEN, eventLog.data: {"targetActivityId":"end","timeStamp":1594303601484,"sourceActivityName":"Activiti is awesome!","targetActivityBehaviorClass":"org.activiti.engine.impl.bpmn.behavior.NoneEndEventActivityBehavior","sourceActivityType":"org.activiti.bpmn.model.UserTask","targetActivityName":"结束","id":"flow2","sourceActivityBehaviorClass":"org.activiti.engine.impl.bpmn.behavior.UserTaskActivityBehavior","targetActivityType":"org.activiti.bpmn.model.EndEvent","sourceActivityId":"someTask"}
     * eventLogEntry.type: ACTIVITY_STARTED, eventLog.data: {"timeStamp":1594303601485,"activityId":"end","processDefinitionId":"my-process:1:3","processInstanceId":"4","executionId":"5","behaviorClass":"org.activiti.engine.impl.bpmn.behavior.NoneEndEventActivityBehavior","activityName":"结束","activityType":"endEvent"}
     * eventLogEntry.type: ACTIVITY_COMPLETED, eventLog.data: {"timeStamp":1594303601486,"activityId":"end","processDefinitionId":"my-process:1:3","processInstanceId":"4","executionId":"5","behaviorClass":"org.activiti.engine.impl.bpmn.behavior.NoneEndEventActivityBehavior","activityName":"结束","activityType":"endEvent"}
     * eventLogEntry.type: PROCESSINSTANCE_END, eventLog.data: {"timeStamp":1594303601497,"processDefinitionId":"my-process:1:3","id":"4","endTime":1594303601497}
     * eventLogEntries.size: 12
     */
    @Test
    @Deployment(resources = {"my-process-event-log.bpmn20.xml"})
    public void test() {
        ProcessInstance processInstance = activitiRule.getRuntimeService()
                .startProcessInstanceByKey("my-process");
        Task task = activitiRule.getTaskService().createTaskQuery().singleResult();
        activitiRule.getTaskService().complete(task.getId());

        List<EventLogEntry> eventLogEntries
                = activitiRule.getManagementService()
                .getEventLogEntriesByProcessInstanceId(processInstance.getId());

        for (EventLogEntry eventLogEntry : eventLogEntries) {
            log.info("eventLogEntry.type: {}, eventLog.data: {}",
                    eventLogEntry.getType(),
                    new String(eventLogEntry.getData()));
        }
        log.info("eventLogEntries.size: {}", eventLogEntries.size());
    }
}
