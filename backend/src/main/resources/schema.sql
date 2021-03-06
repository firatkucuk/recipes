-- CATEGORY --------------------------------------------------------------------

CREATE TABLE "CATEGORY" (
    "ID"         INT8 AUTO_INCREMENT,
    "UUID"       VARCHAR(36) NOT NULL,
    "NAME"       VARCHAR(50) NOT NULL,
    "CREATED_AT" DATETIME    NOT NULL
);

ALTER TABLE "CATEGORY"
    ADD CONSTRAINT "PK_CATEGORY" PRIMARY KEY ("ID");

CREATE UNIQUE INDEX "UX_CATEGORY__UUID" ON "CATEGORY" ("UUID");
CREATE UNIQUE INDEX "UX_CATEGORY__NAME" ON "CATEGORY" ("NAME");

-- RECIPE ----------------------------------------------------------------------

CREATE TABLE "RECIPE" (
    "ID"         INT8 AUTO_INCREMENT,
    "UUID"       VARCHAR(36)  NOT NULL,
    "TITLE"      VARCHAR(150) NOT NULL,
    "YIELD"      INT4         NOT NULL,
    "CREATED_AT" DATETIME     NOT NULL
);

ALTER TABLE "RECIPE"
    ADD CONSTRAINT "PK_RECIPE" PRIMARY KEY ("ID");

CREATE UNIQUE INDEX "UX_RECIPE__UUID" ON "RECIPE" ("UUID");
CREATE UNIQUE INDEX "UX_RECIPE__TITLE" ON "RECIPE" ("TITLE");

-- DIRECTION_STEP --------------------------------------------------------------

CREATE TABLE "DIRECTION_STEP" (
    "ID"         INT8 AUTO_INCREMENT,
    "UUID"       VARCHAR(36)   NOT NULL,
    "RECIPE_ID"  INT8          NOT NULL,
    "CONTENT"    VARCHAR(2000) NOT NULL,
    "CREATED_AT" DATETIME      NOT NULL,
    CONSTRAINT "FK_DIRECTION_STEP__RECIPE_ID"
        FOREIGN KEY ("RECIPE_ID") REFERENCES "RECIPE" ("ID") ON DELETE CASCADE
);

ALTER TABLE "DIRECTION_STEP"
    ADD CONSTRAINT "PK_DIRECTION_STEP" PRIMARY KEY ("ID");

CREATE UNIQUE INDEX "UX_DIRECTION_STEP__UUID" ON "DIRECTION_STEP" ("UUID");

-- INGREDIENT_DIVISION ---------------------------------------------------------

CREATE TABLE "INGREDIENT_DIVISION" (
    "ID"         INT8 AUTO_INCREMENT,
    "UUID"       VARCHAR(36) NOT NULL,
    "RECIPE_ID"  INT8        NOT NULL,
    "TITLE"      VARCHAR(100),
    "CREATED_AT" DATETIME    NOT NULL,
    CONSTRAINT "FK_INGREDIENT_DIVISION__RECIPE_ID"
        FOREIGN KEY ("RECIPE_ID") REFERENCES "RECIPE" ("ID") ON DELETE CASCADE
);

ALTER TABLE "INGREDIENT_DIVISION"
    ADD CONSTRAINT "PK_INGREDIENT_DIVISION" PRIMARY KEY ("ID");

CREATE UNIQUE INDEX "UX_INGREDIENT_DIVISION__UUID" ON "INGREDIENT_DIVISION" ("UUID");

-- INGREDIENT_ITEM -------------------------------------------------------------

CREATE TABLE "INGREDIENT_ITEM" (
    "ID"          INT8 AUTO_INCREMENT,
    "UUID"        VARCHAR(36)  NOT NULL,
    "DIVISION_ID" INT8         NOT NULL,
    "QUANTITY"    VARCHAR(15),
    "UNIT"        VARCHAR(50),
    "CONTENT"     VARCHAR(200) NOT NULL,
    "CREATED_AT"  DATETIME     NOT NULL,
    CONSTRAINT "FK_INGREDIENT_ITEM__DIVISION_ID"
        FOREIGN KEY ("DIVISION_ID") REFERENCES "INGREDIENT_DIVISION" ("ID") ON DELETE CASCADE
);

ALTER TABLE "INGREDIENT_ITEM"
    ADD CONSTRAINT "PK_INGREDIENT_ITEM" PRIMARY KEY ("ID");

CREATE UNIQUE INDEX "UX_INGREDIENT_ITEM__UUID" ON "INGREDIENT_ITEM" ("UUID");

-- RECIPE_CATEGORY -------------------------------------------------------------

CREATE TABLE "RECIPE_CATEGORY" (
    "RECIPE_ID"   INT8 NOT NULL,
    "CATEGORY_ID" INT8 NOT NULL,
    CONSTRAINT "FK_RECIPE_CATEGORY__RECIPE_ID"
        FOREIGN KEY ("RECIPE_ID") REFERENCES "RECIPE" ("ID") ON DELETE CASCADE,
    CONSTRAINT "FK_RECIPE_CATEGORY__CATEGORY_ID"
        FOREIGN KEY ("CATEGORY_ID") REFERENCES "CATEGORY" ("ID") ON DELETE CASCADE
);

ALTER TABLE "RECIPE_CATEGORY"
    ADD CONSTRAINT "PK_RECIPE_CATEGORY" PRIMARY KEY ("RECIPE_ID", "CATEGORY_ID");
