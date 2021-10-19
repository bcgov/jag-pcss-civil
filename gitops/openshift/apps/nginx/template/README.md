## Templates to create/update niginx containers for reverse proxy & split traffic between old webmethods and new jag-pcss-civil api

### Template for Nginx 1 - To reverse proxy and split traffic between new jag-pcss-civil api & the other Nginx 2 server container.
* defaultNetworkPolicies.yaml (downloaded QuickStart.yaml from above link)


### Command to execute template
1) Login to OC using login command
2) Run below command in each env. namespace dev/test/prod/tools
   ``oc process -f nginx_jag_pcss_civil.yaml --param-file=nginx_jag_pcss_civil.env | oc apply -f -``

### Template for Nginx 2 - To reverse proxy the traffic from Nginx 1 to the old webmethods api.


### Command to execute template
1) Login to OC using login command
2) Run below command in each env. namespace dev/test/prod/tools
   ``oc process -f nginx_jag_pcss_civil_oldwm.yaml --param-file=nginx_jag_pcss_civil_oldwm.env | oc apply -f -``

