package qwins.dctest.services;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.NestedQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch._types.query_dsl.RangeQuery;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.indices.CreateIndexRequest;
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qwins.dctest.models.Product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductElasticService {

    @Autowired
    ElasticsearchClient elasticsearchClient;
    //сохранение продукта в индекс
    public void saveProduct(Product product) throws IOException {
        IndexRequest<Product> request = IndexRequest.of(i -> i
                .index("products")
                .id(product.getStringId())
                .document(product)
        );
        elasticsearchClient.index(request);
    }
    //создание индекса с nested полем skus
    public void createNestedIndex() throws IOException {
        CreateIndexRequest request = new CreateIndexRequest.Builder()
                .index("products")
                .mappings(m -> m
                        .properties("date", p -> p.date(t -> t))
                        .properties("id", p -> p.long_(k -> k))
                        .properties("name", p -> p.text(t -> t
                                .fields("keyword", k -> k
                                        .keyword(kf -> kf.ignoreAbove(256))
                                )
                        ))
                        .properties("skus", p -> p.nested(n -> n
                                .properties("id", skuId -> skuId.keyword(k -> k))
                                .properties("price", price -> price.long_(d -> d))
                                .properties("article", article -> article.integer(i -> i))
                                .properties("product", pr -> pr.long_(i -> i))
                                .properties("status", status -> status.text(t -> t
                                        .fields("keyword", k -> k
                                                .keyword(kf -> kf.ignoreAbove(256))
                                        )
                                ))

                        ))
                ).build();
        elasticsearchClient.indices().create(request);
    }
    //поиск
    public List<Product> searchProducts(String searchText) throws IOException {
        Query byName = MatchQuery.of(m -> m
                .field("name")
                .query(searchText)
                .fuzziness("AUTO")
        )._toQuery();

        Query byStatus = NestedQuery.of(n -> n
                .path("skus")  // Указываем путь до вложенного элемента
                .query(q -> q
                        .match(m -> m
                                .field("skus.status")  // Поле дочернего элемента для текстового поиска
                                .query(searchText)  // Используем тот же поисковый запрос
                                .fuzziness("AUTO")  // Поиск с учетом форм слова
                        )
                )
        )._toQuery();

        SearchResponse<Product> response = elasticsearchClient.search(s -> s
                        .index("products")
                        .query(q -> q
                                .bool(b -> b
                                        .must(byName)
                                        .should(byStatus)
                                )
                        ),
                Product.class
        );
        List<Hit<Product>> hits = response.hits().hits();
        List<Product> products = new ArrayList<>();
        for (Hit<Product> hit: hits) {
            Product product = hit.source();
            products.add(product);
        }
        return products;
    }
}
