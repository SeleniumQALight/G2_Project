#!/bin/bash
java -jar selenium-server-standalone-2.44.0.jar -role node -nodeConfig nodeconfig.json -Dwebdriver.chrome.driver="..\drivers\94\chromedriver_mac64\chromedriver"
sleep 10000