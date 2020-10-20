package br.com.lucasromagnoli.cashcontrol.admin;

import br.com.lucasromagnoli.cashcontrol.api.MessageTypeEnum;
import br.com.lucasromagnoli.cashcontrol.api.TemplateMessage;
import br.com.lucasromagnoli.cashcontrol.api.TemplateMessageSupport;
import br.com.lucasromagnoli.cashcontrol.bootstrap.CashControlSupport;
import br.com.lucasromagnoli.cashcontrol.category.Category;
import br.com.lucasromagnoli.cashcontrol.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminRestController {

    @Autowired
    private CashControlSupport cashControlSupport;

    @Autowired
    private CategoriesExcelMapper categoriesExcelMapper;

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/excel/categories")
    public ResponseEntity<TemplateMessage> chargeExcelCategoryAndSubcategory(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        List<Category> categories = categoriesExcelMapper.toCategoryList(multipartFile.getInputStream());
        categoryService.saveAll(categories);

        return TemplateMessageSupport.begin()
                .message(cashControlSupport.getPropertie("cashcontrol.messages.operation.save.success.generic"))
                .messageType(MessageTypeEnum.SUCCESS)
                .payload(categories)
                .build()
                .toResponseEntity();
    }
}
