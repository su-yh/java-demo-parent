package com.imooc.activiti.helloworld.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.impl.agenda.AbstractOperation;
import org.activiti.engine.impl.interceptor.DebugCommandInvoker;
import org.activiti.engine.logging.LogMDC;

@Slf4j
public class MDCCommandInvoker extends DebugCommandInvoker {

    public void executeOperation(Runnable runnable) {
        boolean mdcEnabled = LogMDC.isMDCEnabled();
        LogMDC.setMDCEnabled(true);
        if (runnable instanceof AbstractOperation) {
            AbstractOperation operation = (AbstractOperation)runnable;
            if (operation.getExecution() != null) {
                log.info("info: {}", operation.getExecution());
                LogMDC.putMDCExecution(operation.getExecution());
            }
        }

        super.executeOperation(runnable);
        LogMDC.clear();
        LogMDC.setMDCEnabled(mdcEnabled);
    }
}
