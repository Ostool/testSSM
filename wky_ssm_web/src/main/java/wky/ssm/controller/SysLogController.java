package wky.ssm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import wky.ssm.domain.SysLog;
import wky.ssm.service.SysLogService;

import java.util.List;

@Controller
@RequestMapping("/sysLog")
public class SysLogController {
    @Autowired
    private SysLogService sysLogService;
    @RequestMapping("/findAll")
    public ModelAndView showSysLog()throws Exception{
        ModelAndView mv = new ModelAndView();
        List<SysLog> sysLogList = sysLogService.findAllSysLog();
        mv.setViewName("syslog-list");
        mv.addObject("sysLogs",sysLogList);
        return mv;
    }
}
