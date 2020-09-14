package top.anly.common;

/**
 * Redis缓存服务
 *
 * @author yinhaijing
 * @date 2020/2/20 11:01
 */

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Service
public class MyRedisTemplate {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 写入缓存(不设置时间)
     * @param key
     * @param value
     * @return
     */
    public boolean set(final String key, Object value) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().set(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 写入缓存（设置时效（秒））
     * @param key
     * @param value
     * @param expireTime 秒为单位
     * @return
     */
    public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;
        try {
            if(set(key, value)) {
                redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 读取缓存
     * @param key
     * @return
     */
    public Object get(final String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 更新缓存
     */
    public boolean getAndSet(final String key, String value) {
        boolean result = false;
        try {
            redisTemplate.opsForValue().getAndSet(key, value);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 删除缓存
     */
    public boolean delete(final String key) {
        boolean result = false;
        try {
            redisTemplate.delete(key);
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}