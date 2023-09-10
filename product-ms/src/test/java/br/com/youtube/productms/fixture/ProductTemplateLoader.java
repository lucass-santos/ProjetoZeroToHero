package br.com.youtube.productms.fixture;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import br.com.youtube.productms.dto.ProductDTO;

public class ProductTemplateLoader implements TemplateLoader {

    @Override
    public void load() {
        Fixture.of(ProductDTO.class).addTemplate("valid", new Rule(){{
            add("name", random("Shampoo", "Shampoo low poo", "shampoo e condicionador"));
            add("description", "Sabonete líquido para uso nos cabelos que permite a higienização capilar retirando oleosidade, celulas mortas e renovando o fio.");
            add("price", random(Double.class, range(10.01, 21.99)));
        }});
    }
}
