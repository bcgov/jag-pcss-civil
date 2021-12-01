## Templates to create/update all required components to set-up load testing environment in Opennshift using Jmeter, InfluxDb & Grafana

### Reference Link
https://blog.kubernauts.io/load-testing-as-a-service-with-jmeter-on-kubernetes-fc5288bb0c8b


### Command to execute template to create/update Jmeter Master
1) Login to OC using login command
2) Run below command in each env. namespace dev/test/prod/tools
   ``oc process -f Jmeter_Manager.yaml --param-file=Jmeter.env | oc apply -f -``

### Command to execute template to create/update Jmeter Slaves
1) Login to OC using login command
2) Run below command in each env. namespace dev/test/prod/tools
   ``oc process -f Jmeter_Worker.yaml --param-file=Jmeter.env | oc apply -f -``

### Command to execute template to create/update InfluxDB
1) Login to OC using login command
2) Run below command in each env. namespace dev/test/prod/tools
   ``oc process -f InfluxDB.yaml --param-file=Jmeter.env | oc apply -f -``

### Command to execute template to create/update Grafana
1) Login to OC using login command
2) Run below command in each env. namespace dev/test/prod/tools
   ``oc process -f Grafana.yaml --param-file=Jmeter.env | oc apply -f -``


