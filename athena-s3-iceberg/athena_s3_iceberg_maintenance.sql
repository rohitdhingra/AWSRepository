-- snapshots
SELECT
    snapshot_id,
    parent_id,
    committed_at,
    operation,
    summary
FROM "athena_s3_iceberg"."sales_iceberg$snapshots"
ORDER BY committed_at DESC;

--all files

SELECT
    file_path, record_count
FROM "athena_s3_iceberg"."sales_iceberg$files"


SELECT file_path, deleted_at
FROM "athena_s3_iceberg"."sales_iceberg$deleted_files";


SELECT path, added_snapshot_id
FROM "athena_s3_iceberg"."sales_iceberg$manifests";


SHOW TABLES IN athena_s3_iceberg;

SELECT *
FROM "awsdatacatalog$iceberg-aws"."athena_s3_iceberg"."sales_iceberg$deleted_files";

