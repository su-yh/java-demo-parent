package com.suyh.es3202.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@ApiModel(value = "ProductEntity", description = "ProductEntity 表单实体")
public class ProductEntity implements Serializable {
    static final long serialVersionUID = 42L;

    @Field(name = "tax_amount", type = FieldType.Half_Float)
    private Double taxAmount;

    @Field(name = "taxful_price", type = FieldType.Half_Float)
    private Double taxfulPrice;

    @Field(type = FieldType.Integer)
    private Integer quantity;

    @Field(name = "taxless_price", type = FieldType.Half_Float)
    private Double taxlessPrice;

    @Field(name = "discount_amount", type = FieldType.Half_Float)
    private Double discountAmount;

    @Field(name = "base_unit_price", type = FieldType.Half_Float)
    private Double baseUnitPrice;

    @Field(name = "discount_percentage", type = FieldType.Half_Float)
    private Double discountPercentage;

    @Field(name = "product_name", type = FieldType.Text, analyzer = "english")
    private String productName;

    @Field(type = FieldType.Text)
    private String manufacturer;

    @Field(name = "min_price", type = FieldType.Half_Float)
    private Double minPrice;

    @Field(name = "created_on", type = FieldType.Date)
    private Date createdOn;

    @Field(type = FieldType.Half_Float)
    private Double price;

    @Field(name = "unit_discount_amount", type = FieldType.Half_Float)
    private Double unitDiscountAmount;

    @Field(name = "product_id", type = FieldType.Long)
    private Long productId;

    @Field(name = "base_price", type = FieldType.Half_Float)
    private Double basePrice;

    @Field(type = FieldType.Keyword)
    private String id;

    @Field(type = FieldType.Text)
    private String category;

    @Field(type = FieldType.Keyword)
    private String sku;
}
