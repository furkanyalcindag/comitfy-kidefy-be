package com.comitfy.kidefy.component;

import com.comitfy.kidefy.app.entity.Authorizations;
import com.comitfy.kidefy.app.entity.Module;
import com.comitfy.kidefy.app.repository.AuthorizationsRepository;
import com.comitfy.kidefy.app.repository.ModuleRepository;
import com.comitfy.kidefy.userModule.entity.Role;
import com.comitfy.kidefy.userModule.entity.User;
import com.comitfy.kidefy.userModule.repository.RoleRepository;
import com.comitfy.kidefy.userModule.repository.UserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Component
public class InitializeDatabase implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;



    @Autowired
    ModuleRepository moduleRepository;
    @Autowired
    private AuthorizationsRepository authorizationsRepository;

    @Override
    public void run(String... args) throws Exception {
        loadModuleData();
        loadAuthData();
        loadRoleData();
        loadUserData();
        checkAndAddDefaultValues();

    }


    private void loadRoleData() {

        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Role>> typeReference = new TypeReference<List<Role>>() {
        };
        InputStream inputStream = TypeReference.class.getResourceAsStream("/db/roles.json");
        try {
            List<Role> roleList = mapper.readValue(inputStream, typeReference);

            for (Role role : roleList) {


                try {
                    roleRepository.save(role);
                    System.out.println("role Saved!");
                } catch (Exception e) {
                    System.out.println("duplicated role: " + e.getMessage());
                }


            }


        } catch (IOException e) {
            System.out.println("duplicated role: " + e.getMessage());
        }

    }



    private void loadUserData() {

        if (userRepository.count() == 0) {
            User user = new User();
            user.setEmail("admin@demo.com");
            user.setUsername("admin@demo.com");
            user.setPassword(passwordEncoder.encode("Comitfy2022."));
            Set<Role> roles = new HashSet<>();
            roles.add(roleRepository.findByName("ADMIN").get());
            user.setRoles(roles);


            userRepository.save(user);

        }
        System.out.println(userRepository.count());
    }

    private void loadModuleData() {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Module>> typeReference = new TypeReference<List<Module>>() {};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/db/modules.json");

        try {
            List<Module> moduleList = mapper.readValue(inputStream, typeReference);

            for (Module module : moduleList) {
                try {
                    module.setUuid(UUID.randomUUID());

                    Optional<Module> existingModule = moduleRepository.findById(module.getId());
                    if (existingModule.isPresent()) {
                        Module dbModule = existingModule.get();

                        if (dbModule.getManualId() == null) {
                            dbModule.setManualId(module.getManualId());
                            moduleRepository.save(dbModule);
                            System.out.println("Updated manualId for module: " + dbModule.getId());
                        }
                    } else {
                        moduleRepository.save(module);
                        System.out.println("New module saved!");
                    }
                } catch (Exception e) {
                    System.out.println("Error saving module: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading JSON: " + e.getMessage());
        }
    }

    private void loadAuthData() {
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<Authorizations>> typeReference = new TypeReference<List<Authorizations>>() {
            };
            InputStream inputStream = TypeReference.class.getResourceAsStream("/db/permission.json");
            try {
                List<Authorizations> authList = mapper.readValue(inputStream, typeReference);

                for (Authorizations auth : authList) {


                    try {
                        authorizationsRepository.save(auth);
                        System.out.println("auth Saved!");
                    } catch (Exception e) {
                        System.out.println("duplicated auth: " + e.getMessage());
                    }


                }


            } catch (IOException e) {
                System.out.println("duplicated auth: " + e.getMessage());
            }


    }

    private void checkAndAddDefaultValues() {
        Optional<Role> adminRoleOptional = roleRepository.findById(1L);
        if (adminRoleOptional.isPresent()) {
            Role adminRole = adminRoleOptional.get();

            if (adminRole.getModuleIds() == null || adminRole.getModuleIds().isEmpty()) {
                adminRole.setModuleIds(List.of(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L, 14L, 15L, 16L, 17L, 18L, 19L, 20L, 21L, 22L)); // Default moduleIds ekle
            }

            if (adminRole.getAuthorizationsIds() == null || adminRole.getAuthorizationsIds().isEmpty()) {
                adminRole.setAuthorizationsIds(List.of(1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 13L, 14L, 15L, 16L, 17L)); // Default authorizationsIds ekle
            }

            roleRepository.save(adminRole);
            System.out.println("Admin role updated with default values.");
        } else {
            System.out.println("Admin role not found. Please ensure it exists with ID 1.");
        }
    }




/*
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    CurrencyRepository currencyRepository;

    @Autowired
    CityRepository cityRepository;

    @Autowired
    DistrictRepository districtRepository;

    @Autowired
    SettingsRepository settingsRepository;

    @Autowired
    BoardColumnRepository boardColumnRepository;

    @Autowired
    BranchRepository branchRepository;
    @Autowired
    private ProductYedsanRepository productYedsanRepository;


    @Override
    public void run(String... args) throws Exception {
        loadYedsan();
        loadRoleData();
        loadUserData();
        loadCurrency();
        loadCity();
        loadDistrict();
        loadSettings();
        loadBoardColumn();
        loadInitialBranch();
    }

    private void loadCity() throws FileNotFoundException {


        if (cityRepository.count() > 0) {

        } else {
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<City>> typeReference = new TypeReference<List<City>>() {
            };
            InputStream inputStream = TypeReference.class.getResourceAsStream("/db/cities.json");
            try {
                List<City> cityList = mapper.readValue(inputStream, typeReference);

                for (City city : cityList) {

                    cityRepository.save(city);

                }

                System.out.println("city Saved!");
            } catch (IOException e) {
                System.out.println("Unable to save city: " + e.getMessage());
            }
        }


    }


    private void loadDistrict() throws FileNotFoundException {

        if (districtRepository.count() > 0) {

        } else {
            ObjectMapper mapper = new ObjectMapper();
            TypeReference<List<District>> typeReference = new TypeReference<List<District>>() {
            };
            InputStream inputStream = TypeReference.class.getResourceAsStream("/db/districts.json");
            try {
                List<District> cityList = mapper.readValue(inputStream, typeReference);

                for (District city : cityList) {

                    districtRepository.save(city);

                }

                System.out.println("district Saved!");
            } catch (IOException e) {
                System.out.println("Unable to save city: " + e.getMessage());
            }
        }

    }


    private void loadRoleData() {

        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Role>> typeReference = new TypeReference<List<Role>>() {
        };
        InputStream inputStream = TypeReference.class.getResourceAsStream("/db/roles.json");
        try {
            List<Role> roleList = mapper.readValue(inputStream, typeReference);

            for (Role role : roleList) {


                try {
                    roleRepository.save(role);
                    System.out.println("role Saved!");
                } catch (Exception e) {
                    System.out.println("duplicated role: " + e.getMessage());
                }


            }


        } catch (IOException e) {
            System.out.println("duplicated role: " + e.getMessage());
        }

    }

    private void loadCurrency() {
        if (currencyRepository.count() == 0) {
            Currency currency = new Currency();
            currency.setIsDefault(Boolean.TRUE);
            currency.setName("TL");
            currency.setCode("TRY");
            currency.setSymbol("₺");
            currency.setExchangeRate(BigDecimal.ONE);
            currencyRepository.save(currency);
        }


    }


    private void loadUserData() {

        if (userRepository.count() == 0) {
            User user = new User();
            user.setEmail("admin");
            user.setUsername("admin");
            user.setPassword(passwordEncoder.encode("Comitfy2022."));
            Set<Role> roles = new HashSet<>();
            roles.add(roleRepository.findByName("ADMIN").get());
            user.setRoles(roles);


            userRepository.save(user);

        }
        System.out.println(userRepository.count());
    }


    private void loadSettings() throws FileNotFoundException {


        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Settings>> typeReference = new TypeReference<List<Settings>>() {
        };
        InputStream inputStream = TypeReference.class.getResourceAsStream("/db/settings.json");
        try {
            List<Settings> settingsList = mapper.readValue(inputStream, typeReference);

            for (Settings settings : settingsList) {
                settings.setCurrent(Boolean.TRUE);

                try {
                    settingsRepository.save(settings);
                    System.out.println("settings Saved!");
                } catch (Exception e) {
                    System.out.println("duplicated setting: " + e.getMessage());
                }


            }


        } catch (IOException e) {
            System.out.println("duplicated setting: " + e.getMessage());
        }


    }


    private void loadBoardColumn() throws FileNotFoundException {


        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<BoardColumn>> typeReference = new TypeReference<List<BoardColumn>>() {
        };
        InputStream inputStream = TypeReference.class.getResourceAsStream("/db/boardColumns.json");
        try {
            List<BoardColumn> boardColumnList = mapper.readValue(inputStream, typeReference);

            for (BoardColumn boardColumn : boardColumnList) {


                try {
                    boardColumnRepository.save(boardColumn);
                    System.out.println("boardColumn Saved!");
                } catch (Exception e) {
                    System.out.println("duplicated boardColumn: " + e.getMessage());
                }


            }


        } catch (IOException e) {
            System.out.println("duplicated boardColumn: " + e.getMessage());
        }


    }


    private void loadInitialBranch() {


        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Branch>> typeReference = new TypeReference<List<Branch>>() {
        };
        InputStream inputStream = TypeReference.class.getResourceAsStream("/db/branch.json");
        try {
            List<Branch> branchList = mapper.readValue(inputStream, typeReference);

            for (Branch branch : branchList) {


                try {
                    branchRepository.save(branch);
                    System.out.println("branch Saved!");
                } catch (Exception e) {
                    System.out.println("duplicated branch: " + e.getMessage());
                }


            }


        } catch (IOException e) {
            System.out.println("duplicated branch: " + e.getMessage());
        }
    }


    public void loadYedsan() {
        try {
            if (productYedsanRepository.count() == 0) {


                ClassLoader classLoader = getClass().getClassLoader();
                File file = new File(classLoader.getResource("db/urunler.xml").getFile());

                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//an instance of builder to parse the specified xml file
                DocumentBuilder db = dbf.newDocumentBuilder();
                Document doc = db.parse(file);
                doc.getDocumentElement().normalize();
                System.out.println("Root element: " + doc.getDocumentElement().getNodeName());
                NodeList nodeList = doc.getElementsByTagName("product");
// nodeList is not iterable, so we are using for loop
                for (int itr = 0; itr < nodeList.getLength(); itr++) {
                    Node node = nodeList.item(itr);
                    System.out.println("\nNode Name :" + node.getNodeName());
                    if (node.getNodeType() == Node.ELEMENT_NODE) {
                        Element eElement = (Element) node;
                        System.out.println("Student id: " + eElement.getElementsByTagName("name").item(0).getTextContent());
                        System.out.println("First Name: " + eElement.getElementsByTagName("description").item(0).getTextContent());

                        ProductYedsan product = new ProductYedsan();
                        product.setProductId(eElement.getElementsByTagName("id").item(0).getTextContent());
                        product.setBarcode(eElement.getElementsByTagName("barcode").item(0).getTextContent());
                        product.setProductCode(eElement.getElementsByTagName("productCode").item(0).getTextContent());
                        product.setBrand(eElement.getElementsByTagName("brand").item(0).getTextContent());
                        product.setDescription(eElement.getElementsByTagName("description").item(0).getTextContent());
                        product.setName(eElement.getElementsByTagName("name").item(0).getTextContent());
                        try{
                            product.setImage1(eElement.getElementsByTagName("image1").item(0).getTextContent());

                        }
                        catch (Exception e){
                            System.out.println("image null");
                        }
                        productYedsanRepository.save(product);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
*/

}
