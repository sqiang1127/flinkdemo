package com.xdf.flink.demo02

import org.apache.flink.api.java.utils.ParameterTool
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment}
import org.apache.flink.api.scala._

object SocketStreamWordCount {
  def main(args: Array[String]): Unit = {
    val params = ParameterTool.fromArgs(args)
    val hostname = params.get("host")
    val port = params.getInt("port")

    val env = StreamExecutionEnvironment.getExecutionEnvironment
    val textDstream: DataStream[String] = env.socketTextStream(hostname,port)
    val wordcount:DataStream[(String,Int)] = textDstream
      .flatMap(_.split("\\s")).setParallelism(1)
      .filter(_.nonEmpty).disableChaining().setParallelism(2)
      .map((_,1)).slotSharingGroup("aaa").setParallelism(3)
      .keyBy(0)
      .sum(1).setParallelism(7)
    wordcount.print().setParallelism(10)

    //启动任务执行
    env.execute("socket stream world count")

  }
}
