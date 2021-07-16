package com.xdf.flink.demo03

import com.xdf.flink.demo03.bean.Canal
import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.streaming.api.scala._
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010
import org.apache.kafka.common.serialization.StringDeserializer

import java.util.Properties

object KafkaConsumerFlink {
  def main(args: Array[String]): Unit = {
    val prop = new Properties()
    prop.setProperty("bootstrap.servers","172.24.29.179:9092,172.24.29.198:9092172.24.29.200:9092")
    prop.setProperty("group.id","flink_kafka_demo")
    prop.put("enable.auto.commit", "false")
    //        props.put("auto.commit.interval.ms", "10000");
    prop.put("session.timeout.ms", "10000")
    prop.put("auto.offset.reset", "earliest")
//    prop.put("key.deserializer", classOf[StringDeserializer].getName)
//    prop.put("value.deserializer", classOf[StringDeserializer].getName)
//    prop.put("security.protocol", "SASL_PLAINTEXT")
//    prop.put("sasl.mechanism", "PLAIN")
//    prop.put("sasl.jaas.config", "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"rw43\" password=\"vwh3Kp\";")
    val env = StreamExecutionEnvironment.getExecutionEnvironment
    val ds = env.addSource(new FlinkKafkaConsumer010[String]("mysql_oc_order",new SimpleStringSchema(),prop))
    ds.map(json =>{
        val encode = "GB2312"
        new String(json.getBytes(encode),encode)+"  ======================================="
    }).print()
    env.execute("kafkaflinkdemo")
  }
}
