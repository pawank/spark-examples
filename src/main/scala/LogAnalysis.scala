package kumar.pawan

import org.apache.spark.SparkContext
import org.apache.spark.sql.SparkSession

import scala.concurrent.{ExecutionContext, Future}

class LogAnalysis(spark: SparkSession) {
  implicit val ec = ExecutionContext.global

  //Interested only in error lines containing substring matching in ignored errors sequence
  val ignoredErrors = Seq("A COM exception has been encountered:", "Data input error after valid decoding:")

  val folderName = "/Users/pawan/git/products/data-analytics/logs"

  def errorsCount(): Long = {
    val _ignoredErrors = this.ignoredErrors
    val files = spark.read.textFile(folderName)
    val errors = files.filter(x => {
      if (_ignoredErrors.foldLeft(false)((a, b) => a || x.contains(b))) true else false
    })
    val count = errors.count()
    // Use collect on driver side only when enough ram exists
    // errors.collect().map(f => { println(f) })
    count
  }

}

object LogAnalysis {
  implicit val ec = ExecutionContext.global
  def run(spark: SparkSession) = {
    val log = new LogAnalysis(spark)
    val counter = spark.time(log.errorsCount())
    println(s"Count: $counter")
  }
}
