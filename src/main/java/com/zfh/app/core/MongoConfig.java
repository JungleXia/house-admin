package com.zfh.app.core;

import java.math.BigDecimal;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.bson.types.Decimal128;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;

/**
 * spring-boot mongodb 配置
 * 
 * @author CB
 * 
 * @dateTime 2019年4月11日下午2:05:58
 */

@Configuration
public class MongoConfig {

	@Value("${spring.data.mongodb.uri}")
	private String MONGO_URI1;

	@Value("${spring.data.mongodb2.uri}")
	private String MONGO_URI2;
//	
//	@Value("${spring.data.mongodb3.uri}")
//	private String MONGO_URI_RENT;

	@Bean
	public MongoMappingContext mongoMappingContext() {
		MongoMappingContext mappingContext = new MongoMappingContext();
		return mappingContext;
	}

	@Bean // 使用自定义的typeMapper去除写入mongodb时的“_class”字段
	public MappingMongoConverter mappingMongoConverter1() throws Exception {
		DefaultDbRefResolver dbRefResolver = new DefaultDbRefResolver(this.mongoDbFactory());
		MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, this.mongoMappingContext());
		converter.setTypeMapper(new DefaultMongoTypeMapper(null));
		converter.setCustomConversions(customConversions());
		converter.afterPropertiesSet();
		return converter;
	}
	
	@Bean // 使用自定义的typeMapper去除写入mongodb时的“_class”字段
	public MappingMongoConverter mappingMongoConverter2() throws Exception {
		DefaultDbRefResolver dbRefResolver = new DefaultDbRefResolver(this.mongoDbFactory2());
		MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, this.mongoMappingContext());
		converter.setTypeMapper(new DefaultMongoTypeMapper(null));
		converter.setCustomConversions(customConversions());
		converter.afterPropertiesSet();
		return converter;
	}
//	
//	@Bean // 使用自定义的typeMapper去除写入mongodb时的“_class”字段
//	public MappingMongoConverter mappingMongoConverter3() throws Exception {
//		DefaultDbRefResolver dbRefResolver = new DefaultDbRefResolver(this.mongoDbFactory3());
//		MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, this.mongoMappingContext());
//		converter.setTypeMapper(new DefaultMongoTypeMapper(null));
//		converter.setCustomConversions(customConversions());
//		converter.afterPropertiesSet();
//		return converter;
//	}

	@Bean
	@Primary
	public MongoDbFactory mongoDbFactory() throws UnknownHostException {
		MongoClientOptions.Builder optionsBuilder = MongoClientOptions.builder();
		optionsBuilder.connectTimeout(1000 * 60);
		optionsBuilder.socketTimeout(1000 * 60 * 60);
		optionsBuilder.serverSelectionTimeout(1000 * 60);
		return new SimpleMongoDbFactory(new MongoClientURI(MONGO_URI1, optionsBuilder));
	}

	@Bean(name = "mongoTemplate")
	@Primary
	public MongoTemplate mongoTemplate() throws Exception {
		return new MongoTemplate(this.mongoDbFactory(), this.mappingMongoConverter1());
	}

	@Bean
	public MongoDbFactory mongoDbFactory2() throws UnknownHostException {
		MongoClientOptions.Builder optionsBuilder = MongoClientOptions.builder();
		optionsBuilder.connectTimeout(1000 * 60);
		optionsBuilder.socketTimeout(1000 * 120);
		optionsBuilder.serverSelectionTimeout(1000 * 60);
		return new SimpleMongoDbFactory(new MongoClientURI(MONGO_URI2, optionsBuilder));
	}

	@Bean(name = "mongoTemplate2")
	public MongoTemplate mongoTemplate2() throws Exception {
		return new MongoTemplate(mongoDbFactory2(), this.mappingMongoConverter2());
	}
	
//	@Bean
//	public MongoDbFactory mongoDbFactory3() throws UnknownHostException {
//		MongoClientOptions.Builder optionsBuilder = MongoClientOptions.builder();
//		optionsBuilder.connectTimeout(1000 * 60);
//		optionsBuilder.socketTimeout(1000 * 120);
//		optionsBuilder.serverSelectionTimeout(1000 * 60);
//		return new SimpleMongoDbFactory(new MongoClientURI(MONGO_URI_RENT, optionsBuilder));
//	}
//
//	@Bean(name = "mongoTemplate3")
//	public MongoTemplate mongoTemplate3() throws Exception {
//		return new MongoTemplate(mongoDbFactory3(), this.mappingMongoConverter3());
//	}
	
	@Bean
    public CustomConversions customConversions() {
        List<Converter<?, ?>> converters = new ArrayList<>();
        converters.add(new BigDecimalDoubleConverter());
//        converters.add(new Decimal128BigDecimalConverter());
        return new CustomConversions(converters);
    }

}


@WritingConverter
class BigDecimalDoubleConverter implements Converter<BigDecimal, Double> {
	@Override
	public Double convert(@NotNull BigDecimal source) {
		return source.doubleValue();
	}
}

@ReadingConverter
class Decimal128BigDecimalConverter implements Converter<Decimal128, BigDecimal> {

	@Override
	public BigDecimal convert(@NotNull Decimal128 source) {
		return source.bigDecimalValue();
	}

}
