package br.com.lucasromagnoli.cashcontrol.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping(value = {"/", "/form", "/save"})
    public ModelAndView form(Category category) {
        ModelAndView mv = new ModelAndView("category/form");
        mv.addObject("categoryList", categoryService.list());
        return mv;

    }

    @PostMapping("/save")
    public ModelAndView save(Category category) {
        ModelAndView mv = new ModelAndView("category/form");
        categoryService.save(category);

        mv.addObject("categoryList", categoryService.list());
        return mv;
    }

}
