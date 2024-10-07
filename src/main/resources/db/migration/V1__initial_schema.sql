create table if not exists OPERATION_TYPE(
    OPERATION_TYPE_ID     SMALLINT    NOT NULL,
    DESCRIPTION     VARCHAR(30)  NOT NULL,
CONSTRAINT PK_OPERATION_TYPE PRIMARY KEY (OPERATION_TYPE_ID)
);

create table if not exists ACCOUNT(
    ACCOUNT_ID     SERIAL    NOT NULL,
    DOCUMENT_NUMBER     VARCHAR(30)  NOT NULL,
    CREATED_AT      TIMESTAMP   NOT NULL,
CONSTRAINT PK_ACCOUNT PRIMARY KEY (ACCOUNT_ID)
);

create table if not exists TRANSACTIONS(
    TRANSACTION_ID  SERIAL    NOT NULL,
    ACCOUNT_ID      INTEGER  NOT NULL,
    OPERATION_TYPE_ID    SMALLINT    NOT NULL,
    AMOUNT          DECIMAL(19,2) not null,
    EVENT_DATE      TIMESTAMP   NOT NULL,
CONSTRAINT PK_TRANSACTIONS PRIMARY KEY (TRANSACTION_ID),
CONSTRAINT FK_ACCOUNT FOREIGN KEY (ACCOUNT_ID) REFERENCES ACCOUNT(ACCOUNT_ID),
CONSTRAINT FK_OPERATION_TYPE FOREIGN KEY (OPERATION_TYPE_ID) REFERENCES OPERATION_TYPE(OPERATION_TYPE_ID)
);