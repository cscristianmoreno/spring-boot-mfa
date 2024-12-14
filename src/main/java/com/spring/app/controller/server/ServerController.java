package com.spring.app.controller.server;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.app.dto.ResponseEntityDTO;
import com.spring.app.utils.ResponseEntityUtil;
import com.spring.app.utils.ServerUtil;

@Controller
@ResponseBody
@RequestMapping("/server")
public class ServerController {
    
    @PostMapping("/turn-off")
    public ResponseEntity<ResponseEntityDTO> serverOff() {
        ServerUtil.off();
        return ResponseEntityUtil.success("Run server off function...");
    }
}
