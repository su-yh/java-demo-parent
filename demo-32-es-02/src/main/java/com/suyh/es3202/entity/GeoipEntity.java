package com.suyh.es3202.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.elasticsearch.common.geo.GeoPoint;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

@Data
@ApiModel(value = "GeoipEntity", description = "GeoipEntity 实体")
public class GeoipEntity implements Serializable {
    static final long serialVersionUID = 42L;

    @Field(name = "continent_name", type = FieldType.Keyword)
    private String continentName;

    @Field(name = "city_name", type = FieldType.Keyword)
    private String cityName;

    @Field(name = "country_ios_code", type = FieldType.Keyword)
    private String countryIosCode;

    // TODO: type: geo_point
//    @Field(type = FieldType.Object)
//    private GeoPoint location;

    @Field(name = "region_name", type = FieldType.Keyword)
    private String regionName;
}
