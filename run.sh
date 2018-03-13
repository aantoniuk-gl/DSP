#!/bin/bash

for i in $(seq 1 $1); do
    cmd="java -jar target/crypto.poc.dsp-1.0-SNAPSHOT.jar --server.port=808$i";
    echo $cmd
    $cmd &
done