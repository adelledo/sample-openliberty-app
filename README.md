[Creating a container]


podman build . -t logging-sample 

[Running the container]
podman run -ti --rm --name logging-sample --privileged -p9080:9080 localhost/logging-sample:latest  

visit localhost:9080/health to generate some logs...


exec into the pod:
`podman exec -it logging-sample /usr/bin/bash`

observe logs created at /logs/sample-app.log