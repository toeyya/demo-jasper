package th.co.dtac


import net.sf.jasperreports.engine.*
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource
import net.sf.jasperreports.view.JasperViewer
import org.apache.commons.io.FileUtils
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import th.co.dtac.model.Country
import java.io.File
import java.io.FileOutputStream
import java.nio.charset.Charset
import java.util.*


@SpringBootApplication
open class DemoJasperApplication{

    @Bean
    open fun init() = CommandLineRunner {

        val jrxmlfile : String= "/home/araya/Workspace/demo-jasper/src/main/kotlin/jasperreport/jasper_report_template.jrxml"
        val data = Country()
        data.name = "กุมภาพันธ์"
        data.country = "Thailand"
        val data1 = Country()
        data1.name = "อารญา"
        data1.country = "English"
        val data2 = Country()
        data2.name = "ดีแทค"
        data2.country = "France"

        val dataList : MutableList<Country> = mutableListOf()
        dataList.add(data)
        dataList.add(data1)
        dataList.add(data2)

        try{
            val beanColDataSource = JRBeanCollectionDataSource(dataList)
            val jasperReport = JasperCompileManager.compileReportToFile(jrxmlfile)
            val parameters : MutableMap<String,Any> = mutableMapOf()
            val jasperPrint = JasperFillManager.fillReportToFile(jasperReport, parameters,beanColDataSource)

            val initialFile = File(jasperPrint)
            val inputStream = FileUtils.openInputStream(initialFile)

            val pdf = File.createTempFile("/home/araya/Workspace/demo-jasper/src/main/kotlin/jasperreport/output.", ".pdf")
            val outputStream = FileUtils.openOutputStream(pdf)
            JasperExportManager.exportReportToPdfStream(inputStream, outputStream)

        }catch (ex: JRException){
            ex.printStackTrace()
        }
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(DemoJasperApplication::class.java, *args)

}


