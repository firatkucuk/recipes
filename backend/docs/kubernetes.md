Deployment definition

```bash
echo "
apiVersion: apps/v1
kind: Deployment
metadata:
  name: recipes-backend
spec:
  replicas: 1
  selector:
    matchLabels:
      app: recipes-backend
  template:
    metadata:
      labels:
        app: recipes-backend
    spec:
      containers:
        - name: repsy-panel-backend
          image: firatkucuk/recipes:latest
          ports:
            - containerPort: 9090
" | kubectl apply -f -
```

Service definition

```bash
echo "
apiVersion: v1
kind: Service
metadata:
  name: recipes-backend
spec:
  ports:
    - port: 80
      targetPort: 9090
      protocol: TCP
      name: http
  selector:
    app: recipes-backend
" | kubectl apply -f -
```

Ingress Rule:

```bash
echo "
apiVersion: extensions/v1beta1
kind: Ingress
metadata:
  name: recipes-backend
spec:
  rules:
  - host: recipes.firat.io
    http:
      paths:
      - path: /
        backend:
          serviceName: recipes-backend
          servicePort: 80
" | kubectl apply -f -
```

