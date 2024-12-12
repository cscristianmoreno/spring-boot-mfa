DO $$
    DECLARE
        role_type_exist RECORD;
    BEGIN
        RAISE NOTICE 'Ha llegado aquí';
        SELECT typname FROM pg_type WHERE typname = 'role_type' INTO role_type_exist;

        IF role_type_exist IS NULL THEN
            CREATE TYPE role_type AS ENUM (
                'ROLE_ADMIN',
                'ROLE_USER'
            );
        END IF;
    END;
$$;