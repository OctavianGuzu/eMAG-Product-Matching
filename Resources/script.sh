#!/bin/bash

touch c
echo "$(cat $1)" >> c
echo "$(cat $2)" >> c
