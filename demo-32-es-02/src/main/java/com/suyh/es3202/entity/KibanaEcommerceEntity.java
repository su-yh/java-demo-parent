package com.suyh.es3202.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
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
@ApiModel(value = "KibanaSampleDataEcommerceEntity", description = "KibanaSampleDataEcommerceEntity 实体")
public class KibanaEcommerceEntity implements Serializable {
    static final long serialVersionUID = 42L;

    @Id
    private String id;

    @Field(name = "geoip", type = FieldType.Object)
    private GeoipEntity geoip;

    // 使用Field 注解的时候最好不要使用别名，而是使用变量名，或者直接用Json 序列化与反序列化的别名
    // 如果使用Field 中的name 来定义es 中的字段别名会出现很难处理的序列化与反序列化的问题。
    // 这个我搞了好久没有搞定需要感觉应该是可以处理的，但是处理起来却会非常复杂和麻烦。
    @Field(name = "customer_first_name", type = FieldType.Text)
    @JsonProperty("customer_first_name")
    private String customerFirstName;

    @Field(name = "customer_phone", type = FieldType.Keyword)
    @JsonProperty("customer_phone")
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
    @JsonProperty("customer_full_name")
    private String customerFullName;

    @Field(name = "order_date", type = FieldType.Date)
    private Date orderDate;

    @Field(name = "customer_last_name", type = FieldType.Text)
    @JsonProperty("customer_last_name")
    private String customerLastName;

    @Field(name = "day_of_week_i", type = FieldType.Integer)
    @JsonProperty("day_of_week_i")
    private Integer dayOfWeekI;

    @Field(name = "total_quantity", type = FieldType.Integer)
    @JsonProperty("total_quantity")
    private Integer totalQuantity;

    @Field(type = FieldType.Keyword)
    private String currency;

    @Field(name = "taxless_total_price", type = FieldType.Half_Float)
    @JsonProperty("taxless_total_price")
    private Double taxlessTotalPrice;

    @Field(name = "total_unique_products", type = FieldType.Integer)
    @JsonProperty("total_unique_products")
    private Integer totalUniqueProducts;

    @Field(type = FieldType.Text)
    private List<String> category;

    @Field(name = "customer_id", type = FieldType.Keyword)
    @JsonProperty("customer_id")
    private String customerId;

    @Field(type = FieldType.Keyword)
    private List<String> sku;

    @Field(name = "order_id", type = FieldType.Keyword)
    @JsonProperty("order_id")
    private String orderId;

    @Field(type = FieldType.Keyword)
    private String user;

    @Field(name = "customer_gender", type = FieldType.Keyword)
    @JsonProperty("customer_gender")
    private String customerGender;

    @Field(type = FieldType.Keyword)
    private String email;

    @Field(name = "day_of_week", type = FieldType.Keyword)
    @JsonProperty("day_of_week")
    private String dayOfWeek;

    @Field(name = "taxful_total_price", type = FieldType.Half_Float)
    @JsonProperty("taxful_total_price")
    private Double taxfulTotalPrice;
}
