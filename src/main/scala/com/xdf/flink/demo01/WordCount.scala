package com.xdf.flink.demo01



import org.apache.flink.api.scala.{DataSet, ExecutionEnvironment}
import org.apache.flink.streaming.api.scala.createTypeInformation

object WordCount {
  def main(args: Array[String]): Unit = {
    val env = ExecutionEnvironment.getExecutionEnvironment
    val inputDS:DataSet[String] = env.readTextFile("/Users/shiqiang/gitclone/flinkdemo/src/main/resources//file/wordcount.txt")
    var wordCount = inputDS.flatMap(_.split(" ")).map((_,1)).groupBy(0).sum(1)
    wordCount.print()
  }
}
