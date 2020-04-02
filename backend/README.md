This is java backend part of the `recipes` project. You can compile it via

```bash
./mvnw -U -DskipTests clean compile package
```

It creates a `recipes.jar` file in the "target" directory. You can run it directly:

```bash
java -jar target/recipes.jar
```

If you want to create a docker image:

```bash
docker build . -t firatkucuk/recipes
```
