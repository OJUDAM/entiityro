package com.ujo.entityro.converting.controller;

import com.ujo.entityro.converting.dto.request.PlainCreateTable;
import com.ujo.entityro.converting.service.ConvertEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class ConvertEntityController {

    private final ConvertEntityService convertEntityService;

    @GetMapping("/convert")
    @ResponseBody
    public String getEntity(PlainCreateTable plainCreateTable) {
        return convertEntityService.convertDdlToEntity(plainCreateTable.getCreateScript());
    }
}
