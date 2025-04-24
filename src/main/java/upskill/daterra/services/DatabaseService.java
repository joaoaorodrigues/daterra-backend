package upskill.daterra.services;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import upskill.daterra.entities.Admin;
import upskill.daterra.entities.Category;
import upskill.daterra.entities.Produto;
import upskill.daterra.entities.Produtor;
import upskill.daterra.repositories.AdminRepository;
import upskill.daterra.repositories.CategoryRepository;
import upskill.daterra.repositories.ProdutoRepository;
import upskill.daterra.repositories.ProdutorRepository;
import upskill.daterra.services.produto.ProdutoSamples;

import java.time.LocalDate;
import java.util.*;

@Service
public class DatabaseService {

    @Autowired
    private ProdutorRepository produtorRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostConstruct
    @Transactional
    public void initializeData() {
        initializeCategories();
        initializeProdutores();
        initializeProdutos();
        initializeAdmin();
    }

    private void initializeCategories() {
        if (categoryRepository.count() != 0) return;

        List<Category> categories = Arrays.asList(
                new Category("Hortícolas", "#95BC80"),
                new Category("Frutas", "#95BC80"),
                new Category("Apícolas", "#F9D537"),
                new Category("Vinhos", "#D5CAF7"),
                new Category("Ervas e Especiarias", "#95BC80"),
                new Category("Processados", "#F9D537"),
                new Category("Biológico", "#95BC80")
        );

        categoryRepository.saveAll(categories);
    }

    private void initializeProdutores() {
        if (produtorRepository.count() != 0) return;
        
        Category horticolas = categoryRepository.findByName("Hortícolas").orElseThrow();
        Category frutas = categoryRepository.findByName("Frutas").orElseThrow();
        Category vinhos = categoryRepository.findByName("Vinhos").orElseThrow();
        Category apicolas = categoryRepository.findByName("Apícolas").orElseThrow();
        Category ervasEspeciarias = categoryRepository.findByName("Ervas e Especiarias").orElseThrow();
        Category processados = categoryRepository.findByName("Processados").orElseThrow();
        Category biologico = categoryRepository.findByName("Biológico").orElseThrow();

        List<Produtor> produtores = Arrays.asList(
                createProdutor(
                        "Quinta do Pomar",
                        "quintapomar@teste.pt",
                        "123",
                        "Produção de frutas frescas e hortícolas no coração de Aveiro.",
                        "Aveiro",
                        "Aveiro",
                        "Roxico - Fermelã",
                        "3865-115",
                        40.71169,
                        -8.54565,
                        List.of(frutas, horticolas),
                        "BIO-PT-01",
                        true,
                        true
                ),
                createProdutor(
                        "Hortas do Sul",
                        "hortassul@teste.pt",
                        "123",
                        "Hortícolas frescos e biológicos diretamente de Beja.",
                        "Beja",
                        "Beja",
                        "Estrada das Hortas, nº 45",
                        "7800-456",
                        38.0151,
                        -7.8632,
                        List.of(horticolas, biologico),
                        "BIO-PT-02",
                        true,
                        false
                ),
                createProdutor(
                        "Frutas do Minho",
                        "frutasminho@teste.pt",
                        "123",
                        "Variedade de frutas da região de Braga, colhidas no ponto certo.",
                        "Póvoa de Lanhoso",
                        "Braga",
                        "Alameda das Macieiras, nº 78",
                        "4700-789",
                        41.5454,
                        -8.4265,
                        List.of(frutas),
                        null,
                        true,
                        true
                ),
                createProdutor(
                        "Mel da Serra",
                        "melserra@teste.pt",
                        "123",
                        "Apicultura tradicional nas serras de Bragança.",
                        "Bragança",
                        "Bragança",
                        "Rua da Igreja, 12",
                        "5300-421",
                        41.8062,
                        -6.7567,
                        List.of(apicolas),
                        "BIO-PT-03",
                        false,
                        true
                ),
                createProdutor(
                        "Ervas da Beira",
                        "ervasbeira@teste.pt",
                        "123",
                        "Cultivo de ervas aromáticas e especiarias em Castelo Branco.",
                        "Castelo Branco",
                        "Castelo Branco",
                        "Estrada de Santiago, nº 34",
                        "6000-654",
                        39.8222,
                        -7.4931,
                        List.of(ervasEspeciarias),
                        null,
                        true,
                        false
                ),
                createProdutor(
                        "Quinta da Madeira",
                        "quintamadeira@teste.pt",
                        "123",
                        "Produção de frutas tropicais na Região Autónoma da Madeira.",
                        "Funchal",
                        "Região Autónoma da Madeira",
                        "Vereda das Três Marias, nº 56",
                        "9000-789",
                        32.6669,
                        -16.9241,
                        List.of(frutas, horticolas),
                        "BIO-PT-04",
                        true,
                        true
                ),
                createProdutor(
                        "Horta dos Açores",
                        "hortaacores@teste.pt",
                        "123",
                        "Hortícolas frescos cultivados nos solos férteis dos Açores.",
                        "Ponta Delgada",
                        "Região Autónoma dos Açores",
                        "Rua do Pico, nº 89",
                        "9500-123",
                        37.7412,
                        -25.6756,
                        List.of(horticolas),
                        null,
                        true,
                        true
                ),
                createProdutor(
                        "Vinhos do Alentejo",
                        "vinhosalentejo@teste.pt",
                        "123",
                        "Produção de vinhos tradicionais na região de Évora.",
                        "Viana do Alentejo",
                        "Évora",
                        "Estrada dos Vinhedos, nº 101",
                        "7000-456",
                        38.5711,
                        -7.9097,
                        List.of(vinhos),
                        null,
                        false,
                        true
                ),
                createProdutor(
                        "Compotas de Matosinhos",
                        "compotasmatosinhos@teste.pt",
                        "123",
                        "Produção artesanal de compotas e conservas em Porto.",
                        "Matosinhos",
                        "Porto",
                        "Rua das Conservas, nº 67",
                        "4000-789",
                        41.1496,
                        -8.6109,
                        List.of(processados),
                        null,
                        true,
                        false
                ),
                createProdutor(
                        "Frutas do Algarve",
                        "frutasalgarve@teste.pt",
                        "123",
                        "Frutas cítricas frescas colhidas no Algarve.",
                        "Faro",
                        "Faro",
                        "Largo das Laranjeiras, nº 23",
                        "8000-321",
                        37.0194,
                        -7.9304,
                        List.of(frutas),
                        "BIO-PT-05",
                        true,
                        true
                ),
                createProdutor(
                        "Raízes de Sever",
                        "raizesdesever@teste.pt",
                        "123",
                        "Produção local de hortícolas em Sever do Vouga.",
                        "Sever do Vouga",
                        "Aveiro",
                        "Rua do Moinho, nº 18",
                        "3740-256",
                        40.7295,
                        -8.3728,
                        List.of(horticolas, biologico),
                        "BIO-PT-21",
                        true,
                        false
                ),
                createProdutor(
                        "Pomar de Castelo de Paiva",
                        "pomarpaiva@teste.pt",
                        "123",
                        "Frutas frescas cultivadas com dedicação em Castelo de Paiva.",
                        "Castelo de Paiva",
                        "Aveiro",
                        "Rua das Laranjeiras, nº 45",
                        "4550-123",
                        41.0081,
                        -8.2764,
                        List.of(frutas),
                        null,
                        true,
                        true
                ),
                createProdutor(
                        "Vinhas do Vouga",
                        "vinhasvouga@teste.pt",
                        "123",
                        "Vinhos artesanais das encostas de Águeda.",
                        "Águeda",
                        "Aveiro",
                        "Estrada das Vinhas, nº 98",
                        "3750-321",
                        40.5766,
                        -8.4482,
                        List.of(vinhos),
                        null,
                        false,
                        true
                ),
                createProdutor(
                        "Monte do Rosmaninho",
                        "monterosmaninho@teste.pt",
                        "123",
                        "Ervas aromáticas e hortícolas da planície alentejana.",
                        "Serpa",
                        "Beja",
                        "Herdade do Vale, nº 23",
                        "7830-108",
                        37.9392,
                        -7.5969,
                        List.of(horticolas, ervasEspeciarias),
                        "BIO-PT-22",
                        true,
                        true
                ),
                createProdutor(
                        "Quinta da Amendoeira",
                        "amendoeira@teste.pt",
                        "123",
                        "Frutas e produtos biológicos nos arredores de Moura.",
                        "Moura",
                        "Beja",
                        "Caminho das Oliveiras, nº 12",
                        "7860-234",
                        38.1459,
                        -7.4487,
                        List.of(frutas, biologico),
                        null,
                        true,
                        false
                ),
                createProdutor(
                        "Vinhos da Vidigueira",
                        "vinhosvidigueira@teste.pt",
                        "123",
                        "Produção tradicional de vinhos na Vidigueira.",
                        "Vidigueira",
                        "Beja",
                        "Rua dos Lagares, nº 44",
                        "7960-120",
                        38.2116,
                        -7.7952,
                        List.of(vinhos),
                        null,
                        false,
                        true
                ),
                // LISBOA
                createProdutor(
                        "Quinta do Sobralinho",
                        "sobralinho@teste.pt",
                        "123",
                        "Produção familiar de hortícolas frescos e ovos caseiros em Sobral de Monte Agraço.",
                        "Sobral de Monte Agraço",
                        "Lisboa",
                        "Rua do Vale, nº 12",
                        "2590-123",
                        39.0172,
                        -9.1440,
                        List.of(horticolas),
                        "BIO-PT-11",
                        true,
                        true
                ),
                createProdutor(
                        "Hortas de Mafra",
                        "hortasmafra@teste.pt",
                        "123",
                        "Hortícolas e frutas da zona saloia, com produção sustentável e entrega local.",
                        "Mafra",
                        "Lisboa",
                        "Estrada das Hortas, nº 56",
                        "2640-456",
                        38.9422,
                        -9.3270,
                        List.of(horticolas, frutas),
                        null,
                        true,
                        false
                ),
                createProdutor(
                        "Vinhos da Arruda",
                        "vinhosarruda@teste.pt",
                        "123",
                        "Vinhos regionais e azeite, tradição centenária em Arruda dos Vinhos.",
                        "Arruda dos Vinhos",
                        "Lisboa",
                        "Rua da Adega, nº 8",
                        "2630-123",
                        38.9831,
                        -9.0780,
                        List.of(vinhos),
                        null,
                        true,
                        true
                ),

// SANTARÉM
                createProdutor(
                        "Frutas do Cartaxo",
                        "frutascartaxo@teste.pt",
                        "123",
                        "Produção de maçã, pera e uva em pomares tradicionais do Cartaxo.",
                        "Cartaxo",
                        "Santarém",
                        "Travessa dos Pomares, nº 3",
                        "2070-234",
                        39.1605,
                        -8.7872,
                        List.of(frutas),
                        null,
                        true,
                        false
                ),
                createProdutor(
                        "Horta de Tomar",
                        "hortatomar@teste.pt",
                        "123",
                        "Hortícolas frescos junto ao rio Nabão, com entrega semanal em Tomar.",
                        "Tomar",
                        "Santarém",
                        "Rua do Nabão, nº 19",
                        "2300-456",
                        39.6031,
                        -8.4091,
                        List.of(horticolas),
                        "BIO-PT-12",
                        true,
                        true
                ),
                createProdutor(
                        "Vinhos de Almeirim",
                        "vinhosalmeirim@teste.pt",
                        "123",
                        "Vinhos do Tejo, produção familiar em Almeirim.",
                        "Almeirim",
                        "Santarém",
                        "Rua das Vinhas, nº 21",
                        "2080-123",
                        39.2087,
                        -8.6231,
                        List.of(vinhos),
                        null,
                        false,
                        true
                ),

// LEIRIA
                createProdutor(
                        "Chícharos de Alvaiázere",
                        "chicharosalvaiazere@teste.pt",
                        "123",
                        "Produção tradicional de chícharos e hortícolas em Alvaiázere.",
                        "Alvaiázere",
                        "Leiria",
                        "Rua do Chícharo, nº 7",
                        "3250-123",
                        39.8220,
                        -8.3780,
                        List.of(horticolas),
                        "BIO-PT-13",
                        true,
                        false
                ),
                createProdutor(
                        "Frutaria de Alcobaça",
                        "frutariaalcobaca@teste.pt",
                        "123",
                        "Maçã de Alcobaça e pêra rocha, produção certificada em Alcobaça.",
                        "Alcobaça",
                        "Leiria",
                        "Rua dos Pomares, nº 13",
                        "2460-321",
                        39.5485,
                        -8.9777,
                        List.of(frutas),
                        null,
                        true,
                        true
                ),
                createProdutor(
                        "Vinhas do Bombarral",
                        "vinhasbombarral@teste.pt",
                        "123",
                        "Vinhos DOC Óbidos e uvas de mesa, tradição familiar no Bombarral.",
                        "Bombarral",
                        "Leiria",
                        "Estrada das Vinhas, nº 2",
                        "2540-567",
                        39.2670,
                        -9.1580,
                        List.of(vinhos, frutas),
                        null,
                        true,
                        true
                ),

// FARO
                createProdutor(
                        "Citrinos de Silves",
                        "citrinossilves@teste.pt",
                        "123",
                        "Laranjas e limões do barrocal algarvio, produção sustentável em Silves.",
                        "Silves",
                        "Faro",
                        "Rua dos Citrinos, nº 10",
                        "8300-123",
                        37.1881,
                        -8.4386,
                        List.of(frutas),
                        "BIO-PT-14",
                        true,
                        false
                ),
                createProdutor(
                        "Horta de São Brás",
                        "hortasaobras@teste.pt",
                        "123",
                        "Hortícolas frescos e ervas aromáticas em São Brás de Alportel.",
                        "São Brás de Alportel",
                        "Faro",
                        "Caminho das Hortas, nº 5",
                        "8150-234",
                        37.1531,
                        -7.8851,
                        List.of(horticolas, ervasEspeciarias),
                        null,
                        true,
                        true
                ),
                createProdutor(
                        "Vinhos de Lagoa",
                        "vinhoslagoa@teste.pt",
                        "123",
                        "Vinhos do Algarve e figos secos, tradição familiar em Lagoa.",
                        "Lagoa",
                        "Faro",
                        "Rua da Adega, nº 18",
                        "8400-321",
                        37.1366,
                        -8.4536,
                        List.of(vinhos, frutas),
                        null,
                        false,
                        true
                ),

// VISEU
                createProdutor(
                        "Quinta da Beira",
                        "quintabeira@teste.pt",
                        "123",
                        "Frutas e hortícolas do interior beirão, produção biológica.",
                        "Sátão",
                        "Viseu",
                        "Rua da Quinta, nº 11",
                        "3560-123",
                        40.7392,
                        -7.7280,
                        List.of(frutas, horticolas),
                        "BIO-PT-15",
                        true,
                        false
                ),
                createProdutor(
                        "Vinhos de Mangualde",
                        "vinhosmangualde@teste.pt",
                        "123",
                        "Vinhos do Dão, tradição familiar em Mangualde.",
                        "Mangualde",
                        "Viseu",
                        "Rua das Vinhas, nº 14",
                        "3530-234",
                        40.6042,
                        -7.7634,
                        List.of(vinhos),
                        null,
                        true,
                        true
                ),
                createProdutor(
                        "Horta de Lamego",
                        "hortalamego@teste.pt",
                        "123",
                        "Hortícolas frescos, produção local em Lamego.",
                        "Lamego",
                        "Viseu",
                        "Travessa das Hortas, nº 8",
                        "5100-321",
                        41.0970,
                        -7.8092,
                        List.of(horticolas),
                        null,
                        false,
                        true
                )

        );

        produtorRepository.saveAll(produtores);
    }

    private Produtor createProdutor(
            String businessName,
            String email,
            String password,
            String description,
            String city,
            String region,
            String address,
            String postalCode,
            double latitude,
            double longitude,
            List<Category> categories,
            String organicCertificate,
            boolean hasDeliveryOption,
            boolean hasPickupOption
    ) {
        Produtor produtor = new Produtor();
        produtor.setBusinessName(businessName);
        produtor.setEmail(email);
        produtor.setPassword(passwordEncoder.encode(password));
        produtor.setDescription(description);
        produtor.setAddress(address);
        produtor.setCity(city);
        produtor.setRegion(region);
        produtor.setAddress(address);
        produtor.setPostalCode(postalCode);
        produtor.setLatitude(latitude);
        produtor.setLongitude(longitude);
        produtor.setCategories(categories);
        produtor.setOrganicCertificate(organicCertificate);
        produtor.setHasDeliveryOption(hasDeliveryOption);
        produtor.setHasPickupOption(hasPickupOption);

        produtor.setFirstName("José Maria");
        produtor.setLastName("da Terra");
        produtor.setPhone("+351 912 123 123");
        produtor.setCountry("Portugal");
        produtor.setNif("123456789");
        produtor.setBirthDate(LocalDate.of(1974, 4, 25));
        produtor.setProfileImageUrl("profile-default.svg");
        produtor.setCoverImageUrl("cover-default.svg");
        produtor.setApproved(true);

        return produtor;
    }


    private void initializeProdutos() {
        if (produtoRepository.count() != 0) return;

        List<Produtor> approvedProdutores = produtorRepository.findApprovedWithCategories();
        List<Produto> produtos = new ArrayList<>();

        for (Produtor produtor : approvedProdutores) {
            Set<Produto> produtosGerados = new HashSet<>();

            for (Category categoria : produtor.getCategories()) {
                List<Produto> listaCategoria = ProdutoSamples.getSamplesForCategory(categoria.getName());

                if (categoria.getName().equals("Frutas") &&
                        !(produtor.getRegion().contains("Madeira") || produtor.getRegion().contains("Açores"))) {
                    listaCategoria = listaCategoria.stream()
                            .filter(p -> !p.getName().toLowerCase().contains("banana"))
                            .toList();
                }


                listaCategoria = new ArrayList<>(listaCategoria);
                Collections.shuffle(listaCategoria);
                for (Produto sample : listaCategoria) {
                    if (produtosGerados.size() >= 6) break;
                    Produto produto = new Produto();
                    produto.setName(sample.getName());
                    produto.setDescription(sample.getDescription());
                    produto.setPrice(sample.getPrice());
                    produto.setPricingUnit(sample.getPricingUnit());
                    produto.setQuantity((int) (Math.random() * 30 + 5));
                    produto.setProductImageUrl(sample.getProductImageUrl());
                    produto.setCategories(List.of(categoria));
                    produto.setProdutor(produtor);
                    produtosGerados.add(produto);
                }

                if (produtosGerados.size() >= 6) break;
            }

            produtos.addAll(produtosGerados);
        }

        produtoRepository.saveAll(produtos);
    }



    private void initializeAdmin() {

        if (adminRepository.count() != 0) return;

        Admin admin = new Admin();
        admin.setEmail("admin@test.com");
        admin.setPassword(passwordEncoder.encode("admin123"));
        admin.setFirstName("Admin");
        admin.setLastName("daTerra");
        adminRepository.save(admin);

        }



}
