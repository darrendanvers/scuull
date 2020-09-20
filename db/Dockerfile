#
# Grab the latest PostgreSQL container.
#
FROM postgres

#
# Load the schema and sample data SQL files so that the
# schema and sample data will be created at startup.
#
COPY ./schema.sql /docker-entrypoint-initdb.d/100-schema.sql
COPY ./data.sql /docker-entrypoint-initdb.d/200-data.sql