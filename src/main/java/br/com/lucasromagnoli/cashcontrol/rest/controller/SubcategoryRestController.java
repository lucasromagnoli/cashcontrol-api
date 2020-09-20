package br.com.lucasromagnoli.cashcontrol.rest.controller;

import br.com.lucasromagnoli.cashcontrol.rest.commons.MessageTypeEnum;
import br.com.lucasromagnoli.cashcontrol.rest.commons.TemplateMessage;
import br.com.lucasromagnoli.cashcontrol.rest.commons.TemplateMessageSupport;
import br.com.lucasromagnoli.cashcontrol.subcategory.SubcategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subcategory")
public class SubcategoryRestController {
    
    @Autowired
    private SubcategoryService subcategoryService;
    
    @GetMapping
    public ResponseEntity<TemplateMessage> index() {
        return TemplateMessageSupport.begin()
                .messageType(MessageTypeEnum.SUCCESS)
                .message("Listando as subcategorias")
                .payload(subcategoryService.list())
                .build()
                .toResponseEntity();
    }
}
