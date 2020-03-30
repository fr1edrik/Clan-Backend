#!/bin/sh
free -m -h | grep -v "total" | awk '{printf "%s %s \n", $2, $4}'