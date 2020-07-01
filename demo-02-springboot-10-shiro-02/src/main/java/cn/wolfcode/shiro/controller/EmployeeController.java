package cn.wolfcode.shiro.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wolfcode-lanxw
 */
@Controller
public class EmployeeController {
    @RequestMapping("/employee")
    @RequiresPermissions("employee:page")
    public String employeePage(){
        return "employee/list";
    }
}
