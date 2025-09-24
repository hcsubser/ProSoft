#!/bin/bash
DIR=/home/mmmdeb/Public/ProSoft
sed -i "s/$1/$2/g" $DIR/Client/src/controller/* $DIR/Client/src/main/* $DIR/Client/src/session/* \
$DIR/Client/src/tableModel/* $DIR/Client/src/view/*

sed -i "s/$1/$2/g" $DIR/Common/src/domain/* $DIR/Common/src/transfer/util/* $DIR/Common/src/transfer/Request.java \
$DIR/Common/src/transfer/Response.java $DIR/Common/src/validator/*

sed -i "s/$1/$2/g" $DIR/Server/src/controller/* $DIR/Server/src/dbb/* $DIR/Server/src/main/* $DIR/Server/src/thread/* \
$DIR/Server/src/view/* $DIR/Server/src/so/OpstaSistemskaOperacija.java $DIR/Server/src/so/evidencijakursa/* \
$DIR/Server/src/so/instruktor/* $DIR/Server/src/so/licenca/* $DIR/Server/src/so/mesto/* $DIR/Server/src/so/polaznik/* \
$DIR/Server/src/so/poreskastopa/* $DIR/Server/src/so/stavkaevidencijekursa/* $DIR/Server/src/so/tipcasa/*