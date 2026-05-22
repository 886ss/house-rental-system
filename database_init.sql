-- 房屋租赁系统数据库初始化脚本

-- 创建数据库
CREATE DATABASE IF NOT EXISTS house_rental_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- 使用数据库
USE house_rental_db;

-- 用户表
CREATE TABLE IF NOT EXISTS users (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    phone VARCHAR(20),
    role ENUM('admin', 'user') DEFAULT 'user',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 房屋表
CREATE TABLE IF NOT EXISTS houses (
    house_id VARCHAR(20) PRIMARY KEY,
    address VARCHAR(200) NOT NULL,
    area DECIMAL(10,2) NOT NULL,
    bedrooms INT NOT NULL,
    rent DECIMAL(10,2) NOT NULL,
    status ENUM('vacant', 'rented') DEFAULT 'vacant',
    description TEXT,
    owner_id INT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (owner_id) REFERENCES users(id)
);

-- 租客表
CREATE TABLE IF NOT EXISTS tenants (
    tenant_id VARCHAR(20) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    id_card VARCHAR(18) UNIQUE NOT NULL,
    work_place VARCHAR(200),
    email VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 租赁合同表
CREATE TABLE IF NOT EXISTS contracts (
    contract_id VARCHAR(20) PRIMARY KEY,
    house_id VARCHAR(20) NOT NULL,
    tenant_id VARCHAR(20) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    deposit DECIMAL(10,2) NOT NULL,
    rent DECIMAL(10,2) NOT NULL,
    status ENUM('active', 'terminated', 'expired') DEFAULT 'active',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (house_id) REFERENCES houses(house_id),
    FOREIGN KEY (tenant_id) REFERENCES tenants(tenant_id)
);

-- 插入管理员账户 (密码: admin123 - MD5加密)
INSERT IGNORE INTO users (username, password, email, role)
VALUES ('admin', '21232f297a57a5a743894a0e4a801fc3', 'admin@rental.com', 'admin');

-- 插入示例房屋数据
INSERT IGNORE INTO houses (house_id, address, area, bedrooms, rent, description) VALUES
('H001', '北京市海淀区中关村大街1号', 85.5, 2, 4500.00, '交通便利，靠近地铁站'),
('H002', '上海市浦东新区陆家嘴路100号', 120.0, 3, 8000.00, '豪华装修，视野开阔'),
('H003', '广州市天河区体育西路50号', 65.0, 1, 3200.00, '单身公寓，配套齐全'),
('H004', '深圳市南山区科技园路88号', 95.0, 2, 5500.00, '精装修，拎包入住');

-- 插入示例租客数据
INSERT IGNORE INTO tenants (tenant_id, name, phone, id_card, work_place, email) VALUES
('T001', '张三', '13800138000', '110101199001011234', '阿里巴巴公司', 'zhangsan@email.com'),
('T002', '李四', '13900139000', '110101199102022345', '腾讯科技', 'lisi@email.com');

-- 查询验证
SELECT '用户表数据:' AS '';
SELECT * FROM users;

SELECT '租客表数据:' AS '';
SELECT * FROM tenants;

SELECT '房屋表数据:' AS '';
SELECT * FROM houses;

SELECT '合同表数据:' AS '';
SELECT * FROM contracts;