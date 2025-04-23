package upskill.daterra.services.produto;

import upskill.daterra.entities.Produto;

import java.util.*;

public class ProdutoSamples {

    private static final Map<String, List<Produto>> samples = new HashMap<>();

    static {
        samples.put("Hortícolas", List.of(
                create("Cenoura", "Cenoura Bio", 1.02, "kg", "cenoura.jpg"),
                create("Couve", "Couve portuguesa - Bio", 1.60, "uni", "couve.jpg"),
                create("Batata doce", "Batata doce branca, excelente para assar ou cozer.", 1.60, "kg", "batata.jpg"),
                create("Feijão verde", "Feijão verde tenro e crocante", 2.20, "kg", "feijao-verde.jpg"),
                create("Espinafres", "Espinafres frescos e lavados", 1.90, "molho", "espinafres.jpg"),
                create("Beterraba", "Beterraba com rama", 1.98, "kg", "beterraba.jpg"),
                create("Cebola", "Cebola amarela", 1.10, "kg", "cebola.jpg"),
                create("Brócolos", "Brócolos verdes, frescos e firmes", 2.30, "kg", "brocolos.jpg")
        ));

        samples.put("Frutas", List.of(
                create("Maçã Fuji", "Maçã crocante e doce de calibre médio", 1.55, "kg", "maca.jpg"),
                create("Laranja", "Laranja sumarenta do Algarve", 1.80, "kg", "laranja.jpg"),
                create("Pêra Rocha", "Pêra portuguesa com sabor suave", 1.65, "kg", "pera.jpg"),
                create("Morangos", "Morangos frescos e doces", 2.50, "caixa (500g)", "morangos.jpg"),
                create("Mirtilos", "Mirtilos silvestres ideais para sobremesas", 3.90, "caixa (250g)", "mirtilos.jpg"),
                create("Bananas da Madeira", "Bananas pequenas e doces da ilha da Madeira", 2.20, "kg", "banana.jpg")
        ));

        samples.put("Apícolas", List.of(
                create("Mel de Rosmaninho", "Mel suave com notas florais", 5.00, "frasco (500g)", "mel-rosmaninho.jpg"),
                create("Mel Multifloral", "Mel obtido de várias flores da serra", 4.70, "frasco (500g)", "mel.jpg")
        ));

        samples.put("Vinhos", List.of(
                create("Vinho Tinto Reserva", "Notas de frutos vermelhos e madeira", 9.90, "garrafa (750ml)", "vinho-tinto.jpg"),
                create("Vinho Branco", "Vinho branco seco e refrescante", 7.50, "garrafa (750ml)", "vinho-branco.jpg"),
                create("Rosé da Terra", "Rosé leve, perfeito para o verão", 8.00, "garrafa (750ml)", "vinho-rose.jpg")
        ));

        samples.put("Ervas e Especiarias", List.of(
                create("Manjericão Fresco", "Erva aromática ideal para pratos italianos", 1.00, "molho", "manjericao.jpg"),
                create("Tomilho", "Tomilho fresco colhido na hora", 0.90, "molho", "tomilho.jpg"),
                create("Orégãos", "Orégãos secos para temperos", 1.20, "frasco (50g)", "oregãos.jpg")
        ));

        samples.put("Processados", List.of(
                create("Compota de Morango", "Compota artesanal feita com morangos frescos", 3.50, "frasco (250g)", "compota.jpg"),
                create("Doce de Abóbora", "Doce tradicional com nozes", 3.80, "frasco (250g)", "abobora.jpg"),
                create("Pickles Artesanais", "Mistura de legumes em conserva", 4.00, "frasco (300g)", "pickles.jpg")
        ));
    }

    private static Produto create(String name, String desc, double price, String unit, String imageUrl) {
        Produto produto = new Produto();
        produto.setName(name);
        produto.setDescription(desc);
        produto.setPrice(price);
        produto.setPricingUnit(unit);
        produto.setProductImageUrl(imageUrl);
        return produto;
    }

    public static List<Produto> getSamplesForCategory(String categoryName) {
        return samples.getOrDefault(categoryName, List.of());
    }
}

