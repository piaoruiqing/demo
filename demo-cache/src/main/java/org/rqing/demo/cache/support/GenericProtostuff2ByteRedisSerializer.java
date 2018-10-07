package org.rqing.demo.cache.support;

import java.util.Optional;

import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.runtime.RuntimeSchema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
/**
 * generic protostuff redis serializer
 * 
 * @description 
 * @author piaoruiqing
 * @date: 2018/10/05 23:00
 *
 * @since JDK 1.8
 */
public class GenericProtostuff2ByteRedisSerializer implements RedisSerializer<Object>{

    private final RuntimeSchema<Item> schema = RuntimeSchema.createFrom(Item.class);

	@Override
	public byte[] serialize(Object object) throws SerializationException {
		byte[] bytes = ProtostuffIOUtil.toByteArray(new Item(object), schema, LinkedBuffer.allocate());
		return bytes;
	}

	@Override
	public Object deserialize(byte[] bytes) throws SerializationException {
		return Optional.ofNullable(bytes).map(b -> {
							Item item = new Item();
							ProtostuffIOUtil.mergeFrom(b, item, schema);
							return Optional.ofNullable(item).map(v -> v.getObject()).orElse(null);
						}).orElse(null);
	}
	
	@NoArgsConstructor
	@AllArgsConstructor
	private static class Item {
		@Getter
		private Object object;
	}

}
