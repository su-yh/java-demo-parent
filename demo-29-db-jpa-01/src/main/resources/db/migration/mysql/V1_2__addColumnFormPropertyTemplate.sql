
ALTER TABLE form_property_template ADD COLUMN create_by VARCHAR(64) NULL COMMENT '创建者';
ALTER TABLE form_property_template ADD COLUMN create_time TIMESTAMP NOT NULL default CURRENT_TIMESTAMP COMMENT '创建时间';

ALTER TABLE form_property_template ADD COLUMN update_by VARCHAR(64) NULL COMMENT '修改者';
ALTER TABLE form_property_template ADD COLUMN update_time TIMESTAMP NOT NULL default CURRENT_TIMESTAMP COMMENT '修改时间';

ALTER TABLE form_property_template ADD COLUMN parent_id int NULL COMMENT '父ID';

