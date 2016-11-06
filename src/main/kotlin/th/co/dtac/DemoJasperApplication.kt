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

        var string = "abc\u0E40\u0E15\u0E49\u0E22"
        val utf8 = string.toByteArray(charset("UTF-8"))
        string = kotlin.text.String(utf8, Charset.forName("UTF-8"))
        println(string)


        val jrxmlfile : String= "/home/araya/Workspace/demo-jasper/src/main/kotlin/jasperreport/jasper_report_template.jrxml"
        val data = Country()
        data.name = "\u0E2D\u0E32\u0E23\u0E0D\u0E32"
        data.country = "Thailand"
        val data1 = Country()
        data1.name = "\u0E40\u0E15\u0E49\u0E22"
        data1.country = "English"
        val data2 = Country()
        data2.name = "Araya"
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


