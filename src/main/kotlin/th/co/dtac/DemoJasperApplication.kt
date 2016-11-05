package th.co.dtac


import net.sf.jasperreports.engine.JRException
import net.sf.jasperreports.engine.JasperCompileManager
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
open class DemoJasperApplication{

    @Bean
    open fun init() = CommandLineRunner {

        val jrxmlfile : String= "/home/araya/Workspace/demo-jasper/src/main/kotlin/jasperreport/jasper_report_template.jrxml"
        try{
            JasperCompileManager.compileReportToFile(jrxmlfile)

        }catch (ex: JRException){
            ex.printStackTrace()
        }
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(DemoJasperApplication::class.java, *args)

}


