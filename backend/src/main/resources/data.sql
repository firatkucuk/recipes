INSERT INTO "CATEGORY" ("UUID", "NAME", "CREATED_AT")
VALUES (RANDOM_UUID(), 'Main dish', NOW()),
       (RANDOM_UUID(), 'Chili', NOW()),
       (RANDOM_UUID(), 'Liquor', NOW()),
       (RANDOM_UUID(), 'Cakes', NOW()),
       (RANDOM_UUID(), 'Cake mixes', NOW()),
       (RANDOM_UUID(), 'Microwave', NOW()),
       (RANDOM_UUID(), 'Vegetables', NOW());

INSERT INTO "UNIT" ("UUID", "NAME", "CREATED_AT")
VALUES (RANDOM_UUID(), 'pound', NOW()),
       (RANDOM_UUID(), 'can', NOW()),
       (RANDOM_UUID(), 'teaspoon', NOW()),
       (RANDOM_UUID(), 'tablespoon', NOW()),
       (RANDOM_UUID(), 'cup', NOW()),
       (RANDOM_UUID(), 'package', NOW());

