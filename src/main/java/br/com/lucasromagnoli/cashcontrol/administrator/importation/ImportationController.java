package br.com.lucasromagnoli.cashcontrol.administrator.importation;

import br.com.lucasromagnoli.cashcontrol.category.Category;
import br.com.lucasromagnoli.cashcontrol.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin/importation")
public class ImportationController {
    
    @Autowired
    ImportationCategoriesExcel importationCategoriesExcel;
    
    @Autowired
    CategoryService categoryService;
    
    @GetMapping(value = {"/", "/index"})
    public ModelAndView index() {
        return new ModelAndView("admin/importation/index");
    }
    
    @PostMapping("/categories")
    public ModelAndView loadCategoriesIntoDatabase(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        List<Category> categories = importationCategoriesExcel.parse(multipartFile.getInputStream());
        categoryService.saveAll(categories);
        
        return new ModelAndView("redirect:/admin/importation/index");
    }
}
