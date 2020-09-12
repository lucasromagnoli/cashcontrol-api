package br.com.lucasromagnoli.cashcontrol.administrator.importation;

import br.com.lucasromagnoli.cashcontrol.bootstrap.CashControlSupport;
import br.com.lucasromagnoli.cashcontrol.category.Category;
import br.com.lucasromagnoli.cashcontrol.category.CategoryType;
import br.com.lucasromagnoli.cashcontrol.subcategory.Subcategory;
import org.apache.logging.log4j.util.Strings;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;


import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Component
public class ImportationCategoriesExcel {
    
    @Autowired
    private CashControlSupport cashControlSupport;
    
    private final Logger logger = LoggerFactory.getLogger(ImportationCategoriesExcel.class);
    
    public List<Category> parse(InputStream inputStream) throws IOException {
        final String DESPESAS_WORKSHEET_NAME = cashControlSupport
                .getPropertie("admin.configuration.importation.categories.expenses.worksheet.name");
        final String RECEITAS_WORKSHEET_NAME = cashControlSupport
                .getPropertie("admin.configuration.importation.categories.income.worksheet.name");
        final String NAME_DESCRIPTION_SEPARATOR = cashControlSupport
                .getPropertie("admin.configuration.importation.categories.name.description.separator");

        Workbook workbook = WorkbookFactory.create(inputStream);
        DataFormatter dataFormatter = new DataFormatter();
        Map<String, Category> categories = new LinkedHashMap<>();

        for (Sheet sheet : workbook) {
            final String worksheetName = sheet.getSheetName();
            logger.info(String.format("Lendo a planilha %s.", worksheetName));

            if (worksheetName.equalsIgnoreCase(DESPESAS_WORKSHEET_NAME)
                    || worksheetName.equalsIgnoreCase(RECEITAS_WORKSHEET_NAME)) {

                for (Row row : sheet) {
                    for (Cell cell : row) {
                        String cellValue = dataFormatter.formatCellValue(cell);
                        String[] nameAndDescription = StringUtils.split(cellValue, NAME_DESCRIPTION_SEPARATOR);
                        String nodeName = cellValue, nodeDescription = null;
                        Category target = categories.get(worksheetName + cell.getColumnIndex());

                        if (Strings.isEmpty(nodeName) && nameAndDescription == null) {
                            continue;
                        }

                        if (nameAndDescription != null && nameAndDescription.length == 2) {
                            nodeName = nameAndDescription[0].trim();
                            nodeDescription = nameAndDescription[1].trim();
                        }

                        if (row.getRowNum() == 0) {
                            Category category = new Category();
                            category.setName(nodeName);
                            category.setDescription(nodeDescription);
                            category.setSubcategoryList(new LinkedList<>());
                            category.setType(worksheetName.equalsIgnoreCase(DESPESAS_WORKSHEET_NAME)
                                    ? CategoryType.D : CategoryType.R);
                            categories.put(worksheetName + cell.getColumnIndex(), category);
                            logger.info(String.format("Inserindo a categoria %s", nodeName));
                            continue;
                        }

                        Subcategory subcategory = new Subcategory();
                        subcategory.setName(nodeName);
                        subcategory.setDescription(nodeDescription);
                        subcategory.setCategory(target);

                        target.getSubcategoryList().add(subcategory);
                        logger.info(String.format("Inserindo a subcategoria %s da categoria %s", nodeName, target.getName()));
                    }
                }
            }
        }
        
        return new ArrayList<>(categories.values());
    }
}
