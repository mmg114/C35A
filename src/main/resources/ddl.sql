CREATE TABLE roles (
                       id           BIGSERIAL PRIMARY KEY,
                       name         VARCHAR(100) NOT NULL UNIQUE,   -- p.ej. ROLE_USER, ROLE_ADMIN
                       description  VARCHAR(255)
);

CREATE TABLE app_users (
                           id             BIGSERIAL PRIMARY KEY,
                           username       VARCHAR(100) NOT NULL UNIQUE, -- o usa email si prefieres
                           email          VARCHAR(255) UNIQUE,
                           password_hash  VARCHAR(255) NOT NULL,        -- BCrypt
                           enabled        BOOLEAN NOT NULL DEFAULT TRUE,
                           account_non_locked BOOLEAN NOT NULL DEFAULT TRUE,
                           account_non_expired BOOLEAN NOT NULL DEFAULT TRUE,
                           credentials_non_expired BOOLEAN NOT NULL DEFAULT TRUE,
                           created_at     TIMESTAMPTZ NOT NULL DEFAULT NOW(),
                           updated_at     TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE TABLE user_roles (
                            user_id  BIGINT NOT NULL REFERENCES app_users(id) ON DELETE CASCADE,
                            role_id  BIGINT NOT NULL REFERENCES roles(id)     ON DELETE CASCADE,
                            PRIMARY KEY (user_id, role_id)
);

CREATE INDEX idx_app_users_username ON app_users(username);
CREATE INDEX idx_app_users_email ON app_users(email);
CREATE INDEX idx_user_roles_user ON user_roles(user_id);
CREATE INDEX idx_user_roles_role ON user_roles(role_id);


INSERT INTO roles(name, description) VALUES
                                         ('ROLE_USER', 'Acceso básico'),
                                         ('ROLE_ADMIN', 'Administración');