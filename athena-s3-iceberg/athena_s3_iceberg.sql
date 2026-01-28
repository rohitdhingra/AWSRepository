-- Create Database
create database athena_s3_iceberg

-- Month wise
CREATE TABLE athena_s3_iceberg.sales_iceberg (
     order_id    string,
     customer_id string,
     amount      double,
     order_ts    timestamp
)
PARTITIONED BY (
    month(order_ts)
)
LOCATION 's3://athena-s3-rd-bucket/iceberg/sales/'
TBLPROPERTIES (
  'table_type'='ICEBERG',
  'format'='parquet'
);

-- Day wise
CREATE TABLE athena_s3_iceberg.sales_iceberg (
     order_id    string,
     customer_id string,
     amount      double,
     order_ts    timestamp
)
    PARTITIONED BY (day(order_ts))
LOCATION 's3://athena-s3-rd-bucket/iceberg/sales/'
TBLPROPERTIES (
  'table_type'='ICEBERG',
  'format'='parquet'
);

--with complete timestamp partitioning
CREATE TABLE athena_s3_iceberg.sales_iceberg (
     order_id    string,
     customer_id string,
     amount      double,
     order_ts    timestamp
)
PARTITIONED BY (order_ts)
LOCATION 's3://athena-s3-rd-bucket/iceberg/sales/'
TBLPROPERTIES (
  'table_type'='ICEBERG',
  'format'='parquet'
);

insert into athena_s3_iceberg.sales_iceberg (order_id, customer_id, amount, order_ts)
values ('ORD001', 'CUST001', 250.75, timestamp '2024-01-15 10:30:00'),
       ('ORD002', 'CUST002', 150.00, timestamp '2024-01-20 14:45:00'),
       ('ORD003', 'CUST001', 300.50, timestamp '2024-02-05 09:15:00'),
       ('ORD004', 'CUST003', 450.25, timestamp '2024-02-18 16:00:00');

delete from athena_s3_iceberg.sales_iceberg where order_id = 'ORD001';

-- Drop Table
DROP TABLE `sales_iceberg`;



