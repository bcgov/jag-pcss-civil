## Templates to create openshift components related to jag-pcss-civil api deployment

### Command to execute template
1) Login to OC using login command
2) Run below command in each env. namespace dev/test/prod/tools
   ``oc process -f jag-pcss-civil.yaml --param-file=jag-pcss-civil.env | oc apply -f -``


