package com.suyh.es3202.entity;


import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Document(indexName = "kibana_sample_data_ecommerce", type = "_doc", shards = 1, replicas = 0)
//@Document(indexName = "kibana_sample_data_ecommerce_suyh", type = "_doc", shards = 1, replicas = 0)
@ApiModel(value = "KibanaSampleDataEcommerceEntity", description = "KibanaSampleDataEcommerceEntity 实体")
public class KibanaEcommerceEntity implements Serializable {
    static final long serialVersionUID = 42L;

    @Id
    private String id;

    @Field(name = "geoip", type = FieldType.Object)
    private GeoipEntity geoip;

    // TODO: 有text + keyword 的吗？
    @Field(name = "customer_first_name", type = FieldType.Text)
    private String customerFirstName;

    @Field(name = "customer_phone", type = FieldType.Keyword)
    private String customerPhone;

    @Field(type = FieldType.Keyword)
    private String type;

    @Field(type = FieldType.Text)
    private List<String> manufacturer;

    @Field(type = FieldType.Object)
    private List<ProductEntity> products;

    @Field(name = "customer_birth_date", type = FieldType.Date)
    private Date customerBirthDate;

    @Field(name = "customer_full_name", type = FieldType.Text)
    private String customerFullName;

    @Field(name = "order_date", type = FieldType.Date)
    private Date orderDate;

    @Field(name = "customer_last_name", type = FieldType.Text)
    private String customerLastName;

    @Field(name = "day_of_week_i", type = FieldType.Integer)
    private Integer dayOfWeekI;

    @Field(name = "total_quantity", type = FieldType.Integer)
    private Integer totalQuantity;

    @Field(type = FieldType.Keyword)
    private String currency;

    @Field(name = "taxless_total_price", type = FieldType.Double)
    private Double taxlessTotalPrice;

    @Field(name = "total_unique_products", type = FieldType.Integer)
    private Integer totalUniqueProducts;

    @Field(type = FieldType.Text)
    private List<String> category;

    @Field(name = "customer_id", type = FieldType.Keyword)
    private String customerId;

    @Field(type = FieldType.Keyword)
    private List<String> sku;

    @Field(name = "order_id", type = FieldType.Keyword)
    private String orderId;

    @Field(type = FieldType.Keyword)
    private String user;

    @Field(name = "customer_gender", type = FieldType.Keyword)
    private String customerGender;

    @Field(type = FieldType.Keyword)
    private String email;

    @Field(name = "day_of_week", type = FieldType.Keyword)
    private String dayOfWeek;

    @Field(name = "taxful_total_price", type = FieldType.Double)
    private Double taxfulTotalPrice;
}
