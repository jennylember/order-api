IF NOT EXISTS (SELECT 1 FROM information_schema.tables WHERE table_name = 'Orders') THEN

    CREATE TABLE Orders  (
        id VARCHAR(255),
        description VARCHAR(255)
    );

    INSERT INTO Orders (id, description) VALUES ('1692872867546', 'description 1');
    INSERT INTO Orders (id, description) VALUES ('1692872867547', 'description 2');
    INSERT INTO Orders (id, description) VALUES ('1692872867548', 'description 3');
    INSERT INTO Orders (id, description) VALUES ('1692872868243', 'description 4');
    INSERT INTO Orders (id, description) VALUES ('1692872868244', 'description 5');
    INSERT INTO Orders (id, description) VALUES ('1692872868245', 'description 6');
    INSERT INTO Orders (id, description) VALUES ('1692872868861', 'description 7');
    INSERT INTO Orders (id, description) VALUES ('1692872868862', 'description 8');
    INSERT INTO Orders (id, description) VALUES ('1692872868863', 'description 9');
END IF;
