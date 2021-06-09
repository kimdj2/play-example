# mysqlは5.7を使用
FROM mysql:5.7.16

# imageがdebianのため、apt-getで日本のlocaleを追加
RUN apt-get update && \
    apt-get install -y locales && \
    rm -rf /var/lib/apt/lists/* && \
    echo "ja_JP.UTF-8 UTF-8" > /etc/locale.gen && \
    locale-gen ja_JP.UTF-8
ENV LC_ALL ja_JP.UTF-8

# docker/db/my.cnfをdockerイメージ上の/etc/...にコピーして配置
COPY ./my.cnf /etc/mysql/conf.d/my.cnf
