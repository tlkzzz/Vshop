package com.Vshop.core.auth.shiro.cache;

import com.google.common.collect.Sets;
import com.Vshop.core.codec.util.SerializeUtil;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

/**
 * 使用Redis实现的Session服务器
 */
@Slf4j
public class RedisSessionDAO extends AbstractSessionDAO {

    /**
     * session超时时间,单位为秒
     */
    public final static int SESSION_TIMEOUT = 60 * 60;

	/**
	 * shiro-redis的session对象前缀
	 */
    @Autowired
    private ShardedJedisPool shardedJedisPool;

	/**
	 * The Redis key prefix for the sessions 
	 */
    @Setter@Getter
	private String keyPrefix = "shiro_redis_session:";
	
	@Override
	public void update(Session session) throws UnknownSessionException {
		this.saveSession(session);
	}
	
	/**
	 * save session
	 * @param session
	 * @throws org.apache.shiro.session.UnknownSessionException
	 */
	private void saveSession(Session session) throws UnknownSessionException{
		if(session == null || session.getId() == null){
			log.error("session or session id is null");
			return;
		}

        session.setTimeout(SESSION_TIMEOUT * 1000);
		byte[] key = getByteKey(session.getId());
		byte[] value = SerializeUtil.serialize(session);
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        shardedJedis.set(key, value);
        shardedJedis.expire(key, SESSION_TIMEOUT);
	}

	@Override
	public void delete(Session session) {
		if(session == null || session.getId() == null){
			log.error("session or session id is null");
			return;
		}
        ShardedJedis shardedJedis = shardedJedisPool.getResource();
        shardedJedis.del(this.getByteKey(session.getId()));

	}

	@Override
	public Collection<Session> getActiveSessions() {
		Set<Session> sessions = Sets.newHashSet();

        ShardedJedis shardedJedis = shardedJedisPool.getResource();
		Set<String> keys = shardedJedis.hkeys(this.keyPrefix + "*");
		if(keys != null && keys.size()>0){
			for(String key:keys){
				Session s = (Session)SerializeUtil.unserialize(shardedJedis.get(key).getBytes());
				sessions.add(s);
			}
		}
		
		return sessions;
	}

	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId = this.generateSessionId(session);  
        this.assignSessionId(session, sessionId);
        this.saveSession(session);
		return sessionId;
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		if(sessionId == null){
			log.error("session id is null");
			return null;
		}

        ShardedJedis shardedJedis = shardedJedisPool.getResource();
		Session session = (Session)SerializeUtil.unserialize(shardedJedis.get(this.getByteKey(sessionId)));
		return session;
	}
	
	/**
	 * 获得byte[]型的key
	 * @param sessionId
	 * @return
	 */
	private byte[] getByteKey(Serializable sessionId){
		String preKey = this.keyPrefix + sessionId;
		return preKey.getBytes();
	}
	
}
