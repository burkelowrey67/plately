SELECT 'CREATE DATABASE plately' WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'plately')\gexec
