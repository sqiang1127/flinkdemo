package com.xdf.flink.demo03.bean

import com.google.gson.Gson


case class Canal(var emptyCount:Long,
                 var logFileName:String,
                 var dbName:String,
                 var logFileOffset:Long,
                 var eventType:String,
                 var columnValueList:String,
                 var tableName:String,
                 var timestamp:Long)

object Canal {
  def apply(json:String): Canal = {
    val gson = new Gson()
    val canal = gson.fromJson(json, classOf[Canal])
    canal
  }

  def main(args: Array[String]): Unit = {
    val json = "{\"emptyCount\":1,\"logFileName\":\"mysql-bin.000022\",\"dbName\":\"pyg\",\"logFileOffset\":676,\"eventType\":\"INSERT\",\"columnValueList\":\"\",\"tableName\":\"commodity\",\"timestamp\":1615432068000}"
    println(Canal(json).dbName)

  }

}
