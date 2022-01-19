## Templates to create openshift components related to jag pcss civil, criminal & common automated test api deployments

### Command to execute template
1) Login to OC using login command
2) Run below command in each env. namespace dev/test/prod
   ``oc process -f jag-pcss-tests.yaml --param-file=jag-pcss-tests.env | oc apply -f -``


