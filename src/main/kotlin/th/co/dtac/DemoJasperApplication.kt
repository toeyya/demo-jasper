package th.co.dtac


import net.sf.jasperreports.engine.*
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource
import net.sf.jasperreports.view.JasperViewer
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import th.co.dtac.model.Country
import java.nio.charset.Charset


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
            JasperFillManager.fillReportToFile(jasperReport, parameters,beanColDataSource)
        }catch (ex: JRException){
            ex.printStackTrace()
        }
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(DemoJasperApplication::class.java, *args)

}


