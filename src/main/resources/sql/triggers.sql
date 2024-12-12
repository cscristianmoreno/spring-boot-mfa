CREATE OR REPLACE FUNCTION fn_roles_duplicate() RETURNS trigger AS $$
    DECLARE 
        role TEXT;
    BEGIN
        SELECT 1 FROM roles WHERE name = new.name INTO role;

        IF role IS NOT NULL THEN
            RAISE NOTICE 'This role is already exist';
            RETURN NULL;
        END IF;

        RETURN new;
    END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER tr_roles_duplicate
BEFORE INSERT ON roles
FOR EACH ROW
EXECUTE FUNCTION fn_roles_duplicate();