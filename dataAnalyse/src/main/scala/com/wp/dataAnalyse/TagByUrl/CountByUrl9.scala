package com.wp.dataAnalyse.TagByUrl

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.sql.{DataFrame, SQLContext}  
import com.zb.ac.AC2Util
import java.util.ArrayList
import scala.collection.Seq
import collection.JavaConverters._
import org.apache.spark.broadcast.Broadcast
import com.zb.ac.ACtest
import com.zb.ac.AC2Util3

object CountByUrl9 extends Serializable{
  def main(args: Array[String]): Unit = {
    val conf=new SparkConf().setAppName("CountUsersByUrl")
    val sc=new SparkContext(conf)  
    val sqc=new SQLContext(sc)
    case class user_tag_class(mdn:String,url:String,tag:String)//注册一个类
    //读取标签配置文件，从hdfs
    val tag_conf_data = sc.textFile(args(2))
    var list1 = ("")::Nil 
    var arr = tag_conf_data.collect()
    for(i <- 0 to arr.length-1){
      println("==arr:"+arr(i))
      list1=(arr(i))::list1
      println("list1===>"+list1)
    }
    println("====>"+list1)
    var seq_data:Seq[String] = list1
    AC2Util3.list1 = seq_data.asJava
    val ac = new AC2Util3();
    println("-----ac----"+ac.getTag2("www.baidu.comqq.com").replace(",", "|"))
    @transient
    val tag_list=sc.broadcast(list1)
    println("tag_list.value(0)---++++++------"+tag_list.value(0))
    @transient
    val ac1 = sc.broadcast(ac)
    val tagStr =  ac1.value.getTag2("www.baidu.comqq.com").replace(",", "|")
    println("----tagStr--------->"+tagStr)
    
    val data=sc.textFile(args(0))
    //清洗数据文件 打标签
    var a = data.flatMap{line=>
      
      /*println("tag_list.value(0)---------"+tag_list.value(0))
      println(tag_list.value)
      AC2Util3.list1 = tag_list.value.asJava 
      val ac_tmp = new AC2Util3();*/
      /*val x = line.split("\\|")
      if (x.length == 1) None
      else {
//        println("x===>" + x.length + "---" + x(0))
        var mdn = ""
        if (x.length > 1) {
          //         mdn ==  x(1)
          mdn = x(1)
        }
        var url = ""
        if (x.length > 29) {
          //         url ==  x(29)
          url = x(29)
        }*/
      
      val x = line.split("\t")
      if (x.length == 1) None
      else {
//        println("x===>" + x.length + "---" + x(0))
        var mdn = ""
        if (x.length > 3) {
          mdn = x(3)
        }
        var url = ""
        if (x.length > 23) {
          url = x(23)
        }
        
         val tags =  AC2Util3.getTag(url).replace(",", "|")
        if("".equals(tags)||tags==null){
          None
        }else{
//        println("===>" + mdn + "---" + url + "---" + tags)
//        var x1 = Array(Array(mdn, url),tags)
          var x1=List(List(mdn,url), tags)
           var tmpList=(mdn,url)::Nil
           var list = (tmpList,tags)::Nil
        list
        }
      }
    }
    println("-----------------------------------")
//     a.collect().foreach(println)
     println("-------------------aaaaaaaaaaaaa---------------")
    val b = a.flatMapValues(_.split("\\|")).map(x=>{
      var mdn=x._1(0)._1
      var url=x._1(0)._2
      var tag=x._2
      (mdn,url,tag)
    })
//b.collect.foreach(println(_))


val df=sqc.createDataFrame(b)  // 生成一个dataframe  
    val df_name_colums=df.toDF("mdn","url","tagid")  //给df的每个列取名字
    df_name_colums.registerTempTable("user_pay_table")     //注册临时表 
    var sql:String = "select tagid,count(distinct mdn),count(1) from user_pay_table group by tagid"
    val rs: DataFrame =sqc.sql(sql)  
//    rs.foreach(x=>println(x))  
//    user_transform.saveAsTextFile("/home/wangtuntun/test_file4.txt")  
//    val rs_rdd=rs.map( x=>( x(0),x(1),x(2) ) )  
    println("--------------yyyyasasassadsad------------------------")
    val rs_arr=rs.collect() //rs转为rdd
//    val rs_rdd = sc.parallelize(rs_arr).map(z=>( z(0),z(1),z(2)))
    val rs_rdd = sc.parallelize(rs_arr).map{z=>
      var value=  z(0)+"|"+z(1)+"|"+z(2)
      value
    }
    rs_rdd.collect().foreach(println)
    rs_rdd.saveAsTextFile(args(1)) 
//    var b = sc.parallelize(a.collect())
//   b.flatm
    sc.stop();  
  }
}