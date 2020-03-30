#!/bin/sh
sar 1 1 -u | grep -v "CPU" | awk '{print 100-$8}'