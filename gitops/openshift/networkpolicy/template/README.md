## Apply Default Network Policies.
* Reference: https://github.com/bcgov/networkpolicy-migration-workshop

### Template
* defaultNetworkPolicies.yaml (downloaded QuickStart.yaml from above link)


### Command to execute template
1) Login to OC using login command
2) Run below command in each env. namespace dev/test/prod/tools
   ``oc process -f defaultNetworkPolicies.yaml | oc apply -f -``
