### Unit Test
To run unit tests for controllers, the following components are recommended:

If Linux terminal is preferred, set up WSL by the following instructions:
* WSL (Windows Subsystem for Linux)
1) Install Ubuntu 20.04 from Windows Store
2) Make sure Virtualization Technology is turned on (the default is ON), verified through BIOS 
3) Open up a Windows Powershell (admin)
4) Enable the Virtual Machine Platform optional feature - ```dism.exe /online /enable-feature /featurename:VirtualMachinePlatform /all /norestart```
5) Run ```wsl --set-default-version 2```
6) Run ```wsl --list -v``` to check if the VERSION is 2

* Cypress - On a linux terminal:
1) Execute ```sudo apt install build-essential```
2) Execute ```curl -sL https://deb.nodesource.com/setup_current.x | sudo -E bash -```
3) Install dependencies - ```sudo apt-get install libgtk2.0-0 libgtk-3-0 libgbm-dev libnotify-dev libgconf-2-4 libnss3 libxss1 libasound2 libxtst6 xauth xvfb```
4) cd to AutomatedTests directory and run ```rm -rf node_modules```
5) Install node - ```npm i```
6) To execute unit tests - ```npm test``` 
