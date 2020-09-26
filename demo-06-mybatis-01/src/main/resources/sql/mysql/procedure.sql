-- 存储过程
-- 安全添加一个新字段，若存在则不添加
DROP PROCEDURE IF EXISTS AddColumnUnlessExists;
DELIMITER //
CREATE PROCEDURE AddColumnUnlessExists(
           IN dbName TINYTEXT,
           IN tableName TINYTEXT,
           IN fieldName TINYTEXT,
           IN fieldDef TEXT,        -- e.g.: 'VARCHAR(64) NOT NULL DEFAULT "FORM"'
           IN fieldComment TEXT
)
BEGIN
    IF NOT EXISTS (
      SELECT * FROM information_schema.COLUMNS
      WHERE table_name = tableName
        AND table_schema = dbName
        AND column_name = fieldName
    ) THEN
      SET @ddl=concat('ALTER TABLE ', tableName, ' ADD COLUMN ', fieldName,
        ' ', fieldDef, ' COMMENT ', "'", fieldComment, "'");
      prepare stmt from @ddl;
      execute stmt;
    END IF;
END;
//
DELIMITER ;

call AddColumnUnlessExists(Database(), 'ct_form_data_report_daily', 'scope_report',
    'VARCHAR(64) NOT NULL DEFAULT "form"', 'comment infomation');

DROP PROCEDURE IF EXISTS AddColumnUnlessExists;
