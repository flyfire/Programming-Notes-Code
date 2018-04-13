# 1.分类表
CREATE TABLE category (
  cid   VARCHAR(32) PRIMARY KEY,
  cname VARCHAR(100)
);

# 2.商品表
CREATE TABLE product (
  pid         VARCHAR(32) PRIMARY KEY,
  pname       VARCHAR(40),
  price       DOUBLE,
  category_id VARCHAR(32)
);

ALTER TABLE product
  ADD FOREIGN KEY (category_id) REFERENCES category (cid);

# 3.添加外键列
ALTER TABLE product
  ADD category_id VARCHAR(32);

# 4.添加约束
ALTER TABLE product
  ADD CONSTRAINT product_fk FOREIGN KEY (category_id) REFERENCES category (cid);

# 5.订单表
CREATE TABLE orders (
  oid        VARCHAR(32) PRIMARY KEY,
  totalprice DOUBLE
);

# 6.订单项表
CREATE TABLE orderitem (
  oid VARCHAR(50),
  pid VARCHAR(50)
);

# 7.联合主键
ALTER TABLE orderitem
  ADD PRIMARY KEY (oid, pid);

# 8.订单表和订单项表的主外键关系
ALTER TABLE orderitem
  ADD CONSTRAINT orderitem_orders_fk FOREIGN KEY (oid) REFERENCES orders (oid);

# 9.商品表和订单项表的主外键关系
ALTER TABLE orderitem
  ADD CONSTRAINT orderitem_product_fk FOREIGN KEY (pid) REFERENCES product (pid);


INSERT INTO category (cid, cname) VALUES ('c001', '家电');
INSERT INTO category (cid, cname) VALUES ('c002', '服饰');
INSERT INTO category (cid, cname) VALUES ('c003', '化妆品');

INSERT INTO product (pid, pname, price, category_id) VALUES ('p001', '联想', '5000', 'c001');
INSERT INTO product (pid, pname, price, category_id) VALUES ('p002', '海尔', '5000', 'c001');
INSERT INTO product (pid, pname, price, category_id) VALUES ('p003', '雷神', '5000', 'c001');

INSERT INTO product (pid, pname, price, category_id) VALUES ('p004', 'JACK JONES', '800', 'c002');
INSERT INTO product (pid, pname, price, category_id) VALUES ('p005', '真维斯', '200', 'c002');
INSERT INTO product (pid, pname, price, category_id) VALUES ('p006', '花花公子', '440', 'c002');
INSERT INTO product (pid, pname, price, category_id) VALUES ('p007', '劲霸', '2000', 'c002');

INSERT INTO product (pid, pname, price, category_id) VALUES ('p008', '香奈儿', '800', 'c003');
INSERT INTO product (pid, pname, price, category_id) VALUES ('p009', '相宜本草', '200', 'c003');




