package com.comitfy.kidefy.app.service;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class ModuleGenerator {

    @Test
    void getService() {
        generateClasses("blog"); //ilk harf kucuk verilmeli
    }

    private void generateClasses(String className) {
        String basePackage = "com.comitfy.kidefy.app"; // Temel paket adi

        generateSpecification(basePackage, className);
        generateService(basePackage, className);
        generateEntity(basePackage, className);
        generateRepository(basePackage, className);
        generateController(basePackage, className);
        generateMapper(basePackage, className);
        generateDTO(basePackage, className);
        generateRequestDTO(basePackage, className);
    }

    public static void generateSpecification(String basePackage, String className) {
        className = StringUtils.capitalize(className);
        String packageName = basePackage + ".specification";

        String specificationImport = "import com.comitfy.kidefy.app.entity." + className + ";\n" +
                "import com.comitfy.kidefy.util.common.BaseSpecification;\n" +
                "import org.springframework.stereotype.Component;\n";

        String repositoryClassName = className + "Specification";
        String repositoryCode = specificationImport + "@Component\n" +
                "public class " + className + "Specification extends BaseSpecification<"
                + className + "> {\n\n}";
        generateClassFile(packageName, repositoryClassName, repositoryCode);
    }

    public static void generateService(String basePackage, String className) {
        String capitalizeClassName = StringUtils.capitalize(className);
        String serviceClassName = capitalizeClassName + "Service";
        String packageName = basePackage + ".service";

        String serviceImport = "import com.comitfy.kidefy.app.dto." + capitalizeClassName + "DTO;\n" +
                "import com.comitfy.kidefy.app.dto.request." + capitalizeClassName + "RequestDTO;\n" +
                "import com.comitfy.kidefy.app.entity." + capitalizeClassName + ";\n" +
                "import com.comitfy.kidefy.app.mapper." + capitalizeClassName + "Mapper;\n" +
                "import com.comitfy.kidefy.app.repository." + capitalizeClassName + "Repository;\n" +
                "import com.comitfy.kidefy.app.specification." + capitalizeClassName + "Specification;\n" +
                "import com.comitfy.kidefy.util.common.BaseService;\n" +
                "import org.springframework.beans.factory.annotation.Autowired;\n" +
                "import org.springframework.stereotype.Service;\n";

        String serviceCode = serviceImport + "@Service\n" +
                "public class " + capitalizeClassName + "Service extends " +
                "BaseService<" + capitalizeClassName + "DTO," +
                " " + capitalizeClassName + "RequestDTO, " + capitalizeClassName + ", " + capitalizeClassName + "Repository, "
                + capitalizeClassName + "Mapper, " + capitalizeClassName + "Specification> {\n" +
                "    @Autowired\n" +
                "    " + capitalizeClassName + "Repository " + className + "Repository;\n" +
                "\n" +
                "    @Autowired\n" +
                "    " + capitalizeClassName + "Mapper " + className + "Mapper;\n" +
                "\n" +
                "    @Autowired\n" +
                "    " + capitalizeClassName + "Specification " + className + "Specification;\n" +
                "\n" +
                "    @Override\n" +
                "    public " + capitalizeClassName + "Repository getRepository() {\n" +
                "        return " + className + "Repository;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public " + capitalizeClassName + "Mapper getMapper() {\n" +
                "        return " + className + "Mapper;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public  " + capitalizeClassName + "Specification getSpecification() {\n" +
                "        return " + className + "Specification;\n" +
                "}\n" +
                "}";
        generateClassFile(packageName, serviceClassName, serviceCode);
    }

    public static void generateEntity(String basePackage, String className) {
        String capitalizeClassName = StringUtils.capitalize(className);
        String packageName = basePackage + ".entity";

        String entityImport = "import com.comitfy.kidefy.util.dbUtil.BaseEntity;\n" +
                "import jakarta.persistence.AttributeOverride;\n" +
                "import jakarta.persistence.Column;\n" +
                "import jakarta.persistence.Entity;\n" +
                "import jakarta.persistence.Table;\n" +
                "import lombok.Getter;\n" +
                "import lombok.Setter;\n";


        String entityCode = entityImport + "@Entity\n" +
                "@Table\n" +
                "@Getter\n" +
                "@Setter\n" +
                "@AttributeOverride(\n" +
                "        name = \"uuid\",\n" +
                "        column = @Column(\n" +
                "                name = \"" + camelCaseToUnderscore(className) + "_uuid\"\n" +
                "        )\n" +
                ")\n" +
                "public class " + capitalizeClassName + " extends BaseEntity {\n\n}";
        generateClassFile(packageName, capitalizeClassName, entityCode);
    }

    public static String camelCaseToUnderscore(final String str) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isUpperCase(c)) {
                if (i != 0) {
                    sb.append("_");
                }// w ww.  java2 s .  c o m
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }


    public static String camelCaseToScore(final String str) {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isUpperCase(c)) {
                if (i != 0) {
                    sb.append("-");
                }
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static void generateRepository(String basePackage, String className) {
        className = StringUtils.capitalize(className);
        String packageName = basePackage + ".repository";

        String repositoryImport = "import com.comitfy.kidefy.app.entity." + className + ";\n" +
                "import com.comitfy.kidefy.util.common.BaseRepository;\n" +
                "import org.springframework.stereotype.Repository;\n";

        String repositoryClassName = className + "Repository";
        String repositoryCode = repositoryImport + "@Repository\npublic interface " + repositoryClassName + " extends BaseRepository<"
                + className + "> {\n\n}";
        generateClassFile(packageName, repositoryClassName, repositoryCode);
    }

    public static void generateController(String basePackage, String className) {
        String originalClassName = className;
        className = StringUtils.capitalize(className);
        String packageName = basePackage + ".controller";

        String controllerClassName = className + "Controller";
        String controllerImport = "import com.comitfy.kidefy.app.dto." + className + "DTO;\n" +
                "import com.comitfy.kidefy.app.dto.request." + className + "RequestDTO;\n" +
                "import com.comitfy.kidefy.app.entity." + className + ";\n" +
                "import com.comitfy.kidefy.app.mapper." + className + "Mapper;\n" +
                "import com.comitfy.kidefy.app.repository." + className + "Repository;\n" +
                "import com.comitfy.kidefy.app.service." + className + "Service;\n" +
                "import com.comitfy.kidefy.app.specification." + className + "Specification;\n" +
                "import com.comitfy.kidefy.util.common.BaseCrudController;\n" +
                "import org.springframework.beans.factory.annotation.Autowired;\n" +
                "import org.springframework.web.bind.annotation.RequestMapping;\n" +
                "import org.springframework.web.bind.annotation.RestController;\n";
        String controllerCode = controllerImport + "@RestController\n"
                + "@RequestMapping(\"" + camelCaseToScore(className.toLowerCase()) + "\")\n"
                + "public class " + controllerClassName + " extends BaseCrudController<"
                + className + "DTO, " + className + "RequestDTO, " + className + ", "
                + className + "Repository, " + className + "Mapper, " + className
                + "Specification, " + className + "Service> {\n" +
                "    @Autowired\n" +
                "    " + className + "Mapper " + originalClassName + "Mapper;\n" +
                "\n" +
                "    @Autowired\n" +
                "    " + className + "Service " + originalClassName + "Service;\n" +
                "\n" +
                "    @Override\n" +
                "    public " + className + "Service getService() {\n" +
                "        return " + originalClassName + "Service;\n" +
                "    }\n" +
                "\n" +
                "    @Override\n" +
                "    public " + className + "Mapper getMapper() {\n" +
                "        return " + originalClassName + "Mapper;\n" +
                "    }\n" +
                "\n}";
        generateClassFile(packageName, controllerClassName, controllerCode);
    }

    public static void generateMapper(String basePackage, String className) {
        className = StringUtils.capitalize(className);
        String packageName = basePackage + ".mapper";

        String mapperImport = "import com.comitfy.kidefy.app.entity." + className + ";\n" +
                "import com.comitfy.kidefy.util.common.BaseMapper;\n" +
                "import org.mapstruct.Mapper;\n" +
                "import com.comitfy.kidefy.app.dto." + className + "DTO;\n" +
                "import com.comitfy.kidefy.app.dto.request." + className + "RequestDTO;\n";

        String mapperClassName = className + "Mapper";
        String mapperCode = mapperImport + "@Mapper(componentModel = \"spring\")\npublic interface "
                + mapperClassName
                + " extends BaseMapper<" + className + "DTO," + className + "RequestDTO, "
                + className + "> {\n\n}";
        generateClassFile(packageName, mapperClassName, mapperCode);
    }

    public static void generateDTO(String basePackage, String className) {
        className = StringUtils.capitalize(className);
        String packageName = basePackage + ".dto";

        String dtoImport = "import com.comitfy.kidefy.util.common.BaseDTO;\n" +
                "import lombok.Data;\n";

        String dtoClassName = className + "DTO";
        String dtoCode = dtoImport + "@Data\npublic class " + dtoClassName + " extends BaseDTO {\n\n}";
        generateClassFile(packageName, dtoClassName, dtoCode);
    }

    public static void generateRequestDTO(String basePackage, String className) {
        className = StringUtils.capitalize(className);
        String packageName = basePackage + ".dto.request";
        String dtoImport = "import com.comitfy.kidefy.util.common.BaseDTO;\n" +
                "import lombok.Data;\n";

        String dtoClassName = className + "RequestDTO";
        String dtoCode = dtoImport + "@Data\npublic class " + dtoClassName + " extends BaseDTO {\n\n}";
        generateClassFile(packageName, dtoClassName, dtoCode);
    }

    public static void generateClassFile(String packageName, String className, String classCode) {
        try {
            File packageDir = new File("src/main/java/" + packageName.replace('.', '/'));
            packageDir.mkdirs(); // Klasoru olustur

            File file = new File(packageDir, className + ".java");
            FileWriter writer = new FileWriter(file);
            writer.write("package " + packageName + ";\n" + classCode);
            writer.close();
            System.out.println(className + " class generated successfully in package " + packageName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
