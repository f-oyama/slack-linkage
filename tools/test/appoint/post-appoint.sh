#!/bin/sh

cd `dirname $0`
# ファイルがある場所に移動 = 実行場所を気にしなくてよくなる

echo "post appoint"

# 削除API 洗い替えしたくない場合はコメントアウト
curl -X GET "localhost:8080/appoint/delete/all"
echo

# 予約登録
for ((i=1 ; i<=4 ; i++))
do
curl -X POST "localhost:8080/appoint" -H "Content-type: application/text" --data-binary @data/slack-appoint-sample${i}.txt
echo
done
