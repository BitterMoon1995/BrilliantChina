-- ---------------------------------------------------------------------------------------
ALTER TABLE `user` 
ORDER BY
	role,
	username;
	
SELECT
	* 
FROM
	`user` 
ORDER BY
	CONVERT ( role USING gbk ),
	CONVERT ( username USING gbk );
	
-- 中文分页模糊查询 -----------------------------------------------------------------------
SELECT LOCATE('bar', 'foobarbar');

SELECT * FROM mini_scene WHERE username = 'admin' AND LOCATE('小人',name) > 0;
SELECT * FROM mini_scene WHERE LOCATE('啊',name) > 0 LIMIT 0,2;

SELECT COUNT(*) FROM mini_scene WHERE LOCATE('啊',name) > 0;
SELECT COUNT(*) FROM mini_scene WHERE username = '' AND LOCATE('啊',name) > 0;

-- 景区置顶管理	列表 -----------------------------------------------------------------------
SELECT s.name,
i.id imgId,i.top floorTop,i.order_num floorOrder,
sl.id sliderId,sl.top sliderTop,sl.order_num sliderOrder
FROM mini_scene AS s 
LEFT JOIN mini_scene_image AS i
ON s.id=i.scene_id
LEFT JOIN mini_slider AS sl
ON s.id=sl.target_id
WHERE i.type='postcard';
--  搜索
SELECT s.name,
i.id imgId,i.top floorTop,i.order_num floorOrder,
sl.id sliderId,sl.top sliderTop,sl.order_num sliderOrder
FROM mini_scene AS s 
LEFT JOIN mini_scene_image AS i
ON s.id=i.scene_id
LEFT JOIN mini_slider AS sl
ON s.id=sl.target_id
WHERE i.type='postcard'
AND LOCATE( #{name} ,s.`name`) > 0
LIMIT #{index},#{offset};

show variables like '%time_zone%';

-- 尝试老方法给表重新排序，无论按create_time、order_num都全部失效-----------------------------------
SELECT * FROM mini_route ORDER BY create_time;

SELECT DATE_FORMAT(create_time,'%Y%m%d%H%i%s') theshy FROM mini_route;

ALTER TABLE `mini_route` 
ORDER BY
	order_num	ASC;
	
-- 问题的关键在于索引

-- 查看索引
SHOW INDEX FROM mini_scene;
-- 删除索引
DROP INDEX PRIMARY ON mini_scene;

-- 索引可以优化该字段的查找，与排序
-- 创建时间不一定是唯一的（唯一有两个畜生同时创建景区不GG了）
-- 建议本排序字段弄个普通索引即可
CREATE INDEX index_create_time ON mini_scene(create_time); 
-- 现在就可以按照创建时间排序了
ALTER TABLE mini_scene ORDER BY create_time DESC;

-- 频繁作为查找条件的字段应该创建索引
-- 名字肯定是唯一的，service层控死了的
CREATE UNIQUE INDEX index_name ON mini_scene(name);


-- 小程序模糊查询
SELECT s.name `name`,s.slogan slogan,i.url url 
FROM mini_scene s
LEFT JOIN mini_scene_image i
ON s.id=i.scene_id
WHERE (s.name LIKE CONCAT('%','测试','%')
OR s.slogan LIKE CONCAT('%','测试','%'))
AND i.type='postcard';

SELECT r.name `name`,r.slogan slogan,i.url url 
FROM mini_route r
LEFT JOIN mini_route_image i
ON r.id=i.route_id
WHERE (r.name LIKE CONCAT('%','八旗','%')
OR r.slogan LIKE CONCAT('%','八旗','%'))
AND i.type='postcard';

-- 会员剩余天数每天减1
SHOW VARIABLES LIKE 'event_scheduler'; #查看时间调度是否开启

SET GLOBAL event_scheduler = ON;  #ON开启，OFF关闭

#写一个函数  begin------------
CREATE PROCEDURE reduceVIP() 
BEGIN 
update vip_card SET remaining_days = remaining_days - 1; 
END; 
#写一个函数  end------------

#写一个事件  begin------------
create event if not exists zmchina.reduceVIP
on schedule every 1 second 
on completion preserve 
do call reduceVIP(); 
#写一个事件  end------------

#开启事件
alter event zmchina.reduceVIP ON 
COMPLETION PRESERVE ENABLE;

#关闭事件
alter event e_test ON 
COMPLETION PRESERVE DISABLE;











