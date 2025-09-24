#!/bin/bash
DIR=/home/mmmdeb/Public/ProSoft
grep -r "$1" $DIR/Client/src/* $DIR/Common/src/* $DIR/Server/src/* 