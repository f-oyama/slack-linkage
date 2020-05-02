# 概要

- slackと連携してみるサンプルアプリケーション
- Java / Spring boot / 設計の勉強も兼ねて


# システム構成

- Java : 11
- Spring-boot : 2.2.6


# コマンド

## アプリケーション起動

- Spring boot アプリの起動コマンド : `./gradlew bootRun`

## サンプル予約データのインポート

- インポートするシェル : `./tools/test/appoint/post-appoint.sh`


## 予約確認画面のパス

- 予約一覧の確認 : `http://localhost:8080/appoint/view/all`


# API 仕様

* TODO 書き途中 *

## 予約登録

- POST `/appoint` : `key=value` 形式のRequestBodyのデータを元に予約を行う
- POST `/appoint/json` : json形式のRequestBodyのデータを元に予約を行う


# パッケージ構成

レイヤードアーキテクチャを参考に、次の4つの層で実装中
勉強中なので、パッケージ構成や層ごとの処理・責務の認識が変わり次第、都度更新予定

- presentation
    - 外部とのエンドポイントが責務
        - 外部とやり取りするモデル <=> アプリケーションで扱うモデルの変換
        - リクエストのバリデーション
        - リクエストと処理のマッピング
        - などなど
- application
    - アプリケーションで実現したい機能の、処理をまとめるのが責務
    - domain / infrastructureを使って機能を実現する
    - どんな順番でdomain / infrastructure の各処理を呼び出せば良いかを定義した、業務手順書のようなイメージ
- domain
    - ドメインの業務知識・処理を行うのが責務
    - model / service それぞれで業務知識を定義する
    - ドメイン知識はここに凝集させることをイメージする
        - ドメインの業務知識が変更されました、となった時にここを変更すれば済むように
        - 一番大事なところなので、凝集して集中させたい
- infrastructure
    - データのI/O 永続化が責務
        - DB 接続 / IO が主要
        - DBを建てられていないので、今はListデータで保持している
