INSERT INTO "CATEGORY" ("UUID", "NAME", "CREATED_AT")
VALUES (RANDOM_UUID(), 'Main dish', NOW()),
       (RANDOM_UUID(), 'Chili', NOW()),
       (RANDOM_UUID(), 'Liquor', NOW()),
       (RANDOM_UUID(), 'Cakes', NOW()),
       (RANDOM_UUID(), 'Cake mixes', NOW()),
       (RANDOM_UUID(), 'Microwave', NOW()),
       (RANDOM_UUID(), 'Vegetables', NOW());

INSERT INTO "RECIPE" ("UUID", "TITLE", "YIELD", "CREATED_AT")
VALUES (RANDOM_UUID(), '30 Minute Chili', 6, NOW()),
       (RANDOM_UUID(), 'Amaretto Cake', 1, NOW()),
       (RANDOM_UUID(), 'Another Zucchini Dish', 6, NOW());

INSERT INTO "RECIPE_CATEGORY" ("RECIPE_ID", "CATEGORY_ID")
VALUES (1, 1),
       (1, 2),
       (2, 3),
       (2, 4),
       (2, 5),
       (3, 6),
       (3, 7);

INSERT INTO "DIRECTION_STEP" ("UUID", "RECIPE_ID", "CONTENT", "CREATED_AT")
VALUES (RANDOM_UUID(), 1,
        'Brown the meat in a little butter and cook until the meat is brown -- about\n10 minutes. Add all other ingredients and let simmer for 30 minutes. Your\nchoice of hot sauce may be added to taste.\n\nRecipe by: MasterCook Archives\n\nPosted to recipelu-digest Volume 01 Number 577 by Rodeo46898\n&lt;Rodeo46898@aol.com&gt; on Jan 22, 1998',
        NOW()),
       (RANDOM_UUID(), 2,
        'Sprinkle 1 cup almonds into bottom of a well-greased and floured 10"\ntube pan; set aside. Combine cake mix, pudding mix, eggs, oil, water,\namaretto, and almond extract in a mixing bowl; beat on low speed of\nan\nelectric mixer til dry ingredients are moistened. Increase\nspeed to medium, and beat 4 minutes. Stir in remaining 1/2 cup\nalmonds. Pour batter into prepared tube pan. Bake at 325~ for 1 hour\nor til a wooden pick inserted in center comes out clean. Cool cake in pan\n10-15 minutes; remove from pan, and cool completely. Combine sugar,\nwater, and butter in a small saucepan; bring to a boil. Reduce heat to\nmedium and gently boil 4-5 minutes, stirring occasionally, until sugar\ndissolves. Remove from heat, and cool 15 minutes. Stir\nin amaretto and almond extract. Punch holes in top of cake with a\n\nwooden pick; slowly spoon glaze on top of cake, allowing glaze to absorb incake.\nPosted to MC-Recipe Digest V1 #263\n\nDate: Sun, 27 Oct 1996 20:04:57 +0000\n\nFrom: Cheryl Gimenez &lt;clgimenez@earthlink.net&gt;',
        NOW()),
       (RANDOM_UUID(), 3,
        'Place zucchini and butter in a 2 quart casserole. Cover with plastic wrap.\nMicrowave at high (100%) until tender crisp, 4 to 5 minutes. Stir in egg,\npimentos and cheese. Blend well. Cover with plastic wrap. Microwave at\nmedium (50%) until eggs are set, 10 toll minutes. Sprinkle onion rings on\ntop. Microwave at medium (50%) until onion rings are heated, 2 to 2 1/2\nminutes. Makes 6 servings.\n\nRecipe by: diane@keyway.net\n\nPosted to recipelu-digest Volume 01 Number 217 by "Diane Geary"\n&lt;diane@keyway.net&gt; on Nov 7, 1997',
        NOW());

INSERT INTO "INGREDIENT_DIVISION" ("UUID", "RECIPE_ID", "TITLE", "CREATED_AT")
VALUES (RANDOM_UUID(), 1, NULL, NOW()),
       (RANDOM_UUID(), 2, NULL, NOW()),
       (RANDOM_UUID(), 2, 'GLAZE', NOW()),
       (RANDOM_UUID(), 3, NULL, NOW());

INSERT INTO "INGREDIENT_ITEM" ("UUID", "DIVISION_ID", "QUANTITY", "UNIT", "CONTENT", "CREATED_AT")
VALUES (RANDOM_UUID(), 1, '1', 'pound', 'Ground chuck or lean ground; beef', NOW()),
       (RANDOM_UUID(), 1, '1', NULL, 'Onion; large, chopped', NOW()),
       (RANDOM_UUID(), 1, '1', 'can', 'Kidney beans; (12 oz)', NOW()),
       (RANDOM_UUID(), 1, '1', 'can', 'Tomato soup; undiluted', NOW()),
       (RANDOM_UUID(), 1, '1', 'teaspoon', 'Salt', NOW()),
       (RANDOM_UUID(), 1, '1', 'tablespoon', 'Chili powder; (or to taste)', NOW()),
       (RANDOM_UUID(), 1, NULL, NULL, 'Hot pepper sauce; to taste', NOW()),
       (RANDOM_UUID(), 2, '1 1/2', 'cups', 'Toasted Almonds; chopped', NOW()),
       (RANDOM_UUID(), 2, '1', 'package', 'Yellow cake mix; no pudding', NOW()),
       (RANDOM_UUID(), 2, '1', 'package', 'Vanilla instant pudding', NOW()),
       (RANDOM_UUID(), 2, '4', NULL, 'Eggs', NOW()),
       (RANDOM_UUID(), 2, '1/2', 'cups', 'Vegetable oil', NOW()),
       (RANDOM_UUID(), 2, '1/2', 'cups', 'Water', NOW()),
       (RANDOM_UUID(), 2, '1/2', 'cups', 'Amaretto', NOW()),
       (RANDOM_UUID(), 2, '1', 'teaspoon', 'Almond extract', NOW()),
       (RANDOM_UUID(), 3, '1/2', 'cups', 'Sugar', NOW()),
       (RANDOM_UUID(), 3, '1/4', 'cups', 'Water', NOW()),
       (RANDOM_UUID(), 3, '2', 'tablespoons', 'Butter', NOW()),
       (RANDOM_UUID(), 3, '1/4', 'cups', 'Amaretto', NOW()),
       (RANDOM_UUID(), 3, '1/2', 'teaspoons', 'Almond extract', NOW()),
       (RANDOM_UUID(), 4, '1', 'pound', 'Zucchini; cubed 1/2 "', NOW()),
       (RANDOM_UUID(), 4, '3', 'tablespoons', 'Butter or margarine', NOW()),
       (RANDOM_UUID(), 4, '3', 'tablespoons', 'Eggs; beaten', NOW()),
       (RANDOM_UUID(), 4, '1', NULL, 'Jar pimentos; 2 1/2 oz, diced', NOW()),
       (RANDOM_UUID(), 4, '1', 'cup', 'Cheddar cheese; shredded', NOW()),
       (RANDOM_UUID(), 4, '1', 'can', 'French fried onion rings 3 oz.', NOW());
