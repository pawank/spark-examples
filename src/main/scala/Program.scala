import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

object Program {
  //Spark < 2.0 approach for creating Spark context
  /*
  val conf = new SparkConf()
    .setMaster("local[*]")
    .setAppName("SparkExamples")
  val sc = new SparkContext(conf)
  */

  def main(args: Array[String]): Unit = {
    println("Program 1.0")
    val spark = SparkSession.builder.appName("SparkExamples").config("spark.master", "local[*]").getOrCreate()
    kumar.pawan.LogAnalysis.run(spark)
    spark.stop()
  }

}
