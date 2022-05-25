### yaml파일을 통해 redis pod 새로 만들기

1. 아래 명령어로 yaml 파일 생성하기
   kubectl run redis --image=redis123 --dry-run=client -o yaml > redis-definition.yaml

2. 그럼 아래와 같이 yaml파일이 생성된다

```yml
apiVersion: v1
kind: Pod
metadata:
  creationTimestamp: null
  labels:
    run: redis
  name: redis
spec:
  containers:
    - image: redis123
      name: redis
      resources: {}
  dnsPolicy: ClusterFirst
  restartPolicy: Always
status: {}
```

3. 파드 생성 명령어
   kubectl create -f redis-definition.yaml

### pod를 수정하는 방법

#### 명령어로 하는 방법

- kubectl edit pod redis

#### yaml파일을 수정하고 바로 적용하는 방법

- kubectl applty -f redis-definition.yaml

## ReplicaSet

### replicaset 수정하는 방법

- kubectl edit replicaset new-replica-set

### replicaset 스케일 늘리는 방법

- kubectl edit replicaset new-replica-set -> replicas 수정
- kubectl scale rs new-replica-set --replicas=5

## Deployment

### 아래 조건으로 Deployment 만들기

---

Name: httpd-frontend
Replicas: 3
Image : httpd:2.4-alpine

---

- yml 파일 생성

```yml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: httpd-frontend
spec:
  replicas: 3
  selector:
    matchLabels:
      name: httpd-frontend
  template:
    metadata:
      labels:
        name: httpd-frontend
    spec:
      containers:
        - name: httpd-frontend
          image: httpd:2.4-alpine
```

- kubectl create -f deployment-definition-httpd.yaml

## Service

### 아래와 같은 조건의 서비스에 대한 yaml 파일생성

---

Name: webapp-service
Type: NodePort
tragetPort : 8080
port: 8080
nodePort: 30080
selector: simple-webapp

---

```yaml
apiVersion: v1
kind: Service
metadata:
  name: webapp-service
spec:
  type: NodePort
  ports:
    - targetPort: 8080
      port: 8080
      nodePort: 30080
  selector:
    name: simple-webapp
```

- kubectl apply -f 파일명.yaml

## Tolation

root@controlplane:~# kubectl run bee --image=nginx --restart=Never --dry-run=client -o yaml > bee.yaml
root@controlplane:~# kubectl explain pod --recursive | less
kubectl explain pod --recursive | grep -A5 tolerations

### TLS Certificates
